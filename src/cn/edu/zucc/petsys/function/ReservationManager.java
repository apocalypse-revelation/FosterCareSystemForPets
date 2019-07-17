package cn.edu.zucc.petsys.function;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.petsys.bean.BeanRes;
import cn.edu.zucc.petsys.ifunction.IfReservationManager;
import cn.edu.zucc.petsys.tool.BaseException;
import cn.edu.zucc.petsys.tool.BnException;
import cn.edu.zucc.petsys.tool.JDBCManager;

public class ReservationManager implements IfReservationManager {

	@Override
	public void addReservation(int pid, String rsername, String rstatus) throws BaseException {
		// TODO Auto-generated method stub
//		java.util.Date date= null;
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		try{
//			date = sdf.parse(rtime);
//		}catch(ParseException p)
//		{
//			p.printStackTrace();
//		}
		Connection conn = null;
		String sql = null;
		int max_rid;
		try {
			conn = JDBCManager.getConnection();
			if(pid==0)
				throw new BnException("内部错误，查找不到该宠物（预约申请）");
			//查找最大rid；
			sql = "select max(rid) from BeanRes";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next())
				max_rid = rs.getInt(1) + 1;
			else 
				max_rid = 1;
			//修改宠物的预约信息
			sql = "insert into BeanRes values(?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, max_rid);
			pst.setInt(2, pid);
			pst.setDate(3, new java.sql.Date(System.currentTimeMillis()));
			pst.setString(4, rsername);
			pst.setString(5, rstatus);
			pst.execute();
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
	public List<BeanRes> loadAllthroughStat(String rstatus,String rapply) throws BaseException
	{
		Connection conn = null;
		String sql = null;
		List<BeanRes> lbr = new ArrayList<BeanRes>();
		try {
			conn = JDBCManager.getConnection();
			sql = "select * from BeanRes where rstatus=? and rapply=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, rstatus);
			pst.setString(2, rapply);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				BeanRes br = new BeanRes();
				br.setRid(rs.getInt(1));
				br.setPid(rs.getInt(2));
				br.setRtime(rs.getDate(3));
				br.setRsername(rs.getString(4));
				br.setRstatus(rs.getString(5));
				lbr.add(br);
			}
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
		return lbr;
	}
	public BeanRes loadOneRes(int rid) throws BaseException
	{
		BeanRes br = new BeanRes();
		Connection conn = null;
		String sql = null;
		try {
			if(rid==0)
				throw new BnException("内部错误，rid=0（loadOneres）");
			
			conn = JDBCManager.getConnection();
			sql = "select * from BeanRes where rid=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, rid);
			java.sql.ResultSet rs = pst.executeQuery();
			rs.next();
			br.setRid(rs.getInt(1));
			br.setPid(rs.getInt(2));
			br.setRtime(rs.getDate(3));
			br.setRsername(rs.getString(4));
			br.setRstatus(rs.getString(5));
			br.setRapply(rs.getString(6));
			br.setRrealfinished(rs.getDate(7));
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
		return br;
	}
	public void updateRes(int rid, String rstatus) throws BaseException
	{
		Connection conn = null;
		String sql = null;
		try {
			conn = JDBCManager.getConnection();
			if(rid==0)
				throw new BnException("内部错误 rid==0");
			sql = "update BeanRes set rstatus=? ,rrealfinished=? where rid=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, rstatus);
			pst.setDate(2, new java.sql.Date(System.currentTimeMillis()));
			pst.setInt(3, rid);
			pst.execute();
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
	public List<BeanRes> loadAllthroughPid(int pid) throws BaseException
	{
		Connection conn = null;
		String sql = null;
		List<BeanRes> lbr = new ArrayList<BeanRes>();
		try {
			conn = JDBCManager.getConnection();
			sql = "select * from BeanRes where pid=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, pid);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				BeanRes br = new BeanRes();
				br.setRid(rs.getInt(1));
				br.setPid(rs.getInt(2));
				br.setRtime(rs.getDate(3));
				br.setRsername(rs.getString(4));
				br.setRstatus(rs.getString(5));
				br.setRapply(rs.getString(6));
				br.setRrealfinished(rs.getDate(7));
				lbr.add(br);
			}
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
		return lbr;
	}
	public void alterApply(int rid,String apply) throws BaseException
	{
		Connection conn = null;
		String sql = null;
		
		try {
			conn = JDBCManager.getConnection();
			if(rid==0)
				throw new BnException("内部错误，找不到该预约编号（同意预约）");
			
			sql = "update BeanRes set rapply=? where rid=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, apply);
			pst.setInt(2, rid);
			pst.execute();
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
	public void deleteRes(int rid) throws BaseException
	{
		Connection conn = null;
		String sql = null;
		try {
			conn = JDBCManager.getConnection();
			if(rid==0)
				throw new BnException("rid==0,deleteRes()");
			sql = "select * from BeanRes where rid=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, rid);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next())
				throw new BnException("查不到该预约单");
			
			sql ="delete from BeanRes where rid = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, rid);
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
}
