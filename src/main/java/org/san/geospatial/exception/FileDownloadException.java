package org.san.geospatial.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class FileDownloadException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public FileDownloadException(String message) {
		super(message);
	}
}
