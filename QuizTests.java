//Manu Kondapaneni
//This class is to read a file and assign questions
import java.io.File;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class QuizTests 
{
	
	private File ioFile;
	private File ioFile2;
	private File ioFile3;
	private File ioFile4;
	private File ioFile5;
	
	private Scanner input;
	private String[] questions;//two arrays
	private String[] answers;
	private int[]    questionsTracker;
	private int index, rand;//random and index int
	private String question; 
	private String answer;//Strings for holding answers
	private String fake;
	private String fake2;
	private String[]randAnswer;
	private Splash s;
	int numQues;
	//field var = boolean, button

	public QuizTests(Splash p)//constructor
	{//initialize
		s = p;
		numQues = 5;
		
		ioFile = new File("ques.txt");
		ioFile2 = new File("ques2.txt");
		ioFile3 = new File("ques3.txt");
		ioFile4 = new File("ques4.txt");
		ioFile5 = new File("ques5.txt");

		questions = new String[numQues];//String array Q's
		answers = new String[numQues];
		randAnswer = new String [numQues];//String array a's
		questionsTracker = new int[numQues];
		
		for(int i =0; i<answers.length;i++)
		{	
			answers[i] = "";
			questions[i] = "";
			randAnswer[i] = "";
			questionsTracker[i] = -1;
		}

		index = rand= 0;//int index, rand 
		question = "";
		fake = "";
		fake2 = "";
		answer = "";

	}
	
	public void run()
	{
		readFile();
		assignQuestion();

	}	
	
	public void reset() 
	{
		for(int i =0; i<answers.length;i++)
		{	
			questionsTracker[i] = -1;
		}
	}
	
	public boolean isNewQuestion(int index) 
	{
		if(questionsTracker[index] == -1)
			return true;
		else
			return false;
	}

	public void readFile()//readFile()
	{

		try
		{
			//based on what leel choose file for difficulty degree
			if(s.getLevel() == 1)
				input = new Scanner (ioFile);
			else if(s.getLevel() == 2)
				input = new Scanner (ioFile2);
			else if(s.getLevel() == 3)
				input = new Scanner(ioFile3);
			else if(s.getLevel() == 4)
				input = new Scanner(ioFile4);
			else if(s.getLevel() == 5)
				input = new Scanner(ioFile5);
				
		}
		catch (FileNotFoundException e)
		{
			System.out.println("ERROR: Cannot read file Questions.txt");
			System.exit(1);
		}	
		while (input.hasNextLine() )
		{	
			questions [index] = input.nextLine();//put all questions which are right before the answer in 1st array
			answers [index] = input.nextLine();//put all questions which are right before the answer in 1st array
			index++;
			//at the same time put them in the second array so q's and A's line up	
		}
	}	

	public void assignQuestion()
	{	
		
		index = rand= 0;//int index, rand 
		question = "";
		fake = "";
		fake2 = "";
		answer = "";
		
		//This is to assign a question, an answer, and two wrong answers for QuizPanel and OpenQuiz
		for(int i = 0; i<answers.length;i++)
		{
			randAnswer[i] = answers[i];//variables to get wrong answer
		}
		
		//if bool put == falseSo array wont be re assigned
		//file will be set up
		//x^2+4x+4 = 0
		//-2,2
		for(int i = 0; i < answers.length; i++)
		{
			rand = (int)(Math.random()*numQues+0);//Random everything
			if(isNewQuestion(rand))
				i = 100;
		}	//rand = math.Random having ranges between arrays
		
		
		answer = answers[rand];
		question = questions[rand];//assign vars to question and answers
		
		//Put in question and answer
		questionsTracker[rand] = 1;//answers[rand] = "";
		//questions[rand] = "";
		
		for(int x = 0; x<answers.length; x++) // Making wrong answers for my multiple choice questions
		{	
			rand = (int)(Math.random()*numQues+0);//call from a random spot not 0 in my arrow
		
			if(!randAnswer[rand].equals(answer))//make sure wrong answers aren't the right answer
			{
				fake = randAnswer[rand];				
				x = 100;
			}
			
		}

		for(int x = 0; x<answers.length; x++) // Making wrong answers for my multiple choice questions
		{
			rand = (int)(Math.random()*numQues+0);
			
			if(!randAnswer[rand].equals(answer)
					&& !randAnswer[rand].equals(fake))//make sure no duplicates
			{
				fake2 = randAnswer[rand];//make the array there an empty String
				x = 100;
			}
		}	
		
		
	}

	public String getQuestion()
	{		
		return question;
	}

	public String getAnswer()
	{
		return answer;
	}

	public void setQuestion(String q)
	{
		question = q;
	}

	public void setAnswer(String q)
	{
		answer = q;
	}

	public String getFake() 
	{
		return fake;
	}

	public void setFake(String fake) 
	{
		this.fake = fake;
	}

	public String getFake2() 
	{
		return fake2;
	}

	public void setFake2(String fake2) 
	{
		this.fake2 = fake2;
	}

	public Splash getSplash() 
	{
		return s;
	}

	public void setSplash(Splash s) 
	{
		this.s = s;
	}
	
}