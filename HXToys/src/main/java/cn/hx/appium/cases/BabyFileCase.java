package cn.hx.appium.cases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import cn.hx.appium.base.AndroidDriverBase;
import cn.hx.appium.page.BabyFilePage;
import cn.hx.appium.testng.Assertion;
import cn.hx.appium.util.ProUtil;

public class BabyFileCase extends CaseBase{
	/*public static AndroidDriverBase driver;
	public static Assertion as;
	public static ProUtil p;
	public BabyFilePage BabyFileTest;
	
	@BeforeClass
	@Parameters({ "udid", "port" })
	public void beforeClass(String udid, String port) {
		try {
			System.out.println("读到的udid是："+udid+"读到的port是："+port);
			driver=driverInit(udid, port);
			driver.implicitlyWait(10);
			as=new Assertion(driver);
			BabyFileTest=new BabyFilePage(driver);
			p=new ProUtil("configs/caps.properties");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("执行测试test");
	}*/
	
	private AndroidDriverBase driver;
	public BabyFilePage BabyFileTest;
	public Assertion as;
	public ProUtil p;
	
	@BeforeClass
	public void beforeClass() {
		try {
			driver=LoginRegisterCase.driver;
			as=new Assertion(driver);
			p=new ProUtil("configs/caps.properties");
			BabyFileTest=new BabyFilePage(driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("执行" + this.getClass().getName() + "的测试");
	}
	
	@Test(priority=41)
	public void addBBabyCase() throws Exception{
		boolean flag = BabyFileTest.addBBaby();
		as.assertEquals(flag, true, "添加男宝宝");
	}
	
	@Test(priority=42)
	public void addGBabyCase() throws Exception{
		boolean flag = BabyFileTest.addGBaby();
		as.assertEquals(flag, true, "添加nv宝宝");
	}
	
	@Test(priority=43)
	public void modifyBabyCase() throws Exception{
		System.out.println("asdf ");
		boolean flag = BabyFileTest.modifyBaby();
		as.assertEquals(flag, true, "修改宝宝信息");
	}
	
	@AfterMethod
	public void aftermethod(){
		//重启apk
		driver.startActivity(p.getPro("appPackage") , p.getPro("appActivity"));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
