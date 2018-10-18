package cn.hx.appium.page;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;

import java.text.DecimalFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.RemoteWebElement;

import cn.hx.appium.base.AndroidDriverBase;
import cn.hx.appium.util.Log;

public class BasePage extends RemoteWebElement {
	private Log logger = Log.getLogger(BasePage.class);
	public String curActivity;
	public String pageSource;
	public AndroidDriverBase driver;

	public BasePage(AndroidDriverBase driver) {
		this.driver = driver;
		this.curActivity = getCurActivity();
		this.pageSource = getPageSource();
	}

	// 获取当前activity
	public String getCurActivity() {
		return driver.currentActivity();
	}

	public String getPageSource() {
		return driver.getPageSouce();
	}


	/**
	 * 
	 * @param string
	 * @return 返回文本
	 */
	public String getMyText(By by){
		try {
			return driver.findElement(by).getText();
		} catch (Exception e) {
			System.out.println("获取不到文本");
			logger.info("获取不到文本");
			return null;
		}
	}

	/**
	 * 
	 * @param string
	 * @return 返回文本
	 */
	public String getMyText(AndroidElement el){
		try {
			return el.getText();
		} catch (Exception e) {
			System.out.println("获取不到文本");
			logger.info("获取不到文本");
			return null;
		}
	}


	// 输入
	public void sendkeys(AndroidElement element, String value) {
		if (element != null && value != null) {
			element.sendKeys(value);
		} else {
			System.out.println("输入失败，元素没有定位到，是null 或者 value为空不输入");
			logger.info("输入失败，元素没有定位到，是null 或者 value为空不输入");
		}
	}

	// 输入数字英文字母
	public void setValue(By by, String value) {
		AndroidElement element = driver.findElement(by);
		try {
			setValue(element, value);
		} catch (Exception e) {
			System.out.println("出现异常了");
			// TODO: handle exception
		}

	}

	public void setValue(AndroidElement element, String value) {
		if (element != null && value!= null) {
			element.setValue(value);
		} else {
			System.out.println("点击失败，元素没有定位到，是null");
			logger.info("点击失败，元素没有定位到，是null");
		}
	}


	// 输入中文
	public void replaceValue(By by, String value) {
		AndroidElement element = driver.findElement(by);
		try {
			replaceValue(element, value);
		} catch (Exception e) {
			System.out.println("出现异常了");
			// TODO: handle exception
		}

	}

	public void replaceValue(AndroidElement element, String value) {
		if (element != null && value!= null) {
			element.replaceValue(value);
		} else {
			System.out.println("点击失败，元素没有定位到，是null");
			logger.info("点击失败，元素没有定位到，是null");
		}
	}

	// 直接定位并输入
	public void sendkeys(By by, String value) {
		AndroidElement element = driver.findElement(by);
		sendkeys(element, value);
	}

	// 点击
	public void click(AndroidElement element) {
		if (element != null) {
			element.click();
		} else {
			System.out.println("点击失败，元素没有定位到，是null");
			logger.info("点击失败，元素没有定位到，是null");
		}
	}

	// 定位并点击
	public void click(By by) {
		AndroidElement element = driver.findElement(by);
		if (element!=null) {
			click(element);
		}

	}

	// 清除
	public void clear(AndroidElement element) {
		if (element != null) {
			element.clear();
		} else {
			System.out.println("清除失败，元素没有定位到，是null");
			logger.info("清除失败，元素没有定位到，是null");
		}
	}

	// 逐个清除，对于密码输入框是无效的
	public void clearOneByOne(AndroidElement element) {
		if (element != null) {
			element.click();
			driver.pressKeyCode(AndroidKeyCode.KEYCODE_MOVE_END);
			String text = element.getText();
			for (int i = 0; i < text.length(); i++) {
				driver.pressBackspace();
			}
		} else {
			System.out.println("逐个清除失败，元素没有定位到，是null");
			logger.info("逐个清除失败，元素没有定位到，是null");
		}
	}

