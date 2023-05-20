package sample.cafekiosk.spring.domain.order;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import sample.cafekiosk.spring.IntegrationTestSupport;
import sample.cafekiosk.spring.domain.orderproduct.OrderProductRepository;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;
import sample.cafekiosk.spring.domain.product.ProductType;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static sample.cafekiosk.spring.domain.order.OrderStatus.PAYMENT_COMPLETED;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.SELLING;
import static sample.cafekiosk.spring.domain.product.ProductType.HANDMADE;

//@DataJpaTest
//@ActiveProfiles("test")
class OrderRepositoryTest extends IntegrationTestSupport {
    @Autowired
    private OrderProductRepository orderProductRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    @AfterEach
    void tearDown() {
        orderProductRepository.deleteAllInBatch();
        orderRepository.deleteAllInBatch();
        productRepository.deleteAllInBatch();
    }

    @DisplayName("주문에서 지정한 기간과 주문 상태값으로 데이터 뽑아오기")
    @Test
    void findOrdersBy() {
        // given
        LocalDateTime now = LocalDateTime.of(2023, 5, 20, 0, 0);

        Product product1 = createProduct(HANDMADE, "001", 1000);
        Product product2 = createProduct(HANDMADE, "002", 2000);
        Product product3 = createProduct(HANDMADE, "003", 3000);
        List<Product> products = List.of(product1, product2, product3);
        productRepository.saveAll(products);

        Order order1 = createOrder(LocalDateTime.of(2023, 5, 19, 23, 59,59), products);
        Order order2 = createOrder(now, products);
        Order order3 = createOrder(LocalDateTime.of(2023, 5, 20, 23, 59,59), products);
        Order order4 = createOrder(LocalDateTime.of(2023, 5, 21, 0, 0), products);
        orderRepository.saveAll(List.of(order1, order2, order3, order4));

        // when
        List<Order> orders = orderRepository.findOrdersBy(now, now.plusDays(1), PAYMENT_COMPLETED);

        // then
        assertThat(orders).hasSize(2)
                .extracting("totalPrice")
                .contains(6000, 6000);
    }

    private Order createOrder(LocalDateTime now, List<Product> products) {
        return Order.builder()
                .products(products)
                .orderStatus(PAYMENT_COMPLETED)
                .registeredDateTime(now)
                .build();
    }

    private Product createProduct(ProductType type, String productNumber, int price) {
        return Product.builder()
                .type(type)
                .productNumber(productNumber)
                .price(price)
                .sellingStatus(SELLING)
                .name("메뉴 이름")
                .build();
    }
}
