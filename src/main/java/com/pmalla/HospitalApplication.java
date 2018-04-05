package com.pmalla;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class HospitalApplication extends SpringBootServletInitializer {

	@Autowired
	Environment env;

	@Value("${proxy.host.name}")
	private String proxyHost;

	@Value("${proxy.port}")
	private int proxyPort;

	@Value("${proxy.user}")
	private String proxyUser;

	@Value("${proxy.passwd}")
	private String proxyPassword;

	@Value("${proxy.enabled}")
	private Boolean proxyEnabled;

	public static void main(String[] args) {
		SpringApplication.run(HospitalApplication.class, args);
	}

	@Bean
	public MappingJackson2HttpMessageConverter jacksonMessageConverter() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(mapper);
		return converter;
	}

	@Bean
	public RestTemplate restTemplate() {
		HttpClientBuilder clientBuilder = HttpClientBuilder.create();

		if (proxyEnabled) {
			CredentialsProvider credsProvider = new BasicCredentialsProvider();
			credsProvider.setCredentials(
					new AuthScope(proxyHost, proxyPort),
					new UsernamePasswordCredentials(proxyUser, proxyPassword));

			HttpHost myProxy = new HttpHost(proxyHost, proxyPort);
			clientBuilder.setProxy(myProxy).setDefaultCredentialsProvider(credsProvider).disableCookieManagement();
		}

		HttpClient httpClient = clientBuilder.build();
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setHttpClient(httpClient);

		factory.setReadTimeout(1 * 60 * 1000);
		factory.setConnectTimeout(15 * 1000);
		factory.setConnectionRequestTimeout(15 * 1000);

		return new RestTemplate(factory);
	}

}
