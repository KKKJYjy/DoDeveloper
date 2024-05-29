package com.dodeveloper.etc;

import java.io.File;
import java.io.FileFilter;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileProcessing {

    private String realPath;
    private String realPermaUploadPath;
    private String realTempUploadPath;

    @Autowired
    public FileProcessing(ServletContext servletContext) {
	realPath = servletContext.getRealPath("");
	realPermaUploadPath = realPath + UploadPaths.permanentUploadPath;
	realTempUploadPath = realPath + UploadPaths.tempUploadPath;

	System.out.println(realPermaUploadPath);
	File permaUploadDir = new File(realPermaUploadPath);
	File tempUploadDir = new File(realTempUploadPath);
	permaUploadDir.mkdir();
	tempUploadDir.mkdir();
    }

    public String getRealPath() {
	return realPath;
    }

    public String getPermaUploadPath() {
	return realPermaUploadPath;
    }

    public String getTempUploadPath() {
	return realTempUploadPath;
    }

    public String saveTempFilePermanantly(String fileName) throws Exception {
	return moveFile(fileName, realPath + File.separator + UploadPaths.tempUploadPath,
		realPath + File.separator + UploadPaths.permanentUploadPath);
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

	String ext = getExtension(fileName);
	String nameWithoutExt = getNameWithoutExt(fileName);

	File downloadDir = new File(filePath);
	FileFilter fileFilter = new WildcardFileFilter(nameWithoutExt + ".*");
	File[] sameNamedFiles = downloadDir.listFiles(fileFilter);
	File sameNamedFileButHasNoExt = new File(filePath + File.separator + nameWithoutExt);

	if (sameNamedFiles.length <= 0 && !sameNamedFileButHasNoExt.exists()) {
	    return fileName;
	}

	for (int i = 0; i <= 999; i++) {
	    String newNameWithoutExt = nameWithoutExt + "_" + i;

	    fileFilter = new WildcardFileFilter(newNameWithoutExt + ".*");
	    sameNamedFiles = downloadDir.listFiles(fileFilter);
	    sameNamedFileButHasNoExt = new File(filePath + File.separator + newNameWithoutExt);

	    if (sameNamedFiles.length <= 0 && !sameNamedFileButHasNoExt.exists()) {
		return newNameWithoutExt + "." + ext;
	    }
	}

	throw new Exception("Failed to create unique File name");
    }

    public String getExtension(String fileName) {
	if (fileName.lastIndexOf(".") == -1) {
	    return "";
	}
	return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public String getNameWithoutExt(String fileName) {
	if (fileName.lastIndexOf(".") == -1) {
	    return fileName;
	}
	return fileName.substring(0, fileName.lastIndexOf("."));
    }

    public String getOriginalName(String modifiedName) {
	return modifiedName.substring(modifiedName.indexOf("_") + 1);
    }
}
