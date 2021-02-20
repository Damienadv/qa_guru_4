package demoqa;

import com.codeborne.selenide.CollectionCondition;

import static com.codeborne.selenide.CollectionCondition.texts;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.github.javafaker.Faker;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class ValidateFormTest {
    
    //add annotation for size
    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }

    @Test
    void dataAppearsInOutputBlockTest() {

        Faker faker = new Faker();

        String firstname = faker.name().firstName(),
                lastname = faker.name().lastName(),
                useremail = faker.internet().emailAddress("test"),
                gender = "Male",
                usernumber = faker.phoneNumber().subscriberNumber(10),
                day = "30",
                month = "March",
                year = "2015",
                subjectOne = "Maths",
                subjectTwo = "English",
                picture = "qa.jpg",
                address = faker.address().fullAddress(),
                state = "Rajasthan",
                city = "Jaipur",
                hobby1 = "Sports",
                hobby2 = "Music";


        open("https://demoqa.com/automation-practice-form");
        $(".main-header").shouldHave(text("Practice Form"));

        //act
        $("#firstName").setValue(firstname);
        $("#lastName").setValue(lastname);
        $("#userEmail").setValue(useremail);
        $$(".custom-control-label").find(text(gender)).click();
        $("#userNumber").setValue(usernumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__day--0" + day).click();
        $("#subjectsInput").setValue(subjectOne).pressEnter();
        $("#subjectsInput").setValue(subjectTwo).pressEnter();

        $(byText(hobby1)).click();
        $(byText(hobby2)).click();

        $("#uploadPicture").uploadFromClasspath(picture);

        $("#currentAddress").setValue(address);
        $("#state").scrollTo().click();

        $(byText(state)).click();
        $("#city").click();
        $(byText(city)).click();

        $("#submit").click();
        
        //add test for forms title
        $(".modal-header").shouldHave(text("Thanks for submitting the form"));

        //assert
        ElementsCollection elements = $$(".table-responsive tr");
        elements.filterBy(text("Student Name")).shouldHave(texts(firstname + " " + lastname));
        elements.filterBy(text("Student Email")).shouldHave(texts(useremail));
        elements.filterBy(text("Gender")).shouldHave(texts(gender));
        elements.filterBy(text("Mobile")).shouldHave(texts(usernumber));
        elements.filterBy(text("Date of Birth")).shouldHave(texts(day + " " + month + "," + year));
        elements.filterBy(text("Student Email")).shouldHave(texts(useremail));
        elements.filterBy(text("Subjects")).shouldHave(texts(subjectOne), texts(subjectTwo));
        elements.filterBy(text("Hobbies")).shouldHave(texts("Sports"), texts("Music"));
        elements.filterBy(text("Picture")).shouldHave(texts(picture));
        elements.filterBy(text("Address")).shouldHave(texts(address));
        elements.filterBy(text("State and City")).shouldHave(texts(state + " " + city));
        
        //add action close and test title after that
        $("#closeLargeModal").click();
        $(".main-header").shouldHave(text("Practice Form"));
        
        //or if you use hamcrest
        assertThat($(".main-header").getText(), containsString("Practice Form"));
    }
}
