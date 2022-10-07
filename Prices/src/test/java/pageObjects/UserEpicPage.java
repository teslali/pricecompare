package pageObjects;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;
import java.util.stream.IntStream;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteKeyboard;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.GenericUtils;
import utils.TestContextSetup;

public class UserEpicPage {
	public WebDriver driver;
	GenericUtils genericUtils;
	TestContextSetup testContextSetup;
	UserEpicPage userEpicPage;

	public UserEpicPage(WebDriver driver) {
		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

	}

	public UserEpicPage(TestContextSetup testContextSetup) {
		this.testContextSetup = testContextSetup;
		this.genericUtils = testContextSetup.pageObjectManager.getGenericUtils();
		this.userEpicPage = testContextSetup.pageObjectManager.getUserEpicPage();
	}

	// locators
	By migrosPopUp = By.xpath("//span[@class='mdc-button__label']");
	By migrosSearch = By.xpath("//input[@id='product-search-combobox--trigger']");
	By migrosBtn = By.xpath("//div[@id='product-search-combobox-search-right-button']");
	By migrosMilka = By.xpath("//div[@class='price']");
	By migrosPrdct = By.xpath("//a[@class='mat-caption text-color-black product-name']");
	By kauflandPopUp = By.xpath("//button[@tabindex='1']");
	By kauflandSearch = By.xpath("//input[@placeholder='Online-Marktplatz durchsuchen']");
	By kauflandBtn = By.cssSelector(".rd-button:nth-child(2) .svg-icon__icon");
	By kauflandMilka = By.xpath("//span[@class='rd-price-information__price']");
	By kauflandPrdct = By.xpath("//div[normalize-space()='Milka Oreo 100g']");
	By walmartSearch = By.xpath("//input[@placeholder='Search everything at Walmart online and in store']");
	By walmartBtn = By.xpath("//i[@class='ld ld-Search absolute']");
	By walmartPrdct = By.xpath(
			"//div[@class='flex flex-wrap w-100 flex-grow-0 flex-shrink-0 ph2 pr0-xl pl4-xl mt0-xl mt3']//div[1]//div[1]//div[1]//a[1]");
	By amazonSearch = By.xpath("//input[@id='twotabsearchtextbox']");
	By amazonBtn = By.xpath("//input[@id='nav-search-submit-button']");
	By amazonPrdct = By.linkText("Milka Oreo Bar 100g (10-pack)");
	By amazonMilka = By.xpath(
			"//span[@class='a-price aok-align-center reinventPricePriceToPayMargin priceToPay']//span[@aria-hidden='true']");
	By walmartMilka = By.xpath("//div[@class='f6 gray lh-title mb3 dn db-m']");
	By firstCurSlct = By.xpath("//select[@class='zuzy3c l84FKc']");
	By firstCurInput = By.xpath("//div[@id='knowledge-currency__updatable-data-column']/div[3]/div/div/input");
	By secondCurSlct = By.xpath("//select[@class='zuzy3c NKvwhd']");
	By secondCurInput = By.xpath("//div[@id='knowledge-currency__updatable-data-column']/div[3]/div/div[2]/input");
	By walmartRobot = By.linkText("Press & Hold");
	By caresiz = By.xpath("//button[@aria-label='Next carousel slide']");

	// product search at migros site
	public void MigrosSearch() throws IOException, InterruptedException {

		// data insertion from global.properties file
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//test//resources//global.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String search = prop.getProperty("Search");

		driver.findElement(migrosPopUp).click();
		driver.findElement(migrosSearch).sendKeys(search);
		driver.findElement(migrosBtn).click();
		driver.findElement(migrosPrdct).click();

	}

	// product search at kaufland site
	public void KauflandSearch() throws IOException, InterruptedException {
		// data insertion from global.properties file
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//test//resources//global.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String search = prop.getProperty("Search");
		String url3 = prop.getProperty("QAUrl3");
		// opening new tab and switching to new tab
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get(url3);
		ArrayList<String> windowsHandles = new ArrayList<String>(driver.getWindowHandles());
		for (String item : windowsHandles) {
			driver.switchTo().window(item);
		}
		// windowsHandles arraylist's first tab is main tab
		driver.switchTo().window(windowsHandles.get(1));

		driver.findElement(kauflandPopUp).click();
		driver.findElement(kauflandSearch).sendKeys(search);
		Thread.sleep(1000);
		driver.findElement(kauflandBtn).click();
		driver.findElement(kauflandPrdct).click();

	}

