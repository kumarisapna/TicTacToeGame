package com.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.*;

public class MyGame extends JFrame implements ActionListener{
	
	JButton [] btn = new JButton[9];
	
	//game instance variable
	
	
	int gameChances [] = {2,2,2,2,2,2,2,2,2};
	  int activePlayer  = 0;
	  
	  int wps [] []  ={
			          {0,1,2},
			          {3,4,5},
			          {6,7,8},
			          {0,3,6},
			          {1,4,7},
			          {2,5,8},
			          {0,4,8},
			          {2,4,6}
			  
	                  };
	  
	  int winner = 2;
	  boolean gameOver = false;
	MyGame(){
		
		super.setTitle("Tic Tac Toe");
		super.setSize(500, 500);
		ImageIcon icon = new ImageIcon("img/tic tac toe.png");
		super.setIconImage(icon.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createGUI();
		super.setVisible(true);
	}
	
   private void createGUI(){
	   
	   this.getContentPane().setBackground(Color.decode("#2196f3"));
	  this.setLayout(new BorderLayout());
	  
	  // north heading
	  
	  JLabel heading ,clockLabel;
	  JPanel mainPanel;
	  
	  Font font = new Font("",Font.BOLD,40);
	  heading = new JLabel("Tic Tac Toe");
	  heading.setFont(font);
	  this.add(heading , BorderLayout.NORTH);
	  heading.setHorizontalAlignment(SwingConstants.CENTER);
	  heading.setForeground(Color.WHITE);
	  heading.setIcon(new ImageIcon("img/tic.jpg"));
	  heading.setHorizontalTextPosition(SwingConstants.CENTER);
	  heading.setVerticalTextPosition(SwingConstants.BOTTOM);
	  
	  
	  //creating object of JLabel clock
	  
	  clockLabel = new JLabel("Clock");
	  clockLabel.setFont(font);
	  clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
	  this.add(clockLabel , BorderLayout.SOUTH);
	  clockLabel.setForeground(Color.WHITE);
	  
	  Thread thread = new Thread(){
		  public void run(){
			  try {
			 while(true) {
				 
				 String dateTime = new Date().toLocaleString();
				 
				 clockLabel.setText(dateTime);
					Thread.sleep(1000);
				}
			 } 
			 catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			 }
		  
	  };
	  thread.start();
	  
	  /// panel section 
	  
	  
	  mainPanel = new JPanel();
	  mainPanel.setLayout(new GridLayout(3 ,3 ));
	  
	  for(int i =1;i<=9;i++){
		  JButton btns = new JButton();
		 
		  btns.setBackground(Color.decode("#90caf9"));
		  btns.setFont(font);
		  mainPanel.add(btns);
		  btn[i-1] = btns;
		  btns.addActionListener(this);
		  btns.setName(String.valueOf(i-1));
	  }
	  this.add(mainPanel ,BorderLayout.CENTER);
   }

@Override
public void actionPerformed(ActionEvent e) {
	JButton currentButton = (JButton) e.getSource();
	String name2 = currentButton.getName();
	int names = Integer.parseInt(name2.trim());
	
	
	if(gameOver){
		JOptionPane.showMessageDialog(this, "Game Already over..");
		return;
	}
	
	
	if(gameChances[names] == 2){
		
		if(activePlayer == 1){
			currentButton.setIcon(new ImageIcon("img/x.jpg"));
			gameChances[names] = activePlayer;
			activePlayer =0;
		}
		else {
			currentButton.setIcon(new ImageIcon("img/o.jpg"));
			gameChances[names] = activePlayer;
			activePlayer =1;
		}
		
		for(int[] temp :wps){
			
			if((gameChances[temp[0]] ==gameChances[temp[1]]) &&
					(gameChances[temp[1]]== gameChances[temp[2]]) && 
					(gameChances[temp[2]]!=2)){
				
				winner = gameChances[temp[0]];
				gameOver = true;
				
				JOptionPane.showMessageDialog(null, "player" + " " + winner +" " +  "has won the game");
				int i = JOptionPane.showConfirmDialog(this, "Do You Want to play again ??..");
				
				if(i==0){

					this.setVisible(false);
					new MyGame();
				}
				else if (i==1){
				System.exit(3452);	
				}
				else{
					
				}
				//System.out.println(i);
				break;
			}
		}
		
		// draw logic...
		
		int counter =0;
		
		for(int x : gameChances){
			
			if(x==2){
				counter++;
				break;
			}
		}
		if(counter ==0 && gameOver==false){
			JOptionPane.showMessageDialog(null, "it's draw..");
			int i = JOptionPane.showConfirmDialog(this, "Do You Want to play again ??..");
			if(i ==0){
				
				this.setVisible(false);
				new MyGame();
			}
			else if(i == 1){
				System.exit(5343);
			}
			else{
				
			}
			gameOver = true;
		}
	}
	else{
		JOptionPane.showMessageDialog(this, "position is already occupied...");
	}
	
}
}
