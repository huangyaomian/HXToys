package cn.hx.appium.page;

import io.appium.java_client.android.AndroidElement;

import java.net.URLEncoder;
import java.util.List;

import org.openqa.selenium.WebElement;

import net.sf.json.JSONObject;

import cn.hx.appium.base.AndroidDriverBase;
import cn.hx.appium.base.CrazyPath;
import cn.hx.appium.util.AseUtil;
import cn.hx.appium.util.ConnectionHttp;
import cn.hx.appium.util.GetByLocator;
import cn.hx.appium.util.ImageUtil;
import cn.hx.appium.util.Log;
import cn.hx.appium.util.ProUtil;
import cn.hx.appium.util.RandomUtil;

public class PersonalDataPage extends BasePage{
	private Log logger = Log.getLogger(PersonalDataPage.class);
	private ProUtil  p = new ProUtil(CrazyPath.elementPath);
	public PersonalDataPage(AndroidDriverBase driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}


	//修改用户头像，拍照和相册选择两种方式
	public boolean modifyImage() throws Exception {
		super.click(GetByLocator.getLocator("rb_me"));
		super.click(GetByLocator.getLocator("meInfoBtn"));
		String path = System.getProperty("user.dir") + "/images/";
		driver.takeScreenForElement(driver.findElement(GetByLocator.getLocator("avatarImage")), path, "avatarImage1");
		super.click(GetByLocator.getLocator("avatarImage"));
		super.click(GetByLocator.getLocator("albumBtn"));
		List<AndroidElement> ivThumbs = driver.findElements(GetByLocator.getLocator("ivThumbs"));
		AndroidElement element = ivThumbs.get(RandomUtil.randomInt(2, ivThumbs.size()));
		super.click(element);
		super.click(GetByLocator.getLocator("OKBtn"));
		driver.takeScreenForElement(driver.findElement(GetByLocator.getLocator("avatarImage")), path, "avatarImage2");
		boolean falg = !ImageUtil.compareImg("images/" + "avatarImage1.png", "images/" + "avatarImage2.png");
		super.click(GetByLocator.getLocator("avatarImage"));
		super.click(GetByLocator.getLocator("cameraBtn"));
//		区别不同的手机拍照页面进行不同的拍照
		if (super.getPageSource().contains("NONE")) { //三星手机
			//先对焦再拍照就可以了。
			super.pressAny(80);
			super.pressAny(27);
			super.click(GetByLocator.getLocator("okay"));
			super.click(GetByLocator.getLocator("OKBtn"));
		}else if (super.getPageSource().contains("huawei")) { //华为手机
			super.click(GetByLocator.getLocator("shutter_button"));
			super.click(GetByLocator.getLocator("btn_review_confirm"));
			super.click(GetByLocator.getLocator("OKBtn"));
		}
		driver.takeScreenForElement(driver.findElement(GetByLocator.getLocator("avatarImage")), path, "avatarImage3");
		falg = false;
		falg = !ImageUtil.compareImg("images/" + "avatarImage2.png", "images/" + "avatarImage3.png");
		return falg;
	}


	//修改用户昵称
	public boolean modifyNick() {
		super.click(GetByLocator.getLocator("rb_me"));
		super.click(GetByLocator.getLocator("meInfoBtn"));
		super.click(GetByLocator.getLocator("nickText"));
		String value = RandomUtil.getRndStrZhByLen(7);
		super.replaceValue(GetByLocator.getLocator("nickEdit"), value);
		super.click(GetByLocator.getLocator("tvRight"));
		if (value.equals(super.getMyText(GetByLocator.getLocator("nickText")))) {
			super.click(GetByLocator.getLocator("leftBtn"));
			if (value.equals(super.getMyText(GetByLocator.getLocator("meNameBtn")))) {
				return true;
			}
		}
		return false;
	}

