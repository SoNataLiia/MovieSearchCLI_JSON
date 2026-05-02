# MovieSearch CLI — Block 1: JSON + HTTP API

Console Java application for searching movies using The Movie Database API.
Built with no external libraries — standard JDK only.

## Features

- Displays a list of popular movies (TMDB API)
- Shows movie details by ID (title + overview)
- Console menu with Scanner and error handling

## Tech Stack

- Java (no dependencies)
- `HttpURLConnection` — HTTP requests
- Manual JSON parsing via `indexOf` / `substring`
- `Scanner` + `do-while` — console menu

## Run

```bash
mvn compile
mvn exec:java -Dexec.mainClass="MoviMenu"
```

## Part of a larger project

This is Block 1 of 4 of the MovieSearch learning project:

| Block | Topic |
|-------|-------|
| **1 — JSON + HTTP** | `MoviMenu.java` — current |
| 2 — Spring Boot REST | Save favourite movies (JPA + MySQL) |
| 3 — MongoDB | Alternative storage |
| 4 — Files | Export to encrypted file |
# MovieSearch CLI — Блок 1: JSON + HTTP API

Консольное Java-приложение для поиска фильмов через The Movie Database API.
Написано без сторонних библиотек — только стандартный JDK.

## Что умеет

- Показывает список популярных фильмов (TMDB API)
- Выводит детали фильма по ID (название + описание)
- Меню на Scanner с обработкой ошибок

## Технологии

- Java (без зависимостей)
- `HttpURLConnection` — HTTP-запросы
- Ручной парсинг JSON через `indexOf` / `substring`
- `Scanner` + `do-while` — консольное меню

## Запуск

```bash
mvn compile
mvn exec:java -Dexec.mainClass="MoviMenu"
```

## Часть проекта

Это Блок 1 из 4 учебного проекта MovieSearch:

| Блок | Тема |
|------|------|
| **1 — JSON + HTTP** | `MoviMenu.java` — текущий |
| 2 — Spring Boot REST | Сохранение избранных фильмов (JPA + MySQL) |
| 3 — MongoDB | Альтернативное хранилище |
| 4 — Файлы | Экспорт в зашифрованный файл |
