import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MultiFileUpload {

	private ChromeDriver driver;
	private Actions actions;

	public MultiFileUpload() {
		String p = "c:\\Users\\user\\Downloads\\chromedriver\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", p);

		driver = new ChromeDriver();
	}

	public void run() {
		actions = new Actions(driver);

		driver.get("http://localhost:9090/");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("id")));

		actions.moveToElement(driver.findElement(By.id("id")));
		actions.sendKeys(driver.findElement(By.id("id")), "munshik");
		actions.perform();
		actions.moveToElement(driver.findElement(By.id("enpw")));
		actions.sendKeys(driver.findElement(By.id("enpw")), "holiday4");
		actions.perform();

		actions.click(driver.findElement(By.xpath("//*[@id=\"wrap_main\"]/div[2]/div/p[3]/a")));
		actions.perform();
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("gnb_sub")));

		// MEET&GREET mchat 페이지로 이동
		driver.get("http://localhost:9090/backoffice/meetgreet/update.m?menuId=2&menuSubId=12&menuThirdId=0&mg_seq=6");
		Set<String> beforeWindows = driver.getWindowHandles();
		String orgWindow = beforeWindows.iterator().next();

		// 두번째 "가져오기" 클릭
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 10; j++) {
				WebElement btn = driver.findElement(By
						.xpath(String.format("//*[@id=\"page_1\"]/table/tbody/tr[8]/td[2]/table/tbody/tr/td[%d]/a[%d]", i, j)));
				upload(beforeWindows, btn);
				driver.switchTo().window(orgWindow);
			}
		}

		// 원 페이지에서 update 클릭
		driver.findElement(By.id("btn_update")).click();

		// driver.close();
		driver.quit();
	}

	private void upload(Set<String> beforeWindows, WebElement btn) {
		actions.click(btn);
		actions.perform();
		Set<String> afterWindows = driver.getWindowHandles();

		afterWindows.removeAll(beforeWindows);
		String newWindows = afterWindows.iterator().next();
		driver.switchTo().window(newWindows);

		// 새 창이 뜰 때까지 대기
		new WebDriverWait(driver, 10)
				.until(ExpectedConditions.textToBePresentInElement(By.cssSelector("p.pt20"), "업로드 파일을 선택하세요"));

		// 업로드 버튼 누르기
		driver.findElement(By.name("uploadFile")).sendKeys("c:\\Users\\user\\Pictures\\1506380000.jpg");
		driver.findElement(By.xpath("//*[@id=\"body\"]/form/div/div[4]/a")).click();
		sleep(5);

		// 첫 번째 요소가 나타날 때까지 대기
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By
				.xpath("//*[@id=\"body\"]/form/div/div[2]/table/tbody/tr[1]")));


		// 완료
		driver.findElement(By.xpath("//*[@id=\"body\"]/form/div/div[5]/a[2]")).click();
	}

	private void sleep(int sec) {
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
		}
	}

	public static void main(String[] args) {
		new MultiFileUpload().run();
	}

}
