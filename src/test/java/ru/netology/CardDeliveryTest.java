package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.ofSeconds;


public class CardDeliveryTest {

    @BeforeEach
    public void setUp() {
        open("http://localhost:9999/");
    }

    @Test
    //валидные значения, отправка запроса
    public void shouldSubmitRequest() {
        $("[placeholder=\"Город\"]").setValue("Москва");
        SelenideElement dateField = $("[placeholder=\"Дата встречи\"]");
        dateField.click();
        dateField.sendKeys(Keys.CONTROL, "a");
        dateField.sendKeys(Keys.BACK_SPACE);
        String dateOfTesting = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        dateField.setValue(dateOfTesting);
        $("[name=\"name\"]").setValue("Иванов Иван");
        $("[name=\"phone\"]").setValue("+79992225533");
        $x("//span[@class =\"checkbox__box\"]").click();
        $x("//span[@class =\"button__text\"]").click();
        $("[data-test-id=notification] .notification__content").shouldBe(Condition.visible, ofSeconds(15))
                .shouldHave(exactText("Встреча успешно забронирована на " + "16.09.2022"));


    }
}
