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
		
	@Autowired
	private IRestService restService;
	
	@Autowired
	private MeepResourceCache cache;
	
	@Autowired
	private Utils utils;

	@Scheduled(cron = "${config.cron}")
	public void scheduler() throws URISyntaxException, MeepException, InterruptedException, ExecutionException, TimeoutException {
		Future<List<MeepResource>> futureResponse =  restService.getObjects(
				utils.createURI(utils.getUrl(), utils.createUrlParameters()));
		List<MeepResource> resources = futureResponse.get(Constants.TIMEOUT, TimeUnit.MILLISECONDS);		
		cache.processResources(resources);
	}

}
