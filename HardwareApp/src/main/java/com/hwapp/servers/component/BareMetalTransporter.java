package com.hwapp.servers.component;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hwapp.servers.model.BareMetal;
import com.hwapp.servers.model.dto.BareMetalDto;

@Component
public class BareMetalTransporter {
	
	@Autowired
	private VirtualMachineCalculator calculator;
	
	/*private BareMetalDto transportOld(BareMetal metal) {
		BareMetalDto metalDto = new BareMetalDto();
		metalDto.setId(metal.getId());
		metalDto.setCpu(metal.getCpu());
		metalDto.setMemory(metal.getMemory());
		metalDto.setHostname(metal.getHostname());
		Double cpu = calculator.getActualCpu(metal, null);
		Integer memory = calculator.getActualMemory(metal, null);
		metalDto.setActualCpu(cpu);
		metalDto.setActualMemory(memory);
		return metalDto;
	}*/
	
	private BareMetalDto transport(BareMetal metal) {
		BareMetalDto metalDto = new BareMetalDto(metal);
		Double cpu = calculator.getActualCpu(metal, null);
		Integer memory = calculator.getActualMemory(metal, null);
		metalDto.setActualCpu(cpu);
		metalDto.setActualMemory(memory);
		return metalDto;
	}
	
	public ArrayList<BareMetalDto> transportList(List<BareMetal> list){
		ArrayList<BareMetalDto> bareMetalList = new ArrayList<>();
		list.stream().forEach(x->bareMetalList.add(this.transport(x)));
		return bareMetalList;
	}

}