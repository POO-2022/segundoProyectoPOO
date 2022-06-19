/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import dao.BingoDAOXML;
import modelo.Carton;
import modelo.Jugador;
import modelo.TJuego;
import modelo.Bingo;
import modelo.Bola;
import vista.IniciarJuego;
import vista.Jugando;

/**
 *
 * @author Andy Porras
 */
public class ControladorJugando implements ActionListener {
  Jugando vista;
  ArrayList<Jugador> jugadores;
  ArrayList<Carton> cartones;
  ArrayList<Bola> bolas;
  ArrayList<Integer> jugados;
  IniciarJuego vistaAnterior;
  ArrayList<Jugador> jugadoresGanadores;
  Bingo bingo;

  public ControladorJugando(Jugando pJugador, ArrayList<Jugador> pJugadores, ArrayList<Carton> pCartones,
      IniciarJuego pVistaAnterior, String tipo, int premio) {
    vista = pJugador;
    this.jugadores = pJugadores;
    this.cartones = pCartones;
    this.vistaAnterior = pVistaAnterior;
    vista.btCantarNumero.addActionListener(this);
    vista.btRegresarIniciarJuego.addActionListener(this);
    vista.setPremio(premio);
    vista.setTipoJuego(tipo);
    vista.setCantidadJugadores(jugadores.size());
    vista.setCantidadCartones(cartones.size());
    jugados = new ArrayList<Integer>();
    bolas = new ArrayList<Bola>();
    jugadoresGanadores = new ArrayList<Jugador>();
    crearBolas();
    bingo = new Bingo(tipoJuego(tipo), premio);
    bingo.setBolas(bolas);
  }

  private TJuego tipoJuego(String tipo) {
    if (tipo.equals("Jugar en X")) {
      return TJuego.JUGAR_EN_X;
    } else if (tipo.equals("Cuatro Esquinas")) {
      return TJuego.CUATRO_ESQUINAS;
    } else if (tipo.equals("Carton Lleno")) {
      return TJuego.CARTON_LLENO;
    } else {
      return TJuego.JUGAR_EN_Z;
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Cantar Número":
        cantarNumero();
        break;
      case "Regresar":
        cerrarVentanaJuego();
        break;
      default:
        break;
    }
  }

  // se crean las 75 bolas del juego
  private void crearBolas() {
    for (int i = 1; i <= 75; i++) {
      Bola b = new Bola(i);
      bolas.add(b);
    }
  }

  private void cerrarVentanaJuego() {
    vista.setVisible(false);
    vistaAnterior.setVisible(true);
  }

  // Esta funcion es "la maquina" que canta los numeros
  private void cantarNumero() {
    int num;
    boolean numeroCantado = false;
    // while (!numeroCantado) {
    num = (int) (Math.random() * (76 - 1)) + 1;
    for (Bola cantado : bolas) {
      // System.out.println(num);
      if (cantado.getNum() == num) {
        if (!cantado.getEstado()) {
          cantado.setEstado(true);
          jugados.add(num);
          vista.setNumCantados(num);
          numeroCantado = true;
          // System.out.println("Numero cantado: "+num);
          break;
        }
      }
      // }
    }
    //if (numeroCantado) {
    hayGanador();
    //}
    //cantarNumero();
  }

