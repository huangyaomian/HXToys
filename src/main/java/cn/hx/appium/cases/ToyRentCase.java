package cn.hx.appium.cases;

import java.lang.reflect.Method;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import cn.hx.appium.base.AndroidDriverBase;
import cn.hx.appium.page.ToyRentPage;
import cn.hx.appium.testng.Assertion;
import cn.hx.appium.util.ProUtil;

public class ToyRentCase extends CaseBase{
	/*public AndroidDriverBase driver;
	public Assertion as;
	public ProUtil p;
	public ToyRentPage ToyRentTest;

	@BeforeClass
	@Parameters({ "udid", "port" })
	public void beforeClass(String udid, String port) {
		try {
			System.out.println("读到的udid是："+udid+"读到的port是："+port);
			driver=driverInit(udid, port);
			driver.implicitlyWait(10);
			as=new Assertion(driver);
			ToyRentTest=new ToyRentPage(driver);
			p=new ProUtil("configs/caps.properties");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("执行测试test");
	}*/

	private AndroidDriverBase driver;
	public ToyRentPage ToyRentTest;
	public Assertion as;
	public ProUtil p;

	@BeforeClass
	@Parameters({"udid"})
	public void beforeClass(String udid) {
		try {
			driver=LoginRegisterCase.driverMap.get(udid);
			as=new Assertion(driver);
			p=new ProUtil("configs/caps.properties");
			ToyRentTest=new ToyRentPage(driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("——————————————>设备"+ driver.getCapabilities().getCapability("udid") +"开始执行:" + this.getClass().getName() + "的测试");
	}

	@Test(priority=71)
	public void typeAndAgeCase() throws Exception {
		boolean flag = ToyRentTest.typeAndAge();
		as.assertEquals(flag, true, "验证筛选功能");
	}

	@Test(priority=72)
	public void addCarRentCase() throws Exception {
		boolean flag = ToyRentTest.addCarRent();
		as.assertEquals(flag, true, "列表加入购物车");
	}

	@Test(priority=73)
	public void addCarDetailCase() throws Exception {
		boolean flag = ToyRentTest.addCarDetail();
		as.assertEquals(flag, true, "详情加入购物车");
	}

	@Test(priority=74)
	public void carOperateCase() throws Exception {
		boolean flag = ToyRentTest.carOperate();
		as.assertEquals(flag, true, "租你想玩购物车增删清空");
	}

//	@Test(priority=75)
	public void payByWXCase() throws Exception {
		boolean flag = ToyRentTest.payByWX();
		as.assertEquals(flag, true, "租你想玩微信购买");
	}

//	@Test(priority=76)
	public void payByAliCase() throws Exception {
		boolean flag = ToyRentTest.payByAli();
		as.assertEquals(flag, true, "租你想玩支付宝购买");
	}

	@Test(priority=77)
	public void rentByNowCase() throws Exception {
		boolean flag = ToyRentTest.rentByNow();
		as.assertEquals(flag, true, "租你想玩金币立即购买");
	}

	@Test(priority=78)
	public void rentByCarCase() throws Exception {
		boolean flag = ToyRentTest.rentByCar();
		as.assertEquals(flag, true, "租你想玩购物车购买");
	}


	@AfterMethod
	public void aftermethod(){
		//重启apk
		try {
			driver.startActivity(p.getPro("appPackage"), p.getPro("appActivity"));
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
