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

    private int _textureWidth;
    private int _textureHeight;
    private int _adjustedTextureWidth;
    private int _adjustedTextureHeight;

    private float Scale;
    public float GetScale() {return Scale;}
    public void SetScale(float value) {
        Scale = value;
        RecalculateAdjustedTextureSize();
    }

    public String GetMessage() {Logger.log("WARNING! GetMessage called on SimpleSprite"); return null;}
    public void SetMessage(String value) {Logger.log("WARNING! SetMessage called on SimpleSprite");}

    public int GetAnimationIndex() {Logger.log("WARNING! GetAnimationIndex called on SimpleSprite"); return -1;}
    public void SetAnimationIndex(int value) {Logger.log("WARNING! SetMessage called on SimpleSprite");}

    public void FireTemporaryAnimation(int animationIndex){Logger.log("WARNING! FireTemporaryAnimation called on SimpleSprite");}

    private Texture image;
    public Texture GetTexture() {return image;}
    public void SetTexture(Texture value) {image = value; RecalculateTextureSize();}

    public SimpleSprite(String spriteName){
        Id = UUID.randomUUID();

        SimpleSpriteBlueprint blueprintData = SpriteBlueprintProvider.GetInstance().GetSimpleSprite(spriteName);
        image = TextureManager.getInstance().getTexture(blueprintData.TextureName);
        SetScale(blueprintData.Scale);
        SetPosition(blueprintData.Position);

        RecalculateTextureSize();
    }

    private void RecalculateTextureSize(){
        _textureWidth = image.getWidth();
        _textureHeight = image.getHeight();

        RecalculateAdjustedTextureSize();
    }

    private void RecalculateAdjustedTextureSize(){
        _adjustedTextureWidth = (int) (image.getWidth() * Scale);
        _adjustedTextureHeight = (int) (image.getHeight() * Scale);
    }

    public void Update(float elapsedTime){} // just a placeholder for animated sprite to use...
    // I figure the performance hit of an unnecessary function call is preferable to a cast attempt for every sprite.

    public void Draw(FrameBuffer fb){
        fb.blit(image, 0, 0, (int) Position.x, (int) Position.y, _textureWidth, _textureHeight,
                _adjustedTextureWidth, _adjustedTextureHeight, 255, false);
    }
}
