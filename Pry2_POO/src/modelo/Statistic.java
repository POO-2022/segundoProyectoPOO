/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 * Clase generica para la realizacion de las bases estadisticas de los graficos mostrados en el programa
 * @author KEVIN SALAZAR
 */

public class Statistic {
    private int dato;
    private String dato_aux;
    private int cant;
    
    /**
     * Constructor de la clase
     * @param pDato dato (si fuese entero)
     * @param pDato_aux dato (si fuese string)
     * @param pCant las veces que se repite dicho dato
     */
    public Statistic(int pDato, String pDato_aux, int pCant){
      setDato(pDato);
      setDato_aux(pDato_aux);
      setCant(cant);
    }

    public int getDato() {
        return dato;
    }

    public String getDato_aux() {
        return dato_aux;
    }

    public void setDato_aux(String dato_aux) {
        this.dato_aux = dato_aux;
    }
    
    public void setDato(int dato) {
        this.dato = dato;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }
    
    
}
