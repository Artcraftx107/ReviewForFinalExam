import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Path;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import management.*;

public class ConferenceManagementSystemTest {

    private ConferenceManagementSystem cms;

    @TempDir
    Path tempDir;

    @BeforeEach
    public void setUp() {
        cms = new ConferenceManagementSystem();
    }

    @Test
    public void testAddAndSearchConference() {
        Conference conference = new Conference("conf1", "Conference 1", "Description 1");
        cms.addConference(conference);

        Conference retrievedConference = cms.searchConference("conf1");
        assertNotNull(retrievedConference);
        assertEquals("Conference 1", retrievedConference.getName());
        assertEquals("Description 1", retrievedConference.getDescription());
    }

    @Test
    public void testUpdateConference() {
        Conference conference = new Conference("conf1", "Conference 1", "Description 1");
        cms.addConference(conference);

        conference.setName("Updated Conference 1");
        conference.setDescription("Updated Description 1");
        cms.updateConference(conference);

        Conference updatedConference = cms.searchConference("conf1");
        assertEquals("Updated Conference 1", updatedConference.getName());
        assertEquals("Updated Description 1", updatedConference.getDescription());
    }

    @Test
    public void testDeleteConference() {
        Conference conference = new Conference("conf1", "Conference 1", "Description 1");
        cms.addConference(conference);
        cms.deleteConference("conf1");

        assertThrows(NoSuchElementException.class, ()->cms.searchConference("conf1"));
    }

    @Test
    public void testAddAndSearchSession() {
        Session session = new Session("sess1", "Session 1", "Description 1", "10:00");
        cms.addSession(session);

        Session retrievedSession = cms.searchSession("sess1");
        assertNotNull(retrievedSession);
        assertEquals("Session 1", retrievedSession.getTitle());
        assertEquals("Description 1", retrievedSession.getDescription());
    }

    @Test
    public void testUpdateSession() {
        Session session = new Session("sess1", "Session 1", "Description 1", "10:00");
        cms.addSession(session);

        session.setTitle("Updated Session 1");
        session.setDescription("Updated Description 1");
        session.setScheduledTime("11:00");
        cms.updateSession(session);

        Session updatedSession = cms.searchSession("sess1");
        assertEquals("Updated Session 1", updatedSession.getTitle());
        assertEquals("Updated Description 1", updatedSession.getDescription());
        assertEquals("11:00:00", updatedSession.getScheduledTime().toString());
    }

    @Test
    public void testDeleteSession() {
        Session session = new Session("sess1", "Session 1", "Description 1", "10:00");
        cms.addSession(session);
        cms.deleteSession("sess1");

        assertThrows(NoSuchElementException.class, ()->cms.searchSession("sess1"));
    }

    @Test
    public void testAddAndSearchSpeaker() {
        Speaker speaker = new Speaker("spk1", "Speaker 1", "Bio 1");
        cms.addSpeaker(speaker);

        Speaker retrievedSpeaker = cms.searchSpeaker("spk1");
        assertNotNull(retrievedSpeaker);
        assertEquals("Speaker 1", retrievedSpeaker.getName());
        assertEquals("Bio 1", retrievedSpeaker.getBio());
    }

    @Test
    public void testUpdateSpeaker() {
        Speaker speaker = new Speaker("spk1", "Speaker 1", "Bio 1");
        cms.addSpeaker(speaker);

        speaker.setName("Updated Speaker 1");
        speaker.setBio("Updated Bio 1");
        cms.updateSpeaker(speaker);

        Speaker updatedSpeaker = cms.searchSpeaker("spk1");
        assertEquals("Updated Speaker 1", updatedSpeaker.getName());
        assertEquals("Updated Bio 1", updatedSpeaker.getBio());
    }

    @Test
    public void testDeleteSpeaker() {
        Speaker speaker = new Speaker("spk1", "Speaker 1", "Bio 1");
        cms.addSpeaker(speaker);
        cms.deleteSpeaker("spk1");

        assertThrows(NoSuchElementException.class,()->cms.searchSpeaker("spk1"));
    }

    @Test
    public void testAddAndSearchAttendee() {
        Attendee attendee = new Attendee("att1", "Attendee 1", "att1@example.com");
        cms.addAttendee(attendee);

        Attendee retrievedAttendee = cms.searchAttendee("att1");
        assertNotNull(retrievedAttendee);
        assertEquals("Attendee 1", retrievedAttendee.getName());
        assertEquals("att1@example.com", retrievedAttendee.getEmail());
    }

