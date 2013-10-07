import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Dancing9MenuTraversal {

	private ChromeDriver driver;
	private Actions actions;

	public Dancing9MenuTraversal() {
		String p = "c:\\Users\\user\\Downloads\\chromedriver\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", p);

		driver = new ChromeDriver();
	}

	public void run() {
		actions = new Actions(driver);

		driver.get("http://dancing9.interest.me/index.m");
		메인페이지를_로딩한다();

		Vote메뉴_Vote를_클릭한다();
		Vote메뉴_SoloStage를_클릭한다();

		About메뉴_WinnersPrize를_클릭한다();
		About메뉴_Audition을_클릭한다();
		About메뉴_MCIntroduction을_클릭한다();

		팀메뉴_블루아이vs레드윙스를_클릭한다();
		팀메뉴_마스터를_클릭한다();
		팀메뉴_멤버링크를_클릭한다();

		Video메뉴_Video를_클릭한다();
		Video메뉴_Clip를_클릭한다();
		Video메뉴_Preview를_클릭한다();

		게시판메뉴_NotiveViewersOpinion를_클릭한다();
		게시판메뉴_News를_클릭한다();

		More메뉴_DanceTerms를_클릭한다();
		More메뉴_Webtoon을_클릭한다();
		More메뉴_Sponsor를_클릭한다();
		driver.close();
	}

	private void Vote메뉴_Vote를_클릭한다() {
		Vote메뉴에_마우스를_가져다놓는다();
		moveAndClick(By.xpath("//*[@id=\"navi\"]/div/ul/li[1]/div/ul/li[1]/a"));
		assertTextInElement("con_header", "Vote");
	}

	private void Vote메뉴_SoloStage를_클릭한다() {
		Vote메뉴에_마우스를_가져다놓는다();
		moveAndClick(By.xpath("//*[@id=\"navi\"]/div/ul/li[1]/div/ul/li[2]/a"));
		assertTextInElement("con_header", "Solo Stage Final Voting");
	}

	private void About메뉴_WinnersPrize를_클릭한다() {
		About메뉴에_마우스를_가져다놓는다();
		moveAndClick(By.xpath("//*[@id=\"navi\"]/div/ul/li[2]/div/ul/li[1]/a"));
		assertTextInElement("con_header", "Winner's Prize");
	}

	private void About메뉴_Audition을_클릭한다() {
		About메뉴에_마우스를_가져다놓는다();
		moveAndClick(By.xpath("//*[@id=\"navi\"]/div/ul/li[2]/div/ul/li[2]/a"));
		assertTextInElement("con_header", "How the Audition Works");
	}

	private void About메뉴_MCIntroduction을_클릭한다() {
		About메뉴에_마우스를_가져다놓는다();
		moveAndClick(By.xpath("//*[@id=\"navi\"]/div/ul/li[2]/div/ul/li[3]/a"));
		assertTextInElement("con_header", "MC Introduction");
	}


	private void 팀메뉴_블루아이vs레드윙스를_클릭한다() {
		팀메뉴에_마우스를_가져다놓는다();
		moveAndClick(By.xpath("//*[@id=\"navi\"]/div/ul/li[3]/div/ul/li[1]/a"));
		assertTextInElement("con_header", "Team");
	}

	private void 팀메뉴_마스터를_클릭한다() {
		팀메뉴에_마우스를_가져다놓는다();
		moveAndClick(By.xpath("//*[@id=\"navi\"]/div/ul/li[3]/div/ul/li[2]/a"));
		assertTextInElement("con_header", "Master");
	}

	private void 팀메뉴_멤버링크를_클릭한다() {
		팀메뉴에_마우스를_가져다놓는다();
		moveAndClick(By.xpath("//*[@id=\"navi\"]/div/ul/li[3]/div/ul/li[3]/a"));
		assertTextInElement("con_header", "Member");
	}

	private void Video메뉴_Video를_클릭한다() {
		Video메뉴에_마우스를_가져다놓는다();
		moveAndClick(By.xpath("//*[@id=\"navi\"]/div/ul/li[4]/div/ul/li[1]/a"));
		assertTextInElement("con_header", "Video");
	}

	private void Video메뉴_Clip를_클릭한다() {
		Video메뉴에_마우스를_가져다놓는다();
		moveAndClick(By.xpath("//*[@id=\"navi\"]/div/ul/li[4]/div/ul/li[2]/a"));
		assertTextInElement("con_header", "Clip");
	}

	private void Video메뉴_Preview를_클릭한다() {
		Video메뉴에_마우스를_가져다놓는다();
		moveAndClick(By.xpath("//*[@id=\"navi\"]/div/ul/li[4]/div/ul/li[3]/a"));
		assertTextInElement("con_header", "Preview");
	}

	private void 게시판메뉴_NotiveViewersOpinion를_클릭한다() {
		게시판메뉴에_마우스를_가져다놓는다();
		moveAndClick(By.xpath("//*[@id=\"navi\"]/div/ul/li[5]/div/ul/li[1]/a"));
		assertTextInElement("con_header", "Opinion");
	}

	private void 게시판메뉴_News를_클릭한다() {
		게시판메뉴에_마우스를_가져다놓는다();
		moveAndClick(By.xpath("//*[@id=\"navi\"]/div/ul/li[5]/div/ul/li[2]/a"));
		assertTextInElement("con_header", "News");
	}

	private void More메뉴_DanceTerms를_클릭한다() {
		More메뉴에_마우스를_가져다놓는다();
		moveAndClick(By.xpath("//*[@id=\"navi\"]/div/ul/li[6]/div/ul/li[1]/a"));
		assertTextInElement("con_header", "Dictionary of Dance Terms");
	}

	private void More메뉴_Webtoon을_클릭한다() {
		More메뉴에_마우스를_가져다놓는다();
		moveAndClick(By.xpath("//*[@id=\"navi\"]/div/ul/li[6]/div/ul/li[2]/a"));
		new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
		// alert을 닫는다.
		try {
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
		}
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	private void More메뉴_Sponsor를_클릭한다() {
		More메뉴에_마우스를_가져다놓는다();
		moveAndClick(By.xpath("//*[@id=\"navi\"]/div/ul/li[6]/div/ul/li[3]/a"));
		assertTextInElement("con_header", "Sponsor");
	}


	private void Vote메뉴에_마우스를_가져다놓는다() {
		moveToElement(By.xpath("//*[@id=\"navi\"]/div/ul/li[1]/a"));
	}

	private void About메뉴에_마우스를_가져다놓는다() {
		moveToElement(By.xpath("//*[@id=\"navi\"]/div/ul/li[2]/a"));
	}

	private void 팀메뉴에_마우스를_가져다놓는다() {
		moveToElement(By.xpath("//*[@id=\"navi\"]/div/ul/li[3]/a"));
	}

	private void Video메뉴에_마우스를_가져다놓는다() {
		moveToElement(By.xpath("//*[@id=\"navi\"]/div/ul/li[4]/a"));
	}

	private void 게시판메뉴에_마우스를_가져다놓는다() {
		moveToElement(By.xpath("//*[@id=\"navi\"]/div/ul/li[5]/a"));
	}

	private void More메뉴에_마우스를_가져다놓는다() {
		moveToElement(By.xpath("//*[@id=\"navi\"]/div/ul/li[6]/a"));
	}

	private void moveToElement(By by) {
		actions.moveToElement(driver.findElement(by));
	}

	private void 메인페이지를_로딩한다() {
		new WebDriverWait(driver, 10).until(ExpectedConditions.titleContains("Dancing 9"));
	}

	public static void main(String[] args) {
		new Dancing9MenuTraversal().run();
	}

	private void moveAndClick(By by) {
		WebElement el = driver.findElement(by);
		actions.moveToElement(el);
		actions.click();
		actions.perform();
	}

	private void assertElement(By partialLinkText) {
		WebElement el = driver.findElement(partialLinkText);
		if (el == null) {
			throw new RuntimeException("not found");
		}
	}

	private void assertTextInElement(String css_class, String v) {
		new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElement(By.className(css_class), v));
	}

}
