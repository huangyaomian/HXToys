package cn.hx.appium.page;


import io.appium.java_client.android.AndroidElement;

import java.util.List;

import cn.hx.appium.base.AndroidDriverBase;
import cn.hx.appium.util.GetByLocator;
import cn.hx.appium.util.Log;
import cn.hx.appium.util.RandomUtil;

public class ToyRentPage extends BasePage{
	private Log logger = Log.getLogger(ToyRentPage.class);
	private final String MONEY = "0.1";

	public ToyRentPage(AndroidDriverBase driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	//分类和年龄筛选,检查商品信息
	public boolean typeAndAge() throws Exception {
		super.click(GetByLocator.getLocator("layRent"));
		List<AndroidElement> goodsNames = super.getElementList(GetByLocator.getLocator("mGoodName"));
		List<AndroidElement> rentPricess = super.getElementList(GetByLocator.getLocator("rentPrices"));
		String prices = null;
		String name =null;
		for (int i = 0; i < goodsNames.size(); i++) {
			prices = super.getMyText(rentPricess.get(i));
			name = super.getMyText(goodsNames.get(i));
			super.click(rentPricess.get(i));
			if (name.equals(super.getMyText(GetByLocator.getLocator("NameByDetail")))) {
				if (!prices.equals(super.getMyText(GetByLocator.getLocator("rentPricesByDetail")))) {
					return false;
				}
			}else {
				return false;
			}
			super.click(GetByLocator.getLocator("backImage"));
			Thread.sleep(1000);
		}
		super.click(GetByLocator.getLocator("typeAll"));
		List<AndroidElement> typeAlls = super.getElementList(GetByLocator.getLocator("tpyeName")); 
		AndroidElement type = typeAlls.get(RandomUtil.randomInt(1, typeAlls.size()));
		String tpyeName = super.getMyText(type);
		super.click(type);
		if (tpyeName.equals(super.getMyText(GetByLocator.getLocator("typeAll")))) {
			super.click(GetByLocator.getLocator("typeAge"));
			typeAlls = super.getElementList(GetByLocator.getLocator("tpyeName")); 
			type = typeAlls.get(RandomUtil.randomInt(1, typeAlls.size()));
			tpyeName = super.getMyText(type);
			super.click(type);
			if (tpyeName.equals(super.getMyText(GetByLocator.getLocator("typeAge")))) {
				return true;
			}
		}
		return false;
	}

	//列表加入购物车
	public boolean addCarRent() throws Exception {
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
		super.click(GetByLocator.getLocator("layRent"));
		super.click(GetByLocator.getLocator("imgAddCar"));//随便点几个保证购物车不空
		//先获取购物车总数量
		int num = Integer.parseInt(super.getMyText(GetByLocator.getLocator("redPoint")));
		List<AndroidElement> imgAddCars = super.getElementList(GetByLocator.getLocator("imgAddCar"));
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < imgAddCars.size(); j++) {
				for (int j2 = 0; j2 < RandomUtil.randomInt(2, 5); j2++) {
					super.click(imgAddCars.get(j));
					if (driver.isToast("成功")) {
						Thread.sleep(1500);
						num++;
					}else {
						break;
					}
				}
			}
			driver.swipe("up", 2000);
			Thread.sleep(2000);
			imgAddCars = super.getElementList(GetByLocator.getLocator("imgAddCar"));
		}
		if (num == Integer.parseInt(super.getMyText(GetByLocator.getLocator("redPoint")))) {
			return true;
		}
		return false;
	}

	//详情加入购物车
	public boolean addCarDetail() throws Exception {
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
		super.click(GetByLocator.getLocator("layRent"));
		super.click(GetByLocator.getLocator("imgAddCar"));//随便点几个保证购物车不空
		int num = Integer.parseInt(super.getMyText(GetByLocator.getLocator("redPoint")));
		int numByDetail = 0;
		List<AndroidElement> rentPricess = super.getElementList(GetByLocator.getLocator("rentPrices"));
		for (int i = 0; i < rentPricess.size(); i++) {
			super.click(rentPricess.get(i));
			numByDetail = Integer.parseInt(super.getMyText(GetByLocator.getLocator("redPoint")));
			if (num == numByDetail) {
				for (int j = 0; j < RandomUtil.randomInt(2, 5); j++) {
					super.click(GetByLocator.getLocator("addCar"));
					if (driver.isToast("成功")) {
						Thread.sleep(1500);
						numByDetail++;
						if (!(numByDetail == Integer.parseInt(super.getMyText(GetByLocator.getLocator("redPoint"))))) {
							return false;
						}
					}else {
						break;
					}
				}
				super.click(GetByLocator.getLocator("backImage"));
				String s = super.getMyText(GetByLocator.getLocator("redPoint"));
				if (s != null) {
					num = Integer.parseInt(s);
				}else {
					return false;
				}
			}else {
				return false;
			}
		}
		if (numByDetail == Integer.parseInt(super.getMyText(GetByLocator.getLocator("redPoint")))) {
			return true;
		}
		return false;
	}

	//购物车增删减、清空
	public boolean carOperate() throws Exception {
		super.click(GetByLocator.getLocator("layRent"));
		List<AndroidElement> imgAddCars = super.getElementList(GetByLocator.getLocator("imgAddCar"));
		for (int j = 0; j < imgAddCars.size(); j++) {
			for (int j2 = 0; j2 < RandomUtil.randomInt(4, 7); j2++) {
				super.click(imgAddCars.get(j));
			}
		}
		super.click(GetByLocator.getLocator("img_titleRight"));
		super.click(GetByLocator.getLocator("rightImage"));
		//验证减2之后的数值
		AndroidElement imgMinus = (AndroidElement) driver.findElements(GetByLocator.getLocator("imgMinus")).get(0);
		super.click(imgMinus);
		super.click(imgMinus);
		AndroidElement goodCount = (AndroidElement) driver.findElements(GetByLocator.getLocator("goodCount")).get(0);
		int goodCountNum = Integer.parseInt(super.getMyText(goodCount));
		super.click(GetByLocator.getLocator("rightImage"));
		AndroidElement proPriceForCar = (AndroidElement) driver.findElements(GetByLocator.getLocator("proPriceForCar")).get(0);
		int num = Integer.parseInt(super.getNumForString(super.getMyText(proPriceForCar)));
		if (goodCountNum == num) {
			//验证加3之后的数值
			super.click(GetByLocator.getLocator("rightImage"));
			AndroidElement goodAdd = (AndroidElement) driver.findElements(GetByLocator.getLocator("goodAdd")).get(0);
			super.click(goodAdd);
			super.click(goodAdd);
			super.click(goodAdd);
			goodCount = (AndroidElement) driver.findElements(GetByLocator.getLocator("goodCount")).get(0);
			goodCountNum = Integer.parseInt(super.getMyText(goodCount));
			super.click(GetByLocator.getLocator("rightImage"));
			proPriceForCar = (AndroidElement) driver.findElements(GetByLocator.getLocator("proPriceForCar")).get(0);
			num = Integer.parseInt(super.getNumForString(super.getMyText(proPriceForCar)));
			if (goodCountNum == num) {
				//验证单个删除
				super.click(GetByLocator.getLocator("rightImage"));
				AndroidElement imgDel = (AndroidElement) driver.findElements(GetByLocator.getLocator("imgDel")).get(0);
				AndroidElement proNameForCarEl = (AndroidElement) driver.findElements(GetByLocator.getLocator("proNameForCar")).get(0);
				String proNameForCar = proNameForCarEl.getText();
				super.click(imgDel);
				super.click(GetByLocator.getLocator("rightImage"));
				if (!(super.getPageSource().contains(proNameForCar))) {
					//验证全选删除
					super.click(GetByLocator.getLocator("rightImage"));
					if (super.getAttribute(GetByLocator.getLocator("checkAll"), "checked").equals("false")) {
						super.click(GetByLocator.getLocator("checkAll"));
					}
					super.click(GetByLocator.getLocator("payOrDel"));
					super.click(GetByLocator.getLocator("rightImage"));
					if ((!(driver.isElementExist(GetByLocator.getLocator("proNameForCar"), 2))) && super.getPageSource().contains("去结算")) {
						return true;
					}
				}
			}
		}
		return false;
	}

	//微信购买玩具0.01元
	public boolean payByWX() {
		super.click(GetByLocator.getLocator("layRent"));
		List<AndroidElement> rentForegifts;
		AndroidElement buyBtn;
		for (int i = 0; i < 20; i++) {
			rentForegifts = super.getElementList(GetByLocator.getLocator("rentForegift"));
			for (int j = 0; j < rentForegifts.size(); j++) {
				if (super.getMyText(rentForegifts.get(j)).contains(MONEY)) {
					super.click(rentForegifts.get(j));
					buyBtn = driver.findElement(GetByLocator.getLocator("txtStandard"));
					if (buyBtn == null) {
						super.click(GetByLocator.getLocator("backImage"));
						continue;
					}
					super.click(buyBtn);
					super.click(GetByLocator.getLocator("payNow"));
					if (super.getPageSource().contains("共享计划")) {
						super.click(GetByLocator.getLocator("shareToyBtn"));
						super.click(GetByLocator.getLocator("btnNowBuy"));
					}
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
		}
		return false;
	}

	//支付宝购买0.01元
	public boolean payByAli() {
		super.click(GetByLocator.getLocator("layRent"));
		List<AndroidElement> rentForegifts;
		AndroidElement buyBtn;
		for (int i = 0; i < 20; i++) {
			rentForegifts = super.getElementList(GetByLocator.getLocator("rentForegift"));
			for (int j = 0; j < rentForegifts.size(); j++) {
				if (super.getMyText(rentForegifts.get(j)).contains(MONEY)) {
					super.click(rentForegifts.get(j));
					buyBtn = driver.findElement(GetByLocator.getLocator("txtStandard"));
					if (buyBtn == null) {
						super.click(GetByLocator.getLocator("backImage"));
						continue;
					}
					super.click(buyBtn);
					super.click(GetByLocator.getLocator("payNow"));
					//跳到确认订单页面了
					if (super.getPageSource().contains("共享计划")) {
						super.click(GetByLocator.getLocator("buyToyBtn"));
						super.click(GetByLocator.getLocator("btnNowBuy"));
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
				}
			}
		}
		return false;
	}


	//金币租玩具（立即租）
	public boolean rentByNow() throws InterruptedException {
		super.click(GetByLocator.getLocator("layRent"));
		List<AndroidElement> rentForegifts = super.getElementList(GetByLocator.getLocator("rentForegift"));
		int num  = RandomUtil.randomInt(0, rentForegifts.size());
		AndroidElement rentElement = rentForegifts.get(num);
		String text = super.getMyText(rentElement).split("：")[1];
		super.click(rentElement);
		super.click(GetByLocator.getLocator("payNow"));
		Thread.sleep(1000);
		if (!driver.getPageSouce().contains("确认支付")) {
			super.click(GetByLocator.getLocator("backImage"));
			driver.swipe("up", 1000);
			Thread.sleep(1500);
			rentForegifts = super.getElementList(GetByLocator.getLocator("rentForegift"));
			rentElement = rentForegifts.get(num);
			text = super.getMyText(rentElement).split("：")[1];
			super.click(rentElement);
			super.click(GetByLocator.getLocator("payNow"));
		}
		//跳到确认订单页面了
		if (super.getMyText(GetByLocator.getLocator("orderConst")).contains(text)) {
			super.click(GetByLocator.getLocator("orderConfirmBtn"));
			super.click(GetByLocator.getLocator("viewPassword"));
			super.sendKeysOneByOne("222222");
			if (driver.isElementExist(GetByLocator.getLocator("btnOrder"), 3)) {
				return true;
			}
		}
		return false;
	}

	//购物车中租
	public boolean rentByCar() throws Exception {
		//先清空一下购物车
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
		super.click(GetByLocator.getLocator("layRent"));
		List<AndroidElement> imgAddCars = super.getElementList(GetByLocator.getLocator("imgAddCar"));
		for (int j = 0; j < imgAddCars.size(); j++) {
			for (int j2 = 0; j2 < RandomUtil.randomInt(2, 5); j2++) {
				super.click(imgAddCars.get(j));
			}
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

}
