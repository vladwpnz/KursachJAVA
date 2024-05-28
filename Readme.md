# JavaPivo
курсач JAVA
# tema: Веб-сервіс для обміну та подарунків книг між користувачами.
# 1. Проектування інформаційної системи
## опис User stories з діаграмами послідовностей
https://iampm.club/ua/blog/yak-pisati-user-stories-shhob-bulo-zrozumilo-vsim/ - тут загальні рекомендації по написанню User stories

https://dou.ua/forums/topic/40575/ - тут є загальні рекомендації по створенню діаграм послідовностей
Опційно написання User stories можна доповнити Дфіаграмами діяльності, зменшивши деталізацію User stories

## опис ERD бази даних
https://www.lucidchart.com/pages/er-diagrams - ресурс з описом ER діаграм

УВАГА. В проекті, будь ласка, використовуйте нотацію Crow`s foot
УВАГА 2. При проктуванні баз даних дотримуйтеся принципів нормалізації

## опис діаграми класів
https://shorturl.at/cKV14 - методичні рекомендації зі створення діаграм класів

## написання тестових сценаріїв
https://training.qatestlab.com/blog/course-materials/test-case-topic/

# 2. Реалізація API
## Підготовка бази даних
Загалом з цього пункту Вам все має бути відомо з курсу по БД, але можете скористатися гайдом, як будувати базу з сутностей в Spring
https://www.baeldung.com/spring-data-jpa-generate-db-schema
## Створення Spring Boot Application 
Створити проект зі всіма залежностями можна за допомогою https://start.spring.io/
## Написання контролерів
Невеликий гайд з цього є тут
https://www.baeldung.com/spring-boot-start
## Написання сервісів
Сервіси мають реалізовувати необхідну бізнес логіку. Про використання різних анотацій Spring і менеджмент компонетів, зокрема, можна почитати тут 
https://www.baeldung.com/spring-component-repository-service
## Написання Unit тестів
Про тестування на Spring можна почитати тут
https://www.baeldung.com/spring-boot-testing

# 3. Підключення Swagger, системи авторизації з ролями, Docker Compose для розгортання проекту

# 4. Інтеграційне тестування системи
