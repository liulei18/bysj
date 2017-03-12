package com.edu.zzti.ass.management.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.edu.zzti.ass.core.model.PageInfo;
import com.edu.zzti.ass.core.util.UploadUtil;
import com.edu.zzti.ass.management.dao.IFileShowDao;
import com.edu.zzti.ass.management.model.TClass;
import com.edu.zzti.ass.management.model.TFile;
import com.edu.zzti.ass.management.model.TFileShow;
import com.edu.zzti.ass.management.model.TStudent;
import com.edu.zzti.ass.management.model.TTeacher;
import com.edu.zzti.ass.management.service.IFileService;
import com.edu.zzti.ass.management.service.IFileShowService;
import com.edu.zzti.ass.management.service.IInfoWriteService;
import com.edu.zzti.ass.testlibrary.model.TUnit;

@Controller
@RequestMapping(value = "/manage/mUpload")
public class FileUploadController {

	@Autowired
	private IInfoWriteService infoWriteService;

	@Autowired
	private IFileService fileService;
	
	@Autowired
	private IFileShowService fileShowService;
	
	
	/**
	 * 教学目标
	 * @return
	 */
	@RequestMapping(value = "addTarget", method = RequestMethod.POST)
	public ModelAndView addTarget(@RequestParam("file") MultipartFile file,
			HttpServletRequest request, HttpSession session) {
			ModelAndView target = new ModelAndView();
	    		Integer fKey = Integer.valueOf(request.getParameter("fKey").trim());
	    		List<TFileShow> list = fileShowService.findByFKey(fKey, 1);
	    		if (list.size() > 0) {
	    			target.addObject("message", "已有过上传，如需更改请先删除后再进行上传...");
	    			target.setViewName("/manage/targetManagement");
	    			return target;
	    		}
	    		if (!file.isEmpty()) {
	    			UploadUtil uploadUtil = new UploadUtil(request);
	    			Map<String, String> map = new HashMap<String, String>();
	    			// 获得文件真实名字
	    			String fileName = file.getOriginalFilename();
	    			// 获取文件后缀名
	    			String endName = fileName.substring(fileName.lastIndexOf(".") + 1)
	    					.toLowerCase();
	    			if ("pdf".equals(endName)) {
	    				try {
	    					map = uploadUtil.uploadFile(10485760, "target", file);

	    					TFileShow tfile = new TFileShow();
	    					tfile.setAddTimes(new Date());
	    					tfile.setFtype(1);
	    					tfile.setIsCheck(1);
	    					tfile.setName(map.get("newFileName"));
	    					tfile.setRealName(fileName);
	    					tfile.setUrl(map.get("url"));
	    					tfile.setFkey(fKey);
	    					Integer i = (Integer) fileShowService.save(tfile);
	    					System.out.println("文件存入的id为" + i);

	    				} catch (Exception e) {
	    					e.printStackTrace();
	    				}

	    				if (map.get("error").equals("0")) {
	    					 target.setViewName("redirect:/manage/file/targetManagement/"+1);
	    					 return target;
	    				} else {
	    					target.addObject("message", map.get("message"));
	    				}

	    			} else {
	    				target.addObject("message", "上传文件格式不对，应为pdf为后缀名的文件");
	    			}
	    			target.setViewName("/manage/targetManagement");
	    		} else {
	    			target.addObject("message", "还没有上传文件");
	    			target.setViewName("/manage/targetManagement");
	    		}
	    		return target;		
		
	}
	
	
	/**
	 * 教学方法
	 */
	@RequestMapping(value = "addJxff", method = RequestMethod.POST)
	public ModelAndView addJxff(@RequestParam("file") MultipartFile file,
			HttpServletRequest request, HttpSession session) {
			ModelAndView target = new ModelAndView();
	    		Integer fKey = Integer.valueOf(request.getParameter("fKey").trim());
	    		List<TFileShow> list = fileShowService.findByFKey(fKey, 2);
	    		if (list.size() > 0) {
	    			target.addObject("message", "已有过上传，如需更改请先删除后再进行上传...");
	    			target.setViewName("/manage/jxffManagement");
	    			return target;
	    		}
	    		if (!file.isEmpty()) {
	    			UploadUtil uploadUtil = new UploadUtil(request);
	    			Map<String, String> map = new HashMap<String, String>();
	    			// 获得文件真实名字
	    			String fileName = file.getOriginalFilename();
	    			// 获取文件后缀名
	    			String endName = fileName.substring(fileName.lastIndexOf(".") + 1)
	    					.toLowerCase();
	    			if ("pdf".equals(endName)) {
	    				try {
	    					map = uploadUtil.uploadFile(10485760, "jxff", file);

	    					TFileShow tfile = new TFileShow();
	    					tfile.setAddTimes(new Date());
	    					tfile.setFtype(2);
	    					tfile.setIsCheck(1);
	    					tfile.setName(map.get("newFileName"));
	    					tfile.setRealName(fileName);
	    					tfile.setUrl(map.get("url"));
	    					tfile.setFkey(fKey);
	    					Integer i = (Integer) fileShowService.save(tfile);
	    					System.out.println("文件存入的id为" + i);

	    				} catch (Exception e) {
	    					e.printStackTrace();
	    				}

	    				if (map.get("error").equals("0")) {
	    					 target.setViewName("redirect:/manage/file/jxffManagement/"+1);
	    					 return target;
	    				} else {
	    					target.addObject("message", map.get("message"));
	    				}

	    			} else {
	    				target.addObject("message", "上传文件格式不对，应为pdf为后缀名的文件");
	    			}
	    			target.setViewName("/manage/jxffManagement");
	    		} else {
	    			target.addObject("message", "还没有上传文件");
	    			target.setViewName("/manage/jxffManagement");
	    		}
	    		return target;		
		
	}
	
