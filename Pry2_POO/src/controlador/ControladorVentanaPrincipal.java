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
import java.io.File;
import modelo.Carton;
import modelo.Jugador;
import vista.EnviarCarton;
import vista.Estadistica;
import vista.GenerarCarton;
import vista.IniciarJuego;
import vista.MenuPrincipal;
import vista.RegistrarJugador;
import vista.VerCarton;

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
        enviarCarton();
        break;
      case "Ver Carton":
        System.out.print(cartones.size());
        abrirVentanaVerCarton();
        break;
      case "Generar Cartones":
        abrirVentanaGenerarCartones();
        break;
      case "Jugar Bingo":
        abrirVentanaIniciarJuego();
        break;
      case "Registrar Jugador":
        registrarJugador();
        break;
      case "Generar Estadisticas":
        generarEstadistica();
        break;
      default:
        break;  
    }
  }  
  private void enviarCarton() {
    if(jugadores.size()==0){
        JOptionPane.showMessageDialog(null, "No hay jugadores registrados");
        return;
    }
    if(cartones.isEmpty()){
        JOptionPane.showMessageDialog(null, "No hay cartones disponibles");
        return;
    }  
    vista.setVisible(false);
    EnviarCarton vistaEnviarCarton = new EnviarCarton();
    ControladorEnviarCarton controladorEnviarCarton = new ControladorEnviarCarton(vistaEnviarCarton, jugadores, cartones, vista);
    controladorEnviarCarton.vista.setVisible(true);
    controladorEnviarCarton.vista.setLocationRelativeTo(null);

  }


  private void abrirVentanaVerCarton() {
    vista.setVisible(false);
    VerCarton vistaVerCarton = new VerCarton();
    ControladorVerCarton controladorVerCarton = new ControladorVerCarton(vistaVerCarton, cartones,vista);
    controladorVerCarton.vista.setVisible(true);
    controladorVerCarton.vista.setLocationRelativeTo(null);
  }


  private void abrirVentanaGenerarCartones() {
    vista.setVisible(false);
    GenerarCarton vistaGenerarCartones = new GenerarCarton();
    ControladorGenerarCarton controladorGenerarCarton = new ControladorGenerarCarton(vistaGenerarCartones, cartones, vista);
    controladorGenerarCarton.vista.setVisible(true);
    controladorGenerarCarton.vista.setLocationRelativeTo(null);
  }


  private void registrarJugador() {
    vista.setVisible(false);
    RegistrarJugador vistaRegistrarJugador = new RegistrarJugador();
    ControladorRegistrarJugador controladorRegistrarJugador = new ControladorRegistrarJugador(vistaRegistrarJugador, jugadores, cartones,vista);
    controladorRegistrarJugador.vista.setVisible(true);
    controladorRegistrarJugador.vista.setLocationRelativeTo(null);
  }


  private void abrirVentanaIniciarJuego() {
    if(jugadores.size()==0){     
      JOptionPane.showMessageDialog(null, "No hay jugadores registrados", "Error", JOptionPane.ERROR_MESSAGE);
    }
    if (cartones.size() == 0) {
      JOptionPane.showMessageDialog(null, "No hay cartones para jugar", "Error", JOptionPane.ERROR_MESSAGE);
    } else {
      vista.setVisible(false);
      IniciarJuego vistaIniciarJuego = new IniciarJuego();
      ControladorIniciarJuego controladorIniciarJuego;
      controladorIniciarJuego = new ControladorIniciarJuego(vistaIniciarJuego, jugadores, cartones, vista);
      controladorIniciarJuego.vista.setVisible(true);
      controladorIniciarJuego.vista.setLocationRelativeTo(null);
    }   
  }
  
  public void generarEstadistica(){
      vista.setVisible(false);
      Estadistica estadistica = new Estadistica();
      BingoDAOXML daoBingo = new BingoDAOXML();
      ArrayList<Integer> numeroCantados = daoBingo.numerosCantados();
      ArrayList<String> configuracion = daoBingo.listaTiposDeJuegos();
      ControladorEstadistica controladorEstad = new ControladorEstadistica(estadistica, vista, jugadores,numeroCantados,configuracion);
      controladorEstad.vista.setVisible(true);
      controladorEstad.vista.setLocationRelativeTo(null);
  }
  
  public void cerrarVentanaLogin(){
    this.vista.dispose();
    //limpieza de la carpeta Cartones
    for(Carton actual: cartones){
      System.out.println(actual.getID());
      String ruta = "Cartones/" + actual.getID() + ".png";
      File img = new File(ruta);
      if (img.isFile() && img.exists()) {
        img.delete();
      }
    }
    cartones.clear();
    System.exit(0);
  }

}
