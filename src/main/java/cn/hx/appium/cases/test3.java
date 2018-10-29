package cn.hx.appium.cases;

import org.testng.annotations.Test;

public class test3 {
	@Test(priority=1)
	public void t1(){
		System.out.println("test33333333 priority=1");
	}
	
	@Test(priority=2)
	public void t2(){
		System.out.println("test33333333 priority=2");
	}
	
	@Test(priority=3)
	public void t3(){
		System.out.println("test33333333 priority=3");
	}
	
	@Test(priority=4)
	public void t4(){
		System.out.println("test33333333 priority=4");
	}
	
	@Test(priority=5)
	public void t5(){
		System.out.println("test33333333 priority=5");
	}
}
