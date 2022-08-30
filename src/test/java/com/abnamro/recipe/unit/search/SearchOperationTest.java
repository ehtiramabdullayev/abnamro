package com.abnamro.recipe.unit.search;

import com.abnamro.recipe.search.SearchOperation;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SearchOperationTest {
    @Test
    public void simpleEnumExampleInsideClassTest() {
        SearchOperation contains = SearchOperation.CONTAINS;
        SearchOperation doesNotContain = SearchOperation.DOES_NOT_CONTAIN;
        SearchOperation equal = SearchOperation.EQUAL;
        SearchOperation notEqual = SearchOperation.NOT_EQUAL;
        assertEquals(SearchOperation.valueOf("CONTAINS"), contains);
        assertEquals(SearchOperation.valueOf("DOES_NOT_CONTAIN"), doesNotContain);
        assertEquals(SearchOperation.valueOf("EQUAL"), equal);
        assertEquals(SearchOperation.valueOf("NOT_EQUAL"), notEqual);
    }

    @Test
    public void whenInputEnterItReturnsCorrespondingEnum() {

        SearchOperation cn = SearchOperation.getSimpleOperation("cn");
        SearchOperation nc = SearchOperation.getSimpleOperation("nc");
        SearchOperation eq = SearchOperation.getSimpleOperation("eq");
        SearchOperation ne = SearchOperation.getSimpleOperation("ne");
        assertEquals(SearchOperation.CONTAINS, cn);
        assertEquals(SearchOperation.DOES_NOT_CONTAIN, nc);
        assertEquals(SearchOperation.EQUAL, eq);
        assertEquals(SearchOperation.NOT_EQUAL, ne);
    }
}
