package cn.hx.appium.cases;

import org.testng.annotations.Test;

public class test2 {
	
	@Test(priority=1)
	public void t1(){
		System.out.println("test2222222222222 priority=1");
	}
	
	@Test(priority=2)
	public void o2(){
		System.out.println("test2222222222222 priority=2");
	}
	
	@Test(priority=3)
	public void o3(){
		System.out.println("test2222222222222 priority=3");
	}
	
	@Test(priority=4)
	public void o4(){
		System.out.println("test2222222222222 priority=4");
	}
	
	@Test(priority=5)
	public void o5(){
		System.out.println("test2222222222222 priority=5");
	}
}
