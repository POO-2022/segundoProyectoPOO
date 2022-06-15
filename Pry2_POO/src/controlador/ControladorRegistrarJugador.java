/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
        jugadores.add(j);
        
        //falta que se guarde en toda la cuestion del csv, se asignen cartones, se guarde en el arraylist...
        
        JOptionPane.showMessageDialog(null, "¡Registrado exitosamente!");
        cerrarVentanaRegistrarJugador();
      }
    }
    return;
  }
  private void cerrarVentanaRegistrarJugador() {
    this.vista.setVisible(false);
    this.vistaAnterior.setVisible(true);
  }
}
