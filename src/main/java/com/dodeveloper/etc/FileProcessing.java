package com.dodeveloper.etc;

import java.io.File;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileProcessing {

	private String realPath;

	@Autowired
	public FileProcessing(ServletContext servletContext) {
		realPath = servletContext.getRealPath("");
		File realUploadPath = new File(realPath + File.separator + UploadPaths.realUploadPath);
		File tempUploadPath = new File(realPath + File.separator + UploadPaths.tempUploadPath);
		realUploadPath.mkdir();
		tempUploadPath.mkdir();
	}

	public String getRealPath() {
		return realPath;
	}
	
	public String saveTempFilePermanantly(String fileName) throws Exception {
		return moveFile(fileName, realPath + File.separator + UploadPaths.tempUploadPath,
				realPath + File.separator + UploadPaths.realUploadPath);
	}

	public String uploadFileTemporarily(MultipartFile file) throws Exception {
		return fileUpload(file, realPath + File.separator + UploadPaths.tempUploadPath);
	}

	public String moveFile(String fileName, String from, String dest) throws Exception {

		File oldFile = new File(from + File.separator + fileName);
		if (!oldFile.exists()) {
			throw new Exception("Such file doesn't exist");
		}

		String movedFileName = createUniqueName(fileName, dest);
		File newFile = new File(dest + File.separator + movedFileName);

		FileUtils.moveFile(oldFile, newFile);

		return movedFileName;
	}

	public String fileUpload(MultipartFile file, String uploadPath) throws Exception {

		String originalName = file.getOriginalFilename();
		String uploadName = createUniqueName(modifyFileName(originalName), uploadPath);

		File saveFile = new File(uploadPath + File.separator + uploadName);
		file.transferTo(saveFile);

		return uploadName;
	}

	private String modifyFileName(String fileName) {
		String uuid = UUID.randomUUID().toString().replace("-", "");

		return uuid + "_" + fileName;
	}

	private String createUniqueName(String fileName, String filePath) throws Exception {
		if ((new File(filePath + File.separator + fileName).exists()) == false) {
			return fileName;
		}

		String ext = getExtension(fileName);
		String nameWithoutExt = getNameWithoutExt(fileName);

		for (int i = 0; i <= 999; i++) {
			String newName = nameWithoutExt + "_" + i + "." + ext;
			if ((new File(filePath + File.separator + newName)).exists() == false) {
				return newName;
			}
		}

		throw new Exception("Failed to create unique File name");
	}

	public String getExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}

	public String getNameWithoutExt(String fileName) {
		return fileName.substring(0, fileName.lastIndexOf("."));
	}
	
	public String getOriginalName(String modifiedName) {
		return modifiedName.substring(modifiedName.indexOf("_") + 1);
	}
}
