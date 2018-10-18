package cn.hx.appium.page;

import io.appium.java_client.android.AndroidElement;
import cn.hx.appium.base.AndroidDriverBase;
import cn.hx.appium.util.GetByLocator;
import cn.hx.appium.util.Log;

public class ExchangeTicketPage extends BasePage{
	private Log logger = Log.getLogger(ExchangeTicketPage.class);

	public ExchangeTicketPage(AndroidDriverBase driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	//积分和金币显示的余额、
	public boolean checkNum() {
		super.click(GetByLocator.getLocator("rb_me"));
		double integral = Double.parseDouble(super.getMyText(GetByLocator.getLocator("meInte")));
		double gold = Double.parseDouble(super.getMyText(GetByLocator.getLocator("meGold")));
		super.click(GetByLocator.getLocator("rb_homeBtn"));
		super.click(GetByLocator.getLocator("layExchange"));
		if (integral == Double.parseDouble(super.getNumForString(super.getMyText(GetByLocator.getLocator("integralNum"))))) {
			if (gold == Double.parseDouble(super.getNumForString(super.getMyText(GetByLocator.getLocator("glodNum"))))) {
				return true;
			}
		}
		return false;
	}

	//积分兑换门票
	public boolean intExchange() {
		super.click(GetByLocator.getLocator("layExchange"));
		double integral = Double.parseDouble(super.getNumForString(super.getMyText(GetByLocator.getLocator("integralNum"))));
		double ticketAmount = Double.parseDouble(super.getNumForString(super.getMyText(GetByLocator.getLocator("ticketAmount"))));
		super.click(GetByLocator.getLocator("integralNum"));
		super.click(GetByLocator.getLocator("viewPassword"));
		super.sendKeysOneByOne("222222");
		if (driver.isElementExist(GetByLocator.getLocator("myTicketSpeel"), 3)) {
			if (!driver.isElementExist(GetByLocator.getLocator("seeMyTicket"), 1)) {
				super.pressAny(4);
			}
			super.click(GetByLocator.getLocator("leftBtn"));
			if (integral == Double.parseDouble(super.getNumForString(super.getMyText(GetByLocator.getLocator("integralNum")))) + ticketAmount) {
				return true;
			}
		}
		return false;
	}

	//金币兑换门票
	public boolean goldExchange() {
		super.click(GetByLocator.getLocator("layExchange"));
		double glodNum = Double.parseDouble(super.getNumForString(super.getMyText(GetByLocator.getLocator("glodNum"))));
		double ticketAmount = Double.parseDouble(super.getNumForString(super.getMyText(GetByLocator.getLocator("ticketAmount"))));
		super.click(GetByLocator.getLocator("glodNum"));
		super.click(GetByLocator.getLocator("viewPassword"));
		super.sendKeysOneByOne("222222");
		if (driver.isElementExist(GetByLocator.getLocator("myTicketSpeel"), 3)) {
			if (!driver.isElementExist(GetByLocator.getLocator("seeMyTicket"), 1)) {
				super.pressAny(4);
			}
			super.click(GetByLocator.getLocator("leftBtn"));
			if (glodNum == Double.parseDouble(super.getNumForString(super.getMyText(GetByLocator.getLocator("glodNum")))) + ticketAmount) {
				return true;
			}
		}
		return false;
	}

	//微信兑换门票
	public boolean weChatExchange() {
		super.click(GetByLocator.getLocator("layExchange"));
		super.click(GetByLocator.getLocator("payType"));
		super.click(GetByLocator.getLocator("chooseWX"));
		super.click(GetByLocator.getLocator("weChatPayBtn"));
		int[] num = {9,3,0,7,2,8};
		super.inputPwdForweChat(num);
		super.click(GetByLocator.getLocator("backMerchants"));
		if (driver.isElementExist(GetByLocator.getLocator("myTicketSpeel"), 3)) {
			return true;
		}
		return false;
	}

	//支付宝兑换门票
	public boolean alipayExchange() {
		super.click(GetByLocator.getLocator("layExchange"));
		super.click(GetByLocator.getLocator("payType"));
		super.click(GetByLocator.getLocator("chooseAlipay"));
		super.click(GetByLocator.getLocator("zhifubaoPayBtn"));
		int[] num = {9,3,0,7,2,8};
		AndroidElement allKey = driver.findElement(GetByLocator.getLocator("allKey"));
		super.inputPwds(num, allKey);
		if (driver.isElementExist(GetByLocator.getLocator("myTicketSpeel"), 3)) {
			return true;
		}
		return false;
	}
}