	/**
	 * 教学大纲
	 */
	@RequestMapping(value = "addJxdg", method = RequestMethod.POST)
	public ModelAndView addJxdg(@RequestParam("file") MultipartFile file,
			HttpServletRequest request, HttpSession session) {
			ModelAndView target = new ModelAndView();
	    		Integer fKey = Integer.valueOf(request.getParameter("fKey").trim());
	    		List<TFileShow> list = fileShowService.findByFKey(fKey, 3);
	    		if (list.size() > 0) {
	    			target.addObject("message", "已有过上传，如需更改请先删除后再进行上传...");
	    			target.setViewName("/manage/jxdgManagement");
	    			return target;
	    		}
	    		if (!file.isEmpty()) {
	    			UploadUtil uploadUtil = new UploadUtil(request);
	    			Map<String, String> map = new HashMap<String, String>();
	    			// 获得文件真实名字
	    			String fileName = file.getOriginalFilename();
	    			// 获取文件后缀名
	    			String endName = fileName.substring(fileName.lastIndexOf(".") + 1)
	    					.toLowerCase();
	    			if ("pdf".equals(endName)) {
	    				try {
	    					map = uploadUtil.uploadFile(10485760, "jxdg", file);

	    					TFileShow tfile = new TFileShow();
	    					tfile.setAddTimes(new Date());
	    					tfile.setFtype(3);
	    					tfile.setIsCheck(1);
	    					tfile.setName(map.get("newFileName"));
	    					tfile.setRealName(fileName);
	    					tfile.setUrl(map.get("url"));
	    					tfile.setFkey(fKey);
	    					Integer i = (Integer) fileShowService.save(tfile);
	    					System.out.println("文件存入的id为" + i);

	    				} catch (Exception e) {
	    					e.printStackTrace();
	    				}

	    				if (map.get("error").equals("0")) {
	    					 target.setViewName("redirect:/manage/file/jxdgManagement/"+1);
	    					 return target;
	    				} else {
	    					target.addObject("message", map.get("message"));
	    				}

	    			} else {
	    				target.addObject("message", "上传文件格式不对，应为pdf为后缀名的文件");
	    			}
	    			target.setViewName("/manage/jxdgManagement");
	    		} else {
	    			target.addObject("message", "还没有上传文件");
	    			target.setViewName("/manage/jxdgManagement");
	    		}
	    		return target;		
		
	}
	
	
	/**
	 * 教学日历
	 */
	@RequestMapping(value = "addJxrl", method = RequestMethod.POST)
	public ModelAndView addJxrl(@RequestParam("file") MultipartFile file,
			HttpServletRequest request, HttpSession session) {
			ModelAndView target = new ModelAndView();
	    		Integer fKey = Integer.valueOf(request.getParameter("fKey").trim());
	    		List<TFileShow> list = fileShowService.findByFKey(fKey, 4);
	    		if (list.size() > 0) {
	    			target.addObject("message", "已有过上传，如需更改请先删除后再进行上传...");
	    			target.setViewName("/manage/jxrlManagement");
	    			return target;
	    		}
	    		if (!file.isEmpty()) {
	    			UploadUtil uploadUtil = new UploadUtil(request);
	    			Map<String, String> map = new HashMap<String, String>();
	    			// 获得文件真实名字
	    			String fileName = file.getOriginalFilename();
	    			// 获取文件后缀名
	    			String endName = fileName.substring(fileName.lastIndexOf(".") + 1)
	    					.toLowerCase();
	    			if ("pdf".equals(endName)) {
	    				try {
	    					map = uploadUtil.uploadFile(10485760, "jxrl", file);

	    					TFileShow tfile = new TFileShow();
	    					tfile.setAddTimes(new Date());
	    					tfile.setFtype(4);
	    					tfile.setIsCheck(1);
	    					tfile.setName(map.get("newFileName"));
	    					tfile.setRealName(fileName);
	    					tfile.setUrl(map.get("url"));
	    					tfile.setFkey(fKey);
	    					Integer i = (Integer) fileShowService.save(tfile);
	    					System.out.println("文件存入的id为" + i);

	    				} catch (Exception e) {
	    					e.printStackTrace();
	    				}

	    				if (map.get("error").equals("0")) {
	    					 target.setViewName("redirect:/manage/file/jxrlManagement/"+1);
	    					 return target;
	    				} else {
	    					target.addObject("message", map.get("message"));
	    				}

	    			} else {
	    				target.addObject("message", "上传文件格式不对，应为pdf为后缀名的文件");
	    			}
	    			target.setViewName("/manage/jxrlManagement");
	    		} else {
	    			target.addObject("message", "还没有上传文件");
	    			target.setViewName("/manage/jxrlManagement");
	    		}
	    		return target;		
		
	}
	
	
	/**
	 * 考核方式
	 */
	@RequestMapping(value = "addKhfs", method = RequestMethod.POST)
	public ModelAndView addKhfs(@RequestParam("file") MultipartFile file,
			HttpServletRequest request, HttpSession session) {
			ModelAndView target = new ModelAndView();
	    		Integer fKey = Integer.valueOf(request.getParameter("fKey").trim());
	    		List<TFileShow> list = fileShowService.findByFKey(fKey, 5);
	    		if (list.size() > 0) {
	    			target.addObject("message", "已有过上传，如需更改请先删除后再进行上传...");
	    			target.setViewName("/manage/khfsManagement");
	    			return target;
	    		}
	    		if (!file.isEmpty()) {
	    			UploadUtil uploadUtil = new UploadUtil(request);
	    			Map<String, String> map = new HashMap<String, String>();
	    			// 获得文件真实名字
	    			String fileName = file.getOriginalFilename();
	    			// 获取文件后缀名
	    			String endName = fileName.substring(fileName.lastIndexOf(".") + 1)
	    					.toLowerCase();
	    			if ("pdf".equals(endName)) {
	    				try {
	    					map = uploadUtil.uploadFile(10485760, "khfs", file);

	    					TFileShow tfile = new TFileShow();
	    					tfile.setAddTimes(new Date());
	    					tfile.setFtype(5);
	    					tfile.setIsCheck(1);
	    					tfile.setName(map.get("newFileName"));
	    					tfile.setRealName(fileName);
	    					tfile.setUrl(map.get("url"));
	    					tfile.setFkey(fKey);
	    					Integer i = (Integer) fileShowService.save(tfile);
	    					System.out.println("文件存入的id为" + i);

	    				} catch (Exception e) {
	    					e.printStackTrace();
	    				}

	    				if (map.get("error").equals("0")) {
	    					 target.setViewName("redirect:/manage/file/khfsManagement/"+1);
	    					 return target;
	    				} else {
	    					target.addObject("message", map.get("message"));
	    				}

	    			} else {
	    				target.addObject("message", "上传文件格式不对，应为pdf为后缀名的文件");
	    			}
	    			target.setViewName("/manage/khfsManagement");
	    		} else {
	    			target.addObject("message", "还没有上传文件");
	    			target.setViewName("/manage/khfsManagement");
	    		}
	    		return target;		
		
	}
	
	
	
