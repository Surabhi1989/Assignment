package test;


	import org.testng.annotations.AfterClass;
	import org.testng.annotations.Test;
	import org.testng.annotations.BeforeClass;
	import org.testng.Assert;

	import main.AddHotelsHelper;

	public class TotalHotels {
	  
		AddHotelsHelper at = new AddHotelsHelper();
		
	@BeforeClass
	  public void initiateBrowser(){
		if(at.initiate("https://www.fabhotels.com")){
			System.out.println("Successfully able to verify logo on the website");
		}else {
			Assert.assertTrue(false, "not able to validate Logo");
		}
		
	  }


	@Test
	public void insertdate() throws Exception {
		System.out.println("Inside Test Insert Date");
		if (at.GetHotelNumber()) {
			System.out.println("Provided Hotels has been added");
		} else {
			Assert.assertTrue(false, "not able to validate Hotel Numbers on website");
		}
	}

	@AfterClass
	public void Close() {
		try {
			at.CloseAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
