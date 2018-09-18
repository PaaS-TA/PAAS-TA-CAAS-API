package org.paasta.caas.api.common.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * CommonContainer Model 클래스
 *
 * @author REX
 * @author CISS
 * @author Hyungu Cho
 * @version 1.0
 * @since 2018.08.16
 */
@Data
public class CommonContainer {
   String name;
   String image;
   List<String> args;
   List<Map> env;
   List<CommonPort> ports;
   CommonResourceRequirement resources;
   List<String> command;
}
