package cn.hx.appium.testng;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import org.testng.IAnnotationTransformer;
import org.testng.Reporter;
import org.testng.annotations.ITestAnnotation;

//监听是否执行顺序错乱的,使用了这个类后失败重新执行的功能就失效了。
//Listener to fix TestNG Interleaving issue.  I had to re-write this as the original example I had did not allow for priority to be manually set on a test level.
public class RePrioritizingListener implements IAnnotationTransformer {

	HashMap<Object, Integer> priorityMap = new HashMap<Object, Integer>();
	Integer class_priorityCounter = 10001;
	// The length of the final priority assigned to each method.  分配给每个方法的最后优先级的长度。
	Integer max_testpriorityLength = 5;

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {

		
		// class of the test method.获取测试方法的类
		Class<?> declaringClass = testMethod.getDeclaringClass();
		// Current priority of the test assigned at the test method.  在测试方法中获取当前case的优先级。 
		Integer test_priority = annotation.getPriority();
		// Current class priority.  当前类优先级。
		Integer current_ClassPriority = priorityMap.get(declaringClass);
		System.out.println(declaringClass.toString() +current_ClassPriority);

		if (current_ClassPriority == null) {
			current_ClassPriority = class_priorityCounter++;
			priorityMap.put(declaringClass, current_ClassPriority);
		}

		String concatenatedPriority = test_priority.toString();

		// Adds 0's to start of this number.  添加0开始这个数字。 
		while (concatenatedPriority.length() < max_testpriorityLength) {
			concatenatedPriority = "0" + concatenatedPriority;
		}

		// Concatenates our class counter to the test level priority (example将类计数器连接到测试级别优先级(例如 
		// for test with a priority of 1: 1000100001; same test class with a 优先级为1：1000100001的测试；
		// priority of 2: 1000100002; next class with a priority of 1. 1000200001) 优先级为2：1000100002；下一类优先级为1。1000200001) 
		concatenatedPriority = current_ClassPriority.toString() + concatenatedPriority;

		//Sets the new priority to the test method. 设置测试方法的新优先级
		annotation.setPriority(Integer.parseInt(concatenatedPriority));

		String printText = testMethod.getName() + " Priority = " + concatenatedPriority;
		Reporter.log(printText);
		System.out.println(printText);

	}
}

