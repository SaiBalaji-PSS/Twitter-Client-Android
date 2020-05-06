package com.enigmabits.twitterclient;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.models.User;
import com.twitter.sdk.android.core.services.StatusesService;
import com.twitter.sdk.android.tweetcomposer.ComposerActivity;
import com.twitter.sdk.android.tweetcomposer.TweetUploadService;
import com.twitter.sdk.android.tweetui.TweetUtils;
import com.twitter.sdk.android.tweetui.TweetView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {
Button btn;
ConfigurationBuilder cf;
LinearLayout tweetviewlayout;
EditText hashtagbox;
ImageView profilepic;
EditText tweetbox;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_home, container, false);


          tweetviewlayout = v.findViewById(R.id.tweetslayout);
         cf = new ConfigurationBuilder();
        cf.setOAuthConsumerKey("")
                .setOAuthConsumerSecret("")
                .setOAuthAccessToken("")
                .setOAuthAccessTokenSecret("");
        gettweets();



        return v;
    }

    @Override
    public void onClick(View v) {

     //  TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();

    //    Toast.makeText(getActivity(),"HI",Toast.LENGTH_LONG).show();










    }
    private void gettweets()
    {
        final List<Long> twwetids = new ArrayList<>();

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    TwitterFactory tf = new TwitterFactory(cf.build());
                    twitter4j.Twitter twitter = tf.getInstance();
                    try {
                        List<Status> status = twitter.getHomeTimeline();
                        for(Status st : status)
                        {   Long id = st.getId();
                           Log.d("TWW",id.toString());
                           twwetids.add(id);
                        }
                    } catch (TwitterException e) {
                        Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_LONG).show();

                    }

                    for(Long tweetId:twwetids)
                    {
                        fetchtweets(tweetId);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();




    }

    void fetchtweets(Long t)
    {
        if(TwitterCore.getInstance().getSessionManager().getActiveSession()!=null)
        {
            TweetUtils.loadTweet(t, new Callback<Tweet>() {
                @Override
                public void success(Result<Tweet> result) {

                    final TweetView tweetView = new TweetView(getActivity(), result.data,
                            R.style.tw__TweetLightWithActionsStyle);

                    tweetviewlayout.addView(tweetView);
                }

                @Override
                public void failure(com.twitter.sdk.android.core.TwitterException exception) {

                    Toast.makeText(getActivity(),exception.toString(),Toast.LENGTH_LONG).show();

                }


            });
        }

    }
}
