package com.pmalla.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.pmalla.dto.HospitalData;

/**
 * The purpose of this method is to provide the functionality to fetch hospital
 * data.
 * 
 * @author Praveen Malla
 *
 */
@Component
public class HospitalService {
	private static Logger logger = LoggerFactory.getLogger(HospitalService.class);
	@Value("${sfo.hospital.data.url}")
	private String hospitalDataUrl;
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * The purpose of this method is to find the hospitals in San Francisco
	 * based on third party REST API.
	 * 
	 * @return list of hospitals
	 */
	public List<HospitalData> fetchHospitalData() {
		logger.info("sfo-hospital-data will be fetched from web");
		ResponseEntity<List<HospitalData>> response = restTemplate.exchange(hospitalDataUrl, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<HospitalData>>() {
				});
		logger.debug(String.format("Hospital Data is %s", response));
		return response.getBody();
	}

}
