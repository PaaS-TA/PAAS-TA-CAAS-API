package org.paasta.caas.api.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 읽어들인 환경 변수를 가지고 있는 클래스
 *
 * @author Hyerin
 * @since 2018.07.24
 * @version 20180724
 */
@Component
@Data
public class EnvConfig {

	@Value("${caas.url}")
	String caasUrl;

	@Value("${caas.testUserToken}")
	String testUserToken;
}