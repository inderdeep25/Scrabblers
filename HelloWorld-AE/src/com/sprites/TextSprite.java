package com.sprites;

import com.threed.jpct.FrameBuffer;
import com.threed.jpct.Logger;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.Texture;
import com.threed.jpct.TextureManager;

import java.util.UUID;

public class TextSprite implements ISprite{
    private char[] _alphabet;

    private UUID _id;
    public UUID GetId() {return _id;}
    public void SetId(UUID value) {_id = value;}

    private SimpleVector _position;
    public SimpleVector GetPosition(){
        return _position;
    }
    public void SetPosition(SimpleVector value){
        _position = value;
    }

    private float _scale;
    public float GetScale() {return _scale;}
    public void SetScale(float value) {
        _scale = value;}

    private String _message;
    public String GetMessage() {return _message;}
    public void SetMessage(String value) {_message = value;}

    private Texture _atlasTexture;
    public Texture GetTexture() {Logger.log("WARNING! GetTexture called on TextSprite"); return null;}
    public void SetTexture(Texture value) {Logger.log("WARNING! SetTexture called on TextSprite");}

    public int GetAnimationIndex() {Logger.log("WARNING! GetAnimationIndex called on TextSprite"); return -1;}
    public void SetAnimationIndex(int value) {Logger.log("WARNING! SetMessage called on TextSprite");}

    public void FireTemporaryAnimation(int animationIndex){Logger.log("WARNING! FireTemporaryAnimation called on TextSprite");}

    int _textureWidth;
    int _textureHeight;

    public TextSprite(String spriteName){
        _id = UUID.randomUUID();

        TextSpriteBlueprint blueprintData = SpriteBlueprintProvider.GetInstance().GetTextSprite(spriteName);

        _atlasTexture = TextureManager.getInstance().getTexture(blueprintData.TextureName);
        _textureWidth = blueprintData.Width;
        _textureHeight = blueprintData.Height;
        _alphabet = blueprintData.CharOrder;
        _scale = blueprintData.Scale;
        _message = blueprintData.Message;

        _position = new SimpleVector(0,0,0);
    }

    private TextureCoords IndexToCoordinates(int index){
        int atlasWidth = _atlasTexture.getWidth();
        int atlasHeight = _atlasTexture.getHeight();
        int imagesInAtlasX = atlasWidth / _textureWidth;
        int imagesInAtlasY = atlasHeight / _textureHeight;

        // look for the index
        int iterator = 0;
        for(int y = 0; y < imagesInAtlasY; ++y){
            for(int x = 0; x < imagesInAtlasX; ++x){
                if(index == iterator)
                    return new TextureCoords(x * _textureWidth, y * _textureHeight);

                iterator ++;
            }
        }

        // otherwise, couldn't find the index
        return null;
    }

    private int IndexFromCharacter(char character){
        for(int i = 0; i < _alphabet.length; ++i){
            if(character == _alphabet[i])
                return i;
        }

        return -1;
    }

    public void Update(float elapsedTime){}

    public void Draw(FrameBuffer fb) {
        int targetY = (int) _position.y;

        for(int i = 0; i < _message.length(); ++i){
            int targetX = (int) _position.x + (int) (i * (_scale * _textureWidth));

            int characterIndex = IndexFromCharacter(_message.charAt(i));
            // if -1 then the character doesn't exist in the atlas
            if(characterIndex == -1)
                return;

            TextureCoords atlasCoords = IndexToCoordinates(characterIndex);

            fb.blit(_atlasTexture, atlasCoords.x, atlasCoords.y, targetX, targetY,
                    _textureWidth, _textureHeight,
                    (int) (_textureWidth * _scale), (int) (_textureHeight * _scale),
                    255, false);
        }
    }
}
