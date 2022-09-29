package com.abnamro.recipe.unit.search;

import com.abnamro.recipe.search.DataOption;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DataOptionTest {

    @Test
    public void simpleEnumExampleInsideClassTest() {
        DataOption option1 = DataOption.ALL;
        DataOption option2 = DataOption.ANY;
        assertEquals(DataOption.valueOf("ALL"), option1);
        assertEquals(DataOption.valueOf("ANY"), option2);
    }

    @Test
    public void whenInputEnterItReturnsCorrespondingEnum() {
        Optional<DataOption> all = DataOption.getDataOption("all");
        Optional<DataOption> any = DataOption.getDataOption("any");
        assertTrue(all.isPresent());
        assertTrue(any.isPresent());
        assertEquals(DataOption.ALL, all.get());
        assertEquals(DataOption.ANY, any.get());
    }
}
