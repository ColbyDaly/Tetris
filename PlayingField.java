package tetris;
import java.awt.*;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.image.*;
import java.awt.event.*;
import java.util.HashMap;
import java.net.*;

@SuppressWarnings("serial")
public class PlayingField extends Applet implements Runnable, KeyListener{
	public HashMap<Integer,int[]> board=new HashMap<Integer,int[]>(); //our grid will be 17x20, but the top row will be "off the board", used for fail conditions
	//the keys will be our y values, int arrays will be our x values
	//0 is top row(row where the pieces spawn, if a block gets placed here, user loses the game)
	//17 is bottom row(16th visible row)
	protected int[] rowComplete=new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}; //will be used in checkForComplete
	protected int[] rowEmpty=new int[20]; //used in checkForFailure
	Thread runner;
	//PlayingField screen=new PlayingField();
	Tetrimino piece=new Tetrimino(); 
	private int t1x; //these ints are used to track the position of the tetrimino
	private int t1y;
	private int t2x;
	private int t2y;
	private int t3x;
	private int t3y;
	private int t4x;
	private int t4y;
	
	public int rotation=0;
	Image im;
	int curScore = 0;
	int level = 0;
	boolean gameOver;
	public PlayingField(){
		for(int i=0;i<18;i++){ //initializing the board
			board.put(i, new int[20]);
		}
	}
	
	public void findTetriminoSpawn(int p1x,int p2x,int p3x,int p4x,int p1y,int p2y,int p3y,int p4y){
		t1x=p1x;
		t2x=p2x;
		t3x=p3x;
		t4x=p4x;
		t1y=p1y;
		t2y=p2y;
		t3y=p3y;
		t4y=p4y;
		repaint();
	}
	
	public boolean moveCurrentTetriminoDown(){
		if(tetriminoCanMoveDown()==true){ //checks to make sure the piece CAN move down before it pushes it down!
			board.get(t1y)[t1x]=0;
			board.get(t2y)[t2x]=0;
			board.get(t3y)[t3x]=0;
			board.get(t4y)[t4x]=0;
			t1y++;
			t2y++;
			t3y++;
			t4y++;
			board.get(t1y)[t1x]=2;
			board.get(t2y)[t2x]=2;
			board.get(t3y)[t3x]=2;
			board.get(t4y)[t4x]=2;
			repaint();
			return true;
		}
		else
			return false;
	}
	
	public boolean moveCurrentTetriminoRight(){
		if(tetriminoCanMoveRight()==true){
			board.get(t1y)[t1x]=0;
			board.get(t2y)[t2x]=0;
			board.get(t3y)[t3x]=0;
			board.get(t4y)[t4x]=0;
			t1x++;
			t2x++;
			t3x++;
			t4x++;
			board.get(t1y)[t1x]=2;
			board.get(t2y)[t2x]=2;
			board.get(t3y)[t3x]=2;
			board.get(t4y)[t4x]=2;
			 repaint();
			return true;
		}
		else
			return false;
	}
	
	public boolean moveCurrentTetriminoLeft(){
		if(tetriminoCanMoveLeft()==true){
			board.get(t1y)[t1x]=0;
			board.get(t2y)[t2x]=0;
			board.get(t3y)[t3x]=0;
			board.get(t4y)[t4x]=0;
			t1x--;
			t2x--;
			t3x--;
			t4x--;
			board.get(t1y)[t1x]=2;
			board.get(t2y)[t2x]=2;
			board.get(t3y)[t3x]=2;
			board.get(t4y)[t4x]=2;
			repaint();
			return true;
		}
		else
			return false;
	}
	
	@SuppressWarnings("all")
	public boolean rotateCurrentTetrimino(String type, int rotation){
		if(tetriminoCanRotate(type, rotation)==true){ //needs filling out, hooray
			//T-block, rotate from rotation 0
			if(type=="T"&&rotation==0){ //           1
										//123  -->  42
				                        // 4         3
				board.get(t1y)[t1x]=0;
				board.get(t2y)[t2x]=0;
				board.get(t3y)[t3x]=0;
				board.get(t4y)[t4x]=0;
				t1x++;
				t1y--;
				t2x=t2x;
				t2y=t2y;
				t3x--;
				t3y++;
				t4x--;
				t4y--;
				board.get(t1y)[t1x]=2;
				board.get(t2y)[t2x]=2;
				board.get(t3y)[t3x]=2;
				board.get(t4y)[t4x]=2;
				repaint();
			}
			//T-block, rotate from rotation 1
			else if(type=="T"&&rotation==1){ // 1        4
											// 42  -->  321
											//  3         
				board.get(t1y)[t1x]=0;
				board.get(t2y)[t2x]=0;
				board.get(t3y)[t3x]=0;
				board.get(t4y)[t4x]=0;
				t1x++;
				t1y++;
				t2x=t2x;
				t2y=t2y;
				t3x--;
				t3y--;
				t4x++;
				t4y--;
				board.get(t1y)[t1x]=2;
				board.get(t2y)[t2x]=2;
				board.get(t3y)[t3x]=2;
				board.get(t4y)[t4x]=2;
				repaint();
			}
			//T-block, rotate from rotation 2
			else if(type=="T"&&rotation==2){ //  4        3 
											//  321  -->  24
											//            1
				board.get(t1y)[t1x]=0;
				board.get(t2y)[t2x]=0;
				board.get(t3y)[t3x]=0;
				board.get(t4y)[t4x]=0;
				t1x--;
				t1y++;
				t2x=t2x;
				t2y=t2y;
				t3x++;
				t3y--;
				t4x++;
				t4y++;
				board.get(t1y)[t1x]=2;
				board.get(t2y)[t2x]=2;
				board.get(t3y)[t3x]=2;
				board.get(t4y)[t4x]=2;
				repaint();
			}			
			//T-block, rotate from rotation 3
			else if(type=="T"&&rotation==3){ //3    
											 //24  -->  123
				                             //1         4
				board.get(t1y)[t1x]=0;
				board.get(t2y)[t2x]=0;
				board.get(t3y)[t3x]=0;
				board.get(t4y)[t4x]=0;
				t1x--;
				t1y--;
				t2x=t2x;
				t2y=t2y;
				t3x++;
				t3y++;
				t4x--;
				t4y++;
				board.get(t1y)[t1x]=2;
				board.get(t2y)[t2x]=2;
				board.get(t3y)[t3x]=2;
				board.get(t4y)[t4x]=2;
				repaint();
				
			}
			//Line block, rotate from rotation 0
			if(type=="Line"&&rotation==0){ // 1
											//2
											//3  -->  1234
											//4 
				board.get(t1y)[t1x]=0;
				board.get(t2y)[t2x]=0;
				board.get(t3y)[t3x]=0;
				board.get(t4y)[t4x]=0;
				t1x=t1x;
				t1y=t1y+2;
				t2x=t2x+1;
				t2y=t2y+1;
				t3x=t3x+2;
				t3y=t3y;
				t4x=t4x+3;
				t4y--;
				board.get(t1y)[t1x]=2;
				board.get(t2y)[t2x]=2;
				board.get(t3y)[t3x]=2;
				board.get(t4y)[t4x]=2;
				repaint();
			}
			//Line block, rotate from rotation 1
			else if(type=="Line"&&rotation==1){ //           1
												//           2
												// 1234 -->  3
												//           4
				board.get(t1y)[t1x]=0;
				board.get(t2y)[t2x]=0;
				board.get(t3y)[t3x]=0;
				board.get(t4y)[t4x]=0;
				t1x=t1x;
				t1y=t1y-2;
				t2x=t2x-1;
				t2y=t2y-1;
				t3x=t3x-2;
				t3y=t3y;
				t4x=t4x-3;
				t4y++;
				board.get(t1y)[t1x]=2;
				board.get(t2y)[t2x]=2;
				board.get(t3y)[t3x]=2;
				board.get(t4y)[t4x]=2;
				repaint();
				
			}			
			//Left-L block, rotate from rotation 0
			if(type=="Left L"&&rotation==0){
				board.get(t1y)[t1x]=0;
				board.get(t2y)[t2x]=0;
				board.get(t3y)[t3x]=0;
				board.get(t4y)[t4x]=0;
				t1x++;
				t1y--;
				t2x=t2x;
				t2y=t2y;
				t3x--;
				t3y++;
				t4x=t4x;
				t4y--;
				board.get(t1y)[t1x]=2;
				board.get(t2y)[t2x]=2;
				board.get(t3y)[t3x]=2;
				board.get(t4y)[t4x]=2;
				repaint();
			}
			
			//Left-L block, rotate from rotation 1
			else if(type=="Left L"&&rotation==1){
				board.get(t1y)[t1x]=0;
				board.get(t2y)[t2x]=0;
				board.get(t3y)[t3x]=0;
				board.get(t4y)[t4x]=0;
				t1x++;
				t1y++;
				t2x=t2x;
				t2y=t2y;
				t3x--;
				t3y--;
				t4x=t4x+2;
				t4y=t4y;
				board.get(t1y)[t1x]=2;
				board.get(t2y)[t2x]=2;
				board.get(t3y)[t3x]=2;
				board.get(t4y)[t4x]=2;
				repaint();
			}			
			//Left-L block, rotate from rotation 2
			else if(type=="Left L"&&rotation==2){
				board.get(t1y)[t1x]=0;
				board.get(t2y)[t2x]=0;
				board.get(t3y)[t3x]=0;
				board.get(t4y)[t4x]=0;
				t1x--;
				t1y++;
				t2x=t2x;
				t2y=t2y;
				t3x++;
				t3y--;
				t4x=t4x;
				t4y=t4y+2;
				board.get(t1y)[t1x]=2;
				board.get(t2y)[t2x]=2;
				board.get(t3y)[t3x]=2;
				board.get(t4y)[t4x]=2;
				repaint();
			}			
			//Left-L block, rotate from rotation 3
			else if(type=="Left L"&&rotation==3){
				board.get(t1y)[t1x]=0;
				board.get(t2y)[t2x]=0;
				board.get(t3y)[t3x]=0;
				board.get(t4y)[t4x]=0;
				t1x--;
				t1y--;
				t2x=t2x;
				t2y=t2y;
				t3x++;
				t3y++;
				t4x=t4x-2;
				t4y=t4y;
				board.get(t1y)[t1x]=2;
				board.get(t2y)[t2x]=2;
				board.get(t3y)[t3x]=2;
				board.get(t4y)[t4x]=2;
				repaint();
			}
			//Right-L block, rotate from rotation 0
			if(type=="Right L"&&rotation==0){
				board.get(t1y)[t1x]=0;
				board.get(t2y)[t2x]=0;
				board.get(t3y)[t3x]=0;
				board.get(t4y)[t4x]=0;
				t1x++;
				t1y--;
				t2x=t2x;
				t2y=t2y;
				t3x--;
				t3y++;
				t4x=t4x-2;
				t4y=t4y;
				board.get(t1y)[t1x]=2;
				board.get(t2y)[t2x]=2;
				board.get(t3y)[t3x]=2;
				board.get(t4y)[t4x]=2;
				repaint();
			}
			//Right-L block, rotate from rotation 1
			else if(type=="Right L"&&rotation==1){
				board.get(t1y)[t1x]=0;
				board.get(t2y)[t2x]=0;
				board.get(t3y)[t3x]=0;
				board.get(t4y)[t4x]=0;
				t1x++;
				t1y++;
				t2x=t2x;
				t2y=t2y;
				t3x--;
				t3y--;
				t4x=t4x;
				t4y=t4y-1;
				board.get(t1y)[t1x]=2;
				board.get(t2y)[t2x]=2;
				board.get(t3y)[t3x]=2;
				board.get(t4y)[t4x]=2;
				repaint();
			}
			//Right-L block, rotate from rotation 2
			else if(type=="Right L"&&rotation==2){
				board.get(t1y)[t1x]=0;
				board.get(t2y)[t2x]=0;
				board.get(t3y)[t3x]=0;
				board.get(t4y)[t4x]=0;
				t1x--;
				t1y++;
				t2x=t2x;
				t2y=t2y;
				t3x++;
				t3y--;
				t4x=t4x+1;
				t4y=t4y;
				board.get(t1y)[t1x]=2;
				board.get(t2y)[t2x]=2;
				board.get(t3y)[t3x]=2;
				board.get(t4y)[t4x]=2;
				repaint();
			}
			//Right-L block, rotate from rotation 3
			else if(type=="Right L"&&rotation==3){
				board.get(t1y)[t1x]=0;
				board.get(t2y)[t2x]=0;
				board.get(t3y)[t3x]=0;
				board.get(t4y)[t4x]=0;
				t1x--;
				t1y--;
				t2x=t2x;
				t2y=t2y;
				t3x++;
				t3y++;
				t4x=t4x;
				t4y=t4y+2;
				board.get(t1y)[t1x]=2;
				board.get(t2y)[t2x]=2;
				board.get(t3y)[t3x]=2;
				board.get(t4y)[t4x]=2;
				repaint();
			}
			//Left-Z block, rotate from rotation 0
			if(type=="Left Z"&&rotation==0){
				board.get(t1y)[t1x]=0;
				board.get(t2y)[t2x]=0;
				board.get(t3y)[t3x]=0;
				board.get(t4y)[t4x]=0;
				t1x++;
				t1y--;
				t2x=t2x;
				t2y=t2y;
				t3x--;
				t3y--;
				t4x=t4x-2;
				t4y=t4y;
				board.get(t1y)[t1x]=2;
				board.get(t2y)[t2x]=2;
				board.get(t3y)[t3x]=2;
				board.get(t4y)[t4x]=2;
				repaint();
			}
			//Left-Z block, rotate from rotation 1
			else if(type=="Left Z"&&rotation==1){
				board.get(t1y)[t1x]=0;
				board.get(t2y)[t2x]=0;
				board.get(t3y)[t3x]=0;
				board.get(t4y)[t4x]=0;
				t1x--;
				t1y++;
				t2x=t2x;
				t2y=t2y;
				t3x++;
				t3y++;
				t4x=t4x+2;
				t4y=t4y;
				board.get(t1y)[t1x]=2;
				board.get(t2y)[t2x]=2;
				board.get(t3y)[t3x]=2;
				board.get(t4y)[t4x]=2;
				repaint();
			}			
			//Right-Z block, rotate from rotation 0
			if(type=="Right Z"&&rotation==0){
				board.get(t1y)[t1x]=0;
				board.get(t2y)[t2x]=0;
				board.get(t3y)[t3x]=0;
				board.get(t4y)[t4x]=0;
				t1x++;
				t1y--;
				t2x=t2x;
				t2y=t2y;
				t3x++;
				t3y++;
				t4x=t4x;
				t4y=t4y+2;
				board.get(t1y)[t1x]=2;
				board.get(t2y)[t2x]=2;
				board.get(t3y)[t3x]=2;
				board.get(t4y)[t4x]=2;
				repaint();
			}
			//Right-Z block, rotate from rotation 1
			else if(type=="Right Z"&&rotation==1){
				board.get(t1y)[t1x]=0;
				board.get(t2y)[t2x]=0;
				board.get(t3y)[t3x]=0;
				board.get(t4y)[t4x]=0;
				t1x--;
				t1y++;
				t2x=t2x;
				t2y=t2y;
				t3x--;
				t3y--;
				t4x=t4x;
				t4y=t4y-2;
				board.get(t1y)[t1x]=2;
				board.get(t2y)[t2x]=2;
				board.get(t3y)[t3x]=2;
				board.get(t4y)[t4x]=2;
				repaint();
			}			
			return true;
		}
		return false;
	}
	
	public boolean checkForComplete(Integer i){ //checks to see if the row is entirely filled
		if(board.get(i).equals(rowComplete))
			return true;
		else
			return false;
	}
	
	public void resetRow(Integer i){ //resets the row to 0 after the row has been filled
		for(int j=0;j<20;j++){
			board.get(i)[j]=0;
			repaint();
		}
	}
	
	public void pullRowsDown(Integer i){ //pulls the rows above a resetRow down, should be finished
		for(int k=i;k>0;k--){
			for(int m=0;m<20;m++){
				if(board.get(k)[m]==1){
					board.get(k+1)[m]=1;
					board.get(k)[m]=0;
					repaint();
				}
			}
		}
	}
	
	public boolean checkForFailure(){ //checking to see if the placement squares are already filled
		if(board.get(0).equals(rowEmpty))
			return false;
		else
			return true;
	}
	
	public boolean tetriminoFinalPlacement(){
		if(tetriminoCanMoveDown()==false){
			for(int y=0;y<18;y++){
				for(int x=0;x<20;x++){
					if(board.get(y)[x]==2)
						board.get(y)[x]=1;
				}
			}
			for(int l=0;l<18;l++){
				if(checkForComplete(l)==true)
				{
					resetRow(l); //resets the finished row
					pullRowsDown(l-1); //pulls the rows above the finished row down
				}
			}
			rotation=0; //resets the rotation for future pieces :)
			//repaint();
			return true;
		}
		else
			return false;
	}
	
	public boolean tetriminoCanMoveLeft(){
		boolean moveLeft=true;
		//this isn't making sense atm
		//Checking all blocks to see if there is space to the left
		if(board.get(t1y)[t1x-1]==1)
			moveLeft=false;
		if(board.get(t2y)[t2x-1]==1)
			moveLeft=false;
		if(board.get(t3y)[t3x-1]==1)
			moveLeft=false;
		if(board.get(t4y)[t4x-1]==1)
			moveLeft=false;

		//if tetrimino can move in any direction, return true, otherwise return false
		if(moveLeft==true)
			return true;
		else
			return false;
	}
	
	public boolean tetriminoCanMoveRight(){
		boolean moveRight=true;
		//Checking all blocks to see if there is space to the right
		if(board.get(t4y)[t4x+1]==1)
			moveRight=false;
		if(board.get(t3y)[t3x+1]==1)
			moveRight=false;
		if(board.get(t2y)[t2x+1]==1)
			moveRight=false;
		if(board.get(t1y)[t1x+1]==1)
			moveRight=false;
		
		if(moveRight==true)
			return true;
		else
			return false;
	}
	
	public boolean tetriminoCanMoveDown(){
		boolean moveDown=true;
		//Checking all blocks to see if there is space below them
		if(board.get(t1y+1)[t1x]==1)
			moveDown=false;
		if(board.get(t2y+1)[t2x]==1)
			moveDown=false;
		if(board.get(t3y+1)[t3x]==1)
			moveDown=false;
		if(board.get(t4y+1)[t4x]==1)
			moveDown=false;
		
		if(moveDown==true)
			return true;
		else
			return false;
	}
	
	public boolean tetriminoCanRotate(String type,int rotation){ //needs to be sent the type of current tetrimino+rotation# from TetrisGame!
		//rotate clockwise, check to see if the new positions would be clear. should be pretty extensive
		if(type=="Square"){ //if the piece is a square, it can rotate all it wants.
			return true;
		}
		
		switch(type){
		case "Line":
			
			//Line block, starting position
			if(rotation==0&&(board.get(t4y)[t4x-3]==0)&&(board.get(t4y)[t4x-2]==0)&&(board.get(t4y)[t4x-1]==0)){
				return true;
			}
			//Line block, rotation 1
			else if(rotation==1&&(board.get(t4y-3)[t4x]==0)&&(board.get(t4y-2)[t4x]==0)&&(board.get(t4y-1)[t4x]==0)){
				return true;
			}
			break;

			
		case "Left Z":
			//Left Z, starting position
			if(rotation==0){
				return true;
			}
			//Left Z, rotation 1
			else if(rotation==1){
				return true;
			}
			//Left Z, rotation 2
			else if(rotation==2){
				return true;
			}
			//Left Z, rotation 3
			else if(rotation==3){
				return true;
			}
			break;
			
		case "Right Z":
			//Right Z, starting position
			if(rotation==0){
				return true;
			}
			//Right Z, rotation 1
			else if(rotation==1){
				return true;
			}
			//Right Z, rotation 2
			else if(rotation==2){
				return true;
			}
			//Right Z, rotation 3
			else if(rotation==3){
				return true;
			}
			break;
			
		case "Left L":
			//Left L, starting position
			if(rotation==0){
				return true;
			}
			//Left L, rotation 1
			else if(rotation==1){
				return true;
			}
			//Left L, rotation 2
			else if(rotation==2){
				return true;
			}
			//Left L, rotation 3
			else if(rotation==3){
				return true;
			}
			break;
			
		case "Right L":
			//Right L, starting position
			if(rotation==0){
				return true;
			}
			//Right L, rotation 1
			else if(rotation==1){
				return true;
			}
			//Right L, rotation 2
			else if(rotation==2){
				return true;
			}
			//Right L, rotation 3
			else if(rotation==3){
				return true;
			}
			break;
		
		case "T":
			//T, starting position
			if(rotation==0){
				return true;
			}
			//T, rotation 1
			else if(rotation==1){
				return true;
			}
			//T, rotation 2
			else if(rotation==2){
				return true;
			}
			//T, rotation 3
			else if(rotation==3){
				return true;
			}
			break;
		}
		//activates if nothing else returns true :)
		return false;
	}
	
	
	
	
	//My added code
	public void createBlockBackground()//creates the multi-color blocks
	   {
	      int width = 25; //the width of one block
	      int height = 25;//the height of one block
	      int [] pixels = new int [width * height]; //total number of pixels is width multiplied by height
	        
	      int index = 0;

	      for (int y = 0; y < height; y++)//loop through this for next statement setting the background pixels -- y coordinates
	      {
	           int numerator = y * 255;
	           int b = numerator / height;
	           int r = 255 - numerator / height;

	           for (int x = 0; x < width; x++) //set x coordinates for pixels
	           {
	                int g = x * 255 / width;
	                pixels [index++] = (255 << 24) | (r << 16) | (g << 8) | b;//all colors
	           }
	      }
	      

	      im = createImage (new MemoryImageSource (width, height, pixels,0, width));//now save the image to the memory
	   }
	public void init ()
	   { 
	      addKeyListener(this);//give this applet a key listener to detect key strokes
	      Color c = new Color(150,0,200);
	      this.setBackground(c);//set the background color to light blue
	      createBlockBackground();//make the block color
	      gameOver = false;//start off with the game stopped
	   }//end init
	
	public void paint(Graphics g)
    {

         //repaint the black background
         
         //g.fillRect(0,0,400,500);
         
          for(int x=0; x<16; x++)//the screen is a 16X20 block grid, you determine if there is a piece in a certain position of that grid
             for(int y=0; y<20; y++){//...if there is a 1 in that place (must loop through x and y coordinates to check if there is a piece in
            	 if(board.get(t1y)[t1x]==1 ||board.get(t1y)[t1x]==2)
            		 g.drawImage(im,x*25,y*25,25,25,this);
            	 if(board.get(t2y)[t2x]==1 ||board.get(t2y)[t2x]==2)
            		 g.drawImage(im,x*25,y*25,25,25,this);
            	 if(board.get(t3y)[t3x]==1 ||board.get(t3y)[t3x]==2)
            		 g.drawImage(im,x*25,y*25,25,25,this);
            	 if(board.get(t4y)[t4x]==1 ||board.get(t4y)[t4x]==2)//...that position) a 1 means there is a piece there stopped, a 2 means that the piece is still moving while a 0 means there is no piece there
                     g.drawImage(im,x*25,y*25,25,25,this);
                 if(board.get(t1y)[t1x] == 0)//clear the old positions except for the bottom row(19)
                    g.clearRect(x*25,y*25,25,25);
                 if(board.get(t2y)[t2x] == 0)//clear the old positions except for the bottom row(19)
                     g.clearRect(x*25,y*25,25,25);
                 if(board.get(t3y)[t2x] == 0)//clear the old positions except for the bottom row(19)
                     g.clearRect(x*25,y*25,25,25);
                 if(board.get(t4y)[t2x] == 0)//clear the old positions except for the bottom row(19)
                     g.clearRect(x*25,y*25,25,25);
                  }
          //now draw the boundary lines
          g.setColor(Color.black);
          g.drawRect(0,50,399,475);
          g.drawRect(0,475,400,499);
          g.drawString("   Score: " + curScore,0,20*25-12);//draw score at the bottom of the screen (19th row)
          g.drawString("Level: " + level,200,20*25-12);
          if(checkForFailure() == true)
	  {
        	  g.setColor(Color.white);
               g.drawString("press Enter key to start a game.",40,200);
	       g.drawString("Use the arrow keys to move the pieces",40,250);
	       g.drawString("Use the spacebar to flip the pieces",40,300);
	  }
    }

    public void update(Graphics g)
    {
        paint(g);
    }

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER )//starts the game
			setGame();
		else if(e.getKeyCode()==KeyEvent.VK_LEFT){ //user wants to move left
			moveCurrentTetriminoLeft();
		}
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT){ //user wants to move right
			moveCurrentTetriminoRight();
		}
		if(e.getKeyCode()==KeyEvent.VK_SPACE){ //user wants to flip
			rotateCurrentTetrimino(piece.type,rotation);
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN){ //user wants piece to speed up
			moveCurrentTetriminoDown();
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER )//starts a new game
	           setGame();
			
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Thread current=Thread.currentThread();
		while(runner==current){
			if(tetriminoFinalPlacement()==false){
				Color c = new Color(150,0,200);
                setBackground(c);
				 repaint();
			}
			else{
				if(checkForFailure()==false){
					piece = null;
					piece=new Tetrimino(); //creates the new piece to be worked with
					t1x=piece.returnx1();
					t2x=piece.returnx2();
					t3x=piece.returnx3();
					t4x=piece.returnx4();
					t1y=piece.returny1();
					t2y=piece.returny2();
					t3y=piece.returny3();
					t4y=piece.returny4();
					findTetriminoSpawn(t1x,t2x,t3x,t4x,t1y,t2y,t3y,t4y);
					repaint();
				}
			}
		}
		
	}
	
	public void stop(){
		runner=null; //stops all the things
	}
	
	public void setGame(){
		//first reset the score to 0
	      curScore = 0;
	      level = 1;//first level
	      //now set the entire array of xy bytes to 0 and the entire array of fourCurrentBlocks to 0
	      for(int y=0; y<18; y++)
	    	  for(int x=0; x<20; x++)
	          board.get(y)[x] = 0;
	
		if(runner==null){
			runner=new Thread(this);
		}
		runner.start();
	      }
	
	
	public int returnScore(){
		return curScore;
	}
	public int returnLevel(){
		return level;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}