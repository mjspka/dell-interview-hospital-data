package com.pmalla.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pmalla.dto.HospitalData;

/**
 * The purpose of this method is to provide the cache functionality to store
 * hospital data.
 * 
 * @author Praveen Malla
 *
 */
@Component
public class HospitalServiceCache {
	private static Logger logger = LoggerFactory.getLogger(HospitalServiceCache.class);
	@Autowired
	private HospitalService hospitalService;

	private boolean cached;

	public HospitalServiceCache() {
		cached = false;
	}

	/**
	 * The purpose of this method is to cache hospitals in San Francisco once it
	 * is retrieved from web
	 * 
	 * @return list of hospitals
	 */
	@Cacheable("sfo-hospital-data")
	public List<HospitalData> fetchHospitalData() {
		logger.info("sfo-hospital-data cache will be created after fetching from web");
		List<HospitalData> hospitalData = hospitalService.fetchHospitalData();
		cached = true;
		return hospitalData;
	}

	/**
	 * The purpose of this method is to evict the cache for San Francisco
	 * hospitals. By default, the cache will be evicted automatically for every
	 * minute.
	 */
	@CacheEvict(cacheNames = "sfo-hospital-data", allEntries = true)
	@Scheduled(fixedDelay = 1 * 60 * 1000, initialDelay = 500)
	public void evictHospitalDataFromCache() {
		logger.info("sfo-hospital-data cache is cleared");
		cached = false;
	}

	/**
	 * @return the cached
	 */
	public boolean isCached() {
		return cached;
	}
}
