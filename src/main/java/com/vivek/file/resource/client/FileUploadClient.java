package com.vivek.file.resource.client;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import com.google.common.io.BaseEncoding;
import com.vivek.file.model.Acknowledgement;
import com.vivek.file.model.FileUploadBO;

public class FileUploadClient {
	
	public static void main(String[] args) {
		
		FileUploadClient client = new FileUploadClient();
        File file_upload = new File("F:\\test.pdf");
        try {
			client.sendFileJSON(file_upload);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }


    private void sendFileJSON(File file_upload) throws IOException{

    	Client client = ClientBuilder.newClient();
    	
    	WebTarget baseTarget = client.target("http://localhost:8081/advanced-jaxrs/webapi/");
		WebTarget fileTarget = baseTarget.path("file/upload?persistFile=true");
    	
        FileUploadBO fileBo = new FileUploadBO();
        fileBo.setContent(encodeFileToString(file_upload));
        fileBo.setContentType("application/pdf");

        Response postResponse = fileTarget
									.request(MediaType.APPLICATION_JSON)
									.post(Entity.json(fileBo));

        System.out.println("Response from File Upload");
        System.out.println(postResponse.getStatus());
        
        ObjectMapper mapper = new ObjectMapper();
        Acknowledgement ack = postResponse.readEntity(Acknowledgement.class);
        System.out.println(mapper.writeValueAsString(ack));

    }


    //Convert my file to a Base64 String
    private String encodeFileToString(File file) throws IOException{
        byte[] bytes = Files.readAllBytes(file.toPath());
        System.out.println(file.getName());
        System.out.println(bytes.length);
        
        return BaseEncoding.base64().encode(bytes);
        
//        return new String(Base64.encode(bytes));
    }
	

}
