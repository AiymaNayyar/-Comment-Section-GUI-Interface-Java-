import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

class CommentSection {
    LinkedList<String> comments = new LinkedList<>();

    public void addComment(String text) {
        comments.add(text);
    }

    public void editComment(int index, String text) {
        comments.set(index, text);
    }

    public void deleteComment(int index) {
        comments.remove(index);
    }

    public void searchComment(String text) {
        for (String comment : comments) {
            if (comment.contains(text)) {
                System.out.println(comment);
            }
        }
    }

    public void showComments() {
        for (String comment : comments) {
            System.out.println(comment);
        }
    }
}

class CommentApp extends JFrame {
    CommentSection commentSection = new CommentSection();
    JTextArea textArea= new JTextArea();
    JTextField inputField = new JTextField(15);
    JTextField indexField = new JTextField(5);
    public CommentApp() {
        setTitle("Comment Section");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel panel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        JButton searchButton = new JButton("Search");
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        panel.add(inputField);
        panel.add(indexField);
        panel.add(addButton);
        panel.add(editButton);
        panel.add(deleteButton);
        panel.add(searchButton);
        add(panel, BorderLayout.SOUTH);
        addButton.addActionListener(e -> {
            String text = inputField.getText();
            commentSection.addComment(text);
            inputField.setText("");
            updateTextArea();
        });
        editButton.addActionListener(e -> {
            try {
                int index = Integer.parseInt(indexField.getText());
                String text = inputField.getText();
                commentSection.editComment(index, text);
                inputField.setText("");
                indexField.setText("");
                updateTextArea();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid index");
            }
        });
        deleteButton.addActionListener(e -> {
            try {
                int index = Integer.parseInt(indexField.getText());
                commentSection.deleteComment(index);
                indexField.setText("");
                updateTextArea();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid index");
            }
        });
        searchButton.addActionListener(e -> {
            String text = inputField.getText();
            commentSection.searchComment(text);
        });

        setVisible(true);
    }

    private void updateTextArea() {
        textArea.setText("");
        commentSection.showComments();
        for (String comment : commentSection.comments) {
            textArea.append(comment + "\n");
        }
    }

    public static void main(String[] args) {
        new CommentApp();
    }
}
