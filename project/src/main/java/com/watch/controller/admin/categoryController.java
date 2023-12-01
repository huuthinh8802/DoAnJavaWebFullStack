package com.watch.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.watch.models.Category;
import com.watch.service.CategoryService;




@Controller
@RequestMapping("/admin")
public class categoryController {
	@Autowired
	private CategoryService categoryService;
	@GetMapping("/category")
	public String index(Model model,@Param("keyword") String keyword,@RequestParam(name = "pageNo",defaultValue = "1") Integer pageNo) {
		
		
		Page<Category> list = this.categoryService.getAll(pageNo);
		
		if(keyword != null) { 
			
			list = this.categoryService.searchCategory(keyword,pageNo);
			model.addAttribute("keyword", keyword); 
		}
		 
		model.addAttribute("totalPage", list.getTotalPages());
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("list", list);
		return "admin/category/index";
	}
	@GetMapping("/category-add")
	public String add(Model model){
		
		Category category = new Category();
		category.setCategoryStatus(true);
		model.addAttribute("category", category);
		
		return "admin/category/add";
	}
	@PostMapping("/category-add")
	public String save(@ModelAttribute("category") Category category) {
		if(this.categoryService.create(category)) {
			return "redirect:/admin/category";
		}else {
			return "admin/category/add";
		}
		
	}
	
	@GetMapping("/category-edit/{id}")
	public String edit(Model model,@PathVariable("id") Integer id) {
		Category category = this.categoryService.findById(id);
		model.addAttribute("category", category);
		return "admin/category/edit";
	}
	@PostMapping("/category-edit")
	public String update(@ModelAttribute("category") Category category) {
		if(this.categoryService.create(category)) {
			return "redirect:/admin/category";
		}else {
			return "admin/category/add";
		}
	}
	
	@GetMapping("/category-delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		if(this.categoryService.delete(id)) {
			return "redirect:/admin/category";
		}else {
			return "redirect:/admin/category";
		}
	}
	
	@PostMapping("/category-search/{byID}")
	public String search(Model model,@PathVariable("byID") Integer id) {
		Category category = this.categoryService.findById(id);
		model.addAttribute("category", category);
		return "admin/category/search";
	}
}
