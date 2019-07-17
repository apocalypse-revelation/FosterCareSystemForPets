package cn.edu.zucc.petsys.ifunction;


import java.util.List;

import cn.edu.zucc.petsys.bean.BeanCus;
import cn.edu.zucc.petsys.tool.BaseException;

public interface IfCustomerManager {

	public BeanCus reg(String cusers, String pwd1, String pwd2, String cname, String ctelnum, String cemail,String cocontact) throws BaseException;//用户注册
	public void changePwd(String cusers, String oldpwd, String newpwd) throws BaseException;//用户修改密码
	public BeanCus login(String cusers, String pwd) throws BaseException;//用户登录
	public List<BeanCus> loadAll() throws BaseException;
	public BeanCus loadCus(String cusers) throws BaseException;
	public void deleteCus(String cusers) throws BaseException;
	public String getName(int cid) throws BaseException;//通过用户编号cid查找用户的真实姓名
	//购买商品
	public void cusPurchase(int iid,int amount) throws BaseException;
	//用户模糊查询
	public List<BeanCus> fuzzySearch(String cusers) throws BaseException;
}
