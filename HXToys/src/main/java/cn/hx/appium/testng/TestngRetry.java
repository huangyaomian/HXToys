package cn.hx.appium.testng;

import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;

public class TestngRetry implements IRetryAnalyzer {
	private static Logger logger = Logger.getLogger(TestngRetry.class);
	private int retryCount = 1;
	private int maxRetryCount=3;//这个数是控制失败后再执行多少次。1代表总共一个用例会执行一次
	@Override
	public boolean retry(ITestResult result) {
		if (retryCount < maxRetryCount) {
			String message = "running retry for  '" + result.getName() + "' on class " + this.getClass().getName() + " Retrying "
					+ retryCount + " times";
			logger.info(message);
			Reporter.setCurrentTestResult(result);
			Reporter.log(result.getName() +"第"+retryCount+"次失败后重新执行:");
			System.out.println(result.getName() +"第"+retryCount+"次失败后重新执行:");
			retryCount++;
			return true;
		}
		return false;
	}

}