/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasstream;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

/**
 *
 *
 *
 * @author lenovo
 */
public class Tgs2Ctrl {

    private Tugas2 view;
    private List<Integer> list = new ArrayList<>();

    public Tgs2Ctrl(Tugas2 view) {
        this.view = view;
        this.view.getjButton1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                proses();
            }
        });
        this.view.getSaveBut().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
        this.view.getExBut().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });
    }

    private void proses() {
        JFileChooser loadFile = view.getLoadFile();
        StyledDocument doc = view.getjTextPane1().getStyledDocument();
        if (JFileChooser.APPROVE_OPTION == loadFile.showOpenDialog(view)) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(loadFile.getSelectedFile()));
                String data = null;
                doc.insertString(0, "", null);
                view.getjTextPane1().setText("");
                while ((data = reader.readLine()) != null) {
                    doc.insertString(doc.getLength(), data + "\n", null);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Tgs2Ctrl.class
                        .getName()).log(Level.SEVERE, null, ex);

            } catch (IOException | BadLocationException ex) {
                Logger.getLogger(Tgs2Ctrl.class
                        .getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                        JOptionPane.showMessageDialog(null, "Load File Success.", "Informasi", JOptionPane.INFORMATION_MESSAGE);

                    } catch (IOException ex) {
                        Logger.getLogger(Tgs2Ctrl.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    private void save() {
        JFileChooser loadFile = view.getLoadFile();
        if (JFileChooser.APPROVE_OPTION == loadFile.showSaveDialog(view)) {
            BufferedWriter writer = null;
            try {
                String contents = view.getjTextPane1().getText();
                if (contents != null && !contents.isEmpty()) {
                    writer = new BufferedWriter(new FileWriter(loadFile.getSelectedFile()));
                    writer.write(contents);
                    JOptionPane.showMessageDialog(null, "Data anda berhasil tersimpan.", "Informasi", JOptionPane.INFORMATION_MESSAGE);

                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Tgs2Ctrl.class
                        .getName()).log(Level.SEVERE, null, ex);

            } catch (IOException ex) {
                Logger.getLogger(Tgs2Ctrl.class
                        .getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (writer != null) {
                    try {
                        writer.flush();
                        writer.close();
                        view.getjTextPane1().setText("");

                    } catch (IOException ex) {
                        Logger.getLogger(Tgs2Ctrl.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
    
    private void exit(){
        int selectedOption = JOptionPane.showConfirmDialog(null,
                "Apakah anda yakin akan menutup system?", "Close", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (selectedOption == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
