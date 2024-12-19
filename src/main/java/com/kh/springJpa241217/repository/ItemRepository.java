package com.kh.springJpa241217.repository;

import com.kh.springJpa241217.constant.ItemSellStatus;
import com.kh.springJpa241217.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    // 상품명으로 조회하기
    List<Item> findByItemNum(String itemNum);

    // 상품명이나 상품 상세 설명으로 조회하기: OR
    List<Item> findByItemNumOrItemDetail(String itemNum, String ItemDetail);

    // 설정된 가격: 5,000 미만의 상품 조회하기
    List<Item> findByPriceLessThan(int Price);

    // 상품 판매 상태를 짝수는 SELL, 홀수는 SOLD_OUT 으로 변경하고,
    // 가격이 5,000원 이상이고 판매 중인 상품만 조회하기
    List<Item> findByPriceGreaterThanEqualAndItemSellStatus(int price, ItemSellStatus itemSellStatus);

    // 상품 가격에 대한 내림차순 정렬하기
    List<Item> findByPrice();

    // 상품 이름에 특정 키워드가 포함된 상품 검색
    List<Item> findByItemNumContaining(String itemNum);

    // 상품명과 가격이 일치하는 상품 검색(AND)
    List<Item> findByItemNumAndPrice (String itemNum, int price);
}