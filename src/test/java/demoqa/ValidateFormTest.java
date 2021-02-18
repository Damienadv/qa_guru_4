package demoqa;

import com.codeborne.selenide.CollectionCondition;

import static com.codeborne.selenide.CollectionCondition.texts;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ValidateFormTest {

    private String firstName;
    private String lastName;
    private String userEmail;
    private String gender;
    private String userNumber;
    private String dayOfBirth;
    private String monthOfBirth;
    private String yearOfBirth;
    private String subjectOne;
    private String subjectTwo;
    private String picture;
    private String currentAddress;
    private String state;
    private String city;


    @Test
    void dataAppearsInOutputBlockTest() {

        firstName = "Nicky";
        lastName = "Junior";
        userEmail = "nicky@junior.com";
        gender = "Male";
        userNumber = "7908567890";
        dayOfBirth = "30";
        monthOfBirth = "March";
        yearOfBirth = "2015";
        subjectOne = "Maths";
        subjectTwo = "English";
        picture = "qa.jpg";
        currentAddress = "Taganrog";
        state = "Rajasthan";
        city = "Jaipur";


        open("https://demoqa.com/automation-practice-form");
        $(".main-header").shouldHave(text("Practice Form"));

        //act
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $$(".custom-control-label").find(text(gender)).click();
        $("#userNumber").setValue(userNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption(yearOfBirth);
        $(".react-datepicker__month-select").selectOption(monthOfBirth);
        $(".react-datepicker__day--0" + dayOfBirth).click();
        $("#subjectsInput").setValue(subjectOne).pressEnter();
        $("#subjectsInput").setValue(subjectTwo).pressEnter();

        $(byText("Sports")).click();
        $(byText("Music")).click();

        $("#uploadPicture").uploadFromClasspath(picture);

        $("#currentAddress").setValue(currentAddress);
        $("#state").click();
        $(byText(state)).click();
        $("#city").click();
        $(byText(city)).click();

        $("#submit").click();

        //assert
        ElementsCollection elements = $$(".table-responsive tr");
        elements.filterBy(text("Student Name")).shouldHave(texts(firstName + " " + lastName));
        elements.filterBy(text("Student Email")).shouldHave(texts(userEmail));
        elements.filterBy(text("Gender")).shouldHave(texts(gender));
        elements.filterBy(text("Mobile")).shouldHave(texts(userNumber));
        elements.filterBy(text("Date of Birth")).shouldHave(texts(dayOfBirth + " " + monthOfBirth + "," + yearOfBirth));
        elements.filterBy(text("Student Email")).shouldHave(texts(userEmail));
        elements.filterBy(text("Subjects")).shouldHave(texts(subjectOne), texts(subjectTwo));
        elements.filterBy(text("Hobbies")).shouldHave(texts("Sports"), texts("Music"));
        elements.filterBy(text("Picture")).shouldHave(texts(picture));
        elements.filterBy(text("Address")).shouldHave(texts(currentAddress));
        elements.filterBy(text("State and City")).shouldHave(texts(state + " " + city));
    }
}
