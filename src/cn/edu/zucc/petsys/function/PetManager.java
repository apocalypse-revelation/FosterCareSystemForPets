package cn.edu.zucc.petsys.function;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.petsys.bean.BeanCus;
import cn.edu.zucc.petsys.bean.BeanPet;
import cn.edu.zucc.petsys.ifunction.IfPet;
import cn.edu.zucc.petsys.tool.BaseException;
import cn.edu.zucc.petsys.tool.BnException;
import cn.edu.zucc.petsys.tool.JDBCManager;

public class PetManager implements IfPet {

	public List<BeanPet> loadAll() throws BaseException
	{
		List<BeanPet> result=new ArrayList<BeanPet>();
		Connection conn = null;
		
		try {
			conn = JDBCManager.getConnection();
			String sql = "select * from BeanPet where cid=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, BeanCus.currentLoginUser.getCid());
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
				result.add(bp);
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
		return result;
	}
	
	public BeanPet loadPet(int pid) throws BaseException
	{
		BeanPet bp = new BeanPet();
		Connection conn = null;
		try {
			conn = JDBCManager.getConnection();
			String sql = "select * from BeanPet where pid=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, pid);
			java.sql.ResultSet rs = pst.executeQuery();
			rs.next();
			bp.setPid(pid);
			bp.setPname(rs.getString(2));
			bp.setPbreeds(rs.getString(3));
			bp.setPimg(rs.getString(4));
			bp.setPregtime(rs.getDate(5));
			bp.setCid(rs.getInt(6));
			bp.setPsex(rs.getString(7));
//			bp.setPfoster(rs.getString(8));
//			bp.setPfosterstart(rs.getDate(9));
//			bp.setPfosterend(rs.getDate(10));
//			bp.setPreserve(rs.getString(11));
//			bp.setPretime(rs.getDate(12));
//			bp.setSername(rs.getString(13));
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
		return bp;
	}
	public void addPet(String pname, String pbreeds ,String psex, String pimg) throws BaseException
	{
		Connection conn = null;
		int maxpid;
		
		
		
		try {
			//检测
			if("".equals(pname)||pname==null)
				throw new BnException("宠物昵称未填写！");
			if("".equals(pbreeds)||"".equals(psex))
				throw new BnException("宠物种类或性别未选择！");
			if(pname.length()>45)
				throw new BnException("宠物昵称超过了最大长度45的限制！");
			conn = JDBCManager.getConnection();
			//查询最大pid
			String sql = "select max(pid) from BeanPet";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
//			pst.setInt(1, BeanCus.currentLoginUser.getCid());
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next())
				maxpid = rs.getInt(1) + 1;
			else
				maxpid = 1;
			rs.close();
			
			sql = "insert into BeanPet(pid,pname,pbreeds,pimg,pregdate,cid,psex) values(?,?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, maxpid);
			pst.setString(2, pname);
			pst.setString(3, pbreeds);
			pst.setString(4, pimg);
			pst.setDate(5, new java.sql.Date(System.currentTimeMillis()));
			pst.setInt(6, BeanCus.currentLoginUser.getCid());
			pst.setString(7, psex);
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
	public void UpdatePetFoster(int pid) throws BaseException
	{
		Connection conn = null;
		try {
			conn = JDBCManager.getConnection();
			//检测是否处于寄养状态
			String sql = "select pfoster from BeanPet where pid=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, pid);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next())
				throw new BnException("未知错误，找不到该宠物的登记信息！");
			if(rs.getString(1).equals("否"))
			{
				sql = "update BeanPet set pfoster=?,pfosterstart=? where pid=?";
				pst = conn.prepareStatement(sql);
				pst.setString(1, "是");
				pst.setDate(2, new java.sql.Date(System.currentTimeMillis()));
				pst.setInt(3, pid);
				pst.execute();
			}
			else if(rs.getString(1).equals("是"))
				throw new BnException("该宠物已经处于寄养状态");
			else
				throw new BnException("请先选择宠物！");
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
	public long EndPetFoster(int pid) throws BaseException
	{
		Connection conn = null;
		String sql = null;
		long sql_timediff = 2;
		int id;
		try {
			conn = JDBCManager.getConnection();
			//检测是否已经处在寄养状态;“否”则报错
			sql = "select pfoster from BeanPet where pid=?" ;
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, pid);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next())
				throw new BnException("请先选择宠物再取消寄养");
			else 
				if(rs.getString(1).equals("否"))
					throw new BnException("该宠物没有被寄养");
				else if(rs.getString(1).equals("是"))
				{
					sql = "update BeanPet set pfoster=?,pfosterend=? where pid=?";
					pst = conn.prepareStatement(sql);
					pst.setString(1, "否");
					pst.setDate(2, new java.sql.Date(System.currentTimeMillis()));
					pst.setInt(3, pid);
					pst.execute();
				}
			//关闭
			rs.close();
				//查询寄养日期：计算时间差；单位：天
			sql = "SELECT pid,datediff(pfosterend,pfosterstart) FROM petsystem.BeanPet where pid=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, pid);
			java.sql.ResultSet rs1 = pst.executeQuery();
			if(!rs1.next())
				throw new BnException("内部错误，找不到指定的宠物！");
			id = rs1.getInt(1);
			sql_timediff = rs1.getLong(2);
			System.out.println(sql_timediff+"///"+id);
			rs1.close();
			//清空寄养开始和结束时间pfosterstart/pfosterend
			sql = "update BeanPet set pfosterstart=?,pfosterend=? where pid=?";
			pst = conn.prepareStatement(sql);
			pst.setDate(1, null);
			pst.setDate(2, null);
			pst.setInt(3, pid);
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
		return sql_timediff;
	}
	public void deletePet(int pid) throws BaseException
	{
		Connection conn = null;
		String sql = null;
		try {
			conn = JDBCManager.getConnection();
			if(pid==0)
				throw new BnException("内部错误，pid==0（deletePet）");
			sql = "select * from BeanPet where pid=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, pid);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next())
				throw new BnException("查找不到该pid");
			rs.close();
			
			sql = "delete from BeanPet where pid=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, pid);
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public List<BeanPet> loadAllthroughCid(int cid) throws BaseException
	{
		List<BeanPet> result=new ArrayList<BeanPet>();
		Connection conn = null;
		
		try {
			conn = JDBCManager.getConnection();
			String sql = "select * from BeanPet where cid=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, cid);
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
				result.add(bp);
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
		return result;
	}
}
