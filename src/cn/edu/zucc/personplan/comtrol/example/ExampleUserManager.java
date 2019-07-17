package cn.edu.zucc.personplan.comtrol.example;

import java.sql.Connection;
import java.sql.SQLException;

import cn.edu.zucc.personplan.itf.IUserManager;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.BusinessException;
import cn.edu.zucc.personplan.util.DBUtil;

public class ExampleUserManager implements IUserManager {

	@Override
	public BeanUser reg(String userid, String pwd,String pwd2) throws BaseException {
		// TODO Auto-generated method stub
				/**
				 * 注册：
				 * 要求用户名不能重复，不能为空
				 * 两次输入的密码必须一致，密码不能为空
				 * 如果注册失败，则抛出异常
				 * @param userid
				 * @param pwd  密码
				 * @param pwd2 重复输入的密码
				 * @return
				 * @throws BaseException
				 */
				Connection conn = null;
				//List<BeanUser> list = new ArrayList<BeanUser>();
				BeanUser bu = new BeanUser();
				
				try
				{
					if("".equals(userid))
						throw new BusinessException("用户名不能为空");
					if(!pwd.equals(pwd2))
						throw new BusinessException("两次输入的密码必须一致");
					if("".equals(pwd)||"".equals(pwd2))
						throw new BusinessException("密码不能为空");
					if(userid.length()>45||pwd.length()>45||pwd2.length()>45)
						throw new BusinessException("用户名或密码长度不能超过45个字符");
					if(userid.equals("@")||userid.equals("!")||userid.equals("#")||userid.equals("$")||userid.equals("%")||userid.equals("^")||userid.equals("&")||userid.equals("*")||userid.equals("(")||userid.equals(")")||
							userid.equals("-")||userid.equals("+")||userid.equals(",")||
							userid.equals(".")||userid.equals("/")||userid.equals("`")||
							userid.equals("~")||userid.equals("[")||userid.equals("]")||
							userid.equals("{")||userid.equals("}")||userid.equals(":")||
							userid.equals(";"))
						throw new BusinessException("用户名不允许输入奇怪的字符！");
						
					String sql = "select * from BeanUser";
					conn = DBUtil.getConnection();
					java.sql.PreparedStatement pst = conn.prepareStatement(sql);
					java.sql.ResultSet rs = pst.executeQuery();
					
					while(rs.next())
					{
						if(userid.equals(rs.getString(1)))
							throw new BusinessException("用户名不能重复");
					}
					
					sql = "insert into beanuser values(?,?,?)";
					java.sql.PreparedStatement pst2 = conn.prepareStatement(sql);
					pst2.setString(1, userid);
					pst2.setString(2, pwd);
					pst2.setString(3, pwd2);
					//pst2.setString(4, pwd);//oldpwd
					//pst2.setString(5, pwd);//newpwd
					pst2.execute();
					rs.close();
					pst.close();
					pst2.close();
					sql = "select * from beanuser where userid=?";
					java.sql.PreparedStatement pst3 = conn.prepareStatement(sql);
					pst3.setString(1, userid);
					java.sql.ResultSet rs1 = pst3.executeQuery();
					bu.setUserid(rs1.getString(1));
					bu.setPwd(rs.getString(2));
					bu.setPwd2(rs.getString(2));
				}catch(SQLException e)
				{
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
				return bu;
	}

	
	@Override
	public BeanUser login(String userid, String pwd) throws BaseException {
		// TODO Auto-generated method stub
		/**
		 * 登陆
		 * 1、如果用户不存在或者密码错误，抛出一个异常
		 * 2、如果认证成功，则返回当前用户信息
		 * @param userid
		 * @param pwd
		 * @return
		 * @throws BaseException
		 */
		Connection conn = null;
		BeanUser bu = new BeanUser();
		try
		{
			conn = DBUtil.getConnection();
			String sql = "select * from BeanUser where userid=?";//判断用户名不存在
			java.sql.PreparedStatement pst0 = conn.prepareStatement(sql);
			pst0.setString(1, userid);
			java.sql.ResultSet rs0 = pst0.executeQuery();
			
			if(!rs0.next())
				throw new BusinessException("用户名不存在");
			rs0.close();
			pst0.close();
			sql = "select * from BeanUser where userid=? and pwd=?";//判断用户名和密码是否匹配
			java.sql.PreparedStatement pst1 = conn.prepareStatement(sql);
			pst1.setString(1, userid);
			pst1.setString(2, pwd);
			java.sql.ResultSet rs1 = pst1.executeQuery();
			
			if(!rs1.next())
				throw new BusinessException("密码错误");
			else
			{
				bu.setUserid(rs1.getString(1));
				bu.setPwd(rs1.getString(2));
				bu.setPwd2(rs1.getString(2));
			}
			//System.out.println(bu.getUserid());
			//下面用于修改密码
			//BeanUser.id = rs1.getString(1);
			//BeanUser.password = rs1.getString(2);
		}catch(SQLException e)
		{
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
		
		return bu;
	}


	@Override
	public void changePwd(BeanUser user, String oldPwd, String newPwd,
			String newPwd2) throws BaseException {
		// TODO Auto-generated method stub
		/**
		 * 修改密码
		 * 如果没有成功修改，则抛出异常
		 * @param user    当前用户
		 * @param oldPwd  原密码
		 * @param newPwd  新密码
		 * @param newPwd2 重复输入的新密码
		 */
		Connection conn = null;
		BeanUser bu = new BeanUser();
		try
		{
			conn = DBUtil.getConnection();
			String sql = "select * from BeanUser where userid=?";
			//String sql ="update BeanUser set pwd=?,oldpwd=?,newpwd=? where userid=?";
			java.sql.PreparedStatement pst0 = conn.prepareStatement(sql);
			pst0.setString(1, user.getUserid());
			java.sql.ResultSet rs0 = pst0.executeQuery();
			rs0.next();
			if(!rs0.getString(2).equals(oldPwd))
				throw new BusinessException("原始密码错误");
			if(!newPwd.equals(newPwd2))
				throw new BusinessException("两次修改的密码不符");
			if(newPwd.length()>45)
				throw new BusinessException("修改密码过长");
			rs0.close();
			pst0.close();
			sql = "update BeanUser set pwd=?,pwd2=? where userid=?";
			java.sql.PreparedStatement pst1 = conn.prepareStatement(sql);
			pst1.setString(1, newPwd);
			pst1.setString(2, newPwd);
			pst1.setString(3, user.getUserid());
			pst1.executeUpdate();
			//System.out.println(new BeanUser().getUserid());
			pst1.close();
			//System.out.println(bu.getUserid());
			//System.out.println(user.getUserid());
			//System.out.println(new BeanUser().currentLoginUser.getUserid());
		}catch(SQLException e)
		{
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
