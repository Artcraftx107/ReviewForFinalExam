package management;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Attendee {
    private String attendeeId;
    private String name;
    private String email;
    private List<String> sessionIDs;

    public Attendee(String attendeeId, String name, String email){
        if(attendeeId.isBlank()||name.isBlank()||!email.contains("@")){
            throw new IllegalArgumentException("Neither the attendee's id or name can be blank, and the email address must include an @");
        }
        this.attendeeId=attendeeId;
        this.name=name;
        this.email=email;
        this.sessionIDs=new ArrayList<>();
    }

    public String getAttendeeId() {
        return attendeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getSessionIDs() {
        return sessionIDs;
    }

    //SessionIDs list management
    public void addSession(String sessionId){
        if(!sessionId.isBlank()){
            sessionIDs.add(sessionId);
        }
    }

    public void removeSession(String sessionId){
        if(!sessionIDs.contains(sessionId)){
            throw new NoSuchElementException("There is no session with the specified ID included in the Attendee's sessions");
        }
        sessionIDs.remove(sessionId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(attendeeId).append(",").append(name).append(",").append(email);
        return sb.toString();
    }
}
