package com.example.eatmanandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by colerogers on 7/25/16.
 */
public class BitmapImages {
    //private instance variables
    private int spriteSize, arrowSize, buttonSizeHeight, buttonSizeWidth;
    private Bitmap[] eatmanRight, eatmanDown, eatmanLeft, eatmanUp;
    private Bitmap[] arrowRight, arrowDown, arrowLeft, arrowUp;
    private Bitmap mouseBitmap0, mouseBitmap1, mouseBitmap2, mouseBitmap3;
    private Bitmap muteBitmap, pauseBitmap;

    public BitmapImages(int blockSize, Context context){
        spriteSize = blockSize;
        arrowSize = blockSize*7;
        buttonSizeHeight = blockSize*2;
        buttonSizeWidth = blockSize*4;

        // Add bitmap images of right arrow indicators
        arrowRight = new Bitmap[7]; // 7 image frames for right direction
        arrowRight[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.right_arrow_frame1), arrowSize, arrowSize, false);
        arrowRight[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.right_arrow_frame2), arrowSize, arrowSize, false);
        arrowRight[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.right_arrow_frame3), arrowSize, arrowSize, false);
        arrowRight[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.right_arrow_frame4), arrowSize, arrowSize, false);
        arrowRight[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.right_arrow_frame5), arrowSize, arrowSize, false);
        arrowRight[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.right_arrow_frame6), arrowSize, arrowSize, false);
        arrowRight[6] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.right_arrow_frame7), arrowSize, arrowSize, false);


        arrowDown = new Bitmap[7]; // 7 images frames for down direction
        arrowDown[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.down_arrow_frame1), arrowSize, arrowSize, false);
        arrowDown[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.down_arrow_frame2), arrowSize, arrowSize, false);
        arrowDown[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.down_arrow_frame3), arrowSize, arrowSize, false);
        arrowDown[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.down_arrow_frame4), arrowSize, arrowSize, false);
        arrowDown[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.down_arrow_frame5), arrowSize, arrowSize, false);
        arrowDown[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.down_arrow_frame6), arrowSize, arrowSize, false);
        arrowDown[6] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.down_arrow_frame7), arrowSize, arrowSize, false);


        arrowUp = new Bitmap[7]; // 7 frames for each direction
        arrowUp[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.up_arrow_frame1), arrowSize, arrowSize, false);
        arrowUp[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.up_arrow_frame2), arrowSize, arrowSize, false);
        arrowUp[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.up_arrow_frame3), arrowSize, arrowSize, false);
        arrowUp[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.up_arrow_frame4), arrowSize, arrowSize, false);
        arrowUp[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.up_arrow_frame5), arrowSize, arrowSize, false);
        arrowUp[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.up_arrow_frame6), arrowSize, arrowSize, false);
        arrowUp[6] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.up_arrow_frame7), arrowSize, arrowSize, false);


        arrowLeft = new Bitmap[7]; // 7 images frames for left direction
        arrowLeft[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.left_arrow_frame1), arrowSize, arrowSize, false);
        arrowLeft[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.left_arrow_frame2), arrowSize, arrowSize, false);
        arrowLeft[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.left_arrow_frame3), arrowSize, arrowSize, false);
        arrowLeft[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.left_arrow_frame4), arrowSize, arrowSize, false);
        arrowLeft[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.left_arrow_frame5), arrowSize, arrowSize, false);
        arrowLeft[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.left_arrow_frame6), arrowSize, arrowSize, false);
        arrowLeft[6] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.left_arrow_frame7), arrowSize, arrowSize, false);


        // Add bitmap images of eatman facing right
        eatmanRight = new Bitmap[4];
        eatmanRight[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(),R.drawable.eatman_right1), spriteSize, spriteSize, false);
        eatmanRight[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.eatman_right2), spriteSize, spriteSize, false);
        eatmanRight[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.eatman_right3), spriteSize, spriteSize, false);
        eatmanRight[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.eatman_round), spriteSize, spriteSize, false);


        // Add bitmap images of eatman facing down
        eatmanDown = new Bitmap[4];
        eatmanDown[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.eatman_down1), spriteSize, spriteSize, false);
        eatmanDown[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.eatman_down2), spriteSize, spriteSize, false);
        eatmanDown[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.eatman_down3), spriteSize, spriteSize, false);
        eatmanDown[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.eatman_round), spriteSize, spriteSize, false);


        // Add bitmap images of eatman facing left
        eatmanLeft = new Bitmap[4];
        eatmanLeft[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.eatman_left1), spriteSize, spriteSize, false);
        eatmanLeft[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.eatman_left2), spriteSize, spriteSize, false);
        eatmanLeft[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.eatman_left3), spriteSize, spriteSize, false);
        eatmanLeft[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.eatman_round), spriteSize, spriteSize, false);


        // Add bitmap images of eatman facing up
        eatmanUp = new Bitmap[4];
        eatmanUp[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.eatman_up1), spriteSize, spriteSize, false);
        eatmanUp[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.eatman_up2), spriteSize, spriteSize, false);
        eatmanUp[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.eatman_up3), spriteSize, spriteSize, false);
        eatmanUp[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.eatman_round), spriteSize, spriteSize, false);

        // Add bitmap image of pause
        pauseBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.pause), buttonSizeWidth, buttonSizeHeight, false);

        // Add bitmap image of mouse
        mouseBitmap0 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.mouse0), spriteSize, spriteSize, false);

        // Add bitmap image of mouse
        mouseBitmap1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.mouse1), spriteSize, spriteSize, false);

        // Add bitmap image of mouse
        mouseBitmap2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.mouse2), spriteSize, spriteSize, false);

        // Add bitmap image of mouse
        mouseBitmap3 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.mouse3), spriteSize, spriteSize, false);
    }

    //public getters
    public Bitmap getPauseBitmap(){ return pauseBitmap; }
    public Bitmap getMuteBitmap(){ return muteBitmap; }

    public Bitmap[] getEatmanRight(){ return eatmanRight; }
    public Bitmap[] getEatmanLeft(){ return eatmanLeft; }
    public Bitmap[] getEatmanUp(){ return eatmanUp; }
    public Bitmap[] getEatmanDown(){ return eatmanDown; }

    public Bitmap getMouseBitmap0(){ return mouseBitmap0; }
    public Bitmap getMouseBitmap1(){ return mouseBitmap1; }
    public Bitmap getMouseBitmap2(){ return mouseBitmap2; }
    public Bitmap getMouseBitmap3(){ return mouseBitmap3; }

    public Bitmap[] getArrowRight(){ return arrowRight; }
    public Bitmap[] getArrowLeft(){ return arrowLeft; }
    public Bitmap[] getArrowUp(){ return arrowUp; }
    public Bitmap[] getArrowDown(){ return arrowDown; }
}