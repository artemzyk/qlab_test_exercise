package PageObjects;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class MainPage {

    public static final String mainPageUrl = "http://www.mail.ru";

    public static final SelenideElement authUserField = $("#PH_user-email"),
                                        signInButton  = $("label[id=\"mailbox:submit\"]");
    private static final SelenideElement mailInput     = $("input[id=\"mailbox:login\"]"),
                                         passwordInput = $("input[id=\"mailbox:password\"]");

    public static void typeLoginAndPassword(String login, String password) {

        mailInput
                .should(appear)
                .sendKeys(login);
        passwordInput
                .should(appear)
                .sendKeys(password);

    }

}
