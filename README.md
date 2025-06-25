# 🌐 Wiki Search UI Tests

[![CI Status](https://github.com/FreePoet63/wiki-search-tests/actions/workflows/wiki-tests.yml/badge.svg)](https://github.com/FreePoet63/wiki-search-tests/actions)
[![Allure Report](https://img.shields.io/badge/Allure-Report-purple)](https://freepoet63.github.io/wiki-search-tests/)

---

## 📋 Описание

UI автотесты формы поиска на [ru.wikipedia.org](https://ru.wikipedia.org), реализованные с использованием:

- ☕ **Java 17**
- 🌐 **Selenide**
- 🧪 **JUnit 5**
- 🎨 **Allure Report**
- 🚀 **GitHub Actions**

---

## 🚀 Команды запуска

### ▶️ Стандартный запуск (по умолчанию — Chrome)

```bash
mvn clean test

### 🌍 **Запуск в других браузерах** 

```bash
mvn clean test -Dbrowser=firefox
mvn clean test -Dbrowser=edge

### 📁 **Генерация отчёта после тестов**
### 👁 **Просмотр отчёта локально**

```bash
mvn allure:report
mvn allure:serve
Убедись, что установлен [Allure CLI](https://docs.qameta.io/allure/#_installing_a_commandline)


### 🌐 **Онлайн Allure Report**

👉 [Открыть опубликованный отчёт](https://FreePoet63.github.io/wiki-search-tests/)
_(обновляется автоматически после каждого пуша в `master`)_


## 🧑‍💻 **Ручной запуск на GitHub**

Если у тебя есть доступ к репозиторию:

1. Перейди во вкладку [Actions](https://github.com/FreePoet63/wiki-search-tests/actions)
2. Выбери workflow `Wiki Functional Tests`
3. Нажми кнопку **"Run workflow"**

