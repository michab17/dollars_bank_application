package com.dollarsbank.application;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.dollarsbank.model.Account;
import com.dollarsbank.model.Transaction;
import com.dollarsbank.utility.ConsoleColors;

public class DollarsBankApplication {
	
	static ArrayList<Account> accountList = new ArrayList<Account>();
	
	static Account acc1 = new Account("account one", "address", "(111) 111-1111", "acc1", "password", 1000.00);
	static Account acc2 = new Account("account two", "address", "(111) 111-1111", "acc2", "password", 1000.00);
	static Account acc3 = new Account("account three", "address", "(111) 111-1111", "acc3", "password", 1000.00);

	static Account activeAccount;
	
	public static void menu(Scanner input) {
		String choice;
		
		System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "+------------------------------+\n"
														  + "|  DOLLARSBANK Welcomes You!!  |\n"
														  + "+------------------------------+\n");
		System.out.println(ConsoleColors.WHITE + "1. Create New Account\n"
											   + "2. Log In\n"
											   + "3. Exit\n");
		System.out.println(ConsoleColors.GREEN + "Enter Choice (1, 2 or 3)\n");
		
		choice = input.nextLine();
		
		if(choice.equals("1")) {
			 createAccount(input);
		} else if(choice.equals("2")) {
			logIn(input);
		} else if(choice.equals("3")) {
			System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "\nCome back soon!");

