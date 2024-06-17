package com.aleandro.financial.security.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.aleandro.financial.UserSec.infra.models.UserSecModel;
import com.aleandro.financial.UserSec.services.UserSecServices;
import com.aleandro.financial.security.jwt.service.JwtService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private JwtService jwtservice;
	@Autowired
	private UserSecServices user_details_service;

	public JwtAuthenticationFilter() {
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
			final String authHeader = request.getHeader("Authorization");
			if (authHeader == null || !authHeader.startsWith("Bearer ")) {
				filterChain.doFilter(request, response);
				return;
			}
			String jwt = authHeader.replace("Bearer ", "");

			
			try {jwtservice.extractUserName(jwt);}catch (Exception e) {
					response.sendError(401, "User must be authenticated");
					return;
			}
			final String user_name = jwtservice.extractUserName(jwt);
			if (user_name != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserSecModel user = this.user_details_service.loadUserByUsername(user_name);
				if (jwtservice.isTokenValid(jwt, user)) {
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null,
							user.getAuthorities());
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					
					request.setAttribute("user_id_that_requested", user.getId());
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}

			}
			
		

		filterChain.doFilter(request, response);

	}

}
