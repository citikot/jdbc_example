package online.agatstudio.dao;

import lombok.RequiredArgsConstructor;
import online.agatstudio.model.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class CustomerDao {

    private final NamedParameterJdbcTemplate template;
    private final JdbcTemplate jdbcTemplate;

    public Long createCustomer(Customer customer) {

        // Исплользование NamedParameterJdbcTemplate (имена с двоеточием)

        String sql = "INSERT INTO customer (fio, phone, address) VALUES (:fio, :phone, :address) RETURNING ID";

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("fio", customer.getFio())
                .addValue("phone", customer.getPhone())
                .addValue("address", customer.getAddress());
//        Map<String, Object> map = new HashMap<>();
//        map.put("fio", customer.getFio());
//        map.put("phone", customer.getPhone());
//        map.put("address", customer.getAddress());
        return template.queryForObject(sql, parameterSource, Long.class);
    }

    public Long createCustomer2(Customer customer) {

        // Использование обычного JdbcTemplate

        String sql = "INSERT INTO customer (fio, phone, address) VALUES (?, ?, ?) RETURNING ID";
        return jdbcTemplate.queryForObject(sql, Long.class, customer.getFio(), customer.getPhone(), customer.getAddress());
    }

    public Customer getCustomerById(long id) {

        String sql = "SELECT * FROM customer c WHERE c.id = :id";

        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id); // или через addValue("id", id)
        return template.queryForObject(sql, parameterSource, new CustomerRowMapper());
    }

    public void updateCustomer(Customer customer) {

        String sql = "UPDATE customer SET fio = :fio, phone = :phone, address = :address WHERE id = :id";

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", customer.getId())
                .addValue("fio", customer.getFio())
                .addValue("phone", customer.getPhone())
                .addValue("address", customer.getAddress());

        template.update(sql, parameterSource);
    }

    public void deleteCustomer(long id) {

        String sql = "DELETE FROM customer WHERE id = :id";

        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id); // или через addValue("id", id)
        template.update(sql, parameterSource);
    }
}
