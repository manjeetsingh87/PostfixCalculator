package com.athena.calculator;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * @author : Manjeet Singh
 * Code : postfix calculator
 */

public class PostfixCalculator {
	
	/**
	 * This class takes an array of numeric data followed by the operation to be done on those numbers and outputs the result 
	 * on the console to the user.
	 * It will perform one operation for every line of user input. The currently supported operations are +, -, *, /. 
	 * We can easily extend it to support more operations by adding the operation to the private method validateOperandInput(String operation)
	 * at line 119, assuming the rest of the code hasn't been changed.
	 * 
	 * Validation has been used to check if the user input is only numeric data which would be integer or a float. 
	 * Any other type of input(letters or special characters) would result in an error message to the user.
	 * Also, a validation to check the type of operation is in place to ensure only the aforementioned operators can be used.
	 * 
	 * For testing the post-fix calculator program, we can manually insert data input on the console.
	 * Example: 10 100 1000 5000 * 
	 * Another way of testing this would be, if the main function can be called from a testing tool like j-unit or we can replace the console input 
	 * by a file reader which has the input data along with its operations and the output of all the operations would be written to a new file
	 * (The file reader suggested in the comments for testing hasn't been implemented in this code here but can be implemented very
	 * easily by replacing the input reader by a file reader and a file writer)
	**/
	public static void main(String[] args) {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		System.out.println("Please enter your input in the form: num1 num2 operator.\n Example: 2 3 * \n");
		try {
			while(!input.equals("exit")){
				System.out.println("\nPlease enter Input \n");
				input=br.readLine().trim();
				if(input.equalsIgnoreCase("exit")) {
					System.out.println("Exited from the Postfix Calculator Program.");
					System.exit(0);
				}
				String[] parts=input.split(" ");
				
				String operand = parts[parts.length-1];
				
				String[] inputNumbers = new String[parts.length-1];
				for(int i=0; i<parts.length-1; i++) {
					inputNumbers[i] = parts[i];
				}
				
				if(!validateOperandInput(operand)) {
					System.out.println("Please enter a valid operation type. Expected operands ae +, -, *, /.");
				} else if(!validateNumberInput(inputNumbers)) {
					System.out.println("Please enter only numeric data.");
				} else{
					float calcuatedOutput = calculate(inputNumbers, operand);
					if(calcuatedOutput != Float.POSITIVE_INFINITY)
						System.out.println("Output is: "+calcuatedOutput);
					else
						System.out.println("Divison by zero is not a valid operation.");
				} 
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Encountered an exception: "+e.getMessage());
		}
	}
	
	private static float calculate(String[] inputNumbers, String operand) {
		float result = 0;
		boolean first = true;
		switch (operand) {
		case "+":
			for(String inputNum : inputNumbers) {
				result+= Float.parseFloat(inputNum);
			}
			break;
		case "-":
			for(String inputNum : inputNumbers) {
				if(first) {
					result= Float.parseFloat(inputNum);
					first = false;
				} else {
					result = result-Float.parseFloat(inputNum);
				}
			}		
					break;
		case "*":
			for(String inputNum : inputNumbers) {
				if(first) {
					result= Float.parseFloat(inputNum);
					first = false;
				} else {
					result = result*Float.parseFloat(inputNum);
				}
			}
			break;	
		case "/":
			for(String inputNum : inputNumbers) {
				if(first) {
					result= Float.parseFloat(inputNum);
					first = false;
				} else if(inputNum == "0"){
					result = Float.POSITIVE_INFINITY;
				} else {	
					result = result/Float.parseFloat(inputNum);
				}
			}
			break;	
		default:
			break;
		}
		return result;
	
	}
	
	
	private static boolean validateNumberInput(String[] inputNumbers) {
		for(String input : inputNumbers) {
			String pattern1 = "[\\-\\+]?[0-9]*(\\.[0-9]+)";
			String pattern2 = "-?[0-9]+";
			if(!input.matches(pattern1) && !input.matches(pattern2)) 
				return false;
		}
		return true;
	}
	
	private static boolean validateOperandInput(String operator) {
		if(operator.matches( "[-+*/]" ) )
            return true;

        return false;
	}
}
