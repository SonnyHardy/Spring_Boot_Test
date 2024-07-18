package com.sonny.positions.customers;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
@Repository
public class CustomerDao {

    private final JdbcTemplate jdbcTemplate;
    private final static String FIND_ALL = "select * from customers";
    //private RowMapper<CustomerDTO> customerRowMapper = ((rs, rowNum)
    //        -> new CustomerDTO(rs.getInt("id"), rs.getString("email")));

    public List<CustomerDTO> search() {
        return this.jdbcTemplate.query(FIND_ALL, new RowMapper<CustomerDTO>() {
            @Override
            public CustomerDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new CustomerDTO(rs.getInt("id"), rs.getString("email"));
            }
        });
    }
}
