
import java.util.Scanner;

public class QueringReferences {

	private static Scanner input;
	private static Scanner stringScan;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("*******************************************************************");
		System.out.println("*******************Quering From Project Part I*****************");
		System.out.println("**********************Part II HW CS 623************************");
		System.out.println("*******************************************************************");
		System.out.println("");
		System.out.print("Press any number to start:\t");
		input = new Scanner(System.in);
		stringScan = new Scanner(System.in);
		//integer value assignment
		int value=input.nextInt();
		//string for queries
		String userInput = null;
		//long for values like ids
		long id;
		//data access objects
		ERPaperDAO testPaper=new ERPaperDAO();
		ERReviewDAO testReview= new ERReviewDAO();
		ERAuthorDAO testAuthor= new ERAuthorDAO();
		while(value>=0) {
			System.out.println("*****************************MENU**********************************");
			System.out.println("1) Press number 1 to query papers details by authors email");
			System.out.println("2) Press number 2 to query reviews details by paper's ID");
			System.out.println("3) Press number 3 to get count of all papers summitted");
			System.out.println("4) Press number 4 to create a new paper submission");
			System.out.println("5) Press number 5 to delete the frist author");
			System.out.println("6 Press number -1 to exit");
			value=input.nextInt();
			//second loop to perform the query selected
			while(value > 0) {
				//option 1
				if(value==1) {
						userInput="";
						System.out.println("Enter the author Email: \t");
						userInput= stringScan.nextLine();
						System.out.println(testPaper.searchPaperByAuthorEmail(userInput));
						value=0;
				//option 2
				}else if(value == 2) {
					try {
						System.out.println("Enter the paper's ID: \t");
						id= input.nextLong();
						System.out.println(testReview.searchReviewByPaperIdANDpublished(id));
						//get out from the loop
						value=0;
					} catch (Exception e) {
						e.printStackTrace();
					}
					//option 3
				}else if(value==3) {
					try {
						System.out.println("THE NUMBER OF ROWS IN PAPER TABLE IS:\t"+testPaper.countOfPapers());
						//get out from the loop
						value=0;
					} catch (Exception e) {
						e.printStackTrace();
					}
				//option 4
				}else if(value == 4) {
					try {
						//values to be added to the database
						String firstName="", lastName="", email="", title="", abstractPaper="", fileName="";
						long idPaper;
						//user input for first name
						System.out.println("Enter author first name: ");
						firstName=stringScan.nextLine();
						//user input for last name
						System.out.println("Enter author last name: ");
						lastName=stringScan.nextLine();
						//user input for email
						System.out.println("Enter Email address: ");
						email=stringScan.nextLine();
						//user input for id paper
						System.out.println("Enter paper id: ");
						idPaper= input.nextLong();
						//user input for title 
						System.out.println("Enter paper title: ");
						title= stringScan.nextLine();
						//user input for abstract
						System.out.println("Enter paper abstract: ");
						abstractPaper= stringScan.nextLine();
						//user input for file name
						System.out.println("Enter paper file_name: ");
						fileName= stringScan.nextLine();
						//calling method to add paper to the database, adds a paper and its respective author
						testPaper.addPaper(new ERPaper(idPaper,title, abstractPaper, fileName, email), 
								new ERAuthor(email, firstName, lastName));
						//get out from the loop
						value=0;
					} catch (Exception e) {
						e.printStackTrace();
					}
				//delete author option
				}else if(value==5) {
					try {
						testAuthor.deleteAuthor();
						value=0;
					}catch (Exception e) {
						e.printStackTrace();
					}
				//default option user input wrong
				}else {
					System.out.println("Unavailable, try again");
					value=0;
				}
			}
		}
		System.out.println("Thanks");
		System.out.println("END");
	}
}