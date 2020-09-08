package msscbeerservice.web.mappers;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Component
public class DateMapper {

    public OffsetDateTime asOffsetDateTime(Timestamp ts) {

        OffsetDateTime offsetDateTime = null;
        if (ts != null) {
            offsetDateTime = OffsetDateTime.of(ts.toLocalDateTime().getYear(),
                    ts.toLocalDateTime().getMonthValue(),
                    ts.toLocalDateTime().getDayOfMonth(),
                    ts.toLocalDateTime().getHour(),
                    ts.toLocalDateTime().getMinute(),
                    ts.toLocalDateTime().getSecond(),
                    ts.toLocalDateTime().getNano(),
                    ZoneOffset.UTC);
        }

        return offsetDateTime;
    }

    public Timestamp asTimestamp(OffsetDateTime offsetDateTime) {

        Timestamp timestamp = null;
        if (offsetDateTime != null) {
            timestamp = Timestamp.valueOf(offsetDateTime.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
        }

        return timestamp;
    }
}
