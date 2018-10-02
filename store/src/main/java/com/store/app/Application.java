package com.store.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import com.store.dao.*;
import com.store.model.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
While you are developing your DAO layer, you can configure maven to build a jar file
and use this class to perform tests, before moving on to implementing the REST layer.
*/

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... strings) throws Exception {

        /** TODO:
			You use the provided .sql scripts to create and populate tables.
			Then, you can add calls to your CRUD operations from within this method.

		**/

    }
}
