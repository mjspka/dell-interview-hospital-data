package com.pmalla.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;

import com.pmalla.dto.HospitalData;
import com.pmalla.service.HospitalServiceCache;

@RunWith(SpringRunner.class)
@WebMvcTest(HospitalController.class)
public class HospitalControllerTest {
	@Autowired
	private MockMvc mvc;

	@MockBean
	private HospitalServiceCache hospitalServiceCache;

	/**
	 * The purpose of this jUnit is to test the api when cityCode is not passed
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFindHospitalDataNoCityCode() throws Exception {
		mvc.perform(get("/hospitals"))
				.andDo(print())
				.andExpect(status().isInternalServerError())
				.andExpect(jsonPath("message", is("Required HospitalCities parameter 'cityCode' is not present")))
				.andReturn();
	}

	/**
	 * The purpose of this jUnit is to test the api when it returns no data
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFindHospitalDataWithValidCityCodeButNoData() throws Exception {
		given(hospitalServiceCache.fetchHospitalData()).willReturn(
				Arrays.asList(
						new HospitalData()));

		mvc.perform(get("/hospitals?cityCode=SFO"))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
	}

	/**
	 * The purpose of this jUnit is to test the api when it returns some data
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFindHospitalDataWithValidCityCodeWithData() throws Exception {
		HospitalData hospitalData = new HospitalData();
		hospitalData.setServices("Hospital");
		hospitalData.setFacilityName("California Pacific Med Ctr-california East");

		given(hospitalServiceCache.fetchHospitalData()).willReturn(
				Arrays.asList(
						hospitalData));

		mvc.perform(get("/hospitals?cityCode=SFO"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].facility_name", is("California Pacific Med Ctr-california East")))
				.andReturn();
	}

}
