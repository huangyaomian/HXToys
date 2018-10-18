package cn.hx.appium.page;

import java.net.URLEncoder;

import net.sf.json.JSONObject;

import cn.hx.appium.base.AndroidDriverBase;
import cn.hx.appium.base.CrazyPath;
import cn.hx.appium.util.AseUtil;
import cn.hx.appium.util.ConnectionHttp;
import cn.hx.appium.util.GetByLocator;
import cn.hx.appium.util.Log;
import cn.hx.appium.util.ProUtil;
import cn.hx.appium.util.RandomUtil;

public class LoginRegisterPage extends BasePage{
	private Log logger = Log.getLogger(LoginRegisterPage.class);
	private ProUtil  p = new ProUtil(CrazyPath.elementPath);

	public LoginRegisterPage(AndroidDriverBase driver) {
		super(driver);
	}

	//微信首次获取验证码绑定号码注册登录
	public boolean weChatFrist() {
		this.swipPage();
		super.click(GetByLocator.getLocator("chooseSp"));
		super.click(GetByLocator.getLocator("rb_orderBtn"));
		super.click(GetByLocator.getLocator("allowBtn"));
		super.click(GetByLocator.getLocator("weChatLoginBtn"));
		if (!this.isBindWechat()) {
			return false;
		}
		String phone = "131" + RandomUtil.getRndNumByLen(8);
		super.sendkeys(GetByLocator.getLocator("edPhoneInput"), phone);
		String code = this.getCode(phone);
		super.sendkeys(GetByLocator.getLocator("edCodeInput"), code);
		super.click(GetByLocator.getLocator("loginBtnForphone"));
		super.click(GetByLocator.getLocator("chooseSp"));
		return driver.isElementExist(GetByLocator.getLocator("localShop"), 3);
	}

	//微信二次获取验证码绑定号码登录
	public boolean weChatSecond() throws Exception {
		this.swipPage();
		super.click(GetByLocator.getLocator("chooseSp"));
		super.click(GetByLocator.getLocator("rb_orderBtn"));
		super.click(GetByLocator.getLocator("allowBtn"));
		super.click(GetByLocator.getLocator("weChatLoginBtn"));
		if (!this.isBindWechat()) {
			return false;
		}
		String phone = "131" + RandomUtil.getRndNumByLen(8);
		super.sendkeys(GetByLocator.getLocator("edPhoneInput"), phone);
		super.click(GetByLocator.getLocator("getCodeBtn"));
		Thread.sleep(61000);
		String imgCode = this.getPicturCode(phone);
		String code = this.getCode(phone, imgCode);
		super.sendkeys(GetByLocator.getLocator("edCodeInput"), code);
		super.click(GetByLocator.getLocator("loginBtnForphone"));
		super.click(GetByLocator.getLocator("chooseSp"));
		return driver.isElementExist(GetByLocator.getLocator("localShop"), 3);
	}

	//微信登录已注册号码
	public boolean weChatLogin() {
		this.swipPage();
		super.click(GetByLocator.getLocator("chooseSp"));
		super.click(GetByLocator.getLocator("rb_orderBtn"));
		super.click(GetByLocator.getLocator("allowBtn"));
		super.click(GetByLocator.getLocator("weChatLoginBtn"));
		if (driver.isElementExist(GetByLocator.getLocator("localShop"), 3)) {
			return true;
		}else {
			String phone = "135105" + RandomUtil.getRndNumByLen(5);
			super.sendkeys(GetByLocator.getLocator("edPhoneInput"), phone);
			String code = this.getCode(phone);
			super.sendkeys(GetByLocator.getLocator("edCodeInput"), code);
			super.click(GetByLocator.getLocator("loginBtnForphone"));
			super.click(GetByLocator.getLocator("chooseSp"));
			return driver.isElementExist(GetByLocator.getLocator("localShop"), 3);
		}
	}

	//QQ首次获取验证码绑定号码注册登录
	public boolean qqFrist() {
		this.swipPage();
		super.click(GetByLocator.getLocator("chooseSp"));
		super.click(GetByLocator.getLocator("rb_orderBtn"));
		super.click(GetByLocator.getLocator("allowBtn"));
		super.click(GetByLocator.getLocator("QQLoginBtn"));
		super.click(GetByLocator.getLocator("loginForQQ"));
		if (!this.isBindQQ()) {
			return false;
		}
		String phone = "131" + RandomUtil.getRndNumByLen(8);
		super.sendkeys(GetByLocator.getLocator("edPhoneInput"), phone);
		String code = this.getCode(phone);
		super.sendkeys(GetByLocator.getLocator("edCodeInput"), code);
		super.click(GetByLocator.getLocator("loginBtnForphone"));
		super.click(GetByLocator.getLocator("chooseSp"));
		return driver.isElementExist(GetByLocator.getLocator("localShop"), 3);
	}

