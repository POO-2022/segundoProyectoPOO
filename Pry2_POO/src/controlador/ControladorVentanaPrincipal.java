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
import modelo.Jugador;
import vista.MenuPrincipal;
import vista.VtMenuPrincipal;

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
        //
        break;
      case "Ver Carton":
        //
        break;
      case "Generar Cartones":
        //
        break;
      case "Jugar Juego":
        //
        break;
      case "Registrar Jugador":
        //
        break;
      case "Generar Estadisticas":
        //
        break;
      default:
        break;  
    }
  }
  public void cerrarVentanaLogin(){
    this.vista.dispose();
  }

}
