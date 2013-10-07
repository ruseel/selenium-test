package t;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Aug17VersionTest extends ChromeWebdriverTest {

	static final String URL_MAIN_PAGE = "http://local.dancing9.interest.me:9090/index.m?langCD=ENG";
	static final String URL_NOTICE_PAGE = "http://local.dancing9.interest.me:9090/board/list.m?langCD=ENG";

	@Test
	public void 팀메뉴_에서_Hover하고_Member클릭하면_alert없이_움직이기() {
		Actions actions;

		loadMainPage();

		By _1depth = By.xpath("//*[@id=\"navi\"]/div/ul/li[3]/a");
		By _2depth_member = By.xpath("//*[@id=\"navi\"]/div/ul/li[3]/div/ul/li[3]/a");

		actions = new Actions(driver);
		actions.moveToElement(driver.findElement(_1depth));

		actions.moveToElement(driver.findElement(_2depth_member));
		actions.click();
		actions.perform();

		assertConHeader("Member");
	}

	@Test
	public void VOTE를_클릭하면_alert없이_움직인다() {
		loadMainPage();

		By _1depth = By.xpath("//*[@id=\"navi\"]/div/ul/li[1]/a");
		WebElement el = driver.findElement(_1depth);
		el.click();

		assertConHeader("Vote");
	}

	/**
	 * 1000을 넘어가는 순간 숫자가 , 로 포맷팅 되어 나와서 에러가 나는 경우가 있었다. 
	 */
	@Test
	public void 공지게시판에서_글을_클릭하면_상세페이지로_움직인다() {
		assertPageLoaded(URL_NOTICE_PAGE);

		By detailLink = By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr[1]/td[2]/a");
		WebElement el = driver.findElement(detailLink);
		el.click();

		assertConHeader("Notice");
	}

	@Test
	public void 뉴스링크도_잘_뜬다() {
		loadMainPage();

		By _1depth = By.xpath("//*[@id=\"navi\"]/div/ul/li[5]/a");
		By _2depth_member = By.xpath("//*[@id=\"navi\"]/div/ul/li[5]/div/ul/li[2]/a");

		Actions actions;
		actions = new Actions(driver);
		actions.moveToElement(driver.findElement(_1depth));

		actions.moveToElement(driver.findElement(_2depth_member));
		actions.click();
		actions.perform();

		assertConHeader("News");
	}

	public void loadMainPage() {
		assertPageLoaded(URL_MAIN_PAGE);
	}

	public void assertConHeader(String con_header_value) {
		new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElement(By.className("con_header"),
				con_header_value));
	}
}

