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
    ArrayList<String> bolasJugadas = bingoDAO.numerosCantados();
    ArrayList<String> bolasMasUsadas = diezNumeroMasUsados(bolasJugadas);
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
    System.out.println(bolasJugadas.size());
    for (String b : bolasJugadas) {
      System.out.println(b);
    }
    System.out.println("bolas mas usadas");
    System.out.println(bolasMasUsadas.size());
    for (String b : bolasMasUsadas) {
      System.out.println(b);
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
  public static ArrayList<String> diezNumeroMasUsados(ArrayList<String> lista) {
    ArrayList<String> listaDiez = new ArrayList<String>();
    int contador = 0;
    for (int i = 0; i < lista.size(); i++) {
      int contadorNumero = 0;
      for (int j = 0; j < lista.size(); j++) {
        if (lista.get(i).equals(lista.get(j))) {
          contadorNumero++;
        }
      }
      if (contadorNumero > contador) {
        contador = contadorNumero;
        listaDiez.clear();
        listaDiez.add(lista.get(i));
      } else if (contadorNumero == contador && !listaDiez.contains(lista.get(i))) {
        listaDiez.add(lista.get(i));
      }
    }
    return listaDiez;
  }
}
