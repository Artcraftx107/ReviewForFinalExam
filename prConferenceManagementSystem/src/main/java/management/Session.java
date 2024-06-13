package management;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Session {
    private String sessionId;
    private String title;
    private String description;
    private String speakerId;
    private List<String> attendeeIDs;
    private Time scheduledTime;

    public Session(String sessionId, String title, String description, String scheduledTime){
        if(sessionId.isBlank()||title.isBlank()||description.isBlank()){
            throw new IllegalArgumentException("Neither the session's id, name or description can be blank");
        }
        this.sessionId=sessionId;
        this.title=title;
        this.description=description;
        setScheduledTime(scheduledTime);
        this.attendeeIDs=new ArrayList<>();
    }

    //Getters and Settters


    public String getSessionId() {
        return sessionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpeakerId() {
        return speakerId;
    }

    public void setSpeakerId(String speakerId) {
        this.speakerId = speakerId;
    }

    public Time getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(String scheduledTime) {
        String[] timeParts = scheduledTime.split(":");
        if(timeParts.length<2){
            throw new IllegalArgumentException("The time entered is not valid");
        }
        try{
            int hour = Integer.parseInt(timeParts[0]);
            int minutes = Integer.parseInt(timeParts[1]);
            Time aux = new Time(hour, minutes, 0);
            this.scheduledTime = aux;
        }catch (NumberFormatException e){
            System.err.println("There was an error processing the given time: "+scheduledTime);
        }
    }

    public List<String> getAttendeeIDs() {
        return attendeeIDs;
    }

    //Attendee IDs list management

    public void addAttendee(String attendeeID){
        if(!attendeeID.isBlank()){
            attendeeIDs.add(attendeeID);
        }
    }

    public void removeAttendee(String attendeeID){
        if(!attendeeIDs.contains(attendeeID)){
            throw new NoSuchElementException("There is no attendee with the ID "+attendeeID+" for this session");
        }
        attendeeIDs.remove(attendeeID);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(sessionId).append(",").append(title).append(",").append(description).append(",").append(getScheduledTime());
        return sb.toString();
    }
}