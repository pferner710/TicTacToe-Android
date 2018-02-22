package com.example.patrick.tictactoe.model;

/**
 * Created by Patrick on 2/19/18.
 */

public class TicTacToeModel {

    // This will be the only instance of the model, since the constructor is private
    // We do this because the model represents the state of the game, and we only want 1
    private static TicTacToeModel instance;

    // This will be how we access or create our model
    // We can call this method in any class and we will get the same object
    public static TicTacToeModel getInstance(){
        if (instance == null){
            instance = new TicTacToeModel();
        }

        return instance;
    }

    // Instance of this class cannot be made outside this class
    private TicTacToeModel(){

    }

    public static short EMPTY = 0;
    public static short CROSS = 1;
    public static short CIRCLE = 2;

    // Represents the tic tac toe board
    public short[][] model = {
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY}
    };

    private short nextPlayer = CIRCLE;


    public void setFieldContent(short x, short y, short player) {
        model[x][y] = player;
    }

    public short getFieldContent(short x, short y){
        return model[x][y];
    }

}
