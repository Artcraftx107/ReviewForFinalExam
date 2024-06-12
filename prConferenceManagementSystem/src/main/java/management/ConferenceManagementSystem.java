package management;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;

public class ConferenceManagementSystem {
    private Map<String, Conference> conferences;
    private Map<String, Session> sessions;
    private Map<String, Attendee> attendees;
    private Map<String, Speaker> speakers;

    public ConferenceManagementSystem(){
        this.attendees=new TreeMap<>();
        this.conferences=new TreeMap<>();
        this.sessions=new TreeMap<>();
        this.speakers=new TreeMap<>();
    }

    //Conferences management code
    public void addConference(Conference conference){
        if(conference==null){
            throw new IllegalArgumentException("Conference specified cannot be null");
        }
        if(conferences.containsKey(conference.getConferenceId())){
            updateConference(conference);
        }
        conferences.put(conference.getConferenceId(), conference);
    }

    public void updateConference(Conference conference){
        if(!conferences.containsKey(conference.getConferenceId())){
            throw new NoSuchElementException("There is no conference with the ID "+conference.getConferenceId());
        }
        conferences.replace(conference.getConferenceId(), conference);
    }

    public void deleteConference(String conferenceID){
        if(!conferences.containsKey(conferenceID)){
            throw new NoSuchElementException("There is no added conference with the ID: "+conferenceID);
        }
        conferences.remove(conferenceID);
    }

    public Conference searchConference(String conferenceID){
        if(!conferences.containsKey(conferenceID)){
            throw new NoSuchElementException("There is no added conference with the ID: "+conferenceID);
        }
        return conferences.get(conferenceID);
    }

    //Sessions management
    public void addSession(Session session){
        if(session==null){
            throw new IllegalArgumentException("The given session cannot be null");
        }
        if(sessions.containsKey(session.getSessionId())){
            updateSession(session);
        }
        sessions.put(session.getSessionId(), session);
    }

    private void updateSession(Session session) {
        if(sessions.containsKey(session.getSessionId())){
            throw new NoSuchElementException("There is no session with the ID "+session.getSessionId());
        }
        sessions.replace(session.getSessionId(), session);
    }

    public void deleteSession(String sessionID){
        if(sessions.containsKey(sessionID)){
            throw new NoSuchElementException("There is no session with the ID "+sessionID);
        }
        sessions.remove(sessionID);
    }

    
}
