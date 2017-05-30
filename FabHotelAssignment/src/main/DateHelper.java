package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;

import main.Webhelper;

public class DateHelper {

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

	public boolean addDate(String inDate, String finDate) {
		if (wb.IsDisplayedById("daterangepicker_start")) {
			String initial_date[] = inDate.split("-");
			int inDate1 = Integer.parseInt(initial_date[0]);
			String inMonth = initial_date[1];
			String final_date[] = finDate.split("-");
			int finDate1 = Integer.parseInt(final_date[0]);
			String finMonth = final_date[1];
			int inYear = Integer.parseInt(initial_date[2]);
			int finYear = Integer.parseInt(final_date[2]);
			try {
				wb.ClickbyId("daterangepicker_start");
				Thread.sleep(2000);
				String Text = wb.getText("//div[@class='calendar left']//th[@class='month']");
				String new1[] = Text.split(" ");
				int number = Integer.parseInt(new1[1]);
				if (number > inYear) {
					System.err.println("Invalid Year");
					return false;
				} else {
					if (number < finYear) {
						while (number != finYear) {
							wb.ClickbyXpath("//th[@class='next available']");
							Thread.sleep(1000);
							String Text1 = wb.getText("//div[@class='calendar left']//th[@class='month']");
							String arr[] = Text1.split(" ");
							number = Integer.parseInt(arr[1]);
						}
						int finmonthNumber = GetMonth(finMonth);
						int inmonthNumber = GetMonth(inMonth);
						String Text2 = wb.getText("//div[@class='calendar left']//th[@class='month']");
						String arr2[] = Text2.split(" ");
						int month = GetMonth(arr2[0]);
						while (month != inmonthNumber) {
							wb.ClickbyXpath("//th[@class='next available']");
							Thread.sleep(1000);
							String Text1 = wb.getText("//div[@class='calendar left']//th[@class='month']");
							String arr[] = Text1.split(" ");
							month = GetMonth(arr[0]);
						}
						Thread.sleep(2000);
						System.out.println("now adding date");
						String Path = "//div[@class='calendar left']//tr/td[contains(text(),'" + inDate1 + "')]";
						List<WebElement> arr = wb.findElementsbyXpath(Path);
						for (WebElement ele : arr) {
							System.out.println(ele.getText());
							if (Integer.parseInt(ele.getText())==inDate1) {
								System.out.println("clicking element" +ele.toString());
								Thread.sleep(3000);
								wb.ClickbyXpath(Path);
								break;
							}
						}
						Thread.sleep(2000);
						Path = "//div[@class='calendar right']//tr/td[contains(text(),'" + finDate1 + "')]";
						arr = wb.findElementsbyXpath(Path);
						for (WebElement ele : arr) {
							System.out.println(ele.getText());
							if (Integer.parseInt(ele.getText())==finDate1) {
								wb.ClickbyXpath(Path);
								break;
							}
						}
						Thread.sleep(1000);
					}
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} else {
			System.err.println("Can Not open calender");
			return false;
		}
	}

	public void CloseAll() {
		wb.CloseBrowser();
	}

	public int GetMonth(String Month) {
		int monthNumber;
		switch (Month.toLowerCase()) {
		case "january":
			monthNumber = 1;
			break;
		case "february":
			monthNumber = 2;
			break;
		case "march":
			monthNumber = 3;
			break;
		case "april":
			monthNumber = 4;
			break;
		case "may":
			monthNumber = 5;
			break;
		case "june":
			monthNumber = 6;
			break;
		case "july":
			monthNumber = 7;
			break;
		case "august":
			monthNumber = 8;
			break;
		case "september":
			monthNumber = 9;
			break;
		case "october":
			monthNumber = 10;
			break;
		case "november":
			monthNumber = 11;
			break;
		case "december":
			monthNumber = 12;
			break;
		default:
			monthNumber = 0;
			break;
		}
		return monthNumber;

	}

}
