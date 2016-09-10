package com.sharongaceta.umpirebuddy;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Session {

    int totalOuts = 0;
    int strikeCount = 0;
    int ballCount = 0;
    //int updateCount = 0;


//    TextView numberOfStrikes;
//    TextView txtBallCount;
    //TextView updateStrikeCount;
    //TextView updateBallCount;
    TextView txtStrikeCount;
    TextView txtBallCount;
    TextView txtTotalOuts;

    private Session session;    //global variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new Session(this);    //set SharedPreferences

        strikeCount = session.getStrike();
        ballCount = session.getBall();


        //numberOfStrikes = (TextView) findViewById(R.id.strikeCount);
        txtBallCount = (TextView) findViewById(R.id.ballCount);
        txtStrikeCount = (TextView) findViewById(R.id.strikeCount);
        //updateStrikeCount = (TextView) findViewById((R.id.strikeCount));
        //updateBallCount = (TextView) findViewById(R.id.ballCount);
        txtTotalOuts = (TextView) findViewById(R.id.totalOuts);

        updateBallCount();
        updateStrikeCount();
        updateOuts();
    }



    @Override
    public void onPause() {
        session.setStrike(strikeCount);
        session.setBall(ballCount);

        super.onPause();
    }

    public void onResume() {
        super.onResume();
        strikeCount = session.getStrike();
        ballCount = session.getBall();
        updateBallCount();
        updateStrikeCount();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Handle action bar item clicks here. The action bar will automatically handle clicks
        //on the Home/Up button, so long as you specify a parent activity in AndroidManifest.xml

        int menuItemId = item.getItemId();

        if (menuItemId == R.id.action_about) {
            Intent aboutIntent = new Intent(getApplicationContext(), com.sharongaceta.umpirebuddy.AboutActivity.class);
            startActivity(aboutIntent);
            return true;
        }

        if (menuItemId == R.id.action_reset) {

            strikeCount = 0;
            updateStrikeCount();
            ballCount = 0;
            updateBallCount();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void strikeButton(View view) {

        //On each click on the button, counter value will increase
        //txtStrikeCount.setText(Integer.toString(strikeCount));

        strikeCount++;
        if (strikeCount >= 3) {
            AlertDialog.Builder strikeDialogAlert = new AlertDialog.Builder(this);
            strikeDialogAlert.setMessage("Out!");
            strikeDialogAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    strikeCount = 0;
                    ballCount = 0;
                    updateStrikeCount();
                    updateBallCount();
                    incrementTotalOuts();
                    updateOuts();
                }
            });
            strikeDialogAlert.setCancelable(true);
            strikeDialogAlert.create().show();
        }
        updateStrikeCount();

    }

    public void ballButton(View view) {

        //On each click on the button, ball count value will increase
        //txtBallCount.setText("Ball: " + Integer.toString(ballCount));

        ballCount++;
        if (ballCount >= 4) {
            final AlertDialog.Builder ballDialogAlert = new AlertDialog.Builder(this);
            ballDialogAlert.setMessage("Walk!");
            ballDialogAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int id) {

                    strikeCount = 0;
                    ballCount = 0;
                    updateStrikeCount();
                    updateBallCount();
                }
            });

            ballDialogAlert.setCancelable(true);
            ballDialogAlert.create().show();
        }
        updateBallCount();
    }

    public void incrementTotalOuts(){
        totalOuts++;
    }
    private void updateOuts() {
        txtTotalOuts.setText("Total: " + Integer.toString(totalOuts));
    }

    public void updateStrikeCount() {
        txtStrikeCount.setText(Integer.toString(strikeCount));

    }

    public void updateBallCount() {
        txtBallCount.setText(Integer.toString(ballCount));
    }
}
