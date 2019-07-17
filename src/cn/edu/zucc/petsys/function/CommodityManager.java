package cn.edu.zucc.petsys.function;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.petsys.bean.BeanInfo;
import cn.edu.zucc.petsys.ifunction.IfCommodityManager;
import cn.edu.zucc.petsys.tool.BaseException;
import cn.edu.zucc.petsys.tool.BnException;
import cn.edu.zucc.petsys.tool.JDBCManager;

public class CommodityManager implements IfCommodityManager {

	public void addCommodity(String iname,int tid,String ibrand, Double iprice, int iquantity) throws BaseException
	{
		Connection conn = null;
		String sql = null;
		int max_iid;
		//System.out.println(iname+"  "+tid+"  "+ibrand+"  "+iquantity+"  "+iprice);
		try {
			conn = JDBCManager.getConnection();
			if("".equals(iname))
				throw new BnException("商品名不能为空！");
			if("".equals(ibrand))
				throw new BnException("商品品牌不能为空！");
			if(iprice==0||iquantity==0)
				throw new BnException("单价或库存量不能为空！");
			
			sql = "select iid from BeanInfo where iname=? and ibrand=? and tid=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, iname);
			pst.setString(2, ibrand);
			pst.setInt(3, tid);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next())
				throw new BnException("已存在该类别的商品");
			else
			{
			rs.close();
			//查找最大iid
			sql = "select max(iid) from BeanInfo";
			pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs1 = pst.executeQuery();
			if(rs1.next())
				max_iid = rs1.getInt(1) + 1;
			else
				max_iid = 1;
			rs1.close();
			//插入
			sql = "insert into BeanInfo(iid,iname,tid,ibrand,iquantity,price) values(?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, max_iid);
			pst.setString(2, iname);
			pst.setInt(3, tid);
			pst.setString(4, ibrand);
			pst.setInt(5, iquantity);
			pst.setDouble(6, iprice);
			pst.execute();
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
	}
	public List<BeanInfo> loadAll() throws BaseException
	{
		List<BeanInfo> lbi = new ArrayList<BeanInfo>();
		String sql = null;
		Connection conn = null;
		try {
			conn = JDBCManager.getConnection();
			sql = "select * from BeanInfo";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				BeanInfo bi = new BeanInfo();
				bi.setIid(rs.getInt(1));
				bi.setIname(rs.getString(2));
				bi.setTid(rs.getInt(3));
				bi.setIbrand(rs.getString(4));
				bi.setIquantity(rs.getInt(5));
				bi.setIprice(rs.getDouble(6));
				lbi.add(bi);
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
		return lbi;
	}
	public BeanInfo getCommodity(int iid) throws BaseException
	{
		Connection conn = null;
		String sql = null;
		BeanInfo bi = new BeanInfo();
		try {
			conn = JDBCManager.getConnection();
			sql = "select * from BeanInfo where iid=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, iid);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next())
				throw new BnException("错误！未知的商品编号");
			bi.setIid(iid);
			bi.setIname(rs.getString(2));
			bi.setTid(rs.getInt(3));
			bi.setIbrand(rs.getString(4));
			bi.setIquantity(rs.getInt(5));
			bi.setIprice(rs.getDouble(6));
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
		return bi;
	}
	public void updateOneCommodity(int iid,String iname,int tid,String ibrand,int quantity,Double price ) throws BaseException
	{
		Connection conn = null;
		String sql = null;
		try {
			conn = JDBCManager.getConnection();
			if("".equals(iname))
				throw new BnException("商品名称不能为空！");
			sql = "select * from BeanInfo where iid=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, iid);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next())
				throw new BnException("错误！没有该商品不能更新！");
			rs.close();
			sql = "update BeanInfo set iname=?,tid=?,ibrand=?,iquantity=?,price=? where iid=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, iname);
			pst.setInt(2, tid);
			pst.setString(3, ibrand);
			pst.setInt(4, quantity);
			pst.setDouble(5, price);
			pst.setInt(6, iid);
			pst.executeUpdate();
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
	public void deleteOneCommodity(int iid) throws BaseException
	{
		Connection conn = null;
		String sql = null;
		try {
			conn = JDBCManager.getConnection();
			sql = "select * from BeanInfo where iid=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, iid);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next())
				throw new BnException("该商品不存在，无法删除！");
			rs.close();
			//DELETE
			sql = "delete from BeanInfo where iid=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, iid);
			pst.execute();
			
			//整理序号
			sql = "update BeanInfo set iid=iid-1 where iid>?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, iid);
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
	public List<BeanInfo> fuzzyNameSearch(String iname) throws BaseException
	{
		Connection conn = null;
		String sql = null;
		List<BeanInfo> lbi = new ArrayList<BeanInfo>();
		try {
			conn = JDBCManager.getConnection();
			//模糊查询找不到的情况
			sql = "SELECT * FROM petsystem.BeanInfo where iname like ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, '%'+iname+'%');
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next())
				throw new BnException("找不到匹配的商品");
			rs.close();
			java.sql.ResultSet rs1 = pst.executeQuery();
			while(rs1.next())
			{
				BeanInfo bi = new BeanInfo();
				bi.setIid(rs1.getInt(1));
				bi.setIname(rs1.getString(2));
				bi.setTid(rs1.getInt(3));
				bi.setIbrand(rs1.getString(4));
				bi.setIquantity(rs1.getInt(5));
				bi.setIprice(rs1.getDouble(6));
				lbi.add(bi);
			}
			rs1.close();
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
		return lbi;
	}
	public List<BeanInfo> fuzzyBrandSearch(String ibrand) throws BaseException
	{
		Connection conn = null;
		String sql = null;
		List<BeanInfo> lbi = new ArrayList<BeanInfo>();
		try {
			conn = JDBCManager.getConnection();
			//模糊查询找不到的情况
			sql = "SELECT * FROM petsystem.BeanInfo where ibrand like ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, '%'+ibrand+'%');
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next())
				throw new BnException("找不到匹配的商品");
			rs.close();
			java.sql.ResultSet rs1 = pst.executeQuery();
			while(rs1.next())
			{
				BeanInfo bi = new BeanInfo();
				bi.setIid(rs1.getInt(1));
				bi.setIname(rs1.getString(2));
				bi.setTid(rs1.getInt(3));
				bi.setIbrand(rs1.getString(4));
				bi.setIquantity(rs1.getInt(5));
				bi.setIprice(rs1.getDouble(6));
				lbi.add(bi);
			}
			rs1.close();
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
		return lbi;
	}
	public List<BeanInfo> fuzzyNBSearch(String iname, String ibrand) throws BaseException
	{
		Connection conn = null;
		String sql = null;
		List<BeanInfo> lbi = new ArrayList<BeanInfo>();
		try {
			conn = JDBCManager.getConnection();
			//模糊查询找不到的情况
			sql = "SELECT * FROM petsystem.BeanInfo where iname like ? and ibrand like ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, '%'+iname+'%');
			pst.setString(2, '%'+ibrand+'%');
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next())
				throw new BnException("找不到匹配的商品");
			rs.close();
			java.sql.ResultSet rs1 = pst.executeQuery();
			while(rs1.next())
			{
				BeanInfo bi = new BeanInfo();
				bi.setIid(rs1.getInt(1));
				bi.setIname(rs1.getString(2));
				bi.setTid(rs1.getInt(3));
				bi.setIbrand(rs1.getString(4));
				bi.setIquantity(rs1.getInt(5));
				bi.setIprice(rs1.getDouble(6));
				lbi.add(bi);
			}
			rs1.close();
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
		return lbi;
	}
	public List<BeanInfo> accuracyNameSearch(String iname) throws BaseException
	{
		Connection conn = null;
		String sql = null;
		List<BeanInfo> lbi = new ArrayList<BeanInfo>();
		try {
			conn = JDBCManager.getConnection();
			//模糊查询找不到的情况
			sql = "SELECT * FROM petsystem.BeanInfo where iname=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, iname);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next())
				throw new BnException("找不到匹配的商品");
			rs.close();
			java.sql.ResultSet rs1 = pst.executeQuery();
			while(rs1.next())
			{
				BeanInfo bi = new BeanInfo();
				bi.setIid(rs1.getInt(1));
				bi.setIname(rs1.getString(2));
				bi.setTid(rs1.getInt(3));
				bi.setIbrand(rs1.getString(4));
				bi.setIquantity(rs1.getInt(5));
				bi.setIprice(rs1.getDouble(6));
				lbi.add(bi);
			}
			rs1.close();
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
		return lbi;
	}
	public List<BeanInfo> accuracyBrandSearch(String ibrand) throws BaseException
	{
		Connection conn = null;
		String sql = null;
		List<BeanInfo> lbi = new ArrayList<BeanInfo>();
		try {
			conn = JDBCManager.getConnection();
			//模糊查询找不到的情况
			sql = "SELECT * FROM petsystem.BeanInfo where ibrand=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, ibrand);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next())
				throw new BnException("找不到匹配的商品");
			rs.close();
			java.sql.ResultSet rs1 = pst.executeQuery();
			while(rs1.next())
			{
				BeanInfo bi = new BeanInfo();
				bi.setIid(rs1.getInt(1));
				bi.setIname(rs1.getString(2));
				bi.setTid(rs1.getInt(3));
				bi.setIbrand(rs1.getString(4));
				bi.setIquantity(rs1.getInt(5));
				bi.setIprice(rs1.getDouble(6));
				lbi.add(bi);
			}
			rs1.close();
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
		return lbi;
	}
	public List<BeanInfo> accuracyNBSearch(String iname,String ibrand) throws BaseException
	{
		Connection conn = null;
		String sql = null;
		List<BeanInfo> lbi = new ArrayList<BeanInfo>();
		try {
			conn = JDBCManager.getConnection();
			//模糊查询找不到的情况
			sql = "SELECT * FROM petsystem.BeanInfo where ibrand=? and iname=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, ibrand);
			pst.setString(2, iname);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next())
				throw new BnException("找不到匹配的商品");
			rs.close();
			java.sql.ResultSet rs1 = pst.executeQuery();
			while(rs1.next())
			{
				BeanInfo bi = new BeanInfo();
				bi.setIid(rs1.getInt(1));
				bi.setIname(rs1.getString(2));
				bi.setTid(rs1.getInt(3));
				bi.setIbrand(rs1.getString(4));
				bi.setIquantity(rs1.getInt(5));
				bi.setIprice(rs1.getDouble(6));
				lbi.add(bi);
			}
			rs1.close();
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
		return lbi;
	}
	public List<BeanInfo> loadCommodityThroughType(int tid) throws BaseException
	{
		List<BeanInfo> lbi = new ArrayList<BeanInfo>();
		String sql = null;
		Connection conn = null;
		try {
			conn = JDBCManager.getConnection();
			sql = "select * from BeanInfo where tid=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, tid);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				BeanInfo bi = new BeanInfo();
				bi.setIid(rs.getInt(1));
				bi.setIname(rs.getString(2));
				bi.setTid(rs.getInt(3));
				bi.setIbrand(rs.getString(4));
				bi.setIquantity(rs.getInt(5));
				bi.setIprice(rs.getDouble(6));
				lbi.add(bi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return lbi;
	}
	public List<BeanInfo> loadCommodityThroughTypeName(int tid,String iname) throws BaseException
	{
		List<BeanInfo> lbi = new ArrayList<BeanInfo>();
		String sql = null;
		Connection conn = null;
		try {
			conn = JDBCManager.getConnection();
			sql = "select * from BeanInfo where tid=? and iname=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, tid);
			pst.setString(2, iname);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				BeanInfo bi = new BeanInfo();
				bi.setIid(rs.getInt(1));
				bi.setIname(rs.getString(2));
				bi.setTid(rs.getInt(3));
				bi.setIbrand(rs.getString(4));
				bi.setIquantity(rs.getInt(5));
				bi.setIprice(rs.getDouble(6));
				lbi.add(bi);
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
		return lbi;
	}
	public BeanInfo loadOneCommodity(int tid,String iname,String ibrand) throws BaseException
	{
		Connection conn = null;
		String sql = null;
		BeanInfo bi = new BeanInfo();
		try {
			conn = JDBCManager.getConnection();
			sql = "select * from BeanInfo where tid=? and iname=? and ibrand=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, tid);
			pst.setString(2, iname);
			pst.setString(3, ibrand);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next())
				throw new BnException("错误！未知的商品编号");
			bi.setIid(rs.getInt(1));
			bi.setIname(rs.getString(2));
			bi.setTid(rs.getInt(3));
			bi.setIbrand(rs.getString(4));
			bi.setIquantity(rs.getInt(5));
			bi.setIprice(rs.getDouble(6));
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
		return bi;
	}
}
