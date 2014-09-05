package com.sprites;

import java.util.UUID;

public interface ISpriteToken {
    UUID GetId();
    int GetLayer();
    void Delete();
}
