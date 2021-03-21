package com.test.ExpensesManage;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConverterTest {

    Converter converter;

    @BeforeEach
    void setUp() {
        converter = Converter.getConverter();
    }

    @Test
    void getConverter() {
       assertEquals(converter,Converter.getConverter());
    }
}