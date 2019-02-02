package ru.argos.fuzzycontroller.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static ru.argos.fuzzycontroller.utils.Utils.getOrThrow;
import static ru.argos.fuzzycontroller.utils.Utils.unmodifiableList;

public class UtilsTest {

    @Test
    public void testGetOrThrow() {
        Map<String, String> map = Map.of("k1", "v1", "k2", "v2", "k3", "v3");
        Assert.assertEquals("v1", getOrThrow(map, "k1"));
        Assert.assertEquals("v2", getOrThrow(map, "k2"));
        Assert.assertEquals("v3", getOrThrow(map, "k3"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetOrThrowIllegalArgumentException() {
        getOrThrow(Map.of("k1", "v1"), "k2");
    }

    @Test
    public void testUnmodifiableListEquals() {
        Assert.assertEquals(List.of("1", "2", "3"), unmodifiableList(List.of("1", "2", "3")));
        Assert.assertEquals(List.of("1", "2", "3"), unmodifiableList(List.of("1"), List.of("2", "3")));
        Assert.assertEquals(List.of("1"), unmodifiableList("1"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUnmodifiableListAddElementOneList() {
        unmodifiableList(List.of("1", "2", "3")).add("4");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUnmodifiableListAddElementTwoList() {
        unmodifiableList(List.of("1"), List.of("2", "3")).add("4");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUnmodifiableListAddElementOneElementList() {
        unmodifiableList("1").add("4");
    }
}
