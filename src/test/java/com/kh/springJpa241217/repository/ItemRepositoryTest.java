package com.kh.springJpa241217.repository;

import com.kh.springJpa241217.constant.ItemSellStatus;
import com.kh.springJpa241217.entity.Item;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository; // 필드로 생성자 주입

    @Test
    @DisplayName("상품 저장 테스트")   // 테스트 이름
    public void createItemTest() {
        for(int i = 1; i <= 10; i++) {
            Item item = new Item();
            item.setItemNum("테스트 상품" + i);
            item.setPrice(1000 * i);
            item.setItemDetail("테스트 상품 상세설명" + i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            Item saveItem = itemRepository.save(item);
            log.info("Item 저장: {}", saveItem);
        }
    }
    @Test
    @DisplayName("상품조회 테스트")
    public void findByItemNumTest() {
        this.createItemTest(); // 10개의 상품 생성
        List<Item> itemList = itemRepository.findByItemNum("테스트 상품5");
        for(Item item : itemList) {
            log.info("상품조회 테스트: {}", item);
        }
    }
    @Test
    @DisplayName("상품명이나 상품 상세 설명으로 조회하기")
    public void findByItemDetail() {
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemNumOrItemDetail("테스트 상품5", "테스트 상품 상세설명3");
        for(Item item : itemList) {
            log.info("상품 상세조회 테스트: {}", item);
        }
    }
    @Test
    @DisplayName("가격조회")
    public void findByPrice() {
        this.createItemTest();
        List<Item> itemList = itemRepository.findByPriceLessThan(5000);
        for(Item item : itemList) {
            log.info("상품 가격조회 테스트: {}", item);
        }
    }
    @Test
    @DisplayName("상태변경 및 가격조회")
    public void updateStatusAndPrice() {
        this.createItemTest();
        List<Item> itemList = itemRepository
                .findByPriceGreaterThanEqualAndItemSellStatus(5000, ItemSellStatus.SELL);
        for(Item item : itemList) {
            log.info("상품 상태변경 및 가격조회 테스트: {}", item);
        }
    }
    @Test
    @DisplayName("내림차순 정렬")
    public void sortPrice() {
        this.createItemTest();
        List<Item> itemList = itemRepository.findByPrice();
        for(Item item : itemList) {
            log.info("내림차순 정렬: {}", item);
        }
    }
}