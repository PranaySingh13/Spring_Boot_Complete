package com.gk.blog.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	String fileUpload(String path, MultipartFile file) throws IOException;

	InputStream fileDownload(String path, String fileName) throws FileNotFoundException;

}
