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

    private boolean moreChecks(Transaction t1, Transaction t2) {
        if ((t1.getAmount() != (-1 * t2.getAmount())) || t1.getTransactionCategory() == t2.getTransactionCategory())
            return false;
        if (t1.getSender().getIdentifier() != t2.getSender().getIdentifier()
                || t1.getRecipient().getIdentifier() != t2.getRecipient().getIdentifier())
            return false;
        return true;
    }

    public Transaction[] checkValidity() {
        int numberOfUsers = this.users.getNumberOfUsers();
        int totalMatched = 0;
        int total = 0;
        int index = 0;

        for (int i = 0; i < numberOfUsers; i++) {
            total += this.users.getUserByIndex(i).getTransactionsList().toArray().length;
        }

        Transaction[] allTransaction = new Transaction[total];
        boolean[] unpairedTransactions = new boolean[total];
        for (int i = 0; i < numberOfUsers; i++) {
            User tempUser = this.users.getUserByIndex(i);
            Transaction[] tempTransaction = tempUser.getTransactionsList().toArray();
            for (int j = 0; j < tempTransaction.length; j++) {
                allTransaction[index++] = tempTransaction[j];
            }
        }

        for (int i = 0; i < total; i++) {
            if (unpairedTransactions[i] == true)
                continue;
            for (int j = i + 1; j < total; j++) {
                if (allTransaction[i].getIdentifier().toString().equals(allTransaction[j].getIdentifier().toString())
                        && moreChecks(allTransaction[i], allTransaction[j])) {
                    unpairedTransactions[i] = true;
                    unpairedTransactions[j] = true;
                    totalMatched++;
                    break;
                }
            }
        }

        Transaction[] result = new Transaction[totalMatched * 2];
        int j = 0;

        for (int i = 0; i < total; i++) {
            if (unpairedTransactions[i] == true) {
                result[j] = allTransaction[i];
                j++;
            }
        }

        return result;
    }
}