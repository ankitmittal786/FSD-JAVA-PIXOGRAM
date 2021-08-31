package com.pixogram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pixogram.entity.Media;

@Repository
public interface MediaRepository extends JpaRepository<Media, Integer>{

}
