package com.iocean.employee_xml;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EmployeeTest {
	
	public void testApp(){
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		context.close();
	}
}
