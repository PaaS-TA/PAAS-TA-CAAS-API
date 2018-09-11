package org.paasta.caas.api.accessInfo;

import org.paasta.caas.api.common.Constants;
import org.paasta.caas.api.common.RestTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Base64;
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
        String caCertToken = null;
        String userToken = null;
        String requestUrl = "/api/v1/namespaces/" + namespace + "/secrets/" + accessTokenName;
        HashMap<String, Object> responseMap = (HashMap<String, Object>) restTemplateService.send(Constants.TARGET_CAAS_MASTER_API, requestUrl, HttpMethod.GET, null, Map.class);
        Map map = (Map) responseMap.get("data");

        caCertToken = map.get("ca.crt").toString();
        userToken = map.get("token").toString();

        Base64.Decoder decoder = Base64.getDecoder();
        String caCertDecodeToken = new String(decoder.decode(caCertToken));
        String userDecodeToken = new String(decoder.decode(userToken));


        LOGGER.info("get access caCertToken :: {}", caCertDecodeToken);
        LOGGER.info("get access userToken :: {}", userDecodeToken);

        AccessToken accessToken = new AccessToken();
        accessToken.setCaCertToken(caCertDecodeToken);
        accessToken.setUserAccessToken(userDecodeToken);



        return accessToken;
    }
}
