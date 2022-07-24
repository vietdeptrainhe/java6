package com.shopping.rest.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.service.impl.FileServiceImpl;

@CrossOrigin("*")
@RestController
@RequestMapping("/files")
public class FileManagerRestController {
	
	@Autowired
	ServletContext app;
	
	@Autowired
	FileServiceImpl fileServiceImpl;
	
	@GetMapping("/fffffff/{folder}/{file}")
	public ResponseEntity<byte[]> getImage(@PathVariable("folder") String folder, @PathVariable("file") String file) {
		return ResponseEntity.ok(fileServiceImpl.read(folder, file));	
	}
	
	@GetMapping("/fffffff/{file}")
	public ResponseEntity<byte[]> getImage2(@PathVariable("file") String file) {
		Path path = Paths.get("images/"+file);
		try {
			return ResponseEntity.ok(Files.readAllBytes(path));
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return null;
	}
}
