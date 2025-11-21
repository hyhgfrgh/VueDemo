package com.wanli.hellospringboot.controller;

import com.wanli.hellospringboot.entity.goods_info;
import com.wanli.hellospringboot.repository.GoodsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class Controller {

    @Autowired
    private GoodsRepository repo;

    @GetMapping("/add")
    public void add(String name, BigDecimal price, Integer stock, String category, String img_url,String introduce){
        goods_info u = new goods_info();
        u.name = name;u.stock = stock;
        u.price = price;u.category = category;
        u.img_url = img_url;u.introduce = introduce;
        repo.save(u);
    }

    @GetMapping("/del")
    @Transactional
    public void del(String name){
        repo.deleteByName(name);
    }

    @GetMapping("/delAll")
    public void delAll(){
        repo.deleteAll();
    }

    @GetMapping("/list")
    public List<goods_info>  list(){
        return repo.findAll();
    }

    @GetMapping("/update")
    public void update(Long id,String name, BigDecimal price, Integer stock, String category, String img_url,String introduce){
        Optional<goods_info> u = repo.findById(id);
        if(u.isPresent()){
            goods_info good = u.get();
            if(!(name == null || name.isEmpty())) good.name = name;
            if(!(introduce == null || introduce.isEmpty())) good.introduce = introduce;
            if(!(stock == null)) good.stock = stock;
            if(!(price == null)) good.price = price;
            if(!(category == null || category.isEmpty())) good.category = category;
            if(!(img_url == null || img_url.isEmpty())) good.img_url = img_url;
        }
    }




}
