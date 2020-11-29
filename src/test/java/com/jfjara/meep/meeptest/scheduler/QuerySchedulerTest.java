package com.jfjara.meep.meeptest.scheduler;

import java.util.List;
import java.util.concurrent.Future;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.jfjara.meep.meeptest.cache.MeepResourceCache;
import com.jfjara.meep.meeptest.model.MeepResource;
import com.jfjara.meep.meeptest.service.IRestService;
import com.jfjara.meep.meeptest.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class QuerySchedulerTest {
	
	@InjectMocks
	private QueryScheduler scheduler;
	
	@Mock
	private IRestService restService;
	
	@Mock
	private MeepResourceCache cache;
	
	@Mock
	private Utils utils;
	
	@Test
	public void schedulerTest() throws Exception {
		final Future<List<MeepResource>> mockedFuture = Mockito.mock(Future.class);
		Mockito.when(restService.getObjects(Mockito.any())).thenReturn(mockedFuture);
		scheduler.scheduler();
	}
	
	
}
