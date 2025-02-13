//package com.kh.springJpa241217.service;
//
//import com.kh.springJpa241217.entity.Movie;
//import com.kh.springJpa241217.repository.MovieRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MovieService {
//
//    @Autowired
//    private MovieRepository movieRepository;
//
//    public void saveMovie(Movie movie) {
//        movieRepository.save(movie);  // 영화 데이터를 Elasticsearch에 저장
//    }
//
//    public Iterable<Movie> getAllMovies() {
//        return movieRepository.findAll();  // 모든 영화 데이터를 검색
//    }
//}
