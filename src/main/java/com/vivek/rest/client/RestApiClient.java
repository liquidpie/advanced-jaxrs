package com.vivek.rest.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.vivek.rest.jersey.messenger.model.Message;

public class RestApiClient {
	
	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		
		Message message = client.target("http://localhost:8081/advanced-jaxrs/webapi/messages/2")
				.request(MediaType.APPLICATION_JSON)
				.get(Message.class);
		
//		Message message = (Message) response.readEntity(Message.class);
		System.out.println(message.getMessage());

		// ------------------------------------------------------------------------------------
		
		String str = client.target("http://localhost:8081/advanced-jaxrs/webapi/messages/2")
				.request(MediaType.APPLICATION_JSON)
				.get(String.class);
		
		System.out.println(str);
		
		// ------------------------------------------------------------------------------------
		
		WebTarget baseTarget = client.target("http://localhost:8081/advanced-jaxrs/webapi/");
		WebTarget messagesTarget = baseTarget.path("messages");
		WebTarget singleMessageTarget = messagesTarget.path("{messageId}");
		
		message = singleMessageTarget
								.resolveTemplate("messageId", "2")
								.request(MediaType.APPLICATION_XML)
								.get(Message.class);
		
		System.out.println(message.getAuthor());
		
		// -----------------------------------------------------------------------------------
		// POST Request
		
		Message newMessage = new Message(4, "Rest Client", "vjaiswal");
		Response postResponse = messagesTarget
										.request()
										.post(Entity.json(newMessage));
		
		if (postResponse.getStatus() == 201) {
			Message createdMessage = postResponse.readEntity(Message.class);
			System.out.println(createdMessage.getMessage());
		}
		
		// -----------------------------------------------------------------------------------
		// Generic Type
		
		List<Message> messages = messagesTarget
										.request(MediaType.APPLICATION_JSON)
										.get(new GenericType<List<Message>>() {});

		System.out.println(messages);
		
	}

}
