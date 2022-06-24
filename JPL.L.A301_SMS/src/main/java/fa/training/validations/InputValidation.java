package fa.training.validations;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class InputValidation {
	static final Scanner sc = new Scanner(System.in);

	public String inputString() {
		while(true) {
			try {
				String input =  sc.nextLine();
				if(input == null || input.replaceAll("[ ]+", "").equals("")) {
					System.out.println("Can not input blank value");
					continue;
				}
				return input;
			}
			catch(Exception e) {
				continue;
			}
		}
	}
	public int inputInt() {
		while(true) {
			try {
				return Integer.parseInt(sc.nextLine());
			}catch(NumberFormatException e) {
				System.err.println("Invalid data");
				continue;
			}
		}
	}
	public Date inputDate() {
		while(true) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	        sdf.setLenient(false);
	        String inputDate = sc.nextLine();
	        try{
	        	java.util.Date parsed = sdf.parse(inputDate); // parse to format MM/dd/yyyy
	            java.sql.Date dateConvert = new java.sql.Date(parsed.getTime()); //try to convert to sql Date
	            return dateConvert;
	        }
	        catch(Exception e)
	        {
	            System.err.println(inputDate+" is not a valid Date");
	            continue;
	        }
		}
	}
	public char inputYesNo() {
		while(true) {
			char choice = sc.next(".").toLowerCase().charAt(0);
			if(choice == 'y' || choice == 'n') {
				sc.nextLine();
				return choice;
			}
			System.err.println("Y/y or N/n only"); 
		}
	}
	
	public String pauseScreen() {
		return sc.nextLine();
	}
	
	public double inputDouble() {
		while(true) {
			try {
				return Double.parseDouble(sc.nextLine());
			}catch(NumberFormatException e) {
				System.err.println("Invalid data");
				continue;
			}
		}
	}
}
