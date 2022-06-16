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
import vista.IniciarJuego;
import vista.Jugando;

/**
 *
 * @author Andy Porras
 */
public class ControladorJugando implements ActionListener {
  Jugando vista;
  ArrayList<Jugador> jugadores;
  ArrayList<Carton> cartones;
  IniciarJuego vistaAnterior;

  public ControladorJugando(Jugando pJugador, ArrayList<Jugador> pJugadores, ArrayList<Carton> pCartones, IniciarJuego pVistaAnterior, String tipo, int premio) {
    vista = pJugador;
    this.jugadores = pJugadores;
    this.cartones = pCartones;
    this.vistaAnterior = pVistaAnterior;
    vista.btCantarNumero.addActionListener(this);
    vista.btRegresarIniciarJuego.addActionListener(this);
    vista.setPremio(premio);
    vista.setTipoJuego(tipo);
    vista.setCantidadJugadores(jugadores.size());
    vista.setCantidadCartones(cartones.size());
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "CantarNumero":
        cantarNumero();
        break;
      case "Regresar":
        cerrarVentanaJuego();
        break;
      default:
        break;
    }
  }

  private void cerrarVentanaJuego() {
    vista.setVisible(false);
    vistaAnterior.setVisible(true);
  }

  private void cantarNumero() {
    
  }
}
