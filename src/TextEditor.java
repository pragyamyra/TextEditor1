import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {

    // declare frame
    JFrame frame;
    //declare menubar
    JMenuBar menubar;

    // declare textArea
    JTextArea textArea;

    // declare menus
    JMenu file, edit;

    // declare file menu items

    JMenuItem newFile,openFile , saveFile;

    // declare edit menu items

    JMenuItem cut,copy,paste,selectAll,close;

    TextEditor(){

        // initialize frame
        frame=new JFrame();
        //initialize menubar
        menubar=new JMenuBar();

        textArea = new JTextArea();

        //initialize menus
        file= new JMenu("File");
        edit= new JMenu("Edit");

        // initialze file menuItems
        newFile= new JMenuItem("New");
        openFile= new JMenuItem("Open");
        saveFile=new JMenuItem("Save");

        // add actionlistner to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        // add menuitems to file
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        // initialize edit items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        // add actionlistner to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);


        // add menuitems to edit
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);



        // add menus to menubar
        menubar.add(file);
        menubar.add(edit);



        // frame
        frame.setJMenuBar(menubar);  // addd menubar to frame
        // add content pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        panel.add(textArea, BorderLayout.CENTER);
        // to make scrollabel

        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane);
        frame.add(panel);



        // set dimensions of textArea
        frame.setBounds(0,0,400,400);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource()==cut){
            textArea.cut();
        }
        if(actionEvent.getSource()==copy){
            textArea.copy();
        }
        if(actionEvent.getSource()==paste){
            textArea.paste();
        }
        if(actionEvent.getSource()==cut){
            textArea.selectAll();
        }
        if(actionEvent.getSource()==close){
            System.exit(0);
        }

        if(actionEvent.getSource()==openFile){
            // filechooser
            JFileChooser fileChooser = new JFileChooser();
            int chooseOption = fileChooser.showOpenDialog(null);
            // if option choosen is open button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                // get selected file
                File file= fileChooser.getSelectedFile();
                // file path
                String filePath = file.getPath();
                try{
                    // initialize file reader
                    FileReader fileReader = new FileReader(filePath);
                    // initialize buffered reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate="", output="";

                    // read contents line by line
                    while((intermediate= bufferedReader.readLine())!=null) {
                        output += intermediate;
                    }
                    textArea.setText(output);

                }
                catch(IOException ioException){
                    ioException.printStackTrace();
                }

            }

        }
        if(actionEvent.getSource()==saveFile){
            // file picker
            JFileChooser fileChooser = new JFileChooser("D:");
            int chooseOption = fileChooser.showSaveDialog(null);
            // clicks the save button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    // initialize file Writer
                    FileWriter fileWriter = new FileWriter(file);

                    // initialize buffered Writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                    // write contents to text Area
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch(IOException ioException){
                    ioException.printStackTrace();

                }
            }
        }

        if(actionEvent.getSource()==newFile){
            TextEditor newTextEditor= new TextEditor();
        }
    }
    public static void main(String[] args) {
        TextEditor texteditor = new TextEditor();

    }
}
