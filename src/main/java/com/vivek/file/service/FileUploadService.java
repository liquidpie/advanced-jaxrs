package com.vivek.file.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.google.common.io.BaseEncoding;
import com.vivek.file.model.Acknowledgement;
import com.vivek.file.model.Data;
import com.vivek.file.model.FileUploadBO;
import com.vivek.file.model.Link;
import com.vivek.file.model.Warning;

public class FileUploadService {

	public enum ServiceType {VIEW, SAVE};

	private ServiceType type;
	private String encodedFile;

	public FileUploadService(FileUploadBO fileBo, ServiceType type) {
		this.encodedFile = fileBo.getContent();
		this.type = type;
	}

	public Response successfulUpload(UriInfo uriInfo) {
		Acknowledgement ack = new Acknowledgement();
		Data data = new Data();
		List<Warning> warnings = new ArrayList<Warning>();
		Warning warning = null;
		List<Link> links = new ArrayList<Link>();
		Link link;
		link = new Link();
		data.setId(UUID.randomUUID().toString());
		if (type.equals(ServiceType.SAVE)) {
			try {
				data.setStatus("File Saved - " + contentIdentifier());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			link.setLink(uriInfo.getPath() + "/file/" + data.getId());
			link.setRel("file");
			links.add(link);
		}
		else {
			warning = new Warning();
			warning.setCode("00402");
			warning.setTitle("No Persist File flag");
			warning.setDetail("Persist File Flag is not found");
			data.setStatus("File Not Saved");
		}

		link = new Link();
		link.setLink(uriInfo.getPath() + "/" + data.getId());
		link.setRel("self");
		links.add(link);

		warnings.add(warning);

		ack.setData(data);
		ack.setLinks(links);
		ack.setWarnings(warnings);

		return Response.accepted().entity(ack).build();

	}

	private String contentIdentifier() throws FileNotFoundException {
		byte[] bytes = decodeContent();
		File file = convertFile(bytes);

		String contentType = "no type found";
		
		Scanner input = new Scanner(new FileReader(file));
	    while (input.hasNextLine()) {
	        final String checkline = input.nextLine();
	        if(checkline.contains("%PDF-")) { 
	        	contentType = "application/pdf";
	        }  
	    }

		return contentType;
	}

	private byte[] decodeContent() {
		return BaseEncoding.base64().decode(encodedFile);
	}

	private File convertFile(byte[] bytes) {
		File file = new File("F:\\decoded");
		FileOutputStream fop;
		try {
			fop = new FileOutputStream(file);
			fop.write(bytes);
			fop.flush();
			fop.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return file;
	}

}
