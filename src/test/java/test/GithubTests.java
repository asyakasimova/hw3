package test;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class GithubTests {
    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }

    @Test
    void checkSelenideGithubTest() {
        // Откройте страниц Selenide в Github
        Selenide.open("https://github.com/");
        $("[name=q]").setValue("selenide").pressEnter();
        $("ul.repo-list li a").click();
        $("h1").shouldHave(text("selenide / selenide"));
        // Перейдите в раздел Wiki проекта
        $(byText("Wiki")).click();
        $("#user-content-welcome-to-the-selenide-wiki").shouldBe(visible);
        // Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
        $$(".internal").shouldHave(itemWithText("Soft assertions"));

        // Откройте страницу SoftAssertions, проверьте что внутри есть пример кода для JUnit5
        $(byText("Soft assertions")).click();
        $("#wiki-wrapper").shouldHave(text("SoftAssertions"));
        $$("ol").first().shouldHave(text("JUnit5 extension"));
    }

}
