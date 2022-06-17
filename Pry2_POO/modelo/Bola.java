/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;


/**
 *
 * @author Ing. Kevin
 */
public class Bola {
  private int num;
  private boolean jugado;
  
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