	//QQ二次获取验证码绑定号码登录
	public boolean qqSecond() throws Exception {
		this.swipPage();
		super.click(GetByLocator.getLocator("chooseSp"));
		super.click(GetByLocator.getLocator("rb_orderBtn"));
		super.click(GetByLocator.getLocator("allowBtn"));
		super.click(GetByLocator.getLocator("QQLoginBtn"));
		super.click(GetByLocator.getLocator("loginForQQ"));
		if (!this.isBindQQ()) {
			return false;
		}
		String phone = "131" + RandomUtil.getRndNumByLen(8);
		super.sendkeys(GetByLocator.getLocator("edPhoneInput"), phone);
		super.click(GetByLocator.getLocator("getCodeBtn"));
		Thread.sleep(61000);
		String imgCode = this.getPicturCode(phone);
		String code = this.getCode(phone, imgCode);
		super.sendkeys(GetByLocator.getLocator("edCodeInput"), code);
		super.click(GetByLocator.getLocator("loginBtnForphone"));
		super.click(GetByLocator.getLocator("chooseSp"));
		return driver.isElementExist(GetByLocator.getLocator("localShop"), 3);
	}

	//QQ登录已注册号码
	public boolean qqLogin() {
		this.swipPage();
		super.click(GetByLocator.getLocator("chooseSp"));
		super.click(GetByLocator.getLocator("rb_orderBtn"));
		super.click(GetByLocator.getLocator("allowBtn"));
		super.click(GetByLocator.getLocator("QQLoginBtn"));
		super.click(GetByLocator.getLocator("loginForQQ"));
		if (driver.isElementExist(GetByLocator.getLocator("localShop"), 3)) {
			return true;
		}else {
			if (super.getPageSource().contains("绑定")) {
				String phone = "135105" + RandomUtil.getRndNumByLen(5);
				super.sendkeys(GetByLocator.getLocator("edPhoneInput"), phone);
				String code = this.getCode(phone);
				super.sendkeys(GetByLocator.getLocator("edCodeInput"), code);
				super.click(GetByLocator.getLocator("loginBtnForphone"));
				super.click(GetByLocator.getLocator("chooseSp"));
			}
			return driver.isElementExist(GetByLocator.getLocator("localShop"), 3);
		}
	}

	//手机号码首次获取验证码绑定号码注册登录
	public boolean phoneFrist() {
		this.swipPage();
		super.click(GetByLocator.getLocator("chooseSp"));
		super.click(GetByLocator.getLocator("rb_orderBtn"));
		super.click(GetByLocator.getLocator("allowBtn"));
		String phone = "135105" + RandomUtil.getRndNumByLen(5);
		super.sendkeys(GetByLocator.getLocator("edPhoneInput"), phone);
		String code = this.getCode(phone);
		super.sendkeys(GetByLocator.getLocator("edCodeInput"), code);
		super.click(GetByLocator.getLocator("loginBtnForphone"));
		super.click(GetByLocator.getLocator("chooseSp"));
		return driver.isElementExist(GetByLocator.getLocator("localShop"), 3);
	}

	//手机号码二次获取验证码绑定号码登录
	public boolean phoneSecond() throws Exception {
		this.swipPage();
		super.click(GetByLocator.getLocator("chooseSp"));
		super.click(GetByLocator.getLocator("rb_orderBtn"));
		super.click(GetByLocator.getLocator("allowBtn"));
		String phone = "135" + RandomUtil.getRndNumByLen(8);
		super.sendkeys(GetByLocator.getLocator("edPhoneInput"), phone);
		super.click(GetByLocator.getLocator("getCodeBtn"));
		Thread.sleep(61000);
		String imgCode = this.getPicturCode(phone);
		String code = this.getCode(phone, imgCode);
		super.sendkeys(GetByLocator.getLocator("edCodeInput"), code);
		super.click(GetByLocator.getLocator("loginBtnForphone"));
		super.click(GetByLocator.getLocator("chooseSp"));
		return driver.isElementExist(GetByLocator.getLocator("localShop"), 3);
	}

