package com.pmalla.service;

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
 * The purpose of this method is to test hospital data based on HospitalService.
 * 
 * @author Praveen Malla
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class HospitalServiceTest {
	private static Logger logger = LoggerFactory.getLogger(HospitalServiceTest.class);
	@Autowired
	private HospitalService hospitalService;

	@Test
	public void testFetchHospitalData() {
		StopWatch hospitalDataWatch = new StopWatch("HospitalData");

		hospitalDataWatch.start();
		List<HospitalData> hospitalData = hospitalService.fetchHospitalData();
		hospitalDataWatch.stop();
		Assert.assertNotNull(hospitalData);
	}

}
