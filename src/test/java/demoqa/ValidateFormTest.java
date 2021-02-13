package demoqa;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ValidateFormTest {

    @Test
    void dataAppearsInOutputBlockTest () {
        open("https://demoqa.com/text-box");

        $("#userName").setValue("Alex");
        $("#userEmail").setValue("yasha@dd.com");
        $("#currentAddress").setValue("Taganrog");
        $("#submit").click();

        sleep(5000);
        $("#output").shouldHave(text("Alex"), text("yasha@dd.com"));


    }
}
