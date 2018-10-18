package cn.hx.appium.cases;


import cn.hx.appium.base.AndroidDriverBase;
import cn.hx.appium.base.AndroidSpecific;
import cn.hx.appium.base.CrazyPath;
import cn.hx.appium.util.ProUtil;

public class CaseBase {
	
	public static AndroidDriverBase driver;

	public AndroidDriverBase driverInit(String udid, String port)
			throws Exception {
		ProUtil p = new ProUtil(CrazyPath.globalPath);
		String server=p.getPro("server");
		String capsPath=CrazyPath.capsPath;
		System.out.println(capsPath);
		AndroidSpecific as=new AndroidSpecific();
		//获取原本输入法，方便后续设置回去
		String input=as.getDefaultInput(udid);
		System.out.println("开始创建server连接" + "======连接"+udid+"端口"+port);
		driver = new AndroidDriverBase(server, port, capsPath, udid, input);
		return driver;
	}
}
