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
 *
 * @author Andy Porras
 */
public class JugadorDAOCSV {
  public JugadorDAOCSV() {
  }

  public void registrarJugador(Jugador pJugador) throws FileNotFoundException {
    try (PrintWriter writer = new PrintWriter(new File("Jugadores.csv"))) {

      StringBuilder sb = new StringBuilder();
      // sb.append("id");
      // sb.append(',');
      // sb.append("Name");
      // sb.append(',');
      // sb.append("Address");
      // sb.append('\n');

      // sb.append("101");
      // sb.append(',');
      // sb.append("John Doe");
      // sb.append(',');
      // sb.append("Las Vegas");
      // sb.append('\n');
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
    // JugadorDAOCSV testCSV = new JugadorDAOCSV();
    // testCSV.readCSVFile();
  }

  public ArrayList<Jugador> readCSVFile() {

    ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
    try (Scanner scanner = new Scanner(new File("test.csv"));) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        //System.out.println(getRecordFromLine(line));
        jugadores.add(getRecordFromLine(line));
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    // System.out.println(records.toString());
    return jugadores;
  }

  private Jugador getRecordFromLine(String line) {
    Jugador values;
    try (Scanner rowScanner = new Scanner(line)) {
      rowScanner.useDelimiter(",");
      int contador = 0;
      String nombre = "";
      String cedula = "";
      String correo = "";
      while (rowScanner.hasNext()) {
        if (contador == 0) {
          nombre = rowScanner.next();
        }
        if (contador == 1) {
          cedula = rowScanner.next();
        }
        if (contador == 2) {
          correo = rowScanner.next();
        }
        // String value = rowScanner.next();
        // System.out.println(value);
        // values.add(value);
      }
      int cedulaInt = Integer.parseInt(cedula);
      values = new Jugador(nombre,correo, cedulaInt);
      // values.setNombre(nombre);
      // values.setCedula(cedulaInt);
      // values.setCorreo(correo);
      return values;
    }
  }
}
