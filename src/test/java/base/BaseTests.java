package base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;

import java.time.Duration;


public class BaseTests {
    private WebDriver driver;
    protected HomePage myHomePage;

    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        goHome();
        myHomePage = new HomePage(driver);
        myHomePage.acceptCookiesAlert();
    }

    @BeforeMethod
    public void goHome(){
        driver.get("https://www.fashionette.de/");

    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }

}
