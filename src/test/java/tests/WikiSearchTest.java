package tests;

import base.BaseTest;
import com.codeborne.selenide.junit5.TextReportExtension;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import pages.WikiSearchPage;

import static com.codeborne.selenide.Selenide.refresh;

/**
 * Класс WikiSearchTest содержит тесты для функциональности поиска на Википедии.
 * Он наследуется от класса BaseTest и содержит методы для тестирования различных
 * сценариев поиска с использованием Selenide и Allure для ведения отчетности.
 *
 * @author razlivinsky
 * @since 25.06.2025
 */
@Epic("Поиск на Википедии")
@Feature("Форма поиска")
public class WikiSearchTest extends BaseTest {

    @RegisterExtension
    static TextReportExtension report = new TextReportExtension().onFailedTest(true).onSucceededTest(true);

    WikiSearchPage wiki = new WikiSearchPage();

    /**
     * Тест проверяет появление подсказок при вводе поискового запроса.
     *
     * @story Появление саджестов при вводе
     * @severity NORMAL
     * @displayName Появление саджестов при вводе запроса
     */
    @Test
    @Story("Появление саджестов при вводе")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Появление саджестов при вводе запроса")
    void suggestsAppearsWithQueryTest() {
        wiki.openMainPage();
        wiki.enterSearchQuery("Иван");
        wiki.shouldSeeSuggestsWithText("Иван");
    }

    /**
     * Тест проверяет переход на страницу из первой подсказки.
     *
     * @story Переход по первой подсказке
     * @severity CRITICAL
     * @displayName Переход на страницу из первой подсказки
     */
    @Test
    @Story("Переход по первой подсказке")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Переход на страницу из первой подсказки")
    void goToPageFromSuggestTest() {
        wiki.openMainPage();
        wiki.enterSearchQuery("Иван");
        wiki.clickFirstSuggestion();
        wiki.shouldSeePageTitle("Иван");
    }

    /**
     * Тест проверяет переход по кнопке поиска (лупа) на первую подсказку.
     *
     * @story Переход по кнопке лупы
     * @severity CRITICAL
     * @displayName Переход по кнопке поиска (лупа) на первую подсказку
     */
    @Test
    @Story("Переход по кнопке лупы")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Переход по кнопке поиска (лупа) на первую подсказку")
    void goToFirstSuggestionViaSearchButtonTest() {
        wiki.openMainPage();
        wiki.enterSearchQuery("Иван");
        wiki.clickSearch();
        wiki.shouldSeePageTitle("Иван");
    }

    /**
     * Тест проверяет переход на страницу результатов поиска, если подсказок нет.
     *
     * @story Поиск при отсутствии саджеста
     * @severity NORMAL
     * @displayName Переход на страницу результатов поиска, если подсказок нет
     */
    @Test
    @Story("Поиск при отсутствии саджеста")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Переход на страницу результатов поиска, если подсказок нет")
    void goToSearchPageIfNoSuggestsTest() {
        wiki.openMainPage();
        wiki.enterSearchQuery("Иваннннн");
        wiki.clickSearch();
        wiki.shouldSeePageTitle("Результаты поиска");
    }

    /**
     * Тест проверяет, что клик по нижней подсказке саджеста ведёт на страницу поиска.
     *
     * @story Клик по нижней подсказке саджеста
     * @severity NORMAL
     * @displayName Клик по 'Поиск страниц содержащих' ведёт на страницу поиска
     */
    @Test
    @Story("Клик по нижней подсказке саджеста")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Клик по 'Поиск страниц содержащих' ведёт на страницу поиска")
    void clickBottomSearchHintTest() {
        wiki.openMainPage();
        wiki.enterSearchQuery("Иван ");
        wiki.deleteLastSymbol();
        wiki.clickBottomSuggestion();
        wiki.shouldSeePageTitle("Результаты поиска");
    }

    /**
     * Тест проверяет, что при пустом поле ввода происходит переход на страницу результатов поиска.
     *
     * @story Поиск при пустом запросе
     * @severity MINOR
     * @displayName Переход на страницу результатов поиска при пустом поле ввода
     */
    @Test
    @Story("Поиск при пустом запросе")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Переход на страницу результатов поиска при пустом поле ввода")
    void noSuggestsForEmptyQueryTest() {
        wiki.openMainPage();
        wiki.clickSearch();
        wiki.shouldSeePageTitle("Поиск");
    }

    /**
     * Тест проверяет, что при вводе только пробелов отображается один саджест 'Поиск страниц, содержащих'.
     *
     * @story Поиск при вводе пробелов
     * @severity MINOR
     * @displayName Отображение саджеста 'Поиск страниц, содержащих' при вводе пробелов
     */
    @Test
    @Story("Поиск при вводе пробелов")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Отображение саджеста 'Поиск страниц, содержащих' при вводе пробелов")
    void noSuggestsForSpacesOnlyTest() {
        wiki.openMainPage();
        for (int i = 0; i < 5; i++) {
            wiki.inputSpace();
        }
        wiki.clickBottomSuggestion();
        wiki.shouldSeePageTitle("Результаты поиска");
    }

    /**
     * Тест проверяет, что при вводе спецсимвола и буквы (например “%A”)
     * в подсказках отображаются варианты по букве “A”.
     *
     * @story Отображение подсказок после спецсимвола
     * @severity NORMAL
     * @displayName Подсказки при вводе ‘%A’
     */
    @Test
    @Story("Отображение подсказок после спецсимвола")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Подсказки при вводе ‘%A’")
    void suggestsAfterPercentAndLetterTest() {
        wiki.openMainPage();
        wiki.enterSearchQuery("%A");
        wiki.shouldSeeSuggestsWithText("A");
    }

    /**
     * Тест проверяет работу поиска с запросом на русском и затем на английском.
     *
     * @story Переключение языка интерфейса
     * @severity NORMAL
     * @displayName Подсказки на английском после поиска на русском
     */
    @Test
    @Story("Переключение языка интерфейса")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Подсказки на английском после поиска на русском")
    void suggestsInEnglishAfterLanguageSwitchTest() throws InterruptedException {
        wiki.openMainPage();
        wiki.enterSearchQuery("Иван");
        wiki.shouldSeeSuggestsWithText("Иван");
        refresh(); // Обновляем страницу (не обязательно, если уже не забыли отключить)
        wiki.enterSearchQuery("Ivan");
        wiki.shouldSeeSuggestsWithText("Ivan");
    }

    /**
     * Тест проверяет, что при нажатии клавиши Esc саджесты исчезают.
     *
     * @story Проверка исчезновения саджестов
     * @severity NORMAL
     * @displayName Исчезновение саджестов при нажатии клавиши Esc
     */
    @Test
    @Story("Проверка исчезновения саджестов")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Исчезновение саджестов при нажатии клавиши Esc")
    void suggestsDisappearsOnEscTest() {
        wiki.openMainPage();
        wiki.enterSearchQuery("Иван");
        wiki.shouldSeeSuggestsWithText("Иван");
        wiki.closeSuggests();
        wiki.notSuggests();
    }
}
