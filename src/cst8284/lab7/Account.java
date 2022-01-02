package cst8284.lab7;
//Author: Kim Johnson Student#040950584

import java.text.*;
import java.util.Calendar;
import java.util.Date;

public class Account {
	private String accountNumber = "000-000000";  // branch number - customer account number
	private String firstName, lastName;  
	private static final Calendar ACCOUNTSTARTDATE = Calendar.getInstance();
	private DateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");

	public Account(String accountNumber, String firstName, String lastName, String startDate) {
		dateFormat.setLenient(false);
		setAccountNumber(accountNumber);
		setFirstName(firstName);
		setLastName(lastName);
		setAccountStartDate(startDate);
	}

	private  void setAccountNumber(String accountNumber) { 

		if  (accountNumber.charAt(0)=='0') {
			throw new BadAccountInputException("Brach number must be 100 or greater");
		}



		if(isCheckDigitCorrect(accountNumber))	
			this.accountNumber = accountNumber;

	}
	private static boolean isCheckDigitCorrect(String accountNumber ) {
		int sum = 0;
		int power = 9;

		accountNumber = accountNumber.replaceAll("-", "");
		for (int j = 0; j < accountNumber.length() -1; j++) {
			sum+= j % 2 == 0 ? (-2 * Character.getNumericValue(accountNumber.charAt(j))
					) : (9 * Character.getNumericValue(accountNumber.charAt(j))
							);
		}

		if((sum % 9) == (Character.getNumericValue(accountNumber.charAt(accountNumber.length()-1))))
			return true;

		return false;
	}









	private static boolean isInputNameCorrect(String name) throws BadAccountInputException  {

		if (name.contains(","))
			throw new BadAccountInputException("Name contains comma");


		return true;

	}




	public void setAccountStartDate(String startDate)  {
		try {
			Date date = dateFormat.parse(startDate);
			ACCOUNTSTARTDATE.setTime(date);
		}catch(RuntimeException re) {
			throw new BadAccountInputException("General runtime exception thrown setting start date");		
		}catch(ParseException pe) {
			throw new BadAccountInputException("Format is YYYY-MM-DD");	
		}

	}
	private void setFirstName(String firstName) {
		if (isInputNameCorrect(firstName)) this.firstName = firstName;
	}

	private void setLastName(String lastName) {
		if (isInputNameCorrect(lastName)) this.lastName = lastName;
	}

	public String getAccountNumber() {return accountNumber;}
	public String getFirstName() {return firstName;}
	public String getLastName() {return lastName;}	
	public Calendar getAccountStartDate() {return ACCOUNTSTARTDATE;}

	public String toString() {
		return "Customer name: " + getFirstName() + " " + getLastName() 
		+ "\nCustomer Account number: " + getAccountNumber()
		+ "\nAccount Created: " + getAccountStartDate().getTime().toString();
	}

}
