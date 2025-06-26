package base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

/**
 * Класс BaseTest служит базовым классом для тестов.
 * В классе выполняется начальная настройка перед запуском всех тестов.
 * Он настраивает используемый браузер и добавляет слушатель для Allure Selenide.
 *
 * @author razlivinsky
 * @since 25.06.2025
 */
public class BaseTest {

    /**
     * Метод, выполняющийся один раз перед выполнением всех тестов.
     * Настраивает браузер и добавляет слушатель для логирования Selenide в Allure.
     */
    @BeforeAll
    static void setup() {
        String browser = System.getProperty("browser", "chrome");
        Configuration.browser = browser;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
    }

    /**
     * Метод, выполняющийся после каждого теста.
     * Очищает кэш браузера для предотвращения влияния на последующие тесты.
     */
    @AfterEach
    void clearBrowserCache() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }
}