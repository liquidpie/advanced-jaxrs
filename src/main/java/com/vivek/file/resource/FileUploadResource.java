package com.vivek.file.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.vivek.file.model.FileUploadBO;
import com.vivek.file.service.FileUploadService;
import com.vivek.file.service.FileUploadService.ServiceType;

@Path("file")
public class FileUploadResource {

	@POST
	@Path("upload")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response uploadFile(FileUploadBO fileBo, @QueryParam("persistFile") boolean persistFile, @Context UriInfo uriInfo) {
		// validate json
		// Get encoded file string
		
		FileUploadService res = null;
		if (fileBo != null && fileBo.getContent() != null) {
			if (persistFile) {
				res = new FileUploadService(fileBo, ServiceType.SAVE);
			}
			else {
				res = new FileUploadService(fileBo, ServiceType.VIEW);
			}
			return res.successfulUpload(uriInfo);
		}
		else {
			return Response.status(Status.BAD_REQUEST).entity("{\"info\": \"No File sent in request\"}").build();
		}
		
	}
	
}
