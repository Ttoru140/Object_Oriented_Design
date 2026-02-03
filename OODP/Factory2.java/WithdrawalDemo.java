// Abstract Handler
abstract class AuthorizationHandler {
    protected AuthorizationHandler nextHandler;

    // Set next handler in chain
    public void setNextHandler(AuthorizationHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    // Process withdrawal request
    public abstract void authorize(double amount);
}

// Concrete Handler: Cashier
class Cashier extends AuthorizationHandler {
    @Override
    public void authorize(double amount) {
        if (amount < 10000) {
            System.out.println("Cashier authorized withdrawal of Tk " + amount);
        } else if (nextHandler != null) {
            nextHandler.authorize(amount); // pass to next handler
        }
    }
}

// Concrete Handler: Senior Officer
class SeniorOfficer extends AuthorizationHandler {
    @Override
    public void authorize(double amount) {
        if (amount >= 10000 && amount <= 1000000) {
            System.out.println("Senior Officer authorized withdrawal of Tk " + amount);
        } else if (amount > 1000000 && nextHandler != null) {
            nextHandler.authorize(amount); // pass to next handler
        }
    }
}

// Concrete Handler: Manager
class Manager extends AuthorizationHandler {
    @Override
    public void authorize(double amount) {
        if (amount > 1000000) {
            System.out.println("Manager authorized withdrawal of Tk " + amount);
        } else {
            System.out.println("Withdrawal of Tk " + amount + " does not require Manager approval");
        }
    }
}

// Client class
public class WithdrawalDemo {
    public static void main(String[] args) {

        // Create handlers
        AuthorizationHandler cashier = new Cashier();
        AuthorizationHandler seniorOfficer = new SeniorOfficer();
        AuthorizationHandler manager = new Manager();

        // Set chain: Cashier -> Senior Officer -> Manager
        cashier.setNextHandler(seniorOfficer);
        seniorOfficer.setNextHandler(manager);

        // Test withdrawals
        double[] amounts = {5000, 50000, 2000000};

        for (double amount : amounts) {
            System.out.println("\nRequesting withdrawal of Tk " + amount);
            cashier.authorize(amount); // start from the head of chain
        }
    }
}
