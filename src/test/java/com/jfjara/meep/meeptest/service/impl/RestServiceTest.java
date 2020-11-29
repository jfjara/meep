package com.jfjara.meep.meeptest.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import com.jfjara.meep.meeptest.model.MeepResource;
import com.jfjara.meep.meeptest.utils.Utils;
import com.jfjara.meep.meeptest.utils.UtilsSupport;

@RunWith(MockitoJUnitRunner.class)
public class RestServiceTest {

	@Mock
	private Utils utils;
	
	@Mock
	private ExecutorService executor;
	
	@InjectMocks
	private RestServiceImpl restService;
	
	@Mock 
	Future<List<MeepResource>> mockFuture;
	
	@Mock
	private HttpEntity<HttpHeaders> entity;
			
	@Test
	public void test() throws URISyntaxException, InterruptedException, ExecutionException {
		MeepResource[] resources = UtilsSupport.generateArrayResources();
		URI uri = createMockURI();		
		Mockito.when(utils.createHttpEntity()).thenReturn(entity);
		Mockito.when(executor.submit(Mockito.any(Callable.class))).thenReturn(mockFuture);
		Mockito.when(mockFuture.get()).thenReturn(Arrays.asList(resources));
		Future<List<MeepResource>> objectsReturned = restService.getObjects(uri);
		Assert.assertEquals(resources[0], objectsReturned.get().get(0));		
	}

	
	private URI createMockURI() throws URISyntaxException {
		URI uri = new URI(UtilsSupport.HTTP_LOCALHOST_8080);
		return uri;
	}
	
}
