import PageObjects.MailBoxPage;
import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;
import org.testng.annotations.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
public class ComposeEmail {

    @DataProvider(name = "emailData")
    public Object[][] emailData() {

        return new Object[][]{
                {"qlab_test@mail.ru", "", Utils.generateRandomString(15, "Body")},
                {"qlab_test@mail.ru", Utils.generateRandomString(20, "Subject"),
                        Utils.generateRandomString(150, "Body")}
        };
    }

    @BeforeClass
    @Parameters("browser")
    public void beforeClass(String browser) {
        Utils.createNewRemoteDriver(browser);
        Utils.signIn();
    }

    @Test(priority = 1, dataProvider = "emailData")
    public void composeAndSendEmail(String emailsTo, String subject, String body) {
        SelenideElement emailLink;

        open(MailBoxPage.mailBoxPageUrl);
        MailBoxPage.composeButton
                .should(appear)
                .click();

        MailBoxPage.typeEmailsTo(emailsTo);
        MailBoxPage.typeSubject(subject);
        MailBoxPage.typeBody(body);

        MailBoxPage.sendEmailButton.click();

        $(MailBoxPage.sendMessageStatus)
                .should(appear)
                .shouldHave(text("Ваше письмо отправлено."));

        open(MailBoxPage.sentEmailsUrl);
        emailLink = MailBoxPage.findEmailInList(subject, body);
        Assert.assertTrue(emailLink.isDisplayed(),
                String.format("Email с темой %s и содержанием %s не найден в отправленных", subject, body));
    }

    @AfterClass
    public void afterClass() {

        close();
    }
}