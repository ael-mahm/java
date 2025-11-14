import java.util.UUID;

enum TransactionCategory {
    OUTGOING,
    INCOMING
}

class Transaction {
    private UUID Identifier;
    private User Sender;
    private User Recipient;
    private double Amount;
    private TransactionCategory Type;
    private Transaction next;

    public Transaction(User sender, User recipient, double amount, TransactionCategory type) {
        this.Identifier = UUID.randomUUID();
        this.Sender = sender;
        this.Recipient = recipient;
        if (type == TransactionCategory.OUTGOING && amount > 0) {
            System.out.println("the outgoing (negative amounts only)");
            System.exit(-1);
        } else if (type == TransactionCategory.INCOMING && amount < 0) {
            System.out.println("the incoming (positive amounts only)");
            System.exit(-1);
        }
        this.Type = type;
        this.Amount = amount;
    }

    public Transaction(User sender, User recipient, double amount, TransactionCategory type, UUID uuid) {
        this.Identifier = uuid;
        this.Sender = sender;
        this.Recipient = recipient;
        if (type == TransactionCategory.OUTGOING && amount > 0) {
            System.out.println("the outgoing (negative amounts only)");
            System.exit(-1);
        } else if (type == TransactionCategory.INCOMING && amount < 0) {
            System.out.println("the incoming (positive amounts only)");
            System.exit(-1);
        }
        this.Type = type;
        this.Amount = amount;
    }

    public UUID getIdentifier() {
        return Identifier;
    }

    public User getSender() {
        return Sender;
    }

    public User getRecipient() {
        return Recipient;
    }

    public double getAmount() {
        return Amount;
    }

    public void setSender(User sender) {
        this.Sender = sender;
    }

    public void setRecipient(User recipient) {
        this.Recipient = recipient;
    }

    public Transaction getNext() {
        return next;
    }

    public void setNext(Transaction tran) {
        this.next = tran;
    }
}