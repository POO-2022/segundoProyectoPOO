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
import modelo.Carton;
import vista.GenerarCarton;
import vista.MenuPrincipal;

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
    int cantidadCartones = Integer.parseInt(vista.jtfCantidad.getText());
    if (cantidadCartones <= 500 && cantidadCartones > 0) {
      for (int i = 0; i < cantidadCartones; i++) {
        cartones.add(new Carton());
      }
      JOptionPane.showMessageDialog(vista, "Cartones generados correctamente");
      vistaAnterior.setVisible(true);
      vista.setVisible(false);
    } else {
      JOptionPane.showMessageDialog(vista, "La cantidad de cartones debe ser menor a 500 y mayor a 0");
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
