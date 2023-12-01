package com.watch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watch.models.ImageProduct;
import com.watch.repository.ImageProductRepository;

@Service
public class ImageProductServiceImpl implements ImageProductService{
	
	@Autowired
	private ImageProductRepository imageProductRepository;
	@Override
	public Boolean create(ImageProduct imageProduct) {
		// TODO Auto-generated method stub
		try {
			this.imageProductRepository.save(imageProduct);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	public Boolean deleteByProductId(Integer idProduct) {
		try {
			this.imageProductRepository.deleteByProductId(idProduct);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
}
