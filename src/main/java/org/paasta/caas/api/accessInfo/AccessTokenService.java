package org.paasta.caas.api.accessInfo;

import org.paasta.caas.api.common.Constants;
import org.paasta.caas.api.common.PropertyService;
import org.paasta.caas.api.common.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * AccessToken Service 클래스
 *
 * @author hrjin
 * @version 1.0
 * @since 2018-09-04
 */
@Service
public class AccessTokenService {
    private final RestTemplateService restTemplateService;
    private final PropertyService propertyService;

    /**
     * Instantiates a new accessToken service
     *
     * @param restTemplateService the rest template service
     * @param propertyService     the property service
     */
    @Autowired
    public AccessTokenService(RestTemplateService restTemplateService, PropertyService propertyService) {
        this.restTemplateService = restTemplateService;
        this.propertyService = propertyService;
    }

    /**
     * Secret 에서 cluster certification token 과 user token 을 조회한다.
     *
     * @param namespace       the namespace
     * @param accessTokenName the accessTokenName
     * @return the AccessToken
     */
    AccessToken getSecret(String namespace, String accessTokenName) {
        String caCertToken;
        String userToken;

        HashMap responseMap = (HashMap) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API,
                propertyService.getCaasMasterApiListSecretsGetUrl()
                        .replace("{namespace}", namespace)
                        .replace("{name}", accessTokenName), HttpMethod.GET, null, Map.class);

        Map map = (Map) responseMap.get("data");

        caCertToken = map.get("ca.crt").toString();
        userToken = map.get("token").toString();

        Base64.Decoder decoder = Base64.getDecoder();
        String caCertDecodeToken = new String(decoder.decode(caCertToken));
        String userDecodeToken = new String(decoder.decode(userToken));

        AccessToken accessToken = new AccessToken();
        accessToken.setCaCertToken(caCertDecodeToken);
        accessToken.setUserAccessToken(userDecodeToken);

        return accessToken;
    }
}
