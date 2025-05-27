package ar.uba.fi.ingsoft1.todo_template.partido;

import jakarta.persistence.Entity;
import java.io.Serializable;
import java.time.LocalTime;

@Entity
public class TimeRange implements Serializable {

    private LocalTime start;
    private LocalTime end;

    public TimeRange(LocalTime start, LocalTime end) {
        if (start.isAfter(end) || start.equals(end)) {
            throw new IllegalArgumentException("The start time value must be lower than the end time value");
        }
        this.start = start;
        this.end = end;
    }

    public boolean overlap(TimeRange other) {
        return other.overlap(this.start, this.end);
    }

    public boolean overlap(LocalTime otherStart, LocalTime otherEnd) {
        return (otherStart.isAfter(this.start) && otherStart.isBefore(this.end)) ||
                (otherEnd.isAfter(this.start) && otherEnd.isBefore(this.end)) ||
                (otherStart.isBefore(this.start) && otherEnd.isAfter(this.end));
    }
}

/*
class TimeRangeTest {

    @Test
    void constructor_shouldThrowException_whenStartIsAfterEnd() {
        LocalTime start = LocalTime.of(15, 0);
        LocalTime end = LocalTime.of(14, 0);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TimeRange(start, end);
        });

        assertEquals("The start time value must be lower than the end time value", exception.getMessage());
    }

    @Test
    void constructor_shouldThrowException_whenStartEqualsEnd() {
        LocalTime time = LocalTime.of(10, 0);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TimeRange(time, time);
        });

        assertEquals("The start time value must be lower than the end time value", exception.getMessage());
    }

    @Test
    void overlap_shouldReturnTrue_whenOtherStartsInsideThisRange() {
        TimeRange range = new TimeRange(LocalTime.of(9, 0), LocalTime.of(12, 0));
        TimeRange other = new TimeRange(LocalTime.of(10, 0), LocalTime.of(13, 0));

        assertTrue(range.overlap(other));
    }

    @Test
    void overlap_shouldReturnTrue_whenOtherEndsInsideThisRange() {
        TimeRange range = new TimeRange(LocalTime.of(9, 0), LocalTime.of(12, 0));
        TimeRange other = new TimeRange(LocalTime.of(8, 0), LocalTime.of(10, 0));

        assertTrue(range.overlap(other));
    }

    @Test
    void overlap_shouldReturnTrue_whenOtherCompletelyCoversThisRange() {
        TimeRange range = new TimeRange(LocalTime.of(10, 0), LocalTime.of(11, 0));
        TimeRange other = new TimeRange(LocalTime.of(9, 0), LocalTime.of(12, 0));

        assertTrue(range.overlap(other));
    }

    @Test
    void overlap_shouldReturnFalse_whenRangesAreDisjoint_before() {
        TimeRange range = new TimeRange(LocalTime.of(10, 0), LocalTime.of(12, 0));
        TimeRange other = new TimeRange(LocalTime.of(8, 0), LocalTime.of(9, 0));

        assertFalse(range.overlap(other));
    }

    @Test
    void overlap_shouldReturnFalse_whenRangesAreDisjoint_after() {
        TimeRange range = new TimeRange(LocalTime.of(10, 0), LocalTime.of(12, 0));
        TimeRange other = new TimeRange(LocalTime.of(12, 0), LocalTime.of(13, 0)); // touching but not overlapping

        assertFalse(range.overlap(other));
    }

    @Test
    void overlap_shouldReturnTrue_whenOtherIsFullyContainedInThisRange() {
        TimeRange range = new TimeRange(LocalTime.of(8, 0), LocalTime.of(14, 0));
        TimeRange other = new TimeRange(LocalTime.of(9, 0), LocalTime.of(10, 0));

        assertTrue(range.overlap(other));
    }
}
 */

