package com.piecergames.structure;

import java.util.ArrayList;
import java.util.List;

public class Handler {
    private List<GameObject> objects = new ArrayList<>();

    public void tick(GameObject obj) {
        for(GameObject gObject: objects){
            gObject.tick(obj);
        }
    }

    public void addObject(GameObject obj){
        objects.add(obj);
    }

    public void removeObject(GameObject obj) {
        objects.remove(obj);
    }
}