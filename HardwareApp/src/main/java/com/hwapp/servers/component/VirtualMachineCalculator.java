package com.hwapp.servers.component;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hwapp.servers.model.BareMetal;
import com.hwapp.servers.model.OperationModel;
import com.hwapp.servers.model.VirtualMachine;
import com.hwapp.servers.repository.VirtualMachineRepository;

@Component
public class VirtualMachineCalculator {
	
	@Autowired
	private VirtualMachineRepository vmRepository;

	private OperationModel doesItFitMemory(BareMetal metal, VirtualMachine vm) {
		//SUM ALL MEMORY FROM VMS WITHIN THIS METAL - EXCLUDING ITSELF IF EXISTS
		Integer actualMemory = getActualMemory(metal, vm);
		//TEST TO CONFIRM THAT VM FITS REMAINING MEMORY
		if(vm.getMemory() <= actualMemory) return new OperationModel(true);
		return new OperationModel(false,"Not enough Memory");
	}
	
	private VirtualMachine getNullVm() {
		VirtualMachine vm = new VirtualMachine();
		vm.setId("0");
		return vm;
	}
	
	public Integer getActualMemory(BareMetal metal, VirtualMachine vm) {
		if(vm == null) vm = this.getNullVm();
		ArrayList<VirtualMachine> list = vmRepository.findByBareMetal(metal);
		Integer actualMemory = metal.getMemory();
		for(VirtualMachine virtual : list) {
			if(virtual.getId().equals(vm.getId()))continue;
			actualMemory -= virtual.getMemory();
		}
		return actualMemory;
	}
	
	private OperationModel doesItFitCpu(BareMetal metal, VirtualMachine vm) {
		//SUM ALL CPU FROM VMS WITHIN THIS METAL - EXCLUDING ITSELF IF EXISTS
		Double actualCpu = getActualCpu(metal, vm);
		//TEST TO CONFIRM THAY VM FITS REMAINING CPU
		if(vm.getCpu() <= actualCpu) return new OperationModel(true);
		return new OperationModel(false,"Not enough CPU");
	}
	
	public Double getActualCpu(BareMetal metal, VirtualMachine vm) {
		if(vm == null) vm = this.getNullVm();
		ArrayList<VirtualMachine> list = vmRepository.findByBareMetal(metal);
		Double actualCpu = metal.getCpu();
		for(VirtualMachine virtual : list) {
			if(virtual.getId().equals(vm.getId()))continue;
			actualCpu -= virtual.getCpu();
		}
		return actualCpu;
	}
	
	public OperationModel doesItFit(BareMetal metal, VirtualMachine vm) {
		OperationModel op = doesItFitCpu(metal, vm);
		if(op.isSucess()) op = doesItFitMemory(metal, vm);
		return op;
	}
	
}
