package com.jfjara.meep.meeptest.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.jfjara.meep.meeptest.exceptions.MeepException;

@Component
public class Utils {
	
	@Value("${config.url}")
	private String url;
	
	@Value("${config.lowerLeftLatLon}")
	private String lowerLeftLatLon;
	
	@Value("${config.upperRightLatLon}")
	private String upperRightLatLon;
	
	@Value("${config.companyZoneIds}")
	private String companyZoneIds;
	
	public String getUrl() {
		return url;
	}
	
	public URI createURI(String url, Map<String, String> parameters) throws URISyntaxException, MeepException {
		StringBuilder builder = new StringBuilder();
		builder.append(url).append(Constants.QUESTION_MARK);
		
		if (url == null || url.isEmpty() || parameters == null || parameters.isEmpty()) {
			throw new MeepException("Error creado la URL de petición a servicio");
		}
			
		int parametersProcessed = 0;
		for (Map.Entry<String, String> entry : parameters.entrySet()) {
			
			builder.append(entry.getKey()).append(Constants.EQUALS_MARK).append(entry.getValue());
			if (parametersProcessed < parameters.size() - 1) {
				builder.append(Constants.AMPERSAND_MARK);	
			}
			parametersProcessed++;
		    
		}		
		return new URI(builder.toString());
	}
	
	public HttpHeaders createJsonHeader() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}

	public HttpEntity<HttpHeaders> createHttpEntity() { 
		return new HttpEntity<HttpHeaders>(createJsonHeader());
	}
	
	public Map<String, String> createUrlParameters() {
		Map<String, String> result = new HashMap<String, String>();
		result.put(Constants.LOWER_LEFT_LATLON, lowerLeftLatLon);
		result.put(Constants.UPPER_RIGHT_LATLON, upperRightLatLon);
		result.put(Constants.COMPANY_ZONE_IDS, companyZoneIds);
		return result;
	}
	
}
