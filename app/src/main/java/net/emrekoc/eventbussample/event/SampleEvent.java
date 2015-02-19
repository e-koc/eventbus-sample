package net.emrekoc.eventbussample.event;

/**
 * Created by emre on 18.02.2015.
 *
 * This is just a sample class to pass event data between fragments and activities.
 * You can create special classes for special events.
 */
public class SampleEvent {
    String message;

    public SampleEvent(String message){
        this.message=message;
    }

    public String getMessage() {
        return message;
    }


}
