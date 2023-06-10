package com.webtoeic.repository;

import java.util.List;

import com.webtoeic.entities.BaiGrammar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.webtoeic.entities.BaiTapTuVung;
import com.webtoeic.entities.NoiDungBaiTapTuVung;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface NoiDungBaiTapTuVungRepository extends JpaRepository<NoiDungBaiTapTuVung,Integer>{

	List<NoiDungBaiTapTuVung> findByBaitaptuvung(BaiTapTuVung baitaptuvung);


    @Transactional
    @Modifying
    @Query("delete from NoiDungBaiTapTuVung n where n.baitaptuvung.baitaptuvungid =:baitaptuvungid")
    void deleteByBaitaptuvungid(@Param("baitaptuvungid") Integer baitaptuvungid);


}
