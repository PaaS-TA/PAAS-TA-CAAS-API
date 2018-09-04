package org.paasta.caas.api.accessInfo;

import lombok.Data;
import org.paasta.caas.api.common.model.CommonMetaData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hrjin
 * @version 1.0
 * @since 2018-09-04
 */
@Data
public class AccessToken {
    private String userAccessToken;
}