	//手机号码登录已注册号码,就登录13510526236的账号
	public boolean phoneLogin(String phone) {
		this.swipPage();
		super.click(GetByLocator.getLocator("chooseSp"));
		super.click(GetByLocator.getLocator("rb_orderBtn"));
		super.click(GetByLocator.getLocator("allowBtn"));
		super.sendkeys(GetByLocator.getLocator("edPhoneInput"), phone);
		String code = this.getCode(phone);
		super.sendkeys(GetByLocator.getLocator("edCodeInput"), code);
		super.click(GetByLocator.getLocator("loginBtnForphone"));	
		super.click(GetByLocator.getLocator("rb_homeBtn"));	
		return driver.isElementExist(GetByLocator.getLocator("localShop"), 3);
	}


	//输入手机号码获取验证码
	public String getCode(String phone) {
		//获取验证码
		String code = null;
		try {
			String data = URLEncoder.encode(AseUtil.getInstance().encrypt(phone,"utf-8","a1ba000c29c04oid","40ccdc3b24cc11e7"));
			code = ConnectionHttp.getPostValuseString(p.getPro("postPath")+"/toys/user/getFirstMobileLoginCheckCode?terminal=android&version=1.01&data="+data,"serNo");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return code;
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

	//	获取图形验证码后再获取验证码，即第二次以上获取验证码
	public String getCode(String phone , String picturCode) {
		//获取验证码
		String codeString = null;
		try {
			codeString = ConnectionHttp.getPostValuseString(p.getPro("postPath")+"/toys/user/getMobileLoginCheckCode?terminal=android&version=1.01&imgCode="+ picturCode +"&recNum="+phone,"serNo");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return codeString;
	}

	//判断是否已经绑定微信,如果已经绑定了就解绑
	public boolean isBindWechat() {
		if (driver.isElementExist(GetByLocator.getLocator("rb_meBtn"), 3)) {
			super.click(GetByLocator.getLocator("rb_meBtn"));
			super.click(GetByLocator.getLocator("meNameBtn"));
			super.click(GetByLocator.getLocator("weibin"));
			super.click(GetByLocator.getLocator("weChatBinttext"));
			super.click(GetByLocator.getLocator("confirmBtn"));
			super.click(GetByLocator.getLocator("backImage"));
			super.click(GetByLocator.getLocator("leftBtn"));
			super.click(GetByLocator.getLocator("mySetting"));
			super.click(GetByLocator.getLocator("quitBtn"));
			super.click(GetByLocator.getLocator("confirmBtn"));
			super.click(GetByLocator.getLocator("rb_orderBtn"));
			super.click(GetByLocator.getLocator("weChatLoginBtn"));
			return true;
		}else {
			if (driver.getPageSouce().contains("绑定手机")) {
				return true;
			}else {
				return false;
			}
		}
	}

	//判断是否已经绑定qq,如果绑定就解绑掉
	public boolean isBindQQ() {
		if (driver.isElementExist(GetByLocator.getLocator("rb_meBtn"), 3)) {
			super.click(GetByLocator.getLocator("rb_meBtn"));
			super.click(GetByLocator.getLocator("meNameBtn"));
			super.click(GetByLocator.getLocator("weibin"));
			super.click(GetByLocator.getLocator("QQBinttext"));
			super.click(GetByLocator.getLocator("confirmBtn"));
			super.click(GetByLocator.getLocator("backImage"));
			super.click(GetByLocator.getLocator("leftBtn"));
			super.click(GetByLocator.getLocator("mySetting"));
			super.click(GetByLocator.getLocator("quitBtn"));
			super.click(GetByLocator.getLocator("confirmBtn"));
			super.click(GetByLocator.getLocator("rb_orderBtn"));
			super.click(GetByLocator.getLocator("QQLoginBtn"));
			super.click(GetByLocator.getLocator("loginForQQ"));
			return true;
		}else {
			if (driver.getPageSouce().contains("绑定手机")) {
				return true;
			}else {
				return false;
			}
		}
	}

	//	滑动引导页并点击立即体验
	public void swipPage() {
		super.click(GetByLocator.getLocator("allowBtn"));
		super.click(GetByLocator.getLocator("allowBtn"));
		//		driver.swipeUntilElementAppear(GetByLocator.getLocator("immediatelyStartBtn"), "left", 200, 5);
		//		super.click(GetByLocator.getLocator("immediatelyStartBtn"));
	}

}
