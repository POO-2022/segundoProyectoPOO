/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

import java.util.ArrayList;

import controlador.*;
import vista.*;
import modelo.*;
import dao.*;


/**
 *
 * @author Andy Porras
 */
public class Aplicacion {
    
  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    // LoginForm vista = new LoginForm();
    // Usuario usuario = new Usuario();
    // ControladorUsuario controladorUsuario = new ControladorUsuario(vista, usuario);
    //  controladorUsuario.vista.setVisible(true);
    // controladorUsuario.vista.setLocationRelativeTo(null);
    //VtMenuPrincipal vista = new VtMenuPrincipal();
    MenuPrincipal vista = new MenuPrincipal();
    ArrayList<Jugador> jugadores;
    jugadores = new ArrayList<Jugador>();
    ArrayList<Carton> carton;
    carton = new ArrayList<Carton>();
    ControladorVentanaPrincipal controladorVentanaPrincipal = new ControladorVentanaPrincipal(vista, jugadores, carton);
    controladorVentanaPrincipal.vista.setVisible(true);
    controladorVentanaPrincipal.vista.setLocationRelativeTo(null);  
  }
}
