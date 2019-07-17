package cn.edu.zucc.petsys.bean;


import java.sql.Date;
import java.sql.Timestamp;


public class BeanCus {

	public static BeanCus currentLoginUser = null;//当前登录用户信息
	private int cid;
	private String cname;
	private String ctelnum;
	private String cemail;
	private String cocontact;
	private String cusers;
	private String cpwd;
	private Timestamp cregdate;
	
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCtelnum() {
		return ctelnum;
	}
	public void setCtelnum(String ctelnum) {
		this.ctelnum = ctelnum;
	}
	public String getCemail() {
		return cemail;
	}
	public void setCemail(String cemail) {
		this.cemail = cemail;
	}
	public String getCocontact() {
		return cocontact;
	}
	public void setCocontact(String cocontact) {
		this.cocontact = cocontact;
	}
	public String getCusers() {
		return cusers;
	}
	public void setCusers(String cusers) {
		this.cusers = cusers;
	}
	public String getCpwd() {
		return cpwd;
	}
	public void setCpwd(String cpwd) {
		this.cpwd = cpwd;
	}
//	public Date getCregdate() {
//		return cregdate;
//	}
//	public void setCregdate(Date cregdate) {
//		this.cregdate = cregdate;
//	}
	public Timestamp getCregdate() {
		return cregdate;
	}
	public void setCregdate(Timestamp cregdate) {
		this.cregdate = cregdate;
	}
	
	
	
}
