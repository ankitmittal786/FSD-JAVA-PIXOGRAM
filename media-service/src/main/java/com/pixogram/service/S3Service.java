package com.pixogram.service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pixogram.common.CustomException;

@Service
public interface S3Service {

	public ByteArrayOutputStream downloadFile(String keyName);
	public String uploadFile(String keyName, MultipartFile file);
//	public List listFiles();
	public boolean deleteimage(String uri);
	Map<String, String> uploadFiles(List<MultipartFile> files, String username,long id) throws CustomException;
}
