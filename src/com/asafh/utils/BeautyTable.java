package com.asafh.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.asafh.beans.Company;
import com.asafh.beans.Coupon;
import com.asafh.beans.Customer;

public class BeautyTable {
	public static void tableWithLinesCompanies(List<Company> companies) {
		/*
		 * leftJustifiedRows - If true, it will add "-" as a flag to format string to
		 * make it left justified. Otherwise right justified.
		 */
		boolean leftJustifiedRows = false;
	 
		/*
		 * Table to print in console in 2-dimensional array. Each sub-array is a row.
		 */
		//String ids=String.valueOf(id);
		String[][] table = new String[companies.size()+1][4];// {{"id","name","email","password"}};
		table[0][0]="id";
		table[0][1]="name";
		table[0][2]="email";
		table[0][3]="password";
		
		
		int count=1;
		for (Company compa : companies) {			
			String idS=String.valueOf(compa.getId());
			table[count][0]=idS;
			table[count][1]=compa.getName();
			table[count][2]=compa.getEmail();
			table[count][3]=compa.getPassword();
			count++;
		}
		
		/*
		 * Calculate appropriate Length of each column by looking at width of data in
		 * each column.
		 * 
		 * Map columnLengths is <column_number, column_length>
		 */
		Map<Integer, Integer> columnLengths = new HashMap<>();
		Arrays.stream(table).forEach(a -> Stream.iterate(0, (i -> i < a.length), (i -> ++i)).forEach(i -> {
			if (columnLengths.get(i) == null) {
				columnLengths.put(i, 0);
			}
			if (columnLengths.get(i) < a[i].length()) {
				columnLengths.put(i, a[i].length());
			}
		}));
		//System.out.println("columnLengths = " + columnLengths);
	 
		/*
		 * Prepare format String
		 */
		final StringBuilder formatString = new StringBuilder("");
		String flag = leftJustifiedRows ? "-" : "";
		columnLengths.entrySet().stream().forEach(e -> formatString.append("| %" + flag + e.getValue() + "s "));
		formatString.append("|\n");
		//System.out.println("formatString = " + formatString.toString());
	 
		/*
		 * Prepare line for top, bottom & below header row.
		 */
		String line = columnLengths.entrySet().stream().reduce("", (ln, b) -> {
			String templn = "+-";
			templn = templn + Stream.iterate(0, (i -> i < b.getValue()), (i -> ++i)).reduce("", (ln1, b1) -> ln1 + "-",
					(a1, b1) -> a1 + b1);
			templn = templn + "-";
			return ln + templn;
		}, (a, b) -> a + b);
		line = line + "+\n";
		//System.out.println("Line = " + line);
	 
		/*
		 * Print table
		 */
		System.out.print(line);
		Arrays.stream(table).limit(1).forEach(a -> System.out.printf(formatString.toString(), a));
		System.out.print(line);
	 
		Stream.iterate(1, (i -> i < table.length), (i -> ++i))
				.forEach(a -> System.out.printf(formatString.toString(), table[a]));
		System.out.print(line);
	}
	
	public static void tableWithLinesOneCompany(Company company) {
		/*
		 * leftJustifiedRows - If true, it will add "-" as a flag to format string to
		 * make it left justified. Otherwise right justified.
		 */
		boolean leftJustifiedRows = false;
	 
		/*
		 * Table to print in console in 2-dimensional array. Each sub-array is a row.
		 */
		//
		String[][] table = new String[2][4];// {{"id","name","email","password"}};
		table[0][0]="id";
		table[0][1]="name";
		table[0][2]="email";
		table[0][3]="password";
		String ids=String.valueOf(company.getId());
		table[1][0]=ids;
		table[1][1]=company.getName();
		table[1][2]=company.getEmail();
		table[1][3]=company.getPassword();
			
		
		/*
		 * Calculate appropriate Length of each column by looking at width of data in
		 * each column.
		 * 
		 * Map columnLengths is <column_number, column_length>
		 */
		Map<Integer, Integer> columnLengths = new HashMap<>();
		Arrays.stream(table).forEach(a -> Stream.iterate(0, (i -> i < a.length), (i -> ++i)).forEach(i -> {
			if (columnLengths.get(i) == null) {
				columnLengths.put(i, 0);
			}
			if (columnLengths.get(i) < a[i].length()) {
				columnLengths.put(i, a[i].length());
			}
		}));
		//System.out.println("columnLengths = " + columnLengths);
	 
		/*
		 * Prepare format String
		 */
		final StringBuilder formatString = new StringBuilder("");
		String flag = leftJustifiedRows ? "-" : "";
		columnLengths.entrySet().stream().forEach(e -> formatString.append("| %" + flag + e.getValue() + "s "));
		formatString.append("|\n");
		//System.out.println("formatString = " + formatString.toString());
	 
		/*
		 * Prepare line for top, bottom & below header row.
		 */
		String line = columnLengths.entrySet().stream().reduce("", (ln, b) -> {
			String templn = "+-";
			templn = templn + Stream.iterate(0, (i -> i < b.getValue()), (i -> ++i)).reduce("", (ln1, b1) -> ln1 + "-",
					(a1, b1) -> a1 + b1);
			templn = templn + "-";
			return ln + templn;
		}, (a, b) -> a + b);
		line = line + "+\n";
		//System.out.println("Line = " + line);
	 
		/*
		 * Print table
		 */
		System.out.print(line);
		Arrays.stream(table).limit(1).forEach(a -> System.out.printf(formatString.toString(), a));
		System.out.print(line);
	 
		Stream.iterate(1, (i -> i < table.length), (i -> ++i))
				.forEach(a -> System.out.printf(formatString.toString(), table[a]));
		System.out.print(line);
	}
	
