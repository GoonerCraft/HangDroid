package myapps.hangdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    String mWord;
    int nFailCounter = 0;
    int nGuessedLetters = 0;
    int npoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setRandomWord();
    }

    public void introduceLetter(View v){ //public method because we are calling our method outside of this class.

        EditText myEditText = (EditText) findViewById(R.id.editTextLetter);

        String letter = myEditText.getText().toString();

        myEditText.setText("");

        Log.d("MYLOG", "The introduced is " + letter);

        if(letter.length() ==1){
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
            clearScreen();
            npoints++;
            setRandomWord();
        }
    }

    public void setRandomWord(){

        String words = "AAHS AALS ABAC ABAS ABBA ABBE ABBS ABED ABET ABID ABLE ABLY ABOS ABRI ABUT ABYE ABYS ACAI ACCA ACED ACER ACES ACHE ACHY ACID ACME BAFF BAFT BAGH BAGS BAHT BAIL BAIT BAJU BAKE BALD BALE BALK BALL BALM BALS BALU BAMS BANC BAND BANE BANG BANI BANK BANS BANT BAPS CARS CART CASA CASE CASH CASK CAST CATE CATS CAUF CAUK CAUL CAUM CAUP CAVA CAVE CAVY CAWK CAWS CAYS CEAS CECA CEDE CEDI CEES CEIL CELL CELS CELT CENS CENT CEPE CEPS CERE CERO CERT CESS CETE CHAD DEEP DEER DEES DEET DEEV DEFI DEFT DEFY DEGS DEID DEIF DEIL DEKE DELE DELF DELI DELL DELO DELS DELT DEME DEMO DEMY DENE DENI DENS ERGO ERGS ERIC ERKS ERNE ERNS EROS ERRS ERST ERUV ESES ESKY ESNE FENT FEOD FERE FERM FERN FESS FEST FETA FETE FETS FETT FEUD FEUS GAIT GAJO GALA GALE GALL GALS GAMA GAMB GAME GAMP GAMS GAMY GANE HEAR HEAT HEBE HECH HECK HEED HEEL HEFT HEHS HEID HEIL HEIR HELD IDEM IDES IDLE IDLY IDOL IDYL IFFY IGAD IGGS IGLU IKAN IKAT IKON JAGS JAIL JAKE JAKS JAMB JAMS JANE JANN JAPE JAPS JARK JARL JARP KIBE KICK KIDS KIEF KIER KIFF KIFS KIKE KILD KILL KILN KILO KILP LOSE LOSH LOSS LOST LOTA LOTE LOTH LOTI LOTO LOTS LOUD LOUN LOUP MAES MAGE MAGG MAGI MAGS MAID MAIK MAIL MAIM MAIN MAIR MAKE MAKI NEAR NEAT NEBS NECK NEDS NEED NEEM NEEP NEFS NEGS NEIF NEKS NEMA OGRE OHED OHIA OHMS OHOS OIKS OILS OILY OINK OINT OKAS OKAY OKEH PEND PENE PENI PENK PENS PENT PEON PEPO PEPS PERE PERI PERK PERM QUAY QUEP QUEY QUID QUIM QUIN QUIP QUIT QUIZ QUOD QUOP RABI RACA ROSE ROST ROSY ROTA ROTE ROTI ROTL ROTO ROTS ROUE ROUL ROUM ROUP SEME SEMI SENA SEND SENE SENS SENT SEPS SEPT SERA SERE SERF SERK TUMS TUNA TUND TUNE TUNG TUNS TUNY TUPS TURD TURF TURK TURM TURN UPBY UPDO UPGO UPON UPSY UPTA URAO URBS URDE URDS URDY UREA URES VATS VATU VAUS VAUT VAVS VAWS VEAL VEEP VEER VEES VEGA VEGO VEHM WARE WARK WARM WARN WARP WARS WART WARY WASE WASH WASM WASP WAST YERK YESK YEST YETI YETT YEUK YEVE YEWS YGOE YIDS YIKE YILL YINS ZOBO ZOBU ZOEA ZOIC ZOLS ZONA ZONE ZONK ZOOM ZOON ZOOS ZOOT ZORI";

        String[] arrayWords = words.split(" ");


        Log.d("MYLog" , "The array length" +arrayWords.length);

        int randomNumber = (int) (Math.random() *arrayWords.length);

        String randomWord = arrayWords[randomNumber];

        mWord = randomWord;

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
            finish();
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
