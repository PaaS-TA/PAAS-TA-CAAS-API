package org.paasta.caas.api.common;

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

    /*TARGET API*/
    public static final String TARGET_CAAS_MASTER_API = "caasMasterApi";
    public static final String TARGET_COMMON_API = "commonApi";

    public static final String STRING_DATE_TYPE = "yyyy-MM-dd HH:mm:ss";
    public static final String STRING_ORIGINAL_DATE_TYPE = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    /*FOR TEST*/
    public static final String NAMESPACE_NAME = "kube-system";
    public static final String SERVICE_NAME = "kube-dns";

    /*TODO :: REMOVE*/
    /*CAAS MASTER API*/
    public static final String API_URL_V1 = "/api/v1";
    public static final String API_URL_NAMESPACES_LIST = API_URL_V1 + "/namespaces";
    public static final String API_URL_REPLICASET_LIST =  "/apis/apps/v1/replicasets/";


    public Constants() {
        throw new IllegalStateException();
    }
}
