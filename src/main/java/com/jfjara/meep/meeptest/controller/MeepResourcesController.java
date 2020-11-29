package com.jfjara.meep.meeptest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jfjara.meep.meeptest.cache.MeepResourceCache;
import com.jfjara.meep.meeptest.cache.MeepResourceCache.ResourceTypeEnum;
import com.jfjara.meep.meeptest.exceptions.MeepException;
import com.jfjara.meep.meeptest.model.MeepResource;

@RestController
public class MeepResourcesController {

	@Autowired
	private MeepResourceCache cache;
	
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
	
}
