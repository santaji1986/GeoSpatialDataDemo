package org.san.geospatial.service;

import java.io.File;
import java.time.format.DateTimeFormatter;

import org.san.geospatial.model.GeoSpatialDTO;
import org.san.geospatial.utils.ChannelMap;
import org.san.geospatial.utils.FileExtFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ImageDowndloadService {

	@Value("${dataDir:${user.home}/interstellar/}")
	private String dataDir;
	private static final String FILE_NAME_FORMAT = "T%d%s%s_%sT100031_B05.tif";

	private static final String[] VISIBLE_FILE_EXT = new String[] { "02.tif", "03.tif", "04.tif" };
	private static final String[] VEGETATION_FILE_EXT = new String[] { "05.tif", "06.tif", "07.tif" };
	private static final String[] WATER_VAPOR_FILE_EXT = new String[] { "09.tif" };

	public File downloadImage(GeoSpatialDTO geoSpatialDTO) {
		File[] files = getFiles(geoSpatialDTO);
		log.info("List of Files for ChannelMap : {} - {}", geoSpatialDTO.getChannelMap(),files);

		// TODO : Merge .tif files to create .jpeg file

		return getFileName(geoSpatialDTO);
	}

	private File getFileName(GeoSpatialDTO geoSpatialDTO) {
		String fileName = String.format(FILE_NAME_FORMAT, geoSpatialDTO.getUtmZone(), geoSpatialDTO.getLatitudeBand(),
				geoSpatialDTO.getGridSquare(), geoSpatialDTO.getDate().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
		log.info("ImageController.getFileName() : " + fileName);
		File file = new File(dataDir + File.separatorChar + fileName);
		return file;
	}

	private File[] getFiles(GeoSpatialDTO geoSpatialDTO) {
		File fileDir = new File(dataDir);
		File[] listFiles = new File[0];
		try {
			switch (ChannelMap.getEnum(geoSpatialDTO.getChannelMap())) {
			case VISIBLE:
				listFiles = fileDir.listFiles(new FileExtFilter(VISIBLE_FILE_EXT));
				break;
			case VEGETATION:
				listFiles = fileDir.listFiles(new FileExtFilter(VEGETATION_FILE_EXT));
				break;
			case WATERVAPOR:
				listFiles = fileDir.listFiles(new FileExtFilter(WATER_VAPOR_FILE_EXT));
				break;
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return listFiles;
	}
}
