import PageObjects.MailBoxPage;
import PageObjects.MainPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
public class Utils {

    enum Browsers {CHROME, FIREFOX}

    public static void createNewDriver(Browsers browser) {

        WebDriver newDriver;

        switch (browser) {
            default:
            case CHROME:
                Configuration.browser = WebDriverRunner.CHROME;
                System.setProperty("webdriver.chrome.driver", "C:/Users/Artem/Desktop/qlab/chromedriver.exe");

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--window-size=1920,1080");

                newDriver = new ChromeDriver(options);

                break;
        }

        WebDriverRunner.setWebDriver(newDriver);
    }

    public static void signIn() {

        open(MainPage.mainPageUrl);

        if (!MainPage.authUserField.isDisplayed()) {

            MainPage.typeLoginAndPassword("qlab_test@mail.ru", "12345A");
            MainPage.signInButton.click();

            MailBoxPage.authUserField.waitUntil(appear, 10000);
        }
    }

    public static String generateRandomString(int lenght, String startsWithString) {
        String symbols = "1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
        StringBuilder result = new StringBuilder(startsWithString + "_");

        for(int i = 0; i < lenght; i++) {
            result.append(symbols.charAt((int) (Math.random() * symbols.length())));
        }
        return result.toString();
    }
}
