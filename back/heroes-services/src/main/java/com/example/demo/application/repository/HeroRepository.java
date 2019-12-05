package com.example.demo.application.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.application.bean.Hero;

@Repository
public interface HeroRepository extends CrudRepository<Hero, Long> {

}
