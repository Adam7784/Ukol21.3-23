import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ProhlizecSouboru extends JFrame{


    private JButton otevriSoubor;
    private JPanel panel1;
    private JTextArea textArea1;


    private JFileChooser chooser;

    private JMenu menuFile;
    private JMenuBar menuBar;
    private JMenuItem miOpenFile;

    private ArrayList<String> list =new ArrayList<String>();


    public ProhlizecSouboru(){

        setTitle("Prohlížeč souborů");
        setContentPane(panel1);
        chooser = new JFileChooser();
        menuBar = new JMenuBar();
        menuFile = new JMenu("Výběr");
        menuBar.add(menuFile);
        miOpenFile = new JMenuItem("Vyber soubor");
        menuFile.add(miOpenFile);
        miOpenFile.addActionListener(e -> readFile());
        setJMenuBar(menuBar);

        otevriSoubor.addActionListener(e -> otviraniSouboru());
    }

    private void readFile(){
        otviraniSouboru();
    }

    private void otviraniSouboru(){
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION){
            File selectedFile = chooser.getSelectedFile();
            loadFileContents(selectedFile);
        }
        if(list.size()==0){
            textArea1.setText("v seznamu nic není");
        }else{
            String celyObsah = String.join("\n", list);
            textArea1.setText(celyObsah);
        }
    }
    public static void main(String[] args) {
        ProhlizecSouboru p =new ProhlizecSouboru();
        p.setSize(500, 500);
        p.setVisible(true);
        p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void loadFileContents(File selectedFile) {
        try ( Scanner s = new Scanner(new BufferedReader(
                new FileReader(selectedFile)))
        ) {while (s.hasNextLine()){
            list.add(s.nextLine());
        }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
