package cn.hx.appium.page;


import io.appium.java_client.android.AndroidElement;

import java.util.List;

import cn.hx.appium.base.AndroidDriverBase;
import cn.hx.appium.util.GetByLocator;
import cn.hx.appium.util.Log;
import cn.hx.appium.util.RandomUtil;

public class BabyFilePage extends BasePage{
	private Log logger = Log.getLogger(BabyFilePage.class);
	public BabyFilePage(AndroidDriverBase driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	//添加一个男性宝宝
	public boolean addBBaby() {
		super.click(GetByLocator.getLocator("rb_me"));
		super.click(GetByLocator.getLocator("babyInfo"));
		super.click(GetByLocator.getLocator("tvRight"));
		super.click(GetByLocator.getLocator("babyNick"));
		String babyNick = RandomUtil.getRndStrZhByLen(4);
		super.replaceValue(GetByLocator.getLocator("nickEdit"), babyNick);
		super.click(GetByLocator.getLocator("tvRight"));
		super.click(GetByLocator.getLocator("babyAge"));
		super.click(GetByLocator.getLocator("chooseAge"));
		super.click(GetByLocator.getLocator("babySex"));
		super.click(GetByLocator.getLocator("albumBtn"));
		if ("男".equals(super.getMyText(GetByLocator.getLocator("babySexTex")))) {
			super.click(GetByLocator.getLocator("submitBtn"));
			if (super.getPageSource().contains("添加") || super.getPageSource().contains(babyNick)) {
				return true;
			}
		}

		return false;
	}

	//添加一个nv性的宝宝
	public boolean addGBaby() {
		super.click(GetByLocator.getLocator("rb_me"));
		super.click(GetByLocator.getLocator("babyInfo"));
		super.click(GetByLocator.getLocator("tvRight"));
		super.click(GetByLocator.getLocator("babyNick"));
		String babyNick = RandomUtil.getRndStrZhByLen(4);
		super.replaceValue(GetByLocator.getLocator("nickEdit"), babyNick);
		super.click(GetByLocator.getLocator("tvRight"));
		super.click(GetByLocator.getLocator("babyAge"));
		super.click(GetByLocator.getLocator("chooseAge"));
		super.click(GetByLocator.getLocator("babySex"));
		super.click(GetByLocator.getLocator("cameraBtn"));
		if ("女".equals(super.getMyText(GetByLocator.getLocator("babySexTex")))) {
			super.click(GetByLocator.getLocator("submitBtn"));
			if (super.getPageSource().contains("添加") || super.getPageSource().contains(babyNick)) {
				return true;
			}
		}
		return false;
	}

	//修改宝宝信息，名称，性别，年龄
	public boolean modifyBaby() {
		super.click(GetByLocator.getLocator("rb_me"));
		super.click(GetByLocator.getLocator("babyInfo"));
		List<AndroidElement> babyNickForLists = super.getElementList(GetByLocator.getLocator("babyItemName"));
		int num = RandomUtil.randomInt(1, babyNickForLists.size());
		AndroidElement babyNickElement = babyNickForLists.get(num);
		String babyNickForList = super.getMyText(babyNickElement);
		super.click(babyNickElement);
		if (babyNickForList.equals(super.getMyText(GetByLocator.getLocator("nickText")))) {
			super.click(GetByLocator.getLocator("nickText"));
			String babyNick = RandomUtil.getRndStrZhByLen(4);
			//修改昵称
			super.replaceValue(GetByLocator.getLocator("nickEdit"), babyNick);
			super.click(GetByLocator.getLocator("tvRight"));
			//修改年龄
			super.click(GetByLocator.getLocator("babyAgeText"));
			driver.swipe("up", 1000);
			super.click(GetByLocator.getLocator("chooseAge"));
			//修改性别
			if ("男".equals(super.getMyText(GetByLocator.getLocator("babySexTex")))) {
				super.click(GetByLocator.getLocator("babySexTex"));
				super.click(GetByLocator.getLocator("cameraBtn"));
			}else {
				super.click(GetByLocator.getLocator("babySexTex"));
				super.click(GetByLocator.getLocator("albumBtn"));
			}
			super.click(GetByLocator.getLocator("leftBtn"));
			babyNickForLists = super.getElementList(GetByLocator.getLocator("babyItemName"));
			babyNickElement = babyNickForLists.get(num);
			if (babyNick.equals(super.getMyText(babyNickElement))) {
				return true;
			}
		}
		return false;
	}

}
