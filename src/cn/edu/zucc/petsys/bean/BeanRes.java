package cn.edu.zucc.petsys.bean;

import java.sql.Date;

public class BeanRes {
	public static String resTime = null;
	private int rid;
	private int pid;
	private Date rtime;
	private String rsername;
	private String rstatus;
	private String rapply;
	private Date rrealfinished;
	
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public Date getRtime() {
		return rtime;
	}
	public void setRtime(Date rtime) {
		this.rtime = rtime;
	}
	public String getRsername() {
		return rsername;
	}
	public void setRsername(String rsername) {
		this.rsername = rsername;
	}
	public String getRstatus() {
		return rstatus;
	}
	public void setRstatus(String rstatus) {
		this.rstatus = rstatus;
	}
	public String getRapply() {
		return rapply;
	}
	public void setRapply(String rapply) {
		this.rapply = rapply;
	}
	public Date getRrealfinished() {
		return rrealfinished;
	}
	public void setRrealfinished(Date rrealfinished) {
		this.rrealfinished = rrealfinished;
	}

	
}
