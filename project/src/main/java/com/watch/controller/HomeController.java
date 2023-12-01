package com.watch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.watch.models.Category;
import com.watch.models.Product;
import com.watch.service.CategoryService;
import com.watch.service.ProductService;

@Controller
public class HomeController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;
	@RequestMapping("")
	public String Home(Model model) {
		List<Category> categories = categoryService.getAllByStatus();
		model.addAttribute("listCate", categories);
		List<Product> products = productService.getAll();
		model.addAttribute("productAll", products);
		return "index";
	}
	@RequestMapping("/Wommen")
	public String Wommen(Model model) {
		List<Product> products = productService.getAll();
		model.addAttribute("productAll", products);
		return "Wommen/index";
	}
	@RequestMapping("/Men")
	public String Men(Model model) {
		List<Product> products = productService.getAll();
		model.addAttribute("productAll", products);
		return "Men/index";
	}
}
