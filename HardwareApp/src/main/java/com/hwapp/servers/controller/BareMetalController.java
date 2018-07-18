package com.hwapp.servers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hwapp.servers.component.BareMetalTransporter;
import com.hwapp.servers.model.BareMetal;
import com.hwapp.servers.repository.BareMetalRepository;

@Controller
public class BareMetalController {
	
	@Autowired
	BareMetalRepository metalRepository;
	
	@Autowired
	BareMetalTransporter transporter;
	
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
	
	@PostMapping("/create")
	public ModelAndView createOne(BareMetal metal, ModelAndView mav) {
		metalRepository.save(metal);
		mav.addObject("instance", metalRepository.findById(metal.getId()));
		mav.addObject("success", "Created/Modified successfully");
		mav.setViewName("create-bm");
		return mav;
	}

}
