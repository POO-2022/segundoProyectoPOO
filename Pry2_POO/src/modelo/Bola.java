/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;


/**
 * Clase que representa una bola de un juego de Bingo
 * @author Ing. Kevin
 */
public class Bola {
  private int num;
  private boolean jugado;
  /*
   * Constructor de la clase Bola
   */
  public Bola(int pNum){
    num=pNum;  
    jugado = false;
  }  
  public void setEstado(boolean pEstado) {
      this.jugado = pEstado;
  }
  public boolean getEstado() {
      return jugado;
  }
  public int getNum() {
      return num;
  }
}
