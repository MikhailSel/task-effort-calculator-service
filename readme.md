## Bali Events Bot

### Используемые технологии

- **Spring Boot 3.2**
- **Java 21**
- **PostgreSQL**
- **MapStruct**: Используется для отображения между различными типами Java bean.
- **Lombok**: Используется для уменьшения количества шаблонного кода.
- **Docker**: Используется для упаковки приложения и всех его зависимостей в контейнер, который может быть развернут на любой машине, которая работает на Linux.
- **Docker Compose**: Используется для определения и запуска многоконтейнерных Docker-приложений.
- **Maven**: Используется для управления зависимостями и сборки проекта.
- **JUnit 5**: Используется для написания и запуска тестов.
- **DTO** (Data Transfer Object): Используется для передачи данных между слоями приложения.

### Инструкции по локальному запуску установке

1. Клонируйте репозиторий: `git clone [https://github.com/SimakovIgor/bali-events-bot.git]`
2. Перейдите в директорию проекта: `cd bali-events-bot`
3. Соберите проект: `mvn clean install`
4. Запустите `docker compose -f docker-compose.yaml -p bali-events up -d` [docker-compose.yaml](docker-compose.yaml)
5. Запустите приложение: `java -jar [module_name].jar`

Не стесняйтесь настраивать этот файл `README.md` в соответствии с конкретными деталями и требованиями вашего проекта.
