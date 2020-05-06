package com.enigmabits.twitterclient;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.tweetui.SearchTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {
   EditText searchbox;
   ListView searchtweetlist;
    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v3 = inflater.inflate(R.layout.search_tweets, container, false);
       searchtweetlist = v3.findViewById(R.id.searchtweetlistview);
       searchbox = v3.findViewById(R.id.searchbar);

       searchbox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
           @Override
           public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

               if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_GO)) {
                   gettweets();

               }





               return false;


           }
       });


       return v3;
    }

    private void gettweets()
    {
        String s =searchbox.getText().toString();
        if(TwitterCore.getInstance().getSessionManager().getActiveSession()!=null)
        {

            SearchTimeline searchTimeline = new SearchTimeline.Builder()
                    .query("#"+s)
                    .maxItemsPerRequest(50)
                    .build();

            final TweetTimelineListAdapter adapter =
                    new TweetTimelineListAdapter.Builder(getActivity())
                            .setTimeline(searchTimeline)
                            .setViewStyle(R.style.tw__TweetLightWithActionsStyle)
                            .build();
            searchtweetlist.setAdapter(adapter);
        }

    }
}

