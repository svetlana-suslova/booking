package utils.logging;

import com.codeborne.selenide.Screenshots;
import com.google.common.io.Files;
import io.qameta.allure.Attachment;

import java.io.IOException;

public class SelenideScreenshot {

    @Attachment
    public static byte[] screenshot() {
        byte [] result = null;
        try {
            result = Files.toByteArray(Screenshots.getLastScreenshot());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
