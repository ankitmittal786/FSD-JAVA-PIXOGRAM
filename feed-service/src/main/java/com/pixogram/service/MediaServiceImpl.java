package com.pixogram.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pixogram.client.MediaClient;
import com.pixogram.common.CustomException;
import com.pixogram.entity.Media;
import com.pixogram.entity.NewsFeed;
import com.pixogram.repository.MediaRepository;



@Service
public class MediaServiceImpl implements MediaService {
	
	@Autowired
	private MediaClient mediaClient;
	
	@Autowired
	private MediaRepository mediaRepository;	
	
	

	@Override
	public List<Media> uploadMedia(List<MultipartFile> files,String username,NewsFeed feed) throws CustomException {
		Map<String,String> map= mediaClient.uploadMedia(files,username,feed.getId());
		List<Media> media=new ArrayList<>();
		if(map!=null) {
			for ( Entry<String, String> set : map.entrySet()) {
				media.add(new Media(set.getKey(),set.getValue(),feed));
			}
			return mediaRepository.saveAll(media);
//			return media;
		}
		throw new CustomException("RECORD_NOT_FOUND");
	}

}
