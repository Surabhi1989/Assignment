package test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;

import main.DateHelper;

public class DateTest {
  
	DateHelper dt = new DateHelper();
	
@BeforeClass
  public void initiateBrowser(){
	if(dt.initiate("https://www.fabhotels.com/hotels-in-new-delhi")){
		System.out.println("Successfully able to verify logo on the website");
	}else {
		Assert.assertTrue(false, "not able to validate Logo");
	}
	
  }


@Test
public void insertdate() throws Exception {
	System.out.println("Inside Test Insert Date");
	if (dt.addDate("5-May-2018","21-June-2018")) {
		System.out.println("Provided date has been added");
	} else {
		Assert.assertTrue(false, "not able to validate given date on website");
	}
}

@AfterClass
public void Close() {
	try {
		dt.CloseAll();
	} catch (Exception e) {
		e.printStackTrace();
	}
}
}