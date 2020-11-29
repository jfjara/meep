package com.jfjara.meep.meeptest.service;

import java.net.URI;
import java.util.List;
import java.util.concurrent.Future;

import com.jfjara.meep.meeptest.model.MeepResource;

public interface IRestService {

	public Future<List<MeepResource>> getObjects(URI uri);
}
