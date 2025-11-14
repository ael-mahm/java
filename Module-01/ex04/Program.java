class Program {
        public static void main(String[] args) {
                User mike = new User("Mike", 1, 555.0);
                User john = new User("John", 2, 6000.0);
                TransactionsService sr = new TransactionsService();

                sr.setUser(mike);
                sr.setUser(john);

                sr.transferTransaction(300.0, mike.getIdentifier(), john.getIdentifier());

                System.out.println(
                                "First User: " + mike.getName() + ", ID: " + mike.getIdentifier() + ", Balance: "
                                                + mike.getBalance());
                System.out.println(
                                "Second User: " + john.getName() + ", ID: " + john.getIdentifier() + ", Balance: "
                                                + john.getBalance());

                System.out.println("Balance: " + sr.getUserBalance(mike.getIdentifier()));
                System.out.println("Balance: " + sr.getUserBalance(john.getIdentifier()));
                Transaction[] unpaired = sr.checkValidity();
                System.out.println("Unpaired count = " + unpaired.length);
                System.out.println("=====================================");
                for (int i = 0; i < unpaired.length; i++) {
                        System.out.println("Id:" + unpaired[i].getIdentifier());
                        System.out.println("Amount:" + unpaired[i].getAmount());
                        System.out.println("Sender:" + unpaired[i].getSender().getName());
                        System.out.println("Recipient:" + unpaired[i].getRecipient().getName());
                        System.out.println("Transaction:" + unpaired[i].getTransactionCategory());
                        System.out.println("=====================================");
                }
        }
}
