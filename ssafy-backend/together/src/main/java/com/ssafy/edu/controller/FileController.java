package com.ssafy.edu.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.edu.model.Budget;
import com.ssafy.edu.model.BudgetInfo;
import com.ssafy.edu.model.BudgetList;
import com.ssafy.edu.model.BudgetListResult;
import com.ssafy.edu.service.IBudgetService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//http://localhost:8197/together/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api")
@Api(value = "SSAFY", description = "SSAFY 2020")
public class FileController {
	public static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@ApiOperation(value = "파일 업로드하기", response = List.class)
	@RequestMapping(value = "/upload/{review_num}", method = RequestMethod.POST)
	public List<String> uploadFiles(@RequestPart List<MultipartFile> files) throws Exception {
		logger.info("1-------------getBudgetList-----------------------------" + new Date());
		
		List<String> list = new ArrayList<>();
		for (MultipartFile file : files) {
			String originalfileName = file.getOriginalFilename();
			File dest = new File("C:\\Users\\multicampus\\Desktop\\s02p13b109\\ssafy-backend\\together\\UploadFile" + originalfileName);
			file.transferTo(dest);
		}
		return list;
	}
}
