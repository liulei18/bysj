package com.edu.zzti.ass.management.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.edu.zzti.ass.core.model.PageInfo;
import com.edu.zzti.ass.management.model.TFile;
import com.edu.zzti.ass.management.model.TFileShow;
import com.edu.zzti.ass.management.model.TTeacher;
import com.edu.zzti.ass.management.service.IFileService;
import com.edu.zzti.ass.management.service.IFileShowService;
import com.edu.zzti.ass.testlibrary.model.TUnit;

@Controller
@RequestMapping(value = "/manage/file")
public class FileManagementController {
	@Autowired
	private IFileService fileService;
	
	@Autowired
	private IFileShowService fileShowService;
	
	/**
	 * 转到教学目标列表页面
	 */
	@RequestMapping(value = "targetManagement/{page}", method = { RequestMethod.GET })
	public ModelAndView targetView(Model model, @PathVariable Integer page,
			HttpSession httpSession) {

		ModelAndView mav = new ModelAndView();
		TTeacher teacher = (TTeacher) httpSession.getAttribute("userInfo");
		if (teacher.getIsAdmin() == 0) {
			mav.addObject("info", "对不起，你不是管理员老师！");
			mav.setViewName("manage/info");
			return mav;
		} else {
			mav.addObject("fKey", 50);

			PageInfo<TFileShow> pageInfo = fileShowService.findByPage(page, 1);
			mav.addObject("pageInfo", pageInfo);

			mav.setViewName("manage/targetManagement");
			return mav;
		}

	}
	
	@RequestMapping(value = "targetDelete/{id}/{page}", method = { RequestMethod.GET })
	public ModelAndView targetDelete(Model model, HttpServletRequest request,
			@PathVariable Integer id, @PathVariable Integer page) {

		ModelAndView mav = new ModelAndView();
		String url = request.getServletContext().getRealPath("").replace("\\", "/");
		fileShowService.targetDelete(id, url);
		mav.setViewName("redirect:/manage/file/targetManagement/" + page);
		return mav;
	}
	
	/**
	 * 教学方法列表
	 */
	@RequestMapping(value = "jxffManagement/{page}", method = { RequestMethod.GET })
	public ModelAndView jxffView(Model model, @PathVariable Integer page,
			HttpSession httpSession) {

		ModelAndView mav = new ModelAndView();
		TTeacher teacher = (TTeacher) httpSession.getAttribute("userInfo");
		if (teacher.getIsAdmin() == 0) {
			mav.addObject("info", "对不起，你不是管理员老师！");
			mav.setViewName("manage/info");
			return mav;
		} else {
			mav.addObject("fKey", 51);

			//1代表文件类型是：pdf
			PageInfo<TFileShow> pageInfo = fileShowService.findByPage(page, 2);
			mav.addObject("pageInfo", pageInfo);

			mav.setViewName("manage/jxffManagement");
			return mav;
		}

	}
	
	@RequestMapping(value = "jxffDelete/{id}/{page}", method = { RequestMethod.GET })
	public ModelAndView jxffDelete(Model model, HttpServletRequest request,
			@PathVariable Integer id, @PathVariable Integer page) {

		ModelAndView mav = new ModelAndView();
		String url = request.getServletContext().getRealPath("").replace("\\", "/");
		fileShowService.targetDelete(id, url);
		mav.setViewName("redirect:/manage/file/jxffManagement/" + page);
		return mav;
	}
	
	/**
	 * 教学大纲列表
	 */
	@RequestMapping(value = "jxdgManagement/{page}", method = { RequestMethod.GET })
	public ModelAndView jxdgView(Model model, @PathVariable Integer page,
			HttpSession httpSession) {

		ModelAndView mav = new ModelAndView();
		TTeacher teacher = (TTeacher) httpSession.getAttribute("userInfo");
		if (teacher.getIsAdmin() == 0) {
			mav.addObject("info", "对不起，你不是管理员老师！");
			mav.setViewName("manage/info");
			return mav;
		} else {
			mav.addObject("fKey", 52);

			//1代表文件类型是：pdf
			PageInfo<TFileShow> pageInfo = fileShowService.findByPage(page, 3);
			mav.addObject("pageInfo", pageInfo);
			mav.setViewName("manage/jxdgManagement");
			return mav;
		}

	}
	
