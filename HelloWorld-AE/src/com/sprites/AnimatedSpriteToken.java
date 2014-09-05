package com.sprites;

import com.threed.jpct.SimpleVector;
import com.threed.jpct.Texture;

import java.util.UUID;

public class AnimatedSpriteToken implements ISpriteToken{
    private UUID _id;
    private int _layer;

    public UUID GetId()   {return _id;}
    public int GetLayer() {return _layer;}

    public AnimatedSpriteToken(AnimatedSprite reference, int layer){
        _id = reference.GetId();
        _layer = layer;
    }

    public void SetPosition(SimpleVector position){
        SpriteManager.GetInstance().UpdateSpritePosition(position, this);
    }

    public void SetScale(float scale){
        SpriteManager.GetInstance().UpdateSpriteScale(scale, this);
    }

    public void SwitchAnimation(int animationIndex){
        SpriteManager.GetInstance().SwitchSpriteAnimation(animationIndex, this);
    }

    public void FireTemporaryAnimation(int animationIndex){
        SpriteManager.GetInstance().FireTemporarySpriteAnimation(animationIndex, this);
    }

    public void Delete(){
        SpriteManager.GetInstance().DeleteSprite(this);
    }
}
