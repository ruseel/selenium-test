package t;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChromeWebdriverTest {
	protected static ChromeDriver driver;

	@BeforeClass
	public static void setUp() {
		String p = "c:\\Users\\user\\Downloads\\chromedriver\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", p);

		driver = new ChromeDriver();
	}

	@AfterClass
	public static void tearDown() {
		driver.close();
	}

	public void assertPageLoaded(String url) {
		driver.get(url);
		new WebDriverWait(driver, 10).until(ExpectedConditions.titleContains("Dancing 9"));
	}
}
