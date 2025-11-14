import java.util.UUID;

class TransactionsService {
    private UsersArrayList users = new UsersArrayList();

    public TransactionsService() {

    }

    public void setUser(User user) {
        this.users.addUser(user);
    }

    public double getUserBalance(int userId) {
        return users.getUserById(userId).getBalance();
    }

    public void transferTransaction(double amount, int senderId, int recipientId) {
        User sender = users.getUserById(senderId);
        User recipient = users.getUserById(recipientId);
        if (sender.getBalance() < amount) {
            throw new IllegalTransactionException("Balance less than amount");
        }
        UUID tranId = UUID.randomUUID();
        Transaction outgoing = new Transaction(sender, recipient, -amount, TransactionCategory.OUTGOING, tranId);
        Transaction incoming = new Transaction(sender, recipient, amount, TransactionCategory.INCOMING, tranId);
        sender.setBalance(sender.getBalance() - amount);
        recipient.setBalance(recipient.getBalance() + amount);
        sender.getTransactionsList().addTransaction(outgoing);
        recipient.getTransactionsList().addTransaction(incoming);
    }

    public boolean checkValidity() {
        return false;
    }
}