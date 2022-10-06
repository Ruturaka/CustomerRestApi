package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcCustomerRepository implements CustomerRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Customer customer)
    {
        return jdbcTemplate.update("INSERT INTO customers(name,number) VALUES(?,?,?)",
                new Object[] { customer.getName(), customer.getNumber() });
    }

    @Override
    public int update(Customer customer) {
        return jdbcTemplate.update("UPDATE customers SET name=?, number=? WHERE id=?",
                new Object[] { customer.getName(), customer.getNumber(), customer.getId() });
    }

    @Override
    public Customer findById(Long id) {
        try {
            Customer customer = jdbcTemplate.queryForObject("SELECT * FROM customers WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Customer.class), id);

            return customer;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM customers WHERE id=?", id);
    }

    @Override
    public List<Customer> findAll() {
        return jdbcTemplate.query("SELECT * from tutorials", BeanPropertyRowMapper.newInstance(Customer.class));
    }

    @Override
    public List<Customer> findByPublished(boolean published) {
        return jdbcTemplate.query("SELECT * from tutorials WHERE published=?",
                BeanPropertyRowMapper.newInstance(Customer.class), published);
    }

    @Override
    public List<Customer> findByTitleContaining(String title) {
        String q = "SELECT * from tutorials WHERE title ILIKE '%" + title + "%'";

        return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Customer.class));
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from tutorials");
    }
}
