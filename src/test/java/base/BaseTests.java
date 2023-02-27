package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.CustomerAccountPage;
import pages.HomePage;
import pages.LoginPage;

import java.time.Duration;


public class BaseTests {
    private WebDriver driver;
    protected HomePage myHomePage;

    @BeforeClass
    public void setUp(){
        //if Windows
        //System.setProperty("webdriver.chrome.driver","resources/chromedriver.exe");
        //if Mac
        System.setProperty("webdriver.chrome.driver","resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
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

    /**
     * This method is to log-in
     * @return CustomerAccountPage object
     */
    public CustomerAccountPage login(){
        LoginPage loginPage = myHomePage.clickUserLoginIcon();
        loginPage.enterEmail("QA@fashionette.de");
        loginPage.enterPassword("!8Ntr*BM@!#G3VH");
        return loginPage.clickLogin();
    }

}
