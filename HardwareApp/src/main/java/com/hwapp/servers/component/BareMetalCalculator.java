package com.hwapp.servers.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hwapp.servers.model.BareMetal;
import com.hwapp.servers.model.OperationModel;

@Component
public class BareMetalCalculator {
	
	@Autowired
	VirtualMachineCalculator calculator;
	
	private boolean isCpuResizePossible(BareMetal metal, Double from , Double to) {
		if(to>=from)return true;
		Double actual = calculator.getActualCpu(metal, null);
		System.out.println("C: " + actual + " " + from + " " + to);
		if(actual<(from-to))return false;
		return true;
	}
	
	private boolean isMemoryResizePossible(BareMetal metal, Integer from, Integer to) {
		if(to>=from) return true;
		Integer actual = calculator.getActualMemory(metal, null);
		System.out.println("M: " + actual + " " + from + " " + to);
		if(actual<(from-to))return false;
		return true;
	}
	
	public OperationModel isResizePossible(BareMetal from, BareMetal to) {
		boolean possible = isCpuResizePossible(from, from.getCpu(), to.getCpu());
		if(!possible) return new OperationModel(possible, "CPU Resize not possible");
		possible = isMemoryResizePossible(from, from.getMemory(), to.getMemory());
		if(!possible) return new OperationModel(possible, "Memory Resize not possible");
		return new OperationModel(true);
	}

}
