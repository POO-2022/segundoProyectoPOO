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
    JugadorDAOCSV jugadorDAO = new JugadorDAOCSV();
    ArrayList<Jugador> jugadoresDAO = jugadorDAO.jugadoresRegistrados();
    
    MenuPrincipal vista = new MenuPrincipal();

    ArrayList<Carton> carton;
    carton = new ArrayList<Carton>();
    ControladorVentanaPrincipal controladorVentanaPrincipal = new ControladorVentanaPrincipal(vista, jugadoresDAO, carton);
    controladorVentanaPrincipal.vista.setVisible(true);
    controladorVentanaPrincipal.vista.setLocationRelativeTo(null);  
  }
}
