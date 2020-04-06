package com.epam.sap.developers.core.utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SapDevelopersPathUtilsTest {

    private String pathToCut;
    private String truePath;
    private int levelToCut;

    @BeforeEach
    public void init(){
        this.pathToCut = "/content/developers/uk/sap/in";
        this.truePath = "/content/developers/uk/sap";
        this.levelToCut = 2;
    }

    @Test
    void getPathByLevelRelativeToRootPath() {
        assertEquals(truePath, SapDevelopersPathUtils.getPathByLevelRelativeToRootPath(pathToCut , levelToCut));
    }
}