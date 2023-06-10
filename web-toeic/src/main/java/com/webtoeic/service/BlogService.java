package com.webtoeic.service;

import com.webtoeic.entities.Blog;
import com.webtoeic.entities.NguoiDung;
import com.webtoeic.entities.VaiTro;
import com.webtoeic.repository.BlogRepository;
import com.webtoeic.repository.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

public interface BlogService {


    List<Blog> getAllBlog() ;

    void save(Blog blog);

    void delete(int id);

    List<Blog> getBlog(int id);
}
