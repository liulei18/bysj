package com.edu.zzti.ass.core.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传文件的工具类 用于处理 表单类型为 <form enctype="multipart/form-data">的表单 上传的文件上传至项目根路径下
 * /upload 文件的路径 和 表单的信息 存放在Map<String,String>中
 * 
 * @author feng
 * 
 */
public class UploadUtil {
	private HttpServletRequest request = null;

	/**
	 * 构造函数请放入当前Servlet的request
	 * 
	 * @param request
	 */
	public UploadUtil(HttpServletRequest request) {
		this.request = request;

	}

	/**
	 * error： 若error为0则上传成功 说明处理完毕可以进行操作， 存放路径从map中key为 url 中取，表单信息从其相应的name属性取； 若
	 * error为1 则上传失败 ，请取出 message的信息，并停止操作打印异常；
	 * 
	 * @param maxSize
	 *            文件最大限制
	 * @param dirName
	 *            要存放的目录
	  * @param file
	 *            springMVC MultipartFile
	 * @return Map<String,String>
	 * @throws Exception
	 */
	public Map<String, String> uploadFile(long maxSize, String dirName,
			MultipartFile file) throws Exception {

		// 文件保存目录路径
		String savePath = request.getServletContext().getRealPath("/")
				.replace("\\", "/")
				+ "WEB-INF/upload/";
		// 设置内存缓冲区
		String tempPath = request.getServletContext().getRealPath("/")
				.replace("\\", "/")
				+ "temp";
		// 文件保存目录URL
		String saveUrl ="/WEB-INF/upload/";
		File tempFile = new File(tempPath);
		if (!tempFile.exists()) {
			tempFile.mkdir();
		}
		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media",
				"swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb,mp4");
		extMap.put("file",
				"doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2,exe");
		// 判断是不是表单是否是流
		if (!ServletFileUpload.isMultipartContent(request)) {
			return getError("请选择文件。");
		}
		// 检查目录
		File uploadDir = new File(savePath);
		if (!uploadDir.isDirectory()) {
			return getError("上传目录不存在。");
			// uploadDir.mkdirs();
		}
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			return getError("上传目录没有写权限。");
		}


		// 创建文件夹
		savePath += dirName + "/";
		saveUrl += dirName + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String ym = sdf.format(new Date());
		savePath += ym + "/";
		saveUrl += ym + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		Map<String, String> map = new HashMap<String, String>();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 设置内存缓冲区的大小，默认10k
		factory.setSizeThreshold(500 * 1024); // 500k
		// 设置临时目录
		factory.setRepository(new File(tempPath));
		upload.setHeaderEncoding("UTF-8");

		String fileName = file.getOriginalFilename();

		// 检查文件大小
		if (file.getBytes().length > maxSize) {
			return getError("上传文件大小超过限制。");
		}
		// 检查扩展名
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1)
				.toLowerCase();

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String newFileName = df.format(new Date()) + "_"
				+ new Random().nextInt(10000) + "." + fileExt;
		try {

			File uploadedFile = new File(savePath, newFileName);

			//文件存储到磁盘
			file.transferTo(uploadedFile);

		} catch (Exception e) {
			e.printStackTrace();
			return getError("上传文件失败。");

		}
		map.put("error", "0");
		map.put("url", saveUrl + newFileName);
		map.put("newFileName", newFileName);
		return map;

	}

	private Map<String, String> getError(String message) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("error", "1");
		map.put("message", message);
		return map;
	}
	
	/**
	 * error： 若error为0则上传成功 说明处理完毕可以进行操作， 存放路径从map中key为 url 中取，表单信息从其相应的name属性取； 若
	 * error为1 则上传失败 ，请取出 message的信息，并停止操作打印异常；
	 * 
	 * @param maxSize
	 *            文件最大限制
	 * @param dirName
	 *            要存放的目录
	  * @param file
	 *            springMVC MultipartFile
	 * @return Map<String,String>
	 * @throws Exception
	 */
	public Map<String, String> uploadFile1(long maxSize, String dirName,
			MultipartFile file) throws Exception {

		// 文件保存目录路径
		String savePath = request.getServletContext().getRealPath("/")
				.replace("\\", "/")
				+ "temp/";
		// 设置内存缓冲区
		String tempPath = request.getServletContext().getRealPath("/")
				.replace("\\", "/")
				+ "temp";
		// 文件保存目录URL
		String saveUrl ="/temp/";
		File tempFile = new File(tempPath);
		if (!tempFile.exists()) {
			tempFile.mkdir();
		}
		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media",
				"swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb,mp4");
		extMap.put("file",
				"doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2,exe");
		// 判断是不是表单是否是流
		if (!ServletFileUpload.isMultipartContent(request)) {
			return getError("请选择文件。");
		}
		// 检查目录
		File uploadDir = new File(savePath);
		if (!uploadDir.isDirectory()) {
			return getError("上传目录不存在。");
			// uploadDir.mkdirs();
		}
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			return getError("上传目录没有写权限。");
		}


		// 创建文件夹
		savePath += dirName + "/";
		saveUrl += dirName + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String ym = sdf.format(new Date());
		savePath += ym + "/";
		saveUrl += ym + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		Map<String, String> map = new HashMap<String, String>();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 设置内存缓冲区的大小，默认10k
		factory.setSizeThreshold(500 * 1024); // 500k
		// 设置临时目录
		factory.setRepository(new File(tempPath));
		upload.setHeaderEncoding("UTF-8");

		String fileName = file.getOriginalFilename();

		// 检查文件大小
		if (file.getBytes().length > maxSize) {
			return getError("上传文件大小超过限制。");
		}
		// 检查扩展名
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1)
				.toLowerCase();

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String newFileName = df.format(new Date()) + "_"
				+ new Random().nextInt(10000) + "." + fileExt;
		try {

			File uploadedFile = new File(savePath, newFileName);

			//文件存储到磁盘
			file.transferTo(uploadedFile);

		} catch (Exception e) {
			e.printStackTrace();
			return getError("上传文件失败。");

		}
		map.put("error", "0");
		map.put("url", saveUrl + newFileName);
		map.put("newFileName", newFileName);
		return map;

	}


}
