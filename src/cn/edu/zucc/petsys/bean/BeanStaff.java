package cn.edu.zucc.petsys.bean;

public class BeanStaff {

	public static BeanStaff currentLoginStaff = null;
	private int sid;//操作员登录账号
	private String sname;
	private String srank;
	private String spwd;
	private String suser;
	
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSrank() {
		return srank;
	}
	public void setSrank(String srank) {
		this.srank = srank;
	}
	public String getSpwd() {
		return spwd;
	}
	public void setSpwd(String spwd) {
		this.spwd = spwd;
	}
	public String getSuser() {
		return suser;
	}
	public void setSuser(String suser) {
		this.suser = suser;
	}
	
	
}
