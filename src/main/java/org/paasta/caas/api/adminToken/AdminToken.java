package org.paasta.caas.api.adminToken;

import lombok.Data;

@Data
public class AdminToken {

    private String tokenName;
    private String tokenValue;
    private String resultCode;
}
