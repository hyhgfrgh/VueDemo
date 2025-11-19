package com.wanli.hellospringboot.repository;

import com.wanli.hellospringboot.entity.goods_info;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsRepository extends JpaRepository<goods_info,Long> {


    void deleteByName(String name);

    List<goods_info> findByName(String name);
}
