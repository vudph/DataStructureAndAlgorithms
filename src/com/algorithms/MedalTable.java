package com.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/*
1) Problem Statement
The Olympic Games in Athens end tomorrow. Given the results of the olympic disciplines, generate and return the medal table.
The results of the disciplines are given as a String[] results, where each element is in the format "GGG SSS BBB". GGG, SSS and BBB are the 3-letter country codes (three capital letters from 'A' to 'Z') of the countries winning the gold, silver and bronze medal, respectively.
The medal table is a String[] with an element for each country appearing in results. Each element has to be in the format "CCO G S B" (quotes for clarity), where G, S and B are the number of gold, silver and bronze medals won by country CCO, e.g. "AUT 1 4 1". The numbers should not have any extra leading zeros. 
Sort the elements by the number of gold medals won in decreasing order. If several countries are tied, sort the tied countries by the number of silver medals won in decreasing order. If some countries are still tied, sort the tied countries by the number of bronze medals won in decreasing order. If a tie still remains, sort the tied countries by their 3-letter code in ascending alphabetical order.

2) Definition
Class:	MedalTable
Method:	generate
Parameters:	String[]
Returns:	String[]
Method signature:	String[] generate(String[] results)
(be sure your method is public)

3) Constraints
-	results contains between 1 and 50 elements, inclusive.
-	Each element of results is formatted as described in the problem statement.
-	No more than 50 different countries appear in results.

4) Examples
0)	
{"ITA JPN AUS", "KOR TPE UKR", "KOR KOR GBR", "KOR CHN TPE"}
Returns: 
{"KOR 3 1 0",
"ITA 1 0 0",
"TPE 0 1 1",
"CHN 0 1 0",
"JPN 0 1 0",
"AUS 0 0 1",
"GBR 0 0 1",
"UKR 0 0 1" }
1)	
{"USA AUT ROM"}
Returns: 
{"USA 1 0 0",  
"AUT 0 1 0",
"ROM 0 0 1" }
2)	
{"GER AUT SUI", "AUT SUI GER", "SUI GER AUT"}
Returns: 
{"AUT 1 1 1",
"GER 1 1 1",
"SUI 1 1 1" }
*/



public class MedalTable {

	public static void main(String[] args) {
		String results[] = {"ITA JPN AUS", "KOR TPE UKR", "KOR KOR GBR", "KOR CHN TPE"};
		String table[] = generate(results);
		for (String string : table) {
			System.out.println(string);
		}

	}
	
	private static String[] generate(String[] results) {
		List<String> countryCodes = new ArrayList<String>();
		for (String res : results) {
			String codes[] = res.split(" ");
			for (String cc : codes) {
				if (!countryCodes.contains(cc)) {
					countryCodes.add(cc);
				}
			}
		}
		Country countries[] = new Country[countryCodes.size()];
		for (int i = 0; i < countryCodes.size(); i++) {
			Country c = new Country(countryCodes.get(i), 0, 0, 0);
			countries[i] = c;
		}
		for (String res : results) {
			String codes[] = res.split(" ");
			for (int i = 0; i < codes.length; i++) {
				int countryId = countryCodes.indexOf(codes[i]);
				if (i == 0) {
					countries[countryId].increaseNrGold();
				} else if (i == 1) {
					countries[countryId].increaseNrSilver();
				} else if (i == 2) {
					countries[countryId].increaseNrBronze();
				}
			}
		}
		Arrays.sort(countries);
		String []ret = new String[countries.length];
		for (int i = 0; i < countries.length; i++) {
			ret[i] = countries[i].toString();			
		}
		return ret;
	}
	
	private static String[] generate1(String[] results) {
		HashMap<String, Country> countryMap = new HashMap<String, Country>();
		for (String re : results) {
			String countryCodes[] = re.split(" ");
			for (int i = 0; i < countryCodes.length; i++) {
				Country country = countryMap.get(countryCodes[i]);
				if (country == null) {
					country = new Country(countryCodes[i], 0, 0, 0);
					countryMap.put(countryCodes[i], country);
				}
				int nrGold = country.getNrGold(), nrSilver = country.getNrSilver(), nrBronze = country.getNrBronze();
				switch (i) {
				case 0:
					nrGold++;
					break;
				case 1:
					nrSilver++;
					break;
				case 2:
					nrBronze++;
					break;
				}
				country.setMedals(nrGold, nrSilver, nrBronze);
				
			}
		}
		Collection<Country> list = countryMap.values();
		List<Country> l = new ArrayList<Country>();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Country country = (Country) iterator.next();
			l.add(country);
			System.out.println(country.toString());
		}
		Collections.sort(l);
		System.out.println("======= Sort ========");
		for (Country country : l) {
			System.out.println(country.toString());
		}
		return null;
	}
}

class Country implements Comparable<Country> {
	private String code;
	private int nrGold;
	private int nrSilver;
	private int nrBronze;
	
	public Country(String code) {
		this.code = code;
	}
	
	public Country(String code, int nrGold, int nrSilver, int nrBronze) {
		this.code = code;
		this.nrGold = nrGold;
		this.nrSilver = nrSilver;
		this.nrBronze = nrBronze;
	}
	
	public void setMedals(int nrGold, int nrSilver, int nrBronze) {
		this.nrGold = nrGold;
		this.nrSilver = nrSilver;
		this.nrBronze = nrBronze;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getNrGold() {
		return nrGold;
	}

	public void setNrGold(int nrGold) {
		this.nrGold = nrGold;
	}
	
	public void increaseNrGold() {
		this.nrGold++;
	}

	public int getNrSilver() {
		return nrSilver;
	}

	public void setNrSilver(int nrSilver) {
		this.nrSilver = nrSilver;
	}
	
	public void increaseNrSilver() {
		this.nrSilver++;
	}

	public int getNrBronze() {
		return nrBronze;
	}

	public void setNrBronze(int nrBronze) {
		this.nrBronze = nrBronze;
	}
	
	public void increaseNrBronze() {
		this.nrBronze++;
	}
	/*
	The compareTo() method should compare this object to another object, return an int value. Here are the rules for that int value:

		Return a negative value if this object is smaller than the other object
		Return 0 (zero) if this object is equal to the other object.
		Return a positive value if this object is larger than the other object.
	*/
	@Override
	public int compareTo(Country o) {
		if (this.nrGold > o.nrGold) {
			return -1;
		} else if (this.nrGold < o.nrGold) {
			return 1;
		} else if (this.nrSilver > o.nrSilver) {
			return -1;
		} else if (this.nrSilver < o.nrSilver) {
			return 1;
		} else if (this.nrBronze > o.nrBronze) {
			return -1;
		} else if (this.nrBronze < o.nrBronze) {
			return 1;
		}
		return this.code.compareTo(o.code);
	}
	/*
	Here is an example Comparator that compares two fictive Employee objects:

	public class MyComparator<Employee> implements Comparator<Employee> {
	    public int compare(Employee emp1, Employee emp2){
	       if(emp1.getSalary() <  emp2.getSalary()) return -1;
	       if(emp1.getSalary() == emp2.getSalary()) return 0;
	       return 1;
	    }
	}
	A shorter way to write the comparison would be like this:

	public class MyComparator<Employee> implements Comparator<Employee> {
		public int compare(Employee emp1, Employee emp2){
			return emp1.getSalary() - emp2.getSalary();
		}
	}
	*/
	
	public String toString() {
		return this.code + " " + this.nrGold + " " + this.nrSilver + " " + this.nrBronze;
	}
}


