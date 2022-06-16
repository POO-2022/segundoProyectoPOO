/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
/**
 *
 * @author Kevin Salazar
 * @version 1.0
 */
public class Jugador {
  private String nombre;
  private String correo;
  private int cedula;
  private ArrayList<Carton> cartones;
  
  public Jugador(){
    cartones = new ArrayList<Carton>();
  }
  
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
    cartones = new ArrayList<Carton>();
  }  
  //metodos accesores
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
  
  /**agrega cartones al jugador
   * se permite un max de 5 cartones por jugador 
   * @param pCarton el carton a asignar
   * @return true si se agregó, false sino
   */
  public boolean addCarton(Carton pCarton){
    if(cartones.size()<5 && !pCarton.isAsignado()){
      cartones.add(pCarton);
      return true;
    }
    return false;
  }

  /**Valida que un correo este correctamente escrito
   * 
   * @param correo el correo a validar
   * @return true si es valido, false sino
   */
  public boolean validarCorreo(String correo){
      Pattern p = Pattern.compile("^[_a-z0-9-\\+]+(\\.[_a-z0-9-]+)*@"+ "[a-z0-9-]+(\\.[a-z0-9]+)*(\\.[a-z]{2,4})$");
      Matcher m = p.matcher(correo);
      return m.matches();
  }
}
