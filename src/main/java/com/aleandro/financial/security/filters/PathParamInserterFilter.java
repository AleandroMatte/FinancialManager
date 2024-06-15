package com.aleandro.financial.security.filters;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;


@Component
public class PathParamInserterFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String originalUri = httpRequest.getRequestURI();

		String modifiedUri = modifyUri(originalUri, (Long) request.getAttribute("user_id_that_requested"));

		HttpServletRequestWrapper wrappedRequest = new HttpServletRequestWrapper(httpRequest) {
			@Override
			public String getRequestURI() {
				return modifiedUri;
			}
		};

		chain.doFilter(wrappedRequest, response);
	}

	private String modifyUri(String originalUri,Long user_id_that_requested) {
		System.out.println("\t\n" +user_id_that_requested + "\t\n");
		return originalUri.replace(originalUri, 
				originalUri.replace("{user_id}", String.valueOf(user_id_that_requested)));
	}

}
