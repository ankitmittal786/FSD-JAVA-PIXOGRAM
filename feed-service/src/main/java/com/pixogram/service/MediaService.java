package com.pixogram.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pixogram.common.CustomException;
import com.pixogram.entity.Media;
import com.pixogram.entity.NewsFeed;

@Service
public interface MediaService {

	List<Media> uploadMedia(List<MultipartFile> files,String username,NewsFeed feed) throws CustomException;

}