	@RequestMapping(value = "jxdgDelete/{id}/{page}", method = { RequestMethod.GET })
	public ModelAndView jxdgDelete(Model model, HttpServletRequest request,
			@PathVariable Integer id, @PathVariable Integer page) {

		ModelAndView mav = new ModelAndView();
		String url = request.getServletContext().getRealPath("").replace("\\", "/");
		fileShowService.targetDelete(id, url);
		mav.setViewName("redirect:/manage/file/jxdgManagement/" + page);
		return mav;
	}
	
	
	/**
	 * 教学日历列表
	 */
	@RequestMapping(value = "jxrlManagement/{page}", method = { RequestMethod.GET })
	public ModelAndView jxrlView(Model model, @PathVariable Integer page,
			HttpSession httpSession) {

		ModelAndView mav = new ModelAndView();
		TTeacher teacher = (TTeacher) httpSession.getAttribute("userInfo");
		if (teacher.getIsAdmin() == 0) {
			mav.addObject("info", "对不起，你不是管理员老师！");
			mav.setViewName("manage/info");
			return mav;
		} else {
			mav.addObject("fKey", 53);

			//1代表文件类型是：pdf
			PageInfo<TFileShow> pageInfo = fileShowService.findByPage(page, 4);
			mav.addObject("pageInfo", pageInfo);

			mav.setViewName("manage/jxrlManagement");
			return mav;
		}

	}
	
	@RequestMapping(value = "jxrlDelete/{id}/{page}", method = { RequestMethod.GET })
	public ModelAndView jxrlDelete(Model model, HttpServletRequest request,
			@PathVariable Integer id, @PathVariable Integer page) {

		ModelAndView mav = new ModelAndView();
		String url = request.getServletContext().getRealPath("").replace("\\", "/");
		fileShowService.targetDelete(id, url);
		mav.setViewName("redirect:/manage/file/jxrlManagement/" + page);
		return mav;
	}
	
	/**
	 * 考核方式列表
	 */
	@RequestMapping(value = "khfsManagement/{page}", method = { RequestMethod.GET })
	public ModelAndView khfsView(Model model, @PathVariable Integer page,
			HttpSession httpSession) {

		ModelAndView mav = new ModelAndView();
		TTeacher teacher = (TTeacher) httpSession.getAttribute("userInfo");
		if (teacher.getIsAdmin() == 0) {
			mav.addObject("info", "对不起，你不是管理员老师！");
			mav.setViewName("manage/info");
			return mav;
		} else {
			mav.addObject("fKey", 54);

			//1代表文件类型是：pdf
			PageInfo<TFileShow> pageInfo = fileShowService.findByPage(page, 5);
			mav.addObject("pageInfo", pageInfo);

			mav.setViewName("manage/khfsManagement");
			return mav;
		}

	}
	
	@RequestMapping(value = "khfsDelete/{id}/{page}", method = { RequestMethod.GET })
	public ModelAndView khfsDelete(Model model, HttpServletRequest request,
			@PathVariable Integer id, @PathVariable Integer page) {

		ModelAndView mav = new ModelAndView();
		String url = request.getServletContext().getRealPath("").replace("\\", "/");
		fileShowService.targetDelete(id, url);
		mav.setViewName("redirect:/manage/file/khfsManagement/" + page);
		return mav;
	}
	
	
	/**
	 * 教材列表
	 */
	@RequestMapping(value = "jcManagement/{page}", method = { RequestMethod.GET })
	public ModelAndView jcView(Model model, @PathVariable Integer page,
			HttpSession httpSession) {

		ModelAndView mav = new ModelAndView();
		TTeacher teacher = (TTeacher) httpSession.getAttribute("userInfo");
		if (teacher.getIsAdmin() == 0) {
			mav.addObject("info", "对不起，你不是管理员老师！");
			mav.setViewName("manage/info");
			return mav;
		} else {
			mav.addObject("fKey", 55);

			//1代表文件类型是：pdf
			PageInfo<TFileShow> pageInfo = fileShowService.findByPage(page, 6);
			mav.addObject("pageInfo", pageInfo);

			mav.setViewName("manage/jcManagement");
			return mav;
		}

	}
	
	@RequestMapping(value = "jcDelete/{id}/{page}", method = { RequestMethod.GET })
	public ModelAndView jcDelete(Model model, HttpServletRequest request,
			@PathVariable Integer id, @PathVariable Integer page) {

		ModelAndView mav = new ModelAndView();
		String url = request.getServletContext().getRealPath("").replace("\\", "/");
		fileShowService.targetDelete(id, url);
		mav.setViewName("redirect:/manage/file/jcManagement/" + page);
		return mav;
	}
	
	
	/**
	 * 师资力量
	 */
	@RequestMapping(value = "szllManagement/{page}", method = { RequestMethod.GET })
	public ModelAndView szllView(Model model, @PathVariable Integer page,
			HttpSession httpSession) {

		ModelAndView mav = new ModelAndView();
		TTeacher teacher = (TTeacher) httpSession.getAttribute("userInfo");
		if (teacher.getIsAdmin() == 0) {
			mav.addObject("info", "对不起，你不是管理员老师！");
			mav.setViewName("manage/info");
			return mav;
		} else {
			mav.addObject("fKey", 56);

			//1代表文件类型是：pdf
			PageInfo<TFileShow> pageInfo = fileShowService.findByPage(page, 7);
			mav.addObject("pageInfo", pageInfo);

			mav.setViewName("manage/szllManagement");
			return mav;
		}

	}
	
