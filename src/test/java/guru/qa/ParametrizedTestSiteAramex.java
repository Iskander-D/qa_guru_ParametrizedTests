package guru.qa;

import guru.qa.data.Language;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ParametrizedTestSiteAramex extends TestBase {

    @EnumSource(Language.class)
    @ParameterizedTest(name = "Проверка изменение строки с текстом при смене языка")
    void webSiteShouldHaveCorrectText(Language language) {
        open("https://www.aramex.com/ae/en/home");
        $$(".nav-item").find(text(language.name())).click();
        $(".sas-lbl-h").shouldHave(text(language.description));
    }

}