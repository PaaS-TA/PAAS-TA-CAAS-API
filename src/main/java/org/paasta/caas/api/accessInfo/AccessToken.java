package org.paasta.caas.api.accessInfo;

import lombok.Data;

/**
 * AccessToken Model 클래스
 *
 * @author hrjin
 * @version 1.0
 * @since 2018-09-04
 */
@Data
public class AccessToken {
    private String resultCode;
    private String caCertToken;
    private String userAccessToken;
}
