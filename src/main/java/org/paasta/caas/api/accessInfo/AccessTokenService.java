package org.paasta.caas.api.accessInfo;

import org.paasta.caas.api.common.Constants;
import org.paasta.caas.api.common.RestTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hrjin
 * @version 1.0
 * @since 2018-09-04
 */
@Service
public class AccessTokenService {
    private final RestTemplateService restTemplateService;
    private static final Logger LOGGER = LoggerFactory.getLogger(AccessTokenService.class);

    @Autowired
    public AccessTokenService(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    public AccessToken getSecret(String namespace, String accessTokenName){
        String token = null;
        HashMap<String, Object> responseMap = (HashMap<String, Object>) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, "/api/v1/namespaces/" + namespace + "/secrets/" + accessTokenName, HttpMethod.GET, null, Map.class);
        Map map = (Map) responseMap.get("data");
        token = map.get("token").toString();

        AccessToken accessToken = new AccessToken();
        accessToken.setUserAccessToken(token);

        return accessToken;
    }
}
