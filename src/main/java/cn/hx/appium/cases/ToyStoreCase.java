package cn.hx.appium.cases;

import java.lang.reflect.Method;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import cn.hx.appium.base.AndroidDriverBase;
import cn.hx.appium.page.ToyStorePage;
import cn.hx.appium.testng.Assertion;
import cn.hx.appium.util.ProUtil;

public class ToyStoreCase extends CaseBase{
	/*public static AndroidDriverBase driver;
	public static Assertion as;
	public static ProUtil p;
	public ToyStorePage toyStoreTest;
	
	@BeforeClass
	@Parameters({ "udid", "port" })
	public void beforeClass(String udid, String port) {
		try {
			System.out.println("读到的udid是："+udid+"读到的port是："+port);
			driver=driverInit(udid, port);
			driver.implicitlyWait(10);
			as=new Assertion(driver);
			toyStoreTest=new ToyStorePage(driver);
			p=new ProUtil("configs/caps.properties");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("执行测试test");
	}*/
	
	private AndroidDriverBase driver;
	public ToyStorePage toyStoreTest;
	public Assertion as;
	public ProUtil p;
	
	@BeforeClass
	@Parameters({"udid"})
	public void beforeClass(String udid) {
		try {
			driver=LoginRegisterCase.driverMap.get(udid);
			as=new Assertion(driver);
			p=new ProUtil("configs/caps.properties");
			toyStoreTest=new ToyStorePage(driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("——————————————>设备"+ driver.getCapabilities().getCapability("udid") +"开始执行:" + this.getClass().getName() + "的测试");
	}
	
	//共享计划筛选
	@Test(priority=81)
	public void shareFilterCase() {
		boolean flag = toyStoreTest.shareFilter();
		as.assertEquals(flag, true, "共享计划筛选");
	}
	
	//筛选
	@Test(priority=82)
	public void filterCase() throws Exception {
		boolean flag = toyStoreTest.filter();
		as.assertEquals(flag, true, "筛选");
	}
	
	//立即购买微信
//	@Test(priority=83)
	public void buyByWeChatCase() throws Exception {
		boolean flag = toyStoreTest.buyByWeChat();
		as.assertEquals(flag, true, "立即购买微信");
	}
	
//	立即购买支付宝
//	@Test(priority=84)
	public void bugByAliPayCase() throws Exception {
		boolean flag = toyStoreTest.bugByAliPay();
		as.assertEquals(flag, true, "立即购买支付宝");
	}
	
	//立即租
	@Test(priority=85)
	public void rentByNowCase() throws Exception {
		boolean flag = toyStoreTest.rentByNow();
		as.assertEquals(flag, true, "立即租");
	}
	
	//加入购物车后租
	@Test(priority=86)
	public void rentByCarCase() throws Exception {
		boolean flag = toyStoreTest.rentByCar();
		as.assertEquals(flag, true, "加入购物车后租");
	}
	
	@AfterMethod
	public void aftermethod(){
		//重启apk
		driver.startActivity(p.getPro("appPackage"), p.getPro("appActivity"));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@BeforeMethod
	public void beforeMethod(Method method){
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>设备"+ driver.getCapabilities().getCapability("udid") +"开始执行:" + method.getName()+"用例");
	}
	
}