	public static void tableWithLinesCoupons(List<Coupon> coupons) {
		/*
		 * leftJustifiedRows - If true, it will add "-" as a flag to format string to
		 * make it left justified. Otherwise right justified.
		 */
		boolean leftJustifiedRows = false;
	 
		/*
		 * Table to print in console in 2-dimensional array. Each sub-array is a row.
		 */
		//id=1, companyID=1, category=Food, title=1+1, destraction=buy one get one, startDate=2020-04-25, endDate=2020-05-07, amount=100, price=3.0, image=https://bla bla... 
		String[][] table = new String[coupons.size()+1][10];
		table[0][0]="id";
		table[0][1]="companyID";
		table[0][2]="category";
		table[0][3]="title";
		table[0][4]="description";
		table[0][5]="start date";
		table[0][6]="end date";
		table[0][7]="amount";
		table[0][8]="price";
		table[0][9]="image";
		
		
		int count=1;
		for (Coupon coup : coupons) {			
			String idS=String.valueOf(coup.getId());
			String compID=String.valueOf(coup.getCompanyID());
			String categ= (coup.getCategory()).toString();
			String start=String.valueOf(coup.getStartDate());
			String end=String.valueOf(coup.getEndDate());
			String am=String.valueOf(coup.getAmount());
			String pri= String.valueOf(coup.getPrice());
			
			table[count][0]=idS;
			table[count][1]=compID;
			table[count][2]=categ;
			table[count][3]=coup.getTitle();
			table[count][4]=coup.getDescription();
			table[count][5]=start;
			table[count][6]=end;
			table[count][7]=am;
			table[count][8]=pri;
			table[count][9]=coup.getImage();
			
			count++;
		}
		
		/*
		 * Calculate appropriate Length of each column by looking at width of data in
		 * each column.
		 * 
		 * Map columnLengths is <column_number, column_length>
		 */
		Map<Integer, Integer> columnLengths = new HashMap<>();
		Arrays.stream(table).forEach(a -> Stream.iterate(0, (i -> i < a.length), (i -> ++i)).forEach(i -> {
			if (columnLengths.get(i) == null) {
				columnLengths.put(i, 0);
			}
			if (columnLengths.get(i) < a[i].length()) {
				columnLengths.put(i, a[i].length());
			}
		}));
		//System.out.println("columnLengths = " + columnLengths);
	 
		/*
		 * Prepare format String
		 */
		final StringBuilder formatString = new StringBuilder("");
		String flag = leftJustifiedRows ? "-" : "";
		columnLengths.entrySet().stream().forEach(e -> formatString.append("| %" + flag + e.getValue() + "s "));
		formatString.append("|\n");
		//System.out.println("formatString = " + formatString.toString());
	 
		/*
		 * Prepare line for top, bottom & below header row.
		 */
		String line = columnLengths.entrySet().stream().reduce("", (ln, b) -> {
			String templn = "+-";
			templn = templn + Stream.iterate(0, (i -> i < b.getValue()), (i -> ++i)).reduce("", (ln1, b1) -> ln1 + "-",
					(a1, b1) -> a1 + b1);
			templn = templn + "-";
			return ln + templn;
		}, (a, b) -> a + b);
		line = line + "+\n";
		//System.out.println("Line = " + line);
	 
		/*
		 * Print table
		 */
		System.out.print(line);
		Arrays.stream(table).limit(1).forEach(a -> System.out.printf(formatString.toString(), a));
		System.out.print(line);
	 
		Stream.iterate(1, (i -> i < table.length), (i -> ++i))
				.forEach(a -> System.out.printf(formatString.toString(), table[a]));
		System.out.print(line);
	}
	
