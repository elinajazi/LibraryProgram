class Library {
    Book[] books;
    History[] history;

    Library(Book[] books, History[] history) {
        this.books = books;
        this.history = history;
    }

    void updateBookStock(String name, int stock) {
        for (Book book : books) {
            if (book.name.equals(name)) {
                book.stock = stock;
                return;
            }
        }
        books = addBook(books, new Book(books.length + 1, name, stock));
    }

    void listAllBooks(boolean haveStockInfo) {
        for (Book book : books) {
            if (haveStockInfo) {
                book.display();
            } else {
                System.out.println("Book ID: " + book.id);
                System.out.println("Book Name: " + book.name);
            }
        }
    }

    void listAllHistory(String fromDate, String toDate) {
        for (History history : history) {
            if (history.date.compareTo(fromDate) >= 0 && history.date.compareTo(toDate) <= 0) {
                history.display();
            }
        }
    }

    Book borrowBook(String name) {
        for (Book book : books) {
            if (book.name.equals(name)) {
                if (book.stock == 0) {
                    history = addHistory(history, History.reportMissing(book, "2020-01-01"));
                } else {
                    book.stock--;
                    history = addHistory(history, History.logGetBook(book, "2020-01-01"));
                }
                return book;
            }
        }
        return null;
    }

    Book returnBook(Book book) {
        for (Book b : books) {
            if (b.name.equals(book.name)) {
                b.stock++;
                history = addHistory(history, History.logReturnBook(book, "2020-01-01"));
                return b;
            }
        }
        return null;
    }

    Book[] addBook(Book[] books, Book book) {
        Book[] newBooks = new Book[books.length + 1];
        for (int i = 0; i < books.length; i++) {
            newBooks[i] = books[i];
        }
        newBooks[books.length] = book;
        return newBooks;
    }

    History[] addHistory(History[] history, History h) {
        History[] newHistory = new History[history.length + 1];
        for (int i = 0; i < history.length; i++) {
            newHistory[i] = history[i];
        }
        newHistory[history.length] = h;
        return newHistory;
    }
}