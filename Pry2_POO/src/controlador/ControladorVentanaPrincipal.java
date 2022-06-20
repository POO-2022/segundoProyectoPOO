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
 * clase que controla la ventana principal
 * @author Andy Porras
 */
public class ControladorVentanaPrincipal implements ActionListener {

  public MenuPrincipal vista;
  public ArrayList<Jugador> jugadores;
  public ArrayList<Carton> cartones;
  public BingoDAOXML daoBingo;

  /**
   * constructor de la clase
   * @param pVista
   * @param pJugadores
   * @param pCartones
   */
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

  /*
   * metodo que captura la opcion del boton
   */
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

  /*
   * metodo que abre la ventana de enviar carton
   */
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

  /*
   * metodo que abre la ventana de ver carton
   */
  private void abrirVentanaVerCarton() {
    vista.setVisible(false);
    VerCarton vistaVerCarton = new VerCarton();
    ControladorVerCarton controladorVerCarton = new ControladorVerCarton(vistaVerCarton, cartones,vista);
    controladorVerCarton.vista.setVisible(true);
    controladorVerCarton.vista.setLocationRelativeTo(null);
  }

  /*
   * metodo que abre la ventana de generar cartones
   */
  private void abrirVentanaGenerarCartones() {
    vista.setVisible(false);
    GenerarCarton vistaGenerarCartones = new GenerarCarton();
    ControladorGenerarCarton controladorGenerarCarton = new ControladorGenerarCarton(vistaGenerarCartones, cartones, vista);
    controladorGenerarCarton.vista.setVisible(true);
    controladorGenerarCarton.vista.setLocationRelativeTo(null);
  }


  /*
   * metodo que abre la ventana de registrar jugador
   */
  private void registrarJugador() {
    vista.setVisible(false);
    RegistrarJugador vistaRegistrarJugador = new RegistrarJugador();
    ControladorRegistrarJugador controladorRegistrarJugador = new ControladorRegistrarJugador(vistaRegistrarJugador, jugadores, cartones,vista);
    controladorRegistrarJugador.vista.setVisible(true);
    controladorRegistrarJugador.vista.setLocationRelativeTo(null);
  }

  /*
   * metodo que abre la ventana de iniciar juego
   */
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
  
  /*
   * metodo que abre la ventana de generar estadisticas
   */
  public void generarEstadistica(){
      vista.setVisible(false);
      Estadistica estadistica = new Estadistica();
      BingoDAOXML daoBingo = new BingoDAOXML();
      ArrayList<Integer> numeroCantados = daoBingo.numerosCantados();
      ArrayList<String> configuracion = daoBingo.listaTiposDeJuegos();
      ArrayList<String> ganadores = daoBingo.listaJugadoresGanadores();
      ControladorEstadistica controladorEstad = new ControladorEstadistica(estadistica, vista, ganadores ,numeroCantados,configuracion);
      controladorEstad.vista.setVisible(true);
      controladorEstad.vista.setLocationRelativeTo(null);
  }
  
  /*
   * metodo que cierra la ventana principal, elimina las imagenes de los cartones
   * y cierra el programa en su totalidad
   */
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
