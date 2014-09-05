package com.threed.jpct.example;

import com.sprites.SimpleSpriteToken;
import com.sprites.SpriteManager;
import com.sprites.TextSpriteToken;
import com.threed.jpct.SimpleVector;

public class TileAC {
	SimpleSpriteToken background;
	TextSpriteToken letter;
	TextSpriteToken number;
	TextSpriteToken board;
	
	float scale = 10;
	
	public TileAC(SimpleVector x,String str,String num){
		background = SpriteManager.GetInstance().AddSimpleSprite("blank_tile_blueprint", 1);
		letter=SpriteManager.GetInstance().AddTextSprite("alphabet_blueprint", 2);
		number=SpriteManager.GetInstance().AddTextSprite("number_blueprint", 3);
		
		letter.SetMessage(str);
		number.SetMessage(num);
		/*
		background.SetScale(scale);
		letter.SetScale(scale);
		number.SetScale(scale);
		*/
		setPosition(x);
		
		
	}
	
	public TileAC(SimpleVector x,String str){
		board=SpriteManager.GetInstance().AddTextSprite("Board_blueprint", 0);
		board.SetMessage(str);
		
		board.SetPosition(x);
	}
	
	public void setPosition(SimpleVector pos){
	
		SimpleVector letterdelta=new SimpleVector(3.0f*1.5,3.0f*1.5,0.0f);
		//letterdelta.scalarMul(scale);
		SimpleVector numberdelta=new SimpleVector(20.0f*1.5,20.0f*1.5,0.0f);
		//numberdelta.scalarMul(scale);
		
		background.SetPosition(pos);
		letter.SetPosition(pos.calcAdd(letterdelta));
		number.SetPosition(pos.calcAdd(numberdelta));
		
	}
}
