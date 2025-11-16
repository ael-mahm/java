public interface TransactionsList {
    void addTransaction(Transaction tran);

    void removeTransactionById(String id);

    Transaction[] toArray();

    Transaction getTransactionById(String id);
}