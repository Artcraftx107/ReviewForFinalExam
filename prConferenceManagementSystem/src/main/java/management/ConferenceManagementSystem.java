package management;

import java.io.*;
import java.util.InvalidPropertiesFormatException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;

public class ConferenceManagementSystem {
    private Map<String, Conference> conferences;
    private Map<String, Session> sessions;
    private Map<String, Attendee> attendees;
    private Map<String, Speaker> speakers;
    private static final String LINE_ERROR = "The data in the current line has an incorrect format or is missing values";

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
            throw new NoSuchElementException("There is no conference with the ID: "+conference.getConferenceId());
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

    //Sessions management code
    public void addSession(Session session){
        if(session==null){
            throw new IllegalArgumentException("The given session cannot be null");
        }
        if(sessions.containsKey(session.getSessionId())){
            updateSession(session);
        }
        sessions.put(session.getSessionId(), session);
    }

    public void updateSession(Session session) {
        if(!sessions.containsKey(session.getSessionId())){
            throw new NoSuchElementException("There is no session with the ID "+session.getSessionId());
        }
        sessions.replace(session.getSessionId(), session);
    }

    public void deleteSession(String sessionID){
        if(!sessions.containsKey(sessionID)){
            throw new NoSuchElementException("There is no session with the ID "+sessionID);
        }
        sessions.remove(sessionID);
    }

    public Session searchSession(String sessionID){
        if(!sessions.containsKey(sessionID)){
            throw new NoSuchElementException("There is no session with the ID "+sessionID);
        }
        return sessions.get(sessionID);
    }

    //Speaker management code
    public void addSpeaker(Speaker speaker){
        if(speaker==null){
            throw new IllegalArgumentException("The given speaker cannot be null");
        }
        if(speakers.containsKey(speaker.getSpeakerId())){
            updateSpeaker(speaker);
        }
        speakers.put(speaker.getSpeakerId(), speaker);
    }

    public void updateSpeaker(Speaker speaker) {
        if(!speakers.containsKey(speaker.getSpeakerId())){
            throw new NoSuchElementException("There is no speaker with the ID "+speaker.getSpeakerId());
        }
        speakers.replace(speaker.getSpeakerId(), speaker);
    }

    public void deleteSpeaker(String speakerID){
        if(!speakers.containsKey(speakerID)){
            throw new NoSuchElementException("There is no speaker with the ID "+speakerID);
        }
        speakers.remove(speakerID);
    }

    public Speaker searchSpeaker(String speakerID){
        if(!speakers.containsKey(speakerID)){
            throw new NoSuchElementException("There is no speaker with the ID "+speakerID);
        }
        return speakers.get(speakerID);
    }

    //Attendee management code
    public void addAttendee(Attendee attendee){
        if(attendee==null){
            throw new IllegalArgumentException("The given attendee cannot be null");
        }
        if(attendees.containsKey(attendee.getAttendeeId())){
            updateAttendee(attendee);
        }
        attendees.put(attendee.getAttendeeId(), attendee);
    }

    public void updateAttendee(Attendee attendee) {
        if(!attendees.containsKey(attendee.getAttendeeId())){
            throw new NoSuchElementException("There is no attendee with the ID "+attendee.getAttendeeId());
        }
        attendees.replace(attendee.getAttendeeId(), attendee);
    }

    public void deleteAttendee(String attendeeID){
        if(!attendees.containsKey(attendeeID)){
            throw new NoSuchElementException("There is no attendee with the ID "+attendeeID);
        }
        attendees.remove(attendeeID);
    }

    public Attendee searchAttendee(String attendeeID){
        if(!attendees.containsKey(attendeeID)){
            throw new NoSuchElementException("There is no attendee with the ID "+attendeeID);
        }
        return attendees.get(attendeeID);
    }

    //Management methods
    public void scheduleSession(String sessionID, String conferenceID){
        Session session = searchSession(sessionID);
        Conference conference = searchConference(conferenceID);
        conference.addSession(sessionID);
    }

    public void assignSpeakerToSession(String speakerID, String sessionID){
        Session session = searchSession(sessionID);
        Speaker speaker = searchSpeaker(speakerID);
        session.setSpeakerId(speakerID);
        speaker.addSession(sessionID);
    }

    public void registerAttendeeForSession(String attendeeID, String sessionID){
        Session session = searchSession(sessionID);
        Attendee attendee = searchAttendee(attendeeID);
        session.addAttendee(attendee.getAttendeeId());
        attendee.addSession(sessionID);
    }

