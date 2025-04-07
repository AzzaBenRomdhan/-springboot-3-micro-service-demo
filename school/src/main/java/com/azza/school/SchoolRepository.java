package com.azza.school;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface SchoolRepository extends JpaRepository<School, Integer> {
}
