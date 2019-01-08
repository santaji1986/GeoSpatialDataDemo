package org.san.geospatial.service;

import java.io.File;
import java.time.format.DateTimeFormatter;

import org.san.geospatial.model.GeoSpatialDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ImageDowndloadService {

	@Value("${dataDir:${user.home}/interstellar/}")
	private String dataDir;
	private static final String FILE_NAME_FORMAT = "T%d%s%s_%sT100031_B01.tif";

	public File downloadImage(GeoSpatialDTO geoSpatialDTO) {
		return getFileName(geoSpatialDTO);
	}

	private File getFileName(GeoSpatialDTO geoSpatialDTO) {
		String fileName = String.format(FILE_NAME_FORMAT, geoSpatialDTO.getUtmZone(), geoSpatialDTO.getLatitudeBand(),
				geoSpatialDTO.getGridSquare(), geoSpatialDTO.getDate().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
		log.info("ImageController.getFileName() : " + fileName);
		File file = new File(dataDir + File.separatorChar + fileName);
		return file;
	}
}