	/**
	 * 教材
	 */
	@RequestMapping(value = "addJc", method = RequestMethod.POST)
	public ModelAndView addJc(@RequestParam("file") MultipartFile file,
			HttpServletRequest request, HttpSession session) {
			ModelAndView target = new ModelAndView();
	    		Integer fKey = Integer.valueOf(request.getParameter("fKey").trim());
	    		List<TFileShow> list = fileShowService.findByFKey(fKey, 6);
	    		if (list.size() > 0) {
	    			target.addObject("message", "已有过上传，如需更改请先删除后再进行上传...");
	    			target.setViewName("/manage/jcManagement");
	    			return target;
	    		}
	    		if (!file.isEmpty()) {
	    			UploadUtil uploadUtil = new UploadUtil(request);
	    			Map<String, String> map = new HashMap<String, String>();
	    			// 获得文件真实名字
	    			String fileName = file.getOriginalFilename();
	    			// 获取文件后缀名
	    			String endName = fileName.substring(fileName.lastIndexOf(".") + 1)
	    					.toLowerCase();
	    			if ("pdf".equals(endName)) {
	    				try {
	    					map = uploadUtil.uploadFile(10485760, "jc", file);

	    					TFileShow tfile = new TFileShow();
	    					tfile.setAddTimes(new Date());
	    					tfile.setFtype(6);
	    					tfile.setIsCheck(1);
	    					tfile.setName(map.get("newFileName"));
	    					tfile.setRealName(fileName);
	    					tfile.setUrl(map.get("url"));
	    					tfile.setFkey(fKey);
	    					Integer i = (Integer) fileShowService.save(tfile);
	    					System.out.println("文件存入的id为" + i);

	    				} catch (Exception e) {
	    					e.printStackTrace();
	    				}

	    				if (map.get("error").equals("0")) {
	    					 target.setViewName("redirect:/manage/file/jcManagement/"+1);
	    					 return target;
	    				} else {
	    					target.addObject("message", map.get("message"));
	    				}

	    			} else {
	    				target.addObject("message", "上传文件格式不对，应为pdf为后缀名的文件");
	    			}
	    			target.setViewName("/manage/jcManagement");
	    		} else {
	    			target.addObject("message", "还没有上传文件");
	    			target.setViewName("/manage/jcManagement");
	    		}
	    		return target;		
		
	}
	
	
	/**
	 * 师资力量
	 */
	@RequestMapping(value = "addSzll", method = RequestMethod.POST)
	public ModelAndView addSzll(@RequestParam("file") MultipartFile file,
			HttpServletRequest request, HttpSession session) {
			ModelAndView target = new ModelAndView();
	    		Integer fKey = Integer.valueOf(request.getParameter("fKey").trim());
	    		List<TFileShow> list = fileShowService.findByFKey(fKey, 7);
	    		if (list.size() > 0) {
	    			target.addObject("message", "已有过上传，如需更改请先删除后再进行上传...");
	    			target.setViewName("/manage/szllManagement");
	    			return target;
	    		}
	    		if (!file.isEmpty()) {
	    			UploadUtil uploadUtil = new UploadUtil(request);
	    			Map<String, String> map = new HashMap<String, String>();
	    			// 获得文件真实名字
	    			String fileName = file.getOriginalFilename();
	    			// 获取文件后缀名
	    			String endName = fileName.substring(fileName.lastIndexOf(".") + 1)
	    					.toLowerCase();
	    			if ("pdf".equals(endName)) {
	    				try {
	    					map = uploadUtil.uploadFile(10485760, "jc", file);

	    					TFileShow tfile = new TFileShow();
	    					tfile.setAddTimes(new Date());
	    					tfile.setFtype(7);
	    					tfile.setIsCheck(1);
	    					tfile.setName(map.get("newFileName"));
	    					tfile.setRealName(fileName);
	    					tfile.setUrl(map.get("url"));
	    					tfile.setFkey(fKey);
	    					Integer i = (Integer) fileShowService.save(tfile);
	    					System.out.println("文件存入的id为" + i);

	    				} catch (Exception e) {
	    					e.printStackTrace();
	    				}

	    				if (map.get("error").equals("0")) {
	    					 target.setViewName("redirect:/manage/file/szllManagement/"+1);
	    					 return target;
	    				} else {
	    					target.addObject("message", map.get("message"));
	    				}

	    			} else {
	    				target.addObject("message", "上传文件格式不对，应为pdf为后缀名的文件");
	    			}
	    			target.setViewName("/manage/szllManagement");
	    		} else {
	    			target.addObject("message", "还没有上传文件");
	    			target.setViewName("/manage/szllManagement");
	    		}
	    		return target;		
		
	}
	
	

