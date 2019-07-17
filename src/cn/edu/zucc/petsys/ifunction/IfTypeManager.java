package cn.edu.zucc.petsys.ifunction;

import java.util.List;

import cn.edu.zucc.petsys.bean.BeanType;
import cn.edu.zucc.petsys.tool.BaseException;

public interface IfTypeManager {

	public void addType(String tname,String tintroduction) throws BaseException;
	public List<BeanType> loadAll() throws BaseException;
	public BeanType loadOne(String tname) throws BaseException;
	public void updateOneType(String newtname ,String introduction, String oldtname) throws BaseException;
	public void deleteOneType(String tname) throws BaseException;
	public BeanType loadType(int tid) throws BaseException;
}
