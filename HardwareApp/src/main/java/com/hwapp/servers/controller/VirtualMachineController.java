package com.hwapp.servers.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hwapp.servers.component.VirtualMachineCalculator;
import com.hwapp.servers.model.BareMetal;
import com.hwapp.servers.model.OperationModel;
import com.hwapp.servers.model.VirtualMachine;
import com.hwapp.servers.repository.BareMetalRepository;
import com.hwapp.servers.repository.VirtualMachineRepository;

@Controller
public class VirtualMachineController {

	@Autowired
	BareMetalRepository metalRepository;
	
	@Autowired
	VirtualMachineRepository vmRepository;
	
	@Autowired
	VirtualMachineCalculator calculator;
	
	@GetMapping("/listvm")
	public ModelAndView listAll() {
		ModelAndView mav = new ModelAndView("list-vm");
		mav.addObject("objList", vmRepository.findAll());
		return mav;
	}
	
	@GetMapping("/createvm")
	public ModelAndView create() {
		ModelAndView mav = new ModelAndView("create-vm");
		mav.addObject("instance", new VirtualMachine());
		mav.addObject("combo", metalRepository.findAll());
		return mav;
	}
	
	@GetMapping("/createvm/{id}")
	public ModelAndView edit(@PathVariable String id) {
		ModelAndView mav = new ModelAndView("create-vm");
		mav.addObject("instance", vmRepository.findById(id).get());
		mav.addObject("combo", metalRepository.findAll());
		return mav;
	}
	
	@PostMapping("/createvm")
	public ModelAndView createOne(@Valid VirtualMachine vm, BindingResult brVm, ModelAndView mav) {
		OperationModel op = new OperationModel(brVm);
		if(op.isSucess()) {
			BareMetal metal = metalRepository.findById(vm.getBareMetal().getId()).orElse(vm.getBareMetal());
			op = calculator.doesItFit(metal, vm);
		}
		if(op.isSucess()) {
			vmRepository.save(vm);
			mav.addObject("combo", metalRepository.findAll());
			mav.addObject("instance", vmRepository.findById(vm.getId()).get());
			mav.addObject("success", "Created/Modified successfully");
		}else {
			mav.addObject("instance", vm);
			mav.addObject("critical", op.getMessage());
			mav.addObject("combo", metalRepository.findAll());
		}
		mav.setViewName("create-vm");
		return mav;
	}

}