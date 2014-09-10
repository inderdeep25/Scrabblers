package com.threed.jpct.example;

public class BoardCoordinates {
	public static int x, y;

	public BoardCoordinates(int x, int y) {
		BoardCoordinates.x = x;
		BoardCoordinates.y = y;
	}
	public static int getBoardX(){
		return x;
	}
	public static int getBoardY(){
		return y;
	}
	
	
}
