package cn.edu.zucc.petsys.ifunction;

import java.util.List;

import cn.edu.zucc.petsys.bean.BeanPet;
import cn.edu.zucc.petsys.tool.BaseException;

public interface IfPet {

	public List<BeanPet> loadAll() throws BaseException;
	public BeanPet loadPet(int pid) throws BaseException;
	public void addPet(String pname,String pbreeds ,String psex,String pimg) throws BaseException;
	public void UpdatePetFoster(int pid) throws BaseException;
	public long EndPetFoster(int pid) throws BaseException;
//	public BeanPet loadPet_Withoutlogin
	public void deletePet(int pid) throws BaseException;
	public List<BeanPet> loadAllthroughCid(int cid) throws BaseException;

}
