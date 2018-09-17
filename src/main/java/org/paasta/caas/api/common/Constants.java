package org.paasta.caas.api.common;

import org.springframework.http.MediaType;

/**
 * Constants 클래스
 *
 * @author 최윤석
 * @version 1.0
 * @since 2018.08.01 최초작성
 */
public class Constants {

    /*RESULT STATUS*/
    public static final String RESULT_STATUS_SUCCESS = "SUCCESS";
    public static final String RESULT_STATUS_FAIL = "FAIL";

    /*TARGET API*/
    public static final String TARGET_CAAS_MASTER_API = "caasMasterApi";
    public static final String STRING_DATE_TYPE = "yyyy-MM-dd HH:mm:ss";
    public static final String STRING_ORIGINAL_DATE_TYPE = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String STRING_TIME_ZONE_ID = "Asia/Seoul";
    public static final String ACCEPT_TYPE_JSON = MediaType.APPLICATION_JSON_VALUE;
    public static final String ACCEPT_TYPE_YAML = String.valueOf(MediaType.valueOf("application/yaml"));
    static final String TARGET_COMMON_API = "commonApi";
    private static final String API_URL_V1 = "/api/v1";
    private static final String APIS_URL_V1 = "/apis/rbac.authorization.k8s.io/v1";
    public static final String API_URL_NAMESPACES = API_URL_V1 + "/namespaces";
    public static final String APIS_URL_NAMESPACES = APIS_URL_V1 + "/namespaces";


    public Constants() {
        throw new IllegalStateException();
    }
}
