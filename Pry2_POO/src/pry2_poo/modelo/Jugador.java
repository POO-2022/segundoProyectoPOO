/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pry2_poo.modelo;

/**
 *
 * @author Kevin Salazar
 * @version 1.0
 */
public class Jugador {
  private String nombre;
  private String correo;
  private int cedula;
  
  /**
   * Constructor principal de la clase
   * @param pNombre Nombre del jugador
   * @param pCorreo Correo electronico del jugador
   * @param pCedula Cedula del jugador
   */
  public Jugador(String pNombre, String pCorreo, int pCedula){
    setNombre(pNombre);
    setCorreo(pCorreo);
    setCedula(pCedula);
  }  

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String pNombre) {
    this.nombre = pNombre;
  }

  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String pCorreo) {
    this.correo = pCorreo;
  }

  public int getCedula() {
    return cedula;
  }

  public void setCedula(int pCedula) {
    this.cedula = pCedula;
  }
}
