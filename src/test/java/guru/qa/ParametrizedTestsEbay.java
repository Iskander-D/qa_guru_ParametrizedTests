package guru.qa;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ParametrizedTestsEbay extends TestBase {

    @BeforeEach
    void setUp() {
        open("https://www.ebay.com/");
    }

    @ValueSource(strings = {
            "Iphone 15 pro",
            "Porsche 911"
    })
    @ParameterizedTest(name = "Для каждого поискового запроса {0} должен отдаваться не пустой список")
    void successfulSearchTest(String searchQuery) {
        $("#gh-ac").setValue(searchQuery).pressEnter();
        $$("#srp-river-results").shouldBe(
                CollectionCondition.sizeGreaterThan(0));
    }

    @CsvFileSource(resources = "/testData/searchResultShouldContainExpectedText.csv", delimiter = '|')
    @DisplayName("Тест на проверку необхдимых значений в поисковой строке")
    @ParameterizedTest(name = "Для поискового запроса {0} есть карточка с именем {1}")
    @Tag("SMOKE")
    void searchResultShouldContainText(String searchQuery, String expectedName) {
        $("#gh-ac").setValue(searchQuery).pressEnter();
        $("#srp-river-results").shouldHave(text(expectedName));
    }

}
