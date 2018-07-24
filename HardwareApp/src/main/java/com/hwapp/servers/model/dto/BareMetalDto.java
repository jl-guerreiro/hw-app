package com.hwapp.servers.model.dto;

import com.hwapp.servers.model.BareMetal;

public class BareMetalDto extends BareMetal {

	private Double actualCpu;
	
	private Integer actualMemory;
	
	public BareMetalDto() {
		
	}
	
	public BareMetalDto(BareMetal metal) {
		this.cpu = metal.getCpu();
		this.hostname = metal.getHostname();
		this.id = metal.getId();
		this.memory = metal.getMemory();
	}

	public Double getActualCpu() {
		return actualCpu;
	}

	public void setActualCpu(Double actualCpu) {
		this.actualCpu = actualCpu;
	}

	public Integer getActualMemory() {
		return actualMemory;
	}

	public void setActualMemory(Integer actualMemory) {
		this.actualMemory = actualMemory;
	}
		
}
