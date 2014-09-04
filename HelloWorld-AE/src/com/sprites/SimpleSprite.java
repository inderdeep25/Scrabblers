package com.sprites;

import com.threed.jpct.FrameBuffer;
import com.threed.jpct.Logger;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.Texture;
import com.threed.jpct.TextureManager;

import java.util.UUID;

public class SimpleSprite implements ISprite {
    private UUID Id;
    public UUID GetId() {return Id;}
    public void SetId(UUID value) {Id = value;}

    private SimpleVector Position;
    public SimpleVector GetPosition(){
        return Position;
    }
    public void SetPosition(SimpleVector value){
        Position = value;
    }

    private float Scale;
    public float GetScale() {return Scale;}
    public void SetScale(float value) {Scale = value;}

    public String GetMessage() {Logger.log("WARNING! GetMessage called on Sprite"); return null;}
    public void SetMessage(String value) {Logger.log("WARNING! SetMessage called on Sprite");}

    private Texture image;
    public Texture GetTexture() {return image;}
    public void SetTexture(Texture value) {image = value;}

    public SimpleSprite(String spriteName){
        Id = UUID.randomUUID();

        Position = new SimpleVector(0,0,0);

        SimpleSpriteBlueprint blueprintData = SpriteBlueprintProvider.GetInstance().GetSprite(spriteName);
        image = TextureManager.getInstance().getTexture(blueprintData.TextureName);
        Scale = blueprintData.Scale;
    }

    public void Update(){} // just a placeholder for animated sprite to use...
    // I figure the performance hit of an unnecessary function call is preferable to a cast attempt for every sprite.

    public void Draw(FrameBuffer fb){
        int _textureWidth = image.getWidth();
        int _textureHeight = image.getHeight();

        fb.blit(image, 0, 0, (int) Position.x, (int) Position.y, _textureWidth, _textureHeight,
                (int) Scale * _textureWidth, (int) Scale * _textureHeight, 255, false);
    }
}
