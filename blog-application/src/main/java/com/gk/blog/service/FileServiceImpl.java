package com.gk.blog.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String fileUpload(String path, MultipartFile file) throws IOException {

		// Get File Name
		String fileName = file.getOriginalFilename();

		// Generate Random Name
		String randomId = UUID.randomUUID().toString();
		String fileRandomName = randomId.concat(fileName.substring(fileName.lastIndexOf(".")));

		// Full Path
		// File Separator separate path by slash(/ or \ based on OS)
		String filePath = path + File.separator + fileRandomName;

		// Create Folder if not created
		File f = new File(path);
		if (!f.exists())
			f.mkdir();

		// File Copy
		Files.copy(file.getInputStream(), Paths.get(filePath));
		return fileRandomName;
	}

	@Override
	public InputStream fileDownload(String path, String fileName) throws FileNotFoundException {
		String fullPath = path + File.separator + fileName;
		InputStream stream = new FileInputStream(fullPath);
		return stream;
	}

}
