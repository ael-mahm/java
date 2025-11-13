class UsersArrayList implements UsersList {
    private int size = 10;
    private User[] users;
    private int index = 0;

    UsersArrayList() {
        this.users = new User[size];
    }

    public void addUser(User user) {
        if (index >= size) {
            size = size + size / 2;
            User[] newUsers = new User[size];
            for (int i = 0; i < users.length; i++) {
                newUsers[i] = users[i];
            }
            users = newUsers;
        }
        users[index++] = user;
    }

    public User getUserById(int id) {
        for (int i = 0; i < this.index; i++) {
            if (users[i].getIdentifier() == id) {
                return users[i];
            }
        }
        throw new UserNotFoundException("User with id: " + id + "not found!");
    }

    public User getUserByIndex(int index) {
        if (index < 0 || index >= this.index) {
            throw new UserNotFoundException("[x] invalid index!");
        }
        return users[index];
    }

    public int getNumberOfUsers() {
        return this.index;
    }
}