package org.san.geospatial.controller;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

import org.san.geospatial.model.GeoSpatialDTO;
import org.san.geospatial.model.ResponseDTO;
import org.san.geospatial.service.ImageDowndloadService;
import org.san.geospatial.utils.FileManagementUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController {

	@Autowired
	private ImageDowndloadService imageDowndloadService;

	@RequestMapping("/test")
	public ResponseDTO test(@RequestParam(required = false) String hello) {
		return ResponseDTO.builder()
				.message("Hello " + hello)
				.build();
	}

	@RequestMapping(value = "/generate-images", method = RequestMethod.POST)
	public void testImageDownload(final HttpServletResponse response,@RequestBody GeoSpatialDTO geoSpatialDTO) {

		File downloadImageFile = imageDowndloadService.downloadImage( geoSpatialDTO);
		FileManagementUtils.downloadFile(downloadImageFile, response);
	}

}
