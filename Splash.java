
//Manu Kondapaneni

//4-19-16
//Game.java
//This will hold the Game JFrame, the start panel with two buttons and the instructions with two buttons,
// and the actual JPanel for the Game
//import:
//Graphics
//Timer
//Threads
//JFrame
//JPanel
//JSlider
//images
import java.awt.*;


import java.awt.event.*;
import javax.swing.*;

public class Splash extends JFrame
{
	private Font infoFont;
	private Font instFont;
	private Timer arrowTimer;
	private CardLayout cards;//JFrame
	private CardHolder cardPanel;
	private StartPanel sp;
	private Instructions i;
	private QuizInfo qi;
	private GamePanel gp;
	private QuizPanel qp;
	private OpenQuizPanel oq;
	private GameInfo gf;
	private QuizTests qt;
	private boolean arrowTimerEnabled;
	private int level;

	//JFrame
	public Splash()
	{
		super("Splash");//JFrame
		setSize(1000,700);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		level = 1;
		
		cards = new CardLayout();
		qt = new QuizTests(this);
		sp = new StartPanel();
		i = new Instructions();
		qi = new QuizInfo();
		gp = new GamePanel(this);
		qp = new QuizPanel(this, qt);
		oq = new OpenQuizPanel(qt);
		gf = new GameInfo();
		
		cardPanel = new CardHolder(cards);
		
		setContentPane(cardPanel);
		setVisible(true);	//initialize
	}	
	

	public int getLevel()
	{
		return level;
	}
	
	public void setLevel(int level)
	{
		this.level = level;
	}
	
	public CardLayout getCards() 
	{
		return cards;
	}

	public void setCards(CardLayout cards) 
	{
		this.cards = cards;
	}

	public CardHolder getCardPanel() 
	{
		return cardPanel;
	}

	public void setCardPanel(CardHolder cardPanel) 
	{
		this.cardPanel = cardPanel;
	}

	public QuizTests getQuizTests()
	{
		return qt;
	}
	
	public OpenQuizPanel getOq() {
		return oq;
	}

	public void setOq(OpenQuizPanel oq) {
		this.oq = oq;
	}

	public GamePanel getGamePanel()
	{
		return gp;
	}
	
	
	public QuizPanel getQp() 
	{
		return qp;
	}

	public void setQp(QuizPanel qp) 
	{
		this.qp = qp;
	}


	/*
	 * Panel for card Holder layout
	 */
	class CardHolder extends JPanel
	{
		public CardHolder(CardLayout layout)
		{
			super(layout); 
			
			add(sp,"Start");
			add(i,"Instruct");
			add(qi,"quizInfo");
			add(gf,"gameInfo");
			add(gp,"Game");
			add(oq,"Open");
			add(qp,"Mc");
			
		}
	}
		
	
	
////////////////////////////////////////////////////////////////
	//This class will either start the game or call Instructions
	class StartPanel extends JPanel 
	{

		private Image imageSteph;//image of Stephen Curry
		private Image image;//image of klay thompson
		private Image imageBall;//image of basketball

		private JButton start;//starts the game
		private JButton info;//goes to the instructions
		
		private Font font;
		
		//private  Instructions i;
		//buttons
		public StartPanel()
		{
			
			setLayout(new FlowLayout(FlowLayout.CENTER,500,170));
			JButton start = new JButton("START GAME");
			StartButton sb = new StartButton();
			start.addActionListener(sb);
			add(start);
			JButton info = new JButton("Instructions");
			InfoButton ib = new InfoButton();//Jpanel things
			info.addActionListener(ib);
			add(info);

		}
		
		public void paintComponent(Graphics g)
		{
			setBackground(Color.YELLOW);//for the startpanel
			image = Toolkit.getDefaultToolkit().getImage("KlayThompson.jpg");// drawingimage
			imageSteph = Toolkit.getDefaultToolkit().getImage("Steph.jpg");
			imageBall = Toolkit.getDefaultToolkit().getImage("Ball.jpg");

			super.paintComponent(g);//warriors colored theme
			g.drawImage(imageSteph,500,60,this);
			g.drawImage( image,100,60,this);//image drawing remember top left 
			g.setColor(Color.BLUE);
			Font font = new Font("Serif",Font.BOLD,100);
			g.setFont(font);
			g.drawString("SPLASH", 330,150);

		}
		
		class InfoButton implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				info = (JButton)(e.getSource());// using my cardPanel this will call the instructionsPanel 
				if(info.getActionCommand().equals("Instructions"))	//same thing in Start except it will call my gamePanel
					cards.show(cardPanel,"Instruct");
			}
			
