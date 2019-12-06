package com.eduardo.microservices.netflixzuulapigatewayserver;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

// This will be a filter that just logs some info from all requests that come through the API Gateway
@Component
public class ZuulFilterLogging extends ZuulFilter{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// There are four methods that need to be implemented:
	
	@Override
	public boolean shouldFilter() {
		// Should this filter be actually executed or not?
		// Here you put conditions that must be met in order to actually execute the run() method;
		// usually depends on some values from the Request. Here we just hardcoded it to true
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		// The actual action(s) to be executed
		// In this case, get the Request and just log some of its values
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		logger.info("request -> {} request URI -> {}", request, request.getRequestURI());
		return null;
	}

	@Override
	public String filterType() {
		// The filter executes "pre"viously or "post"eriorly to do anything
		// with the Request, or when the Request returns an "error"
		return "pre";
	}

	@Override
	public int filterOrder() {
		// If there's more filters like this one, you say which one executes first, second, etc.
		return 1;
	}
	
}
