package com.demo.hospital_management.hospitalmanagement.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.demo.hospital_management.hospitalmanagement.service.JwtUserDetailsService;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// Authorization : Bearer eyisookmz-8299snaj200wmsmkoixkzx-mzmm

		String tokenHeader = request.getHeader("Authorization");// it gives Bearer eyisookmz-8299snaj200wmsmkoixkzx-mzmm
		String usernameFromToken = null;
		String tokenWithoutBearer = null;

		if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
			tokenWithoutBearer = tokenHeader.substring(7);// it will remove bearer and return
															// eyisookmz-8299snaj200wmsmkoixkzx-mzmm
			try {
				usernameFromToken = jwtUtil.getUsernameFromToken(tokenWithoutBearer);
			} catch (Exception e) {
				System.out.println("Unable to get username from token or token expired");
			}
		} else {
			System.out.println("Bearer token not present or token is not passed");
		}

		if (usernameFromToken != null) {
			UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(usernameFromToken);
			if (jwtUtil.validateJwtToken(tokenWithoutBearer, userDetails)) {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}

}
