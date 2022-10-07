package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class GenericUtils {
	public WebDriver driver;
	
	public GenericUtils(WebDriver driver)
	{
		this.driver = driver;
	}
	

	public void SwitchWindowToChild()
	{
		Set<String> s1=driver.getWindowHandles();
		Iterator<String> i1 =s1.iterator();
		String parentWindow = i1.next();
		String childWindow = i1.next();
		driver.switchTo().window(childWindow);
	}
	
	//not working
	/*public void MainTab() {
		ArrayList<String> windowsHandles = new ArrayList<String>(driver.getWindowHandles());
		for (String item : windowsHandles) {
			driver.switchTo().window(item);
		}
		// windowsHandles arraylist's first tab is main tab
		driver.switchTo().window(windowsHandles.get(0));
		
	}
	
	public void NewTab() {
		driver.switchTo().newWindow(WindowType.TAB);
	}
	
	public void Stream() throws IOException {
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//test//resources//global.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String mail = prop.getProperty("email");
		String password = prop.getProperty("password");
	}*/
	
}
