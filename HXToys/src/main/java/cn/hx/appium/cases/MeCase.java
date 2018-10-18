package cn.hx.appium.cases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import cn.hx.appium.base.AndroidDriverBase;
import cn.hx.appium.page.MePage;
import cn.hx.appium.testng.Assertion;
import cn.hx.appium.util.ProUtil;

public class MeCase extends CaseBase{
	/*private AndroidDriverBase driver;
	public MePage MeTest;
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
			MeTest=new MePage(driver);
			p=new ProUtil("configs/caps.properties");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("执行测试test");
	}*/
	
	
	private AndroidDriverBase driver;
	public MePage MeTest;
	public Assertion as;
	public ProUtil p;
	
	@BeforeClass
	public void beforeClass() {
		try {
			driver=LoginRegisterCase.driver;
			as=new Assertion(driver);
			p=new ProUtil("configs/caps.properties");
			MeTest=new MePage(driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("执行" + this.getClass().getName() + "的测试");
	}
	
	@Test(priority=21)
	public void checkGoldCase() throws Exception {
		boolean flag = MeTest.checkGold();
		as.assertEquals(flag, true, "验证金币数量");
	}
	
	@Test(priority=22)
	public void checkIntegralCase() throws Exception {
		boolean flag = MeTest.checkIntegral();
		as.assertEquals(flag, true, "验证积分数量");
	}
	
	@Test(priority=24)
	public void feedbackCase() throws Exception {
		boolean flag = MeTest.feedback();
		as.assertEquals(flag, true, "意见反馈");
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
}
