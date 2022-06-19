/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import dao.BingoDAOXML;
import java.io.File;
import modelo.Carton;
import vista.GenerarCarton;
import vista.MenuPrincipal;
import vista.VistaCarton;

/**
 *
 * @author Andy Porras
 */
public class ControladorGenerarCarton implements ActionListener {
  
  public GenerarCarton vista;
  public ArrayList<Carton> cartones;
  public BingoDAOXML daoBingo;
  public MenuPrincipal vistaAnterior;

  public ControladorGenerarCarton(GenerarCarton pVistaGenerarCartones, ArrayList<Carton> pCartones,
      MenuPrincipal pVistaAnterior) {
    this.vista = pVistaGenerarCartones;
    vistaAnterior = pVistaAnterior;
    this.cartones = pCartones;
    vista.btGenerarCartones.addActionListener(this);
    vista.btRegresarMP.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Generar":
        if (vista.jtfCantidad.getText() != null && !vista.jtfCantidad.getText().equals("")) {
          generarCartones();
        }else {
          JOptionPane.showMessageDialog(null, "Ingrese una cantidad de cartones");
        }
        break;
      case "Regresar":
        vistaAnterior.setVisible(true);
        vista.setVisible(false);
        break;
    }
  }

  private void generarCartones() {
  try{
    //limpieza de la carpeta Cartones
    for(Carton actual: cartones){
      System.out.println(actual.getID());
      String ruta = "Cartones/" + actual.getID() + ".png";
      File img = new File(ruta);
      if (img.isFile() && img.exists()) {
        img.delete();
      }
    }
    cartones.clear();
    
    int cantidadCartones = Integer.parseInt(vista.jtfCantidad.getText());
    if (cantidadCartones <= 500 && cantidadCartones > 0) {
      for (int i = 0; i < cantidadCartones; i++) {
        Carton nuevo = new Carton(); 
        
        ControladorVistaCarton ctn = new ControladorVistaCarton(new VistaCarton());  
        ctn.vista.setVisible(true);
        ctn.generacionCarton(nuevo);
        ctn.vista.setVisible(false);
        cartones.add(nuevo);
        
      }
      JOptionPane.showMessageDialog(vista, "Cartones generados correctamente");
      vistaAnterior.setVisible(true);
      vista.setVisible(false);
    } else {
      JOptionPane.showMessageDialog(vista, "La cantidad de cartones debe ser menor a 500 y mayor a 0");
      return;
    }
    }
    catch(Exception e){
      JOptionPane.showMessageDialog(vista, "Ingrese un número válido");  
      return;
    }
  }

  private boolean existeCarton(Carton carton) {
    for (Carton cartonAux : cartones) {
      if (cartonAux.getID() == carton.getID()) {
        return true;
      }
    }
    return false;
  }

}
