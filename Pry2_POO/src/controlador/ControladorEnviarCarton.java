/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import modelo.Carton;
import modelo.Jugador;
import vista.EnviarCarton;
import vista.MenuPrincipal;

/**
 *
 * @author Andy Porras
 */
public class ControladorEnviarCarton implements ActionListener {

  public EnviarCarton vista;
  public ArrayList<Carton> cartones;
  public MenuPrincipal vistaAnterior;
  public ArrayList<Jugador> jugadores;
  public ControladorEnviarCarton(EnviarCarton pVistaEnviarCarton, ArrayList<Jugador> pJugadores,
      ArrayList<Carton> pCartones, MenuPrincipal pVista) {
    vista = pVistaEnviarCarton;
    jugadores = pJugadores;
    cartones = pCartones;
    vistaAnterior = pVista;
    vista.btEnviarCarton.addActionListener(this);
    vista.btRegresarMP.addActionListener(this);
  }
  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Enviar Cartón":
        enviarCarton();
        break;
      case "Regresar":
        vistaAnterior.setVisible(true);
        vista.setVisible(false);
        break;
    }
  }
  private void enviarCarton() {
  }

}
