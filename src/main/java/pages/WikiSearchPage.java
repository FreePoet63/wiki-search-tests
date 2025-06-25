package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.text;
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
    private final ElementsCollection suggests = $$("div.mw-searchSuggest-link");
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
    @Step("Удалить последнюю букву из поля поиска")
    public void deleteLastLetter() {
        searchInput.sendKeys(Keys.BACK_SPACE);
    }

    /**
     * Проверяет, что все подсказки содержат заданный текст.
     *
     * @param expected ожидаемый текст в подсказках
     */
    @Step("Проверить, что все саджесты содержат текст: {expected}")
    public void shouldSeeSuggestsWithText(String expected) {
        suggests.forEach(e -> e.shouldHave(text(expected)));
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