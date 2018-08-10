package org.paasta.caas.api.cluster.namespace;

import lombok.Data;

import java.util.List;

/**
 * Namespace Model 클래스
 *
 * @author REX
 * @version 1.0
 * @since 2018.08.07
 */
@Data
public class Namespace {

    private String result;
    private String statusCode;

    private String name;
    private String uid;
    private String clusterName;

    private List items;

}
