package com.jfjara.meep.meeptest.scheduler;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jfjara.meep.meeptest.cache.MeepResourceCache;
import com.jfjara.meep.meeptest.exceptions.MeepException;
import com.jfjara.meep.meeptest.model.MeepResource;
import com.jfjara.meep.meeptest.service.IRestService;
import com.jfjara.meep.meeptest.utils.Constants;
import com.jfjara.meep.meeptest.utils.Utils;

@Component
public class QueryScheduler {

	@Value("${config.url}")
	private String url;
	
	@Value("${config.lowerLeftLatLon}")
	private String lowerLeftLatLon;
	
	@Value("${config.upperRightLatLon}")
	private String upperRightLatLon;
	
	@Value("${config.companyZoneIds}")
	private String companyZoneIds;
		
	@Autowired
	private IRestService restService;
	
	@Autowired
	private MeepResourceCache cache;
	
	@Autowired
	private Utils utils;

	@Scheduled(cron = "${config.cron}")
	public void scheduler() throws URISyntaxException, MeepException, InterruptedException, ExecutionException, TimeoutException {
		Future<List<MeepResource>> futureResponse =  restService.getObjects(
				utils.createURI(url, createUrlParameters()));
		List<MeepResource> resources = futureResponse.get(Constants.TIMEOUT, TimeUnit.MILLISECONDS);		
		cache.processResources(resources);
	}

	private Map<String, String> createUrlParameters() {
		Map<String, String> result = new HashMap<String, String>();
		result.put(Constants.LOWER_LEFT_LATLON, lowerLeftLatLon);
		result.put(Constants.UPPER_RIGHT_LATLON, upperRightLatLon);
		result.put(Constants.COMPANY_ZONE_IDS, companyZoneIds);
		return result;
	}
	
	

}
