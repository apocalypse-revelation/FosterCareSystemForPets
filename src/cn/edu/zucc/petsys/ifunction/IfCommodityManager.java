package cn.edu.zucc.petsys.ifunction;

import java.util.List;

import cn.edu.zucc.petsys.bean.BeanInfo;
import cn.edu.zucc.petsys.tool.BaseException;

public interface IfCommodityManager {

	public void addCommodity(String iname,int tid,String ibrand, Double iprice, int iquantity) throws BaseException;
	public List<BeanInfo> loadAll() throws BaseException;//作为combobox的商品下拉选择框的size
	public BeanInfo getCommodity(int iid) throws BaseException;
	public void updateOneCommodity(int iid,String iname,int tid,String ibrand,int quantity,Double price ) throws BaseException;
	public void deleteOneCommodity(int iid) throws BaseException;
	//模糊查询
	public List<BeanInfo> fuzzyNameSearch(String iname) throws BaseException;
	public List<BeanInfo> fuzzyBrandSearch(String ibrand) throws BaseException;
	public List<BeanInfo> fuzzyNBSearch(String iname, String ibrand) throws BaseException;
	//直接查询
	public List<BeanInfo> accuracyNameSearch(String iname) throws BaseException;
	public List<BeanInfo> accuracyBrandSearch(String ibrand) throws BaseException;
	public List<BeanInfo> accuracyNBSearch(String iname,String ibrand) throws BaseException;
	//获得相同类别的商品
	public List<BeanInfo> loadCommodityThroughType(int tid) throws BaseException;
	//获得相同类别相同名称且不同品牌的商品 
	public List<BeanInfo> loadCommodityThroughTypeName(int tid,String iname) throws BaseException;
	//类别+名称+品牌唯一确定一个商品
	public BeanInfo loadOneCommodity(int tid,String iname,String ibrand) throws BaseException;
}
