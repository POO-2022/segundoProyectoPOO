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
    BingoDAO bingoDAO = new BingoDAOXML();
    ArrayList<String> fechas = bingoDAO.listaFechasDeJuegos();
    ArrayList<String> tipo = bingoDAO.listaTiposDeJuegos();
    ArrayList<String> ganadores = bingoDAO.listaJugadoresGanadores();
    System.out.println(fechas.size());
    for (String f : fechas) {
      System.out.println(f);
    }
    System.out.println(tipo.size());
    for (String t : tipo) {
      System.out.println(t);
    }
    System.out.println(ganadores.size());
    for (String g : ganadores) {
      System.out.println(g);
    }
    MenuPrincipal vista = new MenuPrincipal();
    ArrayList<Jugador> jugadores;
    jugadores = new ArrayList<Jugador>();
    jugadores.add(new Jugador("Andy Porras", "andyromero320@gmail.com", 703010147));
    ArrayList<Carton> carton;
    carton = new ArrayList<Carton>();
    ControladorVentanaPrincipal controladorVentanaPrincipal = new ControladorVentanaPrincipal(vista, jugadores, carton);
    controladorVentanaPrincipal.vista.setVisible(true);
    controladorVentanaPrincipal.vista.setLocationRelativeTo(null);  
  }
}
