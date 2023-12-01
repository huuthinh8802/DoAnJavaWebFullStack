package com.watch.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.watch.models.Category;
import com.watch.models.ImageProduct;
import com.watch.models.Product;
import com.watch.service.ProductService;

@Controller
public class HomeProductController {
	@Autowired
	private ProductService productService;
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable("id") Integer id,Model model) {
		Product product = this.productService.findById(id);
		model.addAttribute("product", product);
		List<String> imgDetail = new ArrayList<String>();
		for (ImageProduct imgPro : product.getImageProduct()) {
			imgDetail.add(imgPro.getImage());
		}
		model.addAttribute("imgDetail", imgDetail);
		return "detail";
	}

	/*
	 * @RequestMapping("/detail/Wommen") public String Wommen(Model model) {
	 * List<Product> products = productService.getAll();
	 * model.addAttribute("productAll", products); return "Wommen/index"; }
	 */
	@GetMapping("/Wommen")
	public String index(Model model,@Param("keyword") String keyword,@RequestParam(name = "pageNo",defaultValue = "1") Integer pageNo) {
		
		
		Page<Product> products = this.productService.getAll(pageNo);
		
		if(keyword != null) { 
			
			products = this.productService.searchProduct(keyword,pageNo);
			model.addAttribute("keyword", keyword); 
		}
		 
		model.addAttribute("totalPage", products.getTotalPages());
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("productAll", products);
		return "Wommen/index";
	}
	
}
