package com.example.democustomermanagement.repository;

import com.example.democustomermanagement.model.Province;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IProvinceRepository extends PagingAndSortingRepository<Province, Long> {
}
