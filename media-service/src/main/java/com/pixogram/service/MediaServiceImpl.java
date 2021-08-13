package com.pixogram.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pixogram.common.CustomException;
import com.pixogram.entity.Media;
import com.pixogram.repository.MediaRepository;



@Service
public class MediaServiceImpl implements MediaService {
	
	@Autowired
	private S3Service s3Service;
	
	@Autowired
	private MediaRepository mediaRepository;	

	@Override
	public Media uploadMedia(List<MultipartFile> files,String username) throws CustomException {
		List<String> keynames = new ArrayList<>();
		List<String> uris=new ArrayList<>();
		if (files != null && files.size() > 0) {
			Media media=null;
			for(MultipartFile file : files) {
				String keyName=username+"-"+new Date().getTime()+"-"+file.getName();
				String uri=s3Service.uploadFile(keyName, file);
				keynames.add(keyName);
				uris.add(uri);
				media=new Media(username,keynames,uris,file.getContentType(),new Date());
			}
			return mediaRepository.save(media);
		}
		throw new CustomException("RECORD_NOT_FOUND");
	}

}
