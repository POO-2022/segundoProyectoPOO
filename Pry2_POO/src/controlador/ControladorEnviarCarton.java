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
 *
 * @author Andy Porras
 */
public class ControladorEnviarCarton implements ActionListener {

  public EnviarCarton vista;
  public ArrayList<Carton> cartones;
  public MenuPrincipal vistaAnterior;
  public ArrayList<Jugador> jugadores;
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

  private void actualizarVista() {
    // vista.cbJugadores.removeAllItems();
    for (Jugador jugador : jugadores) {
      int cedulaInt = jugador.getCedula();
      String cedula = Integer.toString(cedulaInt);
      vista.jComboBoxCedula.addItem(cedula);
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Enviar Cartón":
        {
            try {
                enviarCarton();
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

  private void enviarCarton() throws MessagingException {
    String cantidadCartones = vista.jComboBoxCantidad.getSelectedItem().toString();
    int cantidad = Integer.parseInt(cantidadCartones);
    String cedulaString =  vista.jComboBoxCedula.getSelectedItem().toString();
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
            Email e=new Email(carton.getID(), jugador.getNombre(), address);
          }
        }

      }
    }
    JOptionPane.showMessageDialog(vista, "Cartón enviado correctamente");
    vista.setVisible(false);
    vistaAnterior.setVisible(true);
  }
}
