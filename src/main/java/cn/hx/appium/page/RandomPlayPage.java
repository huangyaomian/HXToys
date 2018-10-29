package cn.hx.appium.page;

import io.appium.java_client.android.AndroidElement;

import java.util.ArrayList;
import java.util.List;

import cn.hx.appium.base.AndroidDriverBase;
import cn.hx.appium.util.GetByLocator;
import cn.hx.appium.util.Log;
import cn.hx.appium.util.RandomUtil;

public class RandomPlayPage extends BasePage{
	private Log logger = Log.getLogger(RandomPlayPage.class);
	private final String MONEY = "0.1";
	public RandomPlayPage(AndroidDriverBase driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	//店内任玩前两个商品是否和列表显示一样
	public boolean compareGoods() {
		List<AndroidElement> goodsNameEls = super.getElementList(GetByLocator.getLocator("playGoodsName"));
		String goodsName1 = super.getMyText(goodsNameEls.get(0));
		String goodsName2 = super.getMyText(goodsNameEls.get(1));
		super.click(GetByLocator.getLocator("playMore"));
		goodsNameEls = super.getElementList(GetByLocator.getLocator("mGoodName"));
		if (goodsName1.equals(super.getMyText(goodsNameEls.get(0)))) {
			if (goodsName2.equals(super.getMyText(goodsNameEls.get(1)))) {
				return true;
			}
		}
		return false;
	}

	//店内任玩列表和详情买和租图标展示是否一致
	public boolean checkInfo() throws Exception {
		super.click(GetByLocator.getLocator("playMore"));
		List<AndroidElement> goodsNames;
		List<AndroidElement> rentIcons;
		List<AndroidElement> buyIcons;
		List<String> goodsNameTexts = new ArrayList<String>();
		AndroidElement goodsNameEl;
		boolean flag = true;
		boolean flag1 = false;
		while (flag) {
			flag = false;
			goodsNames = super.getElementList(GetByLocator.getLocator("mGoodName"));
			buyIcons = super.getElementList(GetByLocator.getLocator("buyIcon"));
			for (int i = 0; i < buyIcons.size(); i++) {
				goodsNameEl = goodsNames.get(i);
				String goodsNameText = super.getMyText(goodsNameEl);
				if (!goodsNameTexts.contains(goodsNameText)) {
					flag = true;
					goodsNameTexts.add(goodsNameText);
					super.click(buyIcons.get(i));
					if (driver.isElementExist(GetByLocator.getLocator("MaiIcon"),3)) {
						super.click(GetByLocator.getLocator("backImage"));
						Thread.sleep(1000);
						flag1 = true;
					}else {
						return false;
					}
					
				}
			}
			if (flag1) {
				rentIcons = super.getElementList(GetByLocator.getLocator("rentIcon"));
				for (int i = 0; i < rentIcons.size(); i++) {
					super.click(rentIcons.get(i));
					if (driver.isElementExist(GetByLocator.getLocator("rentIcon"),3)) {
						super.click(GetByLocator.getLocator("backImage"));
						Thread.sleep(500);
						flag1 = true;
					}else {
						return false;
					}
				}
			}
			driver.swipe("up", 1800);
		}
		return flag1;
	}

	//店内任玩租，加入购物车，购物车内买单
	public boolean buyByCar() throws Exception {
		super.click(GetByLocator.getLocator("playMore"));
		List<AndroidElement> rentIcons;
		for (int k = 0; k < 20; k++) {
			rentIcons = super.getElementList(GetByLocator.getLocator("rentIcon"));
			if (rentIcons != null) {
				super.click(rentIcons.get(RandomUtil.randomInt(0, rentIcons.size())));
				//先获取购物车内的数量
				super.click(GetByLocator.getLocator("addCar"));
				int count = Integer.parseInt(super.getMyText(GetByLocator.getLocator("redPoint"))); 
				int num = RandomUtil.randomInt(1, 5);
				for (int i = 0; i < num; i++) {
					super.click(GetByLocator.getLocator("addCar"));
					if (driver.isToast("成功")) {
						count++;
						Thread.sleep(500);
					}
				}
				//对比购物车总数量
				if (count == Integer.parseInt(super.getMyText(GetByLocator.getLocator("redPoint")))) {
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
				
			}
			driver.swipe("up", 2000);
		}
		return false;
	}

	//店内任玩立即购买
	public boolean buyByNow() {
		super.click(GetByLocator.getLocator("playMore"));
		List<AndroidElement> buyIcons;
		for (int i = 0; i < 30; i++) {
			buyIcons = super.getElementList(GetByLocator.getLocator("buyIcon"));
			for (int j = 0; j < buyIcons.size(); j++) {
				super.click(buyIcons.get(j));
				if (super.getMyText(GetByLocator.getLocator("txtProPrice")).contains(MONEY)) {
					super.click(GetByLocator.getLocator("payNow"));
					//进去确认订单页面了
					if (super.getPageSource().contains("共享计划")) {
						super.click(GetByLocator.getLocator("buyToyBtn"));
						super.click(GetByLocator.getLocator("txtAdd"));
						String mun = super.getMyText(GetByLocator.getLocator("txtNum"));
						super.click(GetByLocator.getLocator("btnNowBuy"));
						if (!(super.getMyText(GetByLocator.getLocator("proPriceForCar")).contains(mun))) {
							return false;
						}
					}
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
				}else {
					super.click(GetByLocator.getLocator("backImage"));
					continue;
				}
			}
		}
		
		return false;
	}
	
	
}
