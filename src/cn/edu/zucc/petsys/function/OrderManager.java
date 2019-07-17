package cn.edu.zucc.petsys.function;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.petsys.bean.BeanOrd;
import cn.edu.zucc.petsys.ifunction.IfOrderManager;
import cn.edu.zucc.petsys.tool.BaseException;
import cn.edu.zucc.petsys.tool.BnException;
import cn.edu.zucc.petsys.tool.JDBCManager;

public class OrderManager implements IfOrderManager {

	@Override
	public void addOrd(int iid, int cid, int oquantity, Double oprice) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn = null;
		int max_oid;
		String sql = null;
		try {
			if(iid==0||cid==0)
			{
				throw new BnException("无法添加订单！");
			}
			conn = JDBCManager.getConnection();
			sql = "select max(oid) from BeanOrd ";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next())
				max_oid = rs.getInt(1) + 1;
			else
				max_oid = 1;
			rs.close();
			
			sql = "insert into BeanOrd values(?,?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, max_oid);
			pst.setInt(2, iid);
			pst.setInt(3, cid);
			pst.setInt(4, oquantity);
			pst.setDouble(5, oprice);
			pst.setTimestamp(6, new java.sql.Timestamp(System.currentTimeMillis()));
			pst.setString(7, "审核");
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally
		{
			try
			{
				if(conn!=null)
					conn.close();
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	public List<BeanOrd> loadAllOrd() throws BaseException
	{
		Connection conn = null;
		String sql = null;
		List<BeanOrd> lbo = new ArrayList<BeanOrd>();
		try {
			conn = JDBCManager.getConnection();
			sql = "select * from BeanOrd ";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				BeanOrd bo = new BeanOrd();
				bo.setOid(rs.getInt(1));
				bo.setIid(rs.getInt(2));
				bo.setCid(rs.getInt(3));
				bo.setOquantity(rs.getInt(4));
				bo.setOprice(rs.getDouble(5));
				bo.setOtime(rs.getTimestamp(6));
				bo.setOstatus(rs.getString(7));
				lbo.add(bo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally
		{
			try
			{
				if(conn!=null)
					conn.close();
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return lbo;
	}
	public BeanOrd loadOneOrd(int oid) throws BaseException
	{
		BeanOrd bo = new BeanOrd();
		String sql = null;
		Connection conn = null;
		try {
			if(oid==0)
				throw new BnException("订单编号为0");
			conn = JDBCManager.getConnection();
			sql = "select * from BeanOrd where oid=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, oid);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next()) 
				throw new BnException("内部错误，database找不到该商品");
			
			bo.setOid(rs.getInt(1));
			bo.setIid(rs.getInt(2));
			bo.setCid(rs.getInt(3));
			bo.setOquantity(rs.getInt(4));
			bo.setOprice(rs.getDouble(5));
			bo.setOtime(rs.getTimestamp(6));
			bo.setOstatus(rs.getString(7));
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally
		{
			try
			{
				if(conn!=null)
					conn.close();
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return bo;
	}
	public List<BeanOrd> loadUnfilledOrd() throws BaseException
	{
		Connection conn = null;
		String sql = null;
		List<BeanOrd> lbo = new ArrayList<BeanOrd>();
		try {
			conn = JDBCManager.getConnection();
			sql = "select * from BeanOrd where ostatus=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, "审核");
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				BeanOrd bo = new BeanOrd();
				bo.setOid(rs.getInt(1));
				bo.setIid(rs.getInt(2));
				bo.setCid(rs.getInt(3));
				bo.setOquantity(rs.getInt(4));
				bo.setOprice(rs.getDouble(5));
				bo.setOtime(rs.getTimestamp(6));
				bo.setOstatus(rs.getString(7));
				lbo.add(bo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally
		{
			try
			{
				if(conn!=null)
					conn.close();
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return lbo;
	}
	public void sendOrd(int oid) throws BaseException
	{
		Connection conn = null;
		String sql = null;
		try {
			if(oid==0)
				throw new BnException("内部错误，订单编号为0（发货）");
			conn = JDBCManager.getConnection();
			sql = "select * from BeanOrd where oid=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, oid);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next())
				throw new BnException("内部错误，查不到该商品（发货）");
			rs.close();
			
			//更改status
			sql = "update BeanOrd set ostatus=? where oid=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, "已发货");
			pst.setInt(2, oid);
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally
		{
			try
			{
				if(conn!=null)
					conn.close();
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	public void cancelOrd(int oid) throws BaseException
	{
		Connection conn = null;
		String sql = null;
		try {
			if(oid==0)
				throw new BnException("内部错误，订单编号为0（商家取消）");
			conn = JDBCManager.getConnection();
			sql = "select * from BeanOrd where oid=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, oid);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next())
				throw new BnException("内部错误，查不到该商品（商家取消）");
			rs.close();
			
			//更改status
			sql = "update BeanOrd set ostatus=? where oid=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, "商家取消");
			pst.setInt(2, oid);
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally
		{
			try
			{
				if(conn!=null)
					conn.close();
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	public List<BeanOrd> loadOrdwithStatus(String stat) throws BaseException
	{
		Connection conn = null;
		String sql = null;
		List<BeanOrd> lbo = new ArrayList<BeanOrd>();
		try {
			conn = JDBCManager.getConnection();
			sql = "select * from BeanOrd where ostatus=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, stat);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				BeanOrd bo = new BeanOrd();
				bo.setOid(rs.getInt(1));
				bo.setIid(rs.getInt(2));
				bo.setCid(rs.getInt(3));
				bo.setOquantity(rs.getInt(4));
				bo.setOprice(rs.getDouble(5));
				bo.setOtime(rs.getTimestamp(6));
				bo.setOstatus(rs.getString(7));
				lbo.add(bo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally
		{
			try
			{
				if(conn!=null)
					conn.close();
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return lbo;
	}
	public void undoOrd(int iid,int quantity) throws BaseException
	{
		Connection conn = null;
		String sql = null;
		try {
			if(iid==0)
				throw new BnException("内部错误，商品编号为0");
			conn = JDBCManager.getConnection();
			sql = "select * from BeanInfo where iid=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, iid);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next())
				throw new BnException("内部错误，找不到该商品");
			
			sql = "update BeanInfo set iquantity = iquantity + ? where iid=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, quantity);
			pst.setInt(2, iid);
			pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally
		{
			try
			{
				if(conn!=null)
					conn.close();
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	public List<BeanOrd> loadcusStatusOrd(String stat,int cid) throws BaseException
	{

		Connection conn = null;
		String sql = null;
		List<BeanOrd> lbo = new ArrayList<BeanOrd>();
		try {
			conn = JDBCManager.getConnection();
			sql = "select * from BeanOrd where ostatus=? and cid=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, stat);
			pst.setInt(2, cid);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				BeanOrd bo = new BeanOrd();
				bo.setOid(rs.getInt(1));
				bo.setIid(rs.getInt(2));
				bo.setCid(rs.getInt(3));
				bo.setOquantity(rs.getInt(4));
				bo.setOprice(rs.getDouble(5));
				bo.setOtime(rs.getTimestamp(6));
				bo.setOstatus(rs.getString(7));
				lbo.add(bo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally
		{
			try
			{
				if(conn!=null)
					conn.close();
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return lbo;
	}
	public List<BeanOrd> loadcusAllord(int cid) throws BaseException
	{
		Connection conn = null;
		String sql = null;
		List<BeanOrd> lbo = new ArrayList<BeanOrd>();
		try {
			conn = JDBCManager.getConnection();
			sql = "select * from BeanOrd where cid=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, cid);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				BeanOrd bo = new BeanOrd();
				bo.setOid(rs.getInt(1));
				bo.setIid(rs.getInt(2));
				bo.setCid(rs.getInt(3));
				bo.setOquantity(rs.getInt(4));
				bo.setOprice(rs.getDouble(5));
				bo.setOtime(rs.getTimestamp(6));
				bo.setOstatus(rs.getString(7));
				lbo.add(bo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally
		{
			try
			{
				if(conn!=null)
					conn.close();
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return lbo;
	}
	public void cusCancelOrd(int oid) throws BaseException
	{
		Connection conn = null;
		String sql = null;
		try {
			if(oid==0)
				throw new BnException("内部错误，订单编号为0（用户取消）");
			conn = JDBCManager.getConnection();
			sql = "select * from BeanOrd where oid=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, oid);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next())
				throw new BnException("内部错误，查不到该商品（用户取消）");
			rs.close();
			
			//更改status
			sql = "update BeanOrd set ostatus=? where oid=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, "用户取消");
			pst.setInt(2, oid);
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally
		{
			try
			{
				if(conn!=null)
					conn.close();
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	public void deleteOrd(int oid) throws BaseException
	{
		Connection conn = null;
		String sql = null;
		try {
			conn = JDBCManager.getConnection();
			if(oid==0)
				throw new BnException("oid==0,deleteOrd()");
			sql = "select * from BeanOrd where oid=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, oid);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next())
				throw new BnException("查不到该预约单");
			
			sql ="delete from BeanOrd where oid = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, oid);
			pst.execute();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally
		{
			try
			{
				if(conn!=null)
					conn.close();
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	public void gotOrd(int oid) throws BaseException
	{
		Connection conn = null;
		String sql = null;
		try {
			if(oid==0)
				throw new BnException("内部错误，订单编号为0（确认）");
			conn = JDBCManager.getConnection();
			sql = "select * from BeanOrd where oid=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, oid);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next())
				throw new BnException("内部错误，查不到该商品（确认）");
			rs.close();
			
			//更改status
			sql = "update BeanOrd set ostatus=? where oid=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, "已收货");
			pst.setInt(2, oid);
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally
		{
			try
			{
				if(conn!=null)
					conn.close();
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
}
