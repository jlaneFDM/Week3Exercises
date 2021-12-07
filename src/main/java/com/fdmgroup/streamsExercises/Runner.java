package com.fdmgroup.streamsExercises;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import com.fdmgroup.lambdaexercises.BankAccount;

public class Runner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BankAccount t1 = new BankAccount(12345678, 987654, "Mr J Smith", "savings", 1.1, 25362.91);
		BankAccount t2 = new BankAccount(87654321, 234567, "Ms J Jones", "savings", 0.2, 550);
		BankAccount t3 = new BankAccount(74639572, 946284, "Dr T Williams", "savings", 1.1, 4763.32);
		BankAccount t4 = new BankAccount(94715453, 987654, "Ms S Taylor", "savings", 1.1, 10598.01);
		BankAccount t5 = new BankAccount(16254385, 234567, "Mr P Brown", "current/checking", 0.2, -195.74);
		BankAccount t6 = new BankAccount(38776543, 946284, "Ms F Davies", "current/checking", 0.2, -503.47);
		BankAccount t7 = new BankAccount(87609802, 987654, "Mr B Wilson", "savings", 1.1, 2.5);
		BankAccount t8 = new BankAccount(56483769, 234567, "Dr E Evans", "current/checking", 0.2, -947.72);

		// 1.2
		// 1
		List<BankAccount> accounts = new ArrayList<BankAccount>();
		accounts.add(t1);
		accounts.add(t2);
		accounts.add(t3);
		accounts.add(t4);
		accounts.add(t5);
		accounts.add(t6);
		accounts.add(t7);
		accounts.add(t8);

		long currentCheckingCount = accounts.stream()
				.filter(acct -> acct.getAccountType() == "current/checking")
				.count();
		
		System.out.println("# of current/checking accts: " + currentCheckingCount); // outputs 3
		
		// 2
		long overdrawnCount = accounts.stream().filter(acct -> acct.getBalance() < 0).count();
		System.out.println("# of overdrawn accts: " + overdrawnCount); // outputs 3
		
		// 3
		/*The double colon (::) operator, 
		   also known as method reference operator in Java, 
		   is used to call a method by referring to it with 
		   the help of its class directly.*/
		
		Optional<BankAccount> emp = accounts.stream().max(Comparator.comparing(BankAccount::getBalance));
		if (emp.isPresent()) {
			System.out.println("Highest balance: " + emp.get()); 
		}
		
		//4
		DoubleSummaryStatistics stats = 
				accounts.stream().mapToDouble(BankAccount::getBalance).summaryStatistics();
				System.out.println("Average bal: " + stats.getAverage()); 
		
		//5
		
		OptionalDouble avgInTheBlack = 
				accounts.stream().filter(a -> a.getBalance() >= 0).mapToDouble(a -> a.getBalance()).average();
				System.out.println("Average balance of accts in the black: " + avgInTheBlack);
		
		//6
		Double sumOfOverdrafts = 
				accounts.stream().filter(a -> a.getBalance() < 0).mapToDouble(a -> a.getBalance()).sum();
				System.out.println("Sum of Overdrafted Accts: " + sumOfOverdrafts);
		
		//7
		Double sumOfInterestDue =
				accounts.stream().filter(a -> a.getBalance() >= 0).mapToDouble(a -> a.getBalance() * (a.getInterestRate()/100)).sum();
				System.out.println("Interest due on accounts with balance of zero or greater: " + sumOfInterestDue);
				
		//1.3
		//1
		List<BankAccount> overdrawnAccts = accounts.stream().filter(a -> a.getBalance() < 0).collect(Collectors.toList());
		System.out.println(overdrawnAccts);
		
		//2
		accounts.stream().filter(a -> a.getAccountType().equals("savings")).forEach(a -> a.setInterestRate(a.getInterestRate() + 1.3));
		//System.out.println(accounts.toString());
		
		//3
		accounts.stream().filter(a -> a.getBankCode() == 234567).forEach(a -> a.setBankCode(123456));
		System.out.println(accounts.toString());
		
		//4
		List<BankAccount> onlyBankCode987654 = accounts.stream()
				.filter(a -> a.getBankCode() == 987654)
				.collect(Collectors.toList());
		System.out.println( "Only bank code 987654 : " + onlyBankCode987654.toString());
		
		//5
		List<BankAccount> onlyDr = accounts.stream()
				.filter(a -> a.getAccountHolder()
				.charAt(0) == 'D' && a.getAccountHolder().charAt(1) == 'r')
				.collect(Collectors.toList());
			System.out.println("Only title Dr: " + onlyDr.toString());
			
		//1.4
		//1
		Optional<BankAccount> richestPerson =  accounts.stream().reduce((a1, a2) -> a1.getBalance() > a2.getBalance()? a1: a2);
			System.out.println("Richest person: " + richestPerson);
		
		//2
		Optional<BankAccount> lowestBalance = accounts.stream().filter(a -> a.getBankCode() == 987654)
				.reduce((a1, a2) -> a1.getBalance() < a2.getBalance()? a1:a2);
			System.out.println("Lowest balance for sort code 987654: " + lowestBalance);
				
	}

}
