package com.faizal.springrecipe.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

	void save(Long valueOf, MultipartFile file);

}
