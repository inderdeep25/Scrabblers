package com.threed.jpct.example;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

import com.threed.jpct.SimpleVector;

public class Tray {
	
	public List<Tile> _tiles;

	public float trayTilePixelSize;
	public Tray() {
		_tiles = new ArrayList<Tile>();
		float screenWidthTilePixelSize = ScreenInfoProvider.GetInstance().ScreenWidth / 7;
		float screenHeightTilePixelSize = (ScreenInfoProvider.GetInstance().ScreenHeight - ScreenInfoProvider.GetInstance().ScreenWidth) / 2;
		
		if(screenWidthTilePixelSize < screenHeightTilePixelSize)
			trayTilePixelSize = screenWidthTilePixelSize;
		else
			trayTilePixelSize = screenHeightTilePixelSize;
	}

	void updateLetterDataSprites() {
		for(int i = 0; i < _tiles.size(); ++i){
			_tiles.get(i).setPixelPosition(new SimpleVector(xPositionFromIndex(i),
															ScreenInfoProvider.GetInstance().ScreenHeight - trayTilePixelSize, 0));
		}
	}

	public LetterData getLetterDataAtPixel(int x, int y)
	{

		int index = indexFromPixelPosition(x, y);
		if(index != -1)
			return _tiles.get(index)._letterData;
		
		return null;
	}

	public int xPositionFromIndex(int i){
		return (int) (((((2 * i) + 1) / 2 * _tiles.size()) * ScreenInfoProvider.GetInstance().ScreenWidth) - (trayTilePixelSize / 2));
	}
	
	public int indexFromPixelPosition(int x, int y){
		int screenHeight =ScreenInfoProvider.GetInstance().ScreenHeight;
		int screenWidth = ScreenInfoProvider.GetInstance().ScreenWidth;
		
		if(y < screenHeight - ((screenHeight - screenWidth) / 2))
			return -1;
		
		for(int i = 0; i < _tiles.size(); i++){
			int tileX = xPositionFromIndex(i);
			if(x > tileX && x < tileX + trayTilePixelSize)
				return i;
		}
		
		return -1;
	}
	
	void removeLetterAtPixel(int x, int y) {
		int index = indexFromPixelPosition(x, y);
		if(index != -1)
			_tiles.remove(index);
		
		updateLetterDataSprites();
	}

	void AddLettersUntilHandSizelsMet() {
		while(_tiles.size() < 7){
			_tiles.add(new Tile(new BoardCoordinates(0,0),
								TileType.BLANK_TILE,
								LetterManager.getRandomLetter()));
		}
	}
}
