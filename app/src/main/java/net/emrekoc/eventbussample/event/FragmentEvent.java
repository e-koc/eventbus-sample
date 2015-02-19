package net.emrekoc.eventbussample.event;

/**
 * Created by emre on 18.02.2015.
 *
 * This is just a sample class to pass event data between fragments and activities.
 *  You can create special classes for special events.
 */
public class FragmentEvent {
    String message;
    String title;

    public FragmentEvent(String message, String title) {
        this.message = message;
        this.title = title;
    }

    public String getMessage() {
        return message;
    }
}
