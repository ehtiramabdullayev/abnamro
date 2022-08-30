package com.abnamro.recipe.unit.search;

import com.abnamro.recipe.search.DataOption;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DataOptionTest {

    @Test
    public void simpleEnumExampleInsideClassTest(){
        DataOption option1 = DataOption.ALL;
        DataOption option2 = DataOption.ANY;
        assertEquals(DataOption.valueOf("ALL"), option1);
        assertEquals(DataOption.valueOf("ANY"), option2);
    }

    @Test
    public void whenInputEnterItReturnsCorrespondingEnum() {
        DataOption all = DataOption.getDataOption("all");
        DataOption any = DataOption.getDataOption("any");
        assertEquals(DataOption.ALL, all);
        assertEquals(DataOption.ANY, any);
    }
}
