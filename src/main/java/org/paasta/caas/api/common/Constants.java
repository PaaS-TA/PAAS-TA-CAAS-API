package org.paasta.caas.api.common;

import org.springframework.http.MediaType;

/**
 * Constants 클래스
 *
 * @author CISS
 * @version 1.0
 * @since 2018.08.01 최초작성
 */
public class Constants {

    public static final String RESULT_STATUS_SUCCESS = "SUCCESS";
    public static final String RESULT_STATUS_FAIL = "FAIL";

    public static final String TARGET_CAAS_MASTER_API = "caasMasterApi";
    public static final String ACCEPT_TYPE_YAML = String.valueOf(MediaType.valueOf("application/yaml"));

    public static final String TOKEN_KEY = "caas_admin";

    public static final String TARGET_COMMON_API = "commonApi";

    public static final String URI_COMMON_API_ADMIN_TOKEN_DETAIL = "/adminToken/{tokenName:.+}";

    static final String STRING_DATE_TYPE = "yyyy-MM-dd HH:mm:ss";
    static final String STRING_ORIGINAL_DATE_TYPE = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    static final String STRING_TIME_ZONE_ID = "Asia/Seoul";

    static final String ACCEPT_TYPE_JSON = MediaType.APPLICATION_JSON_VALUE;


    public Constants() {
        throw new IllegalStateException();
    }

}
