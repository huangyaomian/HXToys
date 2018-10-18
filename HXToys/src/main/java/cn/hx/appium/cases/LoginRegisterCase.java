package cn.hx.appium.cases;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import cn.hx.appium.base.AndroidDriverBase;
import cn.hx.appium.page.LoginRegisterPage;
import cn.hx.appium.testng.Assertion;
import cn.hx.appium.util.ProUtil;

public class LoginRegisterCase extends CaseBase{
	public static AndroidDriverBase driver;
	public static Assertion as;
	public static ProUtil p;
	public LoginRegisterPage loginRegisterTest;
	
	@BeforeClass
	@Parameters({ "udid", "port" })
	public void beforeClass(String udid, String port) {
		try {
			System.out.println("读到的udid是："+udid+"读到的port是："+port);
			driver=driverInit(udid, port);
			driver.implicitlyWait(10);
			as=new Assertion(driver);
			loginRegisterTest=new LoginRegisterPage(driver);
			p=new ProUtil("configs/caps.properties");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("执行" + this.getClass().getName() + "的测试");
	}
	
	
//	@Test(priority=0)
	public void weChatFristCase() throws Exception {
		boolean flag = loginRegisterTest.weChatFrist();
		as.assertEquals(flag, true, "微信第一次注册");
	}
	
//	@Test(priority=1)
	public void weChatSecondCase() throws Exception {
		boolean flag = loginRegisterTest.weChatSecond();
		as.assertEquals(flag, true, "微信第二次注册");
	}
	
//	@Test(priority=2)
	public void weChatLoginCase() throws Exception {
		boolean flag = loginRegisterTest.weChatLogin();
		as.assertEquals(flag, true, "微信登录");
	}
	
//	@Test(priority=3)
	public void qqFristCase() throws Exception {
		boolean flag = loginRegisterTest.qqFrist();
		as.assertEquals(flag, true, "QQ第一次登录");
	}
	
//	@Test(priority=4)
	public void qqSecondCase() throws Exception {
		boolean flag = loginRegisterTest.qqSecond();
		as.assertEquals(flag, true, "QQ第二次登录");
	}
	
	@Test(priority=5)
	public void qqLoginCase() throws Exception {
		
		boolean flag = loginRegisterTest.qqLogin();
		as.assertEquals(flag, true, "QQ登录");
	}
	
	
//	@Test(priority=6)
	public void phoneFristCase() {
		boolean flag = loginRegisterTest.phoneFrist();
		as.assertEquals(flag, true, "手机第1次注册");
	}
	
//	@Test(priority=7)
	public void phoneSecondCase() throws Exception {
		boolean flag = loginRegisterTest.phoneSecond();
		as.assertEquals(flag, true, "手机第2次注册");
	}
	
//	@Test(priority=8)
	public void phoneLoginCase() throws Exception {
		boolean flag = loginRegisterTest.phoneLogin("13510526236");
		as.assertEquals(flag, true, "已注册手机登录");
	}
	
	@BeforeMethod
	public void beforeMethod(){
		driver.resetApp();
		try {
			Thread.sleep(1800);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterSuite
	public void quit(){
		driver.quit();
	}
}