	// 输入内容直到正确
	public void sendkeysUntilCorrect(AndroidElement element, String str) {
		if (element != null) {
			boolean flag = true;
			element.sendKeys(str);
			while (flag) {
				if (str.equals(element.getText())) {
					flag = false;
				} else {
					element.sendKeys(str);
				}
			}
		} else {
			System.out.println("输入内容直到正确失败，元素为null");
			logger.info("输入内容直到正确失败，元素为null");
		}
	}

	// 逐个输入数字，模拟的是键盘输入，15610112934
	public void sendKeysOneByOne(String text) {
		char[] chr = text.toCharArray();// {1,5,6,1,x,x,x,x}
		for (int i = 0; i < chr.length; i++) {
			int c = Integer.valueOf(String.valueOf(chr[i]));
			int number = 0;
			switch (c) {
			case 0:
				// driver.pressKeyCode(AndroidKeyCode.KEYCODE_0);
				number = AndroidKeyCode.KEYCODE_0;
				break;
			case 1:
				number = AndroidKeyCode.KEYCODE_1;
				break;
			case 2:
				number = AndroidKeyCode.KEYCODE_2;
				break;
			case 3:
				number = AndroidKeyCode.KEYCODE_3;
				break;
			case 4:
				number = AndroidKeyCode.KEYCODE_4;
				break;
			case 5:
				number = AndroidKeyCode.KEYCODE_5;
				break;
			case 6:
				number = AndroidKeyCode.KEYCODE_6;
				break;
			case 7:
				number = AndroidKeyCode.KEYCODE_7;
				break;
			case 8:
				number = AndroidKeyCode.KEYCODE_8;
				break;
			case 9:
				number = AndroidKeyCode.KEYCODE_9;
				break;
			default:
				System.out.println("数值不对");
				logger.info("数值不对");
				break;
			}
			driver.pressKeyCode(number);
		}
	}

