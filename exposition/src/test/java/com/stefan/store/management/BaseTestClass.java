package com.stefan.store.management;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;

@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class BaseTestClass {

    protected static final ObjectMapper jackson = new ObjectMapper().registerModule(new JavaTimeModule());

    @MockBean
    DataSource dataSource;
}
