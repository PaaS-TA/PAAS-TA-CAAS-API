package org.paasta.caas.api.customService;

import lombok.Data;

import java.util.List;

/**
 * Custom Service List Model 클래스
 *
 * @author REX
 * @version 1.0
 * @since 2018.08.09
 */
@Data
public class CustomServiceList {

    private String resultCode;
    private List<CustomService> items;

}
