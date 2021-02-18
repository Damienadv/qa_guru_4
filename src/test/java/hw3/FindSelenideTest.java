//1. Есть ли разница между $("h1 div"); и $("h1").$("div"); - может ли привести к тому что, поиск найдёт разные элементы?
// Если может - приведите пример, когда.

//Ответ:
//$("h1 div") - найдет элемент h1, в котором будет содержаться тэги и h1 и div
//$("h1").$("div") тэг h1 будет родительским, тэг div - дочерним


package hw3;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class FindSelenideTest {
    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }

    @Test
    void shouldFindSelenideInGitHub() {
        // Откройте страницу Selenide в Github
        Selenide.open("https://github.com/selenide/selenide");
        //- Перейдите в раздел Wiki проекта
        $(byText("Wiki")).click();
        //- Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
        $$(".markdown-body ul li").filterBy(text("Soft Assertions")).shouldHaveSize(1);
        //- Откройте страницу SoftAssertions, проверьте что внутри есть пример кода для JUnit5
        $$(".markdown-body ul li a").findBy(text("Soft Assertions")).click();
        $(".page").shouldHave(text("Using JUnit5 extend test class"));
    }
}
