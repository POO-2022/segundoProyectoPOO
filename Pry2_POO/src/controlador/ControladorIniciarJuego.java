/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import dao.BingoDAOXML;
import modelo.Bingo;
import modelo.Carton;
import modelo.Jugador;
import vista.IniciarJuego;
import vista.Jugando;
import vista.MenuPrincipal;

/**
 *
 * @author Andy Porras
 */
public class ControladorIniciarJuego implements ActionListener {

  public IniciarJuego vista;
  public ArrayList<Jugador> jugadores;
  public ArrayList<Carton> cartones;
  public BingoDAOXML daoBingo;
  public Bingo bingo;
  public MenuPrincipal vistaAnterior;

  public ControladorIniciarJuego(IniciarJuego pVistaIniciarJuego, ArrayList<Jugador> pJugadores,
      ArrayList<Carton> pCartones,MenuPrincipal pVistaAnterior) {
    this.vista = pVistaIniciarJuego;
    vistaAnterior = pVistaAnterior;
    this.jugadores = pJugadores;
    this.cartones = pCartones;
    this.vista.btRegresarMenuPrincipal.addActionListener(this);
    this.vista.btJugarBingo.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Regresar":
        cerrarVentanaIniciarJuego();
        break;
      case "Jugar":
        ventanaJuego(); 
        break;
      default:
        break;
    }
  }

  private void ventanaJuego() {
    vista.setVisible(false);
    Jugando vistaJuego = new Jugando();
    ControladorJugando controladorJugando = new ControladorJugando(vistaJuego,jugadores,cartones,vista);
    controladorJugando.vista.setVisible(true);
    controladorJugando.vista.setLocationRelativeTo(null);
  }

  private void cerrarVentanaIniciarJuego() {
    vista.setVisible(false);
    vistaAnterior.setVisible(true);
  }

}
