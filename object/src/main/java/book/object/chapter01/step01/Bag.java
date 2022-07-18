package book.object.chapter01.step01;

public class Bag {
    private Long amount;
    private Invitation invitation;
    private Ticket ticket;

    // 초대장 없이 현금만 보관하는 경우
    public Bag(long amount) {
        this(amount, null);
    }

    // 현금과 초대장을 함께 보관하는 경우
    public Bag(long amount, Invitation invitation) {
        this.amount = amount;
        this.invitation = invitation;
    }

    public boolean hasInvitation() {
        return invitation != null;
    }

    public boolean hasTicket() {
        return ticket != null;
    }

    public void setTicket(Ticket ticket) {
        this.ticket=ticket;
    }

    public void minusAmount(Long amount) {
        this.amount -= amount;
    }

    public void plusAmount(Long amount) {
        this.amount += amount;
    }

}
