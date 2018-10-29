package cn.hx.appium.cases;

import java.lang.reflect.Method;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import cn.hx.appium.base.AndroidDriverBase;
import cn.hx.appium.page.ParentSpecialPage;
import cn.hx.appium.testng.Assertion;
import cn.hx.appium.util.ProUtil;

public class ParentSpecialCase extends CaseBase{
	/*public AndroidDriverBase driver;
	public Assertion as;
	public ProUtil p;
	public ParentSpecialPage parentSpecialTest;

	@BeforeClass
	@Parameters({ "udid", "port" })
	public void beforeClass(String udid, String port) {
		try {
			System.out.println("读到的udid是："+udid+"读到的port是："+port);
			driver=driverInit(udid, port);
			driver.implicitlyWait(10);
			as=new Assertion(driver);
			parentSpecialTest=new ParentSpecialPage(driver);
			p=new ProUtil("configs/caps.properties");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("执行测试test");
	}*/

	private AndroidDriverBase driver;
	public ParentSpecialPage parentSpecialTest;
	public Assertion as;
	public ProUtil p;

	@BeforeClass
	@Parameters({"udid"})
	public void beforeClass(String udid) {
		try {
			driver=LoginRegisterCase.driverMap.get(udid);
			as=new Assertion(driver);
			p=new ProUtil("configs/caps.properties");
			parentSpecialTest=new ParentSpecialPage(driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("——————————————>设备"+ driver.getCapabilities().getCapability("udid") +"开始执行:" + this.getClass().getName() + "的测试");
	}


	@Test(priority=101)
	public void addCarCase() throws Exception {
		boolean flag = parentSpecialTest.addCar();
		as.assertEquals(flag, true, "加入购物车");
	}

	@Test(priority=102)
	public void checkInfoCase() throws Exception {
		boolean flag = parentSpecialTest.checkInfo();
		as.assertEquals(flag, true, "商品信息是否一致");
	}

	@Test(priority=103)
	public void buyByDetailCase() throws Exception {
		boolean flag = parentSpecialTest.buyByDetail();
		as.assertEquals(flag, true, "详情购买");
	}

	@Test(priority=104)
	public void buyByCarCase() throws Exception {
		boolean flag = parentSpecialTest.buyByCar();
		as.assertEquals(flag, true, "购物车购买");
	}

	@Test(priority=105)
	public void updataNumByHomeCase() throws Exception {
		boolean flag = parentSpecialTest.updataNumByHome();
		as.assertEquals(flag, true, "首页商品的购物车数量更新");
	}

	@Test(priority=106)
	public void updataNumCase() throws Exception {
		boolean flag = parentSpecialTest.updataNum();
		as.assertEquals(flag, true, "家长专区列表商品的购物车数量更新");
	}

	@Test(priority=107)
	public void updataNumByListCase() throws Exception {
		boolean flag = parentSpecialTest.updataNumByList();
		as.assertEquals(flag, true, "首页和家长专区红点数的更新");
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
