package com.sprites;

import com.threed.jpct.SimpleVector;
import com.threed.jpct.Texture;

import java.util.UUID;

public class SimpleSpriteToken implements ISpriteToken{
    private UUID _id;
    private int _layer;

    public UUID GetId(){return _id;}
    public int GetLayer(){return _layer;}

    public SimpleSpriteToken(ISprite reference, int layer){
        _id = reference.GetId();
        _layer = layer;
    }

    public void SetPosition(SimpleVector position){
        SpriteManager.GetInstance().UpdateSpritePosition(position, this);
    }

    public void SetScale(float scale){
        SpriteManager.GetInstance().UpdateSpriteScale(scale, this);
    }

    public void SetTexture(Texture texture){
        SpriteManager.GetInstance().UpdateSpriteTexture(texture, this);
    }

    public void Delete(){
        SpriteManager.GetInstance().DeleteSprite(this);
    }
}
