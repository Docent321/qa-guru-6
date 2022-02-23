package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


public class tests {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
    }

    @BeforeEach
    void precondition(){
        open("https://demoqa.com/text-box");
    }

    @AfterEach
    void closedBrowser(){
        closeWebDriver();
    }


    @ValueSource(strings = {"Alex", "Vovan"})
    @ParameterizedTest(name = "Заполнение формы text-box {0}")

    void parameterizedTest(String testData){

        //заполнить поля
        $("#userName").setValue(testData);
        $("#userEmail").setValue(testData + "@asd.sd");
        $("#currentAddress").setValue("123123123");
        $("#permanentAddress").setValue("assffg");

        //кликнуть Submit
        $("#submit").click();
        //проверить значение в поле
        $("#output").shouldHave(text(testData))
                .shouldHave(text(testData + "@asd.sd"))
                .shouldHave(text("123123123"))
                .shouldHave(text("assffg"));

    }


    @CsvSource(value = {
            "Alex/ Alex@asd.sd/ Address, addres Moscov/ Address, addres St-Piter",
            "Vovan/ Vovan@asd.sd/ Address 1 Vovan/ Address 2 Vovan"
    },
    delimiter = '/')
    @ParameterizedTest(name = "Заполнение формы text-box {0}")

    void parameterizedCvTest(String testData, String email, String address, String curaddress){

        //заполнить поля
        $("#userName").setValue(testData);
        $("#userEmail").setValue(email);
        $("#currentAddress").setValue(address);
        $("#permanentAddress").setValue(curaddress);

        //кликнуть Submit
        $("#submit").click();
        //проверить значение в поле
        $("#output").shouldHave(text(testData))
                .shouldHave(text(email))
                .shouldHave(text(address))
                .shouldHave(text(curaddress));

    }
}
