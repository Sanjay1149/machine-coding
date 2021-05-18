package splitWise.modal;

public class PendingPayment {

    String currentUser;
    String otherUser;
    int remainingBalance;
    PaymentStatus status;

    public PendingPayment(String currentUser, String otherUser, int remainingBalance, PaymentStatus paymentStatus) {
        this.currentUser = currentUser;
        this.otherUser = otherUser;
        this.remainingBalance = remainingBalance;
        this.status = paymentStatus;
    }

    public void revisePayment(int amountAdded) {
        remainingBalance += amountAdded;
        if ( remainingBalance > 0 ) {
            status = PaymentStatus.NEED;
        } else if ( remainingBalance == 0 ) {
            status = PaymentStatus.NO_ACTION;
        } else {
            status = PaymentStatus.OWE;
        }
    }

    @Override
    public String toString() {
        return "PendingPayment{" +
                "currentUser='" + currentUser + '\'' +
                ", otherUser='" + otherUser + '\'' +
                ", remainingBalance=" + remainingBalance +
                ", status=" + status +
                '}';
    }
}
