package jpabook.jpashop.service;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.fail;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    @Test
    public void 상품주문() {

        //given
        Member member = createMember();
        Item item = createBook("시골 JPA", 10000, 10);
        int orderCount = 2;

        //when
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals(OrderStatus.ORDER, getOrder.getStatus(),"상품 주문시 상태는 ORDER");
        assertEquals(1,getOrder.getOrderItems().size(),"주문한 상품 종류 수가 정확해야 한다.");
        assertEquals(10000 * 2, getOrder.getTotalPrice(),"주문 가격은 가격 * 수량이다.");
        assertEquals(8, item.getStockQuantity(),"주문 수량만큼 재고가 줄어야 한다.");

    }

//    @Test
//    public void 상품주문_재고수량초과() {
//
//        //given
//        Member member = createMember();
//        Item item = createBook("시골 JPA", 10000, 10);
//
//        int orderCount = 11; //재고보다 많은 수량
//
//        //when
//        orderService.order(member.getId(), item.getId(), orderCount)
//
//        //then
//        fail("재고 수량 부족 예외가 발생해야 한다.");
//    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setStockQuantity(stockQuantity);
        book.setPrice(price);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);
        return member;
    }


}