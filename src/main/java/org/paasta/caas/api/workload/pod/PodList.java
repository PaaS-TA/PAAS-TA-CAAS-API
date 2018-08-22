package org.paasta.caas.api.workload.pod;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Pod List Model 클래스
 *
 * @author REX
 * @version 1.0
 * @since 2018.8.13
 */
@Data
public class PodList {
    private String resultCode;

    private List<Pod> items = new ArrayList<>();

}
