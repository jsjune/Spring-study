package book.object.chapter01.step01;

public class Bag {
    private Long amount; //현금
    private Invitation invitation;
    private Ticket ticket;

    //현금만 가지고 있다.
    public Bag(Long amount) {
        this(null, amount);
    }

    //현금과 초대장을 함께 가지고 있다.
    public Bag(Invitation invitation, long amount) {
        this.invitation = invitation;
        this.amount = amount;
    }

    public boolean hasInvitation() {
        return invitation != null;
    }

    public boolean hasTicket() {
        return ticket != null;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public void minusAmount(Long amount) {
        this.amount -= amount;
    }

    public void plusAmount(Long amount) {
        this.amount += amount;
    }
}