package com.shopping.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shopping.service.FileService;

@Service
public class FileServiceImpl implements FileService{

	@Autowired
	ServletContext app;
	
	@Override
	public Path getPath(String folder, String filename) {
		String realPath = app.getRealPath("/files");
//		File dir = Paths.get(app.getRealPath("/"),folder).toFile();
		if (!Files.exists(Path.of(realPath))) {
			try {
				Files.createDirectory(Path.of(realPath));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(realPath);
		return null;
	}

	@Override
	public byte[] read(String folder, String filename) {
		Path path = this.getPath(folder, filename);
		try {
			return Files.readAllBytes(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<String> save(String folder, MultipartFile[] files) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String folder, String filename) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> list(String folder) {
		List<String> filenames = new ArrayList<String>();
		File dir = Paths.get(app.getRealPath("/files/"),folder).toFile();
		if (dir.exists()) {
			File[] files = dir.listFiles();
			for (File file : files) {
				filenames.add(file.getName());
			}
		}
		System.out.println(dir);
		return filenames;
	}
	
}
