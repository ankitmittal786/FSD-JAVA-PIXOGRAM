package com.pixogram.config;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

@Component
public class AWSS3Config {

	    @Autowired
	    private AmazonS3 aws3Client;
	    
	    @Value("${s3.bucket}")
		private String bucketName;
	    
	    @Value("${s3.endpointUrl}")
	    private String endpointUrl;


	    public void createBucket(){
	        // check is bucket already exists
	        if(!aws3Client.doesBucketExistV2(bucketName)){
	            aws3Client.createBucket(bucketName);
	        }
	    }

	    public void listBuckets(){
	        aws3Client.listBuckets().forEach(bucket -> {
	            System.out.println(bucket.getName() +"--- "+bucket.getOwner());
	        });
	    }

	    public void deleteBucket(){
	        aws3Client.deleteBucket(bucketName);
	    }

	    // upload object into s3 bucket
	    public String createObject(String keyName, InputStream inputStream, ObjectMetadata metadata){
	    	String uri=endpointUrl+"/"+bucketName+"/"+keyName;
	        aws3Client.putObject(new PutObjectRequest(bucketName, keyName, inputStream, metadata).withCannedAcl(CannedAccessControlList.PublicRead));
	        return uri;
	    }

	    public void listObjects(){
	        ObjectListing listing = aws3Client.listObjects(bucketName);
	        listing.getObjectSummaries().forEach(s3ObjectSummary -> System.out.println(s3ObjectSummary.getKey()));
	    }

	    public S3Object downloadObject(String objectName) throws IOException {
	        return  aws3Client.getObject(bucketName,objectName);
//	        S3ObjectInputStream inputStream = object.getObjectContent();
//
//	        // write stream to file
//	        FileUtils.copyInputStreamToFile(inputStream,new File("hello1.txt"));
	    }
	    public void deleteObject(){

	    }

}
