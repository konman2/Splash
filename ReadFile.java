//Manu Kondapaneni
//4/19/16
//ReadFile.java
//Testing:Make sure clear to user
//Test if all JRadio Buttons work and asdsigned
//import File libraries
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile
{
	private File ioFile;
	private Scanner input;
	String[] questions;//two arrays
	String[]answers;
	int index,rand;//random and index int
	String question; 
	String answer;//Strings for holding answers
	
	public ReadFile()
	{
		ioFile = new File("gameQuestions.txt");
		 questions = new String[25];//String array Q's
		answers = new String[25];//String array a's
		for(int i =0; i<answers.length;i++)
		{	
			answers[i] = "";
			questions[i] = "";
		}
			
		index =rand= 0;//int index, rand 
		answer = "";//String question,answer
		question = "";
	}
	
	public void readFile()//readFile()
	{
		
		try
		{
			input = new Scanner (ioFile);
		}
		catch (FileNotFoundException e)
		{
			System.out.println("ERROR: Cannot read file gameQuestions.txt");
			System.exit(1);
		}	
		while (input.hasNext() )
		{	
			questions [index] = input.nextLine();
			answers [index] = input.next();//put all questions which are right before the answer in 1st array
			input.nextLine();
			index++;//at the same time put them in the second array so q's and A's line up	
		}
	}	
	
	public void assignQuestion()
	{
		
		//if bool put == falseSo array wont be re assigned
		//file will be set up
		//x^2+4x+4 = 0
		//-2,2
		for(int i = 0; i<answers.length;i++)
		{
			rand = (int)(Math.random()*25+0);
			if(!questions[rand].equals(""))
				i = 100;
		}	//rand = math.Random having ranges between arrays
		answer = answers[rand];
		question = questions[rand];//assign vars to question and answers
		//Put in question and answer
		answers[rand] = "";
		questions[rand] = "";//make the array there an empty String
		Quiz q = new Quiz(answer,question);//call constructor with(String question)
	}
	
	public void setAnswer()//set answer
	{
		return answer;
	}
	
	public void setQuestion()//setQuestion
	{
		return question;
	}	
		
	
		
	
	
}