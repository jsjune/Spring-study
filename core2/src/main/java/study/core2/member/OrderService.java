package study.core2.member;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
