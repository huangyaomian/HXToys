package cn.hx.appium.page;

import io.appium.java_client.android.AndroidElement;

import java.util.List;

import cn.hx.appium.base.AndroidDriverBase;
import cn.hx.appium.util.GetByLocator;
import cn.hx.appium.util.Log;
import cn.hx.appium.util.RandomUtil;

/**
 *我的页面
 * 
 */
public class MePage extends BasePage{
	private Log logger = Log.getLogger(MePage.class);

	public MePage(AndroidDriverBase driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	//验证金币显示的数量正确，和点击充值的页面跳转是否正确
	public boolean checkGold() throws Exception {
		super.click(GetByLocator.getLocator("rb_me"));
		String meGold = super.getMyText(GetByLocator.getLocator("meGold"));
		super.click(GetByLocator.getLocator("meGold"));
		String textGold = super.getMyText(GetByLocator.getLocator("textIntegral"));
		List<AndroidElement> titleElements;
		List<AndroidElement> numGolds;
		if (meGold.equals(textGold)) {
			titleElements = super.getElementList(GetByLocator.getLocator("titleText"));
			numGolds = super.getElementList(GetByLocator.getLocator("pointAllTextlist"));
			for (int i = 0; i < titleElements.size(); i++) {
				if (!"押金扣费".equals(super.getMyText(titleElements.get(i)))) {
					if (meGold.equals(super.getMyText(numGolds.get(i)).split("：")[1])) {
						super.click(GetByLocator.getLocator("ntegralExchang"));
						Thread.sleep(1500);
						if (super.getPageSource().contains("避免不必要的麻烦")) {
							return true;
						}else {
							return false;
						}
					}
				}
			}
		}
		return false;
	}

	//验证积分显示的数量正确，和点击兑换的页面跳转是否正确
	public boolean checkIntegral() throws Exception {
		super.click(GetByLocator.getLocator("rb_me"));
		String meGold = super.getMyText(GetByLocator.getLocator("meInte"));
		super.click(GetByLocator.getLocator("meInte"));
		String textInte = super.getMyText(GetByLocator.getLocator("textIntegral"));
		if (meGold.equals(textInte)) {
			//因为这个地方只需要验证列表的第一个值所以直接取
			if (meGold.equals(super.getMyText(GetByLocator.getLocator("pointAllTextlist")).split("：")[1])) {
				super.click(GetByLocator.getLocator("ntegralExchang"));
				Thread.sleep(1500);
				if (super.getPageSource().contains("请选择支付方式")) {
					return true;
				}else {
					return false;
				}
			}
		}
		return false;
	}
	
	
	//意见反馈
	public boolean feedback() {
		super.click(GetByLocator.getLocator("rb_me"));
		super.click(GetByLocator.getLocator("mySetting"));
		super.click(GetByLocator.getLocator("opinionFeedback"));
		super.replaceValue(GetByLocator.getLocator("feedbackEdit"), RandomUtil.getRndStrZhByLen(30));
		super.click(GetByLocator.getLocator("submitBtn"));
		if (driver.isToast("成功")) {
			return true;
		}
		return false;
	}

}


