package cn.edu.zucc.petsys.function;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.petsys.bean.BeanType;
import cn.edu.zucc.petsys.ifunction.IfTypeManager;
import cn.edu.zucc.petsys.tool.BaseException;
import cn.edu.zucc.petsys.tool.BnException;
import cn.edu.zucc.petsys.tool.JDBCManager;

public class TypeManager implements IfTypeManager{
	public void addType(String tname,String tintroduction) throws BaseException
	{
		Connection conn = null;
		String sql = null;
		int max_tid;
		try {
			conn = JDBCManager.getConnection();
			//检测输入
			if("".equals(tname))
				throw new BnException("类别名不能为空！");
			//检测数据库是否有这个类别
			sql = "select * from BeanType where tname=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, tname);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next())
				throw new BnException("类别名已存在！");
			rs.close();
			//查找最大tid
			sql = "select max(tid) from BeanType ";
			pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs1 = pst.executeQuery();
			if(rs1.next())
				max_tid = rs1.getInt(1)+1;
			else
				max_tid = 1;
			rs1.close();
			//插入
			sql = "insert into BeanType values(?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, max_tid); 
			pst.setString(2, tname);
			pst.setString(3, tintroduction);
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
	public List<BeanType> loadAll() throws BaseException
	{
		String sql = null;
		Connection conn = null;
		List<BeanType> lbt = new ArrayList<BeanType>();
		try {
			conn = JDBCManager.getConnection();
			sql = "select * from BeanType";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				BeanType bt = new BeanType();
				bt.setTid(rs.getInt(1));
				bt.setTname(rs.getString(2));
				bt.setTintroduction(rs.getString(3));
				lbt.add(bt);
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
		return lbt;
	}
	public BeanType loadOne(String tname) throws BaseException
	{
		String sql = null;
		Connection conn = null;
		BeanType bt = new BeanType();
		try {
			conn = JDBCManager.getConnection();
			sql = "select * from BeanType where tname=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, tname);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next())
			{
				bt.setTid(rs.getInt(1));
				bt.setTname(rs.getString(2));
				bt.setTintroduction(rs.getString(3));
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
		return bt;
	}
	public void updateOneType(String newtname,String introduction,String oldtname) throws BaseException
	{
		Connection conn = null;
		String sql = null;
		try {
			if("".equals(newtname))
				throw new BnException("更新的新类名不能为空！");
			conn = JDBCManager.getConnection();
			sql = "update BeanType set tname=?,tintroduction=? where tname=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, newtname);
			pst.setString(2, introduction);
			pst.setString(3, oldtname);
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
	public void deleteOneType(String tname) throws BaseException
	{
		Connection conn = null;
		String sql = null;
		int tid;
		try {
			conn = JDBCManager.getConnection();
			sql = "select * from BeanType where tname=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, tname);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next())
				throw new BnException("未找到该类别");
			tid = rs.getInt(1);
			rs.close();
			//有商品就无法删除类别
			sql = "select * from BeanInfo where tid=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, tid);
			java.sql.ResultSet rs1 = pst.executeQuery();
			if(rs1.next())
				throw new BnException("该类别下有对应的商品，无法删除类别");
			rs1.close();
			
			//delete
			sql = "delete from BeanType where tname=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, tname);
			pst.execute();
			
			//整理序号
			sql = "update BeanType set tid=tid-1 where tid>?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, tid);
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
	public BeanType loadType(int tid) throws BaseException
	{
		String sql = null;
		Connection conn = null;
		BeanType bt = new BeanType();
		try {
			conn = JDBCManager.getConnection();
			sql = "select * from BeanType where tid=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, tid);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next())
			{
				bt.setTid(rs.getInt(1));
				bt.setTname(rs.getString(2));
				bt.setTintroduction(rs.getString(3));
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
		return bt;
	}
}
