package com.benjaminjmiller.customermanagementapp;

import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class CustomerManagementAppTests {

    @Test
    void testEscapeAddress() {
        String address = "123 Main St., Seattle, WA 98122";
        String expected = "123 Main St.#{COMMA} Seattle#{COMMA} WA 98122";
        String actual = new CustomerManagementApp(new CustomerController()).escapeAddress(address);
        assertEquals(expected, actual);
    }

    @Test
    void testUnescapeAddress() {
        String address = "123 Main St.#{COMMA} Seattle#{COMMA} WA 98122";
        String expected = "123 Main St., Seattle, WA 98122";
        String actual = new CustomerManagementApp(new CustomerController()).unescapeAddress(address);
        assertEquals(expected, actual);
    }
}
