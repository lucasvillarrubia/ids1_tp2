package ar.uba.fi.ingsoft1.todo_template.match;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
/*
can't parse JSON.  Raw result:

org.springframework.http.converter.HttpMessageNotReadableException
JSON parse error: Cannot deserialize value of type `java.time.LocalTime`
from String "13:00:00": Failed to deserialize java.time.LocalTime:
(java.time.format.DateTimeParseException) Text '13:00:00' could not be parsed:
Unable to obtain LocalTime from TemporalAccessor: {HourOfDay=13, MonthOfYear=0, NanoOfSecond=0}
,ISO of type java.time.format.Parsed
 */

import java.io.Serializable;
import java.time.LocalTime;
@Embeddable
public class TimeRange implements Serializable {

    @JsonFormat(pattern="HH:mm:ss")
    @Column(name = "startTime", nullable = false)
    private LocalTime start;

    @JsonFormat(pattern="HH:mm:ss")
    @Column(name = "endTime", nullable = false)
    private LocalTime end;


    /*
    can't parse JSON.  Raw result:

org.springframework.dao.DataIntegrityViolationException could not execute statement [ERROR: null value in column "end_time" of relation "partido" violates not-null constraint
  Detail: Failing row contains (753, 9007199254740991, 9007199254740991, \xaced00057372003f61722e7562612e66692e696e67736f6674312e746f646f..., null, null, null).] [insert into partido (cancha_id,organizer_id,participation_type,end_time,start_time,id) values (?,?,?,?,?,?)]; SQL [insert into partido (cancha_id,organizer_id,participation_type,end_time,start_time,id) values (?,?,?,?,?,?)]; constraint [end_time" of relation "partido]
     */


    public TimeRange() {}

    public TimeRange(LocalTime start, LocalTime end) {

        if (start.isAfter(end) || start.equals(end))  {
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
class LocalTimeToObjectSerializer extends JsonSerializer<LocalTime> {
    @Override
    public void serialize(LocalTime time, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("hour", time.getHour());
        gen.writeNumberField("minute", time.getMinute());
        gen.writeNumberField("second", time.getSecond());
        gen.writeNumberField("nano", time.getNano());
        gen.writeEndObject();
    }
}

class ObjectToLocalTimeDeserializer extends JsonDeserializer<LocalTime> {
    @Override
    public LocalTime deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);
        int hour = node.get("hour").asInt();
        int minute = node.get("minute").asInt();
        int second = node.get("second").asInt();
        int nano = node.get("nano").asInt();
        return LocalTime.of(hour, minute, second, nano);
    }
}


 */



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

