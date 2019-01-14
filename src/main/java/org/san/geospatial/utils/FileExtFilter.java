package org.san.geospatial.utils;

import java.io.File;
import java.io.FileFilter;

public class FileExtFilter implements FileFilter {
	private String[] fileExts;

	public FileExtFilter(String[] fileExts) {
		this.fileExts = fileExts;
	}

	@Override
	public boolean accept(File pathname) {
		for (String fileExt : fileExts) {
			if (pathname.getName().endsWith(fileExt))
				return true;
		}
		return false;
	}
}
