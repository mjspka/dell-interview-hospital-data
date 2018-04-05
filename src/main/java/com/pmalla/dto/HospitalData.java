package com.pmalla.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This is POJO to store hospital data.
 * 
 * @author Praveen Malla
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true, value = { "uid", "oshpd_id", "services" }, allowSetters = true)
@JsonInclude(Include.NON_NULL)
public class HospitalData {
	private String uid;

	@JsonProperty("oshpd_id")
	private String oshpdId;

	@JsonProperty("facility_type")
	private String facilityType;

	private String services;

	@JsonProperty("facility_name")
	private String facilityName;

	@JsonProperty("location")
	private HospitalLocation hospitalLocation;

	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * @param uid
	 *            the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * @return the oshpdId
	 */
	@JsonIgnore
	public String getOshpdId() {
		return oshpdId;
	}

	/**
	 * @param oshpdId
	 *            the oshpdId to set
	 */
	@JsonProperty
	public void setOshpdId(String oshpdId) {
		this.oshpdId = oshpdId;
	}

	/**
	 * @return the facilityType
	 */
	public String getFacilityType() {
		return facilityType;
	}

	/**
	 * @param facilityType
	 *            the facilityType to set
	 */
	public void setFacilityType(String facilityType) {
		this.facilityType = facilityType;
	}

	/**
	 * @return the services
	 */
	@JsonIgnore
	public String getServices() {
		return services;
	}

	/**
	 * @param services
	 *            the services to set
	 */
	@JsonProperty
	public void setServices(String services) {
		this.services = services;
	}

	/**
	 * @return the facilityName
	 */
	public String getFacilityName() {
		return facilityName;
	}

	/**
	 * @param facilityName
	 *            the facilityName to set
	 */
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	/**
	 * @return the hospitalLocation
	 */
	public HospitalLocation getHospitalLocation() {
		return hospitalLocation;
	}

	/**
	 * @param hospitalLocation
	 *            the hospitalLocation to set
	 */
	public void setHospitalLocation(HospitalLocation hospitalLocation) {
		this.hospitalLocation = hospitalLocation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((facilityName == null) ? 0 : facilityName.hashCode());
		result = prime * result + ((facilityType == null) ? 0 : facilityType.hashCode());
		result = prime * result + ((hospitalLocation == null) ? 0 : hospitalLocation.hashCode());
		result = prime * result + ((oshpdId == null) ? 0 : oshpdId.hashCode());
		result = prime * result + ((services == null) ? 0 : services.hashCode());
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HospitalData other = (HospitalData) obj;
		if (facilityName == null) {
			if (other.facilityName != null)
				return false;
		} else if (!facilityName.equals(other.facilityName))
			return false;
		if (facilityType == null) {
			if (other.facilityType != null)
				return false;
		} else if (!facilityType.equals(other.facilityType))
			return false;
		if (hospitalLocation == null) {
			if (other.hospitalLocation != null)
				return false;
		} else if (!hospitalLocation.equals(other.hospitalLocation))
			return false;
		if (oshpdId == null) {
			if (other.oshpdId != null)
				return false;
		} else if (!oshpdId.equals(other.oshpdId))
			return false;
		if (services == null) {
			if (other.services != null)
				return false;
		} else if (!services.equals(other.services))
			return false;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HospitalData [uid=" + uid + ", oshpdId=" + oshpdId + ", facilityType=" + facilityType + ", services="
				+ services + ", facilityName=" + facilityName + ", hospitalLocation=" + hospitalLocation + "]";
	}
}
