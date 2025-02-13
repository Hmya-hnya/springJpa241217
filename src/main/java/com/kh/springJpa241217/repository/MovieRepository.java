//package com.kh.springJpa241217.repository;
//
//import com.kh.springJpa241217.entity.Movie;
//import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import java.util.List;
//
//
//public interface MovieRepository extends ElasticsearchRepository<Movie, String> {
//
//    // movieName과 director만 조회하도록 쿼리
//    @Query("{\"_source\": [\"movieName\", \"director\"]}")
//    List<Movie> findMovieNameAndDirector();
//}
