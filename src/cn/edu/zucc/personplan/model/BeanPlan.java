package cn.edu.zucc.personplan.model;

public class BeanPlan {
	public static final String[] tableTitles={"序号","名称","步骤数","已完成数"};
	/**
	 * 请自行根据javabean的设计修改本函数代码，col表示界面表格中的列序号，0开始
	 */
	private int plan_id;
	private String plan_name; 
	private int step_number;
	private int completed_number;
	private BeanUser user;
	public int getPlan_Id()
	{
		return this.plan_id;
	}
	public void setPlan_Id(int plan_id)
	{
		this.plan_id = plan_id;
	}
	public String getPlan_Name()
	{
		return this.plan_name;
	}
	public void setPlan_Name(String plan_name)
	{
		this.plan_name = plan_name;
	}
	public int getStep_Number()
	{
		return this.step_number;
	}
	public void setStep_Number(int step_number)
	{
		this.step_number = step_number;
	}
	public int getCompleted_Number()
	{
		return this.completed_number;
	}
	public void setCompleted_Number(int completed_number)
	{
		this.completed_number = completed_number;
	}
	public BeanUser getUser()
	{
		return this.user;
	}
	public void setUser(BeanUser user)
	{
		this.user = user;
	}
	//下面这个是计划表的文字数据
	public String getCell(int col){
		
		if(col==0) return String.valueOf(getPlan_Id());
		else if(col==1) return getPlan_Name();
		else if(col==2) return String.valueOf(getStep_Number());
		else if(col==3) return String.valueOf(getCompleted_Number());
		else return "";
		
	}

}
