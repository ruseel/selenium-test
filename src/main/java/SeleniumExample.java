import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SeleniumExample {

	private ChromeDriver driver;

	public SeleniumExample() {
		String p = "c:\\Users\\user\\Downloads\\chromedriver\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", p);
		
		driver = new ChromeDriver();
	}

	public void run() {
		driver.get("http://beta.dancing9.interest.me/index.m?langCd=ENG");
		
		new WebDriverWait(driver, 10).until(ExpectedConditions.titleContains("Dancing 9"));
		assertElement(By.partialLinkText("투표하기"));
		assertElement(By.partialLinkText("소개"));
		assertElement(By.partialLinkText("프로그램 진행"));
		assertElement(By.partialLinkText("다시보기"));
		assertElement(By.partialLinkText("뉴스"));

		{
			WebElement element = driver.findElement(By.partialLinkText("뉴스"));
			element.click();
			new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElement(By.className("con_header"), "뉴스"));
		}

		{
			WebElement element = driver.findElement(By.partialLinkText("공지"));
			element.click();
			new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElement(By.className("con_header"), "시청자의견"));
		}

		{
			WebElement element = driver.findElement(By.partialLinkText("투표하기"));
			element.click();
			Alert alert = new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
			alert.dismiss();
		}

		{
			WebElement element = driver.findElement(By.partialLinkText("소개"));
			element.click();
			new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElement(By.className("con_header"), "소개"));
		}

		{
			WebElement element = driver.findElement(By.partialLinkText("프로그램 진행"));
			element.click();
			new WebDriverWait(driver, 10)
					.until(ExpectedConditions.textToBePresentInElement(By.className("con_header"), "프로그램 진행"));
		}

		driver.close();
	}

	public static void main(String[] args) {
		new SeleniumExample().run();
	}

	private void assertElement(By partialLinkText) {
		WebElement el = driver.findElement(partialLinkText);
		if (el == null) {
			throw new RuntimeException("not found");
		}
	}

}
