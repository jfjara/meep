package com.jfjara.meep.meeptest.tasks;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.jfjara.meep.meeptest.model.MeepResource;

public class MeepResourcesTask implements Callable<List<MeepResource>> {
	
	private RestTemplate customRestTemplate;	
	private URI uri; 
	private HttpEntity<HttpHeaders> entity;
	
	public MeepResourcesTask(URI uri, RestTemplate customRestTemplate, HttpEntity<HttpHeaders> entity) {
		this.uri = uri;
		this.entity = entity;
		this.customRestTemplate = customRestTemplate;
	}

	@Override
	public List<MeepResource> call() throws Exception {
		ResponseEntity<MeepResource[]> response = customRestTemplate.exchange(uri, HttpMethod.GET, 
				entity,
				MeepResource[].class);
		return Arrays.asList(response.getBody());
	}

}
