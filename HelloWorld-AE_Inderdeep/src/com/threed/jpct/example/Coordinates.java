package com.threed.jpct.example;



public class Coordinates {
	
	public int x,y;
	
	public Coordinates(){}
	
	public Coordinates(int x,int y)
	{
		this.x=x;
		this.y=y;
	}

	//public TileGenerator getTileAtCoordinates(int x,int y,BoardGenerator board)
	public void getTileAtCoordinates(int x,int y,BoardGenerator board)
	{
		Coordinates touchCord=new Coordinates();
		touchCord.x=x;
		
		int heightOfScreen =100;   // in pixels
		int widthOfScreen=100;
		int sizeOfBoard=5;
		int tileSize=widthOfScreen/sizeOfBoard;
		touchCord.y=heightOfScreen - y;
		
		//Moving the board
		Coordinates moveCoord=new Coordinates();
		touchCord.x=widthOfScreen/2;
		touchCord.y=heightOfScreen/2;
		
		//Getting proper coordinates
		Coordinates boardCoord=new Coordinates();
		boardCoord.x=touchCord.x/tileSize;
		boardCoord.y=touchCord.y/tileSize;
		
		//TileGenerator tileAtCoord=new TileGenerator(boardCoord.x, boardCoord.y);
		//tileAtCoord.tile=board.ret_TileType(boardCoord.x, boardCoord.y);
		
		//return tileAtCoord;
	}
}
