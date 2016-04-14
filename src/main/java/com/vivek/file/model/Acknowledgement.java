package com.vivek.file.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Acknowledgement {
	
	private Data data;
	private List<Link> links;
	private List<Warning> warnings;
	
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	public List<Link> getLinks() {
		return links;
	}
	public void setLinks(List<Link> links) {
		this.links = links;
	}
	public List<Warning> getWarnings() {
		return warnings;
	}
	public void setWarnings(List<Warning> warnings) {
		this.warnings = warnings;
	}
	
}
