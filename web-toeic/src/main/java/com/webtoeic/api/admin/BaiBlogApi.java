package com.webtoeic.api.admin;

import com.webtoeic.entities.BaiGrammar;
import com.webtoeic.entities.Blog;
import com.webtoeic.service.BaiGrammarService;
import com.webtoeic.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin/blog")
public class BaiBlogApi {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private BaiGrammarService baigrammarService;

	@Autowired
	private BlogService blogService;

	@GetMapping("/loadBlog")
	public List<String> showAllBlog() {

		List<Blog> list = blogService.getAllBlog();

		List<String> response = new ArrayList<String>();

		for (int i = 0; i < list.size(); i++) {
			String json = "id:" + list.get(i).getId() + "," + "hinhanh:"
					+ list.get(i).getHinhanh() + "," + "tieude:"
					+ list.get(i).getTieude();

			response.add(json);
		}

		return response;

	}

	@RequestMapping(value = "/delete/{idBlog}")
	public String deleteById(@PathVariable("idBlog") int id) {
		blogService.delete(id);
		return "success";
	}
	
	// get info Grammar ->edit Grammar
	@RequestMapping(value = "/infoBlog/{idBlog}")
	public String infoBlogById(@PathVariable("idBlog") int id) {
		Blog blog = blogService.getBlog(id).get(0);
		
		String json = "tieude==" + blog.getTieude() + "|" + "hinhanh=="
				+ blog.getHinhanh() + "|" + "mota==" + blog.getMota()
				+ "|" + "noidung==" + blog.getNoidung_MarkDown();
		
		return json;
	}
	

	@PostMapping(value = "/save")
	@ResponseBody
	public List<String> addBlog(
			@RequestParam("file_hinhanh") MultipartFile file_hinhanh,
			@RequestParam("tieude") String tieude,
			@RequestParam("mota") String mota,
			@RequestParam("contentMarkdown") String contentMarkdown,
			@RequestParam("contentHtml") String contentHtml) {

		List<String> response = new ArrayList<String>();

		String rootDirectory = request.getSession().getServletContext().getRealPath("/");

		Blog blog = new Blog();
		blogService.save(blog);

		try {	
			// save file upload to local folder
			Path pathImage = Paths.get(rootDirectory + "/resources/file/images/blog/" + "" + blog.getId() + "."
					+ file_hinhanh.getOriginalFilename());
			file_hinhanh.transferTo(new File(pathImage.toString()));
			blog.setHinhanh(blog.getId() + "." + file_hinhanh.getOriginalFilename());

			blog.setTieude(tieude);
			blog.setMota(mota);
			blog.setNoidung_MarkDown(contentMarkdown);
			blog.setNoidung_HTML(contentHtml);
			LocalDateTime datetime = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			Timestamp formattedDateTime = Timestamp.valueOf(datetime.format(formatter));
			blog.setNgaydang(formattedDateTime);

			blogService.save(blog);

		} catch (Exception e) {
			response.add(e.toString());
			System.out.println("ErrorAddBlog:" + e);

		}

		return response;
	}
	
	
	@PostMapping(value = "/update")
	@ResponseBody
	public List<String> updateBaiGrammar(
			@RequestParam("idBlog") int id,
			@RequestParam(name = "file_hinhanh", required = false) MultipartFile file_hinhanh,
			@RequestParam("tieude") String tieude,
			@RequestParam("mota") String mota,
			@RequestParam("contentMarkdown") String noidungMarkdown,
			@RequestParam("contentHtml") String noidungHtml) {

		List<String> response = new ArrayList<String>();

		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		Blog blog = blogService.getBlog(id).get(0);
		blogService.save(blog);
		try {	
			// save file upload to local folder
			if(file_hinhanh != null && !file_hinhanh.equals("undefined")) {
			Path pathImage = Paths.get(rootDirectory + "/resources/file/images/blog/" + "" + blog.getId() + "."
					+ file_hinhanh.getOriginalFilename());
			file_hinhanh.transferTo(new File(pathImage.toString()));
			blog.setHinhanh(blog.getId() + "." + file_hinhanh.getOriginalFilename());
			}

			blog.setTieude(tieude);
			blog.setMota(mota);
			blog.setNoidung_MarkDown(noidungMarkdown);
			blog.setNoidung_HTML(noidungHtml);

			blogService.save(blog);

		} catch (Exception e) {
			response.add(e.toString());
			System.out.println("ErrorAddBlog:" + e);

		}

		return response;
	}


}
