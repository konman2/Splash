
//Manu Kondapaneni


//4/18/16
//P.1
//Game Project
//Quiz.java
//This is for the mc quiz
//import libraries- file,graphics, JRadioButton, 
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.Timer;


public class QuizPanel extends JPanel 
{
	private boolean correct;
	private boolean pressed;
	private boolean radioPress;
	private JButton enter;
	private JRadioButton Rbutton1;//private JRadioButton
	private JRadioButton Rbutton2;
	private JRadioButton Rbutton3;
	private int choose;//String answer
//	private OpenQuiz oq;
	private QuizTests qt;
	private int right;
	private int submit;
	private Splash splash;
	private ButtonGroup group;
	
//	public static void main(String[]args)
//	{
//		
//		QuizFrame rf = new QuizFrame();
//
//		rf.run();
//	}	
	
	public QuizPanel( Splash s, QuizTests qt)
	{
		setLayout(new FlowLayout(FlowLayout.CENTER, 500, 80)); 
		splash = s;
		correct = false;
		pressed = false;
		radioPress = false;
		submit = 0;
		right = 0;
		
		this.qt = qt;
		Rbutton1 = new JRadioButton();
		Rbutton2 = new JRadioButton();
		Rbutton3 = new JRadioButton();
		
		Radio1 r1 = new Radio1();
		Rbutton1.addActionListener(r1);

		//Math.Random() for numbers
		Radio2 r2 = new Radio2();
		Rbutton2.addActionListener(r2);

		//Math.Random for numbers
		Radio3 r3 = new Radio3();
		Rbutton3.addActionListener(r3);

		add(Rbutton1);//Radio1
		add(Rbutton2);//Radio2
		add(Rbutton3);//Radio3	
		
		group = new ButtonGroup();
		group.add(Rbutton1);
		group.add(Rbutton2);
		group.add(Rbutton3);

		enter = new JButton("Submit");
		Button but = new Button();
		enter.addActionListener(but);
		add(enter);
		setBackground(Color.BLUE);
	}
	
	public void init() 
	{
		
		/* Init quizTests which will read the questions from the file
		 * and save into an array of questions and answers
		 */
		
		//answer = ans;
		choose = 0;//inttialize other variables
		//Initialize
		//classes with handlers
		choose =(int)( Math.random()*3+1);//if math.rand btwn 1 - 3 = 1,ans is in radio 1

	
		
	
		if(choose ==1)
		{//int choose == corresponding number to Radio Button
			
			Rbutton1.setText(qt.getAnswer());
			
			//Math.Random() for numbers
			Rbutton2.setText(qt.getFake());

			//Math.Random for numbers
			Rbutton3.setText(qt.getFake2());

		}	
		else if(choose ==2)
		{//int choose == corresponding number to Radio Button
			Rbutton1.setText(qt.getFake());

			//Math.Random() for numbers
			Rbutton2.setText(qt.getAnswer());

			//Math.Random for numbers
			Rbutton3.setText(qt.getFake2());


		}	
		else
		{//int choose == corresponding number to Radio Button
			Rbutton1.setText(qt.getFake2());

			//Math.Random() for numbers
			Rbutton2.setText(qt.getFake());

			//Math.Random for numbers
			Rbutton3.setText(qt.getAnswer());	

		}	
		
	}
	
	public void reset()
	{
		group.clearSelection();
		correct = false;
		pressed = false;
		
	}
	
	public QuizTests getQuizTests()
	{
		return qt;
	}
	
	
	public void paintComponent(Graphics g)//paint Component
	{
		//Prints question and decides answer
		super.paintComponent(g);
		g.setColor(Color.YELLOW);
		Font font = new Font("Serif",Font.BOLD,50);
		g.setFont(font);
		g.drawString(qt.getQuestion(),375,50);//Choose  question with and drawString
		if(correct&&pressed)
			g.drawString("Correct", 450, 600);
		else if(pressed)
			g.drawString("Incorrect",450,600);
		
	}	
	
