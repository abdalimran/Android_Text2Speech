package com.github.abdalimran.androidtext2speech;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;

import java.util.Locale;

public class MainActivity extends Activity implements TextToSpeech.OnInitListener {

    private TextToSpeech engine;
    private EditText editText;
    private ImageButton speechButton;
    private SeekBar setPitch;
    private SeekBar setSpeed;
    private double pitch=1.0;
    private double speed=1.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        speechButton = (ImageButton) findViewById(R.id.speechButton);
        editText = (EditText) findViewById(R.id.editText);
        engine = new TextToSpeech(this, this);

        speechButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Speak();
            }
        });

        setPitch = (SeekBar) findViewById(R.id.setPitch);
        setPitch.setThumbOffset(5);
        setPitch.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pitch = (float) progress / (seekBar.getMax() / 2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        setSpeed = (SeekBar) findViewById(R.id.setSpeed);
        setSpeed.setThumbOffset(5);
        setSpeed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                speed = (float) progress / (seekBar.getMax() / 2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            engine.setLanguage(Locale.US);
        }
    }

    private void Speak() {
        engine.setPitch((float) pitch);
        engine.setSpeechRate((float) speed);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            engine.speak(editText.getText().toString(), TextToSpeech.QUEUE_FLUSH, null, null);
        }
        else {
            engine.speak(editText.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
        }
    }
}