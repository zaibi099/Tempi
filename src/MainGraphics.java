import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

import java.util.Scanner;
 
public class MainGraphics {
	
   public static void main(String[] args) throws IOException{
	   
	   Scanner inp = new Scanner(System.in);
	   boolean flag=true;
	   
		   GraphicsWork obj = null;
		   String ist=null;
		   String sec=null;
		   
		   System.out.println("Who will Start First \n0- You\n1- Transformer\nOption : ");
		System.out.println("Umar's Edited ");
		   int turn = inp.nextInt();
		   
		   if(turn==0){
				ist = "Me";
				sec = "Transformer";
			}
			else{
				ist = "Transformer";
				sec = "Me";
			}
		   
		   obj = new GraphicsWork(turn);
		   obj.gameStarter(ist,sec);
   }
}
