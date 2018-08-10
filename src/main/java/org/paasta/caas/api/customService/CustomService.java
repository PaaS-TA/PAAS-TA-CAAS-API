package org.paasta.caas.api.customService;

import lombok.Data;
import org.paasta.caas.api.common.model.CommonMetaData;
import org.paasta.caas.api.common.model.CommonSpec;
import org.paasta.caas.api.common.model.CommonStatus;

/**
 * Custom Service Model 클래스
 *
 * @author REX
 * @version 1.0
 * @since 2018.08.09
 */
@Data
public class CustomService {

    private String resultCode;
    private String resultMessage;

    private CommonMetaData metadata;
    private CommonSpec spec;
    private CommonStatus status;
}
