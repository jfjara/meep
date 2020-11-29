package com.jfjara.meep.meeptest.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.jfjara.meep.meeptest.cache.MeepResourceCache;
import com.jfjara.meep.meeptest.cache.MeepResourceCache.ResourceTypeEnum;
import com.jfjara.meep.meeptest.model.MeepResource;
import com.jfjara.meep.meeptest.utils.UtilsSupport;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(MeepResourcesController.class)
public class MeepResourcesControllerTest {

	@InjectMocks
	private MeepResourcesController controller;
	
	@Mock
	private MeepResourceCache cache;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
	
	@Test
	public void testAvailResourcesWithResults() throws Exception {
		List<MeepResource> resources = createMockResourcesArray();
		Mockito.when(cache.getCache(ResourceTypeEnum.AVAIL)).thenReturn(resources);
		
		mockMvc.perform(MockMvcRequestBuilders.get(UtilsSupport.AVAIL_CONTROLLER).accept(MediaType.APPLICATION_JSON))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testNotAvailResourcesWithResults() throws Exception {
		List<MeepResource> resources = createMockResourcesArray();
		Mockito.when(cache.getCache(ResourceTypeEnum.NOT_AVAIL)).thenReturn(resources);
		
		mockMvc.perform(MockMvcRequestBuilders.get(UtilsSupport.NOT_AVAIL_CONTROLLER).accept(MediaType.APPLICATION_JSON))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testModifiedResourcesWithResults() throws Exception {
		List<MeepResource> resources = createMockResourcesArray();
		Mockito.when(cache.getCache(ResourceTypeEnum.MODIFIED)).thenReturn(resources);
		
		mockMvc.perform(MockMvcRequestBuilders.get(UtilsSupport.MODIFIED_CONTROLLER).accept(MediaType.APPLICATION_JSON))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	
	private List<MeepResource> createMockResourcesArray() {
		List<MeepResource> resources = new ArrayList<>();
		MeepResource firstResource = UtilsSupport.createFirstMockMeepResource();
		resources.add(firstResource);
		MeepResource secondResource = UtilsSupport.createSecondMockMeepResource();
		resources.add(secondResource);
		return resources;
	}
	
}
