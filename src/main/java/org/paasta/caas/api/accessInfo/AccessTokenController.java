package org.paasta.caas.api.accessInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hrjin
 * @version 1.0
 * @since 2018-09-04
 */
@RestController
@RequestMapping("/accessInfo")
public class AccessTokenController {

    private final AccessTokenService accessTokenService;

    @Autowired
    public AccessTokenController(AccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

    @RequestMapping(value = "/namespaces/{namespace}/secrets/{accessTokenName}")
    @ResponseBody
    public AccessToken getSecret(@PathVariable("namespace") String namespace, @PathVariable("accessTokenName") String accessTokenName){
        return accessTokenService.getSecret(namespace, accessTokenName);
    }
}
