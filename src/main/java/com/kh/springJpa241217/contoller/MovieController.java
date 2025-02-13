//package com.kh.springJpa241217.contoller;
//
//import com.kh.springJpa241217.entity.Movie;
//import com.kh.springJpa241217.repository.MovieRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//public class MovieController {
//
//    @Autowired
//    private MovieRepository movieRepository;
//
//    // movieName과 director만 조회하는 엔드포인트
//    @GetMapping("/movies")
//    public List<Movie> getMovies() {
//        return movieRepository.findMovieNameAndDirector();
//    }
//}
