package cn.edu.zucc.petsys.function;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.petsys.bean.BeanCus;
import cn.edu.zucc.petsys.bean.BeanInfo;
import cn.edu.zucc.petsys.ifunction.IfCustomerManager;
import cn.edu.zucc.petsys.tool.BaseException;
import cn.edu.zucc.petsys.tool.BnException;
import cn.edu.zucc.petsys.tool.JDBCManager;

import java.sql.Connection;
import java.sql.SQLException;



public class CustomerManager implements IfCustomerManager{
	public BeanCus reg(String cusers, String pwd1, String pwd2,String cname, String ctelnum, String cemail,String cocontact) throws BaseException
	{
		Connection conn = null;
		BeanCus bc = new BeanCus();
		int usercid ;
		try {
			//exception检测
			if("".equals(cusers))
				throw new BnException("用户账号不能为空");
			if(!pwd1.equals(pwd2))
				throw new BnException("两次输入的密码必须一致");
			if("".equals(pwd1)||"".equals(pwd2))
				throw new BnException("密码不能为空");
			if(cusers.length()>45||pwd1.length()>45||pwd2.length()>45)
				throw new BnException("用户昵称或密码长度不能超过45个字符");
			if("".equals(cname))
				throw new BnException("您的姓名不能为空");
			if("".equals(ctelnum))
				throw new BnException("联系电话不能为空！");
			
			conn = JDBCManager.getConnection();
			String sql = "select * from Beancus where cusers=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, cusers);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next())
				throw new BnException("用户账户已经存在，请换一个！");
			rs.close();
			//搜寻最大cid从而生成新用户cid，设置变量为usercid；
			
			sql = "select max(cid) from BeanCus ";
			pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs1 = pst.executeQuery();
			if(rs1.next())
				usercid = rs1.getInt(1)+1;//找得cid得到就赋值+1
			else 
				usercid = 1;
			rs1.close();
			
			//注册，数据存入数据库
			sql = "insert into BeanCus(cid,cname,ctelnum,cemail,cocontact,cusers,cpwd,cregdate) values(?,?,?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, usercid);
			pst.setString(2, cname);
			pst.setString(3, ctelnum);
			pst.setString(4, cemail);
			pst.setString(5, cocontact);
			pst.setString(6, cusers);
			pst.setString(7, pwd1);
			pst.setTimestamp(8, new java.sql.Timestamp(System.currentTimeMillis()));//注册时间
			
			
			pst.execute();
			//Return Beancus
			sql = "select * from Beancus where cid=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, usercid);
			java.sql.ResultSet rs2 = pst.executeQuery();
			rs2.next();
			bc.setCid(usercid);
			bc.setCname(cname);
			bc.setCemail(cemail);
			bc.setCocontact(cocontact);
			bc.setCpwd(pwd1);
			bc.setCregdate(rs2.getTimestamp(8));
			bc.setCtelnum(ctelnum);
			bc.setCusers(cusers);
			
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally {
			try {
				if(conn!=null)
					conn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
				// TODO: handle exception
			}
			
		}
		return bc;
	}
	public BeanCus login(String cusers, String pwd) throws BaseException
	{
		Connection conn = null;
		BeanCus bc = new BeanCus(); 
		//检测
		try {
			if("".equals(cusers)||"".equals(pwd))
				throw new BnException("用户名或密码未填写！");
			if(cusers.length()>45||pwd.length()>45)
				throw new BnException("用户名或密码超过最大45字符的长度限制！");
			conn = JDBCManager.getConnection();
			
			
			String sql = "select cpwd from BeanCus where cusers=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, cusers);
			java.sql.ResultSet rs = pst.executeQuery();
			//检测
			if(!rs.next())
				throw new BnException("该用户名未注册！");
			
			if(!rs.getString(1).equals(pwd))
				throw new BnException("该用户名对应的密码错误");
			rs.close();
			
			//读取当前login信息所需的cid 
			sql = "select * from BeanCus where cusers=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, cusers);
			java.sql.ResultSet rs1 = pst.executeQuery();
			rs1.next();
			bc.setCid(rs1.getInt(1));
			bc.setCemail(rs1.getString(7));
			bc.setCname(rs1.getString(2));
			bc.setCtelnum(rs1.getString(3));
			bc.setCocontact(rs1.getString(4));
			bc.setCregdate(rs1.getTimestamp(8));
			bc.setCpwd(pwd);
			bc.setCusers(cusers);
			rs1.close();
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
		return bc;
	}
	public void changePwd(String cusers, String oldpwd,String newpwd) throws BaseException
	{
		Connection conn = null;
		try {
			conn = JDBCManager.getConnection();
			if("".equals(cusers))
				throw new BnException("未填写用户名");
			if("".equals(oldpwd)||"".equals(newpwd))
				throw new BnException("未填写原密码或新密码");
			if(oldpwd.length()>45||newpwd.length()>45)
				throw new BnException("密码长度不能大于45个字符");
			
			
			String sql = "select cpwd from BeanCus where cusers=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, cusers);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next())
				throw new BnException("该用户名未注册");
			if(!rs.getString(1).equals(oldpwd))
				throw new BnException("该用户名对应的密码不正确");
			rs.close();
			
			
			sql = "update BeanCus set cpwd=? where cusers=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, newpwd);
			pst.setString(2, cusers);
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
	public List<BeanCus> loadAll() throws BaseException
	{
		Connection conn = null;
		String sql = null;
		List<BeanCus> lbc = new ArrayList<BeanCus>();
		try {
			conn = JDBCManager.getConnection();
			sql = "select * from BeanCus ";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				BeanCus bc = new BeanCus();
				bc.setCid(rs.getInt(1));
				bc.setCname(rs.getString(2));
				bc.setCtelnum(rs.getString(3));
				bc.setCocontact(rs.getString(4));
				bc.setCusers(rs.getString(5));
				bc.setCpwd(rs.getString(6));
				bc.setCemail(rs.getString(7));
				bc.setCregdate(rs.getTimestamp(8));
				lbc.add(bc);
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
		return lbc;
	}
	public BeanCus loadCus(String cusers) throws BaseException
	{
		Connection conn = null;
		String sql = null;
		BeanCus bc = new BeanCus();
		try {
			if("".equals(cusers))
				throw new BnException("内部错误，用户账号未空！loadCus");
			conn = JDBCManager.getConnection();
			sql = "select * from BeanCus where cusers=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, cusers);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next())
				throw new BnException("内部错误，找不到这个用户！loadCus");
			
			bc.setCid(rs.getInt(1));
			bc.setCname(rs.getString(2));
			bc.setCtelnum(rs.getString(3));
			bc.setCocontact(rs.getString(4));
			bc.setCusers(rs.getString(5));
			bc.setCpwd(rs.getString(6));
			bc.setCemail(rs.getString(7));
			bc.setCregdate(rs.getTimestamp(8));
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
		return bc;
	}
	public void deleteCus(String cusers) throws BaseException
	{
		Connection conn = null;
		String sql = null;
		int cid;
		try {
			if(cusers.equals(""))
				throw new BnException("内部错误，用户账号为空！（删除用户）");
			
			conn = JDBCManager.getConnection();
			sql = "select * from BeanCus where cusers=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, cusers);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next())
				throw new BnException("找不到该用户，无法删除！");
			cid = rs.getInt(1);
			rs.close();
			//有宠物就不能删除
			sql = "select * from BeanPet where cid=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, cid);
			java.sql.ResultSet rs1 = pst.executeQuery();
			
			if(rs1.next())
				throw new BnException("该用户名下已经登记了宠物");
			
			sql = "delete from BeanCus where cusers=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, cusers);
			pst.execute();
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
	}
	public String getName(int cid) throws BaseException
	{
		String sql = null;
		Connection conn = null;
		String name = null;
		try {
			conn = JDBCManager.getConnection();
			sql = "select cname from BeanCus where cid=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, cid);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next())
				name = rs.getString(1);
			else
				throw new BnException("没有这个cid的用户");
			
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return name;
	}
	public void cusPurchase(int iid,int amount) throws BaseException
	{
		Connection conn = null;
		String sql = null;
		try {
			conn = JDBCManager.getConnection();
			//检测库存量为0的情况
			sql = "select iquantity from BeanInfo where iid=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, iid);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next())
				throw new BnException("未知错误，查询不到该商品");
			
			if(rs.getInt(1)==0)
				throw new BnException("该商品没有存余了！");
			else if(amount-rs.getInt(1)>0)
				throw new BnException("存余量不够，不能买这么多！");
				
		
			rs.close();
			
			sql = "update BeanInfo set iquantity=iquantity-? where iid=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, amount);
			pst.setInt(2, iid);
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
	public List<BeanCus> fuzzySearch(String cusers) throws BaseException
	{
		Connection conn = null;
		String sql = null;
		List<BeanCus> lbc = new ArrayList<BeanCus>();
		try {
			conn = JDBCManager.getConnection();
			//模糊查询找不到的情况
			sql = "SELECT * FROM BeanCus where cusers like ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, '%'+cusers+'%');
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next())
				throw new BnException("找不到匹配的用户");
			rs.close();
			
			java.sql.ResultSet rs1 = pst.executeQuery();
			while(rs1.next())
			{
				BeanCus bc = new BeanCus();
				bc.setCid(rs1.getInt(1));
				bc.setCname(rs1.getString(2));
				bc.setCtelnum(rs1.getString(3));
				bc.setCocontact(rs1.getString(4));
				bc.setCusers(rs1.getString(5));
				bc.setCpwd(rs1.getString(6));
				bc.setCemail(rs1.getString(7));
				bc.setCregdate(rs1.getTimestamp(8));
				lbc.add(bc);
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
		return lbc;
	}
}
