package com.dashu.lazyapidoc.annotation;

import org.springframework.web.bind.annotation.RequestMethod;

public class RequestMappingBean {

    String name = "";

    String[] value = {};

    String[] path = {};

    RequestMethod[] method = {};

    String[] params = {};

    String[] headers = {};

    String[] consumes = {};

    String[] produces = {};

    public String getName() {
        return name;
    }

    public RequestMappingBean setName(String name) {
        this.name = name;
        return this;
    }

    public String[] getValue() {
        return value;
    }

    public RequestMappingBean setValue(String[] value) {
        this.value = value;
        return this;
    }

    public String[] getPath() {
        return path;
    }

    public RequestMappingBean setPath(String[] path) {
        this.path = path;
        return this;
    }

    public RequestMethod[] getMethod() {
        return method;
    }

    public RequestMappingBean setMethod(RequestMethod[] method) {
        this.method = method;
        return this;
    }

    public String[] getParams() {
        return params;
    }

    public RequestMappingBean setParams(String[] params) {
        this.params = params;
        return this;
    }

    public String[] getHeaders() {
        return headers;
    }

    public RequestMappingBean setHeaders(String[] headers) {
        this.headers = headers;
        return this;
    }

    public String[] getConsumes() {
        return consumes;
    }

    public RequestMappingBean setConsumes(String[] consumes) {
        this.consumes = consumes;
        return this;
    }

    public String[] getProduces() {
        return produces;
    }

    public RequestMappingBean setProduces(String[] produces) {
        this.produces = produces;
        return this;
    }
}
