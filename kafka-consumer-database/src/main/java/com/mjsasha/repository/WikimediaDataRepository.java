package com.mjsasha.repository;

import com.mjsasha.entity.WikimediaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikimediaDataRepository extends JpaRepository<WikimediaData, Long> {
}
