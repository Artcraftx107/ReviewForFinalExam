package management;

import java.util.ArrayList;
import java.util.List;

public class Conference {
    private String conferenceId;
    private String name;
    private String description;
    private List<String> sessions;

    public Conference(String conferenceId, String name, String description){
        if(conferenceId.isBlank()||name.isBlank()||description.isBlank()){
            throw new IllegalArgumentException("Neither the conference's id, name or description can be blank");
        }
        this.name=name;
        this.conferenceId=conferenceId;
        this.description=description;
        this.sessions=new ArrayList<>();
    }

    //Getters and Setters

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(String conferenceId) {
        this.conferenceId = conferenceId;
    }

    public List<String> getSessions() {
        return sessions;
    }

    //Sessions list management
    public void addSession(String sessionId){
        sessions.add(sessionId);
    }

    public void removeSession(String sessionId){
        sessions.remove(sessionId);
    }
}
