//package com.kh.springJpa241217.entity;
//
//import org.springframework.data.annotation.Id;
//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.elasticsearch.annotations.FieldType;
//
//@Document(indexName = "movie")
//public class Movie {
//
//    @Id
//    private String id;
//
//    // movieName 필드만 Elasticsearch에 인덱싱하도록 설정
//    @Field(type = FieldType.Text)
//    private String movieName;
//
//    @Field(type = FieldType.Text)  // 예시로 director도 매핑
//    private String director;
//
//    // 기본 생성자, getter, setter
//    public Movie() {}
//
//    public Movie(String id, String movieName, String director) {
//        this.id = id;
//        this.movieName = movieName;
//        this.director = director;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getMovieName() {
//        return movieName;
//    }
//
//    public void setMovieName(String movieName) {
//        this.movieName = movieName;
//    }
//
//    public String getDirector() {
//        return director;
//    }
//
//    public void setDirector(String director) {
//        this.director = director;
//    }
//}