  // validar si ya hay ganador
  public void hayGanador() {
    String tipoJuego = vista.elTipoDeJuego.getText();
    if (tipoJuego.equals("Jugar en X")) {
      if (!jugarEnX().isEmpty()) {
        String ganador = "";
        for (Carton carton : jugarEnX()) {
          ganador += carton.getID() + "-";
        }
        JOptionPane.showMessageDialog(vista, "El ganador es: " + ganador);
        bingo.setNumCantados(jugados);
        bingo.setBolas(bolas);
        bingo.setJugadoresGanadores(jugadoresGanadores);
        BingoDAOXML bingoDAO = new BingoDAOXML();
        if (bingoDAO.registrarBingo(bingo)) {
          JOptionPane.showMessageDialog(vista, "Se ha registrado el juego correctamente");
        } else {
          JOptionPane.showMessageDialog(vista, "No se ha registrado el juego");
        }
        cerrarVentanaJuego();
      }
      return;
    }
    if (tipoJuego.equals("Cuatro Esquinas")) {
      if (!cuatroEsquinas().isEmpty()) {
        String ganadores = "";
        for (Carton g : cuatroEsquinas()) {
          ganadores += g.getID() + "-";
        }
        JOptionPane.showMessageDialog(null, "El ganador es: " + ganadores);
        bingo.setNumCantados(jugados);
        bingo.setBolas(bolas);
        bingo.setJugadoresGanadores(jugadoresGanadores);
        BingoDAOXML bingoDAO = new BingoDAOXML();
        if (bingoDAO.registrarBingo(bingo)) {
          JOptionPane.showMessageDialog(null, "Se guardo el juego");
        } else {
          JOptionPane.showMessageDialog(null, "No se guardo el juego");
        }
        System.out.println(ganadores);
        cerrarVentanaJuego();
      }
      return;
    }
    if (tipoJuego.equals("Carton Lleno")) {
      if (!cartonLleno().isEmpty()) {
        String ganadores = "-";
        for (Carton g : cartonLleno()) {
          ganadores += g.getID() + "-";
        }
        JOptionPane.showMessageDialog(null, "Ganador(es):\n" + ganadores);
        bingo.setNumCantados(jugados);
        bingo.setJugadoresGanadores(jugadoresGanadores);
        BingoDAOXML bd = new BingoDAOXML();
        if (bd.registrarBingo(bingo)) {
          JOptionPane.showMessageDialog(null, "Bingo registrado con exito");
        } else {
          JOptionPane.showMessageDialog(null, "Error al registrar el Bingo");
        }
        System.out.println(ganadores);
        cerrarVentanaJuego();
      }
      return;
    }
    if (tipoJuego.equals("Jugar en Z")) {
      if (!jugarEnZ().isEmpty()) {
        String ganadores = "";
        for (Carton g : jugarEnZ()) {
          ganadores += g.getID() + "-";
        }
        JOptionPane.showMessageDialog(null, "Ganador(es):\n" + ganadores);
        bingo.setNumCantados(jugados);
        bingo.setJugadoresGanadores(jugadoresGanadores);
        BingoDAOXML bd = new BingoDAOXML();
        if (bd.registrarBingo(bingo)) {
          JOptionPane.showMessageDialog(null, "Bingo registrado con exito");
        } else {
          JOptionPane.showMessageDialog(null, "Error al registrar el Bingo");
        }
        System.out.println(ganadores);
        cerrarVentanaJuego();
      }
      return;
    }

  }

  // Funciones para validar los cartones ganadores
  public ArrayList<Carton> cartonLleno() {
    ArrayList<Carton> ganadores = new ArrayList<Carton>();
    for (Carton actual : cartones) {
      int[][] nums = actual.getValores();
      boolean gana = true;

      for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
          // al ser carton lleno, si no tiene un numero incluido, se descarta como ganador
          if (!jugados.contains(nums[i][j])) {
            gana = false;
          }
        }
      }
      if (gana) {
        ganadores.add(actual);
        if (actual.getDuenio() != null) {
          jugadoresGanadores.add(actual.getDuenio());
        }
      }
    }
    return ganadores;
  }

  public ArrayList<Carton> jugarEnZ() {
    ArrayList<Carton> ganadores = new ArrayList<Carton>();
    for (Carton actual : cartones) {
      int[][] nums = actual.getValores();
      boolean gana = true;
      for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
          if (i == 0 || i == 4) {
            if (!jugados.contains(nums[i][j])) {
              gana = false;
            }
          }
          if (i < 4 && 0 < i) {
            if (!jugados.contains(nums[i][j])) {
              gana = false;
              i++;
            }
          }
        }
      }
      if (gana) {
        ganadores.add(actual);
        if (actual.getDuenio() != null) {
          jugadoresGanadores.add(actual.getDuenio());
        }
      }
    }
    return ganadores;
  }

  public ArrayList<Carton> cuatroEsquinas() {
    ArrayList<Carton> ganadores = new ArrayList<Carton>();
    for (Carton actual : cartones) {
      int[][] nums = actual.getValores();
      boolean gana = true;
      for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
          if (i == 0 || i == 4) {
            if (!jugados.contains(nums[i][j])) {
              gana = false;
              j += 3;
            }
          }
        }
      }
      if (gana) {
        ganadores.add(actual);
        if (actual.getDuenio() != null) {
          jugadoresGanadores.add(actual.getDuenio());
        }
      }
    }
    return ganadores;
  }

  public ArrayList<Carton> jugarEnX() {
    ArrayList<Carton> ganadores = new ArrayList<Carton>();
    for (Carton actual : cartones) {
      int[][] nums = actual.getValores();
      boolean gana = true;
      for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
          //System.out.println("i: " + i + " j: " + j);
          if (i == 0 || i == 4) {
            if (!jugados.contains(nums[i][j])) {//
              gana = false;
              j += 3;
            }
          }
          if ((i == 1 || i == 3) && (j == 1 || j == 3)) { //
            if (!jugados.contains(nums[i][j])) {
              gana = false;
            }
          }
          if (i == 2 && j == 2 ) { // si es el dato del medio
            if (!jugados.contains(nums[i][j])) {
              gana = false;
            }
          }
        }
      }
      if (gana) {
        ganadores.add(actual);
        if (actual.getDuenio() != null) {
          jugadoresGanadores.add(actual.getDuenio());
        }
      }
    }
    return ganadores;
  }
}
