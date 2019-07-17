package cn.edu.zucc.personplan.model;

public class BeanUser {
	public static BeanUser currentLoginUser=null;
	private String userid;
	private String pwd;
	private String pwd2;
	//public static String id;
	//public static String password;
	public String getUserid()
	{
		return userid;
	}
	public void setUserid(String userid)
	{
		this.userid = userid;
	}
	public String getPwd()
	{
		return pwd;
	}
	public void setPwd(String pwd)
	{
		this.pwd = pwd;
	}
	public String getPwd2()
	{
		return pwd2;
	}
	public void setPwd2(String pwd2)
	{
		this.pwd2 = pwd2;
	}
}
