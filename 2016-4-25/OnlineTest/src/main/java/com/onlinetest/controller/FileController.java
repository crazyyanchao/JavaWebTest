/**
 * 
 */
package com.onlinetest.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.onlinetest.common.FixedLengthRandom;
import com.onlinetest.common.Response;

/**
 * @author 丁鹏
 *
 */
@Controller("FileController")
public class FileController {
	private Logger logger = Logger.getLogger(FileController.class);
	
	@RequestMapping(value = "/files/uploadfile", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String uploadFile(HttpServletRequest request,HttpServletResponse reponse){
		Response resp = new Response();
		try {
			//解析器解析request的上下文
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
			String path = request.getServletContext().getRealPath("/");
			//先判断request中是否包涵multipart类型的数据，
			if (multipartResolver.isMultipart(request)) {
				// 再将request中的数据转化成multipart类型的数据
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				Iterator<String> iter = multiRequest.getFileNames();
				int i = 1;
				while (iter.hasNext()) {
					MultipartFile file = multiRequest.getFile( (String) iter.next() );
					if (file != null) {
						String OriginalFilename = file.getOriginalFilename();
						String fileType = OriginalFilename.substring(OriginalFilename.lastIndexOf("."), OriginalFilename.length());
						FixedLengthRandom flr = new FixedLengthRandom();
						String fileName = System.currentTimeMillis()+ flr.getFixLenthRandom() +fileType;
						String uploadpath = "img/tc/";
						String newpath = path +uploadpath + fileName;
						File localFile = new File(newpath);
						// 写文件到本地
						file.transferTo(localFile);
						resp.addString("path"+i, uploadpath + fileName);
						i++;
					}else{
						logger.info("上传文件为空!");
						return Response.failuer("上传文件为空!");
					}
				}
			}else{
				logger.info("文件为空!");
				return Response.failuer("文件为空!");
			}
		} catch (IllegalStateException e) {
			logger.error(e, e);
			return Response.failuer("发生了未知错误");
		} catch (IOException e) {
			logger.error(e, e);
			return Response.failuer("发生了未知错误");
		}
		return resp.toString();
	}
}
