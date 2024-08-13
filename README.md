# Hello World

<details><summary><b>Об проекте</b></summary>

## Этот проект состоит из следующих мини-приложений:
- Авторизация, регистрация, профиль и редактирование данных пользователя;
- Программа решения неравенства вида ax + b < 0;
- Осуществить вычисление 3^fn со всеми десятичными знаками, где n in 1..45, fn - числа фибоначчи. Это вычисление должно осуществляться внутри Service в отдельном потоке. После вычисления результаты должны появиться в Activity, а если он неактивен, то должно появиться оповещение, кликнув по которому, будет осуществлен переход на Activity с ответом. Также должна быть возможность остановить вычисление по желанию пользователя;
- CRUD приложение с использованием room, dagger-hilt, а также внедрение возможности осуществлять прикрепление фотографии к хранимым сущностям;
- Учёт количества прыжков через скакалку с использованием сенсора акселерометр. Телефон в кармане брюк и должен быть жестко зафиксирован. Информация об активностях должна сохраняться в базе данных. Пользователь должен иметь возможность корректировать результаты неточных измерений;
- Доработка приложения, выполненного в предыдущей практической, чтобы оно в виде анимации показывало активности пользователя.
- Работа с минимум одним rest-api запросом, который должен выполняется в фоне, имеющим аргумент. Также присутствует реализация возможности просмотра загруженной информации при отсуствии Интернета, т.е. с сохранением загруженной информации на мобильном устройстве и вывод его на экран в случае отсутствия Интернета. Реализуйте автоматическое UNIT и UI-тестирование (тестирование в процессе).

### В пункте 7 были использованы следующие API:
1. https://github.com/astrocatalogs/OACAPI;
2. По данному выражению найдите его упрощенный вариант https://newton.now.sh/;
3. Bybit. [Тестовое задание](https://github.com/FredNekrasov/jetpack-compose-projects/blob/master/info/TestTask.pdf)

</details>

<details><summary><b>Тесты</b></summary>

## Тесты
Пока в процессе

</details>

<details><summary><b>Архитектура</b></summary>

## Коротко про архитектуру проекта
Приложение соответствует SAA(Single Activity Architecture) и разработано оно по принципам Clean Architecture, что обеспечивает четкое разделение слоев и ответственностей, делает код более читаемым и поддерживаемым. При разработке приложения также учитывался принцип KISS, то есть стремление к простоте и минимализму в процессе разработки, что позволило избежать излишней сложности, сохранить код чистым, сделать приложение легким для понимания.

![HelloWorldAppsDiagram drawio](https://github.com/FredNekrasov/jetpack-compose-projects/blob/master/info/HelloWorldAppsDiagram.png)

</details>

<details><summary><b>Граф навигации</b></summary>

## Навигация между экранами

![HelloWorldNavGraph](https://github.com/FredNekrasov/jetpack-compose-projects/blob/master/info/HelloWorldNavGraph.png)

</details>

Использованные технологии:
-
- kotlin;
- compose - для создания UI;
- room предоставляет удобный API для работы с базами данных SQLite. Он автоматически генерирует часть кода и обеспечивает проверку SQL запросов на этапе компиляции, что обеспечивает безопасность и удобство использования. Room используется в данном приложении для кэширования информации;
- kotlinx-coroutines - это библиотека для асинхронного программирования в Kotlin. Она позволяет управлять параллельными задачами и обрабатывать асинхронные операции без блокировки потоков. Это полезно для обработки сетевых запросов и длительных операций в мобильном приложении.
- gson-converter - это библиотека для работы с JSON, которая делает сериализацию и десериализацию данных очень простой. В данном приложении она используется для работы с данными, получаемыми с сервера;
- retrofit - это библиотека для Android и JVM, которая упрощает работу с REST API. Она позволяет быстро создавать сетевые запросы, используя аннотации. В данном проекте он используется для получения информации из вышеупомянутых API;
- dagger-hilt - DI фреймворк;
- navigation-compose - это библиотека для навигации между экранами в приложении;
- coil - это библиотека для загрузки и отображения изображений в приложениях на Android. Она предоставляет простой и эффективный способ загрузки изображений из сети или локального хранилища и их отображения в пользовательском интерфейсе.

## Как запустить этот проект?
Просто клонируйте этот репозиторий
