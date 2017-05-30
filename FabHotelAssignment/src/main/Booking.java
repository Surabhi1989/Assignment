package main;

import main.Webhelper;

public class Booking {
	
	Webhelper wb = new Webhelper();
	public boolean initiate(String URL) {
		if (!(wb.initmethod(URL))) {
			System.out.println("NOT ABLE TO OPEN FIREFOX");
		}
		if (wb.IsDisplayed("//div[@class='logo']", "logo")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean BookingHotel() {
		try{
			wb.IsDisplayed("(//div[@class='catalogue_property_price_book']/a)[1]","Booking button");
			wb.ClickbyXpath("(//div[@class='catalogue_property_price_book']/a)[1]");
			Thread.sleep(2000);
			wb.IsDisplayed("//div[@class='property_booking_form']", "Booking Form");
			wb.ClickbyXpath("//input[@id='checkIn']");
			Thread.sleep(3000);
			wb.ClickbyXpath("(//div[@id='booking_calender']//th[@class='next'])[1]");
			Thread.sleep(2000);
			wb.ClickbyXpath("//div[@class='datepicker-days']//tr[3]//td[3]");
			Thread.sleep(3000);
			wb.ClickbyXpath("//input[@id='checkOut']");
			Thread.sleep(3000);
			wb.ClickbyXpath("//div[@class='property_sub_total']/following-sibling::button[@id='propertyReviewBooking']");
			Thread.sleep(5000);
			int TotalAmmount = Integer.parseInt(wb.getText("//div[@class='review_booking_total_amount']/span"));
			wb.IsDisplayed("//div[@class='review_booking_offers']", "CouponCode");
			Thread.sleep(2000);
			wb.enterTextByxpath("//input[@class='coupon-code required_param']", "FH15P");
			Thread.sleep(2000);
			wb.ClickbyXpath("//div[@class='apply_coupon_code']");
			Thread.sleep(4000);
			int DeductedAmount = Integer.parseInt(wb.getText("//div[@class='review_booking_total_amount']/span"));
			if(DeductedAmount>=TotalAmmount){
				System.err.println("Coupon not applied successfully");
				return false;
			}
			wb.ClickbyXpath("//div[@class='review_booking_continue submit-booking-details']");
			Thread.sleep(2000);
			wb.IsDisplayed("//div[@class='review_guest_details']","User Details");
			wb.enterTextByxpath("//input[@class='user-name required_param']","abcd efgh");
			wb.enterTextByxpath("//input[@class='user-email required_param']", "abcd@abcd.com");
			wb.enterTextByxpath("//input[@class='user-mobile user_mobile required_param']", "9809876542");
			wb.ClickbyXpath("//div[@class='review_guest_continue submit-guest-details']");
			Thread.sleep(2000);
			wb.IsDisplayed("(//div[@class='review_details_content'])[3]","Payment Info");
			wb.ClickbyXpath("//li[@class='payment_options_tab_single payment-option-pay-at-hotel']");
			Thread.sleep(3000);
			wb.IsDisplayed("//div[@class='review_payment_remove_coupon_text']","Coupon Remove");
			int aftercouponamount = Integer.parseInt(wb.getText("//div[@class='review_details_complete_price']/span"));
			if(aftercouponamount!=TotalAmmount){
				System.err.println("Coupon not removed successfully");
				return false;
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	public void CloseAll() {
		wb.CloseBrowser();
	}
}
