package com.example.zach.taskswithpomodoro;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.NotificationCompat;
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
    NotificationManager notificationManager;
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */

    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private TaskContent.Task mItem;

    private String currentItemId;

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

            currentItemId = getArguments().getString(ARG_ITEM_ID);
            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.title);
            }
        }
        notificationManager = (NotificationManager) getActivity().getSystemService(getContext().NOTIFICATION_SERVICE);
        notificationManager.cancel(0);


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
        Button buttonUpdateTimer = (Button) rootView.findViewById(R.id.buttonUpdateTimer);
        buttonUpdateTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotification();
            }
        });

        //nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

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

    private void showNotification(){

        //Notification notification = new Notification(R.drawable.clock, "potato", System.currentTimeMillis());
        Intent MyIntent = new Intent(getContext(), TaskDetailActivity.class);
        MyIntent.putExtra(ARG_ITEM_ID,currentItemId);
        PendingIntent StartIntent = PendingIntent.getActivity(getActivity(), (int) System.currentTimeMillis(), MyIntent,0);
        Notification mNotification = new Notification.Builder(getContext()).setContentText("potato").setContentText("nuggets").setSmallIcon(R.drawable.clock).setContentIntent(StartIntent).build();


        //We get a reference to the NotificationManager










        //A PendingIntent will be fired when the notification is clicked. The FLAG_CANCEL_CURRENT flag cancels the pendingintent

        //mNotification.setLatestEventInfo(getContext(), MyNotificationTitle, MyNotificationText, StartIntent);

        int NOTIFICATION_ID = 0;
        notificationManager.notify(NOTIFICATION_ID , mNotification);




    }


}
