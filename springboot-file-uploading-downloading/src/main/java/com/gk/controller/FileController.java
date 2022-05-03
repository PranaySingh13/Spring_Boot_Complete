package com.gk.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gk.payload.FileResponse;
import com.gk.service.FileService;

@RestController
@RequestMapping("/file")
public class FileController {

	@Autowired
	private FileService fileService;

	@Value("${project.files}")
	private String path;

	@PostMapping("/upload")
	public ResponseEntity<FileResponse> fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
		String fileName = fileService.fileUpload(path, file);
		return new ResponseEntity<FileResponse>(new FileResponse(fileName, "File is Successfully Uploaded"),
				HttpStatus.OK);
	}

	@GetMapping(value = "/download/{fileName}", produces = MediaType.ALL_VALUE)
	public void fileDownload(@PathVariable String fileName, HttpServletResponse response) throws IOException {
		InputStream resource = fileService.fileDownload(path, fileName);
		response.setContentType(MediaType.ALL_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
}
