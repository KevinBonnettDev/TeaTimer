package bonnett.kevin.teatimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView timerTextView;
    SeekBar timerSeekBar;
    Boolean timerActive = false;
    CountDownTimer countDownTimer;

    Button customTimerButton;
    Button frenchPressTimerButton;
    Button blackTeaTimerButton;
    Button greenTeaTimerButton;
    Button herbalTeaTimerButton;

    public void resetTimer() {

        timerTextView.setText("1:00");
        timerSeekBar.setProgress(60);
        timerSeekBar.setEnabled(true);
        countDownTimer.cancel();

        customTimerButton.setText("Start Custom Timer");
        frenchPressTimerButton.setText("French Press");
        blackTeaTimerButton.setText("Black Tea");
        greenTeaTimerButton.setText("Green Tea");
        herbalTeaTimerButton.setText("Herbal Tea");

        timerActive = false;

    }

    public void timerStart(final Button buttonName, int time) {

        if (timerActive) {
            resetTimer();
        } else {

            timerActive = true;
            timerSeekBar.setEnabled(false);
            buttonName.setText("STOP");

            countDownTimer = new CountDownTimer(time + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer alarm = MediaPlayer.create(getApplicationContext(), R.raw.alarm);
                    alarm.start();
                    resetTimer();
                }
            }.start();
        }

    }

    public void updateTimer(int secondsLeft) {

        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - (minutes * 60);

        String secondsString = Integer.toString(seconds);

        if (Integer.parseInt(secondsString) < 10) {
            secondsString = "0" + secondsString;
        }

        timerTextView.setText(Integer.toString(minutes) + ":" + secondsString);

    }

    public void customTimerStart(View view) {

        timerStart(customTimerButton, timerSeekBar.getProgress() * 1000);

    }

    public void frenchPressTimerStart(View view) {

        timerStart(frenchPressTimerButton, 300000);

    }

    public void blackTeaTimerStart(View view) {

        timerStart(blackTeaTimerButton, 240000);

    }

    public void greenTeaTimerStart(View view) {

        timerStart(greenTeaTimerButton, 180000);

    }

    public void herbalTeaTimerStart(View view) {

        timerStart(herbalTeaTimerButton, 120000);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = findViewById(R.id.seekBar);
        timerTextView = findViewById(R.id.textView);

        customTimerButton = findViewById(R.id.customTimerButton);
        frenchPressTimerButton = findViewById(R.id.frenchPressButton);
        blackTeaTimerButton = findViewById(R.id.blackTeaButton);
        greenTeaTimerButton = findViewById(R.id.greenTeaButton);
        herbalTeaTimerButton = findViewById(R.id.herbalTeaButton);

        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(60);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });

    }
}
