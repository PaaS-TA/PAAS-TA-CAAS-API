package org.paasta.caas.api.common.model;

import lombok.Data;

/**
 * ObjectReference Model 클래스
 *
 * @author CISS
 * @version 1.0
 * @since 2018.08.23
 */
@Data
public class CommonObjectReference {
    private String apiVersion;
    private String fieldPath;
    private String kind;
    private String name;
    private String namespace;
    private String resourceVersion;
    private String uid;
}
