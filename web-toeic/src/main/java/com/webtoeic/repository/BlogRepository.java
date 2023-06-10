package com.webtoeic.repository;

import com.webtoeic.entities.BaiGrammar;
import com.webtoeic.entities.Blog;
import com.webtoeic.entities.NguoiDung;
import com.webtoeic.entities.VaiTro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {

    List<Blog> findByid(int id);
}
