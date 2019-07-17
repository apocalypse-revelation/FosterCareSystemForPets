package cn.edu.zucc.petsys.bean;

import java.sql.Blob;
import java.sql.Date;
import java.sql.Timestamp;





public class BeanPet {

	private int pid;
	private String pname;
	private String pbreeds;
	private String psex;
	private String pimg;
	private Date pregtime;
	private String pfoster;
	private Date pfosterstart;
	private Date pfosterend;
	private int cid;
	private String preserve;
	private Date pretime;
	private String sername;
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPbreeds() {
		return pbreeds;
	}
	public void setPbreeds(String pbreeds) {
		this.pbreeds = pbreeds;
	}
	
	
	public Date getPregtime() {
		return pregtime;
	}
	public void setPregtime(Date pregtime) {
		this.pregtime = pregtime;
	}
	public String getPsex() {
		return psex;
	}
	public void setPsex(String psex) {
		this.psex = psex;
	}
	public String getPimg() {
		return pimg;
	}
	public void setPimg(String pimg) {
		this.pimg = pimg;
	}
	public String getPfoster() {
		return pfoster;
	}
	public void setPfoster(String pfoster) {
		this.pfoster = pfoster;
	}
	public Date getPfosterstart() {
		return pfosterstart;
	}
	public void setPfosterstart(Date pfosterstart) {
		this.pfosterstart = pfosterstart;
	}
	public Date getPfosterend() {
		return pfosterend;
	}
	public void setPfosterend(Date pfosterend) {
		this.pfosterend = pfosterend;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getPreserve() {
		return preserve;
	}
	public void setPreserve(String preserve) {
		this.preserve = preserve;
	}
	public Date getPretime() {
		return pretime;
	}
	public void setPretime(Date pretime) {
		this.pretime = pretime;
	}
	public String getSername() {
		return sername;
	}
	public void setSername(String sername) {
		this.sername = sername;
	}
	
	
	
}
