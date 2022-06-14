/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import vista.MenuPrincipal;
import vista.RegistrarJugador;
import modelo.Bingo;
import modelo.Carton;
import modelo.Jugador;

/**
 *
 * @author KEVIN SALAZAR
 * @version 1.0
 */
public class ControladorRegistrarJugador implements ActionListener {
  /*
   *** detalles a considerar***
   * Para la parte de correo, en la clase jugador hay una funcion booleana que
   * valida que el correo este bien escrito
   * dicha funcion es primordial para poder crear al jugador, si eso no se valida,
   * va a dar futuros problemas
   * 
   * la cedula esta declarada como int
   * no se hacer interfaz xd
   */
  public RegistrarJugador vista;
  public ArrayList<Jugador> jugadores;
  public ArrayList<Carton> cartones;
  public MenuPrincipal vistaAnterior;

  public ControladorRegistrarJugador(RegistrarJugador pVistaRegistrarJugador, ArrayList<Jugador> pJugadores,
      ArrayList<Carton> pCartones, MenuPrincipal pVistaAnterior) {
    this.vista = pVistaRegistrarJugador;
    this.jugadores = pJugadores;
    this.cartones = pCartones;
    this.vistaAnterior = pVistaAnterior;
    this.vista.btAtras.addActionListener(this);
    this.vista.btRegistrarJugador.addActionListener(this);
  }
  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Atras":
        cerrarVentanaRegistrarJugador();
        break;
      case "Registrar Jugador":
        registrarJugador();
        break;
      default:
        break;
    }
  }
  private void registrarJugador() {
  }
  private void cerrarVentanaRegistrarJugador() {
    this.vista.setVisible(false);
    this.vistaAnterior.setVisible(true);
  }
}
