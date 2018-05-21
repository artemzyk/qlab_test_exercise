import PageObjects.MailBoxPage;
import PageObjects.MainPage;
import org.testng.Assert;
import org.testng.annotations.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
public class Login {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {

        return new Object[][]{
                {"qlab_test@mail.ru", "12345A"}
        };
    }

    @BeforeClass
    public void beforeClass() {
        Utils.createNewDriver(Utils.Browsers.CHROME);
    }

    @BeforeMethod
    public void beforeMethod() {
        clearBrowserCookies();
    }

    @Test(priority = 1, dataProvider = "loginData")
    public void signInOnMainPage(String mail, String password) {

        open(MainPage.mainPageUrl);

        MainPage.typeLoginAndPassword(mail, password);
        MainPage.signInButton.click();

        MailBoxPage.authUserField.waitUntil(appear, 10000);
        Assert.assertEquals(MailBoxPage.getAuthUserMail(),mail);
    }

    @AfterClass
    public void afterClass() {

        close();
    }
}
