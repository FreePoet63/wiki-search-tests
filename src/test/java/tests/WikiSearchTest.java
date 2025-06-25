package tests;

import base.BaseTest;
import com.codeborne.selenide.junit5.TextReportExtension;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import pages.WikiSearchPage;

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
        wiki.enterSearchQuery("Иван");
        wiki.deleteLastLetter();
        wiki.deleteLastLetter();
        wiki.clickBottomSuggestion();
        wiki.shouldSeePageTitle("Результаты поиска");
    }
}
