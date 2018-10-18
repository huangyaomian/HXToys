package cn.hx.appium.cases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import cn.hx.appium.base.AndroidDriverBase;
import cn.hx.appium.page.ExchangeTicketPage;
import cn.hx.appium.testng.Assertion;
import cn.hx.appium.util.ProUtil;

public class ExchangeTicketCase extends CaseBase{
	/*public static AndroidDriverBase driver;
	public static Assertion as;
	public static ProUtil p;
	public ExchangeTicketPage ExchangeTicketTest;

	@BeforeClass
	@Parameters({ "udid", "port" })
	public void beforeClass(String udid, String port) {
		try {
			System.out.println("读到的udid是："+udid+"读到的port是："+port);
			driver=driverInit(udid, port);
			driver.implicitlyWait(10);
			as=new Assertion(driver);
			ExchangeTicketTest=new ExchangeTicketPage(driver);
			p=new ProUtil("configs/caps.properties");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("执行测试test");
	}*/
	
	private AndroidDriverBase driver;
	public ExchangeTicketPage ExchangeTicketTest;
	public Assertion as;
	public ProUtil p;
	
	@BeforeClass
	public void beforeClass() {
		try {
			driver=LoginRegisterCase.driver;
			as=new Assertion(driver);
			p=new ProUtil("configs/caps.properties");
			ExchangeTicketTest=new ExchangeTicketPage(driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("执行" + this.getClass().getName() + "的测试");
	}
	
	@Test(priority=61)
	public void intExchangeCase() {
		boolean flag = ExchangeTicketTest.intExchange();
		as.assertEquals(flag, true, "积分兑换门票");
	}
	
	@Test(priority=62)
	public void goldExchangeCase() {
		boolean flag = ExchangeTicketTest.goldExchange();
		as.assertEquals(flag, true, "金币兑换门票");
	}
	
	@Test(priority=63)
	public void checkNumCase() {
		boolean flag = ExchangeTicketTest.checkNum();
		as.assertEquals(flag, true, "检查积分和金币余额");
	}
	
//	@Test(priority=28)
	public void weChatExchangeCase() {
		boolean flag = ExchangeTicketTest.weChatExchange();
		as.assertEquals(flag, true, "微信兑换门票");
	}
	
//	@Test(priority=29)
	public void alipayExchangeCase() {
		boolean flag = ExchangeTicketTest.alipayExchange();
		as.assertEquals(flag, true, "支付宝兑换门票");
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
