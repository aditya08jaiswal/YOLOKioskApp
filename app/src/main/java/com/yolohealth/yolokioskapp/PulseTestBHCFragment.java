package com.yolohealth.yolokioskapp;

import android.media.MediaPlayer;
import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import pl.droidsonroids.gif.GifImageView;

public class PulseTestBHCFragment extends Fragment {

    View view;
    GifImageView instructionAudioGif;
    MediaPlayer mediaPlayer;
    ImageView playAudioImage;

    public PulseTestBHCFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(getContext(), R.raw.insert_your_finger_in_oximeter_and_press_start_button);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_pulse_test_bhc, container, false);
        instructionAudioGif = view.findViewById(R.id.instruction_playing_image_gif);
        playAudioImage = view.findViewById(R.id.pulse_test_play_iv);

        try{
            mediaPlayer.start();

        }catch(Exception e){e.printStackTrace();}

        instructionAudioGif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                playAudioImage.setVisibility(View.GONE);
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mediaPlayer.release();
        mediaPlayer = null;
    }
}
