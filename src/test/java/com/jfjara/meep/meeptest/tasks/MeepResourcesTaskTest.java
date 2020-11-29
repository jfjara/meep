package com.jfjara.meep.meeptest.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.jfjara.meep.meeptest.model.MeepResource;
import com.jfjara.meep.meeptest.utils.UtilsSupport;

@RunWith(MockitoJUnitRunner.class)
public class MeepResourcesTaskTest {

	@Mock
	private RestTemplate customRestTemplate;
	
	@Mock
	private URI uri;
	
	@Mock 
	private HttpMethod method;
	
	@Mock 
	private HttpEntity<HttpHeaders> entity;
	
	@Test
	public void testCall() throws Exception {
		MeepResource[] resources = UtilsSupport.generateArrayResources();
		ResponseEntity<MeepResource[]> responseMock = Mockito.mock(ResponseEntity.class);					
		ResponseEntity<MeepResource[]> response = new ResponseEntity<MeepResource[]>(resources, HttpStatus.OK);
		HttpEntity<HttpHeaders> entity = new HttpEntity<HttpHeaders>(new HttpHeaders());
		MeepResourcesTask task = new MeepResourcesTask(uri, customRestTemplate, entity);
		PowerMockito.doReturn(responseMock).when(customRestTemplate).exchange(
				uri,
				HttpMethod.GET,
				entity,
				 MeepResource[].class);
		PowerMockito.doReturn(resources).when(responseMock).getBody();
		List<MeepResource> result = task.call();
		assertEquals(response.getBody()[0].getId(), result.get(0).getId());
	}
	
}
