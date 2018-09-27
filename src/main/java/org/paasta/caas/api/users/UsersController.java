package org.paasta.caas.api.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Users Controller 클래스
 *
 * @author hrjin
 * @version 1.0
 * @since 2018-09-03
 */
@RestController
@RequestMapping(value = "/namespaces/{namespace:.+}/serviceAccounts")
public class UsersController {

    private final UsersService usersService;

    /**
     * Instantiates a new Users controller
     *
     * @param usersService the users service
     */
    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    /**
     * Users 를 삭제한다.
     *
     * @param namespace       the namespace
     * @param caasAccountName the caasAccountName
     * @return the Users
     */
    @DeleteMapping(value = "/{caasAccountName:.+}")
    public Users deleteUser(@PathVariable("namespace") String namespace, @PathVariable("caasAccountName") String caasAccountName) {
        return usersService.deleteServiceAccount(namespace, caasAccountName);
    }
}
