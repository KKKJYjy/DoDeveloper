package com.dodeveloper.etc;

import java.io.File;
import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dodeveloper.member.dao.MemberDAO;
import com.dodeveloper.member.service.MemberService;

@Component
public class Scheduler {

	private FileProcessing fileProcessing;
	private MemberService memberService;
	private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);
	
	@Autowired
	public Scheduler(FileProcessing fileProcessing, MemberService memberService) {
		this.fileProcessing = fileProcessing;
		this.memberService = memberService;
	}
	/*
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
	
	@Scheduled(cron = "0 10 3 * * *")
	public void executeDeleteMember() {
		try {
			System.out.println("now it is " + System.currentTimeMillis());
			memberService.deleteAllDroppedMember();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
}
