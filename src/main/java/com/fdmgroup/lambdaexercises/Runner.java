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
		
		/*1*/
		Function<BankAccount, String> getCustomerNameAndBalance = 
				account -> account.getAccountHolder() + " " + account.getBalance();
			System.out.println(getCustomerNameAndBalance.apply(t3));
			
		/*2*/
			
		Function<BankAccount, Double> getInterestDue = 
				(account) -> account.getBalance() * (account.getInterestRate()/100);
			System.out.println("Interest due: " + getInterestDue.apply(t2));
			
		/*3.*/
			
		Function<BankAccount, Double> returnZeroIfOverdrawn = 
				(account) -> account.getBalance() * (account.getInterestRate()/100) > 0? 
							account.getBalance() * (account.getInterestRate()/100) : 0;
			System.out.println(returnZeroIfOverdrawn.apply(t8));//outputs 0
			
		/* 4.*/
			
		Predicate<BankAccount> determineIfCurrent = (account) -> account.getAccountType() == "current/checking";
			System.out.println(determineIfCurrent.test(t1));
			
		/*5.*/
		Predicate<BankAccount> determineIfOverdrawn = (account) -> account.getBalance() < 0? true : false;
			System.out.println("Overdrawn ? : " + determineIfOverdrawn.test(t5));
			
		/*6.*/
		
		BinaryOperator<BankAccount> getHigherBalance = 
				(a1, a2) -> a1.getBalance() > a2.getBalance()? a1: a2;
			System.out.println("Higher Balance between t3 & t4: " + getHigherBalance.apply(t3, t4));
		
		/* 7.*/
		
		Consumer<BankAccount> deductTenGBP = (account) -> account.setBalance(account.getBalance() - 10);
		 	deductTenGBP.accept(t2);
		 	System.out.println("Balance with 10 GBP deducted: " + t2.getBalance());
		 	
		 /* 8.*/
			
		BiConsumer<BankAccount,Integer> deductFromBalance = 
				(account, deduction) -> account.setBalance(account.getBalance() - deduction);
			deductFromBalance.accept(t1, 100);
			System.out.println("Balance after user-defined deduction: " + t1.getBalance());	
				
	}

}