	//自己关联自己
	public boolean relatedOneself() throws Exception {
		super.click(GetByLocator.getLocator("rb_me"));
		super.click(GetByLocator.getLocator("meInfoBtn"));
		String phone = super.getMyText(GetByLocator.getLocator("userPhone"));
		super.click(GetByLocator.getLocator("relationText"));
		Thread.sleep(1000);
		if (super.getPageSource().contains("解绑")) {
			super.click(GetByLocator.getLocator("bindText"));
			super.click(GetByLocator.getLocator("confirmBtn"));
		}
		super.click(GetByLocator.getLocator("tvRight"));
		super.sendkeys(GetByLocator.getLocator("edPhoneInput"), phone);
		String code = this.getFirstCode(phone);
		super.sendkeys(GetByLocator.getLocator("edCodeInput"), code);
		super.click(GetByLocator.getLocator("loginBtnForphone"));
		Thread.sleep(2000);
		if (super.getPageSource().contains("获取验证码")) {
			return true;
		}
		return false;
	}
	
	//账号关联，关联成功，用关联的账号登录看看显示的是不是对的。
	public boolean related() throws Exception {
		super.click(GetByLocator.getLocator("rb_me"));
		String meName = super.getMyText(GetByLocator.getLocator("meNameBtn"));
		super.click(GetByLocator.getLocator("meInfoBtn"));
		super.click(GetByLocator.getLocator("relationText"));
		Thread.sleep(1000);
		if (super.getPageSource().contains("解绑")) {
			super.click(GetByLocator.getLocator("bindText"));
			super.click(GetByLocator.getLocator("confirmBtn"));
		}
		super.click(GetByLocator.getLocator("tvRight"));
		String phone = "130" + RandomUtil.getRndNumByLen(8);
		super.sendkeys(GetByLocator.getLocator("edPhoneInput"), phone);
		String code = this.getFirstCode(phone);
		super.sendkeys(GetByLocator.getLocator("edCodeInput"), code);
		super.click(GetByLocator.getLocator("loginBtnForphone"));
		Thread.sleep(1500);
		if (super.getPageSource().contains(phone)) {
			super.click(GetByLocator.getLocator("leftBtn"));
			Thread.sleep(2000);
			super.click(GetByLocator.getLocator("leftBtn"));
			super.click(GetByLocator.getLocator("mySetting"));
			super.click(GetByLocator.getLocator("quitBtn"));
			super.click(GetByLocator.getLocator("confirmBtn"));
			super.click(GetByLocator.getLocator("meInfoBtn"));
			super.sendkeys(GetByLocator.getLocator("edPhoneInput"), phone);
			Thread.sleep(61000);//这里的等待是因为验证码获取时间间隔太多，会导致重复请求。
			code = this.getFirstCode(phone);
			super.sendkeys(GetByLocator.getLocator("edCodeInput"), code);
			super.click(GetByLocator.getLocator("loginBtnForphone"));
			super.click(GetByLocator.getLocator("rb_me"));
			if (meName.equals(super.getMyText(GetByLocator.getLocator("meNameBtn")))) {
				return true;
			}
		}
		return false;
	}
	
	//第二次获取验证码的关联账号
	public boolean sceondCodeRelated() throws InterruptedException {
		super.click(GetByLocator.getLocator("rb_me"));
		super.click(GetByLocator.getLocator("meInfoBtn"));
		super.click(GetByLocator.getLocator("relationText"));
		Thread.sleep(1000);
		if (super.getPageSource().contains("解绑")) {
			super.click(GetByLocator.getLocator("bindText"));
			super.click(GetByLocator.getLocator("confirmBtn"));
		}
		super.click(GetByLocator.getLocator("tvRight"));
		String phone = "134" + RandomUtil.getRndNumByLen(8);
		super.sendkeys(GetByLocator.getLocator("edPhoneInput"), phone);
		super.click(GetByLocator.getLocator("getCodeBtn"));
		Thread.sleep(61000);
		String imgCode = this.getPicturCode(phone);
		String code = this.getSecondCode(phone, imgCode);
		super.sendkeys(GetByLocator.getLocator("edCodeInput"), code);
		super.click(GetByLocator.getLocator("loginBtnForphone"));
		Thread.sleep(1500);
		if (super.getPageSource().contains(phone) && super.getPageSource().contains("解绑")) {
			return true;
		}
		return false;
	}
	

