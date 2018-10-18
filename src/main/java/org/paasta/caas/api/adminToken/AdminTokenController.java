package org.paasta.caas.api.adminToken;

import org.paasta.caas.api.common.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminTokenController {

    private final RestTemplateService adminTokenService;

    @Autowired
    public AdminTokenController(RestTemplateService adminTokenService) {
        this.adminTokenService = adminTokenService;
    }

    @GetMapping(value = "/adminToken")
    public AdminToken getAdminToken(){
        AdminToken adminTokenValue = adminTokenService.getAdminToken();

        return adminTokenValue;
    }
}
