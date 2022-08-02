package study.tdd.chap03;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class PayData {
    private LocalDate firstBillingDate;
    private LocalDate billingDate;
    private int payAmount;

    public PayData() {
    }

    public PayData(LocalDate firstBillingDate, LocalDate billingDate, int payAmount) {
        this.firstBillingDate = firstBillingDate;
        this.billingDate = billingDate;
        this.payAmount = payAmount;
    }

//    public LocalDate getBillingDate() {
//        return billingDate;
//    }
//
//    public int getPayAmount() {
//        return payAmount;
//    }
//
//    public static Builder builder() {
//        return new Builder();
//    }
//
//    public static class Builder{
//        private PayData data = new PayData();
//
//        public Builder billingDate(LocalDate billingDate) {
//            data.billingDate = billingDate;
//            return this;
//        }
//
//        public Builder payAmount(int payAmount) {
//            data.payAmount = payAmount;
//            return this;
//        }
//
//        public PayData build() {
//            return data;
//        }
//    }
}
