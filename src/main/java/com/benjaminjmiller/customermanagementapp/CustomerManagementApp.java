package com.benjaminjmiller.customermanagementapp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class gets commands from the user and processes them.
 */
public class CustomerManagementApp {

	private CustomerController customerController;

	// The CustomerManagementApp bean has the CustomerController bean as a dependency.
	public CustomerManagementApp(CustomerController customerController) {
		this.customerController = customerController;
	}

	// Parses the command-line arguments and calls the appropriate command method
	public void processCommand(String[] args) {
		if (args.length == 0 || (args.length == 1 && (args[0].equalsIgnoreCase("help")
				|| args[0].equalsIgnoreCase("-h") || args[0].equalsIgnoreCase("--help")))) {
			printHelp();
		} else if (args.length == 1 && args[0].equalsIgnoreCase("list")) {
			list();
		} else if (args.length == 1 && args[0].equalsIgnoreCase("add")) {
			add();
		} else if (args.length == 2 && args[0].equalsIgnoreCase("delete")) {
			long customerId = Long.parseLong(args[1]);
			delete(customerId);
		} else if (args.length == 2 && args[0].equalsIgnoreCase("view")) {
			long customerId = Long.parseLong(args[1]);
			view(customerId);
		} else if (args.length == 2 && args[0].equalsIgnoreCase("edit")) {
			long customerId = Long.parseLong(args[1]);
			edit(customerId);
		} else if (args.length == 3 && args[0].equalsIgnoreCase("export")
				&& args[1].equalsIgnoreCase("all")) {
			String csvFile = args[2];
			exportAll(csvFile);
		} else if (args.length == 3 && args[0].equalsIgnoreCase("export")) {
			long customerId = Long.parseLong(args[1]);
			String csvFile = args[2];
			exportCustomer(customerId, csvFile);
		} else if (args.length == 2 && args[0].equalsIgnoreCase("import")) {
			String csvFile = args[1];
			importCustomers(csvFile);
		} else {
			System.out.println("Incorrect syntax");
			printHelp();
		}
	}

