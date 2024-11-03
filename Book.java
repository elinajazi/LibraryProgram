class Book {
    int id;
    String name;
    int stock;

    Book(int id, String name, int stock) {
        this.id = id;
        this.name = name;
        this.stock = stock;
    }

    void display() {
        System.out.println("Book ID: " + id);
        System.out.println("Book Name: " + name);
        System.out.println("Book Stock: " + stock);
    }
}