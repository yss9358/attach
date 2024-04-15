package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.vo.AttachVo;

@Service
public class AttachService {

	public String exeUpload(MultipartFile file) {
		System.out.println("exeUpload");

		// 운영 체제 이름 확인
		String osName = System.getProperty("os.name").toLowerCase();
		String saveDir;

		// 운영 체제에 따라 다른 경로 설정
		if (osName.contains("linux")) {
			System.out.println("리눅스");
			// 파일저장디렉토리
			saveDir = "/app/upload"; // Linux 경로. username을 실제 사용자 이름으로 변경하세요.
		} else {
			System.out.println("윈도우");
			// 파일저장디렉토리
			saveDir = "C:\\javaStudy\\upload";
		}

		// (1)파일관련 정보 추출///////////////////////////////////////////////////

		// 오리지널 파일명
		String orgName = file.getOriginalFilename();
		System.out.println(orgName);

		// 확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));
		System.out.println(exName);

		// 저장파일명(겹치지 않아야 된다)
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		System.out.println(saveName);

		// 파일사이즈
		long fileSize = file.getSize();
		System.out.println(fileSize);

		// 파일전체경로
		String filePath = saveDir + File.separator + saveName;
		System.out.println(filePath);

		// vo로묶기
		AttachVo attachVo = new AttachVo(orgName, saveName, filePath, fileSize);
		System.out.println(attachVo);

		// Dao만들기 --> DB저장 과제
		System.out.println(attachVo + "DB저장");

		// (2)파일저장(서버쪽 하드디스크에 저장)///////////////////////////////////////////////////
		try {
			byte[] fileData;
			fileData = file.getBytes();

			OutputStream os = new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(os);

			bos.write(fileData);
			bos.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// (3)DB저장 /////////////////////////////////////////////////////
		int count = 1;
		return saveName;
	}

}
