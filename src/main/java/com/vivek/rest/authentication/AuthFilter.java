package com.vivek.rest.authentication;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

@Provider
public class AuthFilter implements ContainerRequestFilter {

	private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic";
	private static final String SECURED_URI_PREFIX = "secured";
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if (requestContext.getUriInfo().getPath().contains(SECURED_URI_PREFIX)) {
			List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
			if (authHeader != null && authHeader.size() > 0) {
				String authToken = authHeader.get(0);
				authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
				System.out.println(authToken);
				String decodedString = Base64.decodeAsString(authToken);
				System.out.println(decodedString);
				StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
				System.out.println(tokenizer.countTokens());
				String username = tokenizer.nextToken();
				String password = tokenizer.nextToken();
				
				if ("user".equals(username) && "password".equals(password)) {
					return;
				}
			}
			
			Response unauthResponse = Response
											.status(Status.UNAUTHORIZED)
											.entity("User is not allowed to access resource")
											.build();
			
			requestContext.abortWith(unauthResponse);
											
		}
	}

}
