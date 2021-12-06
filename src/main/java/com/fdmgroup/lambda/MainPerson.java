package com.fdmgroup.lambda;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class MainPerson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person p1 = new Person(10, "Smith", "John", 18);
		Person p2 = new Person(11, "Smith", "Jane", 30);
		Person p3 = new Person(12, "Williams", "Tom", 40);
		Person p4 = new Person(13, "Taylor", "Samuel", 45);
		Person p5 = new Person(14, "Patel", "Jasjeet", 18);
		
		List<Person> persons = new ArrayList<Person>();
		
		persons.add(p1);
		persons.add(p2);
		persons.add(p3);
		persons.add(p4);
		persons.add(p5);
		
		Function<Person, String> getPersonName = t -> t.getFirstName();
		System.out.println(getPersonName.apply(p5));
		
		BiFunction<Person, String, String> getAllPersonsWithLastName = 
				(person, title) -> title + " " + person.getLastName();
		System.out.println(getAllPersonsWithLastName.apply(p4, "Mr."));
		
		Predicate<Person> getAgeLessThanThirty = x -> x.getAge() < 30;
		System.out.println(getAgeLessThanThirty.test(p3));
		
		BinaryOperator<Person> getOldestPerson = 
				(person1, person2) ->person1.getAge() > person2.getAge()? person1:person2;
				
		System.out.println(getOldestPerson.apply(p1, p2));
		
		Comparator<Person> comparePersonByFirstName = (o1,o2) -> o1.getFirstName().compareTo(o2.getFirstName());
		Comparator<Person> comparePersonByLastName = (c1, c2) -> c1.getLastName().compareTo(c2.getLastName());
		Comparator<Person> comparePersonByLastNameThenFirstName = 
				comparePersonByLastName.thenComparing(comparePersonByFirstName);
		
		System.out.println(comparePersonByLastNameThenFirstName.compare(p3, p4));
		
		Consumer<Person> getName = 
				p -> System.out.println("First name: " + p.getFirstName() + " Last name: " + p.getLastName());
		
		Collections.sort(persons, comparePersonByLastNameThenFirstName);
		persons.forEach(getName);
				
	}

}
