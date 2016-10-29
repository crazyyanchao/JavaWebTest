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
 * @author ����
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
			//����������request��������
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
			String path = request.getServletContext().getRealPath("/");
			//���ж�request���Ƿ����multipart���͵����ݣ�
			if (multipartResolver.isMultipart(request)) {
				// �ٽ�request�е�����ת����multipart���͵�����
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
						// д�ļ�������
						file.transferTo(localFile);
						resp.addString("path"+i, uploadpath + fileName);
						i++;
					}else{
						logger.info("�ϴ��ļ�Ϊ��!");
						return Response.failuer("�ϴ��ļ�Ϊ��!");
					}
				}
			}else{
				logger.info("�ļ�Ϊ��!");
				return Response.failuer("�ļ�Ϊ��!");
			}
		} catch (IllegalStateException e) {
			logger.error(e, e);
			return Response.failuer("������δ֪����");
		} catch (IOException e) {
			logger.error(e, e);
			return Response.failuer("������δ֪����");
		}
		return resp.toString();
	}
}
