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
import modelo.Bingo;
import modelo.Carton;
import modelo.Jugador;
import vista.IniciarJuego;
import vista.Jugando;
import vista.MenuPrincipal;

/**
 * clase que controla el inicio del juego
 * @author KEVIN SALAZAR
 * @author Andy Porras
 */
public class ControladorIniciarJuego implements ActionListener {

  public IniciarJuego vista;
  public ArrayList<Jugador> jugadores;
  public ArrayList<Carton> cartones;
  public BingoDAOXML daoBingo;
  public Bingo bingo;
  public MenuPrincipal vistaAnterior;

  /**
   * constructor de la clase
   * @param vista
   * @param jugadores
   * @param cartones
   * @param vistaAnterior
   */
  public ControladorIniciarJuego(IniciarJuego pVistaIniciarJuego, ArrayList<Jugador> pJugadores,
      ArrayList<Carton> pCartones,MenuPrincipal pVistaAnterior) {
    this.vista = pVistaIniciarJuego;
    vistaAnterior = pVistaAnterior;
    this.jugadores = pJugadores;
    this.cartones = pCartones;
    this.vista.btRegresarMenuPrincipal.addActionListener(this);
    this.vista.btJugarBingo.addActionListener(this);
  }

  /*
   * metodo que captura la opcion del boton
   */
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

  /**
   * cierra la ventana de inicio del juego
   * y abre la ventana de jugar
   */
  private void ventanaJuego() {
    //validar que el premio esté correctamente escrito}
    int premio = vista.getPremio();
    String tipo = vista.getTipo();
    if(premio<50000){
      JOptionPane.showMessageDialog(null, "El premio debe ser un número mayor o igual a 50 000", "Error", JOptionPane.ERROR_MESSAGE);   
      return;
    }
    vista.setVisible(false);
    Jugando vistaJuego = new Jugando();
    ControladorJugando controladorJugando = new ControladorJugando(vistaJuego,jugadores,cartones,vista, tipo, premio);
    controladorJugando.vista.setVisible(true);
    controladorJugando.vista.setLocationRelativeTo(null);
  }

  /**
   * cierra la ventana de inicio del juego
   * y abre la ventana de menu principal
   */
  private void cerrarVentanaIniciarJuego() {
    vista.setVisible(false);
    vistaAnterior.setVisible(true);
  }

}
