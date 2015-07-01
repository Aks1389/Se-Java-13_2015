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

public class AddFilm extends selenium.tests.pages.TestBase {
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Test
  public void testAddFilm() throws Exception {
    driver.findElement(By.xpath("//div[@id='content']/section/nav//a[contains(@href,\"?go=add\")]")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.id("imdbsearchform"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    String movie_name = "Interstellar";
    driver.findElement(By.name("name")).clear();
    driver.findElement(By.name("name")).sendKeys(movie_name);
    driver.findElement(By.name("year")).clear();
    driver.findElement(By.name("year")).sendKeys("2014");
    driver.findElement(By.name("duration")).clear();
    driver.findElement(By.name("duration")).sendKeys("180");
    driver.findElement(By.id("own_no")).click();
    driver.findElement(By.id("seen_no")).click();
    driver.findElement(By.id("loaned_no")).click();
    driver.findElement(By.id("submit")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.xpath("//div[@class='content']//div[@class='maininfo_full']/h2"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    try {
      assertTrue(driver.findElement(By.xpath("//div[@class='content']//div[@class='maininfo_full']/h2")).getText().matches("^\\$\\{movie_name\\}[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
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
