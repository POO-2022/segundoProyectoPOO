/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author Andy Porras
 */
public class Bingo {
  private TJuego tipJuego;
  private ArrayList<Integer> numeroJugado;
  private ArrayList<Carton> cartones;
  private ArrayList<Jugador> jugadores;
  private double premio;
  private ArrayList<Jugador> ganadores;
  
  public Bingo(TJuego pTipo, double pPremio) {
    setTipJuego(pTipo);
    setPremio(pPremio);
    numeroJugado = new ArrayList<Integer>();
    cartones = new ArrayList<Carton>();
    jugadores = new ArrayList<Jugador>();
    ganadores = new ArrayList<Jugador>();
  }

  public void addNumeroJugado(Integer num){
    numeroJugado.add(num);
  }

  public void addCarton(Carton pCarton){
    cartones.add(pCarton);
  }

  public void addJugador(Jugador jugador){
    jugadores.add(jugador);
  }

  public void addGanadores(Jugador jugador){
    ganadores.add(jugador);
  }

  public TJuego getTipJuego() {
    return tipJuego;
  }

  public void setTipJuego(TJuego tipJuego) {
    this.tipJuego = tipJuego;
  }

  public ArrayList<Integer> getNumeroJugado() {
    return numeroJugado;
  }

  public double getPremio() {
    return premio;
  }

  public void setPremio(double premio) {
    this.premio = premio; 
  }

  /**
   * Asigna un carton al jugador
   * @param pIDCarton id del carton que recibira el jugador
   * @param pIDJugador: el numero de cedula del jugador
   */

   public void enviarCartonAJugador(String pIDCarton, int pIDJugador){
    Carton carton;
    for(Carton actual: cartones){
      if(actual.getID().equals(pIDCarton)){
        carton = actual;
        break;
      }
    }
    for(Jugador actual: jugadores){
      if(actual.getCedula() == pIDJugador){
        if(actual.addCarton(pCarton)){
          carton.setAsignado(true);
          return;
        }else{
          return;
        }
      }
    }
   }
}
