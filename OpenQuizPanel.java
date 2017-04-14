//Manu Kondapaneni
//OpenQuizPanel.java
//This class is for the open ended quiz
import java.awt.*;


import javax.swing.*;


import java.awt.event.*;

public class OpenQuizPanel extends JPanel 
{
	private String ans;
	private String ques;
	private JTextField field;
	private QuizTests qt;
	private boolean correct;
	private boolean entered;
	private Keys k;
	private int right;
	private int submit;
	
	public OpenQuizPanel( QuizTests qt)
	{
		
		correct = false;
		entered = false;

		setLayout( new BorderLayout());
		
		this.qt = qt;
		ques = "";
		ans = "";
		field = new JTextField("Enter Answer", 50);//initJText Arrea
		Text txt = new Text();//handler Class
		k = new Keys();
		field.addActionListener(txt);
		field.addKeyListener(k);
		add(field,BorderLayout.SOUTH);
		

		//FlowLayout
		
	}
	
	public void start()
	{
		
		submit = qt.getSplash().getQp().getSubmit();
		right = qt.getSplash().getQp().getRight();
		
		ques = qt.getQuestion();
		ans = qt.getAnswer();
		
		

	}
	
	public void paintComponent(Graphics g)//public Paint component(g)
	{
		//Draws questiion
		setBackground(Color.YELLOW);
		super.paintComponent(g);
		g.setColor(Color.BLUE);
		Font font = new Font("Serif",Font.BOLD,50);
		g.setFont(font);
		g.drawString(ques,375,50);
		g.drawString("Answer Here", 0,650);//Choose  question with and drawString
		
		if(correct && entered)				
			g.drawString("Good Job!!",500,100);
		else if (entered)
			g.drawString("Incorrect!!",500,100);
			
		//qt.getSplash().getQp().setSubmit(submit);
		//qt.getSplash().getQp().setRight(right);
			//g.drawString("That's the wrong answer!",500,100);//draw String with question
		//if Answermatches
		
	}
	
	public void reset()
	{
		correct = false;
		entered = false;		
	}
	
	class TextTimerActionListener implements ActionListener {
		
		private Timer t;
	
		public void setTimer(Timer t) {
			this.t = t;
		}
		
		public void actionPerformed(ActionEvent e) 
		{

			t.stop();
			reset();

			if(submit == 5 && right >=4)
			{	
				qt.getSplash().getQp().setSubmit(0);
				qt.getSplash().getQp().setRight(0);
				qt.getSplash().getGamePanel().newLevel(false);
				qt.getSplash().getGamePanel().restartGame(true);


			}	
			else if(submit == 5)
			{	
				qt.reset();
				qt.assignQuestion();


				qt.getSplash().getQp().setSubmit(0);
				qt.getSplash().getQp().setRight(0);

				qt.getSplash().getGamePanel().restartGame(false);

				start();
			} 
			else 	
			{
				qt.assignQuestion();
				ques = qt.getQuestion();
				ans = qt.getAnswer();
			}	

			OpenQuizPanel.this.repaint();
		}

	}
	
	class Text implements ActionListener
	{
		
		private Timer t;
		private TextTimerActionListener al;

		public Text() {
			al = new TextTimerActionListener();
			t = new Timer(1000, al) {};	
			al.setTimer(t);
		}
		
		
		//Text field to decide answer
		public void actionPerformed(ActionEvent e) 
		{
			field.setText("");
			String commandTF = e.getActionCommand();
			if(commandTF.equalsIgnoreCase(ans)) 
			{
				right ++;
				correct = true;
			
			} 
			else  
			{
				correct = false;
			}
			
			if (t.isRunning() == false)
				t.start();
			
			OpenQuizPanel.this.repaint();
		}
		
		//if Text area matches question retrun true
		//else false
	}

	class Keys implements KeyListener
	{

		
		public void keyTyped(KeyEvent e) 
		{
			
			
		}

		
		public void keyPressed(KeyEvent e) 
		{			
			if(e.getKeyChar() == KeyEvent.VK_ENTER)
			{	
				submit ++;				
				entered = true;
			}
		}


		
		public void keyReleased(KeyEvent e) 
		{
			
			
		}
		
	}
}	
