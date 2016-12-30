package ata.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Convert String to Timestamp
 */
public class TimestampConverter  implements Converter<String, Timestamp> {

    private static Logger logger = LoggerFactory.getLogger(TimestampConverter.class);

    @Override
    public Timestamp convert(String source) {
        if (source==null || source.isEmpty())
          return null;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        try {
            return new Timestamp(dateFormat.parse(source).getTime());
        } catch (ParseException e) {
          logger.error("convert error", e);
        }
        return null;
    }

}
