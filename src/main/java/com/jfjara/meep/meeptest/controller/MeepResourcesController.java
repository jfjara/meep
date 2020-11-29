package com.jfjara.meep.meeptest.controller;

import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jfjara.meep.meeptest.cache.MeepResourceCache;
import com.jfjara.meep.meeptest.cache.MeepResourceCache.ResourceTypeEnum;
import com.jfjara.meep.meeptest.exceptions.MeepException;
import com.jfjara.meep.meeptest.model.MeepResource;
import com.jfjara.meep.meeptest.service.IRestService;
import com.jfjara.meep.meeptest.utils.Constants;
import com.jfjara.meep.meeptest.utils.Utils;

@RestController
public class MeepResourcesController {

	@Autowired
	private MeepResourceCache cache;
	
	@Autowired
	private IRestService restService;
	
	@Autowired
	private Utils utils;
	
	@GetMapping("/avail")
	public ResponseEntity<List<MeepResource>> getAvailResources() throws MeepException {
		return new ResponseEntity<>(cache.getCache(ResourceTypeEnum.AVAIL), HttpStatus.OK);
	}
	
	@GetMapping("/notavail")
	public ResponseEntity<List<MeepResource>> getNotAvailResources() throws MeepException {
		return new ResponseEntity<>(cache.getCache(ResourceTypeEnum.NOT_AVAIL), HttpStatus.OK);
	}
	
	@GetMapping("/modified")
	public ResponseEntity<List<MeepResource>> getUpdatedResourcesSinceLastRead() throws MeepException {
		return new ResponseEntity<>(cache.getCache(ResourceTypeEnum.MODIFIED), HttpStatus.OK);
	}
	
	@GetMapping("/get")
	public ResponseEntity<List<MeepResource>> get() throws MeepException, URISyntaxException, InterruptedException, ExecutionException, TimeoutException {
		Future<List<MeepResource>> futureResponse =  restService.getObjects(
				utils.createURI(utils.getUrl(), utils.createUrlParameters()));
		List<MeepResource> resources = futureResponse.get(Constants.TIMEOUT, TimeUnit.MILLISECONDS);		
		cache.processResources(resources);
		return new ResponseEntity<>(resources, HttpStatus.OK);
	}
	
	
}
