package com.newlecture.web.controller.admin.board;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;
import javax.mail.Multipart;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.mysql.fabric.xmlrpc.base.Array;
import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;
import com.newlecture.web.service.NoticeService;

@Controller("adminNoticeController")
@RequestMapping("/admin/board/notice/")
public class NoticeContoller {

	@Autowired
	private NoticeService noticeService;

	@GetMapping("{id}/edit")
	public String edit(@PathVariable("id") Integer id, Model model) {

		Notice notice = noticeService.getNotice(id);
		model.addAttribute("n", notice);

		// return "admin/board/notice/edit";
		return "admin.board.notice.edit";
	}

	@PostMapping("{id}/edit")
	public String edit(Integer id, String title, String content) {

		Notice notice = noticeService.getNotice(id);
		notice.setContent(content);
		notice.setTitle(title);

		noticeService.updateNotice(notice);

		return "redirect:../" + notice.getId();
	}

	@GetMapping("{id}/del")
	public String delete(@PathVariable("id") Integer id) {

		noticeService.deleteNotice(id);

		return "redirect:../list";
	}

	@GetMapping("reg")
	// @RequestMapping(value="reg", method=RequestMethod.GET)
	public String reg() {

		// return "admin/board/notice/reg";
		return "admin.board.notice.reg";
	}

	@PostMapping("reg")
	// @RequestMapping(value="reg", method=RequestMethod.POST)
	public String reg(Notice notice, MultipartFile file, Principal principal, HttpServletRequest request) {

		notice.setFiles(file.getOriginalFilename());

		String fileNames = "";
		String fileName = file.getOriginalFilename();
		fileNames += fileName + ",";

		ServletContext appication = request.getServletContext();
		String urlPath = "/upload";
		String realPath = appication.getRealPath(urlPath);

		File file1 = new File(realPath);
		if (!file1.exists())
			file1.mkdirs();
		else
			System.out.println("경로가 존재합니다.");
		System.out.println(realPath);

		InputStream fis;
		try {
			fis = file.getInputStream();
			OutputStream fos = new FileOutputStream(realPath + File.separator + fileName);

			System.out.println(fileName);

			byte[] buf = new byte[1024];
			int size = fis.read(buf);
			if ((size = fis.read(buf)) != -1)
				fos.write(buf, 0, size);

			fos.close();
		} catch (IOException e) {
			return "redirect:/error?code=500";
		}

		// System.out.println(file.getOriginalFilename());
		System.out.println(notice);
		// ������ ���� : Spring Security lib
		notice.setWriterId(principal.getName());
		noticeService.insertNotice(notice);

		return "redirect:list"; // -> "/WEB-INF/view/" + "list" + ".jsp";
	}

	@GetMapping("list")
	public String list(@RequestParam(defaultValue = "1", name = "p") Integer page, Model model) {

		System.out.println("page:" + page);
		// this.getClass().getMethods()[0].getParameters()[0].getName()

		List<NoticeView> list = noticeService.getNoticeList();
		model.addAttribute("list", list);

		return "admin.board.notice.list";
		// return "admin/board/notice/list";
	}

	@PostMapping("list")
	public String list(String cmd, @RequestParam("del-id") int[] ids) {

		switch (cmd) {
		case "�ϰ�����":
			noticeService.deleteNoticeList(ids);
			break;
		}

		System.out.println(cmd + ", " + ids);

		return "redirect:list";
	}

	@GetMapping("{id}")
	public String detail(@PathVariable("id") Integer id, Model model) {

		model.addAttribute("n", noticeService.getNotice(id));

		// return "admin/board/notice/detail";
		return "admin.board.notice.detail";
	}

	@PostMapping("upload")
	@ResponseBody
	public String upload(MultipartFile file, HttpServletRequest request) {

		String fileName = file.getOriginalFilename();

		ServletContext application = request.getServletContext();
		String urlPath = "/resource/upload";
		String realPath = application.getRealPath(urlPath);

		File file1 = new File(realPath);
		if (!file1.exists())
			file1.mkdirs();
		else
			System.out.println("경로가 존재합니다.");

		System.out.println(realPath);

		InputStream fis;
		try {
			fis = file.getInputStream();

			OutputStream fos = new FileOutputStream(realPath + File.separator + fileName);

			byte[] buf = new byte[1024];

			int size = 0;
			while ((size = fis.read(buf)) != -1)
				fos.write(buf, 0, size);

			fos.close();

		} catch (IOException e) {
			return "redirect:/error?code=500";
		}

		return "ok";
	}

	@GetMapping("explorer")
	@ResponseBody
	public String explorer(String path, HttpServletRequest request) {

		ServletContext application = request.getServletContext();
		String urlPath = path;
		String realPath = application.getRealPath(urlPath);

		File pathFile = new File(realPath);
		File[] files = pathFile.listFiles();
		
		List<Map<String,String>> list = new ArrayList<>();
		for (File f : files) {
			Map<String,String> map = new HashMap<>();
	    	map.put("name",f.getName());
	    	map.put("size", String.valueOf(f.length()));
	    	
	    	if(f.length()>0)
	        	{
	    		double bytes = f.length();
	    		double kb = bytes/1024;
	        	}
	    	
//	    	String mimetype = URLConnection.guessContentTypeFromName(f.getName());
	    	//이름을 가지고 확장자를 꺼내서 붙여줌
            //웹에서 
	    	MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
	    	String mimetype = fileTypeMap.getContentType(f);
	    	//확장자 알려주는 다른 방법
	    	
	    	map.put("type", mimetype);
	    	//double 
	    	
	    	list.add(map);
//			System.out.println(f.getName());
//		    f.get}
		}
		
		String json = new Gson().toJson(list); 
		return json;
		
	}
}
