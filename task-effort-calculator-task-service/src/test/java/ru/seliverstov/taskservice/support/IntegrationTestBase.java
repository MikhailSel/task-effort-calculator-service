package ru.seliverstov.taskservice.support;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.transaction.support.TransactionTemplate;
import ru.seliverstov.taskservice.initializer.PostgreSqlInitializer;
import ru.seliverstov.taskservice.repository.TaskEstimationRepository;
import ru.seliverstov.taskservice.repository.TaskRepository;

import java.util.Set;
import java.util.stream.Collectors;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = PostgreSqlInitializer.class)
public abstract class IntegrationTestBase {
    @Autowired
    protected WebTestClient webTestClient;
    @Autowired
    protected JdbcTemplate jdbcTemplate;
    @Autowired
    protected TransactionTemplate transactionTemplate;
    @Autowired
    protected TaskRepository taskRepository;
    @Autowired
    protected TaskEstimationRepository taskEstimationRepository;

    @AfterEach
    void truncateTables() {
        jdbcTemplate.execute("TRUNCATE TABLE " + getTablesToTruncate() + " RESTART IDENTITY CASCADE");
    }

    private String getTablesToTruncate() {
        return getTables()
            .stream()
            .map(this::tableNameWithSchema)
            .collect(Collectors.joining(", "));
    }

    protected String tableNameWithSchema(final String tableName) {
        final var schema = getSchema();
        return tableName.startsWith(schema)
               ? tableName
               : String.format("%s.%s", schema, tableName);
    }

    private Set<String> getTables() {
        return Set.of("tasks", "task_user_estimation");
    }

    private String getSchema() {
        return "public";
    }
}
