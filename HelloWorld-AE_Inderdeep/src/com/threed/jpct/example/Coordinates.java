package com.threed.jpct.example;

//import com.threed.jpct.example.TileGenerator;

public class Coordinates {
	
	public int x,y;
	
	
	

	/*public static BoardCoordinates getTileAtCoordinates(int x,int y)
	{
		Coordinates touchCord=new Coordinates();
		touchCord.x=x;
		
		int heightOfScreen =100;   // in pixels
		int widthOfScreen=100;
		
		int tileSize=widthOfScreen/BoardGenerator.sizeOfBoard;
		touchCord.y=heightOfScreen - y;
		
		//Moving the board
		//Coordinates moveCoord=new Coordinates();
		touchCord.x-=widthOfScreen/2;
		touchCord.y-=heightOfScreen/2;
		
		//Getting proper coordinates
		Coordinates boardCoord=new Coordinates();
		boardCoord.x=touchCord.x/tileSize;
		boardCoord.y=touchCord.y/tileSize;
		
		BoardCoordinates _boardCoord = new BoardCoordinates(boardCoord.x, boardCoord.y);
		
		//TileGenerator tileAtCoord=new TileGenerator();
		//tileAtCoord.tile=board.ret_TileType(boardCoord.x, boardCoord.y);
		
		return _boardCoord;
	}*/
	
	
	public static int getsizeOfTile(int Width,int Height){
		int sizeOfTile = Width/BoardGenerator.sizeOfBoard;
		return sizeOfTile;
	}
	
	
 public static int getBoardX(int w,int pixel_x ){
	 int x = (w/2)- (int)ArrayCoord.ArrayCoordX(pixel_x);
	 if(pixel_x>(w/2)){
		 x=-x;
	 }
	 return x;
 }

 public static int getBoardY(int w,int h,int pixel_y ){
	 
	 int y = (w/2) - (int)ArrayCoord.ArrayCoordY(pixel_y, h,w);
	 if(pixel_y>(w/2))
		 y=-y;
		 return y;
	 }
 
}
