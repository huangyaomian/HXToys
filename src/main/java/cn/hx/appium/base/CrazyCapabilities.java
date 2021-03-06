package cn.hx.appium.base;

import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;

import org.openqa.selenium.remote.DesiredCapabilities;

import cn.hx.appium.util.ProUtil;

public class CrazyCapabilities {
	public static int systemPort = 8201;
	public  DesiredCapabilities initCaps(String capsPath,String udid){
		ProUtil p=new ProUtil(capsPath);
		File apkPath=new File(p.getPro("apkpath"));
		DesiredCapabilities caps=new DesiredCapabilities();
		try {
			caps.setCapability(AndroidCapabilityType.APP, apkPath.getAbsolutePath());
			caps.setCapability(AndroidCapabilityType.DEVICE_NAME, udid);
			caps.setCapability(AndroidCapabilityType.APP_PACKAGE, p.getPro(AndroidCapabilityType.APP_PACKAGE));
			caps.setCapability(AndroidCapabilityType.APP_ACTIVITY, p.getPro(AndroidCapabilityType.APP_ACTIVITY));
			caps.setCapability(AndroidCapabilityType.NO_RESET, p.getPro(AndroidCapabilityType.NO_RESET)); //不需要再次安装
			caps.setCapability(AndroidCapabilityType.NO_SIGN, p.getPro(AndroidCapabilityType.NO_SIGN));
			caps.setCapability(AndroidCapabilityType.UNICODE_KEY_BOARD, p.getPro(AndroidCapabilityType.UNICODE_KEY_BOARD));
			caps.setCapability(AndroidCapabilityType.RESET_KEY_BOARD, p.getPro(AndroidCapabilityType.RESET_KEY_BOARD));
			caps.setCapability(AndroidCapabilityType.AUTOMATION_NAME,AndroidCapabilityType.UI_AUTOMATOR_2);
			caps.setCapability(AndroidCapabilityType.UDID,udid);
			caps.setCapability(AndroidCapabilityType.NEW_COMMAND_TIMEOUT, p.getPro(AndroidCapabilityType.NEW_COMMAND_TIMEOUT));
			caps.setCapability(AndroidCapabilityType.SYSTEM_PORT,String.valueOf(systemPort++));//uiautomator2并发的时候才需要设置
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return caps;
	}
	public  DesiredCapabilities initCapsWait(String capsPath,String udid){
		ProUtil p=new ProUtil(capsPath);
		File apkPath =new File(p.getPro("apkpath"));
		DesiredCapabilities caps=new DesiredCapabilities();
		try {
			caps.setCapability(AndroidCapabilityType.APP, apkPath.getAbsolutePath());
			caps.setCapability(AndroidCapabilityType.DEVICE_NAME, udid);
			caps.setCapability(AndroidCapabilityType.APP_PACKAGE, p.getPro(AndroidCapabilityType.APP_PACKAGE));
			caps.setCapability(AndroidCapabilityType.APP_ACTIVITY, p.getPro(AndroidCapabilityType.APP_ACTIVITY));
			caps.setCapability(AndroidCapabilityType.APP_WAIT_ACTIVITY, p.getPro(AndroidCapabilityType.APP_WAIT_ACTIVITY));
			caps.setCapability(AndroidCapabilityType.NO_SIGN, p.getPro(AndroidCapabilityType.NO_SIGN));
			caps.setCapability(AndroidCapabilityType.UNICODE_KEY_BOARD, p.getPro(AndroidCapabilityType.UNICODE_KEY_BOARD));
			caps.setCapability(AndroidCapabilityType.RESET_KEY_BOARD, p.getPro(AndroidCapabilityType.RESET_KEY_BOARD));
			caps.setCapability(MobileCapabilityType.AUTOMATION_NAME,AndroidCapabilityType.UI_AUTOMATOR_2);
			caps.setCapability(AndroidCapabilityType.UDID,udid);
			caps.setCapability(AndroidCapabilityType.NEW_COMMAND_TIMEOUT, p.getPro(AndroidCapabilityType.NEW_COMMAND_TIMEOUT));
			caps.setCapability(AndroidCapabilityType.SYSTEM_PORT,String.valueOf(systemPort++));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return caps;
	}
}
