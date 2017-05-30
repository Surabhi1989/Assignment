package main;


	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
	import java.util.List;
	import org.apache.poi.hssf.usermodel.HSSFWorkbook;
	import org.apache.poi.ss.usermodel.Row;
	import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.NoSuchElementException;
	import org.openqa.selenium.WebElement;

	import main.Webhelper;

	public class AddHotelsHelper {

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
		
		public boolean GetHotelNumber() {
			try{
				for(int i = 1; i<23;i++){
					String Path= "(//div[@class='dropdown-content']//a)["+i+"]";
				wb.HoverElement("//button[@class='dropbtn']/span",Path);
				wb.ClickbyXpath(Path);
				Thread.sleep(4000);
				System.out.println("Hotels in the city are: "+wb.getText("//h1[@class='propertyCount']"));
				Thread.sleep(2000);
				wb.Back();
				}
			return true;
			}catch (Exception e){
				e.printStackTrace();
				return false;
			}
		}
		
		public void CloseAll() {
			wb.CloseBrowser();
		}
}
