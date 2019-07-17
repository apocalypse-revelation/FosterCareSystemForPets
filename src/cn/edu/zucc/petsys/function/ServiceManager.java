package cn.edu.zucc.petsys.function;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.petsys.bean.BeanCus;
import cn.edu.zucc.petsys.bean.BeanPet;
import cn.edu.zucc.petsys.bean.BeanRes;
import cn.edu.zucc.petsys.bean.BeanService;
import cn.edu.zucc.petsys.ifunction.IfServiceManager;
import cn.edu.zucc.petsys.tool.BaseException;
import cn.edu.zucc.petsys.tool.BnException;
import cn.edu.zucc.petsys.tool.JDBCManager;

public class ServiceManager implements IfServiceManager {

	@Override
	public List<BeanService> loadAllService() throws BaseException {
		// TODO Auto-generated method stub
		List<BeanService> lbs = new ArrayList<BeanService>();
		Connection conn = null;
		
		try {
			conn = JDBCManager.getConnection();
			String sql = "select * from BeanService";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				BeanService bs = new BeanService();
				bs.setSername(rs.getString(1));
				bs.setSerstatus(rs.getString(2));
				lbs.add(bs);
			}
			rs.close();
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
		return lbs;
	}
	public int cusReserve(int pid, String retime, String sername) throws BaseException
	{
		Connection conn = null;
		String sql = null;
		java.util.Date date= null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int max_rid = 0;
		try{
			date = sdf.parse(retime);
		}catch(ParseException p)
		{
			p.printStackTrace();
		}
		try {
			conn = JDBCManager.getConnection();
			if(pid==0)
				throw new BnException("内部错误，查找不到该宠物（预约申请）");
			
			//新增预约订单
			sql = "select max(rid) from BeanRes ";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next())
				max_rid = rs.getInt(1) + 1;
			else
				max_rid = 1;
			rs.close();
			
			sql = "insert into BeanRes values(?,?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, max_rid);
			pst.setInt(2, pid);
			pst.setDate(3, new java.sql.Date(date.getTime()));
			pst.setString(4, sername);
			pst.setString(5, "未完成");
			pst.setString(6, "申请中");
			pst.setDate(7, null);
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
		return max_rid;
	}
	public List<BeanRes> loadAllRes(String pres) throws BaseException
	{
		List<BeanRes> lbs = new ArrayList<BeanRes>();
		Connection conn = null;
		String sql = null;
		try {
			conn = JDBCManager.getConnection();
			sql = "select * from BeanRes where rapply=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, pres);
			java.sql.ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				BeanRes brs = new BeanRes();
				brs.setRid(rs.getInt(1));
				brs.setPid(rs.getInt(2));
				brs.setRtime(rs.getDate(3));
				brs.setRsername(rs.getString(4));
				brs.setRstatus(rs.getString(5));
				brs.setRapply(rs.getString(6));
				brs.setRrealfinished(rs.getDate(7));
				lbs.add(brs);
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
		return lbs;
	}
	/*
	public void confirmRes(int pid,String pres) throws BaseException
	{
		Connection conn = null;
		String sql = null;
		
		try {
			conn = JDBCManager.getConnection();
			if(pid==0)
				throw new BnException("内部错误，找不到该宠物编号（同意预约）");
			
			sql = "update BeanPet set preserve=? where pid=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, pres);
			pst.setInt(2, pid);
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	**/
	public void addService(String sername) throws BaseException
	{
		Connection conn = null;
		String sql = null;
		try {
			conn = JDBCManager.getConnection();
			if(sername.equals(""))
				throw new BnException("服务名不能为空！");
			sql = "select * from BeanService where sername=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, sername);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next())
				throw new BnException("已经存在该名称的服务了！");
			rs.close();
			
			sql = "insert into BeanService(sername) values(?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, sername);
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
	public void updateService(String oldsername, String newsername) throws BaseException
	{
		Connection conn = null;
		String sql = null;
		try {
			conn = JDBCManager.getConnection();
			if(newsername.equals(""))
				throw new BnException("修改的服务名不能为空！");
			sql = "select * from BeanService where sername=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, newsername);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next())
				throw new BnException("该用户名已经存在，不能修改为同名的！");
			
			sql = "update BeanService set sername=? where sername=?";
			pst = conn.prepareStatement(sql);
			pst = conn.prepareStatement(sql);
			pst.setString(2, oldsername);
			pst.setString(1, newsername);
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
	public void stopService(String sername) throws BaseException
	{
		Connection conn = null;
		String sql = null;
		try {
			conn = JDBCManager.getConnection();
			if(sername.equals(""))
				throw new BnException("内部错误，服务名为空！（停办服务）");
			sql = "select * from BeanService where sername=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, sername);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next())
				throw new BnException("内部错误，找不到该服务(停办服务）");
			if(rs.getString(2).equals("停办"))
				throw new BnException("该服务已经停办！");
			
			sql = "update BeanService set serstatus=? where sername=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, "停办");
			pst.setString(2, sername);
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
	public void bootUpService(String sername) throws BaseException
	{
		Connection conn = null;
		String sql = null;
		try {
			conn = JDBCManager.getConnection();
			if(sername.equals(""))
				throw new BnException("内部错误，服务名为空！（重启服务）");
			sql = "select * from BeanService where sername=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, sername);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next())
				throw new BnException("内部错误，找不到该服务(重启服务）");
			
			if(rs.getString(2).equals("可预约"))
				throw new BnException("该服务是正在运营的，不要重复重启！");
			
			sql = "update BeanService set serstatus=? where sername=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, "可预约");
			pst.setString(2, sername);
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
	public List<BeanRes> loadParRes(String rstatus,String rapply) throws BaseException
	{
		List<BeanRes> lbs = new ArrayList<BeanRes>();
		Connection conn = null;
		String sql = null;
		try {
			conn = JDBCManager.getConnection();
			sql = "select * from BeanRes where rstatus=? and rapply=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, rstatus);
			pst.setString(2, rapply);
			java.sql.ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				BeanRes brs = new BeanRes();
				brs.setRid(rs.getInt(1));
				brs.setPid(rs.getInt(2));
				brs.setRtime(rs.getDate(3));
				brs.setRsername(rs.getString(4));
				brs.setRstatus(rs.getString(5));
				brs.setRapply(rs.getString(6));
				brs.setRrealfinished(rs.getDate(7));
				lbs.add(brs);
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
		return lbs;
	}
}
