package com.example.zach.taskswithpomodoro;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import com.example.zach.taskswithpomodoro.dummy.DummyContent;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A fragment representing a single Task detail screen.
 * This fragment is either contained in a {@link TaskListActivity}
 * in two-pane mode (on tablets) or a {@link TaskDetailActivity}
 * on handsets.
 */
public class TaskDetailFragment extends Fragment {
    TextView textViewTimer;
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */

    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private TaskContent.Task mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TaskDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = TaskContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.title);
            }
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_task_detail, container, false);
        textViewTimer = (TextView) rootView.findViewById(R.id.textViewTimer);

        Button buttonStartTimer = (Button) rootView.findViewById(R.id.buttonStartTimer);
        buttonStartTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });
        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.task_detail)).setText(mItem.details);
        }


        return rootView;
    }

    public void startTimer(){
        final long startMillis = System.currentTimeMillis();
        final long totalLength = 1500;

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                final long elapseMillis = System.currentTimeMillis() - startMillis;
                final long elapseSeconds = elapseMillis/1000;
                final long timeLeft = totalLength-elapseSeconds;
                String result;
                if(timeLeft>=0){
                    result = String.valueOf(timeLeft);
                }
                else{
                    result = "done";
                }
                final String displayResult = result;
                textViewTimer.post(new Runnable() {
                    @Override
                    public void run() {
                        textViewTimer.setText(displayResult);
                    }
                });

            }
        };
        Timer timer = new Timer(true);
        timer.schedule(task,0,1000);
    }



}