			//if(button1 pressed call game panel
			//else instructions
		}
		
		class StartButton implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				start = (JButton)(e.getSource());
				if(start.getActionCommand().equals("START GAME"))
				{
					cards.show(cardPanel, "Game");
				}	
			}
		
			//if(button1 pressed call game panel
			//else instructions
		}
	}
	
///////////////////////////////////////////////////////////////////////////////////////	
	class Instructions  extends JPanel
	{
		private JButton quizInst;
		private JButton gameInst;//buttons
		private JButton back;
		public Instructions()
		{
			setBackground(Color.BLUE); 
			
			
			
			setLayout(new FlowLayout(FlowLayout.CENTER,500,100));
			quizInst = new JButton("Quiz Instructions");
			QuizInfoButton  qib = new QuizInfoButton();
			quizInst.addActionListener(qib); //initialize
			add(quizInst);
			gameInst = new JButton ("Game Instructions");
			GameInfoButton gib = new GameInfoButton();
			gameInst.addActionListener(gib);
			add(gameInst);
			BackButton bb = new BackButton();
			JButton back = new JButton("Go To Title");
			back.addActionListener(bb);
			add(back);
		}	
		
		//This class is very simple. It will use CardLayout to call either the quiz Instructions and help or the game instructions 
		class QuizInfoButton implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				quizInst = (JButton)(e.getSource());
				if(quizInst.getActionCommand().equals("Quiz Instructions"))//if button quiz or 
					cards.show(cardPanel,"quizInfo");//else button for game instructions
			}
		}
		
		class GameInfoButton implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				gameInst = (JButton)(e.getSource());
				if(gameInst.getActionCommand().equals("Game Instructions"))//if button quiz or 
					cards.show(cardPanel,"gameInfo");
			}	
		}
		
		class BackButton implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				back = (JButton)(e.getSource());
				if(back.getActionCommand().equals("Go To Title"))
					cards.show(cardPanel,"Start");
			}
		}
	}

	class QuizInfo extends JPanel
	{
		private Image factoring;
		private Image factoring2;
		private JButton back;
		public QuizInfo()
		{
			setLayout(new FlowLayout(FlowLayout.LEFT));
			BackButton bb = new BackButton();
			back = new JButton("Go To Title");
			
			back.addActionListener(bb);
			add(back);
			setBackground(Color.YELLOW);
		}
		
		public void paintComponent(Graphics g)
		{
			//QuizInfo is only graphics and Images
			factoring = Toolkit.getDefaultToolkit().getImage("Factoring.jpg");
			factoring2 = Toolkit.getDefaultToolkit().getImage("Factoring2.jpg");
			
			
			
			super.paintComponent(g);
			g.setFont(infoFont);//This is a title size Font
			g.drawString("Multiple Choice",460,30 );//g.drawString instructions
			g.setFont(instFont);//this is a text size font
			//Drawing Strings
			g.drawString("For the multiple choice quiz simply select your answer from those given. If it is the correct answer you will see correct on the screen",10,50);
			g.setFont(infoFont);
			g.drawString("Open Ended Quiz",440,80);
			g.setFont(instFont);
			g.drawString("For the open-ended quiz please enter your answer with the smallest number first followed by a comma and then the other number with ",10,110);
			g.drawString("no spaces in between in the form of -4,6. Remember each problem is set equal to 0, so it is the opposite of your factors. ",10,130);
			g.drawString("Type No if there is no solution", 10, 150);
			g.setFont(infoFont);
			g.drawString("How To Factor",440,250);
			//Drawing Images
			g.drawImage(factoring,0,300,500,360,this);
			g.drawImage(factoring2,500,300,500,360,this);
		}
		
		class BackButton implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				back = (JButton)(e.getSource());
				if(back.getActionCommand().equals("Go To Title"))
					cards.show(cardPanel,"Start");
			}
		}

	}
	
	class GameInfo extends JPanel
	{
		//GameInfo is only graphics and Images
		private Image image;
		private JButton back;
		public GameInfo()
		{
			setLayout(new FlowLayout(FlowLayout.LEFT));

			BackButton bb = new BackButton();
			back = new JButton("Go To Title");
			back.addActionListener(bb);
			add(back);
		}
		
		public void paintComponent(Graphics g)
		{
			image = Toolkit.getDefaultToolkit().getImage("Dubnation.jpg");
			//Font font1 = new Font("Serif",Font.BOLD,50);
			setBackground(Color.BLUE);
		
			
			super.paintComponent(g);
			g.setColor(Color.YELLOW);
			g.setFont(infoFont);
			//Drawing Strings
			g.drawString("How To Play",440,30);
			g.setFont(instFont);
			g.drawString("To start click the screen with your mouse",200,50);
			g.drawString("To shoot the ball simply stop the arrow going in an arc when it reaches the direction you will shoot ",200,70);
			g.drawString("using the spacebar. Remember that the ball will travel in the direction of the line. ",200,90);
			g.drawString("Click your mouse on the screen to shoot the ball again or continue onto the quiz. If you make 5 shots you ",200,110);
			g.drawString("will move onto the quiz which will consist of 3 multiple choice questions and two open ",200,130);
			g.drawString("ended questions. You will move on to the next level only if you score 4/5 on the quiz.", 200, 150);
			//Drawing Image
			g.drawImage(image,200,200,this);
		}	//g.drawString instructions
		
		class BackButton implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				back = (JButton)(e.getSource());
				if(back.getActionCommand().equals("Go To Title"))
					cards.show(cardPanel,"Start");
			}
		}
	}
	
	
	class GamePanel extends JPanel 
		implements KeyListener,	MouseListener
	{
		//set up Panel
		
		private TimerHandler th;
		private int shots;
		private ArcPanel ap;
		private Image ball;
		private Image hoop;
		private int ballX;
		private int ballY;
		private boolean drawBall;
		private boolean splash;
		private int counter;
		private int timerSpeed;
		private boolean drawLevel;
		private QuizTests qt;
		private int levels;
		private Splash s;
		private JButton back;
		private boolean end;
		//3 booleans
		
		public GamePanel(Splash t)
		{
			end = false;
			s = t;
			levels = 5;
			drawLevel = true;
			qt = s.getQuizTests();
			timerSpeed = 100;
		 
			setLayout(new FlowLayout(FlowLayout.LEFT));
			BackButton bb = new BackButton();
			back = new JButton("Go To Title");
			
			back.addActionListener(bb);
			add(back);
			addMouseListener(this);
            addKeyListener(this);
			setLayout(new BorderLayout());
			hoop = Toolkit.getDefaultToolkit().getImage("hoop.jpeg");
			ball = Toolkit.getDefaultToolkit().getImage("basketball.png");
			shots = 500;
			counter = 0;
			
			
			ap = new ArcPanel();
			ap.findArc(); // finds the points for an arc pattern
			
			th = new TimerHandler(this,ap);
			arrowTimer = new Timer(timerSpeed,th);

		}//initialize
		
		public void run()
		{
			if(level<=5)
			{
				if(counter == levels )
				{		
					newLevel(true);
				} 
				else 
				{
				
					if(counter<levels)
					{					
						if(!arrowTimer.isRunning())
						{	
							init();
						}	
					}
				
				}
			}
		}	
		
		public void newLevel(boolean restart)
		{	
			qt.readFile();
			qt.assignQuestion();
			
			qp.init();
			
			if(qp.getSubmit() == 3)
				cards.show(cardPanel, "Open");
			else if(restart)
				cards.show(cardPanel, "Mc");
			//drawLevel = true;
			
			counter = 0;
		}
		
		public void restartGame(boolean whichPanel)
		{
			if(whichPanel)//chooses whether to go to the next level or start over
			{
				level ++;
				if(level > 5) 
				{
					end = true; 
				} else 
				{
					timerSpeed -= 20;
					arrowTimer = new Timer(timerSpeed,th);
					init();
				}
				cards.show(cardPanel,"Game");
			}			
			else 
			{
				cards.show(cardPanel,"Mc");
			}			

		}
		
		public void init() //reset everything
		{
			ap.computeHoopIndex();
			ballX = 0;
			ballY = 0;
			drawBall = true;
			splash = false;
			arrowTimer.start();
			arrowTimer.setCoalesce(true);
			arrowTimerEnabled=true;
		}
		

		public void paintComponent(Graphics g)//paint Component
		{
			//Draws everything
			//for loop
			setBackground(Color.BLUE);
			super.paintComponent(g);
			Font font = new Font("Serif",Font.BOLD,50);
			g.setFont(font);	
			g.setColor(Color.YELLOW);
			
			if(end) 
			{
				g.drawString("CHAMPIONS!!", 450,50 );
				return;
			}
			
			if(drawLevel)
				g.drawString("SPLASH", 450, 50);
			//
			//	g.fillOval(nextX, nextY, 100,100);
			g.drawImage(hoop, ap.getHoopX()[ap.getHoopIndex()]-80, ap.getHoopY(), this);
			g.fillRect(ap.getHoopX()[ap.getHoopIndex()]+15, ap.getHoopY()+140, 
					10, 10);
			
			int hoopX = ap.getHoopX()[ap.getHoopIndex()]+15;//+15 is for offset of top left corner
			int hoopY = ap.getHoopY()+140;
			
			if(ballX>0 && ballY<1000 && drawBall ) 
			{
				g.drawImage(ball, ballX-25, ballY-25, 50,50, this);//Offsets points that I found in shot
			}
			
			
			if(ballX >= hoopX - 50 &&
					ballX  <= hoopX + 50 )// if the ball is at the hoop position at x
			{
			
			  if(ballY >= (hoopY - 50)  && 
					ballY  <= (hoopY + 50) )// if it equals the y hoop
			  {			 
					drawBall = false;//stopDrawing
					splash = true;
					counter ++;
			  }
	
			}
			
			if (splash == true) 
			{
				g.drawString("SPLASH!", 475, 400);
				
			}

					
			g.drawOval(ap.getPointsX()[ap.getIndex()] - 25,ap.getPointsY()[ap.getIndex()] - 25, 50, 50);//draw ball going around arc

			g.drawLine(450, 650, ap.getPointsX()[ap.getIndex()],
					ap.getPointsY()[ap.getIndex()]);
			
			//drawImage of ball
				
		}	
		

/////////////////////////////////useless//////////////////////////////////////////////////		
		public void mouseClicked(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void keyTyped(KeyEvent e) {}
		public void keyReleased(KeyEvent e) {}
///////////////////////////////////////useful////////////////////////////////////////////		
	
		
		public void mousePressed(MouseEvent e) 
		{
			requestFocus();
			//if(!shotTimer.isRunning())
			//{	
				
				run();
			//}
			
			drawLevel = false;

		}

		public void keyPressed(KeyEvent e) 
		{
			
			if (e.getKeyCode() ==  KeyEvent.VK_SPACE) 
			{						
				if(arrowTimer.isRunning())
				{	
					arrowTimer.stop(); 
					arrowTimerEnabled = false;
					
				} 
				Shoot sh = new Shoot();
				repaint();
			}
			
		}
		
		class BackButton implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				back = (JButton)(e.getSource());
				if(back.getActionCommand().equals("Go To Title"))
					cards.show(cardPanel,"Start");
			}
		}
		
		class Shoot implements ActionListener 
		{
			
			private int   powerSetting;
			private int hypotenuse;
			private double sin = 0;
			private double cos = 0;			
			private int index = 0;
			private Timer shotTimer;

			//This is to find the direction of the ball after the user sets his location on the screen

			public Shoot()
			{
				
				hypotenuse = 450;//Point to the y of the hoop
				powerSetting = 100;//Timer speed
				shotTimer = new Timer(powerSetting, this);
				
				
				sin = Math.sin(Math.toRadians(ap.getAngles()[ap.getIndex()]));//This is to find the y value of the triangle to the point
				sin = sin*hypotenuse;
				
				cos = Math.cos(Math.toRadians(ap.getAngles()[ap.getIndex()]));//This is to find the y value of the triangle to the point
				cos = cos*hypotenuse;
				
				index = 0;
				
				ballX = 450;//This is always the starting point
				ballY = 650;
				shotTimer.start();

			}
			

			/* compute the next location of the ball
			 * towards the hoop/
			 */
			public void actionPerformed(ActionEvent e)
			{
				if (splash == true) 
				{
					shotTimer.stop();
					return;
				}
				
				ballX = 450 - (int)(cos/10 * index);//Get slope 10 tmes
				ballY = 650 - (int)(sin/10 * index);//Get slope 10 times * index so it will go to the slope times the amount

				index += 1;
				
				

				if(ballX<0||ballX>1000)//off the screen
					shotTimer.stop();
				
				if(ballY<0)
					shotTimer.stop();//of the screen
				
				repaint();
				
			}
		}

		class TimerHandler implements ActionListener
		{
			private GamePanel gamePanel;
			private ArcPanel apanel;
			
			private final static int CLOCKWISE = 1;
			private final static int ANTICLOCKWISE = 2;

			private int direction;
			
			public TimerHandler(GamePanel gamePanel,ArcPanel ap)
			{
				direction = CLOCKWISE;
				this.gamePanel = gamePanel;
				this.apanel = ap;
			}
			
			public void actionPerformed(ActionEvent e) 
			{
			
				if(apanel.getIndex() ==0 || direction == CLOCKWISE)
				{
					apanel.incrementIndexAndSet(1);
				}
				
				if(apanel.getIndex() == apanel.getPointsX().length
						|| direction == ANTICLOCKWISE)
				{
					apanel.decrementIndexAndSet(1);
					direction = ANTICLOCKWISE;
					if(apanel.getIndex() ==0)
						direction = CLOCKWISE;
				}

				if (arrowTimerEnabled)
					gamePanel.repaint();
			 
			}
		}
			
	}	
	
	
	public static void main(String[]dubs)
	{
		Splash g = new Splash();//instantiate
	}
}
