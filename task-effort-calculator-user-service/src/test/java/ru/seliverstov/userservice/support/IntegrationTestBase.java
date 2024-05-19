package ru.seliverstov.userservice.support;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.transaction.support.TransactionTemplate;
import ru.seliverstov.userservice.initializer.PostgreSqlInitializer;
import ru.seliverstov.userservice.mapper.VacationPeriodMapper;
import ru.seliverstov.userservice.repository.RoleRepository;
import ru.seliverstov.userservice.repository.UserRepository;
import ru.seliverstov.userservice.repository.VacationPeriodRepository;

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
    protected UserRepository userRepository;
    @Autowired
    protected RoleRepository roleRepository;
    @Autowired
    protected VacationPeriodRepository vacationPeriodRepository;
    @Autowired
    protected VacationPeriodMapper vacationPeriodMapper;

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
        return Set.of("users");
    }

    private String getSchema() {
        return "public";
    }
}
