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
        generarCartones();
        break;
      case "Regresar":
        vistaAnterior.setVisible(true);
        vista.setVisible(false);
        break;
    }
  }

  private void generarCartones() {

  }

}
