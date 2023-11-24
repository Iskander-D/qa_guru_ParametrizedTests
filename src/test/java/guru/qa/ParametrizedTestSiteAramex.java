package guru.qa;

import guru.qa.data.Language;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class ParametrizedTestSiteAramex extends TestBase {

    @EnumSource(Language.class)
    @ParameterizedTest(name = "Проверка изменение строки с текстом при смене языка")
    void webSiteShouldHaveCorrectText(Language language) {
        open("https://www.aramex.com/ae/en/home");
        $$(".nav-item").find(text(language.name())).click();
        $(".sas-lbl-h").shouldHave(text(language.description));
    }
    static Stream<Arguments> webSiteShouldCorrectDisplayCorrectButtons() {
        return Stream.of(
                Arguments.of(
                        Language.ENGLISH,
                        List.of("Customer Support","Business Enquiries","Careers")),

                Arguments.of(
                        Language.العربية,
                        List.of("دعم العملاء", "الاستفسارات التجارية", "وظائف")));
    }


    @MethodSource
    @ParameterizedTest
    void webSiteShouldCorrectDisplayCorrectButtons(Language language, List<String> expectedButtons) {
        open("https://www.aramex.com/ae/en/home");
        $$(".nav-item").find(text(language.name())).click();
        $$("h6.customer-services-card-title").filter(visible).shouldHave(texts(expectedButtons));


    }
}