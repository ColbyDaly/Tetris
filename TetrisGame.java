package tetris;

//OKAY. This file should be entirely archaic at this point and no longer needed. So... let's pretend it's useless
//Yay!

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.image.*;
import java.awt.event.*;
import java.util.HashMap;
import java.net.*;
import java.awt.Component;
public class TetrisGame implements Runnable, KeyListener { //here the game will actually be played out
	
	int p1x;
	int p2x;
	int p3x;
	int p4x;
	int p1y;
	int p2y;
	int p3y;
	int p4y;
	
	

	public void run() { //where the program does the running things to make the game workarino
	}
	
	
	public void stop(){
	
	}

	@Override
	public void keyPressed(KeyEvent e) {
	
	}

    
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_DOWN){
			//user wants piece to slow back down
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		//intentionally left empty :D
	}	
}