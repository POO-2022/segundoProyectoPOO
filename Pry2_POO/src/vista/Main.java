/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import modelo.Bola;

/**
 *
 * @author Ing. Kevin
 */
public class Main {
  public static void main(String[] args) {
    Bola[][] bola = new Bola[5][5];
    for (int i = 0; i < bola.length; i++) {
      for (int j = 0; j < bola[i].length; j++) {
        bola[i][j] = new Bola(j*i);
      }
    }
    for (int i = 0; i < bola.length; i++) {
      for (int j = 0; j < bola[i].length; j++) {
        System.out.print(bola[i][j].getNum() + " ");
      }
      System.out.println();
    }
  }
}
