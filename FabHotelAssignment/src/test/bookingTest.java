package test;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import main.Booking;

public class bookingTest {
	Booking bt = new Booking();
	
	@BeforeClass
	  public void initiateBrowser(){
		if(bt.initiate("https://www.fabhotels.com/hotels-in-new-delhi")){
			System.out.println("Successfully able to verify logo on the website");
		}else {
			Assert.assertTrue(false, "not able to validate Logo");
		}
		
	  }


	@Test
	public void insertdate() throws Exception {
		System.out.println("Inside Test Insert Date");
		if (bt.BookingHotel()) {
			System.out.println("Amount has been verified has been added");
		} else {
			Assert.assertTrue(false, "not able to varify ammount on website");
		}
	}

	@AfterClass
	public void Close() {
		try {
			bt.CloseAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
