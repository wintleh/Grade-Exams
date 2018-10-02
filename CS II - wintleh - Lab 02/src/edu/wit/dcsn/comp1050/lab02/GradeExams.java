package edu.wit.dcsn.comp1050.lab02 ;

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
 *         The answer key will be provided as a single-dimensional array of char, where each
 *         array element corresponds with a question on the exam.  Students' answers will be
 *         provided as a multi(two)-dimensional array of char where the outer array represents
 *         a student and the inner array represents that student's answers, corresponding to the
 *         answer key.
 *         <p>
 *         Mark an answer correct if the corresponding element of the answer key matches.
 *         <p>
 *         The methods are package visible to enable your ExamGrader to access them.
 */
public final class GradeExams
    {
    /**
     * prevent instantiating this class
     */
    private GradeExams() {}
    

    /**
     * Test driver
     * 
     * @param args -unused-
     */
    public static void main( String[] args )
        {
        char[] answerKey =            "DBDCCDAEAD".toCharArray() ;  // the correct answers
        char[][] studentsAnswers =    {                             // each student's answers
                                        "ABACCDEEAD".toCharArray(),
                                        "DBABCAEEAD".toCharArray(),
                                        "EDDACBEEAD".toCharArray(),
                                        "CBAEDCEEAD".toCharArray(),
                                        "ABDCCDEEAD".toCharArray(),
                                        "BBECCDEEAD".toCharArray(),
                                        "BBACCDEEAD".toCharArray(),
                                        "EBECCDEEAD".toCharArray(),
                                        "EBECCDEEADX".toCharArray(),
                                        "EBECCDEEA".toCharArray()
                                      } ;

        
        // descriptive introductory text
        displayIntro() ;
        

        // grade the exams: display the results on a per-student basis and summary statistics
        gradeAllExams( answerKey, studentsAnswers );
        }
    
    
    /**
     * Utility method to display identifying information and a description of what it does
     */
    static void displayIntro()
        {
        System.out.println( "Comp 1050, Computer Science II" ) ;
        System.out.println( "Lab 2: Grade Exams" ) ;
        System.out.println( "Due: Su 2/18/2018" ) ;
        System.out.println(  ) ;
        System.out.println( "This test driver grades multiple-choice exams given fixed (hard-coded)" ) ;
        System.out.println( "student answers and answer key.  Information about each student's" ) ;
        System.out.println( "exam results are displayed, followed by a summary of the performance" ) ;
        System.out.println( "of all students on a per-question basis." ) ;
        }
    
    
    /**
     * Utility method to grade all exams and display summary statistics
     * 
     * @param answerKey the set of correct answers
     * @param allStudentsAnswers the answers for all of the students
     */
    static void gradeAllExams( char[] answerKey,
                               char[][] allStudentsAnswers
                               )
        {
    	
    	int[] correctAnswerCount = new int[answerKey.length]; // Keeps track of how many times a question is answered correctly - each question is at a different index
    	int totalNumberOfQuestions = answerKey.length; // Holds the total number of questions
    	int gradedTests = 0; // Keeps track of how many tests are graded
    	
    	// Prints grades header
    	System.out.println("\n-------------\nStudent Grades:\n");
    	System.out.printf("The exam contains %d questions%n", totalNumberOfQuestions);
    	System.out.print("The correct answers are: ");
    	
    	// Prints answerKey
    	for(int keyQuestion = 0; keyQuestion < totalNumberOfQuestions; keyQuestion++) {
    		System.out.print(answerKey[keyQuestion]);
    	}
    	
    	// Prints 2 new lines for style
    	System.out.println("\n");
    	
    	// Grades all the given exams
    	for(int student = 0; student < allStudentsAnswers.length; student++) {
    		// gradeAnExam returns true if it was able to grade the exam
    		if(gradeAnExam(student, answerKey, allStudentsAnswers[student], correctAnswerCount)) {
    			gradedTests++;
    		}
    	}
    	
    	// Prints statistics header
    	System.out.println("\n-------------\nExam Statistics:\n");
    	
    	// Prints statistics
    	reportStatistics(correctAnswerCount, allStudentsAnswers.length, gradedTests);
    	
        }
    
    
    /**
     * Utility method to grade a single exam
     * 
     * @param studentId the student's identification number
     * @param answerKey the set of correct answers
     * @param studentAnswers the student's answers
     * @param correctAnswerCount counters for each question to track number of times answered 
     * 		correctly
     * @return true indicates that we were able to grade the exam; false means the number of 
     * 		questions according to the answer key does not match the number of questions answered 
     * 		by the student - does not reflect whether the student 'passed'
     */
    static boolean gradeAnExam( int studentId, 
                                char[] answerKey, 
                                char[] studentAnswers,
                                int[] correctAnswerCount
                                )
        {
    	
    	// The number of questions answered must equal the number of question on the answer key
    	if(answerKey.length == studentAnswers.length) {
    	
    		// String used to print correct answers
	    	StringBuilder studentCorrectAnswers = new StringBuilder();
	    	int individualCorrectAnswerCount = 0;
	    	
	    	// Loops through all the questions to see if the student answer matches the answer key
	    	for(int question = 0; question < studentAnswers.length; question++) {
	    		// If studentAnswers[question] and answerKey[question] match then the students answer is correct
	    		// Then append the correct answer at the end of studentCorrectAnswers
	    		// Determining the match is case insensitive because both sides of comparison are changed to upper-case
	    		if(Character.toUpperCase(studentAnswers[question]) == Character.toUpperCase(answerKey[question])) {
	    			studentCorrectAnswers.append(answerKey[question]);
	    			correctAnswerCount[question]++;
	    			individualCorrectAnswerCount++;
	    		}
	    		else {
	    			// Append '-' if the answer was not correct
	    			studentCorrectAnswers.append('-');
	    		}
	    	}
			
			// Multiplying by 100.0 makes sure this does not do integer division
			double percentCorrect = (individualCorrectAnswerCount * 100.0) / answerKey.length;
			
			// studentId is 0 indexed so 1 is added so it is displayed as 1 indexed
			System.out.printf("Student %d answered %d correct (%3.0f%%): %s%n", studentId + 1, individualCorrectAnswerCount, percentCorrect, studentCorrectAnswers);
	    	
	    	return true; // The exam was graded
    	}
    	else {
    		// studentId is 0 indexed so 1 is added so it is displayed as 1 indexed
    		System.out.printf("Error: Student %d answered %d questions: ", studentId + 1, studentAnswers.length);
    		
    		// Print the answers from the student
    		for(int question = 0; question < studentAnswers.length; question++) {
    			System.out.print(studentAnswers[question]);
    		}
    		System.out.println(); // Print new line for style
    		
    		return false; // The exam was not graded
    	}    	
    	
        }
    
    
    /**
     * Utility method to display the statistics for an exam
     * 
     * @param correctAnswersCount number of correct responses per question
     * @param studentCount number of students that took the exam
     * @param gradedExamCount number of exams that were actually graded
     */
    static void reportStatistics( int[] correctAnswersCount,
                                  int studentCount,
                                  int gradedExamCount
                                  )
        {
    	// Prints the header
    	System.out.printf("%d students took the exam, %d exams were graded.%n%n", studentCount, gradedExamCount);
    	System.out.println("Question #   Times Correct");
    	
    	// Loops through correctAnswersCount
    	// Finds the percentage of the correct answers for each question
    	// Prints the percentage with the question and the raw correct answer number
    	for(int question = 0; question < correctAnswersCount.length; question++) {
    		int percentCorrect = correctAnswersCount[question] * 100 / gradedExamCount;
    		System.out.printf("%5d%18d (%3d%%)%n", question + 1, correctAnswersCount[question], percentCorrect);
    	}
    	
        }
    
    }
