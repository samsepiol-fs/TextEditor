import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    // declaring prop of TE
    JFrame frame;

    JMenuBar menuBar;

    JMenu file, edit;

    JMenuItem newFile, openFile, saveFile;
    JMenuItem cut, copy, paste, selectAll, close;

    JTextArea textArea;

    TextEditor() {
        // initialize a frame
        frame = new JFrame();
        textArea = new JTextArea();
        // initialize menu bar
        menuBar = new JMenuBar();

        // create menu
        file = new JMenu("File");
        edit = new JMenu("Edit");

        // Initialize file Menu Items
        newFile = new JMenuItem("New");
        openFile = new JMenuItem("Open");
        saveFile = new JMenuItem("Save");
        // adding action listener to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        // adding file menu items
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        // Initialize edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");
        // adding action listener to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);
        // adding edit menu items
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);
        // add menus to menu bar
        menuBar.add(file);
        menuBar.add(edit);

        // set menuBar to frame
        frame.setJMenuBar(menuBar);
        // create content pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout(0, 0));
        // add text area to panel
        panel.add(textArea, BorderLayout.CENTER);
        // create scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // add scroll pane to panel
        panel.add(scrollPane);
        // add panel to frame
        frame.add(panel);
        // set dimensions of frame
        frame.setBounds(100, 100, 400, 400);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // adding functionality to edit menu items
        if (actionEvent.getSource() == cut) {
            //perform cut operation
            textArea.cut();
        }
        if (actionEvent.getSource() == copy) {
            // perform copy operation
            textArea.copy();
        }
        if (actionEvent.getSource() == paste) {
            // perfrorm paste operation
            textArea.paste();
        }
        if (actionEvent.getSource() == selectAll) {
            // perform select all operation
            textArea.selectAll();
        }
        if (actionEvent.getSource() == close) {
            // perform close operation
            System.exit(0);
        }

        // adding functionality to file menu items
        if (actionEvent.getSource() == openFile) {
            // open file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            // if we have clicked on open button
            if (chooseOption == JFileChooser.APPROVE_OPTION) {
                // getting selected file
                File file = fileChooser.getSelectedFile();
                // get the path of the selected file
                String filePath = file.getPath();
                try {
                    // initialize file reader
                    FileReader fileReader = new FileReader(filePath);
                    // initialize buffered reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";
                    // read contents of file line by line
                    while ((intermediate = bufferedReader.readLine()) != null) {
                        output += intermediate + "\n";
                    }
                    // set the output string to set Area
                    textArea.setText(output);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        }
        if (actionEvent.getSource() == saveFile) {
            // initialize file picker
            JFileChooser fileChooser = new JFileChooser("C:");
            // get choose option from file picker
            int chooseOption = fileChooser.showSaveDialog(null);
            // check if we chose save option
            if (chooseOption == JFileChooser.APPROVE_OPTION) {
                // create a file with choosen directory path and name
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");
                try {
                    // initialize file writer
                    FileWriter fileWriter = new FileWriter(file);
                    // initialize buffered writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    //write contents of text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        if (actionEvent.getSource() == newFile) {
            TextEditor newTextEditor = new TextEditor();
        }
    }

    public static void main(String[] args) {
        // write your code here
        TextEditor textEditor = new TextEditor();
    }
}
