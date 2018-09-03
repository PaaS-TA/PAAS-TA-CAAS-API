package org.paasta.caas.api.customServices;

import lombok.Data;

import java.util.List;

/**
 * Custom Services List Model 클래스
 *
 * @author REX
 * @version 1.0
 * @since 2018.08.09
 */
@Data
public class CustomServicesList {

    private String resultCode;
    private List<CustomServices> items;

}
