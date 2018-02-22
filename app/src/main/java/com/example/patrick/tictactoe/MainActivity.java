package com.example.patrick.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.patrick.tictactoe.ui.TicTacToeView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnClear = findViewById(R.id.btnClear);
        final TicTacToeView ticTacToeView = findViewById(R.id.ticTacToe);

        // When button is clicked, clear the circles by calling clearBoard()
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ticTacToeView.clearBoard();
            }
        });
    }

    // Display text on screen.
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
