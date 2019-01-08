package org.san.geospatial.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.san.geospatial.exception.FileDownloadException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileManagementUtils {

	public static void downloadFile(File downloadImageFile, HttpServletResponse response) throws FileDownloadException {
		String errorMessage = "Error downloading file from server.";
		if (null == downloadImageFile) {
			throw new FileDownloadException(errorMessage);
		}else {
			try (InputStream fileInputStream = new FileInputStream(downloadImageFile);
					OutputStream output = response.getOutputStream();) {
				response.reset();
				response.setContentType("image/jpeg");
				response.setContentLength((int) downloadImageFile.length());
				response.setHeader("Content-Disposition",
						"attachment; filename=\"" + downloadImageFile.getName() + "\"");
				IOUtils.copyLarge(fileInputStream, output);
				output.flush();

			} catch (final IOException e) {
				log.error(errorMessage, e);
				throw new FileDownloadException(errorMessage);
			}
		}
	}

}
