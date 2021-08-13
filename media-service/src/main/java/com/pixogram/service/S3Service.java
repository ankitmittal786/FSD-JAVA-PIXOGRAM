package com.pixogram.service;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface S3Service {

	public ByteArrayOutputStream downloadFile(String keyName);
	public String uploadFile(String keyName, MultipartFile file);
//	public List listFiles();
}
