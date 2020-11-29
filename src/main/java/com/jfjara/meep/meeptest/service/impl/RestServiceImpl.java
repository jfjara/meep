package com.jfjara.meep.meeptest.service.impl;

import java.net.URI;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jfjara.meep.meeptest.model.MeepResource;
import com.jfjara.meep.meeptest.service.IRestService;
import com.jfjara.meep.meeptest.tasks.MeepResourcesTask;
import com.jfjara.meep.meeptest.utils.Utils;

@Service
public class RestServiceImpl implements IRestService {

	@Autowired
	private RestTemplate customRestTemplate;
	
	@Autowired
	private Utils utils;
	
	@Autowired
	private ExecutorService executor;
	
	public Future<List<MeepResource>> getObjects(URI uri) {	
		return executor.submit(new MeepResourcesTask(uri, customRestTemplate, utils.createHttpEntity()));
	}
	
}
