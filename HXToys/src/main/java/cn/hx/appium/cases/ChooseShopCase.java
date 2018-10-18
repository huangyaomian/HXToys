package cn.hx.appium.cases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import cn.hx.appium.base.AndroidDriverBase;
import cn.hx.appium.page.ChooseShopPage;
import cn.hx.appium.testng.Assertion;
import cn.hx.appium.util.ProUtil;

public class ChooseShopCase extends CaseBase{
	/*public static AndroidDriverBase driver;
	public static Assertion as;
	public static ProUtil p;
	public ChooseShopPage ChooseShopTest;
	
	@BeforeClass
	@Parameters({ "udid", "port" })
	public void beforeClass(String udid, String port) {
		try {
			System.out.println("读到的udid是："+udid+"读到的port是："+port);
			driver=driverInit(udid, port);
			driver.implicitlyWait(10);
			as=new Assertion(driver);
			ChooseShopTest=new ChooseShopPage(driver);
			p=new ProUtil("configs/caps.properties");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("执行测试test");
	}*/
	
	private AndroidDriverBase driver;
	public ChooseShopPage ChooseShopTest;
	public Assertion as;
	public ProUtil p;
	
	@BeforeClass
	public void beforeClass() {
		try {
			driver=LoginRegisterCase.driver;
			as=new Assertion(driver);
			p=new ProUtil("configs/caps.properties");
			ChooseShopTest=new ChooseShopPage(driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("执行" + this.getClass().getName() + "的测试");
	}
	
	@Test(priority=11)
	public void chooseHistorySpCase() throws Exception{
		boolean flag = ChooseShopTest.chooseAllSp();
		as.assertEquals(flag, true, "切换所有中的玩具城");
	}
	
	
	@Test(priority=12)
	public void achooseAllSpCase() throws Exception{
		boolean flag = ChooseShopTest.chooseHistorySp();
		as.assertEquals(flag, true, "切换历史记录玩具城");
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