    @Test
    public void testUpdateAttendee() {
        Attendee attendee = new Attendee("att1", "Attendee 1", "att1@example.com");
        cms.addAttendee(attendee);

        attendee.setName("Updated Attendee 1");
        attendee.setEmail("updated_att1@example.com");
        cms.updateAttendee(attendee);

        Attendee updatedAttendee = cms.searchAttendee("att1");
        assertEquals("Updated Attendee 1", updatedAttendee.getName());
        assertEquals("updated_att1@example.com", updatedAttendee.getEmail());
    }

    @Test
    public void testDeleteAttendee() {
        Attendee attendee = new Attendee("att1", "Attendee 1", "att1@example.com");
        cms.addAttendee(attendee);
        cms.deleteAttendee("att1");

        assertThrows(NoSuchElementException.class,()->cms.searchAttendee("att1"));
    }

    @Test
    public void testScheduleSession() {
        Conference conference = new Conference("conf1", "Conference 1", "Description 1");
        Session session = new Session("sess1", "Session 1", "Description 1", "10:00");
        cms.addConference(conference);
        cms.addSession(session);

        cms.scheduleSession("sess1", "conf1");

        assertTrue(cms.searchConference("conf1").getSessions().contains("sess1"));
    }

    @Test
    public void testAssignSpeakerToSession() {
        Speaker speaker = new Speaker("spk1", "Speaker 1", "Bio 1");
        Session session = new Session("sess1", "Session 1", "Description 1", "10:00");
        cms.addSpeaker(speaker);
        cms.addSession(session);

        cms.assignSpeakerToSession("spk1", "sess1");

        assertEquals("spk1", cms.searchSession("sess1").getSpeakerId());
    }

    @Test
    public void testRegisterAttendeeForSession() {
        Attendee attendee = new Attendee("att1", "Attendee 1", "att1@example.com");
        Session session = new Session("sess1", "Session 1", "Description 1", "10:00");
        cms.addAttendee(attendee);
        cms.addSession(session);

        cms.registerAttendeeForSession("att1", "sess1");

        assertTrue(cms.searchSession("sess1").getAttendeeIDs().contains("att1"));
    }

    @Test
    public void testSaveAndLoadConferences() throws IOException {
        Conference conference = new Conference("conf1", "Conference 1", "Description 1");
        cms.addConference(conference);

        Path filePath = tempDir.resolve("conferences.txt");
        cms.saveConferencesToFile(filePath.toString());

        cms = new ConferenceManagementSystem();
        cms.loadConferencesFromFile(filePath.toString());

        Conference loadedConference = cms.searchConference("conf1");
        assertNotNull(loadedConference);
        assertEquals("Conference 1", loadedConference.getName());
        assertEquals("Description 1", loadedConference.getDescription());
    }

    @Test
    public void testSaveAndLoadSessions() throws IOException {
        Session session = new Session("sess1", "Session 1", "Description 1", "10:00");
        cms.addSession(session);

        Path filePath = tempDir.resolve("sessions.txt");
        cms.saveSessionsToFile(filePath.toString());

        cms = new ConferenceManagementSystem();
        cms.loadSessionsFromFile(filePath.toString());

        Session loadedSession = cms.searchSession("sess1");
        assertNotNull(loadedSession);
        assertEquals("Session 1", loadedSession.getTitle());
        assertEquals("Description 1", loadedSession.getDescription());
    }

    @Test
    public void testSaveAndLoadSpeakers() throws IOException {
        Speaker speaker = new Speaker("spk1", "Speaker 1", "Bio 1");
        cms.addSpeaker(speaker);

        Path filePath = tempDir.resolve("speakers.txt");
        cms.saveSpeakersToFile(filePath.toString());

        cms = new ConferenceManagementSystem();
        cms.loadSpeakersFromFile(filePath.toString());

        Speaker loadedSpeaker = cms.searchSpeaker("spk1");
        assertNotNull(loadedSpeaker);
        assertEquals("Speaker 1", loadedSpeaker.getName());
        assertEquals("Bio 1", loadedSpeaker.getBio());
    }

    @Test
    public void testSaveAndLoadAttendees() throws IOException {
        Attendee attendee = new Attendee("att1", "Attendee 1", "att1@example.com");
        cms.addAttendee(attendee);

        Path filePath = tempDir.resolve("attendees.txt");
        cms.saveAttendeesToFile(filePath.toString());

        cms = new ConferenceManagementSystem();
        cms.loadAttendeesFromFile(filePath.toString());

        Attendee loadedAttendee = cms.searchAttendee("att1");
        assertNotNull(loadedAttendee);
        assertEquals("Attendee 1", loadedAttendee.getName());
        assertEquals("att1@example.com", loadedAttendee.getEmail());
    }
}