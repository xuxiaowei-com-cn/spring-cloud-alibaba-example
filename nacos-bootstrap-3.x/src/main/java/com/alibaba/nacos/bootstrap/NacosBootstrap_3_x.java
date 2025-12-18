/*
 * Copyright 1999-2023 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.nacos.bootstrap;

import com.alibaba.nacos.NacosServerBasicApplication;
import com.alibaba.nacos.NacosServerWebApplication;
import com.alibaba.nacos.console.NacosConsole;
import com.alibaba.nacos.core.listener.startup.NacosStartUp;
import com.alibaba.nacos.core.listener.startup.NacosStartUpManager;
import com.alibaba.nacos.mcpregistry.NacosMcpRegistry;
import com.alibaba.nacos.plugin.auth.exception.AccessException;
import com.alibaba.nacos.plugin.auth.impl.token.TokenManagerDelegate;
import com.alibaba.nacos.sys.env.Constants;
import com.alibaba.nacos.sys.env.DeploymentType;
import com.alibaba.nacos.sys.env.EnvUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.ResourceBanner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static com.alibaba.nacos.client.auth.impl.NacosAuthLoginConstant.ACCESSTOKEN;

/**
 * Nacos bootstrap class.
 *
 * @author xiweng.yy
 */
@SpringBootApplication
public class NacosBootstrap_3_x {

	private static final String SPRING_JMX_ENABLED = "spring.jmx.enabled";

	private static final Logger log = LoggerFactory.getLogger(NacosBootstrap_3_x.class);

	public static void main(String[] args) {

		// 使用单机模式
		System.setProperty(Constants.STANDALONE_MODE_PROPERTY_NAME, "true");
		// server + console
		System.setProperty(Constants.NACOS_DEPLOYMENT_TYPE, Constants.NACOS_DEPLOYMENT_TYPE_MERGED);

		String type = System.getProperty(Constants.NACOS_DEPLOYMENT_TYPE, Constants.NACOS_DEPLOYMENT_TYPE_MERGED);
		DeploymentType deploymentType = DeploymentType.getType(type);
		EnvUtil.setDeploymentType(deploymentType);
		switch (deploymentType) {
			case MERGED:
				startWithConsole(args);
				break;
			case SERVER:
				startWithoutConsole(args);
				break;
			case CONSOLE:
				startOnlyConsole(args);
				break;
			default:
				throw new IllegalArgumentException("Unsupported nacos deployment type " + type);
		}
	}

	private static void prepareCoreContext(ConfigurableApplicationContext coreContext) {
		if (coreContext.getEnvironment().getProperty(SPRING_JMX_ENABLED, Boolean.class, false)) {
			// Avoid duplicate registration MBean to exporter.
			coreContext.getBean(MBeanExporter.class).setRegistrationPolicy(RegistrationPolicy.IGNORE_EXISTING);
		}
	}

	private static void startWithoutConsole(String[] args) {
		ConfigurableApplicationContext coreContext = startCoreContext(args);
		prepareCoreContext(coreContext);
		ConfigurableApplicationContext webContext = startServerWebContext(args, coreContext);
		if (isEnabledMcpRegistryApi(coreContext)) {
			ConfigurableApplicationContext mcpRegistryContext = startMcpRegistryContext(args, coreContext);
		}
	}

	private static void startWithConsole(String[] args) {
		ConfigurableApplicationContext coreContext = startCoreContext(args);
		prepareCoreContext(coreContext);
		ConfigurableApplicationContext serverWebContext = startServerWebContext(args, coreContext);
		ConfigurableApplicationContext consoleContext = startConsoleContext(args, coreContext);
		if (isEnabledMcpRegistryApi(coreContext)) {
			ConfigurableApplicationContext mcpRegistryContext = startMcpRegistryContext(args, coreContext);
		}

		String env = System.getenv("NACOS_CREATE_TOKEN");
		if (StringUtils.hasText(env) && "true".equalsIgnoreCase(env)) {
			TokenManagerDelegate tokenManagerDelegate = coreContext.getBean(TokenManagerDelegate.class);
			try {
				String token = tokenManagerDelegate.createToken("nacos");

				String url = "http://127.0.0.1:8080/v3/console/cs/config";
				HttpHeaders headers = new HttpHeaders();
				headers.add(ACCESSTOKEN, token);
				headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
				MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
				requestBody.set("dataId", "token.yml");
				requestBody.set("group", "DEFAULT_GROUP");
				requestBody.set("groupName", "DEFAULT_GROUP");
				requestBody.set("content", "nacos:\n" + "  token: " + token);
				requestBody.set("type", "yaml");
				HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(requestBody, headers);
				RestTemplate restTemplate = new RestTemplate();
				Map map = restTemplate.postForObject(url, entity, Map.class);
				log.info(String.valueOf(map));
				if (map == null) {
					throw new RuntimeException("create token failed");
				}
				if (!map.containsKey("data") || !(map.get("data") instanceof Boolean) || !((Boolean) map.get("data"))) {
					throw new RuntimeException("create token failed");
				}
			}
			catch (AccessException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private static ConfigurableApplicationContext startCoreContext(String[] args) {
		NacosStartUpManager.start(NacosStartUp.CORE_START_UP_PHASE);
		return new SpringApplicationBuilder(NacosServerBasicApplication.class).web(WebApplicationType.NONE)
			.banner(getBanner("core-banner.txt"))
			.run(args);
	}

	private static ConfigurableApplicationContext startServerWebContext(String[] args,
			ConfigurableApplicationContext coreContext) {
		NacosStartUpManager.start(NacosStartUp.WEB_START_UP_PHASE);
		return new SpringApplicationBuilder(NacosServerWebApplication.class).parent(coreContext)
			.banner(getBanner("nacos-server-web-banner.txt"))
			.run(args);
	}

	private static ConfigurableApplicationContext startConsoleContext(String[] args,
			ConfigurableApplicationContext coreContext) {
		NacosStartUpManager.start(NacosStartUp.CONSOLE_START_UP_PHASE);
		return new SpringApplicationBuilder(NacosConsole.class).parent(coreContext)
			.banner(getBanner("nacos-console-banner.txt"))
			.run(args);
	}

	private static ConfigurableApplicationContext startMcpRegistryContext(String[] args,
			ConfigurableApplicationContext coreContext) {
		NacosStartUpManager.start(NacosStartUp.MCP_REGISTRY_START_UP_PHASE);
		return new SpringApplicationBuilder(NacosMcpRegistry.class).parent(coreContext)
			.banner(getBanner("nacos-mcp-registry-banner.txt"))
			.run(args);
	}

	private static void startOnlyConsole(String[] args) {
		NacosStartUpManager.start(NacosStartUp.CONSOLE_START_UP_PHASE);
		ConfigurableApplicationContext consoleContext = new SpringApplicationBuilder(NacosConsole.class)
			.banner(getBanner("nacos-console-banner.txt"))
			.run(args);
	}

	private static Banner getBanner(String bannerFileName) {
		return new ResourceBanner(new ClassPathResource(bannerFileName));
	}

	private static boolean isEnabledMcpRegistryApi(ConfigurableApplicationContext coreContext) {
		return coreContext.getEnvironment().getProperty("nacos.ai.mcp.registry.enabled", Boolean.class, false);
	}

}
