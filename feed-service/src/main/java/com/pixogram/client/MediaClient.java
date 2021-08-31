package com.pixogram.client;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Component
//@FeignClient(name = "media-service")
@FeignClient(url = "http://localhost:8083",value = "media-service")
public interface MediaClient {
	
	@RequestMapping(method = RequestMethod.GET,value = "/media-service/health")
	public String getMessage();

	/** both consumer & producer service registered with eurka for service discovery even though
	we can use feign client with out eureka as well but with eureka it does load balancing
	becuase feign client uses ribbon internally so it will do the load-balancing for this
	 * @param id 
	**/
	
	@RequestMapping(method = RequestMethod.POST,value = "/media-service/media/uploadMedia/{username}/{id}", consumes = {"multipart/form-data"})
	public Map<String, String> uploadMedia(@RequestBody List<MultipartFile> files,@PathVariable String username,@PathVariable long id);

	@RequestMapping(method = RequestMethod.POST,value = "/media-service/media/deleteimage")
	public boolean deleteImage(@RequestParam("uri") String uri);
}

