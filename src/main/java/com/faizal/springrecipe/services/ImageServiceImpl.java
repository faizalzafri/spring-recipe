package com.faizal.springrecipe.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements ImageService {

	@Override
	public void saveImageFile(Long valueOf, MultipartFile file) {
		// TODO Auto-generated method stub
		System.out.println("Recieved a file");
	}

}
