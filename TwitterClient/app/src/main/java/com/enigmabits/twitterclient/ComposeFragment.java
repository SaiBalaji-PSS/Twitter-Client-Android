package com.enigmabits.twitterclient;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.tweetcomposer.ComposerActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class ComposeFragment extends Fragment {
    Button sendbtn;
    EditText tweettext;
    EditText hasgtagtext;


    public ComposeFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v4 = inflater.inflate(R.layout.fragment_compose, container, false);

        sendbtn = v4.findViewById(R.id.sendbtn);
        tweettext = v4.findViewById(R.id.tweetbox);
        hasgtagtext = v4.findViewById(R.id.hastagbox);

        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();

               if(session!=null)
               {
                    Intent intent = new ComposerActivity.Builder(getActivity())
                           .session(session)

                           .text(tweettext.getText().toString())
                           .hashtags(hasgtagtext.getText().toString())
                           .createIntent();
                   startActivity(intent);
               }
            }
        });


        return v4;
    }
}
