package cn.hx.appium.page;

import io.appium.java_client.android.AndroidElement;

import java.util.List;

import cn.hx.appium.base.AndroidDriverBase;
import cn.hx.appium.util.GetByLocator;
import cn.hx.appium.util.Log;
import cn.hx.appium.util.RandomUtil;

public class ChooseShopPage extends BasePage{
	private Log logger = Log.getLogger(ChooseShopPage.class);

	public ChooseShopPage(AndroidDriverBase driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	//切换所有玩具城中的店铺
	public boolean chooseAllSp() {
		super.click(GetByLocator.getLocator("localShop"));
		List<AndroidElement> nalShops = super.getElementList(GetByLocator.getLocator("nalShop"));
		AndroidElement nalShop;
		if (driver.isElementExist(GetByLocator.getLocator("hisRecord"), 2)) {
			int a = RandomUtil.randomInt(1, nalShops.size()) - 1 ;
			nalShop = nalShops.get(a);
		}else {
			nalShop = nalShops.get(RandomUtil.randomInt(0, nalShops.size()));
		}
		String spName = super.getMyText(nalShop);
		super.click(nalShop);
		if (spName.equals(super.getMyText(GetByLocator.getLocator("localShop")))) {
			return true;
		}
		return false;
	}
	
	//切换历史记录的店铺
	public boolean chooseHistorySp() {
		super.click(GetByLocator.getLocator("localShop"));
		List<AndroidElement> nalShops = super.getElementList(GetByLocator.getLocator("nalShop"));
		AndroidElement nalShop;
		if (driver.isElementExist(GetByLocator.getLocator("hisRecord"), 2)) {
			nalShop = nalShops.get(0);
			String spName = super.getMyText(nalShop);
			super.click(nalShop);
			if (spName.equals(super.getMyText(GetByLocator.getLocator("localShop")))) {
				return true;
			}
		}else {
			super.click(nalShops.get(RandomUtil.randomInt(0, nalShops.size())));
		}
		return false;
	}
	
}
