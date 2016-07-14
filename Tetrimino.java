package tetris;

import java.util.Random;

public class Tetrimino {
	private int b1x;
	private int b1y; 
	private int b2x; //x is used for the x-position of the block
	private int b2y; //y is used for the y-position of the block
	private int b3x;
	private int b3y; //there are 4 blocks to each tetrimino
	private int b4x;
	private int b4y;
	public String type;
	
	public Tetrimino(){
		Random rand=new Random();
		int typenum=rand.nextInt(7);
		switch(typenum){ //spawn them at the 0 y, 7x
		case 0: //Right L-block
			type="Right L";
			b1x=7;
			b1y=0;
			b2x=8;
			b2y=0;
			b3x=9;
			b3y=0;
			b4x=9;
			b4y=1;
			
			break;
		case 1: //Left L-block
			type="Left L";
			b1x=7;
			b1y=0;
			b2x=8;
			b2y=0;
			b3x=9;
			b3y=0;
			b4x=7;
			b4y=1;
			
			break;
		case 2: //Right Z
			type="Right Z";
			b1x=7;
			b1y=1;
			b2x=8;
			b2y=1;
			b3x=8;
			b3y=0;
			b4x=9;
			b4y=0;
			
			break;
		case 3: //Left Z
			type="Left Z";
			b1x=7;
			b1y=0;
			b2x=8;
			b2y=0;
			b3x=8;
			b3y=1;
			b4x=9;
			b4y=1;
			
			break;
		case 4: //T block
			type="T";
			b1x=7;
			b1y=0;
			b2x=8;
			b2y=0;
			b3x=9;
			b3y=0;
			b4x=8;
			b4y=1;
			
			break;
		case 5: //Square
			type="Square";
			b1x=7;
			b1y=0;
			b2x=7;
			b2y=1;
			b3x=8;
			b3y=0;
			b4x=8;
			b4y=1;
			
			break;
		case 6: //Line
			type="Line";
			b1x=7;
			b1y=0;
			b2x=7;
			b2y=1;
			b3x=7;
			b3y=2;
			b4x=7;
			b4y=3;
			break;
		
		}
		
	}
	
	public int returnx1(){
		return b1x;
	}
	
	public int returnx2(){
		return b2x;
	}
	
	public int returnx3(){
		return b3x;
	}
	
	public int returnx4(){
		return b4x;
	}
	
	public int returny1(){
		return b1y;
	}
	
	public int returny2(){
		return b2y;
	}
	
	public int returny3(){
		return b3y;
	}
	
	public int returny4(){
		return b4y;
	}
}