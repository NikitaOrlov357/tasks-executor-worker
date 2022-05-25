package com.nesterov.tasksexecutor.worker.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringUtilsTest {

    @Test
    public void testIsNotBlankWithBlankString(){
        String str = "  ";
        boolean result = StringUtils.isNotBlank(str);

        assertFalse(result);
    }

    @Test
    public void testIsNotBlankWithNotBlankString(){
        String str = "not blank string";
        boolean result = StringUtils.isNotBlank(str);

        assertTrue(result);
    }
}
