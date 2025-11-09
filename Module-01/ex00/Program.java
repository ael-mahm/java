class Program {
    public static void main(String[] args) {
        User mike = new User("Mike", 1, 555.0);
        User john = new User("John", 2, 6000.0);

        Transaction mtoj = new Transaction(mike, john, -300.0, TransactionCategory.OUTGOING);
        Transaction jtom = new Transaction(john, mike, 300.0, TransactionCategory.INCOMING);

        System.out.println(
                "First User: " + mike.getName() + ", ID: " + mike.getIdentifier() + ", Balance: " + mike.getBalance());
        System.out.println(
                "Second User: " + john.getName() + ", ID: " + john.getIdentifier() + ", Balance: " + john.getBalance());
        System.out.println(
                "Transaction from Mike to John: ID: " + mtoj.getIdentifier() + ", Amount: " + mtoj.getAmount());
        System.out.println(
                "Transaction from John to Mike: ID: " + jtom.getIdentifier() + ", Amount: " + jtom.getAmount());
    }
}