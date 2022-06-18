/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Andy Porras
 */
public class Bingo {
  private Date fecha;
  private Date hora; 
  private TJuego tipJuego;
  private ArrayList<Integer> numeroJugado;
  private ArrayList<Carton> cartones;
  private ArrayList<Jugador> jugadores;
  private int premio;
  private ArrayList<Jugador> ganadores;
  private ArrayList<Bola> bolas;
  
  public Bingo(TJuego pTipo, int pPremio) {
    setTipJuego(pTipo);
    setPremio(pPremio);
    setFecha();
    setHora();
    numeroJugado = new ArrayList<Integer>();
    cartones = new ArrayList<Carton>();
    jugadores = new ArrayList<Jugador>();
    ganadores = new ArrayList<Jugador>();
    bolas = new ArrayList<Bola>();
    crearBolas();
  }

  public int cantarNumero() {
    int num = (int) (Math.random() *(76-1)) + 1;
    boolean numeroCantado = false;
    while (!numeroCantado) {
      num = (int)(Math.random()*(76-1))+1;
      for(Bola cantado:bolas){
        //System.out.println(num);
        if(cantado.getNum()==num){
          if(!cantado.getEstado()){
            cantado.setEstado(true);
            numeroJugado.add(num);
            //System.out.println("Numero cantado: "+num);
            break;
          }
        }
      }  
      numeroCantado = true;
    } 
    return num;
  }

  private void setHora() {
    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    Date date = new Date();
    hora = date;
  }

  private void setFecha() {
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    Date date = new Date();
    fecha = date;
  }

  public void addNumeroJugado(Integer num){
    numeroJugado.add(num);
  }

  public void addCarton(Carton pCarton){
    //verificar que no se repitan IDs de cartones
    for(Carton act: cartones){
      if(act.getID().equals(pCarton.getID())){
        return;
      }
    }
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
  public String getTipoJuego(){
    switch(tipJuego){
      case JUGAR_EN_X:
        return "X";
      case CUATRO_ESQUINAS:
        return "E";
      case CARTON_LLENO:
        return "L";
      case JUGAR_EN_Z:
        return "Z";
      default:
        return "";
    }
  }

  public void setTipJuego(TJuego tipJuego) {
    this.tipJuego = tipJuego;
  }

  public ArrayList<Integer> getNumeroJugado() {
    return numeroJugado;
  }
  public void setNumCantados(ArrayList<Integer> pNum){
    numeroJugado = pNum;
  }
  public void setJugadoresGanadores(ArrayList<Jugador> pJugadores){
    ganadores = pJugadores; 
  }
  public ArrayList<Jugador> getJugadoresGadores(){
    return ganadores;
  }

  public int getPremio() {
    return premio;
  }

  public void setPremio(int premio) {
    this.premio = premio; 
  }

  public ArrayList<Bola> getBolas() {
    return bolas;
  }
  private void crearBolas(){
    for(int i = 1; i<=75; i++){
        Bola b = new Bola(i);
        bolas.add(b);
    }
}
  public ArrayList<Bola> getBolasJugadas(){
    ArrayList<Bola> bolasJugadas = new ArrayList<Bola>();
    for(Bola act: bolas){
      if(act.getEstado() == true){
        bolasJugadas.add(act);
      }
    }
    return bolasJugadas;
  }

  public void setBolas(ArrayList<Bola> bolas) {
    this.bolas = bolas;
  }

  public String getFecha() {
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    return dateFormat.format(fecha);
  }

  public String getHora() {
    DateFormat dateFormat = new SimpleDateFormat("HH:mm");
    return dateFormat.format(hora);
  }

  /**
   * Asigna un carton al jugador
   * @param pIDCarton id del carton que recibira el jugador
   * @param pIDJugador: el numero de cedula del jugador
   */

  public boolean enviarCartonAJugador(String pIDCarton, int pIDJugador){
    Carton carton=null;
    
    //buscar el carton en la lista de cartones
    for(Carton actual: cartones){
      if(actual.getID().equals(pIDCarton)){
        //buscar al jugador  
        for(Jugador j: jugadores){
          if(j.getCedula() == pIDJugador){
            //asignar el carton  
            if(j.addCarton(carton)){
              actual.setAsignado();
              return true;
            }
          }
        }
      }
    }return false;
  }
}
