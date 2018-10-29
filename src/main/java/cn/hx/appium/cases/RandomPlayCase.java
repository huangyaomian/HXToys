package cn.hx.appium.cases;

import java.lang.reflect.Method;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import cn.hx.appium.base.AndroidDriverBase;
import cn.hx.appium.page.RandomPlayPage;
import cn.hx.appium.testng.Assertion;
import cn.hx.appium.util.ProUtil;

public class RandomPlayCase extends CaseBase{
	/*private AndroidDriverBase driver;
	public RandomPlayPage randomPlayTest;
	public Assertion as;
	public ProUtil p;

	@BeforeClass
	@Parameters({ "udid", "port" })
	public void beforeClass(String udid, String port) {
		try {
			System.out.println("读到的udid是："+udid+"读到的port是："+port);
			driver=driverInit(udid, port);
			driver.implicitlyWait(10);
			as=new Assertion(driver);
			randomPlayTest=new RandomPlayPage(driver);
			p=new ProUtil("configs/caps.properties");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("执行测试test");
	}*/
	
	
	private AndroidDriverBase driver;
	public RandomPlayPage randomPlayTest;
	public Assertion as;
	public ProUtil p;
	
	@BeforeClass
	@Parameters({"udid"})
	public void beforeClass(String udid) {
		try {
			driver=LoginRegisterCase.driverMap.get(udid);
			as=new Assertion(driver);
			p=new ProUtil("configs/caps.properties");
			randomPlayTest=new RandomPlayPage(driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("——————————————>设备"+ driver.getCapabilities().getCapability("udid") +"开始执行:" + this.getClass().getName() + "的测试");
	}
	
	@Test(priority=91)
	public void compareGoodsCase() throws Exception {
		boolean flag = randomPlayTest.compareGoods();
		as.assertEquals(flag, true, "对比商品信息");
	}
	
	@Test(priority=92)
	public void checkInfoCase() throws Exception {
		boolean flag = randomPlayTest.checkInfo();
		as.assertEquals(flag, true, "图标展示");
	}
	
	@Test(priority=93)
	public void buyByCarCase() throws Exception {
		boolean flag = randomPlayTest.buyByCar();
		as.assertEquals(flag, true, "购物车内买单");
	}
	
//	@Test(priority=94)
	public void buyByNowCase() throws Exception {
		boolean flag = randomPlayTest.buyByNow();
		as.assertEquals(flag, true, "立即购买");
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
