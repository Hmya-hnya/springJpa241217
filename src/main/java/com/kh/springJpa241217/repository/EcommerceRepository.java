package com.kh.springJpa241217.repository;

import com.kh.springJpa241217.entity.EcommerceData;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EcommerceRepository extends ElasticsearchRepository<EcommerceData, String> {

}
