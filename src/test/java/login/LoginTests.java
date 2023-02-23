package login;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.CustomerAccountPage;
import pages.LoginPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTests extends BaseTests {
    @Test
    public void testSuccessfulLogin(){
        assertEquals(myHomePage.getLoginTitle(),"fashionette | Designer Handtaschen, Schuhe, Accessoires & Beauty online kaufen","Incorrect Home Page");
        LoginPage loginPage = myHomePage.clickUserLoginIcon();
        assertEquals(loginPage.getLoginTitle(),"Designertaschen und Accessoires | fashionette","Incorrect login page title");
        loginPage.enterEmail("QA@fashionette.de");
        loginPage.enterPassword("!8Ntr*BM@!#G3VH");
        CustomerAccountPage customerAccountPage = loginPage.clickLogin();
        assertTrue(customerAccountPage.getWelcomeCustomerMessage().contains("Hallo john!"),"Incorrect Login Success Message");
    }
}
