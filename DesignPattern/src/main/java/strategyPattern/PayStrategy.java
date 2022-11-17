package strategyPattern;

/* 결제 메서드들의 공통 인터페이스 */

public interface PayStrategy {

    boolean pay(int paymentAmount);

    void collectPaymentDetails();

}
