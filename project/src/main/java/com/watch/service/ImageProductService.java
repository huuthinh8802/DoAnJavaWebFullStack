package com.watch.service;


import com.watch.models.ImageProduct;

public interface ImageProductService {
	
	Boolean create(ImageProduct imageProduct);
	Boolean deleteByProductId(Integer idProduct);
}
