package splitWise.modal;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class PendingPayment {

    String currentUser;
    String otherUser;
    double remainingBalance;
    PaymentStatus status;

    public PendingPayment(String currentUser, String otherUser, double remainingBalance, PaymentStatus paymentStatus) {
        this.currentUser = currentUser;
        this.otherUser = otherUser;
        this.remainingBalance = remainingBalance;
        this.status = paymentStatus;
    }

    public void revisePayment(double amountAdded) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        remainingBalance = Double.parseDouble(decimalFormat.format(remainingBalance + amountAdded));
        if ( remainingBalance > 0 ) {
            status = PaymentStatus.NEED;
        } else if ( remainingBalance == 0 ) {
            status = PaymentStatus.NO_ACTION;
        } else {
            status = PaymentStatus.OWE;
        }
    }

    public double getRemainingBalance() {
        return remainingBalance;
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