	//微信绑定
	public boolean weChatBind() {
		super.click(GetByLocator.getLocator("rb_me"));
		super.click(GetByLocator.getLocator("meInfoBtn"));
		super.click(GetByLocator.getLocator("weibin"));
		if ("解绑".equals(super.getMyText(GetByLocator.getLocator("weChatBinttext")))) {
			super.click(GetByLocator.getLocator("weChatBinttext"));
			super.click(GetByLocator.getLocator("confirmBtn"));
			super.click(GetByLocator.getLocator("weChatBinttext"));
			if (driver.isElementExist(GetByLocator.getLocator("weChatBinttext"),60)) {
				if (driver.isToast("当前的第三方账号")) {
					return true;
				}else {
					if ("解绑".equals(super.getMyText(GetByLocator.getLocator("weChatBinttext")))) {
						return true;
					}
				}
			}
		}else {
			super.click(GetByLocator.getLocator("weChatBinttext"));
			if (driver.isElementExist(GetByLocator.getLocator("weChatBinttext"),60)) {
				if (driver.isToast("当前的第三方账号")) {
					return true;
				}else {
					if ("解绑".equals(super.getMyText(GetByLocator.getLocator("weChatBinttext")))) {
						return true;
					}
				}
			}
		}
		return false;
	}

	//qq绑定
	public boolean QQBind() {
		super.click(GetByLocator.getLocator("rb_me"));
		super.click(GetByLocator.getLocator("meInfoBtn"));
		super.click(GetByLocator.getLocator("weibin"));
		if ("解绑".equals(super.getMyText(GetByLocator.getLocator("QQBinttext")))) {
			super.click(GetByLocator.getLocator("QQBinttext"));
			super.click(GetByLocator.getLocator("confirmBtn"));
			super.click(GetByLocator.getLocator("QQBinttext"));
			super.click(GetByLocator.getLocator("loginForQQ"));
			if (driver.isElementExist(GetByLocator.getLocator("QQBinttext"),60)) {
				if (driver.isToast("当前的第三方账号")) {
					return true;
				}else {
					if ("解绑".equals(super.getMyText(GetByLocator.getLocator("QQBinttext")))) {
						return true;
					}
				}
			}
		}else {
			super.click(GetByLocator.getLocator("QQBinttext"));
			super.click(GetByLocator.getLocator("loginForQQ"));
			if (driver.isElementExist(GetByLocator.getLocator("QQBinttext"),60)) {
				if (driver.isToast("当前的第三方账号")) {
					return true;
				}else {
					if ("解绑".equals(super.getMyText(GetByLocator.getLocator("QQBinttext")))) {
						return true;
					}
				}
			}
		}
		return false;
	}

	//关联获取第一次获取验证码
	public String getFirstCode(String phone) {
		//获取验证码
		String codeString = null;
		try {
			String data = URLEncoder.encode(AseUtil.getInstance().encrypt(phone,"utf-8","a1ba000c29c04oid","40ccdc3b24cc11e7"));
			codeString = ConnectionHttp.getPostValuseString(p.getPro("postPath")+"/toys/user/getFirstMobileLoginCheckCode?terminal=android&version=1.01&type=1&data="+data,"serNo");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return codeString;
	}

	//关联第二次获取验证码
	public String getSecondCode(String phone , String picturCode) {
		//获取验证码
		String codeString = null;
		try {
			codeString = ConnectionHttp.getPostValuseString(p.getPro("postPath")+"/toys/user/getMobileLoginCheckCode?terminal=android&version=1.01&type=1&&imgCode="+ picturCode +"&recNum="+phone,"serNo");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return codeString;
	}

	//获取图形验证码
	public String getPicturCode(String phone) {
		String urlString = null;
		try {
			urlString = ConnectionHttp.getGetAllMessage(p.getPro("postPath")+"/toys/user/toysPictureCode?terminal=android&version=1.01&recNum="+phone);
			String sKey = "a1ba000c29c04" + phone.substring(phone.length()-3,phone.length());
			urlString =AseUtil.getInstance().decrypt(urlString, "utf-8", sKey, "40ccdc3b24cc11e7");
			String code;
			JSONObject jsonObj;
			jsonObj = JSONObject.fromObject(urlString);
			code = jsonObj.get("object").toString();
			return code;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