	// tries to search for product at walmart site
	public void WalmartSearch() throws IOException, InterruptedException {
		// data insertion from global.properties file
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//test//resources//global.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String search = prop.getProperty("Search");
		String url4 = prop.getProperty("QAUrl4");
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get(url4);
		// opening new tab and switching to new tab
		ArrayList<String> windowsHandles = new ArrayList<String>(driver.getWindowHandles());
		for (String item : windowsHandles) {
			driver.switchTo().window(item);
		}
		
		driver.switchTo().window(windowsHandles.get(2));
		Thread.sleep(3000);

		// does press & and hold captcha but website acts too unstable to add for a test
		// got a locator's xpath right behind press & hold captcha button, printed it's
		// coordinates then moved mouse to that coordinates
		// gave click and hold command to mouse
		// need to readjust for another device
		Actions a = new Actions(driver);
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.TAB);
		Thread.sleep(2000);
		a.moveByOffset(840, 561).clickAndHold().perform();
		a.release();
		Thread.sleep(5000);

	}

	//compare prices at google's currency compare site
	public void ComparePrices() throws IOException, InterruptedException {
		// data insertion from global.properties file
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//test//resources//global.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String currency = prop.getProperty("Currency");
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get(currency);
		
		// opening new tab and switching to new tab
		ArrayList<String> windowsHandles = new ArrayList<String>(driver.getWindowHandles());
		for (String item : windowsHandles) {
			driver.switchTo().window(item);
		}

		driver.switchTo().window(windowsHandles.get(4));
		
		//goes back to migros' product page and gets product's price
		driver.switchTo().window(windowsHandles.get(0));
		String mPrice = driver.findElement(migrosMilka).getText().replace("T", "").replace("L", "").trim();
		System.out.println("100g Milka Oreo costs " + mPrice + " Turkish Lira at Migros");
		
		//goes back to kaufland's product page and gets product's price
		driver.switchTo().window(windowsHandles.get(1));
		String kPrice = driver.findElement(kauflandMilka).getAttribute("innerText").replace("€", "").trim();
		System.out.println("100g Milka Oreo costs " + kPrice + " Euro at Kaufland");

		//goes back to amazon's product page and gets procudt's price
		driver.switchTo().window(windowsHandles.get(3));
		String aPrice = driver.findElement(amazonMilka).getText().replace("\n", "").replace("$", "").replace("9", ".")
				.replace("5", "9").trim();
		float f = Float.parseFloat(aPrice);
		float zawarudo = f / 10;
		System.out.println("100g Milka Oreo costs " + zawarudo + " U.S. Dollars at Amazon US");
		String epitaph = Float.toString(zawarudo);
		
		//switching back to currency compare site	
		driver.switchTo().window(windowsHandles.get(4));

		// selects first currency as euro and changes second ones as TL and USD then
		// gets
		// euro equivalences and put them in an array to print them in order
		Select cur1 = new Select(driver.findElement(firstCurSlct));
		cur1.selectByVisibleText("Euro");
		Thread.sleep(2000);
		Select cur2 = new Select(driver.findElement(secondCurSlct));
		cur2.selectByVisibleText("Türk Lirası");
		Thread.sleep(2000);
		driver.findElement(secondCurInput).clear();
		driver.findElement(secondCurInput).sendKeys(mPrice);
		driver.findElement(secondCurInput).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		String comp1 = driver.findElement(firstCurInput).getAttribute("value");
		System.out.println("100g Milka oreo costs " + comp1 + " Euro at Migros");

		Select cur3 = new Select(driver.findElement(secondCurSlct));
		cur3.selectByVisibleText("ABD Doları");
		Thread.sleep(2000);
		driver.findElement(secondCurInput).clear();
		driver.findElement(secondCurInput).sendKeys(epitaph);
		driver.findElement(secondCurInput).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		String comp2 = driver.findElement(firstCurInput).getAttribute("value");
		System.out.println("100g Milka oreo costs " + comp2 + " Euro at Amazon US");
		//changing type of prices to float to put all of them in same array
		float kar = Float.parseFloat(comp2);
		float kar1 = Float.parseFloat(comp1);

		float[] f1 = { kar1, (float) 1.35, kar };

		for (int index = f1.length - 1; index >= 0; index--)
			System.out.print(f1[index] + ">");

	}

	// product search at amazon site
	public void AmazonSearch() throws IOException {
		// data insertion from global.properties file
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//test//resources//global.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String search = prop.getProperty("Search");
		String url5 = prop.getProperty("QAUrl5");
		// opening new tab and switching to new tab
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get(url5);
		ArrayList<String> windowsHandles = new ArrayList<String>(driver.getWindowHandles());
		for (String item : windowsHandles) {
			driver.switchTo().window(item);
		}

		driver.switchTo().window(windowsHandles.get(3));
		driver.findElement(amazonSearch).sendKeys(search);
		driver.findElement(amazonBtn).click();
		driver.findElement(amazonPrdct).click();

	}
	
	//dummy method for comparing prices
	public void dummyCompare() throws IOException, InterruptedException {
		Actions a = new Actions(driver);
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//test//resources//global.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String currency = prop.getProperty("Currency");
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get(currency);
		ArrayList<String> windowsHandles = new ArrayList<String>(driver.getWindowHandles());
		for (String item : windowsHandles) {
			driver.switchTo().window(item);
		}
		// windowsHandles arraylist's first tab is main tab
		driver.switchTo().window(windowsHandles.get(1));
		Select cur1 = new Select(driver.findElement(firstCurSlct));
		cur1.selectByVisibleText("Euro");
		Thread.sleep(2000);
		Select cur2 = new Select(driver.findElement(secondCurSlct));
		cur2.selectByVisibleText("Türk Lirası");
		Thread.sleep(2000);
		driver.findElement(secondCurInput).clear();
		driver.findElement(secondCurInput).sendKeys("20.75 ");
		driver.findElement(secondCurInput).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		String comp1 = driver.findElement(firstCurInput).getAttribute("value");
		System.out.println("100 gram Milka oreo " + comp1 + " Euro at Migros");

		Select cur3 = new Select(driver.findElement(secondCurSlct));
		cur3.selectByVisibleText("ABD Doları");
		Thread.sleep(2000);
		driver.findElement(secondCurInput).clear();
		driver.findElement(secondCurInput).sendKeys("2.49");
		driver.findElement(secondCurInput).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		String comp2 = driver.findElement(firstCurInput).getAttribute("value");
		System.out.println("100 gram Milka oreo costs " + comp2 + " Euro at Amazon US");
		float kar = Float.parseFloat(comp2);
		float kar1 = Float.parseFloat(comp1);

		float[] f = { kar1, (float) 1.35, kar };

		for (int index = f.length - 1; index >= 0; index--)
			System.out.print(f[index] + ">");

	}

}