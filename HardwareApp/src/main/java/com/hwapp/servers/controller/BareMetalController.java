package com.hwapp.servers.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hwapp.servers.component.BareMetalCalculator;
import com.hwapp.servers.component.BareMetalTransporter;
import com.hwapp.servers.model.BareMetal;
import com.hwapp.servers.model.OperationModel;
import com.hwapp.servers.repository.BareMetalRepository;

@Controller
public class BareMetalController {
	
	@Autowired
	BareMetalRepository metalRepository;
	
	@Autowired
	BareMetalTransporter transporter;
	
	@Autowired
	BareMetalCalculator calculator;
	
	@GetMapping("/list")
	public ModelAndView listAll() {
		ModelAndView mav = new ModelAndView("list-bm");
		mav.addObject("objList", transporter.transportList((List<BareMetal>) metalRepository.findAll()));
		return mav;
	}
	
	@GetMapping("/create")
	public ModelAndView create() {
		ModelAndView mav = new ModelAndView("create-bm");
		mav.addObject("instance", new BareMetal());
		return mav;
	}
	
	@GetMapping("/create/{id}")
	public ModelAndView edit(@PathVariable String id) {
		ModelAndView mav = new ModelAndView("create-bm");
		mav.addObject("instance", metalRepository.findById(id).get());
		return mav;
	}
	
	@PostMapping("/create")
	public ModelAndView createOne(@Valid BareMetal metal, BindingResult brMetal, ModelAndView mav) {
		OperationModel op = new OperationModel(brMetal);
		if(op.isSucess()) {
			if(metalRepository.existsById(metal.getId())) {
				BareMetal existingMetal = metalRepository.findById(metal.getId()).get();
				op = calculator.isResizePossible(existingMetal, metal);
			}
		}
		if(op.isSucess()) {
			metalRepository.save(metal);
			mav.addObject("success", "Created/Modified successfully");
			mav.addObject("instance", metalRepository.findById(metal.getId()));
		}else {
			mav.addObject("critical", op.getMessage());
			mav.addObject("instance", metal);
		}
		mav.setViewName("create-bm");
		return mav;
	}

}
