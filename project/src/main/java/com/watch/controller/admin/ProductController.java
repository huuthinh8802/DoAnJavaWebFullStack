package com.watch.controller.admin;

import java.util.ArrayList;
import java.util.Iterator;
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
import org.springframework.web.multipart.MultipartFile;

import com.watch.models.Category;
import com.watch.models.ImageProduct;
import com.watch.models.Product;
import com.watch.service.CategoryService;
import com.watch.service.ImageProductService;
import com.watch.service.ProductService;
import com.watch.service.StorageService;

@Controller
@RequestMapping("/admin")
public class ProductController {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private StorageService storageService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ImageProductService imageProductService;
	
	@GetMapping("/product")
	public String index(Model model,@Param("keyword") String keyword,@RequestParam(name = "pageNo",defaultValue = "1") Integer pageNo) {
		
		
		Page<Product> products = this.productService.getAll(pageNo);
		
		if(keyword != null) { 
			
			products = this.productService.searchProduct(keyword,pageNo);
			model.addAttribute("keyword", keyword); 
		}
		 
		model.addAttribute("totalPage", products.getTotalPages());
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("productAll", products);
		return "admin/product/index";
	}
	@GetMapping("/product-add")
	public String add(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		
		List<Category> listCate = this.categoryService.getAll();
		model.addAttribute("listCate", listCate);
		return "admin/product/add";
	}
	@PostMapping("/product-add")
	public String save(@ModelAttribute("product") Product product, @RequestParam("fileImage") MultipartFile file,@RequestParam("fileImages") MultipartFile[] files) {
		
		  //upload file
		this.storageService.store(file);
		String fileName = file.getOriginalFilename();
		product.setImage(fileName);
		
		//upload anh mo ta
		
		
		if(this.productService.create(product)) {
			for (MultipartFile multipartFile : files) {
				ImageProduct imageProduct = new ImageProduct();
				imageProduct.setImage(multipartFile.getOriginalFilename());
				imageProduct.setProduct(product);
				
				this.storageService.store(multipartFile);
				this.imageProductService.create(imageProduct);
			}
			return "redirect:/admin/product";
		}else {
			return "redirect:/admin/product/add";
		}
	}
	
	@GetMapping("/product-edit/{id}")
	public String edit(Model model,@PathVariable("id") Integer id) {
		
		Product product = this.productService.findById(id);
		model.addAttribute("product", product);
		List<Category> listCate = this.categoryService.getAll();
		model.addAttribute("listCate", listCate);
		List<String> imgDetail = new ArrayList<String>();
		for (ImageProduct imgPro : product.getImageProduct()) {
			imgDetail.add(imgPro.getImage());
		}
		model.addAttribute("imgDetail", imgDetail);
		return "admin/product/edit";
	}
	@PostMapping("/product-edit")
	public String update(@ModelAttribute("product") Product product,@RequestParam("fileImage") MultipartFile file,@RequestParam("fileImages") MultipartFile[] files) {
		String fileName = file.getOriginalFilename();
		boolean isEmpty = fileName == null || fileName.trim().length() == 0;
		if(!isEmpty) {
			this.storageService.store(file);
			product.setImage(fileName);
		}
		String fileName1 = files[0].getOriginalFilename();
		boolean isEmpty1 = fileName1 == null || fileName1.trim().length() == 0;
		if(!isEmpty) {
			//xoa anh cu
			this.imageProductService.deleteByProductId(product.getId());
			for (MultipartFile multipartFile : files) {
				//upload
				ImageProduct imageProduct = new ImageProduct();
				imageProduct.setImage(multipartFile.getOriginalFilename());
				imageProduct.setProduct(product);
				
				this.storageService.store(multipartFile);
				this.imageProductService.create(imageProduct);
			}
		}
		if(this.productService.update(product)) {
			return "redirect:/admin/product";
		}
		return "admin/product/edit";
	}
	@GetMapping("/product-delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		this.imageProductService.deleteByProductId(id);
		if(this.productService.delete(id)) {
			return "redirect:/admin/product";
		}else {
			return "redirect:/admin/product";
		}
	}
}
