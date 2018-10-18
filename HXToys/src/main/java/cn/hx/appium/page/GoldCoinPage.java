package cn.hx.appium.page;

import io.appium.java_client.android.AndroidElement;

import java.util.List;

import cn.hx.appium.base.AndroidDriverBase;
import cn.hx.appium.util.GetByLocator;
import cn.hx.appium.util.Log;

public class GoldCoinPage extends BasePage{
	private Log logger = Log.getLogger(GoldCoinPage.class);

	public GoldCoinPage(AndroidDriverBase driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	//微信充值最少金币
	public boolean goldForWeChat() {
		super.click(GetByLocator.getLocator("rb_me"));
		super.click(GetByLocator.getLocator("meInfoBtn"));
		String phone = super.getMyText(GetByLocator.getLocator("userPhone"));
		super.click(GetByLocator.getLocator("leftBtn"));
		super.click(GetByLocator.getLocator("rb_homeBtn"));
		super.click(GetByLocator.getLocator("goldMoney"));
		if (phone.equals(super.getMyText(GetByLocator.getLocator("phoneForGold")))) {
			List<AndroidElement> goldNums = super.getElementList(GetByLocator.getLocator("goldNum"));
			super.click(goldNums.get(0));
			super.click(GetByLocator.getLocator("chooseWX"));
			super.click(GetByLocator.getLocator("weChatPayBtn"));
			int[] num = {9,3,0,7,2,8};
			super.inputPwdForweChat(num);
			super.click(GetByLocator.getLocator("backMerchants"));
			if (driver.isElementExist(GetByLocator.getLocator("myTicketSpeel"), 3)) {
				return true;
			}
		}
		
		return false;
	}
	
	//支付宝充值最少金币
	public boolean goldForAlipay() {
		super.click(GetByLocator.getLocator("rb_me"));
		super.click(GetByLocator.getLocator("meInfoBtn"));
		String phone = super.getMyText(GetByLocator.getLocator("userPhone"));
		super.click(GetByLocator.getLocator("leftBtn"));
		super.click(GetByLocator.getLocator("rb_homeBtn"));
		super.click(GetByLocator.getLocator("goldMoney"));
		if (phone.equals(super.getMyText(GetByLocator.getLocator("phoneForGold")))) {
			List<AndroidElement> goldNums = super.getElementList(GetByLocator.getLocator("goldNum"));
			super.click(goldNums.get(0));
			super.click(GetByLocator.getLocator("chooseAlipay"));
			super.click(GetByLocator.getLocator("zhifubaoPayBtn"));
			int[] num = {9,3,0,7,2,8};
			AndroidElement allKey = driver.findElement(GetByLocator.getLocator("allKey"));
			super.inputPwds(num, allKey);
			if (driver.isElementExist(GetByLocator.getLocator("myTicketSpeel"), 3)) {
				return true;
			}
		}
		return false;
	}
	
	//
}
