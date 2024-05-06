package com.dodeveloper.etc;

import java.io.File;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileProcessing {
	public static String moveFile(String fileName, String from, String dest) throws Exception {

		File oldFile = new File(from + File.separator + fileName);
		if (!oldFile.exists()) {
			throw new Exception("Such file doesn't exist");
		}

		String movedFileName = createUniqueName(fileName, dest);
		File newFile = new File(dest + File.separator + movedFileName);

		FileUtils.moveFile(oldFile, newFile);
		
		return movedFileName;
	}

	public static String fileUpload(MultipartFile file, String uploadPath)
			throws Exception {
		
		String originalName = file.getOriginalFilename();
		String uploadName = createUniqueName(modifyFileName(originalName), uploadPath);

		File saveFile = new File(uploadPath + File.separator + uploadName);
		file.transferTo(saveFile);
		
		return uploadName;
	}

	private static String modifyFileName(String fileName) {
		String uuid = UUID.randomUUID().toString().replace("-", "");

		return uuid + "_" + fileName;
	}

	private static String createUniqueName(String fileName, String filePath) throws Exception {
		if ((new File(filePath + File.separator + fileName).exists()) == false) {
			return fileName;
		}

		String ext = getExtension(fileName);
		String nameWithoutExt = getNameWithoutExt(fileName);

		for (int i = 0; i < 9; i++) {
			String newName = nameWithoutExt + "_" + i + "." + ext;
			if ((new File(filePath + File.separator + newName)).exists() == false) {
				return newName;
			}
		}
		
		throw new Exception("Failed to create unique File name");
	}

	public static String getExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}

	public static String getNameWithoutExt(String fileName) {
		return fileName.substring(0, fileName.lastIndexOf("."));
	}
}
