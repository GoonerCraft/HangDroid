package myapps.hangdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class HangDroidActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hang_droid);
        Toast.makeText(this, "Working", Toast.LENGTH_SHORT).show();

    }

    public void startSinglePlayerGame(View v){ //public method because we are calling our method outside of this class.

            Intent myIntent = new Intent(this, GameActivity.class);
            startActivity(myIntent);
    }

    public void startMultiGame(View v){
        Intent myIntent = new Intent(this, MultiplayerActivity.class);
        startActivity(myIntent);
    }

    public void startScores(View v){
        Intent myIntent = new Intent(this, ScoresActivity.class);
        startActivity(myIntent);
    }
}
