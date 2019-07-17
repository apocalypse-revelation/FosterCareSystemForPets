package cn.edu.zucc.petsys.function;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.petsys.bean.BeanCus;
import cn.edu.zucc.petsys.bean.BeanPet;
import cn.edu.zucc.petsys.bean.BeanStaff;
import cn.edu.zucc.petsys.ifunction.IfStaffManager;
import cn.edu.zucc.petsys.tool.BaseException;
import cn.edu.zucc.petsys.tool.BnException;
import cn.edu.zucc.petsys.tool.JDBCManager;

public class SystemManager implements IfStaffManager{

	public List<BeanPet> loadAllpet() throws BaseException
	{
		Connection conn = null;
		String sql = null;
		List<BeanPet> lbp = new ArrayList<BeanPet>();
		try {
			conn = JDBCManager.getConnection();
			sql = "select * from BeanPet";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				BeanPet bp = new BeanPet();
				bp.setPid(rs.getInt(1));
				bp.setPname(rs.getString(2));
				bp.setPbreeds(rs.getString(3));
				bp.setPimg(rs.getString(4));
				bp.setPregtime(rs.getDate(5));
				bp.setCid(rs.getInt(6));
				bp.setPsex(rs.getString(7));
//				bp.setPfoster(rs.getString(8));
//				bp.setPfosterstart(rs.getDate(9));
//				bp.setPfosterend(rs.getDate(10));
//				bp.setPreserve(rs.getString(11));
//				bp.setPretime(rs.getDate(12));
//				bp.setSername(rs.getString(13));
				lbp.add(bp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally
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
		return lbp;
	}
	public BeanStaff login(String user, String spwd) throws BaseException//管理员登录
	{
		Connection conn = null;
		BeanStaff bs = new BeanStaff(); 
		//检测
		try {
			if(user.equals("")||"".equals(spwd))
				throw new BnException("员工账户或密码未填写！");
			if(user.length()>45||spwd.length()>45)
				throw new BnException("员工账户或密码超过最大45字符的长度限制！");
			conn = JDBCManager.getConnection();
			String sql = "select spwd from BeanStaff where susers=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, user);
			java.sql.ResultSet rs = pst.executeQuery();
			//检测
			if(!rs.next())
				throw new BnException("没有该管理账户！");
			
			if(!rs.getString(1).equals(spwd))
				throw new BnException("密码错误!");
			rs.close();
			
			//读取当前login信息所需的susers
			sql = "select * from BeanStaff where susers=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, user);
			java.sql.ResultSet rs1 = pst.executeQuery();
			rs1.next();
			
			bs.setSid(rs1.getInt(1));
			bs.setSname(rs1.getString(2));
			bs.setSpwd(rs1.getString(3));
			bs.setSrank(rs1.getString(4));
			bs.setSuser(rs1.getString(5));
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
		return bs;
	}
	public List<BeanStaff> loadAllStaff() throws BaseException
	{
		Connection conn = null;
		String sql = null;
		List<BeanStaff> lbs = new ArrayList<BeanStaff>();
		try {
			conn = JDBCManager.getConnection();
			sql = "select * from BeanStaff";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				BeanStaff bs = new BeanStaff();
				bs.setSid(rs.getInt(1));
				bs.setSname(rs.getString(2));
				bs.setSpwd(rs.getString(3));
				bs.setSrank(rs.getString(4));
				lbs.add(bs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally
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
	public BeanStaff addStaff(String user,String pwd,String name, String rank) throws BaseException
	{
		Connection conn = null;
		String sql = null;
		BeanStaff bs = new BeanStaff();
		int max_sid;
		try {
			conn = JDBCManager.getConnection();
			if("".equals(user)||"".equals(pwd)||"".equals(name)||"".equals(rank))
				throw new BnException("四个选项都不能为空！");
			
			sql = "select sid from BeanStaff";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next())
				max_sid = rs.getInt(1) + 1;
			else
				max_sid = 1;
			rs.close();
			sql = "select * from BeanStaff where susers=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, user);
			java.sql.ResultSet rs2 = pst.executeQuery();
			if(rs2.next())
				throw new BnException("该管理账号已经被使用");
			
			sql = "insert into BeanStaff values(?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, max_sid);
			pst.setString(2, name);
			pst.setString(3, pwd);
			pst.setString(4, rank);
			pst.setString(5, user);
			pst.execute();

			sql = "select * from BeanStaff where sid=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, max_sid);
			java.sql.ResultSet rs1 = pst.executeQuery();
			rs1.next();
			bs.setSid(max_sid);
			bs.setSname(rs1.getString(2));
			bs.setSpwd(rs1.getString(3));
			bs.setSrank(rs1.getString(4));
			bs.setSuser(rs1.getString(5));
			rs1.close();
			
			
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
		return bs;
	}
	public void deletestaff(int sid) throws BaseException
	{
		Connection conn = null;
		String sql = null;
		try {
			conn = JDBCManager.getConnection();
			if(sid==0)
				throw new BnException("内部错误，sid=0（删除员工账号）");
			
			sql = "delete from BeanStaff where sid=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, sid);
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
}
