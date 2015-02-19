package net.emrekoc.eventbussample.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import net.emrekoc.eventbussample.R;
import net.emrekoc.eventbussample.event.FragmentEvent;
import net.emrekoc.eventbussample.event.SampleEvent;

import de.greenrobot.event.EventBus;

/**
 * Created by emre on 18.02.2015.
 */
public class MouseFragment extends Fragment implements View.OnClickListener {
    private Button btnSendToAll;
    private TextView tvMouse;
    private Button btnSendToFragments;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mouse, container, false);
        btnSendToAll = (Button) view.findViewById(R.id.btn_fragment_mouse_send_all);
        btnSendToFragments = (Button) view.findViewById(R.id.btn_fragment_mouse_send_to_fragments);
        btnSendToAll.setOnClickListener(this);
        btnSendToFragments.setOnClickListener(this);
        tvMouse = (TextView) view.findViewById(R.id.tv_fragment_mouse);
        return view;
    }

    /**EventBus uses this method to pass specified event to classes which is registered*/
    public void onEvent(SampleEvent event) {
        Log.d(getClass().getSimpleName(), "Event message: " + event.getMessage());
        tvMouse.setText(event.getMessage());
    }

    /**EventBus uses this method to pass specified event to classes which is registered*/
    public void onEvent(FragmentEvent event) {
        Log.d(getClass().getSimpleName(), "Event message: " + event.getMessage());
        tvMouse.setText(event.getMessage());
    }


    public static MouseFragment getInstance() {
        return new MouseFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**Register to receive events*/
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        /**
         * You need to unregister EventBus while your fragment destroying.
         * Create a BaseFragment class and move this code BaseFragment's onDestroy method to avoid code repetition.
         * */
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_fragment_mouse_send_all:
                EventBus.getDefault().post(new SampleEvent("Event sent by MouseFragment to everywhere"));
                break;
            case R.id.btn_fragment_mouse_send_to_fragments:
                EventBus.getDefault().post(new FragmentEvent("Event sent by KeyboardFragment to fragments","I'm title"));
                break;

        }
    }
}
