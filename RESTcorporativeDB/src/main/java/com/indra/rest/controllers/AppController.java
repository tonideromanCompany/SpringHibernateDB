package com.indra.rest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {
	@RequestMapping("/app")
	public String app(Model model) {
		return "index";
	}
}
