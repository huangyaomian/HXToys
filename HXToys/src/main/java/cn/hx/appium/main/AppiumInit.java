package cn.hx.appium.main;

import java.util.ArrayList;
import java.util.List;

import cn.hx.appium.util.DosCmd;
import cn.hx.appium.util.XmlUtil;
import cn.hx.appium.server.Port;
import cn.hx.appium.server.Servers;

public class AppiumInit {
	public static void init(){
		Servers servers=new Servers(new Port(new DosCmd()), new DosCmd());
		DosCmd dc=new DosCmd();
		if(dc.killServer()){
			try {
				System.out.println("开始启动appium服务");
				servers.startServers();
				System.out.println("appium服务启动完毕");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				List<String> classes=new ArrayList<String>();
				classes.add("cn.hx.appium.cases.LoginRegisterCase");
//				classes.add("cn.hx.appium.cases.ChooseShopCase");
//				classes.add("cn.hx.appium.cases.ExchangeTicketCase");
//				classes.add("cn.hx.appium.cases.MeCase");
//				classes.add("cn.hx.appium.cases.PersonalDataCase");
//				classes.add("cn.hx.appium.cases.BabyFileCase");
////				classes.add("cn.hx.appium.cases.GoldCoinCase");
//				classes.add("cn.hx.appium.cases.ToyRentCase");
//				classes.add("cn.hx.appium.cases.ToyStoreCase");
//				classes.add("cn.hx.appium.cases.RandomPlayCase");
//				classes.add("cn.hx.appium.cases.ParentSpecialCase");
				XmlUtil.createTestngXml(classes);
//				XmlUtil.createTestngXml("cn.crazy.appium.testcases.PerInfoTest");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			System.out.println("清除appium服务失败");
		}
	}
}
