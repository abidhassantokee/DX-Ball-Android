package tokee.test.dxball;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class DxBallActivity extends Activity {

    AudioManager audManager;
    public static String[] stage;
    public static MediaPlayer bgMusic=null;

    public void readStageTextFile()
    {
        InputStream inputStream = this.getResources().openRawResource(R.raw.allstage);

        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader buffreader = new BufferedReader(inputreader);
        String line="";
        StringBuilder text = new StringBuilder();
        int count=0;
        try {
            while (( line = buffreader.readLine()) != null) {
                stage[count]=line;
                count++;
            }
        } catch (IOException e) {
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("Life Cycle","On Create Called");
        super.onCreate(savedInstanceState);
        Ball.setContext(this);
        audManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        stage = new String[10];
        readStageTextFile();
    }

    @Override
    protected void onResume() {
        Log.d("Life Cycle", "On Resume Called");
        super.onResume();
        if (GameCanvas.gameView)
        {
            if (GameCanvas.gamePause == true)
            {
                setContentView(new GameCanvas(this));
                GameCanvas.gamePause = false;
                if (GameCanvas.otherThreadRunning == false)
                    GameCanvas.startOtherThreads();
            }
        }
        else
        {
            setContentView(R.layout.activity_dx_ball);
            startBgSound();
        }
    }

    @Override
    protected void onPause() {
        Log.d("Life Cycle","On Pause Called");
        super.onPause();
        if(GameCanvas.gameView)
        {
            if(GameCanvas.firstTime==false)
                GameCanvas.gamePause=true;
        }
        else
        {
            bgMusic.stop();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP)
        {
            audManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                    AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
        }
        else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)
        {
            audManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                    AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
        }
        return super.onKeyDown(keyCode, event);
    }

    public void btnStageEvent(View view) {
        final Button btnStage = (Button) view;
        int n=0,nBricks=0,nRowLevel=0,perBrickPoint=0,brickPerRow=0,type2BrickVal1=0,type2BrickVal2=0,bonusPoint=0,count=0;
        n= Integer.parseInt(btnStage.getText().toString())-1;
        for (String retval: stage[n].split(",", 7)){
            if(count==0) { nBricks=Integer.parseInt(retval); }
            if(count==1) { nRowLevel=Integer.parseInt(retval); }
            if(count==2) { perBrickPoint=Integer.parseInt(retval); }
            if(count==3) { brickPerRow=Integer.parseInt(retval); }
            if(count==4) { type2BrickVal1=Integer.parseInt(retval); }
            if(count==5) { type2BrickVal2=Integer.parseInt(retval); }
            if(count==6) { bonusPoint=Integer.parseInt(retval); }
            count++;
        }
        Brick.setStageProperties(nBricks,nRowLevel,perBrickPoint,brickPerRow,type2BrickVal1,type2BrickVal2,bonusPoint);
        bgMusic.stop();
        setContentView(new GameCanvas(this));
    }

    @Override
    public void onBackPressed() {
        if(GameCanvas.gameView) {
            GameCanvas.gameOver=true;
            GameCanvas.firstTime=true;
            GameCanvas.gameView = false;
            setContentView(R.layout.activity_dx_ball);
            startBgSound();
        }
        else {
            super.onBackPressed();
        }
    }

    public void startBgSound()
    {
        bgMusic = MediaPlayer.create(this, R.raw.dxballthemesong);
        bgMusic.setLooping(true);
        bgMusic.start();
    }

}
