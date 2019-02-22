package com.piecergames.structure;

import java.awt.Graphics2D;

public abstract class GameObject{

    protected float[] x, y, z;
    protected float speedX, speedY, speedZ;
    protected Id id;

    public GameObject(float[] x, float[] y, float[] z, Id id){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public abstract void tick(GameObject obj);
    public abstract void render(Graphics2D g2);
    
    public float[] getX(){
        return x;
    }

    public float[] getY(){
        return y;
    }
    
    public float[] getZ(){
        return z;
    }

    public Id getId(){
        return id;
    }
    
    @Override
    public String toString() {
        String output = "";

        for (int i = 0; i < x.length; i++) {
            output += "X" + (i+1) + ": "+ x[i] + ", " + "Y" + (i+1) + ": "+ y[i] + ", " + "Z" + (i+1) + ": "+ z[i] + "\n";
        }

        return output;
    }

    @Override
    public boolean equals(Object obj) {
        GameObject gobj = (GameObject) obj;
        return this.id == gobj.id? true : false;
    }

    @Override
    public int hashCode() {
        String output = "1"+ (int)Math.ceil(x[0])+ "4"+ (int)Math.ceil(y[1]) + "3" + (int)Math.ceil(z[2])+ "2"; ;

        return Integer.parseInt(output);
    }

}