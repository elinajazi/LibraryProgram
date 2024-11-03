import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LibraryGUI extends JFrame {
    private Library library;
    private JTextArea outputArea;
    private JTextField nameField;
    private JTextField stockField;
    private JTextField fromDateField;
    private JTextField toDateField;

    public LibraryGUI() {
        // Initialize library
        library = new Library(new Book[0], new History[0]);

        // Set up the frame
        setTitle("Library Management System");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Initialize components
        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        
        nameField = new JTextField(15);
        stockField = new JTextField(5);
        fromDateField = new JTextField(10);
        toDateField = new JTextField(10);
        
        JButton createBookButton = new JButton("Create Book");
        JButton borrowBookButton = new JButton("Borrow Book");
        JButton returnBookButton = new JButton("Return Book");
        JButton updateStockButton = new JButton("Update Stock");
        JButton listBooksButton = new JButton("List Books");
        JButton listHistoryButton = new JButton("List History");
        JButton searchHistoryButton = new JButton("Search History");

    
        add(new JLabel("Book Name:"));
        add(nameField);
        add(new JLabel("Stock:"));
        add(stockField);
        add(createBookButton);
        add(borrowBookButton);
        add(returnBookButton);
        add(updateStockButton);
        add(listBooksButton);
        add(new JLabel("From Date:"));
        add(fromDateField);
        add(new JLabel("To Date:"));
        add(toDateField);
        add(searchHistoryButton);
        add(scrollPane);

    
        createBookButton.addActionListener(new CreateBookAction());
        borrowBookButton.addActionListener(new BorrowBookAction());
        returnBookButton.addActionListener(new ReturnBookAction());
        updateStockButton.addActionListener(new UpdateStockAction());
        listBooksButton.addActionListener(new ListBooksAction());
        listHistoryButton.addActionListener(new ListHistoryAction());
        searchHistoryButton.addActionListener(new SearchHistoryAction());
    }

    private class CreateBookAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            int stock = Integer.parseInt(stockField.getText());
            library.updateBookStock(name, stock);
            outputArea.append("Book created: " + name + " with stock " + stock + "\n");
        }
    }

    private class BorrowBookAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            Book book = library.borrowBook(name);
            if (book == null) {
                outputArea.append("Book not found: " + name + "\n");
            } else {
                outputArea.append("Book borrowed: " + name + "\n");
            }
        }
    }

    private class ReturnBookAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            int stock = Integer.parseInt(stockField.getText());
            Book book = new Book(0, name, stock);
            book = library.returnBook(book);
            if (book == null) {
                outputArea.append("Book not found: " + name + "\n");
            } else {
                outputArea.append("Book returned: " + name + "\n");
            }
        }
    }

    private class UpdateStockAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            int stock = Integer.parseInt(stockField.getText());
            library.updateBookStock(name, stock);
            outputArea.append("Stock updated for: " + name + "\n");
        }
    }

    private class ListBooksAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            StringBuilder sb = new StringBuilder();
            library.listAllBooks(true);
            for (Book book : library.books) {
                sb.append("ID: ").append(book.id).append(", Name: ").append(book.name).append(", Stock: ").append(book.stock).append("\n");
            }
            outputArea.append(sb.toString());
        }
    }

    private class ListHistoryAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            StringBuilder sb = new StringBuilder();
            library.listAllHistory("2020-01-01", "2020-12-31");
            for (History h : library.history) {
                sb.append("Book ID: ").append(h.book.id).append(", Operation: ").append(h.operationType).append(", Date: ").append(h.date).append("\n");
            }
            outputArea.append(sb.toString());
        }
    }

    private class SearchHistoryAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String fromDate = fromDateField.getText();
            String toDate = toDateField.getText();
            StringBuilder sb = new StringBuilder();
            library.listAllHistory(fromDate, toDate);
            for (History h : library.history) {
                sb.append("Book ID: ").append(h.book.id).append(", Operation: ").append(h.operationType).append(", Date: ").append(h.date).append("\n");
            }
            outputArea.append(sb.toString());
        }
    }
}

