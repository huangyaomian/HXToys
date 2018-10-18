package cn.hx.appium.page;

import io.appium.java_client.android.AndroidElement;

import java.util.ArrayList;
import java.util.List;

import cn.hx.appium.base.AndroidDriverBase;
import cn.hx.appium.util.GetByLocator;
import cn.hx.appium.util.Log;
import cn.hx.appium.util.RandomUtil;

public class ParentSpecialPage extends BasePage{
	private Log logger = Log.getLogger(ParentSpecialPage.class);

	public ParentSpecialPage(AndroidDriverBase driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	//加入购物车以及操作购物车
	public boolean addCar() throws Exception {
		List<AndroidElement> addImgs;
		List<AndroidElement> addImgs1;
		//需要先清空购物车
		this.clearCar();
		Thread.sleep(1000);
		driver.swipe("up", 4500);
		Thread.sleep(500);
		super.click(GetByLocator.getLocator("goodAdd"));  //点一下保证购物车有东西
		String carNum = super.getMyText(GetByLocator.getLocator("redPoint"));
		super.click(GetByLocator.getLocator("layPMall"));
		Thread.sleep(1500);
		addImgs1 = super.getElementList(GetByLocator.getLocator("goodAdd"));
		for (int i = 0; i < addImgs1.size(); i++) {
			for (int j = 0; j < RandomUtil.randomInt(2, 5); j++) {
				super.click(addImgs1.get(i));
				addImgs1 = super.getElementList(GetByLocator.getLocator("goodAdd"));
			}
		}
		if (!carNum.equals(super.getMyText(GetByLocator.getLocator("redPoint")))) {
			carNum = super.getMyText(GetByLocator.getLocator("redPoint"));
			super.click(GetByLocator.getLocator("backImage"));
			if (carNum.equals(super.getMyText(GetByLocator.getLocator("redPoint"))) ) {
				addImgs = super.getElementList(GetByLocator.getLocator("goodAdd"));
				for (int i = 0; i < addImgs.size(); i++) {
					for (int j = 0; j < RandomUtil.randomInt(2, 5); j++) {
						super.click(addImgs.get(i));
						addImgs = super.getElementList(GetByLocator.getLocator("goodAdd"));
					}
				}
				if (!carNum.equals(super.getMyText(GetByLocator.getLocator("redPoint"))) ) {
					carNum = super.getMyText(GetByLocator.getLocator("redPoint"));
					super.click(GetByLocator.getLocator("layPMall"));
					Thread.sleep(1500);
					if (carNum.equals(super.getMyText(GetByLocator.getLocator("redPoint")))) {
						return true;
					}
				}
			}
		}
		return false;
	}

	//列表和详情信息是否一致
	public boolean checkInfo() throws Exception {
		Thread.sleep(1500);
		driver.swipe("up", 1800);
		List<AndroidElement> goodsNames;
		List<AndroidElement> goodRules;
		List<AndroidElement> goodUnitPrices;
		List<AndroidElement> goodNoPrices;
		List<String> goodsNameTexts = new ArrayList<String>();
		AndroidElement goodsNameEl;
		boolean flag = true;
		while (flag) {
			flag = false;
			goodsNames = super.getElementList(GetByLocator.getLocator("goodName"));
			for (int i = 0; i < goodsNames.size(); i++) {
				for (int j = 0; j < goodsNames.size(); j++) {
					System.out.println(super.getMyText(goodsNames.get(j)));
				}
				goodsNameEl = goodsNames.get(i);
				String goodsNameText = super.getMyText(goodsNameEl);
				if (!goodsNameTexts.contains(goodsNameText)) {
					flag = true;
					goodsNameTexts.add(goodsNameText);
					super.click(goodsNameEl);
					if (goodsNameText.equals(super.getMyText(GetByLocator.getLocator("txtProName")))) {
						super.click(GetByLocator.getLocator("backImage"));
						Thread.sleep(1000);
						goodsNames = super.getElementList(GetByLocator.getLocator("goodName"));
					}else {
						return false;
					}

				}
			}
			if (flag) {
				goodRules = super.getElementList(GetByLocator.getLocator("goodRule"));
				for (int i = 0; i < goodRules.size(); i++) {
					String rule = super.getMyText(goodRules.get(i));
					if (!rule.isEmpty()) {
						super.click(goodRules.get(i));
						if (rule.equals(super.getMyText(GetByLocator.getLocator("txtProDetail")))) {
							super.click(GetByLocator.getLocator("backImage"));
							Thread.sleep(1000);
							goodRules = super.getElementList(GetByLocator.getLocator("goodRule"));
						}else {
							return false;
						}
					}
				}
				goodUnitPrices = super.getElementList(GetByLocator.getLocator("goodUnitPrice"));
				for (int i = 0; i < goodUnitPrices.size(); i++) {
					String unitPrices = super.getMyText(goodUnitPrices.get(i));
					super.click(goodUnitPrices.get(i));
					if (unitPrices.equals(super.getMyText(GetByLocator.getLocator("txtProPrice")))) {
						super.click(GetByLocator.getLocator("backImage"));
						Thread.sleep(1000);
						goodUnitPrices = super.getElementList(GetByLocator.getLocator("goodUnitPrice"));
					}else {
						return false;
					}
				}
				if (driver.isElementExist(GetByLocator.getLocator("goodNoPrice"), 1)) {
					goodNoPrices = super.getElementList(GetByLocator.getLocator("goodNoPrice"));
					for (int i = 0; i < goodNoPrices.size(); i++) {
						String noPrice = super.getMyText(goodNoPrices.get(i));
						super.click(goodNoPrices.get(i));
						if (noPrice.equals(super.getMyText(GetByLocator.getLocator("goodNoPrice")))) {
							super.click(GetByLocator.getLocator("backImage"));
							Thread.sleep(1000);
							goodNoPrices = super.getElementList(GetByLocator.getLocator("goodNoPrice"));
						}else {
							return false;
						}
					}
				}
			}
			driver.swipe("up", 2000);
			Thread.sleep(2000);
		}
		if (super.getPageSource().contains("真的没有啦")) {
			return true;
		}else {
			//这里是怕没有滑动到底部，再补滑一下，才能出现真的没有啦！
			driver.swipe("up", 1000);
			if (super.getPageSource().contains("真的没有啦")) {
				return true;
			}
		}
		return false;
	}

	//详情购买
	public boolean buyByDetail() throws Exception {
		Thread.sleep(1500);
		List<AndroidElement> goodUnitPrices;
		AndroidElement goodUnitPrice;
		while (true) {
			driver.swipe("up", 4500);
			Thread.sleep(500);
			goodUnitPrices = super.getElementList(GetByLocator.getLocator("goodUnitPrice"));
			if (goodUnitPrices.size() >= 0) {
				goodUnitPrice = goodUnitPrices.get(RandomUtil.randomInt(0, goodUnitPrices.size()));
				String unitPrices = super.getMyText(goodUnitPrice);
				super.click(goodUnitPrice);
				super.click(GetByLocator.getLocator("payNow"));
				//购买的流程
				if (super.getMyText(GetByLocator.getLocator("orderConst")).contains(unitPrices)) {
					super.click(GetByLocator.getLocator("orderConfirmBtn"));
					super.click(GetByLocator.getLocator("viewPassword"));
					super.sendKeysOneByOne("222222");
					if (driver.isElementExist(GetByLocator.getLocator("btnOrder"), 3)) {
						return true;
					}
				}
			}
		}
	}

	//购物车购买
	public boolean buyByCar() throws InterruptedException {
		//需要先清空购物车
		this.clearCar();
		List<AndroidElement> addImgs = null;
		Thread.sleep(1000);
		driver.swipe("up", 4500);
		Thread.sleep(1000);
		addImgs = super.getElementList(GetByLocator.getLocator("goodAdd"));
		for (int j = 0; j < RandomUtil.randomInt(2, 5); j++) {
			super.click(addImgs.get(RandomUtil.randomInt(0, addImgs.size())));
			addImgs = super.getElementList(GetByLocator.getLocator("goodAdd"));
		}
		super.click(GetByLocator.getLocator("layPMall"));
		if (driver.isElementExist(GetByLocator.getLocator("goodAdd"), 3)) {
			addImgs = super.getElementList(GetByLocator.getLocator("goodAdd"));
			for (int j = 0; j < RandomUtil.randomInt(2, 5); j++) {
				super.click(addImgs.get(RandomUtil.randomInt(0, addImgs.size())));
				addImgs = super.getElementList(GetByLocator.getLocator("goodAdd"));
			}
		}else {
			return false;
		}
		super.click(GetByLocator.getLocator("img_titleRight"));
		if (super.getAttribute(GetByLocator.getLocator("checkAll"), "checked").equals("false")) {
			super.click(GetByLocator.getLocator("checkAll"));
		}
		String totalCold = super.getMyText(GetByLocator.getLocator("totalCold")).split("：")[1];
		super.click(GetByLocator.getLocator("payNow"));
		if (totalCold.equals(super.getMyText(GetByLocator.getLocator("orderConst")).split("：")[1])) {
			super.click(GetByLocator.getLocator("orderConfirmBtn"));
			super.click(GetByLocator.getLocator("viewPassword"));
			super.sendKeysOneByOne("222222");
			if (driver.isElementExist(GetByLocator.getLocator("btnOrder"), 3)) {
				return true;
			}
		}
		return false;
	}

	//首页的详情加入购物车后购物车数量是否更新到列表
	public boolean updataNumByHome() throws Exception {
		this.clearCar();
		Thread.sleep(1000);
		for (int i = 0; i < RandomUtil.randomInt(5, 8); i++) {
			driver.swipe("up", 2000);
			Thread.sleep(1000);
		}
		for (int i = 0; i < RandomUtil.randomInt(5, 10); i++) {
			super.click(GetByLocator.getLocator("goodAdd"));
		}
		int num = Integer.parseInt(super.getMyText(GetByLocator.getLocator("goodCount")));
		super.click(GetByLocator.getLocator("goodCount"));
		for (int i = 0; i < RandomUtil.randomInt(3, 10); i++) {
			super.click(GetByLocator.getLocator("addCar"));
			if (driver.isToast("成功")) {
				num++;
				Thread.sleep(1000);
			}
		}
		super.click(GetByLocator.getLocator("backImage"));
		if (driver.isElementExist(GetByLocator.getLocator("goodCount"), 1)) {
			if (num == Integer.parseInt(super.getMyText(GetByLocator.getLocator("goodCount")))) {
				if (num == Integer.parseInt(super.getMyText(GetByLocator.getLocator("redPoint")))) {
					return true;
				}
			}else {
				return false;
			}
		}
		return false;
	}

	//家长专区列表详情加入购物车后购物车数量更新到列表
	public boolean updataNumByList() throws Exception {
		this.clearCar();
		Thread.sleep(1000);
		driver.swipe("up", 4500);
		Thread.sleep(1000);
		for (int i = 0; i < RandomUtil.randomInt(5, 10); i++) {
			super.click(GetByLocator.getLocator("goodAdd"));
		}
		int num = Integer.parseInt(super.getMyText(GetByLocator.getLocator("goodCount")));
		super.click(GetByLocator.getLocator("layPMall"));
		for (int i = 0; i < RandomUtil.randomInt(5, 10); i++) {
			super.click(GetByLocator.getLocator("goodAdd"));
			if (driver.isToast("成功")) {
				num++;
			}
		}
		super.click(GetByLocator.getLocator("backImage"));
		if (driver.isElementExist(GetByLocator.getLocator("goodCount"), 1)) {
			if (num == Integer.parseInt(super.getMyText(GetByLocator.getLocator("goodCount")))) {
				if (num == Integer.parseInt(super.getMyText(GetByLocator.getLocator("redPoint")))) {
					return true;
				}
			}else {
				return false;
			}
		}
		return false;
	}
	
	//家长专区列表详情加入购物车后购物车数量更新到列表
		public boolean updataNum() throws Exception {
			this.clearCar();
			Thread.sleep(1000);
			driver.swipe("up", 4500);
			Thread.sleep(1000);
			super.click(GetByLocator.getLocator("layPMall"));
			for (int i = 0; i < RandomUtil.randomInt(4, 7); i++) {
				driver.swipe("up", 2000);
				Thread.sleep(1000);
			}
			for (int i = 0; i < RandomUtil.randomInt(5, 10); i++) {
				super.click(GetByLocator.getLocator("goodAdd"));
			}
			int num = Integer.parseInt(super.getMyText(GetByLocator.getLocator("goodCount")));
			super.click(GetByLocator.getLocator("goodCount"));
			for (int i = 0; i < RandomUtil.randomInt(3, 10); i++) {
				super.click(GetByLocator.getLocator("addCar"));
				if (driver.isToast("成功")) {
					num++;
					Thread.sleep(1000);
				}
			}
			super.click(GetByLocator.getLocator("backImage"));
			if (driver.isElementExist(GetByLocator.getLocator("goodCount"), 1)) {
				if (num == Integer.parseInt(super.getMyText(GetByLocator.getLocator("goodCount")))) {
					if (num == Integer.parseInt(super.getMyText(GetByLocator.getLocator("redPoint")))) {
						return true;
					}
				}else {
					return false;
				}
			}
			return false;
		}

//	清空购物车
	public void clearCar() {
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
	}
}
