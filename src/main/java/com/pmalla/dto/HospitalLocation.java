package com.pmalla.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This is POJO to store hospital location.
 * 
 * @author Praveen Malla
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true, value = { "human_address", "needs_recoding" })
@JsonInclude(Include.NON_NULL)
public class HospitalLocation {
	private String latitude;

	private String longitude;

	@JsonProperty("human_address")
	private String humanAddress;

	@JsonProperty("needs_recoding")
	private Boolean needsRecording;

	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the humanAddress
	 */
	@JsonIgnore
	public String getHumanAddress() {
		return humanAddress;
	}

	/**
	 * @param humanAddress
	 *            the humanAddress to set
	 */
	@JsonProperty
	public void setHumanAddress(String humanAddress) {
		this.humanAddress = humanAddress;
	}

	/**
	 * @return the needsRecording
	 */
	public Boolean getNeedsRecording() {
		return needsRecording;
	}

	/**
	 * @param needsRecording
	 *            the needsRecording to set
	 */
	public void setNeedsRecording(Boolean needsRecording) {
		this.needsRecording = needsRecording;
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
		result = prime * result + ((humanAddress == null) ? 0 : humanAddress.hashCode());
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
		result = prime * result + ((needsRecording == null) ? 0 : needsRecording.hashCode());
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
		HospitalLocation other = (HospitalLocation) obj;
		if (humanAddress == null) {
			if (other.humanAddress != null)
				return false;
		} else if (!humanAddress.equals(other.humanAddress))
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		if (needsRecording == null) {
			if (other.needsRecording != null)
				return false;
		} else if (!needsRecording.equals(other.needsRecording))
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
		return "HospitalLocation [latitude=" + latitude + ", longitude=" + longitude + ", humanAddress=" + humanAddress
				+ ", needsRecording=" + needsRecording + "]";
	}
}
