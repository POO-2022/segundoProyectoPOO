/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.util.Random;
import java.util.ArrayList;

/**
 * Clase que representa un carton de bingo
 * 
 * @author KEVIN SALAZAR
 * @version 1.0
 */
public class Carton {
  private String ID;
  private int[][] valores;
  private ArrayList<Bola> jugados;
  private boolean asignado;
  
  public Carton(){
    asignado = false;
    jugados = new ArrayList<Bola>();
    valores = new int[5][5];
    setID();
    setValores();
  }

  //se asigna automáticamente
  public void setID(){
    ID="";
    Random r = new Random();  
    ID+=(char) (r.nextInt(26) + 'A');
    ID+=(char) (r.nextInt(26) + 'A');
    ID+=(char) (r.nextInt(26) + 'A');
    ID+=(char) (r.nextInt(9) + 48);
    ID+=(char) (r.nextInt(9) + 48);
    ID+=(char) (r.nextInt(9) + 48);
  }
  public String getID(){
      return ID;
  }

  //se asignan automáticamente con random
  public void setValores(){
    int valor;  
    //columna 1 = valores entre 1 y 15
    for(int j=0; j<5; j++){
        valor = (int)(Math.random()*(16-1))+1;
        //validar que los numeros no se repitan
        for(int y=0;y<5;y++){
            if(valores[y][0]==valor){
                valor= (int)(Math.random()*(16-1))+1;
                y= -1;
            }
        }
        valores[j][0] = valor;
    }
    //columna 2 = valores entre 16 y 30
    for(int j=0; j<5; j++){
        valor= (int)(Math.random()*(31-16))+16;
        //validar que los numeros no se repitan
        for(int y=0;y<5;y++){
            if(valores[y][1]==valor){
                valor= (int)(Math.random()*(31-16))+16;
                y= -1;
            }
        }
        valores[j][1] = valor;
    }

    //columna 3 = valores entre 31 y 45
    for(int j=0; j<5; j++){
        valor= (int)(Math.random()*(46-31))+31;
        //validar que los numeros no se repitan
        for(int y=0;y<5;y++){
            if(valores[y][2]==valor){
                valor= (int)(Math.random()*(46-31))+31;
                y= -1;
            }
        }
        valores[j][2] = valor;
    }

    //columna 4 = valores entre 46 y 60
    for(int j=0; j<5; j++){
        valor= (int)(Math.random()*(61-46))+46;
        //validar que los numeros no se repitan
        for(int y=0;y<5;y++){
            if(valores[y][3]==valor){
                valor= (int)(Math.random()*(61-46))+46;
                y= -1;
            }
        }
        valores[j][3] = valor;
    }

    //columna 5 = valores entre 61 y 75
    for(int j=0; j<5; j++){
        valor= (int)(Math.random()*(76-61))+61;
        //validar que los numeros no se repitan
        for(int y=0;y<5;y++){
            if(valores[y][4]==valor){
                valor= (int)(Math.random()*(76-61))+61;
                y= -1;
            }
        }
        valores[j][4] = valor;
    }
  }

  public void setJugados(Bola b){
    jugados.add(b);
  }

  public ArrayList<Bola> getJugados(){
      return jugados;
  }

  public int[][] getValores(){
    return valores;
  }
  public void setAsignado(){
    asignado = true;
  }

  public boolean isAsignado(){
      return asignado;
  }
}
