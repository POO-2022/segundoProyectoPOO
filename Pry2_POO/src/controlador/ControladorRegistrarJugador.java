/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import dao.JugadorDAOCSV;
import modelo.Carton;
import modelo.Jugador;
import vista.MenuPrincipal;
import vista.RegistrarJugador;

/**
 * clase que controla el registro de jugadores
 * @author KEVIN SALAZAR
 * @version 1.0
 */
public class ControladorRegistrarJugador implements ActionListener {

  public RegistrarJugador vista;
  public ArrayList<Jugador> jugadores;
  public ArrayList<Carton> cartones;
  public MenuPrincipal vistaAnterior;
  public JugadorDAOCSV jugadorDAO;

  /**
   * constructor de la clase
   * @param vista
   * @param jugadores
   * @param cartones
   * @param vistaAnterior
   */
  public ControladorRegistrarJugador(RegistrarJugador pVistaRegistrarJugador, ArrayList<Jugador> pJugadores,
      ArrayList<Carton> pCartones, MenuPrincipal pVistaAnterior) {
    this.vista = pVistaRegistrarJugador;
    this.jugadores = pJugadores;
    this.cartones = pCartones;
    jugadorDAO = new JugadorDAOCSV();
    this.vistaAnterior = pVistaAnterior;
    this.vista.btAtras.addActionListener(this);
    this.vista.btRegistrarJugador.addActionListener(this);
  }
  /*
   * metodo que captura la opcion del boton
   */
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
  /**
   * Registra al jugador
   */
  private void registrarJugador() {
    if(!vista.validarVacios()){
      String nombre = vista.getNombre();
      String correo = vista.getCorreo();
      Integer cedula = vista.getCedula();
      Jugador j = new Jugador();
      if(cedula==-1){
          JOptionPane.showMessageDialog(null, "La cédula debe ser un número", "Atención", JOptionPane.WARNING_MESSAGE);
          return;
      }
      if(!j.validarCorreo(correo)){
        JOptionPane.showMessageDialog(null, "Correo no válido", "Atención", JOptionPane.WARNING_MESSAGE);
        return;
      }
      else{
        j.setCedula(cedula);
        j.setCorreo(correo);
        j.setNombre(nombre);
        if (jugadores.contains(j)) {
          JOptionPane.showMessageDialog(null, "El jugador ya se encuentra registrado", "Atención",
              JOptionPane.WARNING_MESSAGE);
          return;
        }else{
          try {
            jugadores.add(j);
            jugadorDAO.registrarJugador(j);
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          }
          //falta que se guarde en toda la cuestion del csv, se asignen cartones, se guarde en el arraylist...
          
          JOptionPane.showMessageDialog(null, "¡Registrado exitosamente!");
          cerrarVentanaRegistrarJugador();
        }
      }
    }
    return;
  }
  /**
   * cierra la ventana
   */
  private void cerrarVentanaRegistrarJugador() {
    this.vista.setVisible(false);
    this.vistaAnterior.setVisible(true);
  }
}
