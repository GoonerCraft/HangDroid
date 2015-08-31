package myapps.hangdroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ScoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        SharedPreferences preferences = getSharedPreferences("MY_PREF" , Context.MODE_PRIVATE);

        String scores = preferences.getString("SCORES" , "NO SCORES");

        TextView scoresView = (TextView) findViewById(R.id.textViewScores);

        scoresView.setText(scores);
    }

}
