package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

/**
 * Класс WikiSearchPage представляет страницу поиска Википедии.
 * В этом классе реализованы методы для взаимодействия с элементами страницы.
 *
 * @author razlivinsky
 * @since 25.06.2025
 */
public class WikiSearchPage {
    private final SelenideElement searchInput = $("#searchInput");
    private final SelenideElement searchButton = $("#searchButton");
    private final ElementsCollection suggests = $$("a.mw-searchSuggest-link");
    private final SelenideElement firstSuggestion = $(".suggestions-result");
    private final SelenideElement lastSuggestion = $(".suggestions-special");
    private final SelenideElement pageTitle = $("h1");

    /**
     * Открывает главную страницу Википедии.
     */
    @Step("Открыть главную страницу Википедии")
    public void openMainPage() {
        open("https://ru.wikipedia.org");
    }

    /**
     * Вводит поисковой запрос в поле поиска.
     *
     * @param query текст поискового запроса
     */
    @Step("Ввести поисковый запрос: {query}")
    public void enterSearchQuery(String query) {
        searchInput.setValue(query);
    }

    /**
     * Нажимает кнопку поиска (лупа).
     */
    @Step("Нажать кнопку поиска (лупа)")
    public void clickSearch() {
        searchButton.click();
    }

    /**
     * Удаляет последнюю букву из поля поиска.
     */
    @Step("Удалить последний cимвол из поля поиска")
    public void deleteLastSymbol() {
        suggests.shouldBe(sizeGreaterThan(0), Duration.ofSeconds(5));
        searchInput.sendKeys(Keys.BACK_SPACE);
    }

    /**
     * Вводим пробел.
     */
    @Step("Ввести пробел")
    public void inputSpace() {
        searchInput.sendKeys(Keys.SPACE);
    }

    /**
     * Закрывает список саджестов.
     */
    @Step("Закрыть саджесты")
    public void closeSuggests() {
        searchInput.sendKeys(Keys.ESCAPE);
    }

    /**
     * Саджестов нет.
     */
    @Step("Саджестов нет")
    public void notSuggests() {
        suggests.forEach(el -> el.shouldBe(hidden));
    }

    /**
     * Проверяет, что все подсказки содержат заданный текст.
     *
     * @param expected ожидаемый текст в подсказках
     */
    @Step("Проверить, что все саджесты содержат текст: {expected}")
    public void shouldSeeSuggestsWithText(String expected) {
        // Ждём, пока появится хотя бы одна подсказка (таймаут — 5 секунд)
        suggests.shouldBe(sizeGreaterThan(0), Duration.ofSeconds(5));

        // Проверяем каждую подсказку: она видима и содержит подстроку {expected}
        suggests.forEach(e ->
                e.shouldBe(visible)
                        .shouldHave(text(expected))
        );
    }

    /**
     * Кликает на первую подсказку.
     */
    @Step("Кликнуть на первую подсказку")
    public void clickFirstSuggestion() {
        firstSuggestion.click();
    }

    /**
     * Кликает на нижнюю подсказку "Поиск страниц, содержащих".
     */
    @Step("Кликнуть на нижнюю подсказку 'Поиск страниц, содержащих'")
    public void clickBottomSuggestion() {
        lastSuggestion.click();
    }

    /**
     * Проверяет, что заголовок страницы содержит ожидаемый текст.
     *
     * @param expected ожидаемый текст заголовка страницы
     */
    @Step("Заголовок страницы должен содержать: {expected}")
    public void shouldSeePageTitle(String expected) {
        pageTitle.shouldHave(text(expected));
    }
}