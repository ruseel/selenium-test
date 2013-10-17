import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;

import junit.framework.Assert;
import me.interest.sounddetect.RMSMeter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;

public class PlayTop1Song {
	FirefoxDriver wd;

	@Before
	public void setUp() throws Exception {
		wd = new FirefoxDriver();
		wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

	@Test
	public void playTop1Song() {
		wd.get("http://mnet.interest.me/");
		wd.findElement(By.id("top_userid")).click();
		wd.findElement(By.id("top_userid")).clear();
		wd.findElement(By.id("top_userid")).sendKeys("ruseel");
		wd.findElement(By.id("top_pass")).click();
		wd.findElement(By.id("top_pass")).clear();
		wd.findElement(By.id("top_pass")).sendKeys("xxxx");
		wd.findElement(By.id("top_login")).click();
		wd.findElement(By.linkText("챠트")).click();
		wd.findElement(By.linkText("내가 많이 변했어(With 최자 of 다이나믹듀오)")).click();
		
		RMSMeter m = new RMSMeter(AudioSystem.getMixerInfo()[5], getAudioFormat());
		Assert.assertTrue(m.measureWhile(10000, (float) 0.7));
	}

	private AudioFormat getAudioFormat() {
		float sampleRate = 8000.0F;
		int sampleSizeInBits = 8;
		int channels = 1;
		boolean signed = true;
		boolean bigEndian = true;
		return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
	}

	@After
	public void tearDown() {
		wd.quit();
	}

	public static boolean isAlertPresent(FirefoxDriver wd) {
		try {
			wd.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}
}