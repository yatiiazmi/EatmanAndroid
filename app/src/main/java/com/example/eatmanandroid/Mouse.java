package com.example.eatmanandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by colerogers on 7/25/16.
 */
public class Mouse {
    private int xPos, yPos, dir;

    public Mouse(int blockSize){
        //Mouse is at 8 position of length in the array x-dir
        //e.g. curMap[8,y]
        xPos = 8 * blockSize;

        //Mouse is at 4 position of height in the array y-dir
        //e.g. curMap[x,4]
        yPos = 4 * blockSize;

        dir = 4;
    }

    public int getXPos(){ return xPos; }
    public int getYPos(){ return yPos; }
    public int getDir(){ return dir; }

    public void setXPos(int xPos){ this.xPos = xPos; }
    public void setYPos(int yPos){ this.yPos = yPos; }
    public void setDir(int dir){ this.dir = dir; }

    public void drawMouse(BitmapImages bitmap, Canvas canvas, Movement movement, Paint paint, Context context, int type) {
        if (type == 0) {
            //move mouse
            movement.moveMouse0();
            //draw mouse
            canvas.drawBitmap(bitmap.getMouseBitmap0(), this.getXPos(), this.getYPos(), paint);
            //check if there is a collision and handle game over
            movement.tryDeath(context);
        }
        else if (type == 1) {
            //move mouse
            movement.moveMouse1();
            //draw mouse
            canvas.drawBitmap(bitmap.getMouseBitmap1(), this.getXPos(), this.getYPos(), paint);
            //check if there is a collision and handle game over
            movement.tryDeath(context);
        }
        else if (type == 2) {
            //move mouse
            movement.moveMouse2();
            //draw mouse
            canvas.drawBitmap(bitmap.getMouseBitmap2(), this.getXPos(), this.getYPos(), paint);
            //check if there is a collision and handle game over
            movement.tryDeath(context);
        }
        else if (type == 3) {
            //move mouse
            movement.moveMouse3();
            //draw ghost
            canvas.drawBitmap(bitmap.getMouseBitmap3(), this.getXPos(), this.getYPos(), paint);
            //check if there is a collision and handle game overDrawingView.tryDeath();
            movement.tryDeath(context);
        }
    }
}