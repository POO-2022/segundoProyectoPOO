/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.mail.search.StringTerm;

import modelo.Jugador;

/**
 *  Clase que permite la gestion de los datos de los jugadores en un archivo CSV
 * tiene funciones para leer, escribir los datos de los jugadores en un archivo CSV
 * @author Andy Porras
 */
public class JugadorDAOCSV {
  public JugadorDAOCSV() {
  }
  /*
   * Funcion que regitra los datos del jugador en un archivo CSV
   * @param pJugador objeto de tipo Jugador
   */
  public void registrarJugador(Jugador pJugador) throws FileNotFoundException {
    try (PrintWriter writer = new PrintWriter(new File("Jugadores.csv"))) {

      StringBuilder sb = new StringBuilder();
      sb.append(pJugador.getNombre());
      sb.append(',');
      sb.append(pJugador.getCedula());
      sb.append(',');
      sb.append(pJugador.getCorreo());
      sb.append('\n');

      writer.write(sb.toString());
      writer.close();
      // System.out.println("done!");

    } catch (FileNotFoundException e) {
      System.out.println(e.getMessage());
    }

  }
  /**
   * Funcion que lee los datos de los jugadores de un archivo CSV
   * @return lista de objetos de tipo Jugador
   */
  public ArrayList<Jugador> jugadoresRegistrados() {
    ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
    try (Scanner scanner = new Scanner(new File("Jugadores.csv"));) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        Jugador jugador = getRecordFromLine(line);
        if (jugador != null) {
          jugadores.add(jugador);
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    // System.out.println(records.toString());
    return jugadores;
  }

  /**
   * Funcion que obtiene un objeto de tipo Jugador a partir de una linea de un archivo CSV
   * @param pLinea linea de un archivo CSV
   * @return objeto de tipo Jugador
   */
  private Jugador getRecordFromLine(String pLine) {
    Jugador values;
    try (Scanner rowScanner = new Scanner(pLine)) {
      rowScanner.useDelimiter(",");
      int contador = 0;
      String nombre = "";
      String cedula = "";
      String correo = "";
      while (rowScanner.hasNext()) {
        String dato = rowScanner.next();
        System.out.println(dato+"contador "+contador);
        if (contador == 0) {
          nombre = dato;
        }
        if (contador == 1) {
          cedula = dato;
          System.out.println(cedula);
        }
        if (contador == 2) {
          correo = dato;
        }
        contador++;
      }
      if (cedula.equals("")) {
        return null;
      } else {
        int cedulaInt = Integer.parseInt(cedula);
        values = new Jugador(nombre,correo, cedulaInt);
        return values;
      }
    }
  }
}
