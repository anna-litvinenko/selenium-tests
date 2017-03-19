package test.anna;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by Anna on 3/18/17.
 */
public class PhpTravelsTests {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "geckodriver/geckodriver");
        driver = new FirefoxDriver();
        String appUrl = "http://phptravels.net";
        driver.get(appUrl);
        driver.manage().window().maximize();
    }

    @Test
    public void testCityName() {
        String city = "Seattle";
        searchCity(city);
        WebElement locationSection = driver.findElement(By.xpath("//div[small[text()='Location']]"));
        String location = locationSection.findElement(By.tagName("span")).getText();
        assertThat(location, equalTo(city));
    }

    @Test
    public void testDefaultNumberOfAdults() {
        searchCity("Seattle");
        WebElement adultsSection = driver.findElement(By.xpath("//div[small[text()='Adults']]"));
        String numberOfAdults = adultsSection.findElement(By.tagName("span")).getText();
        assertThat(numberOfAdults, equalTo("2"));
    }

    @Test
    public void testDefaultNumberOfChildren() {
        searchCity("Seattle");
        WebElement childSection = driver.findElement(By.xpath("//div[small[text()='Child']]"));
        String numberOfChildren = childSection.findElement(By.tagName("span")).getText();
        assertThat(numberOfChildren, equalTo("0"));
    }

    private void searchCity(String city) {
        WebElement searchBox = driver.findElement(By.id("citiesInput"));
        searchBox.clear();
        searchBox.sendKeys(city);
        WebElement searchButton = driver.findElement(By.xpath("//button[contains(text(),'Search')]"));
        searchButton.click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }


    @After
    public void tearDown() {
        driver.quit();
    }
}
