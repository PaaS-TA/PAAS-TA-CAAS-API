package org.paasta.caas.api.event;

import lombok.Data;
import org.paasta.caas.api.common.Constants;
import org.paasta.caas.api.common.model.CommonMetaData;
import org.paasta.caas.api.common.model.CommonSpec;
import org.paasta.caas.api.common.model.CommonStatus;
import org.paasta.caas.api.common.model.CommonSubset;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Endpoint Model 클래스
 *
 * @author REX
 * @version 1.0
 * @since 2018.08.13
 */
@Data
public class Event {

    private String resultCode;
    private CommonMetaData metadata;
    //private String action = null;

    private int count;
    //private DateTime eventTime = null;
    private String firstTimestamp;
    private String lastTimestamp;
    private String message;
    //private String reason;
    private EventSource source;
    private String type;

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
