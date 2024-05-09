package com.dodeveloper.etc;

import java.io.File;
import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

	private FileProcessing fileProcessing;
	private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);
	
	@Autowired
	public Scheduler(FileProcessing fileProcessing) {
		this.fileProcessing = fileProcessing;
	}
	
	@Scheduled(cron = "0 0 3 * * *")
	public void tempFileCleaning() {
		File tempDir = new File(fileProcessing.getRealPath() + File.separator + UploadPaths.tempUploadPath);
		File[] files = tempDir.listFiles();
		long curTime = System.currentTimeMillis();
		final long singleDay = 1000 * 60 * 60 * 24;
		
		for(File file : files) {
			if(curTime - file.lastModified() > singleDay) {
				logger.info("Temp file : " + file.getName() + " is deleted");
				file.delete();
			}
		}
	}
}
