class TransactionLinkedList implements TransactionsList {
    private Transaction head;
    private Transaction tail;
    private int size;

    public TransactionLinkedList() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    public void addTransaction(Transaction tran) {
        if (head == null) {
            head = tran;
            tail = tran;
        } else {
            this.tail.setNext(tran);
            this.tail = tran;
        }
        this.size++;
    }

    public void removeTransactionById(String id) {
        Transaction current = this.head;
        Transaction prev = null;

        while (current != null) {
            if (current.getIdentifier().toString().equals(id)) {
                if (prev == null)
                    this.head = current.getNext();
                else
                    prev.setNext(current.getNext());
                this.size--;
                return;
            }
            prev = current;
            current = current.getNext();
        }
        throw new TransactionNotFoundException("id not found!");
    }

    public Transaction[] toArray() {
        Transaction[] array = new Transaction[this.size];
        Transaction current = this.head;
        int index = 0;
        while (current != null) {
            array[index++] = current;
            current = current.getNext();
        }
        return array;
    }
}