	public static void tableWithLinesOneCoupon(Coupon coupon) {
		/*
		 * leftJustifiedRows - If true, it will add "-" as a flag to format string to
		 * make it left justified. Otherwise right justified.
		 */
		boolean leftJustifiedRows = false;
	 
		/*
		 * Table to print in console in 2-dimensional array. Each sub-array is a row.
		 */
		//id=1, companyID=1, category=Food, title=1+1, destraction=buy one get one, startDate=2020-04-25, endDate=2020-05-07, amount=100, price=3.0, image=https://bla bla... 
		String[][] table = new String[2][10];
		table[0][0]="id";
		table[0][1]="companyID";
		table[0][2]="category";
		table[0][3]="title";
		table[0][4]="description";
		table[0][5]="start date";
		table[0][6]="end date";
		table[0][7]="amount";
		table[0][8]="price";
		table[0][9]="image";
		
		
		
				
			String idS=String.valueOf(coupon.getId());
			String compID=String.valueOf(coupon.getCompanyID());
			String categ= (coupon.getCategory()).toString();
			String start=String.valueOf(coupon.getStartDate());
			String end=String.valueOf(coupon.getEndDate());
			String am=String.valueOf(coupon.getAmount());
			String pri= String.valueOf(coupon.getPrice());
			
			table[1][0]=idS;
			table[1][1]=compID;
			table[1][2]=categ;
			table[1][3]=coupon.getTitle();
			table[1][4]=coupon.getDescription();
			table[1][5]=start;
			table[1][6]=end;
			table[1][7]=am;
			table[1][8]=pri;
			table[1][9]=coupon.getImage();
			
			
		
		/*
		 * Calculate appropriate Length of each column by looking at width of data in
		 * each column.
		 * 
		 * Map columnLengths is <column_number, column_length>
		 */
		Map<Integer, Integer> columnLengths = new HashMap<>();
		Arrays.stream(table).forEach(a -> Stream.iterate(0, (i -> i < a.length), (i -> ++i)).forEach(i -> {
			if (columnLengths.get(i) == null) {
				columnLengths.put(i, 0);
			}
			if (columnLengths.get(i) < a[i].length()) {
				columnLengths.put(i, a[i].length());
			}
		}));
		//System.out.println("columnLengths = " + columnLengths);
	 
		/*
		 * Prepare format String
		 */
		final StringBuilder formatString = new StringBuilder("");
		String flag = leftJustifiedRows ? "-" : "";
		columnLengths.entrySet().stream().forEach(e -> formatString.append("| %" + flag + e.getValue() + "s "));
		formatString.append("|\n");
		//System.out.println("formatString = " + formatString.toString());
	 
		/*
		 * Prepare line for top, bottom & below header row.
		 */
		String line = columnLengths.entrySet().stream().reduce("", (ln, b) -> {
			String templn = "+-";
			templn = templn + Stream.iterate(0, (i -> i < b.getValue()), (i -> ++i)).reduce("", (ln1, b1) -> ln1 + "-",
					(a1, b1) -> a1 + b1);
			templn = templn + "-";
			return ln + templn;
		}, (a, b) -> a + b);
		line = line + "+\n";
		//System.out.println("Line = " + line);
	 
		/*
		 * Print table
		 */
		System.out.print(line);
		Arrays.stream(table).limit(1).forEach(a -> System.out.printf(formatString.toString(), a));
		System.out.print(line);
	 
		Stream.iterate(1, (i -> i < table.length), (i -> ++i))
				.forEach(a -> System.out.printf(formatString.toString(), table[a]));
		System.out.print(line);
	}
	
