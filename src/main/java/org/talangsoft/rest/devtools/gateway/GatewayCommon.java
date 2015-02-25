package org.talangsoft.rest.devtools.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.talangsoft.rest.devtools.logging.Loggable;

import java.util.Collections;
import java.util.List;

/**
 * Created by tamaslang on 24/11/14.
 */
public abstract class GatewayCommon implements Loggable {

    @Autowired
    private RestTemplate restTemplate;

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public <T> T genericGetByCode(String endpoint, String code, Class<T> responseClass) {
        UriComponentsBuilder componentBuilder = UriComponentsBuilder.fromHttpUrl(endpoint);
        componentBuilder.pathSegment(code);
        ResponseEntity<T> responseEntity = null;
        try {
            logger().debug("Calling endpoint {}", componentBuilder.build().toString());
            responseEntity = getRestTemplate().getForEntity(componentBuilder.build().toString(), responseClass, Collections.emptyMap());
        } catch (RestClientException ex){
            GatewayExceptionHandler.handleRestClientException(ex, componentBuilder.build().toString(), null);
            return null;
        }
        return responseEntity.getBody();
    }

    /**
     * Deprecated use genericPost instead
     */
    @Deprecated
    public <T> T genericAddNew(String endpoint, Object payload, Class<T> responseType) {

        UriComponentsBuilder componentBuilder = UriComponentsBuilder.fromHttpUrl(endpoint);

        ResponseEntity<T> responseEntity = null;
        try {
            logger().debug("Calling POST on endpoint {}", componentBuilder.build().toString());
            responseEntity = getRestTemplate().postForEntity(componentBuilder.build().toString(), payload, responseType);
            logger().debug("Get response entity {}", responseEntity.getBody());
        } catch (RestClientException ex){
            GatewayExceptionHandler.handleRestClientException(ex, componentBuilder.build().toString(), null);
            return null;
        }
        return responseEntity.getBody();
    }

    public <T> T genericGet(String endpointUrl, Class<T> responseClass) {
        UriComponentsBuilder componentBuilder = UriComponentsBuilder.fromHttpUrl(endpointUrl);
        ResponseEntity<T> responseEntity = null;
        try {
            logger().debug("Calling GET on endpoint {}", componentBuilder.build().toString());
            responseEntity = getRestTemplate().getForEntity(componentBuilder.build().toString(), responseClass, Collections.emptyMap());
        } catch (RestClientException ex){
            GatewayExceptionHandler.handleRestClientException(ex, componentBuilder.build().toString(), null);
            return null;
        }
        return responseEntity.getBody();
    }

    public <T> T genericGetWithHeaders(String endpointUrl, HttpHeaders httpHeaders, Class<T> responseClass) {
        UriComponentsBuilder componentBuilder = UriComponentsBuilder.fromHttpUrl(endpointUrl);
        ResponseEntity<T> responseEntity = null;
        HttpEntity entityWithHeaders = new HttpEntity<>(httpHeaders);
        try {
            logger().debug("Calling GET on endpoint {}", componentBuilder.build().toString());
            responseEntity = getRestTemplate().exchange(componentBuilder.build().toString(),
                    HttpMethod.GET, entityWithHeaders, responseClass);
        } catch (RestClientException ex){
            GatewayExceptionHandler.handleRestClientException(ex, componentBuilder.build().toString(), null);
            return null;
        }
        return responseEntity.getBody();
    }

    public <T> T genericPost(String endpointUrl, Object payload, Class<T> responseType) {
        UriComponentsBuilder componentBuilder = UriComponentsBuilder.fromHttpUrl(endpointUrl);

        ResponseEntity<T> responseEntity = null;
        try {
            logger().debug("Calling POST on endpoint {}", componentBuilder.build().toString());
            responseEntity = getRestTemplate().postForEntity(componentBuilder.build().toString(), payload, responseType);
            logger().debug("Get response entity {}", responseEntity.getBody());
        } catch (RestClientException ex){
            GatewayExceptionHandler.handleRestClientException(ex, componentBuilder.build().toString(), null);
            return null;
        }
        return responseEntity.getBody();
    }

    public void genericPut(String endpointUrl, Object payload) {
        UriComponentsBuilder componentBuilder = UriComponentsBuilder.fromHttpUrl(endpointUrl);

        try {
            logger().debug("Calling PUT on endpoint {}", componentBuilder.build().toString());
            this.getRestTemplate().put(componentBuilder.build().toString(), payload);
        } catch (RestClientException ex){
            GatewayExceptionHandler.handleRestClientException(ex, componentBuilder.build().toString(), null);
        }
    }

    public void genericDelete(String endpointUrl) {
        UriComponentsBuilder componentBuilder = UriComponentsBuilder.fromHttpUrl(endpointUrl);

        try {
            logger().debug("Calling DELETE on endpoint {}", componentBuilder.build().toString());
            this.getRestTemplate().delete(componentBuilder.build().toString());
        } catch (RestClientException ex){
            GatewayExceptionHandler.handleRestClientException(ex, componentBuilder.build().toString(), null);
        }
    }

    /*
    usage: genericGetForList(componentBuilder.build().toString(),AuditSummarySvcDTO.class, new ParameterizedTypeReference<List<AuditSummarySvcDTO>>() {});
     */
    public <T> List<T> genericGetForList(String endpointUrl, Class<T> listElementClass, ParameterizedTypeReference typeReference) {
        return genericForAllHttpMethodForList(endpointUrl, HttpMethod.GET, null, typeReference);
    }

    /*
    usage: genericPostForList(componentBuilder.build().toString(),AuditSummarySvcDTO.class, new ParameterizedTypeReference<List<AuditSummarySvcDTO>>() {});
     */
    public <T> List<T> genericPostForList(String endpointUrl, Object payload, Class<T> listElementClass,ParameterizedTypeReference typeReference) {
        return genericForAllHttpMethodForList(endpointUrl, HttpMethod.POST, payload, typeReference);
    }

    /*
    usage: genericForAllHttpMethodForList(componentBuilder.build().toString(),HttpMethod.POST, AuditSummarySvcDTO.class, new ParameterizedTypeReference<List<AuditSummarySvcDTO>>() {});
     */
    public <T> List<T> genericForAllHttpMethodForList(String endpointUrl,HttpMethod httpMethod, Object payload, ParameterizedTypeReference typeReference) {
        UriComponentsBuilder componentBuilder = UriComponentsBuilder.fromHttpUrl(endpointUrl);

        ResponseEntity<List<T>> responseEntity = null;
        HttpEntity<Object> httpEntity = null;
        if(payload != null){
            httpEntity = new HttpEntity<>(payload);
        }
        try {
            logger().debug("Calling {} for list on endpoint {}", httpMethod, componentBuilder.build().toString());
            responseEntity = getRestTemplate().exchange(componentBuilder.build().toString(), httpMethod,
                    httpEntity, typeReference);
            logger().debug("Get response for list {}",responseEntity.getBody());
        } catch (RestClientException ex){
            GatewayExceptionHandler.handleRestClientException(ex, componentBuilder.build().toString(), null);
            return null;
        }
        return responseEntity.getBody();
    }

}