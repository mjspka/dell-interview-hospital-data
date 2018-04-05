/**
 * 
 */
package com.pmalla.rest;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pmalla.dto.HospitalData;
import com.pmalla.enums.HospitalCities;
import com.pmalla.exception.NotImplementedException;
import com.pmalla.service.HospitalServiceCache;

import io.swagger.annotations.ApiOperation;

/**
 * @author Praveen Malla
 *
 */
@RestController
public class HospitalController {
	@Autowired
	private HospitalServiceCache hospitalServiceCache;

	/**
	 * The purpose of this api is to serve the user find all the hospitals in a
	 * city.
	 * 
	 * @param cityCode
	 *            city code as defined in the system
	 * @return
	 */
	@RequestMapping(value = "/hospitals", method = RequestMethod.GET)
	@ApiOperation(value = "Finds all hospitals")
	public List<HospitalData> findHospitalData(@RequestParam("cityCode") HospitalCities cityCode) {
		if (cityCode != HospitalCities.SFO) {
			throw new NotImplementedException(
					String.format("Hospitals for City, %s is NOT implemented yet", cityCode.getName()));
		}
		List<HospitalData> hospitals = hospitalServiceCache.fetchHospitalData();
		if (hospitals != null && hospitals.size() > 0) {
			return hospitals.stream()
					.filter(hospital -> (hospital.getServices() != null)
							&& hospital.getServices().equalsIgnoreCase("Hospital") == true)
					.collect(Collectors.toList());
		}
		return Collections.emptyList();
	}
}
