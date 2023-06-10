package com.webtoeic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.webtoeic.entities.BaiThiThu;
import com.webtoeic.entities.CauHoiBaiThiThu;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CauHoiBaiThiThuRespository extends JpaRepository<CauHoiBaiThiThu,Integer>{
	
	List<CauHoiBaiThiThu> findByBaithithu(BaiThiThu baithithu);

    @Transactional
    @Modifying
    @Query("delete from CauHoiBaiThiThu c where c.baithithu.baithithuid =:baithithuid")
    void deleteByBaithithuid(@Param("baithithuid") Integer baithithuid);
}
