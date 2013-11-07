import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PopupDetect {

	private ChromeDriver driver;

	@Before
	public void setup(String[] args) {
		String p = "c:\\Users\\user\\Downloads\\chromedriver\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", p);

		driver = new ChromeDriver();
	}

	@Test
	public void popup검사() {
		driver.get("http://localhost:80/root/home");

		final Set<String> oldHandles = driver.getWindowHandles();

		driver.findElement(By.linkText("newwin")).click();

		new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return !oldHandles.equals(driver.getWindowHandles());
			}

			public String toString() {
				return "new popup window";
			}
		});

		driver.quit();
	}
}
