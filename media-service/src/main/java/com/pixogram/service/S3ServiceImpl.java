package com.pixogram.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.pixogram.config.AWSS3Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class S3ServiceImpl implements S3Service {
	
	private Logger logger = LoggerFactory.getLogger(S3ServiceImpl.class);
	
	@Autowired
	private AWSS3Config config;
	
	 @Autowired
	    private AmazonS3 aws3Client;
	
	@Override
	public ByteArrayOutputStream downloadFile(String keyName) {
		try {
            S3Object s3object = config.downloadObject( keyName);
            InputStream is = s3object.getObjectContent();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len;
            byte[] buffer = new byte[4096];
            while ((len = is.read(buffer, 0, buffer.length)) != -1) {
                baos.write(buffer, 0, len);
            }
            
            return baos;
		} catch (IOException ioe) {
			logger.error("IOException: " + ioe.getMessage());
        } catch (AmazonServiceException ase) {
        	logger.info("sCaught an AmazonServiceException from GET requests, rejected reasons:");
			logger.info("Error Message:    " + ase.getMessage());
			logger.info("HTTP Status Code: " + ase.getStatusCode());
			logger.info("AWS Error Code:   " + ase.getErrorCode());
			logger.info("Error Type:       " + ase.getErrorType());
			logger.info("Request ID:       " + ase.getRequestId());
			throw ase;
        } catch (AmazonClientException ace) {
        	logger.info("Caught an AmazonClientException: ");
            logger.info("Error Message: " + ace.getMessage());
            throw ace;
        }
		
		return null;
	}
 
	@Override
	public String uploadFile(String keyName, MultipartFile file) {
		try {
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(file.getSize());
			return config.createObject(keyName, file.getInputStream(), metadata);
		} catch(IOException ioe) {
			logger.error("IOException: " + ioe.getMessage());
		} catch (AmazonServiceException ase) {
			logger.info("Caught an AmazonServiceException from PUT requests, rejected reasons:");
			logger.info("Error Message:    " + ase.getMessage());
			logger.info("HTTP Status Code: " + ase.getStatusCode());
			logger.info("AWS Error Code:   " + ase.getErrorCode());
			logger.info("Error Type:       " + ase.getErrorType());
			logger.info("Request ID:       " + ase.getRequestId());
			throw ase;
        } catch (AmazonClientException ace) {
            logger.info("Caught an AmazonClientException: ");
            logger.info("Error Message: " + ace.getMessage());
            throw ace;
        }
		return null;
	}
	
//	public List listFiles() {
//		
//	  ListObjectsRequest listObjectsRequest = 
//              new ListObjectsRequest()
//                    .withBucketName(bucketName);
//                    //.withPrefix("test" + "/");
//		
//		List keys = new ArrayList<>();
//		
//		ObjectListing objects = s3client.listObjects(listObjectsRequest);
//		
//		while (true) {
//			List summaries = objects.getObjectSummaries();
//			if (summaries.size() < 1) {
//				break;
//			}
//			
//			for (S3ObjectSummary item : summaries) {
//	            if (!item.getKey().endsWith("/"))
//	            	keys.add(item.getKey());
//	        }
//			
//			objects = s3client.listNextBatchOfObjects(objects);
//		}
//		
//		return keys;
//	}

}
