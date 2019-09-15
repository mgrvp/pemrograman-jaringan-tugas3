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
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

/**
 *
 *
 *
 * @author lenovo
 */
public class Tgs1Ctrl {

    private Tugas1 view;
    private List<Integer> list = new ArrayList<>();

    public Tgs1Ctrl(Tugas1 view) {
        this.view = view;
        this.view.getjButton1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                proses();
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
                while ((data = reader.readLine()) != null) {
                    doc.insertString(doc.getLength(), data + "\n", null);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Tgs1Ctrl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | BadLocationException ex) {
                Logger.getLogger(Tgs1Ctrl.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Tgs1Ctrl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
}
