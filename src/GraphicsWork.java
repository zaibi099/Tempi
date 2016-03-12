import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.*;

import java.util.Arrays;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;


public class GraphicsWork implements ActionListener {

	private Dimension Dobj=null;
	private JLabel firstPlayer=null;
	private JLabel secondPlayer=null;
	
	private JButton firstTurn=null;
	private JButton secondTurn=null;
	
	private JFrame f=null;
	private JPanel p=null;
	private JPanel topPanel=null;
	
	private JButton[] number;
	private int flagVar;
	private int whoFirst;
	private Container c=null;
	private ImageIcon crossIcon=null;
	private ImageIcon tickIcon=null;
	private TicTacLogic tObj;
	int playerFlag;
	boolean[] buttonFlag;
	int playerCheckFlag;
	private int playerTries;
	
	GraphicsWork(int turn){
		
		whoFirst = turn;
		flagVar=0;
		playerFlag=turn;
		buttonFlag=new boolean[9];
		for(int i=0;i<9;i++){
			buttonFlag[i]=false;
		}
		tObj=new TicTacLogic(turn);
	}
	
	public void gameStarter(String istArg,String secArg)
	{
		
		GridLayout toplay = new GridLayout(2,2);
		GridLayout buttonlayout = new GridLayout(3,3);
		
		toplay.setVgap(2);
		toplay.setHgap(3);
		
		buttonlayout.setVgap(2);
		buttonlayout.setHgap(3);
		
		p = new JPanel();		// Creating panel for buttons
		p.setLayout(buttonlayout);
		
		topPanel = new JPanel();		// Creating panel for Top Label , iputti
		topPanel.setLayout(toplay);
		topPanel.setBackground(Color.white.brighter());
		topPanel.setPreferredSize(new Dimension(100,100));
		
		createHeader(istArg,secArg);
		
		Dobj=new Dimension();
		Dobj.height=600;
		Dobj.width=500;
		
		f=new JFrame("NIGGA TIC TAC TOE");
		f.setSize(Dobj);				//	FrameObj.setSize(100,200);
		f.setFont(new Font("System",Font.PLAIN, 14));
		f.setResizable(false);
		c=f.getContentPane();
		f.setLayout(new BorderLayout());
		f.setBackground(Color.gray);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setTickIcon();
		setCrossIcon();
		
		c.setLayout(new BorderLayout());
	    
	    AddButtons();
	    c.add(topPanel,BorderLayout.NORTH);
	    c.add(p,BorderLayout.CENTER);
		footer();
		
	    f.setVisible(true);
	    c.setVisible(true);
	    
	    if(whoFirst==1){
	    	int num = tObj.robotMove();
	    	number[num].doClick();
	    }
	}
	
	public void func(){
		System.exit(0);
	}
    public void draw(){
		System.exit(0);
	}
	
	private void AddButtons()
	{   
		number = new JButton[9];
	    for(int i=0;i<9;i++)
	    {
			String str=String.valueOf(i);
		    number[i]= new JButton(str);
		    number[i].setBackground(Color.white.brighter());
			p.add(number[i]);
			number[i].addActionListener(this);
	    }
	}
	
	private void createHeader(String x, String y){
		
		firstPlayer = new JLabel(x,JLabel.CENTER);
		firstPlayer.setFont(new Font("Serif",Font.BOLD,20));
		firstPlayer.setForeground(Color.black.brighter());
		firstPlayer.setPreferredSize(new Dimension(30,30));
		//firstPlayer.setBackground(Color.red.brighter());
		firstPlayer.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		
		secondPlayer = new JLabel(y,JLabel.CENTER);
		secondPlayer.setFont(new Font("Serif",Font.BOLD,20));
		secondPlayer.setForeground(Color.black.brighter());
		secondPlayer.setPreferredSize(new Dimension(30,30));
		//secondPlayer.setBackground(Color.y.brighter());
		secondPlayer.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		
		firstTurn = new JButton(x + " turn");
		firstTurn.setFont(new Font("Serif",Font.BOLD,20));
		firstTurn.setForeground(Color.black.brighter());
		firstTurn.setPreferredSize(new Dimension(30,30));
		firstTurn.setBackground(Color.green.brighter());
		
		secondTurn = new JButton(y+ " turn");
		secondTurn.setFont(new Font("Serif",Font.BOLD,20));
		secondTurn.setForeground(Color.black.brighter());
		secondTurn.setPreferredSize(new Dimension(30,30));
		secondTurn.setBackground(Color.black.brighter());
		
		topPanel.add(firstPlayer);
		topPanel.add(secondPlayer);
		topPanel.add(firstTurn);
		topPanel.add(secondTurn);
		
	}
	
