package ar.uba.fi.ingsoft1.todo_template.match.matchOrganizer;

import ar.uba.fi.ingsoft1.todo_template.common.exception.InvalidActionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestMatchOrganizer {

    private MatchOrganizer organizer;

    @BeforeEach
    void setUp() {
        organizer = new MatchOrganizer();
    }

    @Test
    void testInitialState() {
        assertTrue(organizer.getAvailablePlayers().isEmpty());
        assertTrue(organizer.getTeamAPlayers().isEmpty());
        assertTrue(organizer.getTeamBPlayers().isEmpty());
    }

    @Test
    void testAddAndRemovePlayer() {
        organizer.addPlayer(1L);
        assertTrue(organizer.getAvailablePlayers().contains(1L));

        organizer.removePlayer(1L);
        assertFalse(organizer.getAvailablePlayers().contains(1L));
    }

    @Test
    void testMovePlayerToTeamA() {
        organizer.addPlayer(1L);
        organizer.movePlayer(1L, (short) 0);

        assertTrue(organizer.getTeamAPlayers().contains(1L));
        assertFalse(organizer.getAvailablePlayers().contains(1L));
        assertFalse(organizer.getTeamBPlayers().contains(1L));
    }

    @Test
    void testMovePlayerToAvailable() {
        organizer.movePlayer(1L, (short) 0);
        organizer.movePlayer(1L, (short) 1);

        assertTrue(organizer.getAvailablePlayers().contains(1L));
        assertFalse(organizer.getTeamAPlayers().contains(1L));
        assertFalse(organizer.getTeamBPlayers().contains(1L));
    }

    @Test
    void testMovePlayerToTeamB() {
        organizer.addPlayer(2L);
        organizer.movePlayer(2L, (short) 2);

        assertTrue(organizer.getTeamBPlayers().contains(2L));
        assertFalse(organizer.getAvailablePlayers().contains(2L));
        assertFalse(organizer.getTeamAPlayers().contains(2L));
    }

    @Test
    void testFinishThrowsIfPlayersUnassigned() {
        organizer.addPlayer(1L);
        InvalidActionException ex = assertThrows(InvalidActionException.class, organizer::finish);
        assertEquals("You need to asign all players before starting", ex.getMessage());
    }

    @Test
    void testFinishThrowsIfUnequalTeams() {
        organizer.movePlayer(1L, (short) 0);
        organizer.movePlayer(2L, (short) 2);
        organizer.movePlayer(3L, (short) 0);

        InvalidActionException ex = assertThrows(InvalidActionException.class, organizer::finish);
        assertEquals("Both teams need to have the same number of players", ex.getMessage());
    }

    @Test
    void testFinishSuccess() {
        organizer.movePlayer(1L, (short) 0);
        organizer.movePlayer(2L, (short) 2);

        assertDoesNotThrow(organizer::finish);
    }

    @Test
    void testGenerateRandomTeamsEvenSplit() {
        organizer.addPlayer(1L);
        organizer.addPlayer(2L);
        organizer.addPlayer(3L);
        organizer.addPlayer(4L);

        organizer.generateRandomTeams();

        assertEquals(2, organizer.getTeamAPlayers().size());
        assertEquals(2, organizer.getTeamBPlayers().size());
        assertTrue(organizer.getAvailablePlayers().isEmpty());
        Set<Long> intersection = new HashSet<>(organizer.getTeamAPlayers());
        intersection.retainAll(organizer.getTeamBPlayers());
        assertTrue(intersection.isEmpty());
    }
}
