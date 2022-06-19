/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import dao.BingoDAOXML;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelo.Carton;
import vista.MenuPrincipal;
import vista.VerCarton;

/**
 *
 * @author Andy Porras
 */
public class ControladorVerCarton implements ActionListener {

  public VerCarton vista;
  public ArrayList<Carton> cartones;
  public BingoDAOXML daoBingo;
  public MenuPrincipal vistaAnterior;
  public ControladorVerCarton(VerCarton pVistaVerCarton, ArrayList<Carton> pCartones, MenuPrincipal pVistaAnterior) {
    this.vista = pVistaVerCarton;
    vistaAnterior = pVistaAnterior;
    this.cartones = pCartones;
    vista.btRegresarMP.addActionListener(this);
    vista.btVerCarton.addActionListener(this);
  }
  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Regresar":
        vista.lblimagen.setIcon(null);
        vista.lblimagen.setText("La imagen se muestra aquí");
        vistaAnterior.setVisible(true);
        vista.setVisible(false);
        break;
      case "Ver Cartón":
        {
            try {
                verCarton();
            } catch (IOException ex) {
                Logger.getLogger(ControladorVerCarton.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        break;

    }
  }
  private void verCarton() throws IOException {
    String id = vista.tfIdentificador.getText();
    try{
        File file = new File("Cartones\\"+id+".png");
        BufferedImage bufferedImage = ImageIO.read(file);
        ImageIcon imageIcon = new ImageIcon(bufferedImage);
        vista.lblimagen.setText("");
        vista.lblimagen.setIcon(imageIcon);
    }catch(Exception e){
      vista.lblimagen.setIcon(null);
      vista.lblimagen.setText("La imagen se muestra aquí");
      JOptionPane.showMessageDialog(null, "El cartón no existe");  
    }
  }

}