	private void footer(){
	
		JLabel MyFooter;
		Image img = new ImageIcon("uniLogo.gif").getImage().getScaledInstance(60,60,java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(img);
		
		MyFooter = new JLabel(" ",icon,JLabel.CENTER);
		MyFooter.setPreferredSize(new Dimension(60,60));
		c.add(MyFooter,BorderLayout.PAGE_END);
		
	}
	
	public void actionPerformed(ActionEvent ae) 
	
	{
		        String action = ae.getActionCommand();
		        if (action.equals("0")) {
					if(buttonFlag[0]!=true){
						setImageOfButton(0,"0");
						buttonFlag[0]=true;
					}
		        }
		        else if (action.equals("1")) {
		            if(buttonFlag[1]!=true){
						setImageOfButton(1,"1");
						buttonFlag[1]=true;
					}
		        }
		        else if (action.equals("2")) {
		            if(buttonFlag[2]!=true){
						setImageOfButton(2,"2");
						buttonFlag[2]=true;
					}
		        }
		        else if (action.equals("3")) {
		            if(buttonFlag[3]!=true){
						setImageOfButton(3,"3");
						buttonFlag[3]=true;
					}
		        }
		        else if (action.equals("4")) {
		            if(buttonFlag[4]!=true){
						setImageOfButton(4,"4");
						buttonFlag[4]=true;
					}
		        }
		        else if (action.equals("5")) {
		           if(buttonFlag[5]!=true){
						setImageOfButton(5,"5");
						buttonFlag[5]=true;
					}
		        }
		        else if (action.equals("6")) {
		            if(buttonFlag[6]!=true){
						setImageOfButton(6,"6");
						buttonFlag[6]=true;
					}
		        }
		        else if (action.equals("7")) {
		            if(buttonFlag[7]!=true){
						setImageOfButton(7,"7");
						buttonFlag[7]=true;
					}
		        }
		        else if (action.equals("8")) {
		            if(buttonFlag[8]!=true){
						setImageOfButton(8,"8");
						buttonFlag[8]=true;
					}
		        }
	}
	
	private void setCrossIcon(){
	
		Image img = new ImageIcon("cross_1.png").getImage().getScaledInstance(170,140,java.awt.Image.SCALE_SMOOTH);
		crossIcon = new ImageIcon(img);
	}
	
	private void setTickIcon(){
	
		Image img = new ImageIcon("tick_1.png").getImage().getScaledInstance(170,140,java.awt.Image.SCALE_SMOOTH);
		tickIcon = new ImageIcon(img);
	}
	
	private void setImageOfButton(int x,String b){
	
		if(playerFlag==0){
			
			number[x].setIcon(tickIcon);
			secondTurn.setBackground(Color.green.brighter());
			firstTurn.setBackground(Color.black.brighter());
			tObj.setPlayerIpnut(playerFlag,x,b);
			playerFlag=1;
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(checkWinner()!=true){
				int num = tObj.robotMove();
				if(num!=-1){
					System.out.println(num);
			    	number[num].doClick();	
				}
			}
		}
		else{
			
			number[x].setIcon(crossIcon);
			firstTurn.setBackground(Color.green.brighter());
			secondTurn.setBackground(Color.black.brighter());
			tObj.setPlayerIpnut(playerFlag,x,b);
			playerFlag=0;
		}
		
		checkWinner();
	}
	
	public boolean checkWinner(){
		int ret=-1;
		int count=0;
		int tries=0;
		
		for(int i=0;i<9;i++){
			if(buttonFlag[i]==true)
			count++;
		}
		
		ret=tObj.checkWinner();
		if(ret==0){
			tries = tObj.checkTries(1);
			System.out.println("---Transformer WON ----");
			System.out.println("Moves : " + tries);
			func();
			return true;
		}
		if(ret==1){
			tries = tObj.checkTries(0);
			System.out.println("---YOU WON----");
			System.out.println("Moves : " + tries);
			func();
			return true;
		}
		else if(count==8){
			System.out.println("---DRAW----");
			draw();
			return true;
		}
		return false;
	}
}
