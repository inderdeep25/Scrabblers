package com.threed.jpct.example;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.sprites.SimpleSpriteToken;
import com.sprites.SpriteManager;
import com.sprites.TextSpriteToken;
import com.threed.jpct.SimpleVector;

public class Tile {
	SimpleSpriteToken background,backgroundLetter;
	TextSpriteToken letter;
	TextSpriteToken number;
	TextSpriteToken board;
	LetterData _letterData;
	float scale = 10;
	TileType boardTile;
	int count=0;
	
	public Tile(BoardCoordinates x, TileType tileType,LetterData _letterData) {
		// background =
		// SpriteManager.GetInstance().AddSimpleSprite("blank_tile_blueprint",
		// 0);
		if(_letterData==null)
		setTileType(tileType);

		 //biege tile for the letter background
		
		boardTile=TileType.LETTER_TILE;
		
		if(_letterData !=null){
			backgroundLetter=
					 SpriteManager.GetInstance().AddSimpleSprite("blank_tile_blueprint",
					 1);
			letter = SpriteManager.GetInstance().AddTextSprite(
					"alphabet_blueprint", 2);
			number = SpriteManager.GetInstance().AddTextSprite("number_blueprint",
					3);
		letter.SetMessage(Character.toString(_letterData.get_id()));
		number.SetMessage(Float.toString(_letterData.get_score()));
		}
		/*
		 * background.SetScale(scale); letter.SetScale(scale);
		 * number.SetScale(scale);
		 */
		

	}

	public void Delete() {
		background.Delete();
		//letter.Delete();
		//number.Delete();
	}

	

	public void setTileType(TileType tileType) {
		if (background != null)
			background.Delete();

		switch (tileType) {
		case BLANK_TILE:
			background = SpriteManager.GetInstance().AddSimpleSprite(
					"blankTile_blueprint", 0);
			boardTile=TileType.BLANK_TILE;
			count=1;
			Log.d("xy", "BlankTile");
			break;
		case NULL_TILE:
			background = SpriteManager.GetInstance().AddSimpleSprite(
					"blankTile_blueprint", 0);
			count=1;
			boardTile=TileType.BLANK_TILE;
			Log.d("xy", "nullTile");
			break;

		case TRIPPLE_LETTER_TILE:
			background = SpriteManager.GetInstance().AddSimpleSprite(
					"trippleLetterTile_blueprint", 0);
			count=1;
			boardTile=TileType.TRIPPLE_LETTER_TILE;
			Log.d("xy", "trippleLetterTile");
			break;
		case TRIPPLE_WORD_TILE:
			background = SpriteManager.GetInstance().AddSimpleSprite(
					"trippleWordTile_blueprint", 0);
			count=1;
			boardTile=TileType.TRIPPLE_WORD_TILE;
			Log.d("xy", "trippleWordTile");
			break;
		case DOUBLE_LETTER_TILE:
			background = SpriteManager.GetInstance().AddSimpleSprite(
					"doubleLetterTile_blueprint", 0);
			count=1;
			boardTile=TileType.DOUBLE_LETTER_TILE;
			Log.d("xy", "doubleLetterTile");
			break;
		case DOUBLE_WORD_TILE:
			background = SpriteManager.GetInstance().AddSimpleSprite(
					"doubleWordTile_blueprint", 0);
			count=1;
			boardTile=TileType.DOUBLE_WORD_TILE;
			Log.d("xy", "doubleWordTile");
			break;
		case CENTER_TILE:
			background = SpriteManager.GetInstance().AddSimpleSprite(
					"centerTile_blueprint", 0);
			count=1;
			boardTile=TileType.CENTER_TILE;
			Log.d("xy", "centerTile");
			break;
		case LETTER_TILE:
			break;
		default:
			break;
		

		}
		background.SetScale(BoardGenerator.getScale());
	}

	public void setPixelPosition(SimpleVector pos) {

		//SimpleVector letterdelta = new SimpleVector(3.0f * BoardGenerator.getScale(),
		//		3.0f * BoardGenerator.getScale(), 0.0f);
		// letterdelta.scalarMul(scale);
		//SimpleVector numberdelta = new SimpleVector(20.0f * BoardGenerator.getScale(),
			//	20.0f * BoardGenerator.getScale(), 0.0f);
		// numberdelta.scalarMul(scale);

		background.SetPosition(pos);
		//letter.SetPosition(pos.calcAdd(letterdelta));
		//number.SetPosition(pos.calcAdd(numberdelta));

	}
	public void setLetterPosition(SimpleVector pos) {

		SimpleVector letterdelta = new SimpleVector(3.0f * BoardGenerator.getScale(),
				3.0f * BoardGenerator.getScale(), 0.0f);
		// letterdelta.scalarMul(scale);
		SimpleVector numberdelta = new SimpleVector(20.0f * BoardGenerator.getScale(),
				20.0f * BoardGenerator.getScale(), 0.0f);
		// numberdelta.scalarMul(scale);

		backgroundLetter.SetPosition(pos);
		letter.SetPosition(pos.calcAdd(letterdelta));
		number.SetPosition(pos.calcAdd(numberdelta));

	}
}
