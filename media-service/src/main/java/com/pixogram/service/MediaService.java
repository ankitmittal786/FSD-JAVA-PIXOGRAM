package com.pixogram.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pixogram.common.CustomException;
import com.pixogram.entity.Media;

@Service
public interface MediaService {

	Media uploadMedia(List<MultipartFile> files,String username) throws CustomException;

}
