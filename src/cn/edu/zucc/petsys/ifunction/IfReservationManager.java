package cn.edu.zucc.petsys.ifunction;

import java.util.List;

import cn.edu.zucc.petsys.bean.BeanRes;
import cn.edu.zucc.petsys.tool.BaseException;

public interface IfReservationManager {

	public void addReservation(int pid,String rsername, String rstatus) throws BaseException;
	public List<BeanRes> loadAllthroughStat(String rstatus,String rapply) throws BaseException;
	public BeanRes loadOneRes(int rid) throws BaseException;
	public void updateRes(int rid ,String rstatus) throws BaseException;
	public List<BeanRes> loadAllthroughPid(int pid) throws BaseException;
	public void alterApply(int rid,String apply) throws BaseException;
	public void deleteRes(int rid) throws BaseException;
}
