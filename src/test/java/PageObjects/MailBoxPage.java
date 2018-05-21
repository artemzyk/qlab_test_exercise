package PageObjects;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
public class MailBoxPage {

    public static final String mailBoxPageUrl = "https://e.mail.ru/messages";
    public static final String sentEmailsUrl  = "https://e.mail.ru/messages/sent/";

    public static final SelenideElement authUserField     = $("#PH_user-email"),
                                        composeButton     = $("a[data-name=\"compose\"]"),
                                        sendEmailButton   = $("div[data-name=\"send\"]"),
                                        sendMessageStatus = $("div.message-sent__title");
    private static final SelenideElement emailsToTextarea = $("textarea[data-original-name=\"To\"]"),
                                         subjectInput     = $("input[name=\"Subject\"]"),
                                         bodyEditorIframe = $("iframe[id*=\"composeEditor\"]"),
                                         bodyEditorBody   = $("#tinymce"),
                                         emailRowInList   = $("div[data-bem=\"b-datalist__item\"]");
    private static final String subjectWithBodyPreviewInListSelector = "div.b-datalist__item__subj",
                                bodyPreviewInListSelector            = "span.b-datalist__item__subj__snippet",
                                emailLinkInListSelector              = ".b-datalist__item__link";

    private static final int maxBodyLengthInList = 100;

    public static String getAuthUserMail() {

        authUserField.should(appear);

        return authUserField.getText().trim();
    }

    public static void typeEmailsTo(String emails) {

        emailsToTextarea
                .should(appear)
                .sendKeys(emails);
    }

    public static void typeSubject(String subject) {

        subjectInput
                .should(appear)
                .sendKeys(subject);
    }

    public static void typeBody(String body) {

        switchTo().frame(bodyEditorIframe);
        $(bodyEditorBody)
                .should(appear)
                .clear();
        $(bodyEditorBody).sendKeys(body);
        switchTo().defaultContent();
    }

    public static SelenideElement findEmailInList(String subject, String body) {

        int lengthLimit = body.length() < maxBodyLengthInList ? body.length() : maxBodyLengthInList;

        emailRowInList.should(appear);

        if (subject.trim().length() > 0)
            return $$(subjectWithBodyPreviewInListSelector)
                    .findBy(exactText(subject + body.substring(0, lengthLimit))).closest(emailLinkInListSelector);
        else
            return $$(bodyPreviewInListSelector)
                    .findBy(exactText(body.substring(0, lengthLimit))).closest(emailLinkInListSelector);
    }
}