	@RequestMapping(value = "addStu", method = { RequestMethod.GET })
	public ModelAndView intoAddStu() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("manage/addStudents");
		return mav;
	}

	@RequestMapping(value = "addStu", method = RequestMethod.POST)
	public ModelAndView addStu(@RequestParam("file") MultipartFile file,
			HttpServletRequest request, HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		if (!file.isEmpty()) {
			TTeacher teacher = (TTeacher) httpSession.getAttribute("userInfo");
			UploadUtil uploadUtil = new UploadUtil(request);
			Map<String, String> map = new HashMap<String, String>();
			Map<String, String> mapInfo = new HashMap<String, String>();
			// 获得文件真实名字
			String fileName = file.getOriginalFilename();
			// 获取文件后缀名
			String endName = fileName.substring(fileName.lastIndexOf(".") + 1)
					.toLowerCase();
			if ("xls".equals(endName) || "xlsx".equals(endName)) {
				try {
					map = uploadUtil.uploadFile(10485760, "stuInfo", file);
					System.out.println(request.getServletContext()
							.getRealPath("").replace("\\", "/")
							+ map.get("url"));
					// 下面进行文件的解析或者文件信息录入数据库
					File xlsFile = new File(request.getServletContext()
							.getRealPath("").replace("\\", "/")
							+ map.get("url"));
					if ("xlsx".equals(endName)) {
						try {
							XSSFWorkbook workBook = new XSSFWorkbook(
									FileUtils.openInputStream(xlsFile));
							XSSFSheet sheet = workBook.getSheetAt(0);
							int firstRowNum = 1; // 从表中第二行进行读取
							int lastRowNum = sheet.getLastRowNum();
							for (int i = firstRowNum; i <= lastRowNum; i++) {
								XSSFRow row = sheet.getRow(i);
								int lastCellNum = row.getLastCellNum(); // 当前行最后一列的索引值
								for (int j = 0; j < lastCellNum; j++) {
									XSSFCell cell = row.getCell(j);
									String value = cell.getStringCellValue();
									mapInfo.put("" + j, value);
								}
								TClass tClass = new TClass();
								tClass.setName(mapInfo.get("2"));
								TStudent student = new TStudent();
								student.setId(mapInfo.get("0"));
								student.setRealName(mapInfo.get("1"));
								student.setPwd(mapInfo.get("0"));

								infoWriteService.saveInfo(tClass, student,
										teacher);

							}

						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						try {
							HSSFWorkbook workBook = new HSSFWorkbook(
									FileUtils.openInputStream(xlsFile));
							HSSFSheet sheet = workBook.getSheetAt(0);
							int firstRowNum = 1;
							int lastRowNum = sheet.getLastRowNum();
							for (int i = firstRowNum; i <= lastRowNum; i++) {
								HSSFRow row = sheet.getRow(i);
								int lastCellNum = row.getLastCellNum();
								for (int j = 0; j < lastCellNum; j++) {
									HSSFCell cell = row.getCell(j);
									String value = cell.getStringCellValue();
									mapInfo.put("" + j, value);
								}
								TClass tClass = new TClass();
								tClass.setName(mapInfo.get("2"));
								TStudent student = new TStudent();
								student.setId(mapInfo.get("0"));
								student.setRealName(mapInfo.get("1"));
								student.setPwd(mapInfo.get("0"));

								infoWriteService.saveInfo(tClass, student,
										teacher);
							}

						} catch (IOException e) {
							e.printStackTrace();
						}

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (map.get("error").equals("0")) {
					mav.addObject("message", "上传成功");
				} else {
					mav.addObject("message", map.get("message"));
				}
			} else {
				mav.addObject("message", "上传文件格式不对，应为xls或者xlsx为后缀名的文件");
			}
			mav.setViewName("manage/addStudents");
		} else {
			mav.addObject("message", "还没有上传文件");
			mav.setViewName("manage/addStudents");
		}
		return mav;
	}
	
	
	
	
	
	
	
	/**
	 * ppt上传
	 * @return
	 */
	@RequestMapping(value = "addPpt", method = { RequestMethod.GET })
	public ModelAndView intoAddPpt() {
		ModelAndView ppt = new ModelAndView();
		ppt.setViewName("/manage/pptManagement");
		return ppt;
	}

	@RequestMapping(value = "addPpt", method = RequestMethod.POST)
	public ModelAndView addPpt(@RequestParam("file") MultipartFile file,
			HttpServletRequest request, HttpSession session) {
		ModelAndView ppt = new ModelAndView();
		
	        	List<TUnit> units = fileService.findUnit();
	    		ppt.addObject("units", units);

	    		Integer unitId = Integer.valueOf(request.getParameter("unitId").trim());
	    		List<TFile> list = fileService.findByUnitId(unitId, 1);
	    		
	    		if (list.size() > 0) {
	    			ppt.addObject("message", "此单元已有过上传，如需更改请先删除后再进行上传...");
	    			ppt.setViewName("/manage/pptManagement");
	    			return ppt;
	    		}

	    		if (!file.isEmpty()) {

	    			UploadUtil uploadUtil = new UploadUtil(request);
	    			Map<String, String> map = new HashMap<String, String>();
	    			// 获得文件真实名字
	    			String fileName = file.getOriginalFilename();
	    			// 获取文件后缀名
	    			String endName = fileName.substring(fileName.lastIndexOf(".") + 1)
	    					.toLowerCase();
	    			if ("swf".equals(endName)) {

	    				try {
	    					map = uploadUtil.uploadFile(10485760, "ppt", file);

	    					TFile tfile = new TFile();
	    					tfile.setAddTimes(new Date());
	    					tfile.setFtype(1);
	    					tfile.setIsCheck(1);
	    					tfile.setUnit(new TUnit(unitId));
	    					tfile.setTTeacher((TTeacher) session
	    							.getAttribute("userInfo"));
	    					tfile.setName(map.get("newFileName"));
	    					tfile.setRealName(fileName);
	    					tfile.setUrl(map.get("url"));
	    					Integer i = (Integer) fileService.save(tfile);
	    					System.out.println("文件存入的id为" + i);

	    				} catch (Exception e) {
	    					e.printStackTrace();
	    				}

	    				if (map.get("error").equals("0")) {
	    					 ppt.setViewName("redirect:/manage/file/pptManagement/"+1);
	    					 return ppt;
	    				} else {
	    					ppt.addObject("message", map.get("message"));
	    				}

	    			} else {
	    				ppt.addObject("message", "上传文件格式不对，应为swf为后缀名的文件");
	    			}
	    			ppt.setViewName("/manage/pptManagement");
	    		} else {
	    			ppt.addObject("message", "还没有上传文件");
	    			ppt.setViewName("/manage/pptManagement");
	    		}
	    		return ppt;		
		
	}

	@RequestMapping(value = "addVideo", method = { RequestMethod.GET })
	public ModelAndView intoAddVideo(HttpSession httpSession) {
		ModelAndView video = new ModelAndView();
		
		video.setViewName("/manage/videoManagement");
		return video;
	}

	@RequestMapping(value = "addVideo", method = RequestMethod.POST)
	public ModelAndView addVideo(@RequestParam("file") MultipartFile file,
			HttpServletRequest request, HttpSession session) {
		ModelAndView video = new ModelAndView();
		
		List<TUnit> units = fileService.findUnit();
		video.addObject("units", units);

		Integer unitId = Integer.valueOf(request.getParameter("unitId").trim());
		List<TFile> list = fileService.findByUnitId(unitId, 2);
		
		if (list.size() > 0) {
			video.addObject("message", "此单元已有过上传，如需更改请先删除后再进行上传...");
			video.setViewName("/manage/pptManagement");
			return video;
		}
		
		if (!file.isEmpty()) {

			UploadUtil uploadUtil = new UploadUtil(request);
			Map<String, String> map = new HashMap<String, String>();
			// 获得文件真实名字
			String fileName = file.getOriginalFilename();
			// 获取文件后缀名
			String endName = fileName.substring(fileName.lastIndexOf(".") + 1)
					.toLowerCase();
			if ("mp4".equals(endName) || "avi".equals(endName)) {

				try {
					map = uploadUtil.uploadFile(10485760, "video", file);

					TFile tfile = new TFile();
					tfile.setAddTimes(new Date());
					tfile.setFtype(2);
					tfile.setIsCheck(1);
					tfile.setUnit(new TUnit(unitId));
					tfile.setTTeacher((TTeacher) session
							.getAttribute("userInfo"));
					tfile.setName(map.get("newFileName"));
					tfile.setRealName(fileName);
					tfile.setUrl(map.get("url"));
					Integer i = (Integer) fileService.save(tfile);
					System.out.println("文件存入的id为" + i);
					
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (map.get("error").equals("0")) {
					video.setViewName("redirect:/manage/file/videoManagement/"+1);
					 return video;

				} else {
					video.addObject("message", map.get("message"));
				}

			} else {
				video.addObject("message", "上传文件格式不对，应为mp4或者avi为后缀名的文件");
			}
			video.setViewName("/manage/videoManagement");
		} else {
			video.addObject("message", "还没有上传文件");
			video.setViewName("/manage/videoManagement");
		}
		return video;
	}

	@RequestMapping(value = "addDocument", method = { RequestMethod.GET })
	public ModelAndView intoAddDocument() {
		ModelAndView document = new ModelAndView();
		document.setViewName("/manage/documentManagement");
		return document;
	}

	@RequestMapping(value = "addDocument", method = RequestMethod.POST)
	public ModelAndView addDocument(@RequestParam("file") MultipartFile file,
			HttpServletRequest request, HttpSession session) {
		ModelAndView document = new ModelAndView();
		
		List<TUnit> units = fileService.findUnit();
		document.addObject("units", units);

		Integer unitId = Integer.valueOf(request.getParameter("unitId").trim());
		List<TFile> list = fileService.findByUnitId(unitId, 3);
		
		if (list.size() > 0) {
			document.addObject("message", "此单元已有过上传，如需更改请先删除后再进行上传...");
			document.setViewName("/manage/documentManagement");
			return document;
		}
		
		if (!file.isEmpty()) {

			UploadUtil uploadUtil = new UploadUtil(request);
			Map<String, String> map = new HashMap<String, String>();
			// 获得文件真实名字
			String fileName = file.getOriginalFilename();
			// 获取文件后缀名
			String endName = fileName.substring(fileName.lastIndexOf(".") + 1)
					.toLowerCase();
			if ("pdf".equals(endName)) {

				try {
					map = uploadUtil.uploadFile(10485760, "document", file);

					TFile tfile = new TFile();
					tfile.setAddTimes(new Date());
					tfile.setFtype(3);
					tfile.setIsCheck(1);
					tfile.setUnit(new TUnit(unitId));
					tfile.setTTeacher((TTeacher) session
							.getAttribute("userInfo"));
					tfile.setName(map.get("newFileName"));
					tfile.setRealName(fileName);
					tfile.setUrl(map.get("url"));
					Integer i = (Integer) fileService.save(tfile);
					System.out.println("文件存入的id为" + i);
					
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (map.get("error").equals("0")) {
					document.setViewName("redirect:/manage/file/documentManagement/"+1);
					 return document;

				} else {
					document.addObject("message", map.get("message"));
				}

			} else {
				document.addObject("message", "上传文件格式不对，应为pdf为后缀名的文件");
			}
			document.setViewName("/manage/documentManagement");
		} else {
			document.addObject("message", "还没有上传文件");
			document.setViewName("/manage/documentManagement");
		}
		return document;
	}


}
