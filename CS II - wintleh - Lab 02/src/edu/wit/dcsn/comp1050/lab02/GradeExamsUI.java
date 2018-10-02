package edu.wit.dcsn.comp1050.lab02 ;

import java.util.Scanner ;

/**
 * Comp 1050-01 - Computer Science II
 * Lab 2 - Grade Multiple Choice Exams
 * Hunter Wintle
 * Due: M 2/19/2018
 */

/**
 * @author Hunter Wintle
 * @version 1.0.0 2018-02-13
 *
 *         Create a class to grade student responses to a multiple choice test.
 *         You will create a single-dimensional array for the answer key and a
 *         multi(two)-dimensional array to hold the students’ answers.
 *         Prompt the user to enter the answer key, number of students taking the
 *         exam, and the answers for each student.
 *         <ul>
 *         <li>You must use the provided stub methods.
 *         <li>Complete all Javadoc comment blocks.
 *         <li>Properly document your code with line and/or non-Javadoc block comments.
 *         <li>Call the methods in GradeExams to grade the exams.
 *         </ul> 
 */
public final class GradeExamsUI
    {
    /**
     * prevent instantiating this class
     */
    private GradeExamsUI() {}
    

    /**
     * Application to grade multiple-choice exams
     *  
     * @param args -unused-
     */
    public static void main( String[] args )
        {
        // descriptive introductory text
        displayIntro() ;
        
        // access the keyboard so user can provide the answer key and students' answers
        Scanner keyboard =            new Scanner( System.in ) ;
        
        // get the answer key
        char[] answerKey =            getAnswerKey( keyboard ) ;
        
        // get the students' answers
        char[][] studentsAnswers =    getStudentsAnswers( keyboard, answerKey ) ;
        
        // release Scanner/keyboard resources
        keyboard.close();
        
        // grade the exams: displays the results on a per-student basis and summary statistics
        GradeExams.gradeAllExams( answerKey, studentsAnswers );
        
        }
    
    
    /**
     * Utility method to display identifying information and a description of what it does
     */
    private static void displayIntro()
        {
        System.out.println( "Comp 1050, Computer Science II" ) ;
        System.out.println( "Lab 2: Grade Exams" ) ;
        System.out.println(  ) ;
        System.out.println( "This class grades multiple-choice exams given user-supplied" ) ;
        System.out.println( "student answers and answer key.  Information about each student's" ) ;
        System.out.println( "exam results are displayed, followed by a summary of the performance" ) ;
        System.out.println( "of all students on a per-question basis." ) ;
        }
    
    
    /**
     * Gets the input as a String and turns it into a char array
     * 
     * @param input Scanner object to read input
     * @return the user-supplied answer key
     */
    private static char[] getAnswerKey( Scanner input )
        {
    	// Prompts user for the answer key
    	// Saves answer key as a String
        System.out.println("\nEnter the answer key: ");
        String correctAnswers = input.nextLine();
        
        // Turn String into a char array
        char[] answerKey = correctAnswers.toCharArray();
        
        // Return char array
        return answerKey ;
        }
    
    
    /**
     * Creates a two-dimensional array of the student answers from user inputs
     * The first dimension is for each student
     * The second dimension is that student's answers
     * 
     * 
     * @param input Scanner object to read input
     * @param answerKey the set of correct answers
     * @return two-dimensional array with all the answers from each student
     */
    private static char[][] getStudentsAnswers( Scanner input,
                                                char[] answerKey )
        {
    	// Prompt user for students that took the exam
    	// numberOfExams set to whatever the user enters
    	System.out.println("Enter the number of students that took the exam: ");
    	int numberOfExams = input.nextInt();
    	
    	// First dimension of array is the number of students that took the exam
    	// Second dimension is for the answers from the student
        char[][] studentsAnswers = new char[numberOfExams][];	
        
        input.nextLine(); // Used to move the Scanner cursor to the correct location
        
        // Prompts user for each a student's answers
        // Saves the input as a String
        // Turns String into char array and puts each char array into an array studentsAnswers
        for(int student = 0; student < numberOfExams; student++) {
        	// Student is 0 indexed, add 1 to make it 1 indexed
        	System.out.printf("Enter the answers for student %d: %n", student + 1);
        	String studentAnswersString = input.nextLine();
        	studentsAnswers[student] = studentAnswersString.toCharArray();
        }
        
        return studentsAnswers ;
        }
        
    }
