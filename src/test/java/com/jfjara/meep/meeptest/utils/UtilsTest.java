package com.jfjara.meep.meeptest.utils;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.jfjara.meep.meeptest.exceptions.MeepException;

@RunWith(MockitoJUnitRunner.class)
public class UtilsTest {

	@Mock
	private Utils utils;
	
	@InjectMocks
	private Utils utilsInjected;
	
	@Test(expected = MeepException.class)
	public void testCreateIncorrectURIOne() throws URISyntaxException, MeepException {						
		Mockito.when(utils.createURI(Mockito.anyString(), Mockito.anyMap())).thenThrow(new MeepException(UtilsSupport.EMPTY));
		utils.createURI(UtilsSupport.HTTP_LOCALHOST_8080, new HashMap<String, String>());
	}
	
	@Test(expected = MeepException.class)
	public void testCreateIncorrectURITwo() throws URISyntaxException, MeepException {						
		Mockito.when(utils.createURI(Mockito.anyString(), Mockito.anyMap())).thenThrow(new MeepException(UtilsSupport.EMPTY));
		utils.createURI(UtilsSupport.EMPTY, createDummyMap());
	}
	
	@Test(expected = MeepException.class)
	public void testCreateIncorrectURIThree() throws URISyntaxException, MeepException {						
		Mockito.when(utils.createURI(Mockito.anyString(), Mockito.anyMap())).thenThrow(new MeepException(UtilsSupport.EMPTY));
		utils.createURI(UtilsSupport.EMPTY, new HashMap<String, String>());
	}
	
	@Test(expected = MeepException.class)
	public void testCreateIncorrectURIFour() throws URISyntaxException, MeepException {						
		Mockito.when(utils.createURI(Mockito.isNull(), Mockito.anyMap())).thenThrow(new MeepException(UtilsSupport.EMPTY));
		utils.createURI(null, new HashMap<String, String>());
	}
	
	@Test(expected = MeepException.class)
	public void testCreateIncorrectURIFive() throws URISyntaxException, MeepException {						
		Mockito.when(utils.createURI(Mockito.anyString(), Mockito.isNull())).thenThrow(new MeepException(UtilsSupport.EMPTY));
		utils.createURI(UtilsSupport.EMPTY, null);
	}
	
	@Test(expected = MeepException.class)
	public void testCreateIncorrectURISix() throws URISyntaxException, MeepException {						
		Mockito.when(utils.createURI(Mockito.isNull(), Mockito.isNull())).thenThrow(new MeepException(UtilsSupport.EMPTY));
		utils.createURI(null, null);
	}
	
	@Test
	public void createHttpEntity() {
		utilsInjected.createHttpEntity();
	}	
	
	
	@Test
	public void testCreateCorrectJsonHeader() {
		utilsInjected.createJsonHeader();
	}	

	@Test
	public void testCreateURI() throws URISyntaxException, MeepException {
		utilsInjected.createURI(UtilsSupport.HTTP_LOCALHOST_8080, createDummyMap());				
	}

	private Map<String, String> createDummyMap() {
		Map<String, String> dummy = new HashMap<String, String>();
		dummy.put(UtilsSupport.ONE, UtilsSupport.ONE);
		dummy.put(UtilsSupport.TWO, UtilsSupport.TWO);
		return dummy;
	}
	
	
}
