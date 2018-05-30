import org.testng.annotations.*;

import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public class TestWindowsSelenoid {

    @BeforeClass
    @Parameters("browser")
    public void beforeClass(String browser) {

        Utils.createNewRemoteDriver(browser);

    }


    @Test(priority = 1)
    public void testSelenoid1() {

        open("https://www.google.ru");

    }

    @Test(priority = 2)
    public void testSelenoid2() {

        open("https://www.yahoo.com");

    }

    @Test(priority = 3)
    public void testSelenoid3() {

        open("https://www.yandex.ru");

    }
    @AfterClass
    public void afterClass() {

        close();
    }
}
