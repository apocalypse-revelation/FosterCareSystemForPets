package cn.edu.zucc.petsys.ifunction;

import java.util.List;

import cn.edu.zucc.petsys.bean.BeanOrd;
import cn.edu.zucc.petsys.tool.BaseException;

public interface IfOrderManager {

	public void addOrd(int iid,int cid,int oquantity,Double oprice) throws BaseException;
	public List<BeanOrd> loadAllOrd() throws BaseException;
	public BeanOrd loadOneOrd(int oid) throws BaseException;
	public List<BeanOrd> loadUnfilledOrd() throws BaseException;
	public List<BeanOrd> loadOrdwithStatus(String stat) throws BaseException;
	public List<BeanOrd> loadcusStatusOrd(String stat,int cid) throws BaseException;
	public List<BeanOrd> loadcusAllord(int cid) throws BaseException;
	
	public void sendOrd(int oid) throws BaseException;
	public void cancelOrd(int oid) throws BaseException;
	public void cusCancelOrd(int oid) throws BaseException;
	//用户确认收货
	public void gotOrd(int oid) throws BaseException;
	//恢复Commodity库存
	public void undoOrd(int iid,int quantity) throws BaseException;
	public void deleteOrd(int oid) throws BaseException;
}
