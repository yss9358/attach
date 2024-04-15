package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.AttachService;
import com.javaex.vo.JsonResult;

@RestController
@RequestMapping(value = "/api/attach")
public class AttachController {

	@Autowired
	private AttachService attachService;
	
	@PostMapping(value = "")
	public JsonResult upload(@RequestParam MultipartFile file) {
		System.out.println("AttachController.upload()");
		
		String saveName = attachService.exeUpload(file);

		// 응답 데이터
		return JsonResult.success(saveName);

	}
}
