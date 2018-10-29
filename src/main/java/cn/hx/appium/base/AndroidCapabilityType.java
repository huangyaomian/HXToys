package cn.hx.appium.base;


import io.appium.java_client.remote.MobileCapabilityType;

public interface AndroidCapabilityType extends MobileCapabilityType {
	String APP_PACKAGE = "appPackage";
	String APP_ACTIVITY = "appActivity";
	String NO_SIGN="noSign";
	String UNICODE_KEY_BOARD="unicodeKeyboard";
	String RESET_KEY_BOARD="resetKeyboard";
	String AUTO_LAUNCH="autoLaunch";
	String NEW_COMMAND_TIMEOUT = "newCommandTimeout";
	String UI_AUTOMATOR_2 = "uiautomator2";
	String NO_RESET = "noReset";
	String APP_WAIT_ACTIVITY = "appWaitActivity";
	String SYSTEM_PORT = "systemPort";
}
