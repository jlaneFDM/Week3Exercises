package com.fdmgroup.lambdaexercises;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;


public class Runner {

	private static final String Function = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BankAccount t1 = new BankAccount(12345678, 987654, "Mr J Smith", "savings", 1.1, 25362.91);
		BankAccount t2 = new BankAccount(87654321, 234567, "Ms J Jones", "savings", 0.2, 550);
		BankAccount t3 = new BankAccount(74639572 ,946284, "Dr T Williams", "savings", 1.1, 4763.32);
		BankAccount t4 = new BankAccount(94715453, 987654, "Ms S Taylor", "savings", 1.1, 10598.01);
		BankAccount t5 = new BankAccount(16254385, 234567, "Mr P Brown", "current/checking", 0.2, -195.74);
		BankAccount t6 = new BankAccount(38776543, 946284, "Ms F Davies", "current/checking", 0.2, -503.47);
		BankAccount t7 = new BankAccount(87609802, 987654, "Mr B Wilson", "savings", 1.1, 2.5);
		BankAccount t8 = new BankAccount(56483769, 234567, "Dr E Evans", "current/checking", 0.2, -947.72);
		
		/*1.Write a lambda expression which implements the Function interface. 
		 * It should take a bank account as an argument and return a String 
		 * containing the customer’s name and balance. 
		 * Test using account3 (should return “Dr T Williams: 4763.32”) 
		 * and account4 (should return “Ms S Taylor: 10598.01”)*/
		Function<BankAccount, String> getCustomerNameAndBalance = 
				account -> account.getAccountHolder() + " " + account.getBalance();
			System.out.println(getCustomerNameAndBalance.apply(t3));
			
		/*2. Write a lambda expression which implements 
		 * the Function interface. It should take a bank account as 
		 * an argument and return a double containing the amount of 
		 * interest due on the account. This can be calculated as follows: 
		 * balance * interest rate / 100. Test this using account2. It should return 1.1.*/
			
		Function<BankAccount, Double> getInterestDue = 
				(account) -> account.getBalance() * (account.getInterestRate()/100);
			System.out.println("Interest due: " + getInterestDue.apply(t2));
			
		/*
		 *3. Write a modified version of your previous lambda expression which returns 0 if the account 
		 * is overdrawn. Test with account2 (should return 1.1) and account8 (should return 0.0). 
		 * You will need to use a ternary operator.*/
			
		Function<BankAccount, Double> returnZeroIfOverdrawn = 
				(account) -> account.getBalance() * (account.getInterestRate()/100) > 0? 
							account.getBalance() * (account.getInterestRate()/100) : 0;
			System.out.println(returnZeroIfOverdrawn.apply(t8));//outputs 0
			
		/* 4. Write a lambda expression which implements the Predicate interface. 
		 * It should take a bank account as an argument. If the bank account is a 
		 * current account the expression should return true, otherwise it should return false. 
		 * Test with account1 (should return false) and account6 (should return true)*/
			
		Predicate<BankAccount> determineIfCurrent = (account) -> account.getAccountType() == "current/checking";
			System.out.println(determineIfCurrent.test(t1));
			
		/*5. Write a lambda expression which implements the Predicate interface. 
		 * It should take a bank account as an argument. If the bank account 
		 * is overdrawn it should return true, otherwise it should return false. 
		 * Test with account5 (should return true) and account7 (should return false)*/
		Predicate<BankAccount> determineIfOverdrawn = (account) -> account.getBalance() < 0? true : false;
			System.out.println("Overdrawn ? : " + determineIfOverdrawn.test(t5));
			
		/*6. Write a lambda expression which implements the BinaryOperator interface. 
		 * It should take 2 bank accounts as arguments and return the bank account 
		 * which has the highest balance. Test using account3 and account4 as arguments, 
		 * it should return account4 (balance is 10598.01). You will need to use a ternary operator.*/
		
		BinaryOperator<BankAccount> getHigherBalance = 
				(a1, a2) -> a1.getBalance() > a2.getBalance()? a1: a2;
			System.out.println("Higher Balance between t3 & t4: " + getHigherBalance.apply(t3, t4));
		
			/* 7. Write a lambda expression which implements the Consumer interface. 
			 * It should take a bank account as an argument. It should then deduct a £10 fee from the balance. 
			 * Test using account2 and account6. Call the getBalance() method on the two objects to 
			 * check that their balances have been 
			 * updated (account2 should be 540.0 and account6 should be -513.47).*/
		
		Consumer<BankAccount> deductTenGBP = (account) -> account.setBalance(account.getBalance() - 10);
		 	deductTenGBP.accept(t2);
		 	System.out.println("Balance with 10 GBP deducted: " + t2.getBalance());
		 	
		 /* 8. Write a lambda expression which implements the BiConsumer interface. 
		  * It should take 2 arguments: a bank account and an integer. 
		  * It should deduct the integer value from the account’s balance. 
		  * Test using account1 and 100. 
		  * The getBalance() method should show that account1’s balance is now 25262.91. 
		  * Also test with account5 and 50. The balance afterwards should be -245.74*/
			
		BiConsumer<BankAccount,Integer> deductFromBalance = 
				(account, deduction) -> account.setBalance(account.getBalance() - deduction);
			deductFromBalance.accept(t1, 100);
			System.out.println("Balance after user-defined deduction: " + t1.getBalance());	
		
			
		
		
	}

}
