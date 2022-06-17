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

  public ControladorJugando(Jugando pJugador, ArrayList<Jugador> pJugadores, ArrayList<Carton> pCartones, IniciarJuego pVistaAnterior, String tipo, int premio) {
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
    crearBolas();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "CantarNumero":
        cantarNumero();
        break;
      case "Regresar":
        cerrarVentanaJuego();
        break;
      default:
        break;
    }
  }
  
  //se crean las 75 bolas del juego
  private void crearBolas(){
      for(int i = 1; i<=75; i++){
          Bola b = new Bola(i);
          bolas.add(b);
      }
  }
  
  private void cerrarVentanaJuego() {
    vista.setVisible(false);
    vistaAnterior.setVisible(true);
  }

  //Esta funcion es "la maquina" que canta los numeros
  private void cantarNumero() {
    int num = (int)(Math.random()*(76-1))+1;
    
    for(Bola cantado:bolas){
      if(cantado.getNum()==num){
        if(!cantado.getEstado()){
          cantado.setEstado(true);
          jugados.add(num);
          break;
        }
      }
    }   
    
    hayGanador();
            
  }
  
  //validar si ya hay ganador
  public void hayGanador(){ 
    String tipoJuego = vista.elTipoDeJuego.getText();
    if(tipoJuego.equals("Jugar en X")){
        jugarEnX();
        return;
    }
    if(tipoJuego.equals("Cuatro Esquinas")){
        cuatroEsquinas();
        return;
    }
    if(tipoJuego.equals("Carton Lleno")){
      if(!cartonLleno().isEmpty()){
        String ganadores="-";  
        for(Carton g: cartonLleno()){
          ganadores+=g.getID()+"-";
        }
        JOptionPane.showMessageDialog(null, "Ganador(es):\n"+ganadores);
        cerrarVentanaJuego();
      }return;
    }
    if(tipoJuego.equals("Jugar en Z")){
        jugarEnZ();
        return;
    }

  }
  
  //Funciones para validar los cartones ganadores  
  public ArrayList<Carton> cartonLleno(){
    ArrayList<Carton> ganadores = new ArrayList<Carton>();
    for(Carton actual: cartones){
      int[][] nums=actual.getValores();
      boolean gana=true;

      for(int i = 0; i<5; i++){
        for(int j = 0; j<5; j++){
          //al ser carton lleno, si no tiene un numero incluido, se descarta como ganador
          if(!jugados.contains(nums[i][j])){
            gana=false;
          }
        }
      }
      if(gana){
        ganadores.add(actual);
      }
    }
    return ganadores;
  }
  
  public void jugarEnZ(){
      
  }
  
  public void cuatroEsquinas(){
      
  }
  
  public void jugarEnX(){
      
  }
}
