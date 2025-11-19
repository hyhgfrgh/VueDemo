package com.wanli.hellospringboot.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class goods_info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String introduce;
    public String name;
    public BigDecimal price;
    public Integer stock;
    public String category;

    public String img_url;

}

