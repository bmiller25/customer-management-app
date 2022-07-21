package com.benjaminjmiller.customermanagementapp;

import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertTrue;

class CustomerTests {

	@Test
	void testConstructor() {
		Customer c = new Customer("John", "Doe", "M", "153 Main St, Seattle, WA 98122",
				"2065921833", "John Doe", "1111222233334444",
				"0525", "192", true, 48L);
		assertTrue(c.getFirstName().equals("John"));
		assertTrue(c.getLastName().equals("Doe"));
		assertTrue(c.getGender().equals("M"));
		assertTrue(c.getAddress().equals("153 Main St, Seattle, WA 98122"));
		assertTrue(c.getPhoneNumber().equals("2065921833"));
		assertTrue(c.getNameOnCreditCard().equals("John Doe"));
		assertTrue(c.getCreditCardNumber().equals("1111222233334444"));
		assertTrue(c.getExpirationDate().equals("0525"));
		assertTrue(c.getCsc().equals("192"));
		assertTrue(c.isRewardsMember());
		assertTrue(c.getRewardsPoints() == 48L);
	}

	@Test
	void testConstructorWithSomeEmptyStrings() {
		Customer c = new Customer("John", "Doe", "M", "153 Main St, Seattle, WA 98122",
				"", "John Doe", "",
				"0525", "192", true, 48L);
		assertTrue(c.getFirstName().equals("John"));
		assertTrue(c.getLastName().equals("Doe"));
		assertTrue(c.getGender().equals("M"));
		assertTrue(c.getAddress().equals("153 Main St, Seattle, WA 98122"));
		assertTrue(c.getPhoneNumber() == null);
		assertTrue(c.getNameOnCreditCard().equals("John Doe"));
		assertTrue(c.getCreditCardNumber() == null);
		assertTrue(c.getExpirationDate().equals("0525"));
		assertTrue(c.getCsc().equals("192"));
		assertTrue(c.isRewardsMember());
		assertTrue(c.getRewardsPoints() == 48L);
	}

	@Test
	void testConstructorWithAllEmptyStrings() {
		Customer c = new Customer("", "", "", "",
				"", "", "",
				"", "", true, 48L);
		assertTrue(c.getFirstName() == null);
		assertTrue(c.getLastName() == null);
		assertTrue(c.getGender() == null);
		assertTrue(c.getAddress() == null);
		assertTrue(c.getPhoneNumber() == null);
		assertTrue(c.getNameOnCreditCard() == null);
		assertTrue(c.getCreditCardNumber() == null);
		assertTrue(c.getExpirationDate() == null);
		assertTrue(c.getCsc() == null);
		assertTrue(c.isRewardsMember());
		assertTrue(c.getRewardsPoints() == 48L);
	}

}
