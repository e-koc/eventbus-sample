package net.emrekoc.eventbussample;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.TextView;

import net.emrekoc.eventbussample.event.SampleEvent;
import net.emrekoc.eventbussample.fragments.KeyboardFragment;
import net.emrekoc.eventbussample.fragments.MouseFragment;

import de.greenrobot.event.EventBus;

public class MainActivity extends FragmentActivity {
    TextView tvEventMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**Register to receive events*/
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_main);
        tvEventMessage = (TextView) findViewById(R.id.tv_activity_main_event_message);
        loadFragments();
    }

    private void loadFragments() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame_keyboard, KeyboardFragment.getInstance())
                .add(R.id.frame_mouse, MouseFragment.getInstance())
                .commit();
    }

    @Override
    protected void onDestroy() {
        /**
         * You need to unregister EventBus while your activity destroying.
         * Create a BaseActivity class and move this code BaseActivity's onDestroy method to avoid code repetition.
         * */
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    /**EventBus uses this method to pass specified event to classes which is registered*/
    public void onEvent(SampleEvent event) {
        Log.d("MainActivity", "Event message: " + event.getMessage());
        tvEventMessage.setText(event.getMessage());
    }

}