	public static void tableWithLinesCustomers(List<Customer> customers) {
		/*
		 * leftJustifiedRows - If true, it will add "-" as a flag to format string to
		 * make it left justified. Otherwise right justified.
		 */
		boolean leftJustifiedRows = false;
	 
		/*
		 * Table to print in console in 2-dimensional array. Each sub-array is a row.
		 */
		//String ids=String.valueOf(id);
		String[][] table = new String[customers.size()+1][5];// {{"id","name","email","password"}};
		table[0][0]="id";
		table[0][1]="first name";
		table[0][2]="last name";
		table[0][3]="email";
		table[0][4]="password";
		
		
		int count=1;
		for (Customer cust : customers) {			
			String idS=String.valueOf(cust.getId());
			table[count][0]=idS;
			table[count][1]=cust.getFirstName();
			table[count][2]=cust.getLastName();
			table[count][3]=cust.getEmail();
			table[count][4]=cust.getPassword();
			count++;
		}
		
		/*
		 * Calculate appropriate Length of each column by looking at width of data in
		 * each column.
		 * 
		 * Map columnLengths is <column_number, column_length>
		 */
		Map<Integer, Integer> columnLengths = new HashMap<>();
		Arrays.stream(table).forEach(a -> Stream.iterate(0, (i -> i < a.length), (i -> ++i)).forEach(i -> {
			if (columnLengths.get(i) == null) {
				columnLengths.put(i, 0);
			}
			if (columnLengths.get(i) < a[i].length()) {
				columnLengths.put(i, a[i].length());
			}
		}));
		//System.out.println("columnLengths = " + columnLengths);
	 
		/*
		 * Prepare format String
		 */
		final StringBuilder formatString = new StringBuilder("");
		String flag = leftJustifiedRows ? "-" : "";
		columnLengths.entrySet().stream().forEach(e -> formatString.append("| %" + flag + e.getValue() + "s "));
		formatString.append("|\n");
		//System.out.println("formatString = " + formatString.toString());
	 
		/*
		 * Prepare line for top, bottom & below header row.
		 */
		String line = columnLengths.entrySet().stream().reduce("", (ln, b) -> {
			String templn = "+-";
			templn = templn + Stream.iterate(0, (i -> i < b.getValue()), (i -> ++i)).reduce("", (ln1, b1) -> ln1 + "-",
					(a1, b1) -> a1 + b1);
			templn = templn + "-";
			return ln + templn;
		}, (a, b) -> a + b);
		line = line + "+\n";
		//System.out.println("Line = " + line);
	 
		/*
		 * Print table
		 */
		System.out.print(line);
		Arrays.stream(table).limit(1).forEach(a -> System.out.printf(formatString.toString(), a));
		System.out.print(line);
	 
		Stream.iterate(1, (i -> i < table.length), (i -> ++i))
				.forEach(a -> System.out.printf(formatString.toString(), table[a]));
		System.out.print(line);
	}
	
	public static void tableWithLinesOneCustomer(Customer customer) {
		/*
		 * leftJustifiedRows - If true, it will add "-" as a flag to format string to
		 * make it left justified. Otherwise right justified.
		 */
		boolean leftJustifiedRows = false;
	 
		/*
		 * Table to print in console in 2-dimensional array. Each sub-array is a row.
		 */
		//String ids=String.valueOf(id);
		String[][] table = new String[2][5];// {{"id","name","email","password"}};
		table[0][0]="id";
		table[0][1]="first name";
		table[0][2]="last name";
		table[0][3]="email";
		table[0][4]="password";
		
		String idS=String.valueOf(customer.getId());
		table[1][0]=idS;
		table[1][1]=customer.getFirstName();
		table[1][2]=customer.getLastName();
		table[1][3]=customer.getEmail();
		table[1][4]=customer.getPassword();
					
			
			
		
		/*
		 * Calculate appropriate Length of each column by looking at width of data in
		 * each column.
		 * 
		 * Map columnLengths is <column_number, column_length>
		 */
		Map<Integer, Integer> columnLengths = new HashMap<>();
		Arrays.stream(table).forEach(a -> Stream.iterate(0, (i -> i < a.length), (i -> ++i)).forEach(i -> {
			if (columnLengths.get(i) == null) {
				columnLengths.put(i, 0);
			}
			if (columnLengths.get(i) < a[i].length()) {
				columnLengths.put(i, a[i].length());
			}
		}));
		//System.out.println("columnLengths = " + columnLengths);
	 
		/*
		 * Prepare format String
		 */
		final StringBuilder formatString = new StringBuilder("");
		String flag = leftJustifiedRows ? "-" : "";
		columnLengths.entrySet().stream().forEach(e -> formatString.append("| %" + flag + e.getValue() + "s "));
		formatString.append("|\n");
		//System.out.println("formatString = " + formatString.toString());
	 
		/*
		 * Prepare line for top, bottom & below header row.
		 */
		String line = columnLengths.entrySet().stream().reduce("", (ln, b) -> {
			String templn = "+-";
			templn = templn + Stream.iterate(0, (i -> i < b.getValue()), (i -> ++i)).reduce("", (ln1, b1) -> ln1 + "-",
					(a1, b1) -> a1 + b1);
			templn = templn + "-";
			return ln + templn;
		}, (a, b) -> a + b);
		line = line + "+\n";
		//System.out.println("Line = " + line);
	 
		/*
		 * Print table
		 */
		System.out.print(line);
		Arrays.stream(table).limit(1).forEach(a -> System.out.printf(formatString.toString(), a));
		System.out.print(line);
	 
		Stream.iterate(1, (i -> i < table.length), (i -> ++i))
				.forEach(a -> System.out.printf(formatString.toString(), table[a]));
		System.out.print(line);
	}
}
