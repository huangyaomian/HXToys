package cn.hx.appium.cases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import cn.hx.appium.base.AndroidDriverBase;
import cn.hx.appium.page.PersonalDataPage;
import cn.hx.appium.testng.Assertion;
import cn.hx.appium.util.ProUtil;

public class PersonalDataCase extends CaseBase{
	/*public AndroidDriverBase driver;
	public Assertion as;
	public ProUtil p;
	public PersonalDataPage PersonalDataTest;
	
	@BeforeClass
	@Parameters({ "udid", "port" })
	public void beforeClass(String udid, String port) {
		try {
			System.out.println("读到的udid是："+udid+"读到的port是："+port);
			driver=driverInit(udid, port);
			driver.implicitlyWait(10);
			as=new Assertion(driver);
			PersonalDataTest=new PersonalDataPage(driver);
			p=new ProUtil("configs/caps.properties");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("执行测试test");
	}*/
	
	private AndroidDriverBase driver;
	public PersonalDataPage PersonalDataTest;
	public Assertion as;
	public ProUtil p;
	
	@BeforeClass
	public void beforeClass() {
		try {
			driver=LoginRegisterCase.driver;
			as=new Assertion(driver);
			p=new ProUtil("configs/caps.properties");
			PersonalDataTest=new PersonalDataPage(driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("执行" + this.getClass().getName() + "的测试");
	}
	
	@Test(priority=31)
	public void phoneFristCase() throws Exception {
		boolean flag = PersonalDataTest.modifyImage();
		as.assertEquals(flag, true, "修改头像");
	}
	
	@Test(priority=32)
	public void modifyNickCase() throws Exception {
		boolean flag = PersonalDataTest.modifyNick();
		as.assertEquals(flag, true, "修改昵称");
	}
	
	@Test(priority=33)
	public void relatedOneselfCase() throws Exception {
		boolean flag = PersonalDataTest.relatedOneself();
		as.assertEquals(flag, true, "自己关联自己");
	}
	
	@Test(priority=34)
	public void sceondCodeRelatedCase() throws Exception {
		boolean flag = PersonalDataTest.sceondCodeRelated();
		as.assertEquals(flag, true, "第二次验证关联");
	}
	
	@Test(priority=35)
	public void relatedCase() throws Exception {
		boolean flag = PersonalDataTest.related();
		as.assertEquals(flag, true, "验证关联");
	}
	
//	@Test(priority=36)
	public void weChatBindCase() throws Exception {
		boolean flag = PersonalDataTest.weChatBind();
		as.assertEquals(flag, true, "微信绑定");
	}
	
	@Test(priority=37)
	public void QQBindCase() throws Exception {
		boolean flag = PersonalDataTest.QQBind();
		as.assertEquals(flag, true, "QQ绑定");
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
