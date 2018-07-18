package com.hwapp.servers.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BareMetal {
	
	@Id
	protected String id;
	
	protected String hostname;
	
	protected Double cpu;
	protected Integer memory;
	
	public BareMetal() {
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public Double getCpu() {
		return cpu;
	}

	public void setCpu(Double cpu) {
		this.cpu = cpu;
	}

	public Integer getMemory() {
		return memory;
	}

	public void setMemory(Integer memory) {
		this.memory = memory;
	}

}
