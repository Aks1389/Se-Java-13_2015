package selenium.tests;

import java.util.regex.Pattern;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.testng.*;
import org.testng.annotations.*;

import com.google.common.base.Verify;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class SearchFilm extends selenium.tests.pages.TestBase {
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Test
	public void testSearchFilmNegative() throws Exception {
		driver.get(baseUrl + "/php4dvd/");
		WebElement search_field = driver.findElement(By.xpath("//div[@id='content']/section/nav/div[@id='search']//input[@id='q']"));
		search_field.click();
		search_field.clear();
		search_field.sendKeys("Hobbit");
		search_field.sendKeys(Keys.ENTER);
		
		for (int second = 0;; second++) {
			if (second >= 60)
				fail("timeout");
			try {
				if (isElementPresent(By.xpath("//div[@id='results']/div[contains(text(), \"No movies where found.\")]")))
					break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}

	}

	@Test
	public void testSearchFilmPositive() throws Exception {
		driver.get(baseUrl + "/php4dvd/");
		int film_count = driver.findElements(By.xpath("//div[@id='results']//div[@class='movie_box']")).size();
		int film_countAfter;
		WebElement search_field = driver.findElement(By.xpath("//div[@id='content']/section/nav/div[@id='search']//input[@id='q']"));
		search_field.click();
		search_field.clear();
		search_field.sendKeys("Interstellar");
		search_field.sendKeys(Keys.ENTER);
		
		for (int second = 0;; second++) {
			if (second >= 60)
				fail("timeout");
			try {
				if (isElementPresent(By.xpath("//div[@id='results']/a//div[@class='title']/self::div[contains(text(),\"Interstellar\")]")))
					break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}
		Thread.sleep(3000);
		film_countAfter = driver.findElements(By.xpath("//div[@id='results']//div[@class='movie_box']")).size();
		//check1
		//assertTrue("Count of films has not changed", film_count > film_countAfterSearch);
		try {
			assertNotEquals(film_count, film_countAfter);
		} catch (Exception e) {
			verificationErrors.append(e.toString());
		}
		
		
		//check2
		//List<WebElement> fMovies = driver.findElements(By.xpath("//div[@id='results']//div[@class='movie_box']"));
		List<WebElement> fMovies = driver.findElements(By.xpath("//div[@id='results']//div[@class='movie_box']//div[@class='title']"));
		String title;
		for (WebElement movieBox : fMovies) {
			//title = movieBox.findElement(By.xpath("//div[@class='title']")).getText();
			 try {
				//assertTrue(movieBox.findElement(By.xpath("//div[@class='title']")).getText().contains("Interstellar"));
				 assertTrue(movieBox.getText().contains("Interstellar"));
			} catch (Exception e) {
				verificationErrors.append(e.toString());
			}
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
