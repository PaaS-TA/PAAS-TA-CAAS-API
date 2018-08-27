package org.paasta.caas.api.nodes;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Nodes List Model 클래스
 *
 * @author REX
 * @version 1.0
 * @since 2018.08.13
 */
@Data
public class NodesList {
    private String resultCode;

    private List<Nodes> items = new ArrayList<>();
}
