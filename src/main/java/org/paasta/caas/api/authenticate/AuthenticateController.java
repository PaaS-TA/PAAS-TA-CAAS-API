package org.paasta.caas.api.authenticate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Authenticate Controller 클래스.
 *
 * @author indra
 * @version 1.0
 * @since 2018.09.05 최초작성
 */
@RestController
public class AuthenticateController {

    private final AuthenticateService authenticateService;

    /**
     * Instantiates a Authenticate controller.
     *
     * @param authenticateService the authenticate service
     */
    @Autowired
    public AuthenticateController(AuthenticateService authenticateService) {
        this.authenticateService = authenticateService;
    }

    /**
     * 유저를 생성한다.
     *
     * @param namespace the namespace
     * @param yml the yml
     */
    @PostMapping(value = "/namespaces/{namespace}/serviceaccounts")
    public void createUser(@PathVariable("namespace") String namespace, @RequestBody String yml){
        authenticateService.createUser(namespace, yml);
    }

    /**
     * 롤을 생성한다.
     *
     * @param namespace the namespace
     * @param yml the yml
     */
    @PostMapping(value = "/namespaces/{namespace}/roles")
    public void createRole(@PathVariable("namespace") String namespace, @RequestBody String yml){
        authenticateService.createRole(namespace, yml);
    }

    /**
     * 롤을 바인딩한다.
     *
     * @param namespace the namespace
     * @param yml the yml
     */
    @PostMapping(value = "/namespaces/{namespace}/rolebindings")
    public void createRoleBinding(@PathVariable("namespace") String namespace, @RequestBody String yml){
        authenticateService.createRoleBinding(namespace, yml);
    }

    /**
     * 토큰을 조회한다.
     *
     * @param namespace the namespace
     * @param userName the user name
     * @return the String
     */
    @GetMapping(value = "/namespaces/{namespace}/serviceaccounts/{userName}")
    public String getToken(@PathVariable("namespace") String namespace, @PathVariable("userName") String userName){
        return authenticateService.getToken(namespace, userName);
    }
}
