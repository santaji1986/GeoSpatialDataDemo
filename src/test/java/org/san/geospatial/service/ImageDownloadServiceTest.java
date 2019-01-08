package org.san.geospatial.service;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;
import org.san.geospatial.model.GeoSpatialDTO;

public class ImageDownloadServiceTest {
	
	private ImageDowndloadService imageDowndloadService;
	
	@Test
	public void downloadImageTest() {
	
		// prepare
		GeoSpatialDTO geoSpatialDTO = getGeoSpatialDTO();
	
		// execute
		File downloadImage = imageDowndloadService.downloadImage(geoSpatialDTO);

		// verify
		assertTrue(downloadImage.getName().equalsIgnoreCase(""));
		
	}

	private GeoSpatialDTO getGeoSpatialDTO() {
		// @formatter:off
		 return GeoSpatialDTO.builder()
				 .utmZone(33)
				 .latitudeBand("U")
				 .gridSquare("UP")
				 .date(LocalDate.of(2018, Month.AUGUST, 8))
				 .channelMap("visible")
				 .build();
		// @formatter:on

	}
}
