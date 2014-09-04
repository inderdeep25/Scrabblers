package com.asherLaakes.cavemanRunner.Sprite;

import android.util.SparseArray;

import com.threed.jpct.FrameBuffer;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.Texture;

import java.util.HashMap;
import java.util.UUID;

public class SpriteManager {
    static private SpriteManager _instance;

    SparseArray<HashMap<UUID, ISprite>> _sprites;

    private SpriteManager(){
       _sprites = new SparseArray<HashMap<UUID, ISprite>>();
    }

    public static SpriteManager GetInstance(){
        if(_instance == null)
            _instance = new SpriteManager();

        return _instance;
    }

    public SimpleSpriteToken AddSimpleSprite(String spriteName, int layer){
        HashMap<UUID, ISprite> targetList = _sprites.get(layer);

        if(targetList == null) {
            targetList = new HashMap<UUID, ISprite>();

            SimpleSprite fab = new SimpleSprite(spriteName);
            targetList.put(fab.GetId(), fab);

            _sprites.append(layer, targetList);

            return new SimpleSpriteToken(fab, layer);
        } else {
            SimpleSprite fab = new SimpleSprite(spriteName);
            targetList.put(fab.GetId(), fab);

            return new SimpleSpriteToken(fab, layer);
        }
    }

    public TextSpriteToken AddTextSprite(String spriteName, int layer){
        HashMap<UUID, ISprite> targetList = _sprites.get(layer);

        if(targetList == null) {
            targetList = new HashMap<UUID, ISprite>();

            TextSprite fab = new TextSprite(spriteName);
            targetList.put(fab.GetId(), fab);

            _sprites.append(layer, targetList);

            return new TextSpriteToken(fab, layer);
        } else {
            TextSprite fab = new TextSprite(spriteName);
            targetList.put(fab.GetId(), fab);

            return new TextSpriteToken(fab, layer);
        }
    }

    public void DeleteSprite(int layer, UUID spriteId){
        HashMap<UUID, ISprite> spriteList = _sprites.get(layer);

        if(spriteList == null)
            return;

        spriteList.remove(spriteId);
    }

    public void Draw(FrameBuffer fb){
        for(int i = 0; i < _sprites.size(); i++){
            HashMap<UUID, ISprite> currentMap = _sprites.get(_sprites.keyAt(i));
            for(UUID id : currentMap.keySet()){
                currentMap.get(id).Draw(fb);
            }
        }
    }

    public void UpdateSpritePosition(SimpleVector newPosition, ISpriteToken token){
        HashMap<UUID, ISprite> spriteList = _sprites.get(token.GetLayer());

        if(spriteList == null)
            return;

        spriteList.get(token.GetId()).SetPosition(newPosition);
    }

    public void UpdateSpriteScale(float scale, ISpriteToken token){
        HashMap<UUID, ISprite> spriteList = _sprites.get(token.GetLayer());

        if(spriteList == null)
            return;

        spriteList.get(token.GetId()).SetScale(scale);
    }

    public void UpdateSpriteTexture(Texture texture, ISpriteToken token){
        HashMap<UUID, ISprite> spriteList = _sprites.get(token.GetLayer());

        if(spriteList == null)
            return;

        spriteList.get(token.GetId()).SetTexture(texture);
    }

    public void UpdateSpriteMessage(String message, ISpriteToken token){
        HashMap<UUID, ISprite> spriteList = _sprites.get(token.GetLayer());

        if(spriteList == null)
            return;

        spriteList.get(token.GetId()).SetMessage(message);
    }
}
