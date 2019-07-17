package cn.edu.zucc.personplan.comtrol.example;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

//import com.mysql.jdbc.Connection;

import cn.edu.zucc.personplan.itf.IPlanManager;
import cn.edu.zucc.personplan.model.BeanPlan;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.BusinessException;
import cn.edu.zucc.personplan.util.DBUtil;

public class ExamplePlanManager implements IPlanManager {

	@Override
	
	public BeanPlan addPlan(String name) throws BaseException {
		// TODO Auto-generated method stub
		/**
		 * 添加计划
		 * 要求新增的计划的排序号为当前用户现有最大排序号+1
		 * 注意：当前登陆用户可通过 BeanUser.currentLoginUser获取
		 * @param name  计划名称
		 * @throws BaseException
		 */
		Connection conn = null;
		BeanPlan bp = new BeanPlan();
		int id;
		try
		{
			conn = DBUtil.getConnection();
			String sql = "select max(plan_id) from beanplan where userid=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, BeanUser.currentLoginUser.getUserid());
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next())
				id = rs.getInt(1)+1;
			else id = 1;
			rs.close();
			pst.close();
			
			sql = "insert into beanplan(plan_id,plan_name,userid) values(?,?,?)";
			java.sql.PreparedStatement pst1 = conn.prepareStatement(sql);
			pst1.setInt(1, id);
			pst1.setString(2, name);
			pst1.setString(3, BeanUser.currentLoginUser.getUserid());
			//pst.getGeneratedKeys().next();
			//pst1.setInt(1,pst.getGeneratedKeys().getInt(1));
			pst1.execute();
			pst1.close();
			
			sql = "select * from beanplan where plan_id=?";
			java.sql.PreparedStatement pst2 = conn.prepareStatement(sql);
			pst2.setInt(1, id);
			java.sql.ResultSet rs1 = pst2.executeQuery();
			if(rs1.next())
			{
				bp.setPlan_Id(rs1.getInt(1));
				bp.setPlan_Name(rs1.getString(2));
				
			}
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			try {
				if(conn!=null)
					conn.close();
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return bp;
	}

	@Override
	public List<BeanPlan> loadAll() throws BaseException {
		/**
		 * 提取当前用户所有计划
		 * @return
		 * @throws BaseException
		 */
		List<BeanPlan> result=new ArrayList<BeanPlan>();
		
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql = "select * from beanplan where userid=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, BeanUser.currentLoginUser.getUserid());
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				BeanPlan p=new BeanPlan();
				p.setPlan_Id(rs.getInt(1));
				p.setPlan_Name(rs.getString(2));
				p.setStep_Number(rs.getInt(3));
				p.setCompleted_Number(rs.getInt(4));
				//System.out.println(p.getPlan_Id());
				
				result.add(p);
				
			}
			
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
		
		return result;
	}

	@Override
	public void deletePlan(BeanPlan plan) throws BaseException {
		/**
		 * 删除计划，如果计划下存在步骤，则不允许删除
		 * @param plan
		 * @throws BaseException
		 */
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql = "select * from beanstep where plan_id=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, plan.getPlan_Id());
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next())
				throw new BusinessException("此计划存在步骤，无法删除！");
			rs.close();
			pst.close();
			/**
			sql = "select max(plan_id) from beanplan where userid=?";
			java.sql.PreparedStatement pst0 = conn.prepareStatement(sql);
			pst0.setString(1, BeanUser.currentLoginUser.getUserid());
			java.sql.ResultSet rs0 = pst0.executeQuery();
			rs0.next();
			curplan = rs0.getInt(1); //max(plan_id)
			*/
			sql = "delete from beanplan where plan_id=?";
			java.sql.PreparedStatement pst1 = conn.prepareStatement(sql);
			pst1.setInt(1, plan.getPlan_Id());
			pst1.executeUpdate();
			pst1.close();
			sql = "update beanplan set plan_id = plan_id - 1 where plan_id > ? and userid = ?";
			java.sql.PreparedStatement pst2 = conn.prepareStatement(sql);
			pst2.setInt(1, plan.getPlan_Id());
			pst2.setString(2, BeanUser.currentLoginUser.getUserid());
			pst2.executeUpdate();
			pst2.close();
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
