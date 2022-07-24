package com.shopping.service;

import java.nio.file.Path;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	
	Path getPath(String folder, String filename);
	
	byte[] read(String folder, String filename);

	List<String> save(String folder, MultipartFile[] files);

	void delete(String folder, String filename);

	List<String> list(String folder);
}
