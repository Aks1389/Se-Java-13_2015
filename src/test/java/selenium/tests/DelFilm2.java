package selenium.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.*;
import org.testng.annotations.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class DelFilm2 extends selenium.tests.pages.TestBase{
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	
	@Test
	public void testDelFilm2() throws Exception {
		driver.findElement(By.xpath("//header//nav//a[contains(text(), \"Home\")]")).click();
		waitEndOfLoading(By.xpath("//div[@id=\"results\"]"));
		
		//
		driver.findElement(By.xpath("//div[@id = \"results\"]//div[@class=\"movie_box\"]")).click();
		waitEndOfLoading(By.xpath("/div[@class='content']//div[@class='maininfo_full']/h2"));
		
		driver.findElement(By.xpath("//div[@id='content']/section/nav//a[contains(@onclick, \"delete\")]")).click();
	    assertTrue(closeAlertAndGetItsText().matches("^[\\s\\S]*$"));
	}

	private void waitEndOfLoading(By expected_obj) throws InterruptedException {
		for (int second = 0;; second++) {
			if (second >= 15)
				fail("timeout");
			try {
				if (isElementPresent(expected_obj))
					break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}
	}
	
	private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }
}
