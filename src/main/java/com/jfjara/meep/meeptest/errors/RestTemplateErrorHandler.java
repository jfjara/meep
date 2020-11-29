package com.jfjara.meep.meeptest.errors;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResponseErrorHandler;

import com.jfjara.meep.meeptest.utils.Constants;

@Component
public class RestTemplateErrorHandler implements ResponseErrorHandler {

	public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
		return (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
				|| httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
	}

	@Override
	public void handleError(ClientHttpResponse httpResponse) throws IOException {
		if (httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
			throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.SERVER_ERROR);
		} else if (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
			if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
				throw new HttpClientErrorException(HttpStatus.NOT_FOUND, Constants.ERROR_NOT_FOUND);
			}
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, Constants.ERROR_CLIENT);
			
		}

	}

}
