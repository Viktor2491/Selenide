package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;


public class CardDeliveryTest {

    @BeforeEach
    public void setUp() {
        open("http://localhost:9999/");
    }
     @Test
    //валидные значения, отправка запроса
    public void shouldSubmitRequest(){
        $("[placeholder=\"Город\"]").setValue("Москва");
        $("[placeholder=\"Дата встречи\"]").setValue("16.09.2022");
        $("[name=\"name\"]").setValue("Иванов Иван");
        $("[name=\"phone\"]").setValue("+79992225533");
        $x("//span[@class =\"checkbox__box\"]").click();
        $x("//span[@class =\"button__text\"]").click();
        $x("//div [@class=\"notification__icon\"]/..").getText();
        $x("//* [contains(text(),\"Встреча успешно забронирована на 16.09.2022 \")]").should(Condition.visible, Duration.ofSeconds(15));

                

 }
}
