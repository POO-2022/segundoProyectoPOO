/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import dao.BingoDAOXML;
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
        vistaAnterior.setVisible(true);
        vista.setVisible(false);
        break;
      case "Ver Cartón":
        verCarton();
        break;
    }
  }
  private void verCarton() {
    
  }

}