    //File management code
    public void saveConferencesToFile(String filename) throws IOException {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename))){
            for(Conference conference : conferences.values()){
                bw.write(conference.toString());
                bw.newLine();
            }
        }
    }

    public void loadConferencesFromFile(String filename)throws IOException{
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line;
            try{
                while ((line= br.readLine())!=null){
                    String[] parts = line.split(",");
                    if(parts.length<3){
                        throw new InvalidPropertiesFormatException(LINE_ERROR);
                    }
                    Conference conference = new Conference(parts[0], parts[1], parts[2]);
                    if(conferences.containsKey(conference.getConferenceId())){
                        conferences.replace(conference.getConferenceId(), conference);
                    }else{
                        conferences.put(conference.getConferenceId(), conference);
                    }
                }
            }catch (InvalidPropertiesFormatException e){
                System.err.println(e.getMessage());
            }
        }catch (IOException e){
            throw new FileNotFoundException("The file "+filename+" was not found");
        }
        //Code so the file used to load from is deleted after loading
        File file = new File(filename);
        file.delete();
    }

    public void saveSessionsToFile(String filename) throws IOException{
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename))){
            for(Session session : sessions.values()){
                bw.write(session.toString());
                bw.newLine();
            }
        }
    }

    public void loadSessionsFromFile(String filename) throws IOException{
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line;
            try{
                while((line= br.readLine())!=null){
                    String[] parts = line.split(",");
                    if(parts.length<4){
                        throw new InvalidPropertiesFormatException(LINE_ERROR);
                    }
                    Session session = new Session(parts[0], parts[1], parts[2], parts[3]);
                    if(sessions.containsKey(session.getSessionId())){
                        sessions.replace(session.getSessionId(), session);
                    }else{
                        sessions.put(session.getSessionId(), session);
                    }
                }
            }catch (InvalidPropertiesFormatException e){
                System.err.println(e.getMessage());
            }
        }catch (IOException e){
            throw new FileNotFoundException("The file "+filename+" was not found");
        }
        //Code so the file used to load from is deleted after loading
        File file = new File(filename);
        file.delete();
    }

    public void saveSpeakersToFile(String filename)throws IOException{
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename))){
            for(Speaker speaker : speakers.values()){
                bw.write(speaker.toString());
                bw.newLine();
            }
        }
    }

    public void loadSpeakersFromFile(String filename)throws IOException{
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line;
            try{
                while((line= br.readLine())!=null){
                    String[] parts = line.split(",");
                    if(parts.length<3){
                        throw new InvalidPropertiesFormatException(LINE_ERROR);
                    }
                    Speaker speaker = new Speaker(parts[0], parts[1], parts[2]);
                    if(speakers.containsKey(speaker.getSpeakerId())){
                        speakers.replace(speaker.getSpeakerId(), speaker);
                    }else{
                        speakers.put(speaker.getSpeakerId(), speaker);
                    }
                }
            }catch (InvalidPropertiesFormatException e){
                System.err.println(e.getMessage());
            }
        }catch (IOException e){
            throw new FileNotFoundException("The file "+filename+" was not found");
        }
        //Code so the file used to load from is deleted after loading
        File file = new File(filename);
        file.delete();
    }

    public void saveAttendeesToFile(String filename)throws IOException{
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))){
            for(Attendee attendee : attendees.values()){
                bufferedWriter.write(attendee.toString());
                bufferedWriter.newLine();
            }
        }
    }

    public void loadAttendeesFromFile(String filename)throws IOException{
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))){
            String line;
            try{
                while((line= bufferedReader.readLine())!=null){
                    String[] parts = line.split(",");
                    if(parts.length<3){
                        throw new InvalidPropertiesFormatException(LINE_ERROR);
                    }
                    Attendee attendee = new Attendee(parts[0], parts[1], parts[2]);
                    if(attendees.containsKey(attendee.getAttendeeId())){
                        attendees.replace(attendee.getAttendeeId(), attendee);
                    }else{
                        attendees.put(attendee.getAttendeeId(), attendee);
                    }
                }
            }catch (InvalidPropertiesFormatException e){
                System.err.println(e.getMessage());
            }
        }catch (IOException e){
            throw new FileNotFoundException("The file "+filename+" was not found");
        }
        //Code so the file used to load from is deleted after loading
        File file = new File(filename);
        file.delete();
    }
}