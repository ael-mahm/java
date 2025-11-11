
class User {
    private int Identifier;
    private String Name;
    private double Balance;

    public User(String name, int identifier, double balance) {
        if (balance < 0) {
            System.out.println("Error: Initial balance cannot be negative value!");
            System.exit(1);
        }

        this.Balance = balance;
        this.Name = name;
        this.Identifier = UserIdsGenerator.getInstance().generateId();
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setIdentifier(int identifier) {
        this.Identifier = identifier;
    }

    public void setBalance(double balance) {
        this.Balance = balance;
    }

    public int getIdentifier() {
        return Identifier;
    }

    public String getName() {
        return Name;
    }

    public double getBalance() {
        return Balance;
    }
}