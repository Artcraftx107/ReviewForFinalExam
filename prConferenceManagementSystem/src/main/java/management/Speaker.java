package management;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Speaker {
    private String speakerId;
    private String name;
    private String bio;
    private List<String> sessionIds;

    public Speaker(String speakerId, String name, String bio){
        if(speakerId.isBlank()||name.isBlank()||bio.isBlank()){
            throw new IllegalArgumentException("Neither the speaker's id, name or bio can be blank");
        }
        this.speakerId=speakerId;
        this.name=name;
        this.bio=bio;
        this.sessionIds=new ArrayList<>();
    }

    //Getters and Setters

    public String getSpeakerId() {
        return speakerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<String> getSessionIds() {
        return sessionIds;
    }

    //SessionIDs list management
    public void addSession(String sessionId){
        if(!sessionId.isBlank()){
            sessionIds.add(sessionId);
        }
    }

    public void removeSession(String sessionId){
        if(!sessionIds.contains(sessionId)){
            throw new NoSuchElementException("There is no session assigned to the speaker "+name+" with the specified ID");
        }
        sessionIds.remove(sessionId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(speakerId).append(",").append(name).append(",").append(bio);
        return sb.toString();
    }
}