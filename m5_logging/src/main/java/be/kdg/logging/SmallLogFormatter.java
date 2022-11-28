package be.kdg.logging;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class SmallLogFormatter extends java.util.logging.Formatter {
    @Override
    public String format(java.util.logging.LogRecord record) {
        // date time Level: LEVEL melding: “boodschap
        LocalDateTime ldt = LocalDateTime.ofInstant(record.getInstant(), ZoneId.systemDefault());
        return String.format("%s Level: %s melding: %s\n",
                ldt,
                record.getLevel(),
                MessageFormat.format(record.getMessage(), record.getParameters()));
    }
}
