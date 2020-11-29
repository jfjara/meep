package com.jfjara.meep.meeptest.utils;

import java.util.ArrayList;
import java.util.List;

import com.jfjara.meep.meeptest.model.MeepResource;

public class UtilsSupport {
	
	public static final String TWO = "Two";
	public static final String ONE = "One";
	public static final String EMPTY = "";
	public static final String HTTP_LOCALHOST_8080 = "http://localhost:8080";
	public static final String MODIFIED_CONTROLLER = "/modified";
	public static final String NOT_AVAIL_CONTROLLER = "/notavail";
	public static final String AVAIL_CONTROLLER = "/avail";
	public static final String GET_CONTROLLER = "/get";
	
	public static List<MeepResource> generateMockResources2() {
		List<MeepResource> resources = new ArrayList<MeepResource>();
		resources.add(new MeepResource("ID-1", "Test Name 1", 1.1, 1.2, "Test Licence 1", 2, 35, 1, 1, "Test Model 1", "/test1", true, "Test Type 1", 1));
		resources.add(new MeepResource("ID-2", "Test Name Modified", 12.1, 12.2, "Test Licence 2 Modified", 2, 35, 1, 1, "Test Model 2", "/test2", true, "Test Type 2", 1));
		resources.add(new MeepResource("ID-3", "Test Name 3", 3.1, 3.2, "Test Licence 3", 2, 35, 1, 1, "Test Model 3", "/test3", true, "Test Type 3", 1));
		resources.add(new MeepResource("ID-4", "Test Name 4", 4.1, 4.2, "Test Licence 4", 2, 35, 1, 1, "Test Model 4", "/test4", true, "Test Type 4", 1));
		resources.add(new MeepResource("ID-6", "Test Name 6", 6.1, 6.2, "Test Licence 6", 2, 35, 1, 1, "Test Model 6", "/test6", true, "Test Type 6", 1));
		return resources;
	}

	public static List<MeepResource> generateMockResources1() {
		List<MeepResource> resources = new ArrayList<MeepResource>();
		resources.add(new MeepResource("ID-1", "Test Name 1", 1.1, 1.2, "Test Licence 1", 2, 35, 1, 1, "Test Model 1", "/test1", true, "Test Type 1", 1));
		resources.add(new MeepResource("ID-2", "Test Name 2", 2.1, 2.2, "Test Licence 2", 2, 35, 1, 1, "Test Model 2", "/test2", true, "Test Type 2", 1));
		resources.add(new MeepResource("ID-3", "Test Name 3", 3.1, 3.2, "Test Licence 3", 2, 35, 1, 1, "Test Model 3", "/test3", true, "Test Type 3", 1));
		resources.add(new MeepResource("ID-4", "Test Name 4", 4.1, 4.2, "Test Licence 4", 2, 35, 1, 1, "Test Model 4", "/test4", true, "Test Type 4", 1));
		resources.add(new MeepResource("ID-5", "Test Name 5", 5.1, 5.2, "Test Licence 5", 2, 35, 1, 1, "Test Model 5", "/test5", true, "Test Type 5", 1));
		return resources;
	}
	
	public static MeepResource[] generateArrayResources() {
		List<MeepResource> list = generateMockResources1();
		return list.toArray(new MeepResource[list.size()]);
	}
	
	public static MeepResource createFirstMockMeepResource() {
		return new MeepResource("ID-1", 
				"Test Name", 
				1.1, 
				1.2, 
				"Test Licence",
				2,
				35,
				1,
				1,
				"Test Model",
				"/test",
				true,
				"Test Type", 
				1);
	}
	
	public static MeepResource createSecondMockMeepResource() {
		return new MeepResource("ID-2", 
				"Test Name 2", 
				1.11, 
				1.22, 
				"Test Licence 2",
				2,
				35,
				1,
				1,
				"Test Model 2",
				"/test2",
				true,
				"Test Type 2", 
				2);
	}
	
}