	class  Radio1 implements ActionListener//class Radio1
	{
		public void actionPerformed(ActionEvent e)
		{
			//Checks to see if Rbutton is the answer
			Rbutton1 = (JRadioButton)e.getSource();
			if(Rbutton1.getActionCommand().equals(qt.getAnswer())&&choose == 1)
				correct = true;
			else if(Rbutton1.getActionCommand().equals(qt.getFake())||Rbutton1.getActionCommand().equals(qt.getFake2()))//if rand num corresponds with radio button
				correct =false;
			radioPress = true;
			Rbutton1.setSelected(false);
			repaint();//repaint();

		}	
	}
	
	class Radio2 implements ActionListener//class Radio2
	{
		public void actionPerformed(ActionEvent e)
		{
			Rbutton2 = (JRadioButton)e.getSource();
			if(Rbutton2.getActionCommand().equals(qt.getAnswer())&&choose == 2)
				correct = true;//if rand num corresponds with radio button
			else if(Rbutton2.getActionCommand().equals(qt.getFake())||Rbutton2.getActionCommand().equals(qt.getFake2()))//if rand num corresponds with radio button
				correct = false;
			radioPress = true;
			Rbutton2.setSelected(false);
			repaint();//repaint();

		}	
	}
	
	class Radio3 implements ActionListener//classRadio3
	{
		public void actionPerformed(ActionEvent e)
		{
			Rbutton3 = (JRadioButton)e.getSource();
			if(Rbutton3.getActionCommand().equals(qt.getAnswer())&&choose == 3)
				correct = true;//if rand num corresponds with radio button
			else if(Rbutton3.getActionCommand().equals(qt.getFake())||Rbutton3.getActionCommand().equals(qt.getFake2()))//if rand num corresponds with radio button
				correct = false;
			radioPress = true;
			Rbutton3.setSelected(false);
			repaint();//repaint();
		}	
	}
	
	class ButtonTimerActionListener implements ActionListener 
	{
		
		private Timer t;
		
		public void setTimer(Timer t) {
			this.t =  t;
		}
		
		public void actionPerformed(ActionEvent e) 
		{
			t.stop();
			
			qt.assignQuestion();
			
			if(correct == true)
				right++;
			
			reset();
			
			init();
			submit ++;
									
			if(submit == 3)
			{
				qt.assignQuestion();					
				splash.getOq().start();
				splash.getCards().show(splash.getCardPanel(), "Open");
			}	
			
			QuizPanel.this.repaint();
		}
	}

	
	class Button implements ActionListener
	{
		
		Timer t;
		private ButtonTimerActionListener al;
		
		public Button() {

			al = new ButtonTimerActionListener();
			t = new Timer(1000, al) {};	
			al.setTimer(t);
		}
		
		public void actionPerformed(ActionEvent e)
		{
			
			enter = (JButton)e.getSource();
			if(enter.getActionCommand().equals("Submit")&&radioPress)
			{
				pressed = true;
											
				if (t.isRunning() == false)
					t.start();						
			}
			
			QuizPanel.this.repaint();
			
		}

	}	
	public int getSubmit()
	{
		return submit;
	}
	public void setSubmit(int submit)
	{
		this.submit = submit;
	}
	public int getRight() 
	{
		return right;
	}

	public void setRight(int right) 
	{
		this.right = right;
	}
	
}

//class QuizFrame extends JFrame
//{
//	private Splash s;
//	private OpenQuiz oq;
//	
//	public QuizFrame()//constructor
//	{//initialize
//		super("Splash Quiz");
//		s = new Splash();
//		setSize(1000,700);
//		setLocation(400,50);
//		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//		setVisible(true);
//
//	}
//
//	public void run() 
//	{
//
//		QuizTests qt = new QuizTests(s);
//		qt.run();
//		
//		oq = new OpenQuiz( qt);
//	
//		setVisible(true);
//	}		
//
//}