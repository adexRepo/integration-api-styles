package com.dexcode.apiserver.controller.soap;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.dexcode.apiserver.Model.CountryRepository;
import com.dexcode.apiserver.Model.GetCountryRequest;
import com.dexcode.apiserver.Model.GetCountryResponse;

@Endpoint
public class CountryEndpoint {
	private CountryRepository countryRepository;
    private static final String LOCAL_NAMESPACE_URI = "http://localhost:80802/";

    @PayloadRoot(namespace = LOCAL_NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryRepository.findCountry(request.getName()));

        return response;
    }

	@Autowired
	public CountryEndpoint(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

}