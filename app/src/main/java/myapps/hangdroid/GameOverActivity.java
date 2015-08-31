package myapps.hangdroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GameOverActivity extends AppCompatActivity {
    int nPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        int points = getIntent().getIntExtra("POINTS_IDENTIFIER", 0); //wE RECEIVE THE INTEGER 0 is the default value

        TextView textviewPoints = (TextView) findViewById(R.id.textViewPoints);
        textviewPoints.setText(String.valueOf(points)); //This is converting from integer to string!!!

        nPoints = points;

    }


    public void saveScore(View v){
        SharedPreferences preferences = getSharedPreferences("MY_PREF" , Context.MODE_PRIVATE);

        EditText editText = (EditText) findViewById(R.id.nameText);

        String name = editText.getText().toString();
        String previousScore = preferences.getString("SCORES", " ");

        SharedPreferences.Editor editor = preferences.edit(); //shared preferences store values to device.

        editor.putString("SCORES " , name + " " + nPoints + " POINTS\n" + previousScore);

        editor.commit(); // to commit the change.

        Toast.makeText(this, "Record Saved" , Toast.LENGTH_SHORT).show();

        finish();

    }

}