	// Import customers from the given CSV file
	private void importCustomers(String csvFile) {
		File f = new File(csvFile);
		try {
			List<Customer> customers = new ArrayList<>();
			FileReader fileReader = new FileReader(f);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line = bufferedReader.readLine();
			while (line != null) {
				customers.add(createCustomer(line));
				line = bufferedReader.readLine();
			}
			customerController.addCustomers(customers);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		System.out.println("Imported customers from " + csvFile);
	}

	// Create a customer using the given line from a CSV file
	private Customer createCustomer(String line) {
		String[] parts = line.split(",");
		Customer customer = new Customer();
		customer.setFirstName(parts[0]);
		customer.setLastName(parts[1]);
		customer.setGender(parts[2]);
		customer.setAddress(unescapeAddress(parts[3]));
		customer.setPhoneNumber(parts[4]);
		customer.setNameOnCreditCard(parts[5]);
		customer.setCreditCardNumber(parts[6]);
		customer.setExpirationDate(parts[7]);
		customer.setCsc(parts[8]);
		if (parts[9].equals("")) {
			customer.setRewardsMember(null);
		} else {
			customer.setRewardsMember(Boolean.parseBoolean(parts[9]));
		}
		if (parts.length == 10 || parts[10].equals("")) {
			customer.setRewardsPoints(null);
		} else {
			customer.setRewardsPoints(Long.parseLong(parts[10]));
		}
		return customer;
	}

	// Export the customer with the given ID to a CSV file
	private void exportCustomer(long customerId, String csvFile) {
		Customer customer = customerController.getCustomer(customerId);
		File f = new File(csvFile);
		try {
			FileWriter fileWriter = new FileWriter(f);
			writeCustomerToFileWriter(customer, fileWriter);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		System.out.println("Customer exported to " + csvFile);
	}

	// Write a single customer to a CSV file that is open for writing in the given FileWriter
	private void writeCustomerToFileWriter(Customer customer, FileWriter fileWriter) throws IOException {
		String[] fields = new String[] { customer.getFirstName(), customer.getLastName(),
				customer.getGender(), escapeAddress(customer.getAddress()), customer.getPhoneNumber(),
				customer.getNameOnCreditCard(), customer.getCreditCardNumber(), customer.getExpirationDate(),
				customer.getCsc(), customer.isRewardsMember() == null ? "" : customer.isRewardsMember().toString(),
				customer.getRewardsPoints() == null ? "" : customer.getRewardsPoints().toString() };
		StringBuffer line = new StringBuffer();
		line.append(fields[0]);
		for (int i = 1; i < fields.length; i++) {
			line.append(",");
			if (fields[i] != null) {
				line.append(fields[i]);
			}
		}
		line.append('\n');
		fileWriter.write(line.toString());
	}

	// Write all of the customers to the given CSV file
	private void exportAll(String csvFile) {
		File f = new File(csvFile);
		try {
			FileWriter fileWriter = new FileWriter(f);
			List<Customer> customers = customerController.getCustomers();
			for (Customer customer : customers) {
				writeCustomerToFileWriter(customer, fileWriter);
			}
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		System.out.println("Customers exported to " + csvFile);
	}

	// Returns an escaped form of the given address String
	String escapeAddress(String address) {
		return address.replaceAll(",", "#{COMMA}");
	}

	// Returns an unescaped form of the given escaped address String
	String unescapeAddress(String address) {
		return address.replaceAll("#[{]COMMA[}]", ",");
	}

	// Edit the user with the given id
	private void edit(long id) {
		Customer customer = customerController.getCustomer(id);
		editHelper(customer);
	}

	// Helper for edit that does most of the work
	private void editHelper(Customer customer) {
		printCustomer(customer, true);
		System.out.print("Please select which field you would like to edit (1-11): ");
		Scanner scanner = new Scanner(System.in);
		boolean validSelection = false;
		int selection = 0;
		while (!validSelection) {
			selection = scanner.nextInt();
			if (selection < 1 || selection > 11) {
				System.out.println("Invalid selection");
			} else {
				validSelection = true;
			}
		}
		String[] FIELDS = new String[] { "First Name", "Last Name", "Gender", "Address", "Phone Number",
				"Name on Credit Card", "Credit Card Number", "Expiration Date", "CSC", "Rewards Member",
				"Rewards Points" };
		String field = FIELDS[selection - 1];
		System.out.println("Please enter a new value for " + field + ".");
		System.out.print(PROMPTS[selection - 1]);
		String value = System.console().readLine();
		switch (selection) {
			case 1:
				customer.setFirstName(value);
				break;
			case 2:
				customer.setLastName(value);
				break;
			case 3:
				customer.setGender(value.toLowerCase());
				break;
			case 4:
				customer.setAddress(value);
				break;
			case 5:
				customer.setPhoneNumber(value);
				break;
			case 6:
				customer.setNameOnCreditCard(value);
				break;
			case 7:
				customer.setCreditCardNumber(value);
				break;
			case 8:
				customer.setExpirationDate(value);
				break;
			case 9:
				customer.setCsc(value);
				break;
			case 10:
				if (value.equalsIgnoreCase("yes")
						|| value.equalsIgnoreCase("y")) {
					customer.setRewardsMember(true);
				} else {
					customer.setRewardsMember(false);
				}
				break;
			case 11:
				customer.setRewardsPoints(Long.parseLong(value));
				break;
		}
		customerController.updateCustomer(customer);
		System.out.println("Updated " + field + " for " + customer.getFirstName() + " " + customer.getLastName());
		System.out.print("Would you like to update another field for this customer (Enter Yes or No)? ");
		String response = System.console().readLine();
		if (response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y")) {
			editHelper(customer);
		}
	}

	// View the customer with the given id
	private void view(long id) {
		Customer customer = customerController.getCustomer(id);
		printCustomer(customer, false);
	}

	// Delete the customer with the given id
	private void delete(long id) {
		customerController.deleteCustomer(id);
		System.out.println("Customer deleted");
	}

	// List all of the customers
	private void list() {
		System.out.println("Listing customers");
		List<Customer> customers = customerController.getCustomers();
		System.out.println("ID        \tFirst Name\tLast Name \t");
		for (Customer customer : customers) {
			printCustomerRow(customer);
		}
	}

	// Print basic info for a single customer
	private void printCustomerRow(Customer customer) {
		int COLUMN_LENGTH = 10;
		String id = customer.getId().toString();
		StringBuffer padding = new StringBuffer();
		for (int i = 0; i < COLUMN_LENGTH - id.length(); i++) {
			padding.append(' ');
		}
		System.out.print(id + padding.toString() + '\t');
		padding = new StringBuffer();
		for (int i = 0; i < COLUMN_LENGTH - customer.getFirstName().length(); i++) {
			padding.append(' ');
		}
		System.out.print(customer.getFirstName() + padding.toString() + '\t');
		padding = new StringBuffer();
		for (int i = 0; i < COLUMN_LENGTH - customer.getLastName().length(); i++) {
			padding.append(' ');
		}
		System.out.println(customer.getLastName() + padding.toString());
	}

	// Prompts for each of the fields in Customer
	private String[] PROMPTS = new String[] { "First Name: ", "Last Name: ", "Gender (Enter M or F): ",
			"Address: ", "Phone Number (No Spaces): ", "Name on Credit Card: ", "Credit Card Number (No Spaces): ",
			"Expiration Date (Enter MMYY): ", "CSC: ", "Are they a rewards member (Enter Yes or No)? ",
			"How many rewards points do they have? "};

	// Add a new customer
	private void add() {
		Console console = System.console();
		System.out.println("Please provide the customer's info:");
		int promptIndex = 0;
		System.out.print(PROMPTS[promptIndex++]);
		String firstName = console.readLine();
		System.out.print(PROMPTS[promptIndex++]);
		String lastName = console.readLine();
		System.out.print(PROMPTS[promptIndex++]);
		String gender = console.readLine().toLowerCase();
		System.out.print(PROMPTS[promptIndex++]);
		String address = console.readLine();
		System.out.print(PROMPTS[promptIndex++]);
		String phoneNumber = console.readLine();
		System.out.print(PROMPTS[promptIndex++]);
		String nameOnCreditCard = console.readLine();
		System.out.print(PROMPTS[promptIndex++]);
		String creditCardNumber = console.readLine();
		System.out.print(PROMPTS[promptIndex++]);
		String expirationDate = console.readLine();
		System.out.print(PROMPTS[promptIndex++]);
		String csc = console.readLine();
		System.out.print(PROMPTS[promptIndex++]);
		String isRewardsMemberStr = console.readLine();
		boolean isRewardsMember = false;
		long rewardsPoints = 0;
		if (isRewardsMemberStr.equalsIgnoreCase("yes")
				|| isRewardsMemberStr.equalsIgnoreCase("y")) {
			isRewardsMember = true;
			System.out.print(PROMPTS[promptIndex++]);
			Scanner scanner = new Scanner(System.in);
			rewardsPoints = scanner.nextLong();
		}
		customerController.addCustomer(firstName, lastName, gender, address, phoneNumber, nameOnCreditCard,
				creditCardNumber, expirationDate, csc, isRewardsMember, rewardsPoints);
		System.out.println("Added customer");
	}

	// Print detailed info on the given customer. If printSelectionNumbers is true,
	// also print selection numbers before each selection. The customer's ID
	// is only printed if printSelectionNumbers is false.
	public void printCustomer(Customer customer, boolean printSelectionNumbers) {
		StringBuffer result = new StringBuffer();
		int selectionNumber = 1;
		if (printSelectionNumbers) {
			result.append(selectionNumber + ". ");
			selectionNumber++;
		} else {
			result.append("ID: ");
			result.append(customer.getId());
			result.append('\n');
		}
		result.append("First Name: ");
		result.append(customer.firstName);
		result.append('\n');
		if (printSelectionNumbers) {
			result.append(selectionNumber + ". ");
			selectionNumber++;
		}
		result.append("Last Name: ");
		result.append(customer.lastName);
		result.append('\n');
		if (printSelectionNumbers) {
			result.append(selectionNumber + ". ");
			selectionNumber++;
		}
		result.append("Gender: ");
		if (customer.gender.equals("m")) {
			result.append("Male");
		} else {
			result.append("Female");
		}
		result.append('\n');
		if (printSelectionNumbers) {
			result.append(selectionNumber + ". ");
			selectionNumber++;
		}
		result.append("Address: ");
		result.append(customer.address);
		result.append('\n');
		if (printSelectionNumbers) {
			result.append(selectionNumber + ". ");
			selectionNumber++;
		}
		result.append("Phone Number: ");
		result.append(customer.phoneNumber);
		result.append('\n');
		if (printSelectionNumbers) {
			result.append(selectionNumber + ". ");
			selectionNumber++;
		}
		result.append("Name on Credit Card: ");
		result.append(customer.nameOnCreditCard);
		result.append('\n');
		if (printSelectionNumbers) {
			result.append(selectionNumber + ". ");
			selectionNumber++;
		}
		result.append("Credit Card Number: ");
		result.append(customer.creditCardNumber);
		result.append('\n');
		if (printSelectionNumbers) {
			result.append(selectionNumber + ". ");
			selectionNumber++;
		}
		result.append("Expiration Date: ");
		result.append(customer.expirationDate);
		result.append('\n');
		if (printSelectionNumbers) {
			result.append(selectionNumber + ". ");
			selectionNumber++;
		}
		result.append("CSC: ");
		result.append(customer.csc);
		result.append('\n');
		if (printSelectionNumbers) {
			result.append(selectionNumber + ". ");
			selectionNumber++;
		}
		result.append("Rewards Member: ");
		if (customer.rewardsMember) {
			result.append("Yes");
		} else {
			result.append("No");
		}
		result.append('\n');
		if (printSelectionNumbers) {
			result.append(selectionNumber + ". ");
		}
		result.append("Rewards Points: ");
		result.append(customer.rewardsPoints);
		System.out.println(result.toString());
	}

	// Print all of the commands that the user can use and what they do
	private void printHelp() {
		System.out.println("You can type one of the following commands:");
		System.out.println("list");
		System.out.println("\tList customers");
		System.out.println("add");
		System.out.println("\tAdd a customer");
		System.out.println("view [CUSTOMER ID]");
		System.out.println("\tView info on customer with given id");
		System.out.println("edit [CUSTOMER ID]");
		System.out.println("\tEdit customer with given id");
		System.out.println("delete [CUSTOMER ID]");
		System.out.println("\tDelete customer with given id");
		System.out.println("export [CUSTOMER ID] [CSV FILE]");
		System.out.println("\tExport customer with given id to a CSV file");
		System.out.println("export all [CSV FILE]");
		System.out.println("\tExport all customers to a CSV file");
		System.out.println("import [CSV FILE]");
		System.out.println("\tImport customer(s) from a CSV file");
		System.out.println("help");
		System.out.println("\tPrint help");
	}

}
