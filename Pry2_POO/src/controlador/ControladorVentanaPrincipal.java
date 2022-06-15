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
import modelo.Jugador;
import vista.IniciarJuego;
import vista.MenuPrincipal;
import vista.RegistrarJugador;

/**
 *
 * @author Andy Porras
 */
public class ControladorVentanaPrincipal implements ActionListener {

  public MenuPrincipal vista;
  public ArrayList<Jugador> jugadores;
  public ArrayList<Carton> cartones;
  public BingoDAOXML daoBingo;


  public ControladorVentanaPrincipal(MenuPrincipal pVista, ArrayList<Jugador> pJugadores,
      ArrayList<Carton> pCartones) {
    this.vista = pVista;
    this.jugadores = pJugadores;
    this.cartones = pCartones;
    this.daoBingo = new BingoDAOXML();
    this.vista.btSalir.addActionListener(this);
    this.vista.btEnviarCarton.addActionListener(this);
    this.vista.btVerCarton.addActionListener(this);
    this.vista.btGenerarCartones.addActionListener(this);
    this.vista.btJugar.addActionListener(this);
    this.vista.btRegistrarJugador.addActionListener(this);
    this.vista.btGenerarEstadisticas.addActionListener(this);
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()){
      case "Salir":
        cerrarVentanaLogin();
        break;
      case "Enviar Carton":
        System.out.print(jugadores.size());
        break;
      case "Ver Carton":
        //
        break;
      case "Generar Cartones":
        //
        break;
      case "Jugar Bingo":
        abrirVentanaIniciarJuego();
        break;
      case "Registrar Jugador":
        registrarJugador();
        break;
      case "Generar Estadisticas":
        //
        break;
      default:
        break;  
    }
  }
  private void registrarJugador() {
    vista.setVisible(false);
    RegistrarJugador vistaRegistrarJugador = new RegistrarJugador();
    ControladorRegistrarJugador controladorRegistrarJugador = new ControladorRegistrarJugador(vistaRegistrarJugador, jugadores, cartones,vista);
    controladorRegistrarJugador.vista.setVisible(true);
    controladorRegistrarJugador.vista.setLocationRelativeTo(null);
  }


  private void abrirVentanaIniciarJuego() {
    // if (cartones.size() == 0) {
    //   JOptionPane.showMessageDialog(null, "No hay cartones para jugar", "Error", JOptionPane.ERROR_MESSAGE);
    // } else {
      vista.setVisible(false);
      IniciarJuego vistaIniciarJuego = new IniciarJuego();
      ControladorIniciarJuego controladorIniciarJuego = new ControladorIniciarJuego(vistaIniciarJuego, jugadores, cartones, vista);
      controladorIniciarJuego.vista.setVisible(true);
      controladorIniciarJuego.vista.setLocationRelativeTo(null);
    // }   
  }
  public void cerrarVentanaLogin(){
    this.vista.dispose();
    System.exit(0);
  }

}
