package cn.edu.zucc.personplan.model;

import java.sql.Date;

public class BeanStep {
	public static final String[] tblStepTitle={"序号","名称","计划开始时间","计划完成时间","实际开始时间","实际完成时间"};
	/**
	 * 请自行根据javabean的设计修改本函数代码，col表示界面表格中的列序号，0开始
	 */
	private int step_id;
	private String step_name;
	private Date planstartdate;
	private Date planfinishdate;
	private Date actualstartdate;
	private Date actualfinishdate;
	private BeanPlan beanplan;
	public BeanPlan getBeanPlan()
	{
		return this.beanplan;
	}
	public void setBeanPlan(BeanPlan beanplan)
	{
		this.beanplan = beanplan;
	}
	public int getStep_Id()
	{
		return this.step_id;
	}
	public void setStep_Id(int step_id)
	{
		this.step_id = step_id;
	}
	public String getStep_Name()
	{
		return this.step_name;
	}
	public void setStep_Name(String step_name)
	{
		this.step_name = step_name;
	}
	public Date getPlanStartDate()
	{
		return this.planstartdate;
	}
	public void setPlanStartDate(Date planstartdate)
	{
		this.planstartdate = planstartdate;
	}
	public Date getPlanFinishDate()
	{
		return this.planfinishdate;
	}
	public void setPlanFinishDate(Date planfinishdate)
	{
		this.planfinishdate = planfinishdate;
	}
	public Date getActualStartDate()
	{
		return this.actualstartdate;
	}
	public void setActualStartDate(Date actualstartdate)
	{
		this.actualstartdate = actualstartdate;
	}
	public Date getActualFinishDate()
	{
		return this.actualfinishdate;
	}
	public void setActualFinishDate(Date actualfinishdate)
	{
		this.actualfinishdate = actualfinishdate;
	}
	
	public String getCell(int col){
		if(col==0) return String.valueOf(getStep_Id());
		else if(col==1) return getStep_Name();
		else if(col==2) return String.valueOf(planstartdate);
		else if(col==3) return String.valueOf(planfinishdate);
		else if(col==4) return String.valueOf(actualstartdate);
		else if(col==5) return String.valueOf(actualfinishdate);
		else return "";
	}
}
