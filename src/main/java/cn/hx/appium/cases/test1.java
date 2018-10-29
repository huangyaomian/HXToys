package cn.hx.appium.cases;

import org.testng.Assert;
import org.testng.annotations.Test;

public class test1 {
	
	
	
	@Test(priority=0)
	public void t1(){
		System.out.println("test11111111 priority=1");
		Assert.assertEquals(false, true);
	}
	
	@Test(priority=1, enabled=false)
	public void t2(){
		System.out.println("test11111111 priority=2");
		
	}
	
	@Test(priority=2)
	public void t3(){
		System.out.println("test11111111 priority=3");
		Assert.assertEquals(true, null);
	}
	
	@Test(priority=3)
	public void t4(){
		System.out.println("test11111111 priority=4");
	}
	
	@Test(priority=4)
	public void t5(){
		System.out.println("test11111111 priority=5");
	}
	
	
}
