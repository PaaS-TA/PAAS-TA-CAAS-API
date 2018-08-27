package org.paasta.caas.api.nodes;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Node List Model 클래스
 *
 * @author REX
 * @version 1.0
 * @since 2018.08.13
 */
@Data
public class NodeList {
    private String resultCode;

    private List<Node> items = new ArrayList<>();
}
