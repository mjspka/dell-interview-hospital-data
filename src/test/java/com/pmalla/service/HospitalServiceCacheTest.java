package com.pmalla.service;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StopWatch;

import com.pmalla.dto.HospitalData;

/**
 * The purpose of this method is to test hospital data based on
 * HospitalServiceCache.
 * 
 * @author Praveen Malla
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class HospitalServiceCacheTest {
	private static Logger logger = LoggerFactory.getLogger(HospitalServiceCacheTest.class);
	@Autowired
	private HospitalServiceCache hospitalServiceCache;

	/**
	 * The purpose of this jUnit is to test the cache functionality of hospital
	 * data
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testFetchHospitalData() throws InterruptedException {
		StopWatch hospitalDataWatch = new StopWatch("HospitalData");

		hospitalDataWatch.start();
		Assert.assertEquals(false, hospitalServiceCache.isCached());
		List<HospitalData> hospitalData = hospitalServiceCache.fetchHospitalData();
		hospitalDataWatch.stop();
		Assert.assertNotNull(hospitalData);
		Assert.assertEquals(true, hospitalServiceCache.isCached());
		logger.info(
				String.format("Time taken to fetch from cache is %s SECONDS",
						hospitalDataWatch.getTotalTimeSeconds()));
		logger.info(String.format("Hospital Data is %s", Arrays.toString(hospitalData.toArray())));

		// Now fetching from cache
		Assert.assertEquals(true, hospitalServiceCache.isCached());
		hospitalDataWatch.start();
		List<HospitalData> hospitalDataCache = hospitalServiceCache.fetchHospitalData();
		hospitalDataWatch.stop();
		Assert.assertEquals(true, hospitalServiceCache.isCached());
		Assert.assertEquals(hospitalData, hospitalDataCache);
		logger.info(
				String.format("Time taken to fetch from cache is %s SECONDS",
						hospitalDataWatch.getTotalTimeSeconds()));
		logger.info(String.format("Hospital Data is %s", Arrays.toString(hospitalDataCache.toArray())));

		logger.info("******** The jUnits will sleep for a minute. This is expected *****");
		logger.info("******** The jUnits will sleep for a minute. This is expected *****");
		logger.info(
				"The application is going into sleep for a minute. This is part of validating cache functionality.");
		Thread.sleep(1 * 60 * 1000);
		Assert.assertEquals(false, hospitalServiceCache.isCached());
	}

	/**
	 * The purpose of this jUnit is to test cache eviction functionality.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testEvictHospitalDataFromCache() throws InterruptedException {

		Assert.assertEquals(false, hospitalServiceCache.isCached());
		List<HospitalData> hospitalData = hospitalServiceCache.fetchHospitalData();
		Assert.assertNotNull(hospitalData);
		Assert.assertEquals(true, hospitalServiceCache.isCached());

		hospitalServiceCache.evictHospitalDataFromCache();
		Assert.assertEquals(false, hospitalServiceCache.isCached());
	}
}
