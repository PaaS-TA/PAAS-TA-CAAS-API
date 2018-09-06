package org.paasta.caas.api.authenticate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * DashBoard 에서 인증을 위해 호출 받는 컨트롤러.
 *
 * @author indra
 * @version 1.0
 * @since 2018.09.05 최초작성
 */
@RestController
@RequestMapping(value = "/authenticate")
public class AuthenticateController {

    private final AuthenticateService authenticateService;

    @Autowired
    public AuthenticateController(AuthenticateService authenticateService) {
        this.authenticateService = authenticateService;
    }

    /**
     * createUser 유저를 생성.
     *
     * @param namespace
     * @param yml
     * @return
     */
    @PostMapping(value = "/namespaces/{namespace}/serviceaccounts")
    public void createUser(@PathVariable("namespace") String namespace, @RequestBody String yml){
        authenticateService.createUser(namespace, yml);
    }

    /**
     * createRole 롤을 생성.
     *
     * @param namespace
     * @param yml
     * @return
     */
    @PostMapping(value = "/namespaces/{namespace}/roles")
    public void createRole(@PathVariable("namespace") String namespace, @RequestBody String yml){
        authenticateService.createRole(namespace, yml);
    }

    /**
     * createRoleBinding 롤을 바인딩.
     *
     * @param namespace
     * @param yml
     * @return
     */
    @PostMapping(value = "/namespaces/{namespace}/rolebindings")
    public void createRoleBinding(@PathVariable("namespace") String namespace, @RequestBody String yml){
        authenticateService.createRoleBinding(namespace, yml);
    }

    /**
     * getToken 토큰을 조회.
     *
     * @param namespace
     * @param userName
     * @return the String
     */
    @GetMapping(value = "/namespaces/{namespace}/serviceaccounts/{userName}")
    public String getToken(@PathVariable("namespace") String namespace, @PathVariable("userName") String userName){
        return authenticateService.getToken(namespace, userName);
    }
}
