package cn.hx.appium.cases;


import cn.hx.appium.base.AndroidDriverBase;
import cn.hx.appium.base.AndroidSpecific;
import cn.hx.appium.base.CrazyPath;
import cn.hx.appium.util.ProUtil;

public class CaseBase {
	public static ThreadLocal<AndroidDriverBase> ThreadDriver=new ThreadLocal<AndroidDriverBase>(); //添加线程安全用的
	public AndroidDriverBase driver;

	public AndroidDriverBase driverInit(String udid, String port)
			throws Exception {
		driver=ThreadDriver.get(); //添加线程安全用的
		if (null == driver) {
			Thread t = Thread.currentThread();
			System.out.println("---------------------线程号" + t.getId());
			ProUtil p = new ProUtil(CrazyPath.globalPath);
			String server=p.getPro("server");
			String capsPath=CrazyPath.capsPath;
			System.out.println(capsPath);
			//获取原本输入法，方便后续设置回去
			String input=AndroidSpecific.getDefaultInput(udid);
			System.out.println("开始创建server连接" + "======连接"+udid+"端口"+port);
			driver = new AndroidDriverBase(server, port, capsPath, udid, input);
			ThreadDriver.set(driver); //创建driver并使用ThreadDriver.set()方法绑定到线程中。
		}
		return driver;
	}
}
