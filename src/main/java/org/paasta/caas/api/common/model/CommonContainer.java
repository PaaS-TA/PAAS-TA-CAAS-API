package org.paasta.caas.api.common.model;

import com.google.gson.annotations.SerializedName;
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
   /* For PodTemplateSpec -- START */
   String name;
   String image;
   List<String> args;
   List<Map> env;
   List<CommonPort> ports;
   CommonResourceRequirement resources;
   List<String> command;

//   @SerializedName("envFrom")
//   private List<EnvFromSource> envFrom = null;
//
//   @SerializedName("imagePullPolicy")
//   private String imagePullPolicy = null;
//
//   @SerializedName("lifecycle")
//   private CommonLifecycle lifecycle = null;
//
//   @SerializedName("livenessProbe")
//   private CommonProbe livenessProbe = null;
//
//   @SerializedName("readinessProbe")
//   private CommonProbe readinessProbe = null;
//
//   @SerializedName("securityContext")
//   private CommonSecurityContext securityContext = null;
//
//   @SerializedName("stdin")
//   private boolean stdin = null;
//
//   @SerializedName("stdinOnce")
//   private boolean stdinOnce = null;
//
//   @SerializedName("terminationMessagePath")
//   private String terminationMessagePath = null;
//
//   @SerializedName("terminationMessagePolicy")
//   private String terminationMessagePolicy = null;
//
//   @SerializedName("tty")
//   private boolean tty = null;
//
//     @SerializedName( "volumeMounts" )
//     List<VolumeMount> volumeMounts;
//
//     @SerializedName( "volumeDevices" )
//     List<VolumeDevice> volumeDevices;
//
//     @SerializedName( "workingDir" )
//     String workingDir;
   /* For PodTemplateSpec -- END   */
}
