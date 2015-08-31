package myapps.hangdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameMultiActivity extends AppCompatActivity {

    String mWord;
    int nFailCounter = 0;
    int nGuessedLetters = 0;
    int npoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_game);

        String wordSent = getIntent().getStringExtra("WORD_IDENTIFIER");

        Log.d("MyLog: ", wordSent);

        mWord = wordSent;

        createTextViews(wordSent);
    }

    public void introduceLetter(View v){ //public method because we are calling our method outside of this class.

        EditText myEditText = (EditText) findViewById(R.id.editTextLetter);

        String letter = myEditText.getText().toString();

        myEditText.setText("");

        Log.d("MYLOG", "The introduced is " + letter);

        if(letter.length() >0){
            checkLetter(letter.toUpperCase());
        }else{
            Toast.makeText(this, "Please Introduce a letter" , Toast.LENGTH_SHORT).show();
        }
    }


    public void checkLetter(String introducedLetter){

        char charIntroduced = introducedLetter.charAt(0);// First key it picks up from the user.

        boolean letterGuessed = false;

        for(int i=0; i<mWord.length();i++){

                char charFromTheWord = mWord.charAt(i);

                if(mWord.charAt(i) == charIntroduced){

                Log.d("MYLOG", "There was one match");

                showLettersAtIndex(i, charIntroduced);

                letterGuessed = true;

                    nGuessedLetters++;
            }
        }
        if(letterGuessed==false){
            letterFailed(Character.toString(charIntroduced));
        }
        if(nGuessedLetters == mWord.length()){
            finish();
        }
    }

    public void createTextViews(String word){


        LinearLayout layoutLetters = (LinearLayout) findViewById(R.id.layoutLetters);

        for(int i =0; i<word.length();i++){
            TextView newTextView = (TextView) getLayoutInflater().inflate(R.layout.textviewmulti, null); //Inflate, in order to add xml to screen from java

            layoutLetters.addView(newTextView); //Adding the xml file to the container. Had to change from margin to padding on the xml file.
        }

    }

    public void clearScreen(){
        TextView failedWords = (TextView) findViewById(R.id.textViewFailedWords);
        failedWords.setText("");

        nFailCounter=0;
        nGuessedLetters=0;

        LinearLayout layoutLetters = (LinearLayout) findViewById(R.id.layoutLetters);

        for (int i=0; i<layoutLetters.getChildCount(); i++){ //Clear the letters in _

            TextView currentTextview = (TextView) layoutLetters.getChildAt(i);
            currentTextview.setText("_");
        }
        ImageView imageView= (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.hangdroid_0);

    }
    public void letterFailed(String letterFailed){

        nFailCounter++;
        ImageView imageView= (ImageView) findViewById(R.id.imageView);

        TextView failedWords = (TextView) findViewById(R.id.textViewFailedWords);

        String previousFail = failedWords.getText().toString();

        failedWords.setText(previousFail+letterFailed);

        if(nFailCounter==1){
            imageView.setImageResource(R.drawable.hangdroid_1);
        }else if(nFailCounter ==2 ){
            imageView.setImageResource(R.drawable.hangdroid_2);
        }else if(nFailCounter ==3 ){
            imageView.setImageResource(R.drawable.hangdroid_3);
        }else if(nFailCounter ==4 ){
            imageView.setImageResource(R.drawable.hangdroid_4);
        }else if(nFailCounter ==5 ){
            imageView.setImageResource(R.drawable.hangdroid_5);
        }else if(nFailCounter == 6 ){
            Intent gameOverIntent = new Intent(this, GameOverActivity.class);
            gameOverIntent.putExtra("POINTS_IDENTIFIER", npoints);

            startActivity(gameOverIntent);
        }
    }
    /**
     * Displaying a letter guessed by the user
     * @param position of the letter
     * @param letterGuessed
     */
    public void showLettersAtIndex(int position, char letterGuessed){
        LinearLayout layoutLetters = (LinearLayout) findViewById(R.id.layoutLetters);
        TextView textView = (TextView) layoutLetters.getChildAt(position);
        textView.setText(Character.toString(letterGuessed)); //Conversion from string to character! must do this to display it on screen.
    }
}
