package cn.edu.zucc.petsys.ifunction;

import java.util.List;

import cn.edu.zucc.petsys.bean.BeanPet;
import cn.edu.zucc.petsys.bean.BeanRes;
import cn.edu.zucc.petsys.bean.BeanService;
import cn.edu.zucc.petsys.tool.BaseException;

public interface IfServiceManager {

	public List<BeanService> loadAllService() throws BaseException;
	public int cusReserve(int pid, String retime,String sername) throws BaseException;
	public List<BeanRes> loadAllRes(String pres) throws BaseException;
	public List<BeanRes> loadParRes(String rstatus,String rapply) throws BaseException;
	
	//商家同意预约
	//public void confirmRes(int pid,String pres) throws BaseException;
	
	//服务
	public void addService(String sername) throws BaseException;
	public void updateService(String oldsername, String newsername) throws BaseException;
	public void stopService(String sername) throws BaseException;
	public void bootUpService(String sername) throws BaseException;
}
