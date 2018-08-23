package org.paasta.caas.api.event;

import lombok.Data;
import org.paasta.caas.api.common.Constants;
import org.paasta.caas.api.common.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Event Model 클래스
 *
 * @author CISS
 * @version 1.0
 * @since 2018.08.13
 */
@Data
public class Event {

    private String resultCode;
    private CommonMetaData metadata;
    //private String action;
    private int count;
    //private String eventTime;
    private String firstTimestamp;
    private String lastTimestamp;
    private String message;
    //private String reason;
    private EventSource source;
    private String type;
    private CommonObjectReference involvedObject;

    public String getFirstTimestamp() {
        try {
            this.firstTimestamp = (firstTimestamp != null) ? new SimpleDateFormat(Constants.STRING_DATE_TYPE)
                    .format(new SimpleDateFormat(Constants.STRING_ORIGINAL_DATE_TYPE).parse(firstTimestamp)) : null;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return firstTimestamp;
    }

    public String getLastTimestamp() {
        try {
            this.lastTimestamp = (lastTimestamp != null) ? new SimpleDateFormat(Constants.STRING_DATE_TYPE)
                    .format(new SimpleDateFormat(Constants.STRING_ORIGINAL_DATE_TYPE).parse(lastTimestamp)) : null;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return lastTimestamp;
    }

    @Data
    public class EventSource {
        private String component;
        private String host;
    }
}
