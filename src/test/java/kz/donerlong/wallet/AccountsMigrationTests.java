package kz.donerlong.wallet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class AccountsMigrationTests {

    @Autowired
    JdbcTemplate jdbc;

    @Test
    void accountsTableExists() {
        String table = jdbc.queryForObject("SELECT to_regclass('public.accounts')::text", String.class);

        assertThat(table).isEqualTo("accounts");
    }

    @Test
    void negativeBalanceIsRejected() {
        assertThatThrownBy(() -> jdbc.update("INSERT INTO accounts (owner_id, balance) VALUES (uuidv7(), -1)"))
                .isInstanceOf(DataIntegrityViolationException.class)
                .hasMessageContaining("accounts_balance_non_negative");
    }
}
