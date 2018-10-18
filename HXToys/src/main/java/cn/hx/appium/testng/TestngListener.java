package cn.hx.appium.testng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.log4testng.Logger;

public class TestngListener extends TestListenerAdapter {
	private static Logger logger = Logger.getLogger(TestngListener.class);
	public static final String CONFIG = "config.properties";

	@Override
	public void onTestFailure(ITestResult tr) {
		super.onTestFailure(tr);
		logger.info(tr.getName() + " Failure");
		//takeScreenShot(tr);
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		super.onTestSkipped(tr);
		logger.info(tr.getName() + " Skipped");
		//takeScreenShot(tr);
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		super.onTestSuccess(tr);
		logger.info(tr.getName() + " Success");
	}

	@Override
	public void onTestStart(ITestResult tr) {
		super.onTestStart(tr);
		logger.info(tr.getName() + " Start");
	}

	@Override
	public void onFinish(ITestContext testContext) {
		super.onFinish(testContext);

		// 稍后我们将删除的测试结果列表 
		ArrayList<ITestResult> testsToBeRemoved = new ArrayList<ITestResult>();
		// 收集所有通过测试的id
		Set<Integer> passedTestIds = new HashSet<Integer>();
		for (ITestResult passedTest : testContext.getPassedTests().getAllResults()) {
			logger.info("PassedTests = " + passedTest.getName());
			passedTestIds.add(getId(passedTest));
		}

		Set<Integer> failedTestIds = new HashSet<Integer>();
		for (ITestResult failedTest : testContext.getFailedTests().getAllResults()) {
			logger.info("failedTest = " + failedTest.getName());
			// id = class + method + dataprovider
			int failedTestId = getId(failedTest);

			//这个地方是做失败的用例的去除
			// if we saw this test as a failed test before we mark as to be deleted
			// or delete this failed test if there is at least one passed version
			if (failedTestIds.contains(failedTestId) || passedTestIds.contains(failedTestId)) {
				testsToBeRemoved.add(failedTest);
			} else {
				failedTestIds.add(failedTestId);
			}
		}

	/*	// finally delete all tests that are marked
		for (Iterator<ITestResult> iterator = testContext.getFailedTests().getAllResults().iterator(); iterator.hasNext();) {
			ITestResult testResult = iterator.next();
			if (testsToBeRemoved.contains(testResult)) {
				logger.info("Remove repeat Fail Test: " + testResult.getName());
				iterator.remove();
			}
		}*/

		//这个地方是做跳过的用例的去除
		// if we saw this test as a failed test before we mark as to be deleted
		// or delete this failed test if there is at least one passed version
		Set<Integer> skippedTestIds = new HashSet<Integer>();
		for (ITestResult skippedTest : testContext.getSkippedTests().getAllResults()) {
			logger.info("skippedTest = " + skippedTest.getName());
			// id = class + method + dataprovider
			int skippedTestId = getId(skippedTest);

			// if we saw this test as a skiped test before we mark as to be deleted
			// or delete this failed test if there is at least one passed version
			if (skippedTestIds.contains(skippedTestId) || passedTestIds.contains(skippedTestId)) {
				testsToBeRemoved.add(skippedTest);
			} else {
				failedTestIds.add(skippedTestId);
			}
		}

		// 最后，删除所有标记的case。
		for (Iterator<ITestResult> iterator = testContext.getSkippedTests().getAllResults().iterator(); iterator.hasNext();) {
			ITestResult testResult = iterator.next();
			if (testsToBeRemoved.contains(testResult)) {
				logger.info("Remove repeat Skip or Fail Test: " + testResult.getName());
				iterator.remove();
			}
		}

	}

	private int getId(ITestResult result) {
		int id = result.getTestClass().getName().hashCode();
		id = id + result.getMethod().getMethodName().hashCode();
		id = id + (result.getParameters() != null ? Arrays.hashCode(result.getParameters()) : 0);
		return id;
	}
}