package variousConcepts;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LeartTestNG {

	WebDriver driver;

	@BeforeMethod
	public void init() {

		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		// WebDriver driver = new ChromeDriver();
		driver = new ChromeDriver();
		driver.get("https://www.techfios.com/billing/?ng=admin/");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	// @Test
	public void loginTest() {

		Assert.assertEquals(driver.getTitle(), "Login - iBilling", "Wrong Page");

		// Element Lib
		// string using webElement class
		WebElement USER_NAME_FIELD_ELEMENT = driver.findElement(By.xpath("//input[@id='username']"));
		WebElement PASSWORD_FIELD_ELEMENT = driver.findElement(By.xpath("//input[@id='password']"));
		WebElement SIGNIN_BUTTON_ELEMENT = driver.findElement(By.xpath("//button[@type='submit']"));

		// login data

		String loginId = "demo@techfios.com";
		String password = "abc123";

		USER_NAME_FIELD_ELEMENT.clear();
		USER_NAME_FIELD_ELEMENT.sendKeys(loginId);
		PASSWORD_FIELD_ELEMENT.sendKeys(password);
		SIGNIN_BUTTON_ELEMENT.click();

		Assert.assertEquals(driver.getTitle(), "Dashboard- iBilling", "Wrong Page");

	}

	@Test
	public void addCustomerTest() {

		// ELEMENT LIBRARY
		By USER_NAME_FIELD = By.name("username");
		By PASSWORD_FIELD = By.name("password");
		By SIGNIN_BUTTON = By.name("login");

		// By MENU_BUTTON = By.xpath("");
		// By DASHBOARD_BUTTON = By.xpath("");

		By CUSTOMERS_BUTTON = By.xpath("//span[text()='Customers']");
		By ADD_CUSTOMER_BUTTON = By.xpath("//a[text()='Add Customer']");

		By FULL_NAME_FIELD = By.xpath("//input[@id='account']");
		
		By COMPANY_NAME_FIELD = By.xpath("//select[@id='cid']");

	//	By EMAIL_FIELD = By.xpath("//input[@id='email']");
	//	By PHONE_FIELD = By.xpath("//input[@id='phone']");

//		driver.findElement(By.xpath("//input[@id='address']")).sendKeys("9244 Janet Rose Ct");
//		driver.findElement(By.xpath("//input[@id='city']")).sendKeys("Manassas");
//		driver.findElement(By.xpath("//input[@id='state']")).sendKeys("Virginia");
//		driver.findElement(By.xpath("//input[@id='zip']")).sendKeys("20111");
//		Thread.sleep(4000);

		// LOGIN DATA

		String loginId = "demo@techfios.com";
		String password = "abc123";

		// test data or mock data

		String fullName = "Test Jan";
		String conpanyName = "Techfios";
		String EmailAddress = "TestJan@gmail.com";
		String phoneNumber = "5712345690";

		// Log In

		Assert.assertEquals(driver.getTitle(), "Login - iBilling", "Wrong Page");
		driver.findElement(USER_NAME_FIELD).sendKeys(loginId);
		driver.findElement(PASSWORD_FIELD).sendKeys(password);
		driver.findElement(SIGNIN_BUTTON).click();
		Assert.assertEquals(driver.getTitle(), "Dashboard- iBilling", "Wrong Page");

		// add customer
		driver.findElement(CUSTOMERS_BUTTON).click();
		driver.findElement(ADD_CUSTOMER_BUTTON).click();
		
		
		//explicit wait
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(FULL_NAME_FIELD));

		// generate random number
		Random rnd = new Random();
		int generatedNo = rnd.nextInt(999);

		driver.findElement(FULL_NAME_FIELD).sendKeys(fullName + generatedNo);
		
		//driver.findElement(COMPANY_NAME_FIELD)
		
		Select sel = new Select(driver.findElement(COMPANY_NAME_FIELD));
		sel.selectByVisibleText(conpanyName);
		
		

		// driver.findElement(PHONE_FIELD)

	}

	//@AfterMethod
	public void tearDown() {
		driver.close();
		driver.quit();

	}
}
