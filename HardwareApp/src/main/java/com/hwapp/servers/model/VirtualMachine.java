package com.hwapp.servers.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class VirtualMachine {
	
	@Id
	protected String id;
	
	protected String hostname;
	protected Integer memory;
	protected Double cpu;
	
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

	public Integer getMemory() {
		return memory;
	}

	public void setMemory(Integer memory) {
		this.memory = memory;
	}

	public Double getCpu() {
		return cpu;
	}

	public void setCpu(Double cpu) {
		this.cpu = cpu;
	}

	@OneToOne
	private BareMetal bareMetal;

	public BareMetal getBareMetal() {
		return bareMetal;
	}

	public void setBareMetal(BareMetal bareMetal) {
		this.bareMetal = bareMetal;
	}
	
	public VirtualMachine() {
		this.id = UUID.randomUUID().toString();
	}
	
	
}