/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.Carton;
import modelo.Jugador;
import vista.EnviarCarton;
import vista.MenuPrincipal;
import dao.Email;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;

/**
 * clase que controla el envio de cartones
 * @author Andy Porras
 */
public class ControladorEnviarCarton implements ActionListener {

  public EnviarCarton vista;
  public ArrayList<Carton> cartones;
  public MenuPrincipal vistaAnterior;
  public ArrayList<Jugador> jugadores;

  /**
   * constructor de la clase
   * @param vista
   * @param cartones
   * @param vistaAnterior
   * @param jugadores
   */
  public ControladorEnviarCarton(EnviarCarton pVistaEnviarCarton, ArrayList<Jugador> pJugadores,
      ArrayList<Carton> pCartones, MenuPrincipal pVista) {
    vista = pVistaEnviarCarton;
    jugadores = pJugadores;
    cartones = pCartones;
    vistaAnterior = pVista;
    vista.btEnviarCarton.addActionListener(this);
    vista.btRegresarMP.addActionListener(this);
    actualizarVista();
  }

  /**
   * metodo que actualiza la vista
   */
  private void actualizarVista() {
    // vista.cbJugadores.removeAllItems();
    for (Jugador jugador : jugadores) {
      int cedulaInt = jugador.getCedula();
      String cedula = Integer.toString(cedulaInt);
      vista.jComboBoxCedula.addItem(cedula);
    }
  }

  /**
   * metodo que envia el carton
   * o regresa al menu principal
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Enviar Cartón": {
        try {
          String cantidadCartones = vista.jComboBoxCantidad.getSelectedItem().toString();
          int cantidad = Integer.parseInt(cantidadCartones);
          if (cantidad <= cartones.size()) {
            enviarCarton();
          } else {
            JOptionPane.showMessageDialog(null, "No hay cartones suficientes");
          }
        } catch (MessagingException ex) {
          Logger.getLogger(ControladorEnviarCarton.class.getName()).log(Level.SEVERE, null, ex);
          JOptionPane.showMessageDialog(vista, "Error al enviar el carton");
        }
      }
        break;

      case "Regresar":
        vistaAnterior.setVisible(true);
        vista.setVisible(false);
        break;
    }
  }

  /*
   * metodo que envia el carton
   */
  private void enviarCarton() throws MessagingException {
    String cantidadCartones = vista.jComboBoxCantidad.getSelectedItem().toString();
    int cantidad = Integer.parseInt(cantidadCartones);
    String cedulaString = vista.jComboBoxCedula.getSelectedItem().toString();
    int cedula = Integer.parseInt(cedulaString);
    for (Jugador jugador : jugadores) {
      int cedulaInt = jugador.getCedula();
      String address = jugador.getCorreo();
      if (cedulaInt == cedula) {
        for (Carton carton : cartones) {
          if (cantidad == 0) {
            break;
          }
          if (jugador.addCarton(carton)) {
            cantidad--;
            carton.setDuenio(jugador);
            String mensaje = "Saludos, "+jugador.getNombre()+". Se le hace entrega de su correspondiente cartón para el juego de Bingo\n\n¡Suerte!";
            String asunto="Entrega de cartón: "+carton.getID();
            // Email e=new Email(carton.getID(), jugador.getNombre(), address, mensaje, asunto);
            new Email(carton.getID(), jugador.getNombre(), address, mensaje, asunto);
          }
        }

      }
    }
    JOptionPane.showMessageDialog(vista, "Cartón enviado correctamente");
    vista.setVisible(false);
    vistaAnterior.setVisible(true);
  }
}
