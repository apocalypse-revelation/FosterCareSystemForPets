package cn.edu.zucc.petsys.tool;

import cn.edu.zucc.petsys.function.CustomerManager;
import cn.edu.zucc.petsys.function.PetManager;
import cn.edu.zucc.petsys.function.SystemManager;
import cn.edu.zucc.petsys.function.TypeManager;
import cn.edu.zucc.petsys.ifunction.IfCommodityManager;
import cn.edu.zucc.petsys.ifunction.IfCustomerManager;
import cn.edu.zucc.petsys.ifunction.IfOrderManager;
import cn.edu.zucc.petsys.ifunction.IfPet;
import cn.edu.zucc.petsys.ifunction.IfReservationManager;
import cn.edu.zucc.petsys.ifunction.IfServiceManager;
import cn.edu.zucc.petsys.ifunction.IfStaffManager;
import cn.edu.zucc.petsys.ifunction.IfTypeManager;
import cn.edu.zucc.petsys.ui.StaffManager;

public class PetsysUtil {
	public static IfCustomerManager cusManager = new CustomerManager();//需要换成自行设计的实现类
	public static IfPet petManager = new PetManager();
	public static IfStaffManager sysManager = new SystemManager();
	public static IfTypeManager typeManager = new TypeManager();
	public static IfCommodityManager comManager = new cn.edu.zucc.petsys.function.CommodityManager();
	public static IfOrderManager ordManager = new cn.edu.zucc.petsys.function.OrderManager();
	public static IfServiceManager serManager = new cn.edu.zucc.petsys.function.ServiceManager();
	public static IfReservationManager resManager = new cn.edu.zucc.petsys.function.ReservationManager();
}

