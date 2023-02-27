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
        //verify success navigation to the home page
        assertEquals(myHomePage.getLoginTitle(),"fashionette | Designer Handtaschen, Schuhe, Accessoires & Beauty online kaufen","Incorrect Home Page title");
        LoginPage loginPage = myHomePage.clickUserLoginIcon();
        //verify success navigation to the login page
        assertEquals(loginPage.getLoginTitle(),"Designertaschen und Accessoires | fashionette","Incorrect login page title");
        loginPage.enterEmail("QA@fashionette.de");
        //verify email format validation
        assertTrue(loginPage.getValidEmailTick(),"Invalid email format!");
        loginPage.enterPassword("!8Ntr*BM@!#G3VH");
        //verify email format validation
        assertTrue(loginPage.getValidPasswordTick(),"Invalid password format!");
        CustomerAccountPage customerAccountPage = loginPage.clickLogin();
        //verify customer welcome message
        assertTrue(customerAccountPage.getWelcomeCustomerMessage().contains("Hallo john! Willkommen in Deinem Kundenkonto."),"Incorrect customer welcome message");
    }
}
