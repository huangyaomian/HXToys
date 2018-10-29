package cn.hx.appium.page;

import io.appium.java_client.android.AndroidElement;

import java.util.ArrayList;
import java.util.List;

import cn.hx.appium.base.AndroidDriverBase;
import cn.hx.appium.util.GetByLocator;
import cn.hx.appium.util.Log;

public class ToyStorePage extends BasePage{
	private Log logger = Log.getLogger(ToyStorePage.class);
	private final String MONEY = "0.1";

	public ToyStorePage(AndroidDriverBase driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	//共享计划筛选
	public boolean shareFilter() {
		List<AndroidElement> goodsNames;
		List<String> goodsNameTexts = new ArrayList<String>();
		AndroidElement goodsNameEl;
		super.click(GetByLocator.getLocator("toyStore"));
		super.click(GetByLocator.getLocator("shareType"));
		for (int j = 0; j < 3; j++) {
			goodsNames = super.getElementList(GetByLocator.getLocator("playGoodsName"));
			for (int i = 0; i < goodsNames.size(); i++) {
				goodsNameEl = goodsNames.get(i);
				String goodsNameText = super.getMyText(goodsNameEl);
				if (!goodsNameTexts.contains(goodsNameText)) {
					goodsNameTexts.add(goodsNameText);
					super.click(goodsNameEl);
					if (driver.isElementExist(GetByLocator.getLocator("shareIconByDetail"),3)) {
						super.click(GetByLocator.getLocator("backImage"));
					}else {
						return false;
					}
				}
			}
			driver.swipe("up", 1800);
		}
		return true;
	}

	//筛选
	public boolean filter() throws Exception {
		super.click(GetByLocator.getLocator("toyStore"));
		super.click(GetByLocator.getLocator("chooseType"));
		super.click(GetByLocator.getLocator("noShare"));
		super.click(GetByLocator.getLocator("txtConfirm"));
		if (!super.getPageSource().contains("不参与计划") && super.getPageSource().contains("本店全部")) {
			return true;
		}
		return false;
	}

	//立即购买微信
	public boolean buyByWeChat() throws Exception {
		List<AndroidElement> txtMoneys;
		super.click(GetByLocator.getLocator("toyStore"));
		super.click(GetByLocator.getLocator("shareType"));
		for (int i = 0; i < 30; i++) {
			txtMoneys = super.getElementList(GetByLocator.getLocator("txtMoney"));
			for (int j = 0; j < txtMoneys.size(); j++) {
				if (super.getMyText(txtMoneys.get(j)).contains(MONEY)) {
					super.click(txtMoneys.get(j));
					super.click(GetByLocator.getLocator("payNow"));
					super.click(GetByLocator.getLocator("shareToyBtn"));
					super.click(GetByLocator.getLocator("btnNowBuy"));
					//进入确认订单页面
					super.click(GetByLocator.getLocator("orderConfirmBtn"));
					super.click(GetByLocator.getLocator("weChatPayBtn"));
					int[] num = {9,3,0,7,2,8};
					super.inputPwdForweChat(num);
					super.click(GetByLocator.getLocator("backMerchants"));
					if (driver.isElementExist(GetByLocator.getLocator("btnOrder"), 3)) {
						return true;
					}else {
						return false;
					}
				}
			}
			driver.swipe("up", 2000);
			Thread.sleep(1500);
		}
		return false;
	}	

	//立即购买支付宝
	public boolean bugByAliPay() throws Exception {
		List<AndroidElement> txtMoneys;
		super.click(GetByLocator.getLocator("toyStore"));
		super.click(GetByLocator.getLocator("shareType"));
		for (int i = 0; i < 30; i++) {
			txtMoneys = super.getElementList(GetByLocator.getLocator("txtMoney"));
			for (int j = 0; j < txtMoneys.size(); j++) {
				if (super.getMyText(txtMoneys.get(j)).contains(MONEY)) {
					super.click(txtMoneys.get(j));
					super.click(GetByLocator.getLocator("payNow"));
					//进入确认订单页面
					super.click(GetByLocator.getLocator("buyToyBtn"));
					super.click(GetByLocator.getLocator("btnNowBuy"));
					super.click(GetByLocator.getLocator("txtPayType"));
					super.click(GetByLocator.getLocator("chooseAlipay"));
					super.click(GetByLocator.getLocator("orderConfirmBtn"));
					super.click(GetByLocator.getLocator("zhifubaoPayBtn"));
					int[] num = {9,3,0,7,2,8};
					AndroidElement allKey = driver.findElement(GetByLocator.getLocator("allKey"));
					super.inputPwds(num, allKey);
					if (driver.isElementExist(GetByLocator.getLocator("btnOrder"), 3)) {
						return true;
					}else {
						return false;
					}
				}
			}
			driver.swipe("up", 2000);
			Thread.sleep(1500);
		}
		return false;
	}

	//立即租
	public boolean rentByNow() throws Exception {
		List<AndroidElement> txtMoneys;
		AndroidElement rentBtn;
		super.click(GetByLocator.getLocator("toyStore"));
		for (int i = 0; i < 30; i++) {
			txtMoneys = super.getElementList(GetByLocator.getLocator("txtMoney"));
			for (int j = 0; j < txtMoneys.size(); j++) {
				if (super.getMyText(txtMoneys.get(j)).contains(MONEY)) {
					super.click(txtMoneys.get(j));
					rentBtn = driver.findElement(GetByLocator.getLocator("txtStandard"));
					if (rentBtn == null) {
						super.click(GetByLocator.getLocator("backImage"));
						continue;
					}
					super.click(rentBtn);
					super.click(GetByLocator.getLocator("payNow"));
					//进入确认订单页面
					super.click(GetByLocator.getLocator("orderConfirmBtn"));
					super.click(GetByLocator.getLocator("viewPassword"));
					super.sendKeysOneByOne("222222");
					if (driver.isElementExist(GetByLocator.getLocator("btnOrder"), 3)) {
						return true;
					}
				}
			}
			driver.swipe("up", 2000);
			Thread.sleep(1500);
		}
		return false;
	}

	//加入购物车后租
	public boolean rentByCar() throws Exception {
		//需要先清空购物车
		if (driver.isElementExist(GetByLocator.getLocator("redPoint"), 3)) {
			super.click(GetByLocator.getLocator("imgRight"));
			super.click(GetByLocator.getLocator("rightImage"));
			if (super.getAttribute(GetByLocator.getLocator("checkAll"), "checked").equals("false")) {
				super.click(GetByLocator.getLocator("checkAll"));
			}
			super.click(GetByLocator.getLocator("payOrDel"));
			super.click(GetByLocator.getLocator("rightImage"));
			super.click(GetByLocator.getLocator("backImage"));
		}
		List<AndroidElement> txtMoneys;
		AndroidElement rentBtn;
		super.click(GetByLocator.getLocator("toyStore"));
		for (int i = 0; i < 30; i++) {
			txtMoneys = super.getElementList(GetByLocator.getLocator("txtMoney"));
			for (int j = 0; j < txtMoneys.size(); j++) {
				super.click(txtMoneys.get(j));
				rentBtn = driver.findElement(GetByLocator.getLocator("txtStandard"));
				if (rentBtn == null) {
					super.click(GetByLocator.getLocator("backImage"));
					continue;
				}
				super.click(rentBtn);
				super.click(GetByLocator.getLocator("addCar"));
				//这里还要验证一下购物车数量。
				if (!driver.isElementExist(GetByLocator.getLocator("redPoint"), 3)) {
					super.click(GetByLocator.getLocator("backImage"));
					continue;
				}
				super.click(GetByLocator.getLocator("layShopCar"));
				if (super.getAttribute(GetByLocator.getLocator("checkAll"), "checked").equals("false")) {
					super.click(GetByLocator.getLocator("checkAll"));
				}
				super.click(GetByLocator.getLocator("payNow"));
				//进入确认订单页面
				super.click(GetByLocator.getLocator("orderConfirmBtn"));
				super.click(GetByLocator.getLocator("viewPassword"));
				super.sendKeysOneByOne("222222");
				if (driver.isElementExist(GetByLocator.getLocator("btnOrder"), 3)) {
					return true;
				}
			}
			driver.swipe("up", 2000);
			Thread.sleep(1500);
		}
		return false;
	}
}