	@RequestMapping(value = "szllDelete/{id}/{page}", method = { RequestMethod.GET })
	public ModelAndView szllDelete(Model model, HttpServletRequest request,
			@PathVariable Integer id, @PathVariable Integer page) {

		ModelAndView mav = new ModelAndView();
		String url = request.getServletContext().getRealPath("").replace("\\", "/");
		fileShowService.targetDelete(id, url);
		mav.setViewName("redirect:/manage/file/szllManagement/" + page);
		return mav;
	}

	@RequestMapping(value = "pptManagement/{page}", method = { RequestMethod.GET })
	public ModelAndView pptView(Model model, @PathVariable Integer page,
			HttpSession httpSession) {

		ModelAndView mav = new ModelAndView();
		TTeacher teacher = (TTeacher) httpSession.getAttribute("userInfo");
		if (teacher.getIsAdmin() == 0) {
			mav.addObject("info", "对不起，你不是管理员老师！");
			mav.setViewName("manage/info");
			return mav;
		} else {
			List<TUnit> units = fileService.findUnit();
			mav.addObject("units", units);

			PageInfo<TFile> pageInfo = fileService.findByPage(page, 1);
			mav.addObject("pageInfo", pageInfo);

			mav.setViewName("manage/pptManagement");
			return mav;
		}

	}

	@RequestMapping(value = "pptDelete/{id}/{page}", method = { RequestMethod.GET })
	public ModelAndView pptDelete(Model model, HttpServletRequest request,
			@PathVariable Integer id, @PathVariable Integer page) {

		ModelAndView mav = new ModelAndView();
		String url = request.getServletContext().getRealPath("")
				.replace("\\", "/");
		fileService.pptDelete(id, url);
		mav.setViewName("redirect:/manage/file/pptManagement/" + page);
		return mav;
	}

	@RequestMapping(value = "videoManagement/{page}", method = { RequestMethod.GET })
	public ModelAndView videoView(Model model, @PathVariable Integer page,
			HttpSession httpSession) {

		ModelAndView mav = new ModelAndView();
		TTeacher teacher = (TTeacher) httpSession.getAttribute("userInfo");
		if (teacher.getIsAdmin() == 0) {
			mav.addObject("info", "对不起，你不是管理员老师！");
			mav.setViewName("manage/info");
			return mav;
		} else {
			List<TUnit> units = fileService.findUnit();
			mav.addObject("units", units);

			PageInfo<TFile> pageInfo = fileService.findByPage(page, 2);
			mav.addObject("pageInfo", pageInfo);
			mav.setViewName("manage/videoManagement");
			return mav;
		}
	}

	@RequestMapping(value = "videoDelete/{id}/{page}", method = { RequestMethod.GET })
	public ModelAndView videoDelete(Model model, HttpServletRequest request,
			@PathVariable Integer id, @PathVariable Integer page) {

		ModelAndView mav = new ModelAndView();
		String url = request.getServletContext().getRealPath("")
				.replace("\\", "/");
		fileService.pptDelete(id, url);

		mav.setViewName("redirect:/manage/file/videoManagement/" + page);
		return mav;
	}

	@RequestMapping(value = "documentManagement/{page}", method = { RequestMethod.GET })
	public ModelAndView documentView(Model model, @PathVariable Integer page,HttpSession httpSession) {

		ModelAndView mav = new ModelAndView();
		TTeacher teacher = (TTeacher) httpSession.getAttribute("userInfo");
		if (teacher.getIsAdmin() == 0) {
			mav.addObject("info", "对不起，你不是管理员老师！");
			mav.setViewName("manage/info");
			return mav;
		} else {
			List<TUnit> units = fileService.findUnit();
			mav.addObject("units", units);

			PageInfo<TFile> pageInfo = fileService.findByPage(page, 3);
			mav.addObject("pageInfo", pageInfo);
			mav.setViewName("manage/documentManagement");
			return mav;
		}

	}

	@RequestMapping(value = "documentDelete/{id}/{page}", method = { RequestMethod.GET })
	public ModelAndView documentDelete(Model model, HttpServletRequest request,
			@PathVariable Integer id, @PathVariable Integer page) {

		ModelAndView mav = new ModelAndView();
		String url = request.getServletContext().getRealPath("")
				.replace("\\", "/");
		fileService.pptDelete(id, url);

		mav.setViewName("redirect:/manage/file/documentManagement/" + page);
		return mav;
	}
}
