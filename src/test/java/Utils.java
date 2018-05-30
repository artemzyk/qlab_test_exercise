import PageObjects.MailBoxPage;
import PageObjects.MainPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
public class Utils {

    enum Browsers {CHROME, FIREFOX}

    private final static String CHROME_BROWSER_VERSION = "67.0";
    private final static String FIREFOX_BROWSER_VERSION = "60.0";

    public static void createNewDriver(Browsers browser) {

        WebDriver newDriver;

        switch (browser) {
            default:
            case CHROME:
                Configuration.browser = WebDriverRunner.CHROME;
                System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--window-size=1920,1080");

                newDriver = new ChromeDriver(options);

                break;
        }

        WebDriverRunner.setWebDriver(newDriver);
    }

    public static void createNewRemoteDriver(String browser) {
        RemoteWebDriver driver = null;

        DesiredCapabilities capabilities = new DesiredCapabilities();

        switch (browser) {
            default:
            case "chrome":
                capabilities.setBrowserName("chrome");
                capabilities.setVersion(CHROME_BROWSER_VERSION);
                break;
            case "firefox":
                capabilities.setBrowserName("firefox");
                capabilities.setVersion(FIREFOX_BROWSER_VERSION);
                break;
        }

        try {
            driver = new RemoteWebDriver(URI.create("http://192.168.99.100:4444/wd/hub").toURL(),capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().window().setSize(new Dimension(1920, 1080));
        WebDriverRunner.setWebDriver(driver);

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
