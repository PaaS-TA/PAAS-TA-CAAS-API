package org.paasta.caas.api.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 읽어들인 환경 변수를 가지고 있는 클래스
 *
 * @author 최윤석
 * @since 2018.07.24
 * @version 20180724
 */

@Data
public class EnvConfig {

	String kubeApiUrl;


	String kubeAdminToken;
}