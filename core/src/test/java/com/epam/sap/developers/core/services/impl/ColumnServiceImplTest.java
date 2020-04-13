package com.epam.sap.developers.core.services.impl;

import com.epam.sap.developers.core.services.ColumnService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColumnServiceImplTest {

    private String gridStyle;
    private int numberColumn;
    private ColumnService columnService;

    @BeforeEach
    void setUp(){
        gridStyle = "grid-template-columns: repeat(2, 1fr);";
        columnService = new ColumnServiceImpl();
        numberColumn = 2;
    }

    @Test
    void getColumnStyleByNumberColumns() {
        assertEquals(gridStyle, columnService.getColumnStyleByNumberColumns(2));
    }
}