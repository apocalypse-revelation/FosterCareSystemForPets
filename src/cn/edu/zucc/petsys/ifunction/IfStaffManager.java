package cn.edu.zucc.petsys.ifunction;

import java.util.List;

import cn.edu.zucc.petsys.bean.BeanCus;
import cn.edu.zucc.petsys.bean.BeanPet;
import cn.edu.zucc.petsys.bean.BeanStaff;
import cn.edu.zucc.petsys.tool.BaseException;

public interface IfStaffManager {

	public List<BeanPet> loadAllpet() throws BaseException;
	//public BeanStaff reg(String cusers, String pwd1, String pwd2, String cname, String ctelnum, String cemail,String cocontact) throws BaseException;//用户注册
	public BeanStaff login(String user, String spwd) throws BaseException;//管理员登录
	public List<BeanStaff> loadAllStaff() throws BaseException;
	public BeanStaff addStaff(String user,String pwd,String name, String rank) throws BaseException;
	public void deletestaff(int sid) throws BaseException;
}
