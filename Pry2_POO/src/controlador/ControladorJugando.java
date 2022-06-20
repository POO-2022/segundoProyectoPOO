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
import dao.Email;
import modelo.Carton;
import modelo.Jugador;
import modelo.TJuego;
import modelo.Bingo;
import modelo.Bola;
import vista.IniciarJuego;
import vista.Jugando;

/**
 * clase que controla la ventana de juego 
 * @author Andy Porras
 * @author KEVIN SALAZAR
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
  int elPremio;
  /*
   * constructor de la clase
   * @param pJugador ventana de juego
   * @param pJugadores lista de jugadores
   * @param pCartones lista de cartones
   * @param pVistaAnterior ventana anterior
   * @param pTipo tipo de juego
   * @param pPremio premio del juego
   */
  public ControladorJugando(Jugando pJugador, ArrayList<Jugador> pJugadores, ArrayList<Carton> pCartones,
      IniciarJuego pVistaAnterior, String pTipo, int pPremio) {
    vista = pJugador;
    this.jugadores = pJugadores;
    this.cartones = pCartones;
    this.vistaAnterior = pVistaAnterior;
    vista.btCantarNumero.addActionListener(this);
    vista.btRegresarIniciarJuego.addActionListener(this);
    vista.setPremio(pPremio);
    elPremio = pPremio; //es necesario para la funcion de notificacion al jugador ganador
    vista.setTipoJuego(pTipo);
    vista.setCantidadJugadores(jugadores.size());
    vista.setCantidadCartones(cartones.size());
    jugados = new ArrayList<Integer>();
    bolas = new ArrayList<Bola>();
    jugadoresGanadores = new ArrayList<Jugador>();
    crearBolas();
    bingo = new Bingo(tipoJuego(pTipo), pPremio);
    bingo.setBolas(bolas);
  }

  /*
   * metodo que transforma el tipo de juego en un objeto de tipo TJuego
   */
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

  /*
   * metodo que captura el evento del boton de cantar numero
   * y el boton de regresar a la ventana de inicio de juego
   */
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

  /*
   * metodo que crea las bolas para el juego
   */
  private void crearBolas() {
    for (int i = 1; i <= 75; i++) {
      Bola b = new Bola(i);
      bolas.add(b);
    }
  }

  /*
   * metodo que cierra la ventana de juego
   */
  private void cerrarVentanaJuego() {
    vista.setVisible(false);
    vistaAnterior.setVisible(true);
  }

  /*
   * Esta funcion es "la maquina" que canta los numeros
   */
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

  /*
   * metodo que verifica si hay ganador
   */
  public void hayGanador() {
    String tipoJuego = vista.elTipoDeJuego.getText();
    if (tipoJuego.equals("Jugar en X")) {
      if (!jugarEnX().isEmpty()) {
        String ganador = "";
        for (Carton carton : jugarEnX()) {
          ganador += carton.getID() + "-";
          jugadoresGanadores.add(carton.getDuenio());
        }
        JOptionPane.showMessageDialog(vista, "El ganador es: " + ganador);
        //notificando al jugador del gane
        for(Carton g: jugarEnX()){
          try{
            String msg = "Saludos, "+g.getDuenio().getNombre()+", es un gusto notificarle que usted es el ganador "
                    + "del juego de bingo en modalidad Juego en X, por un monto de "+elPremio+".\n\n ¡Felicitaciones! \n\n\nPor favor, envíenos su número de cuenta, "
                    + "su cédula y cuantos organos le sirven para confirmar y enviarle su premio. \n¡Gracias por jugar!";
            String asunto ="Ganador de juego de bingo";
            Email e = new Email(g.getID(), g.getDuenio().getNombre(), g.getDuenio().getCorreo(), msg, asunto);
          }catch(Exception e){
              continue;
          }  
        }
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
          jugadoresGanadores.add(g.getDuenio());
        }
        JOptionPane.showMessageDialog(null, "El ganador es: " + ganadores);
        //notificando al jugador del gane
        for(Carton g: cuatroEsquinas()){
          try{
            String msg = "Saludos, "+g.getDuenio().getNombre()+", es un gusto notificarle que usted es el ganador "
                    + "del juego de bingo en modalidad Cuatro Esquinas, por un monto de "+elPremio+".\n\n ¡Felicitaciones! \n\n\nPor favor, envíenos su número de cuenta, "
                    + "su cédula y cuantos organos le sirven para confirmar y enviarle su premio. \n¡Gracias por jugar!";
            String asunto ="Ganador de juego de bingo";
            Email e = new Email(g.getID(), g.getDuenio().getNombre(), g.getDuenio().getCorreo(), msg, asunto);
          }catch(Exception e){
              continue;
          }  
        }
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
          jugadoresGanadores.add(g.getDuenio());
        }
        JOptionPane.showMessageDialog(null, "Ganador(es):\n" + ganadores);
        //notificando a los ganadores mediante correo
        for(Carton g: cartonLleno()){
          try{
            String msg = "Saludos, "+g.getDuenio().getNombre()+", es un gusto notificarle que usted es el ganador "
                    + "del juego de bingo en modalidad Carton Lleno, por un monto de "+elPremio+".\n\n ¡Felicitaciones! \n\n\nPor favor, envíenos su número de cuenta, "
                    + "su cédula y cuantos organos le sirven para confirmar y enviarle su premio. \n¡Gracias por jugar!";
            String asunto ="Ganador de juego de bingo";
            Email e = new Email(g.getID(), g.getDuenio().getNombre(), g.getDuenio().getCorreo(), msg, asunto);
          }catch(Exception e){
              continue;
          }  
        }
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
          jugadoresGanadores.add(g.getDuenio());
        }
        JOptionPane.showMessageDialog(null, "Ganador(es):\n" + ganadores);
        for(Carton g: jugarEnZ()){
          try{
            String msg = "Saludos, "+g.getDuenio().getNombre()+", es un gusto notificarle que usted es el ganador "
                    + "del juego de bingo en modalidad Juego en Z, por un monto de "+elPremio+".\n\n ¡Felicitaciones! \n\n\nPor favor, envíenos su número de cuenta, "
                    + "su cédula y cuantos organos le sirven para confirmar y enviarle su premio. \n¡Gracias por jugar!";
            String asunto ="Ganador de juego de bingo";
            Email e = new Email(g.getID(), g.getDuenio().getNombre(), g.getDuenio().getCorreo(), msg, asunto);
          }catch(Exception e){
              continue;
          }  
        }
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

  /*
   * metodo que retorna los cartones llenos ganadores
   * @return lista de cartones llenos ganadores
   */
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
        // if (actual.getDuenio() != null) {
        //   jugadoresGanadores.add(actual.getDuenio());
        // }
      }
    }
    return ganadores;
  }

  /*
   * metodo que retorna los cartones ganadores en juego en z
   * @return lista de cartones ganadores en juego en z
   */
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
          if (i == 1 && j == 3) {
            if (!jugados.contains(nums[i][j])) {
              gana = false;
            }
          }
          if (i == 2 && j == 2) {
            if (!jugados.contains(nums[i][j])) {
              gana = false;
            }
          }
          if (i == 3 && j == 1) {
            if (!jugados.contains(nums[i][j])) {
              gana = false;
            }
          }
        }
      }
      if (gana) {
        ganadores.add(actual);
        // if (actual.getDuenio() != null) {
        //   jugadoresGanadores.add(actual.getDuenio());
        // }
      }
    }
    return ganadores;
  }

  /*
   * metodo que retorna los cartones ganadores en juego en cuatro esquinas
   * @return lista de cartones ganadores en juego en cuatro esquinas
   */
  public ArrayList<Carton> cuatroEsquinas() {
    ArrayList<Carton> ganadores = new ArrayList<Carton>();
    for (Carton actual : cartones) {
      int[][] nums = actual.getValores();
      boolean gana = true;
      for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
          if ((i == 0 || i == 4) && (j == 0 || j == 4)) {
            if (!jugados.contains(nums[i][j])) {
              gana = false;
            }
          }
        }
      }
      if (gana) {
        ganadores.add(actual);
        // if (actual.getDuenio() != null) {
        //   System.out.println(actual.getDuenio().getNombre());
        //   jugadoresGanadores.add(actual.getDuenio());
        // }
      }
    }
    return ganadores;
  }

  /*
   * metodo que retorna los cartones ganadores en juego en x
   * @return lista de cartones ganadores en juego en x
   */
  public ArrayList<Carton> jugarEnX() {
    ArrayList<Carton> ganadores = new ArrayList<Carton>();
    for (Carton actual : cartones) {
      int[][] nums = actual.getValores();
      boolean gana = true;
      for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
          //System.out.println("i: " + i + " j: " + j);
          if ((i == 0 || i == 4) && (j == 0 || j == 4)) {
            if (!jugados.contains(nums[i][j])) {//
              gana = false;
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
        // if (actual.getDuenio() != null) {
        //   jugadoresGanadores.add(actual.getDuenio());
        // }
      }
    }
    return ganadores;
  }
}
