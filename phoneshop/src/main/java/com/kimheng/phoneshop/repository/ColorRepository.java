package com.kimheng.phoneshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kimheng.phoneshop.entity.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, Integer>{

}
