package spring.security.jwtrefreshtoken.settings;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@ActiveProfiles("test")
@Sql("classpath:db/teardown.sql")
public abstract class IntegrationTestSupport {
}
