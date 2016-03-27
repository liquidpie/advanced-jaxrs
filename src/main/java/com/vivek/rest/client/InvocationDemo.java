package com.vivek.rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public class InvocationDemo {

	public static void main(String[] args) {
		
		InvocationDemo demo = new InvocationDemo();
		
		Invocation invocation = demo.prepareMessageForYear(2016);
		Response resp = invocation.invoke();
		
		System.out.println(resp);
	}
	
	
	private Invocation prepareMessageForYear(int year) {
		Client client = ClientBuilder.newClient();
		
		WebTarget target = client.target("http://localhost:8081/advanced-jaxrs/webapi/messages");
			
		return target.queryParam("year", year)
						.request(MediaType.APPLICATION_JSON)
						.buildGet();
	}

}
