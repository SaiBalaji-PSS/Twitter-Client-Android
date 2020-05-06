package com.enigmabits.twitterclient;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.User;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

import java.util.List;

import retrofit2.Call;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserTimeLineFragment extends Fragment {
   ListView mytweets;
   TextView username;
   ImageView bannerpic;
   UserTimeline userTimeline;
   ImageView profilepic;
   TweetTimelineListAdapter adapter;
    public UserTimeLineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View v2 = inflater.inflate(R.layout.fragment_user_time_line, container, false);
        final TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();

      mytweets = v2.findViewById(R.id.mytweetlist);
      userTimeline = new UserTimeline.Builder()
              .screenName(session.getUserName())
              .includeReplies(true)
              .includeRetweets(true)


              .build();
         adapter = new TweetTimelineListAdapter.Builder(getActivity())
                .setTimeline(userTimeline)

                .build();
         mytweets.setAdapter(adapter);

         profilepic = v2.findViewById(R.id.profileimage);
         bannerpic = v2.findViewById(R.id.imageView);
         username = v2.findViewById(R.id.usernamelbl);
         getdata();


      return v2;
    }


    void getdata()
    {
        final TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
          username.setText("@"+session.getUserName());
        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
        Call<User> call = twitterApiClient.getAccountService().verifyCredentials(true, false, true);
        call.enqueue(new Callback<User>() {
            @Override
            public void success(Result<User> result) {

                String u = result.data.profileBannerUrl;
                String p = result.data.profileImageUrlHttps;

                p =  p.replace("_normal", "");

                Picasso.with(getActivity()).load(u).into(bannerpic);
                Picasso.with(getActivity()).load(p).into(profilepic);

            }

            @Override
            public void failure(TwitterException exception) {
                Toast.makeText(getActivity(), exception.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
