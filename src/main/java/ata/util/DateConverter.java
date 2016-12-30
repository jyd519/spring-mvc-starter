package ata.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jiyongdong on 15/5/24.
 */
public class DateConverter implements Converter<String, Date> {

    private static Logger logger = LoggerFactory.getLogger(DateConverter.class);

    @Override
    public Date convert(String source) {
        if (source==null || source.isEmpty())
          return null;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            return dateFormat.parse(source);
        } catch (ParseException e) {
            logger.error("convert error", e);
        }
        return null;
    }

}
