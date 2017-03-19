package test.anna;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.SystemClock;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * Created by Anna on 1/14/17.
 */
public class AmazonTests {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "geckodriver/geckodriver");
        driver = new FirefoxDriver();
        String appUrl = "https://www.amazon.com";
        driver.get(appUrl);
        driver.manage().window().maximize();
    }

    @Test
    public void testTitle() {
        String expectedTitle = "Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more";
        String actualTitle = driver.getTitle();
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void testPrice() {
        WebElement select = driver.findElement(By.id("searchDropdownBox"));
        Select dropdownSelect = new Select(select);
        dropdownSelect.selectByVisibleText("Baby");
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.clear();
        searchBox.sendKeys("Toys");
        WebElement searchButton = driver.findElement(By.id("nav-search-submit-text"));
        searchButton.click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebElement result = driver.findElement(By.partialLinkText("VTech Rhyme and Discover Book"));
        result.click();
        WebElement price = driver.findElement(By.id("priceblock_ourprice"));
        String priceString = price.getText();
        assertEquals("$15.99", priceString);//need to compare the actual price before executing. In case it's different - change the actual price
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
