class TransactionNotFoundException extends RuntimeException {
    TransactionNotFoundException(String msg) {
        super(msg);
    }
}