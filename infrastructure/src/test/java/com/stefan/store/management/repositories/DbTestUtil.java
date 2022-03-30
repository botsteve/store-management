package com.stefan.store.management.repositories;

import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public final class DbTestUtil {

    private DbTestUtil() {}

    public static void resetAutoIncrementColumns(ApplicationContext applicationContext,
                                                 String tableName, String columnId) throws SQLException {
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        String resetSqlTemplate = getResetSqlTemplate(applicationContext);
        try (Connection dbConnection = dataSource.getConnection()) {
            //Create SQL statements that reset the auto increment columns and invoke
            //the created SQL statements.
                try (Statement statement = dbConnection.createStatement()) {
                    String resetSql = String.format(resetSqlTemplate, tableName, columnId);
                    statement.execute(resetSql);
                }
        }
    }

    private static String getResetSqlTemplate(ApplicationContext applicationContext) {
        //Read the SQL template from the properties file
        Environment environment = applicationContext.getBean(Environment.class);
        return environment.getRequiredProperty("test.reset.sql.template");
    }
}