	// 坐标元素点击，针对一些能定位到整体元素但具体元素无法定位并且具有规律性的元素,获取每一个子元素的中心点坐标
	// 思路：获取整体元素，将整体元素分为多行多列元素，取每一个子元素的中心坐标进行点击
	public List<int[]> getElementByCoordinates(AndroidElement element,
			int rows, int columns) {
		int[] coordinate = new int[2];
		List<int[]> elementResolve = new ArrayList<int[]>();
		if (element != null) {
			int startx = element.getLocation().getX();// 起始点坐标x
			int starty = element.getLocation().getY();// 起始点坐标y
			int offsetx = element.getSize().getWidth();// 该元素的宽
			int offsety = element.getSize().getHeight();// 该元素的高
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					coordinate[0] = startx + (offsetx / 2 * columns)
							* (2 * j + 1);
					coordinate[1] = starty
							+ (offsety / (2 * rows) * (2 * i + 1));
					elementResolve.add(coordinate);
				}
			}
		}
		return elementResolve;
	}

	// 根据整体元素拆分后的规律子元素索引点击元素
	public void clickElementByCoordinate(AndroidElement element, int rows,
			int columns, int index) {
		if (element != null) {
			List<int[]> elementResolve = getElementByCoordinates(element, rows,
					columns);
			if (!elementResolve.isEmpty() && elementResolve != null) {
				driver.clickByCoordinate(elementResolve.get(index)[0],
						elementResolve.get(index)[1]);
			} else {
				System.out.println("坐标集合为空");
			}
		} else {
			System.out.println("元素为null");
		}
	}

	// 输入安全键盘的密码, 128606
	public void sendkeysPwd(List<int[]> pwd, int[] password) {
		if (password.length > 0) {
			for (int i = 0; i < password.length; i++) {
				if (password[i] == 0) {
					driver.clickByCoordinate(pwd.get(10)[0], pwd.get(10)[1]);
				} else {
					driver.clickByCoordinate(pwd.get(password[i] - 1)[0],
							pwd.get(password[i] - 1)[1]);
				}
			}
		}
	}

	// 输入安全键盘的密码, 128606
	public void sendkeysPwd(AndroidElement element, int rows, int columns,
			int[] password) {
		if (element != null) {
			List<int[]> pwd = getElementByCoordinates(element, rows, columns);
			if (password.length > 0) {
				for (int i = 0; i < password.length; i++) {
					if (password[i] == 0) {
						driver.clickByCoordinate(pwd.get(10)[0], pwd.get(10)[1]);
					} else {
						driver.clickByCoordinate(pwd.get(password[i] - 1)[0],
								pwd.get(password[i] - 1)[1]);
					}
				}
			}
		}

	}

	// 九宫格手势解锁,参数indexs是密码数字组成的数组，参数indexs={1,2,5,6,8,9}
	/**
	 * 九宫格手势解锁
	 * 
	 * @throws
	 */
	public void wakeByGestures(AndroidElement element, int[] indexs) {
		if (element != null) {
			List<int[]> elementResolve = getElementByCoordinates(element, 3, 3);
			TouchAction ta = null;
			if (indexs.length > 0) {
				ta = new TouchAction(driver).press(elementResolve.get(indexs[0] - 1)[0],
						                           elementResolve.get(indexs[0] - 1)[1]).waitAction(Duration.ofMillis(500));
			}
			for (int i = 1; i < indexs.length; i++) {
				ta.moveTo(elementResolve.get(indexs[i] - 1)[0] - elementResolve.get(indexs[i - 1] - 1)[0],
						elementResolve.get(indexs[i] - 1)[1] - elementResolve.get(indexs[i - 1] - 1)[1])
						.waitAction(Duration.ofMillis(500));
			}
			ta.release().perform();
		}
	}



	/**
	 * 从字符串中获取所有的数字,有小数点的就获取小数没有就是整数
	 * 
	 * @throws 
	 */
	public String getNumForString(String string) {
		// 需要取整数和小数的字符串
		String str;
		// 控制正则表达式的匹配行为的参数(小数)
		Pattern p = Pattern.compile("(\\d+\\.\\d+)");
		//Matcher类的构造方法也是私有的,不能随意创建,只能通过Pattern.matcher(CharSequence input)方法得到该类的实例. 
		Matcher m = p.matcher(string);
		//m.find用来判断该字符串中是否含有与"(\\d+\\.\\d+)"相匹配的子串
		if (m.find()) {
			//如果有相匹配的,则判断是否为null操作
			//group()中的参数：0表示匹配整个正则，1表示匹配第一个括号的正则,2表示匹配第二个正则,在这只有一个括号,即1和0是一样的
			str = m.group(1) == null ? "" : m.group(1);
		} else {
			//如果匹配不到小数，就进行整数匹配
			p = Pattern.compile("(\\d+)");
			m = p.matcher(string);
			if (m.find()) {
				//如果有整数相匹配
				str = m.group(1) == null ? "" : m.group(1);
			} else {
				//如果没有小数和整数相匹配,即字符串中没有整数和小数，就设为空
				str = "";
			}
		}
		return str;
	}

	/**
	 * 模拟回车键
	 * @throws 
	 */
	public void pressEnter() {
		driver.pressEnter();
	}

	/**
	 * 模拟键盘操作
	 * @throws num 按键值  
	 */
	public void pressAny(int num){
		driver.pressKeyCode(num);
	}

	/**
	 * 隐藏键盘
	 * @throws  
	 */
	public void hideKeyboard(){
		driver.hideKeyboard();
	}

	/**
	 * 执行adb命令
	 * @param s 要执行的命令
	 */
	public void excuteAdbShell(String s) {
		Runtime runtime=Runtime.getRuntime();
		try{
			runtime.exec(s);
		}catch(Exception e){
			System.out.println("执行命令:"+s+"出错");
		}
	}

	/**
	 * 模拟微信输入支付密码,先获取整个键盘的长宽
	 * @throws 
	 */
	public void inputPwdForweChat(int[] num) {
		AndroidElement element = driver.findElement(By.id("com.tencent.mm:id/aam"));
		int elementY = element.getLocation().getY();
		int elementHeight = element.getSize().getHeight();
		int keyHeight = driver.manage().window().getSize().getHeight() - (elementHeight + elementY);
		int keyWidth = driver.manage().window().getSize().getWidth();
		for (int i = 0; i < num.length; i++) {
			int x = keyWidth/3;
			int y = keyHeight/4;
			switch (num[i]) {
			case 0:
				driver.clickByCoordinate(keyWidth/2, (elementHeight + elementY)+(y/2)+(3*y));
				break;
			case 1:
				driver.clickByCoordinate((keyWidth/2)-x, (elementHeight + elementY)+(y/2));
				break;
			case 2:
				driver.clickByCoordinate(keyWidth/2, (elementHeight + elementY)+(y/2));
				break;
			case 3:
				driver.clickByCoordinate((keyWidth/2)+x, (elementHeight + elementY)+(y/2));
				break;
			case 4:
				driver.clickByCoordinate((keyWidth/2)-x, (elementHeight + elementY)+(y/2)+y);
				break;
			case 5:
				driver.clickByCoordinate(keyWidth/2, (elementHeight + elementY)+(y/2)+y);
				break;
			case 6:
				driver.clickByCoordinate((keyWidth/2)+x, (elementHeight + elementY)+(y/2)+y);
				break;
			case 7:
				driver.clickByCoordinate((keyWidth/2)-x, (elementHeight + elementY)+(y/2)+(2*y));
				break;
			case 8:
				driver.clickByCoordinate(keyWidth/2, (elementHeight + elementY)+(y/2)+(2*y));
				break;
			case 9:
				driver.clickByCoordinate((keyWidth/2)+x, (elementHeight + elementY)+(y/2)+(2*y));
				break;
			default:
				break;
			}
		}
	}

	/**
	 * 安全键盘输入密码，4行3列的
	 * @throws 
	 */
	public void inputPwds(int[] num , AndroidElement element) {
		int elementY = element.getLocation().getY();
		int elementHeight = element.getSize().getHeight();
		int elementWidth = element.getSize().getWidth();
		//		int keyHeight = driver.manage().window().getSize().getHeight() - (elementHeight + elementY);
		//		int keyWidth = driver.manage().window().getSize().getWidth();
		for (int i = 0; i < num.length; i++) {
			int x = elementWidth/3;
			int y = elementHeight/4;
			switch (num[i]) {
			case 0:
				driver.clickByCoordinate(elementWidth/2, (elementY)+(y/2)+(3*y));
				break;
			case 1:
				driver.clickByCoordinate((elementWidth/2)-x, (elementY)+(y/2));
				break;
			case 2:
				driver.clickByCoordinate(elementWidth/2, (elementY)+(y/2));
				break;
			case 3:
				driver.clickByCoordinate((elementWidth/2)+x, (elementY)+(y/2));
				break;
			case 4:
				driver.clickByCoordinate((elementWidth/2)-x, (elementY)+(y/2)+y);
				break;
			case 5:
				driver.clickByCoordinate(elementWidth/2, (elementY)+(y/2)+y);
				break;
			case 6:
				driver.clickByCoordinate((elementWidth/2)+x, (elementY)+(y/2)+y);
				break;
			case 7:
				driver.clickByCoordinate((elementWidth/2)-x, (elementY)+(y/2)+(2*y));
				break;
			case 8:
				driver.clickByCoordinate(elementWidth/2, (elementY)+(y/2)+(2*y));
				break;
			case 9:
				driver.clickByCoordinate((elementWidth/2)+x, (elementY)+(y/2)+(2*y));
				break;

			default:
				break;
			}
		}
	}



	/**
	 * 将数据保留两位小数
	 */
	public double getTwoDecimal(double num) {
		DecimalFormat dFormat=new DecimalFormat("######0.00");
		String yearString=dFormat.format(num);
		Double temp= Double.valueOf(yearString);
		return temp;
	}

	/**
	 * 使用tap方式点击
	 */
	public void singleTap(AndroidElement element) {
		TouchActions action = new TouchActions(driver);
		action.singleTap(element);
		action.perform();
	}

	/**
	 * 使用tap方式点击
	 */
	public void singleTap(By by) {
		TouchActions action = new TouchActions(driver);
		action.singleTap(driver.findElement(by));
		action.perform();
	}


	/**
	 * 获取element集合
	 */
	public List<AndroidElement> getElementList(By by) {
		List<AndroidElement> list = driver.findElements(by);
		return list;
	}

	/**
	 * 获取element属性
	 */
	public String getAttribute(By by , String name) {
		return driver.findElement(by).getAttribute(name);
	}
}
