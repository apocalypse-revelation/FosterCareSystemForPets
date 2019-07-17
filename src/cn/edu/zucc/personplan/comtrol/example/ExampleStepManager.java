package cn.edu.zucc.personplan.comtrol.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.edu.zucc.personplan.itf.IStepManager;
import cn.edu.zucc.personplan.model.BeanPlan;
import cn.edu.zucc.personplan.model.BeanStep;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.BusinessException;
import cn.edu.zucc.personplan.util.DBUtil;

public class ExampleStepManager implements IStepManager {

	@Override
	public void add(BeanPlan plan, String name, String planstartdate,
			String planfinishdate) throws BaseException {
		/**
		 * 添加步骤
		 * 新填的步骤序号为当前计划最大步骤序号+1
		 * 注意：需完成字符串和时间类型的转换，添加后需调整计划表中相应的数量值
		 * @param plan
		 * @param name
		 * @param planstartdate
		 * @param planfinishdate
		 * @throws BaseException
		 */
		Connection conn = null;
		java.util.Date date1=null;
		java.util.Date date2=null;
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		
		
		try{
			date1 = sdf1.parse(planstartdate);
			date2 = sdf2.parse(planfinishdate);
		}catch(ParseException p)
		{
			p.printStackTrace();
		}
		
		int stepid = 0;
		
		try {
			int stepnum = 0;
			conn = DBUtil.getConnection();
			String sql = "select max(step_id) from beanstep where plan_id=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, plan.getPlan_Id());
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next())
				stepid = rs.getInt(1)+1;
			else
				stepid = 1;
			rs.close();
			pst.close();
			sql = "insert into beanstep(step_id,step_name,planstartdate,planfinishdate,plan_id) values(?,?,?,?,?)";
			java.sql.PreparedStatement pst1 = conn.prepareStatement(sql);
			pst1.setInt(1, stepid);
			pst1.setString(2, name);
			pst1.setDate(3, new java.sql.Date(date1.getTime()));
			pst1.setDate(4, new java.sql.Date(date2.getTime()));
			pst1.setInt(5, plan.getPlan_Id());
			pst1.execute();
			pst1.close();
			/*beanplan的步骤数修改
			 * 
			 */
				//查询最大步骤数
			sql = "select max(step_id) from beanstep where plan_id=? ";
			java.sql.PreparedStatement pst2 = conn.prepareStatement(sql);
			pst2.setInt(1, plan.getPlan_Id());
			//pst2.setString(2, BeanUser.currentLoginUser.getUserid());
			java.sql.ResultSet rs1 = pst2.executeQuery();
			if(!rs1.next())
				stepnum = 0;
			else
				stepnum = rs1.getInt(1);
			rs1.close();
			pst2.close();
			//更新BeanPlan的步骤数
			sql = "update beanplan set step_number=? where plan_id=? and userid=?";
			java.sql.PreparedStatement pst3 = conn.prepareStatement(sql);
			pst3.setInt(1, stepnum);
			pst3.setInt(2, plan.getPlan_Id());
			pst3.setString(3, BeanUser.currentLoginUser.getUserid());
			pst3.execute();
			pst3.close();
			/**
			sql = "select * from beanplan where plan_id=?,userid=?";
			java.sql.PreparedStatement pst2 = conn.prepareStatement(sql);
			pst2.setInt(1, plan.getPlan_Id());
			pst2.setString(2, BeanUser.currentLoginUser.getUserid());
			java.sql.ResultSet rs1 = pst2.executeQuery();
			rs1.next();
			stepnum = rs.getInt("step_number")+1;
			rs1.close();
			pst2.close();
				//beanplan步骤数+1
			sql = "update beanplan set step_number=? where plan_id=?,userid=?";
			java.sql.PreparedStatement pst3 = conn.prepareStatement(sql);
			pst3.setInt(1, stepnum);
			pst3.setInt(2, plan.getPlan_Id());
			pst3.setString(3, BeanUser.currentLoginUser.getUserid());
			pst3.executeUpdate();
			pst3.close();
			*/
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
			}
		}
	}

	@Override
	public List<BeanStep> loadSteps(BeanPlan plan) throws BaseException {
	
		List<BeanStep> result=new ArrayList<BeanStep>();
		
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from beanstep where plan_id=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, plan.getPlan_Id());
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				BeanStep p=new BeanStep();
				p.setStep_Id(rs.getInt(1));
				p.setStep_Name(rs.getString(2));
				p.setPlanStartDate(rs.getDate(3));
				p.setPlanFinishDate(rs.getDate(4));
				p.setActualStartDate(rs.getDate(5));
				p.setActualFinishDate(rs.getDate(6));
				p.setBeanPlan(plan);
				result.add(p);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally
		{
			try {
				if(conn!=null)
				{
					conn.close();
				}
			} catch (SQLException e) {
				// TODO: handle exception
			}
		}
		//result.add(p);
		return result;
	}

	@Override
	public void deleteStep(BeanStep step) throws BaseException {
		// TODO Auto-generated method stub
		/**
		 * 删除步骤，
		 * 注意删除后需调整计划表中对应的步骤数量
		 * @param step
		 * @throws BaseException
		 */
		Connection conn = null;
		int stepnum = 0;
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from beanstep where step_id=? and plan_id=? ";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, step.getStep_Id());
			pst.setInt(2, step.getBeanPlan().getPlan_Id());
			//pst.setString(3, BeanUser.currentLoginUser.getUserid());
			pst.execute();
			pst.close();
			//删除1个步骤后，上调大于这个（删除的）步骤号的所有步骤；令它们各自都 -1 ；使步骤顺序化
			sql = "update beanstep set step_id = step_id - 1 where step_id > ? and plan_id = ? ";
			java.sql.PreparedStatement pst0 = conn.prepareStatement(sql);
			pst0.setInt(1, step.getStep_Id());
			pst0.setInt(2, step.getBeanPlan().getPlan_Id());
			//pst0.setString(3, BeanUser.currentLoginUser.getUserid());
			pst0.execute();
			pst0.close();
			//查询BeanStep 最大步骤数
			sql = "select max(step_id) from beanstep where step_id=? and plan_id=?";
			java.sql.PreparedStatement pst1 = conn.prepareStatement(sql);
			pst1.setInt(1, step.getStep_Id());
			pst1.setInt(2, step.getBeanPlan().getPlan_Id());
			//pst1.setString(3, BeanUser.currentLoginUser.getUserid());
			java.sql.ResultSet rs = pst1.executeQuery();
			if(rs.next())
				stepnum = rs.getInt(1);
			else
				stepnum = 0;
			rs.close();
			pst1.close();
			//修改BeanPlan的步骤 -1
			sql = "update beanplan set step_number=? where plan_id=? and userid=?";
			java.sql.PreparedStatement pst2 = conn.prepareStatement(sql);
			pst2.setInt(1, stepnum);
			pst2.setInt(2, step.getBeanPlan().getPlan_Id());
			pst2.setString(3, BeanUser.currentLoginUser.getUserid());
			pst2.executeUpdate();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void startStep(BeanStep step) throws BaseException {
		// TODO Auto-generated method stub
		/**
		 * 设置当前步骤的实际开始时间，及对应的计划表中已开始步骤数量
		 * 
		 * @param step
		 * @throws BaseException
		 */
		Connection conn = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			conn = DBUtil.getConnection();
			String sql = "select actualstartdate from beanstep where step_id=? and plan_id=?";
			/**
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, step.getStep_Id());
			pst.setInt(2, step.getBeanPlan().getPlan_Id());
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next())
				throw new BusinessException("此步骤已经开始！");
			rs.close();
			pst.close();
			*/
			sql = "update beanstep set actualstartdate = ? where step_id=? and plan_id=?";
			java.sql.PreparedStatement pst1 = conn.prepareStatement(sql);
			pst1.setDate(1, new java.sql.Date(System.currentTimeMillis()));
			pst1.setInt(2, step.getStep_Id());
			pst1.setInt(3, step.getBeanPlan().getPlan_Id());
			pst1.execute();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally
		{
			try {
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	@Override
	public void finishStep(BeanStep step) throws BaseException {
		// TODO Auto-generated method stub
		/**
		 * 设置当前步骤的实际完成时间，及对应的计划表中已完成步骤数量
		 * @param step
		 * @throws BaseException
		 */
		Connection conn = null;
		try {
			int acend = 0;
			conn = DBUtil.getConnection();
			String sql = "update beanstep set actualfinishdate=? where step_id=? and plan_id=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setDate(1, new java.sql.Date(System.currentTimeMillis()));
			pst.setInt(2, step.getStep_Id());
			pst.setInt(3, step.getBeanPlan().getPlan_Id());
			pst.executeUpdate();
			pst.close();
			
			//查找实际完成时间的数量
			sql = "select actualfinishdate from beanstep where  plan_id=?";
			java.sql.PreparedStatement pst1 = conn.prepareStatement(sql);
			//pst1.setInt(1, step.getStep_Id());
			pst1.setInt(1, step.getBeanPlan().getPlan_Id());
			java.sql.ResultSet rs = pst1.executeQuery();
			while(rs.next())
				if(rs.getDate(1)!=null||"null".equals(rs.getDate(1)))
					acend = acend + 1;
			
			rs.close();
			pst1.close();
			sql = "update beanplan set completed_number=? where userid=? and plan_id=?";
			java.sql.PreparedStatement pst2 = conn.prepareStatement(sql);
			pst2.setInt(1, acend);
			pst2.setString(2, BeanUser.currentLoginUser.getUserid());
			pst2.setInt(3, step.getBeanPlan().getPlan_Id());
			pst2.execute();
			pst2.close();
			/*
			sql = "select completed_number from beanplan where userid=? and plan_id=?";
			java.sql.PreparedStatement pst0 = conn.prepareStatement(sql);
			pst0.setString(1, BeanUser.currentLoginUser.getUserid());
			pst0.setInt(2, step.getBeanPlan().getPlan_Id());
			java.sql.ResultSet rs0 = pst0.executeQuery();
			rs0.next();
			*/
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally
		{
			try {
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	@Override
	/**
	 * 调整当前步骤的顺序号
	 * 注意：数据库表中，plan_id,step_order上建立了唯一索引，调整当前步骤的序号值和上一步骤的序号值时不能出现序号值一样的情况
	 * @param step
	 * @throws BaseException
	 */
	public void moveUp(BeanStep step) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn = null;
		int stepbef = step.getStep_Id() - 1;
		int stepnow = step.getStep_Id();
		try {
			if(step.getStep_Id()==1)
				throw new BusinessException("此为第1个步骤，无法上移！");
			conn = DBUtil.getConnection();
			//查询最大步骤数：
			String sql0 = "select max(step_id) from beanstep where plan_id=?";
			java.sql.PreparedStatement pst0 = conn.prepareStatement(sql0);
			pst0.setInt(1, step.getBeanPlan().getPlan_Id());
			java.sql.ResultSet rs0 = pst0.executeQuery();
			if(rs0.next())
			{	
				if(rs0.getInt(1)==1)
					throw new BusinessException("只有1个步骤，无法上移！");
			}
			else 
				throw new BusinessException("没有步骤，无法上移！");
				
			//当前序号上移 -1
			String sql = "update beanstep set step_id=? where step_id=? and plan_id=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, rs0.getInt(1)+1);//要上移的当前主键先赋值为中间值(最大步骤数+1)
			pst.setInt(2, step.getStep_Id());
			pst.setInt(3, step.getBeanPlan().getPlan_Id());
			pst.executeUpdate();
			pst.close();
			//修改setStep_Id 使得可以连续上移
			//step.setStep_Id(step.getStep_Id()-1);
			
			//前一个下移：作交换 +1
			sql = "update beanstep set step_id=step_id + 1 where step_id=? and plan_id=?";
			java.sql.PreparedStatement pst1 = conn.prepareStatement(sql);
			pst1.setInt(1, stepbef);
			pst1.setInt(2, step.getBeanPlan().getPlan_Id());
			pst1.executeUpdate();
			pst1.close();
			//把作为中间值的序号改回来
			sql = "update beanstep set step_id=? where step_id=? and plan_id=?";
			java.sql.PreparedStatement pst2 = conn.prepareStatement(sql);
			pst2.setInt(1, stepbef);
			pst2.setInt(2, rs0.getInt(1)+1);
			pst2.setInt(3, step.getBeanPlan().getPlan_Id());
			pst2.executeUpdate();
			pst2.close();
			//
			rs0.close();
			pst0.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally
		{
			try {
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
	}

	@Override
	public void moveDown(BeanStep step) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn = null;
		int stepnext = step.getStep_Id() + 1;//下一个值
		try {
			conn = DBUtil.getConnection();
			//查询最大序号：(1)作为步骤数判断;(2)作为中间值
			String sql = "select max(step_id) from beanstep where plan_id=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, step.getBeanPlan().getPlan_Id());
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next())
			{
				if(step.getStep_Id()==rs.getInt(1))
					throw new BusinessException("已是最后一个步骤，无法下移！");
				if(rs.getInt(1)==1)
					throw new BusinessException("当前只有一个步骤（序号为1），无法下移！");
			}
			else 
				throw new BusinessException("没有步骤，无法下移！");
			//当前步骤序号先修改为中间值（最大序号+1）
			sql = "update beanstep set step_id=? where step_id=? and plan_id=?";
			java.sql.PreparedStatement pst1 = conn.prepareStatement(sql);
			pst1.setInt(1, rs.getInt(1)+1);
			pst1.setInt(2, step.getStep_Id());
			pst1.setInt(3, step.getBeanPlan().getPlan_Id());
			pst1.executeUpdate();
			pst1.close();
			//上移下一个步骤的序号：-1
			sql = "update beanstep set step_id=step_id - 1 where step_id=? and plan_id=?";
			java.sql.PreparedStatement pst2 = conn.prepareStatement(sql);
			pst2.setInt(1, stepnext);
			pst2.setInt(2, step.getBeanPlan().getPlan_Id());
			pst2.executeUpdate();
			pst2.close();
			
			//改回当前步骤，改变中间值
			sql = "update beanstep set step_id=? where step_id=? and plan_id=?";
			java.sql.PreparedStatement pst3 = conn.prepareStatement(sql);
			pst3.setInt(1, stepnext);
			pst3.setInt(2, rs.getInt(1)+1);
			pst3.setInt(3, step.getBeanPlan().getPlan_Id());
			pst3.executeUpdate();
			pst3.close();
			rs.close();
			pst.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally
		{
			try {
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

}