			System.exit(0);
		} else {
			System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "INVALID SELECTION! Please enter 1, 2 or 3!");
			menu(input);
		}
	}
	
	public static void createAccount(Scanner input) {
		String name;
		String address;
		String phoneNumber = null;
		String username = null;
		String password;
		Double availableFunds = null;
		boolean correctUsername = false;
		
		System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "+--------------------+\n"
														  + "|  Account Creation  |\n"
														  + "+--------------------+\n");

		System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "Please enter the following information\n"
														  + "Customer Name:");
		
		name = input.nextLine();
		
		System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "Address:");
		
		address = input.nextLine();

		System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "Phone Number:");
				
		while(phoneNumber == null) {
			String phoneInput = input.nextLine();
			
			String regex = "\\(\\d{3}\\)\\s\\d{3}-\\d{4}";
					
			if(phoneInput.matches(regex)) {
				phoneNumber = phoneInput;
			} else {
				System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Please enter ten digits for your phone number!\n"
																 + "It should be structured: (XXX) XXX-XXXX\n");
			}
		}
		
		List<String> usernameList = accountList.stream().map(user -> user.getUsername()).toList();
		
		while(correctUsername == false) {
			System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "User Id:");

			username = input.nextLine();
			
			if(!usernameList.contains(username)) {
				correctUsername = true;
			} else {
				System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Username is already taken, please try again.\n");
			}
		}
		System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "password:");
		
		password = input.nextLine();
		while(availableFunds == null) {
			System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "Please enter the amount you would like to make for your initial deposit");
			try {
				availableFunds = Double.parseDouble(input.nextLine());
			} catch(Exception e) {
				System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "You must enter a number!!!\n");
			}
		}
		Account newAccount = new Account(name, address, phoneNumber, username, password, availableFunds);
		
		newAccount.transactions.add(new Transaction("Initial Deposit", "An initial deposit of $" + availableFunds + " was made when you created your account", LocalDateTime.now()));
		
		accountList.add(newAccount);
		
		menu(input);
	}
		
	public static void logIn(Scanner input) {
		String username;
		String password;

		System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "+----------+\n"
														  + "|  Log In  |\n"
														  + "+----------+\n");
		System.out.println(ConsoleColors.WHITE + "Enter your username:");
		
		username = input.nextLine();
		
		System.out.println(ConsoleColors.WHITE + "Enter your password:");
		
		password = input.nextLine();
		
		Optional<Account> account = accountList.stream().filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password)).findFirst();
		
		if(account.isPresent()) {
			activeAccount = account.get();
			System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "Log in successful!");
			selection(input);
		} else {
			System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Username or password were incorrect, Please try again.");
			logIn(input);
		}

	}
	
	public static void selection(Scanner input) {
		String choice ="";
		System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "+---------------------+\n"
														  + "|  WELCOME Customer!  |\n"
														  + "+---------------------+\n");
		
		System.out.println(ConsoleColors.WHITE + "1. Deposit Amount\n"
											   + "2. Withdraw Amount\n"
											   + "3. Transfer Funds\n"
											   + "4. View 5 Recent Transactions\n"
											   + "5. Display Customer Information\n"
											   + "6. Sign Out\n");
		System.out.println(ConsoleColors.GREEN + "Enter Choice (1, 2, 3, 4, 5 or 6)\n");
		
		choice = input.nextLine();
		
		if(choice.equals("1")) {
			deposit(input);
		} else if(choice.equals("2")) {
			withdraw(input);
		} else if(choice.equals("3")) {
			transfer(input);
		} else if(choice.equals("4")) {
			viewTransactions(input);
		} else if(choice.equals("5")) {
			viewCustomerInfo(input);
		} else if(choice.equals("6")) {
			System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "\nCome back soon!");
			menu(input);
		} else {
			System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "INVALID SELECTION! Please enter 1, 2, 3, 4, 5 or 6!");
			selection(input);
		}
	}

	private static void viewCustomerInfo(Scanner input) {
		System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "+------------------------+\n"
														  + "|  CUSTOMER INFORMATION  |\n"
														  + "+------------------------+\n");
		
		System.out.println(ConsoleColors.WHITE + activeAccount.toString() + "\n");
		
		System.out.println(ConsoleColors.GREEN + "Press any key to go back");
		
		while(input.nextLine() != null) {
			selection(input);
		}
		
	}

	private static void viewTransactions(Scanner input) {
		System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "+-----------------------+\n"
														  + "|  RECENT TRANSACTIONS  |\n"
														  + "+-----------------------+\n");
		
		if(activeAccount.transactions.size() == 0) {
			System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "There are no transactions yet!\n");
		} else {
			for(Transaction transaction : activeAccount.transactions) {
				System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "Transaction # "+ (activeAccount.transactions.indexOf(transaction) + 1) + ":\n" + ConsoleColors.WHITE + transaction.toString() + "\n");
			}
		}
		
		System.out.println(ConsoleColors.GREEN + "Press any key to go back");
		
		while(input.nextLine() != null) {
			selection(input);
		}
	}

	public static void transfer(Scanner input) {
		Account transferAccount = null;
		String choice = "";
		boolean transferBool = true;
		
		if(transferBool == true) {
			System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "+------------+\n"
															  + "|  TRANSFER  |\n"
															  + "+------------+\n");
			System.out.println(ConsoleColors.GREEN + "Please enter the User Id of the account you would like to transfer money to.");
			
			choice = input.nextLine();
			
			for(Account account : accountList) {
				if(account.getUsername().equals(choice)) {
					transferAccount = account;
				}
			}
			
			if(transferAccount == null) {
				System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Account with the give User Id does not exist, Please try again.");
				transfer(input);
			}
			
			System.out.println(ConsoleColors.GREEN + "Please enter the amount of money you would like to transfer.");
			
			try {
				Double transactionAmount = Double.parseDouble(input.nextLine());
				if(transactionAmount > 0) {
					activeAccount.setAvailableFunds((activeAccount.getAvailableFunds() - transactionAmount));
					transferAccount.setAvailableFunds((transferAccount.getAvailableFunds() + transactionAmount));
					Transaction activeTransaction = new Transaction("Transfer", "$" + transactionAmount + " was transfered from your account with User Id: " + activeAccount.getUsername() + " to the account with User Id: " + transferAccount.getUsername(), LocalDateTime.now());
					Transaction transferTransaction = new Transaction("Transfer", "$" + transactionAmount + " was transfered to your account with User Id: " + transferAccount.getUsername() + " from the account with User Id: " + activeAccount.getUsername(), LocalDateTime.now());
					addTransaction(activeAccount, activeTransaction);
					addTransaction(transferAccount, transferTransaction);
					
					System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "Transfer Successful!\n");
					System.out.println(ConsoleColors.GREEN + "Your account now contains $" + activeAccount.getAvailableFunds() + ".");
				} else {
					System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Please enter a number greater than zero!");
				}
			} catch(Exception e) {
				System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Invalid Input! Please enter an amount of money!");
				transfer(input);
			}
		}
		System.out.println(ConsoleColors.GREEN + "Would you like to:\n"
											   + "1. Make another transfer\n"
											   + "2. Go back to selection screen\n"
											   + "3. Log out");
		
		choice = input.nextLine();		
		
		if(choice.equals("1")) {
			transfer(input);
		} else if(choice.equals("2")) {
			selection(input);
		} else if(choice.equals("3")) {
			System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "\nCome back soon!");
			menu(input);
		} else {
			System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "INVALID SELECTION! Please enter 1, 2, or 3!");
			transferBool = false;
			transfer(input);
		}
	}

	public static void withdraw(Scanner input) {
		String choice = "";
		boolean withdrawBool = true;
		
		if(withdrawBool == true) {
			System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "+------------+\n"
															  + "|  WITHDRAW  |\n"
															  + "+------------+\n");
			System.out.println(ConsoleColors.GREEN + "How much would you like to withdraw from your account today?");

			try {
				Double transactionAmount = Double.parseDouble(input.nextLine());
				
				if(transactionAmount > 0 && transactionAmount < activeAccount.getAvailableFunds()) {
					activeAccount.setAvailableFunds((activeAccount.getAvailableFunds() - transactionAmount));
					
					Transaction transaction = new Transaction("Withdrawal", "$" + transactionAmount + " was withdrawn into your account", LocalDateTime.now());
					
					addTransaction(activeAccount, transaction);
					
					System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "Withdrawal Successful!\n");
					System.out.println(ConsoleColors.GREEN + "Your account now contains $" + activeAccount.getAvailableFunds() + ".");
				} else {
					System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Please enter a number greater than zero and less than your account balance!");
					withdraw(input);
				}
			} catch(Exception e) {
				System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Invalid Input! Please enter an amount of money!");
				withdraw(input);
			}
		}
		
		System.out.println(ConsoleColors.GREEN + "Would you like to:\n"
											   + "1. Make another withdrawal\n"
											   + "2. Go back to selection screen\n"
											   + "3. Log out");
		
		choice = input.nextLine();		
		
		if(choice.equals("1")) {
			withdraw(input);
		} else if(choice.equals("2")) {
			selection(input);
		} else if(choice.equals("3")) {
			System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "\nCome back soon!");
			menu(input);
		} else {
			System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "INVALID SELECTION! Please enter 1, 2, or 3!");
			withdrawBool = false;
			withdraw(input);
		}
	}

	public static void deposit(Scanner input) {
		String choice = "";
		boolean depositBool = true;
			
		if(depositBool == true) {
			System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "+-----------+\n"
															  + "|  DEPOSIT  |\n"
															  + "+-----------+\n");
			
			System.out.println(ConsoleColors.GREEN + "How much would you like to deposit into your account today?");
			
			try{
				Double transactionAmount = Double.parseDouble(input.nextLine());
				if(transactionAmount > 0) {
					activeAccount.setAvailableFunds((activeAccount.getAvailableFunds() + transactionAmount));
					
					Transaction transaction = new Transaction("Deposit", "$" + transactionAmount + " was deposited into your account", LocalDateTime.now());
					
					addTransaction(activeAccount, transaction);
					
					System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "Deposit Successful!\n");
					System.out.println(ConsoleColors.GREEN + "Your account now contains $" + activeAccount.getAvailableFunds() + ".");
				} else {
					System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Please enter a number greater than zero!");
				}
			} catch(Exception e) {
				System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Invalid Input! Please enter an amount of money!");
				deposit(input);
			}
			
		}
		
		System.out.println(ConsoleColors.GREEN + "Would you like to:\n"
											   + "1. Make another deposit\n"
											   + "2. Go back to selection screen\n"
											   + "3. Log out");
		
		choice = input.nextLine();		
		
		if(choice.equals("1")) {
			deposit(input);
		} else if(choice.equals("2")) {
			selection(input);
		} else if(choice.equals("3")) {
			System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "\nCome back soon!");
			menu(input);
		} else {
			System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "INVALID SELECTION! Please enter 1, 2, or 3!");
			depositBool = false;
			deposit(input);
		}
	}
	
	public static void addTransaction(Account account, Transaction transaction) {
		if(account.transactions.size() == 5) {
			account.transactions.remove(4);
			account.transactions.add(0, transaction);
		} else {
			account.transactions.add(0, transaction);
		}
		
	}

	public static void main(String[] args) {
		accountList.add(acc1);
		accountList.add(acc2);
		accountList.add(acc3);
		
		Scanner input = new Scanner(System.in);

		menu(input);

		input.close();
	}
}
