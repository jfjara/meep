package com.jfjara.meep.meeptest.cache;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.jfjara.meep.meeptest.cache.MeepResourceCache.ResourceTypeEnum;
import com.jfjara.meep.meeptest.exceptions.MeepException;
import com.jfjara.meep.meeptest.model.MeepResource;
import com.jfjara.meep.meeptest.utils.UtilsSupport;

@RunWith(MockitoJUnitRunner.class)
public class MeepResourceCacheTest {

	private static final String IDENTIFIER_1 = "ID-1";
	private static final String IDENTIFIER_2 = "ID-2";
	private static final String IDENTIFIER_5 = "ID-5";
	
	@Mock
	private MeepResourceCache cacheMock;
	
	@Test
	public void getCacheAvailTest() throws MeepException {
		Mockito.when(cacheMock.getCache(ResourceTypeEnum.AVAIL)).thenReturn(UtilsSupport.generateMockResources1());
		List<MeepResource> results = cacheMock.getCache(ResourceTypeEnum.AVAIL);
		assertEquals(5, results.size());
		assertEquals(results.get(0).getId(), IDENTIFIER_1);
	}		
	
	@Test
	public void getCacheNotAvailTest() throws MeepException {
		Mockito.when(cacheMock.getCache(ResourceTypeEnum.NOT_AVAIL)).thenReturn(UtilsSupport.generateMockResources1());
		List<MeepResource> results = cacheMock.getCache(ResourceTypeEnum.NOT_AVAIL);
		assertEquals(5, results.size());
		assertEquals(results.get(0).getId(), IDENTIFIER_1);
	}	
	
	@Test
	public void getCacheModifiedTest() throws MeepException {
		Mockito.when(cacheMock.getCache(ResourceTypeEnum.MODIFIED)).thenReturn(UtilsSupport.generateMockResources1());
		List<MeepResource> results = cacheMock.getCache(ResourceTypeEnum.MODIFIED);
		assertEquals(5, results.size());
		assertEquals(results.get(0).getId(), IDENTIFIER_1);
	}
	
	@Test
	public void testModifiedCache() throws MeepException {		
		MeepResourceCache cache = new MeepResourceCache();
		cache.processResources(UtilsSupport.generateMockResources1());
		cache.processResources(UtilsSupport.generateMockResources2());		
		List<MeepResource> resourcesModifieds = cache.getCache(ResourceTypeEnum.MODIFIED);		
		assertEquals(1, resourcesModifieds.size());
		assertEquals(IDENTIFIER_2, resourcesModifieds.get(0).getId());		
	}
	
	@Test
	public void testAvailCache() throws MeepException {		
		MeepResourceCache cache = new MeepResourceCache();
		cache.processResources(UtilsSupport.generateMockResources1());
		cache.processResources(UtilsSupport.generateMockResources2());		
		List<MeepResource> resourcesModifieds = cache.getCache(ResourceTypeEnum.AVAIL);		
		assertEquals(5, resourcesModifieds.size());			
	}
	
	public void testNoAvailCache() throws MeepException {		
		MeepResourceCache cache = new MeepResourceCache();
		cache.processResources(UtilsSupport.generateMockResources1());
		cache.processResources(UtilsSupport.generateMockResources2());		
		List<MeepResource> resourcesModifieds = cache.getCache(ResourceTypeEnum.NOT_AVAIL);		
		assertEquals(1, resourcesModifieds.size());
		assertEquals(IDENTIFIER_5, resourcesModifieds.get(0).getId());		
	}
	
	
	

	
}
