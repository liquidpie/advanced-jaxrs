package com.vivek.file.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FileUploadBO {
	
	private String contentType;
	private String content;
	
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
