package com.example.patrick.tictactoe.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.patrick.tictactoe.model.TicTacToeModel;


/**
 * Created by Patrick on 2/15/18.
 */

public class TicTacToeView extends View {

    private Paint paintBackground;
    private Paint paintLine;

    // Originally used a list to keep track of drawing circles
    // Now, we will use our models class
    // Use listCircles.for and hit enter to auto generate a loop for it

    // Inherits from abstract View class to display on screen
    public TicTacToeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paintBackground = new Paint();
        paintBackground.setColor(Color.BLACK);
        paintBackground.setStyle(Paint.Style.FILL);

        paintLine = new Paint();
        paintLine.setColor(Color.WHITE);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(5);
    }

    // Is called when view is created
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0, 0, getWidth(), getHeight(), paintBackground);

        // Create the tic tac toe grid
        drawGameArea(canvas);

        drawPlayers(canvas);
    }

    // Draw circles and crosses, from the matrix in models
    private void drawPlayers(Canvas canvas) {
        for (short i = 0; i < 3; i++) {
            for (short j = 0; j < 3; j++) {
                if (TicTacToeModel.getInstance().getFieldContent(i,j) == TicTacToeModel.CIRCLE) {

                    // draw a circle at the center of the field

                    // X coordinate: left side of the square + half width of the square
                    float centerX = i * getWidth() / 3 + getWidth() / 6;
                    float centerY = j * getHeight() / 3 + getHeight() / 6;
                    int radius = getHeight() / 6 - 2;

                    canvas.drawCircle(centerX, centerY, radius, paintLine);

                } else if (TicTacToeModel.getInstance().getFieldContent(i,j) == TicTacToeModel.CROSS) {
                    canvas.drawLine(i * getWidth() / 3, j * getHeight() / 3,
                            (i + 1) * getWidth() / 3,
                            (j + 1) * getHeight() / 3, paintLine);

                    canvas.drawLine((i + 1) * getWidth() / 3, j * getHeight() / 3,
                            i * getWidth() / 3, (j + 1) * getHeight() / 3, paintLine);
                }
            }
        }
    }

    // * Auto generate method by selecting code and Command+Option+m *
    private void drawGameArea(Canvas canvas) {
        canvas.drawRect(0, 0, getWidth(), getHeight(), paintLine);

        // two horizontal lines
        canvas.drawLine(0, getHeight() / 3, getWidth(), getHeight() / 3,
                paintLine);
        canvas.drawLine(0, 2 * getHeight() / 3, getWidth(),
                2 * getHeight() / 3, paintLine);

        // two vertical lines
        canvas.drawLine(getWidth() / 3, 0, getWidth() / 3, getHeight(),
                paintLine);
        canvas.drawLine(2 * getWidth() / 3, 0, 2 * getWidth() / 3, getHeight(),
                paintLine);
    }


    // Calls this when screen is touched, info of location from event object.
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){

            int tX = (int)event.getX() / ((getWidth() / 3);
            int tY = (int)event.getY() / ((getWidth() / 3);

            if (TicTacToeModel.getInstance().getFieldContent(((short) tX, (short) tY))){
                TicTacToeModel.getInstance().setFieldContent(
                        (short)tX,
                        (short)tY,
                        TicTacToeModel.getInstance().getNextPlayer()
                );
            }
            TicTacToeModel.getInstance().changeNextPlayer();

            invalidate(); //This view is no longer valid, so redraw (calls onDraw again)
            // invalidate() can be called with 4 parameters, indicating a rectangle on screen that
            // will be exclusively updated.
        }
        return super.onTouchEvent(event);
    }

    public void clearBoard() {
        invalidate();
    }
}
