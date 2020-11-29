package com.jfjara.meep.meeptest.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.jfjara.meep.meeptest.exceptions.MeepException;
import com.jfjara.meep.meeptest.model.MeepResource;

public class MeepResourceCache {

	public enum ResourceTypeEnum {
		AVAIL, NOT_AVAIL, MODIFIED
	}

	private Map<String, MeepResource> cacheDisponibles;
	private Map<String, MeepResource> cacheNoDisponibles;
	private Map<String, MeepResource> cacheModificados;
	private Map<String, MeepResource> cacheAuxiliar;

	public MeepResourceCache() {
		cacheDisponibles = new HashMap<String, MeepResource>();
		cacheNoDisponibles = new HashMap<String, MeepResource>();
		cacheAuxiliar = new HashMap<String, MeepResource>();
		cacheModificados = new HashMap<String, MeepResource>();
	}
	
	public void processResources(List<MeepResource> resources) {
		if (resources != null && !resources.isEmpty()) {
			cleanModifyResourcesCache();
			if (isEmpty()) {
				add(resources);
			} else {				
				resources.stream().forEach(resource -> checkResource(resource));				
			}
		}
		flush();
	}

	public List<MeepResource> getCache(ResourceTypeEnum type) throws MeepException {
		switch (type) {
		case AVAIL:
			return cacheDisponibles.values().stream().collect(Collectors.toList());
		case NOT_AVAIL:
			return cacheNoDisponibles.values().stream().collect(Collectors.toList());
		case MODIFIED:
			return cacheModificados.values().stream().collect(Collectors.toList());
		default:
			throw new MeepException("Tipo de cache no existente");
		}
	}

	private void add(MeepResource resource) {
		if (cacheAuxiliar != null) {
			cacheAuxiliar.put(resource.getId(), resource);
			deleteAvailiable(resource.getId());
			deleteNotAvailiable(resource.getId());
		}
	}

	private void cleanModifyResourcesCache() {
		cacheModificados.clear();
	}

	private void addModifyElements(MeepResource resource) {
		if (resource != null) {
			cacheModificados.put(resource.getId(), resource);
		}

	}

	private void add(List<MeepResource> resources) {
		if (cacheAuxiliar != null && resources != null && !resources.isEmpty()) {
			cacheAuxiliar.putAll(
					resources.stream().collect(Collectors.toMap(MeepResource::getId, meepResource -> meepResource)));
		}
	}

	private MeepResource get(String id) {
		MeepResource result = null;
		if (cacheDisponibles != null) {
			result = cacheDisponibles.get(id);
		}
		return result;
	}

	private void deleteAvailiable(String id) {
		if (cacheDisponibles != null) {
			cacheDisponibles.remove(id);
		}
	}

	private void deleteNotAvailiable(String id) {
		if (cacheNoDisponibles != null) {
			cacheNoDisponibles.remove(id);
		}
	}

	private void flush() {
		cacheNoDisponibles.putAll(cacheDisponibles);
		cacheDisponibles.clear();
		cacheDisponibles.putAll(cacheAuxiliar);
		cacheAuxiliar.clear();
	}

	private boolean isEmpty() {
		return cacheDisponibles.isEmpty();
	}

	private void checkResource(MeepResource resource) {
		if (resource != null) {
			MeepResource meepResourceCached = get(resource.getId());
			if (meepResourceCached != null && !resource.equals(meepResourceCached)) {
				addModifyElements(resource);							
			}
			add(resource);
		}
	}

}
