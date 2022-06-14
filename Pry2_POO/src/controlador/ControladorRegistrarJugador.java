/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import vista.MenuPrincipal;
import vista.RegistrarJugador;
import modelo.Bingo;
import modelo.Jugador;

/**
 *
 * @author KEVIN SALAZAR
 * @version 1.0
 */
public class ControladorRegistrarJugador implements ActionListener{
    /*
    ***detalles a considerar***
    * Para la parte de correo, en la clase jugador hay una funcion booleana que valida que el correo este bien escrito
      dicha funcion es primordial para poder crear al jugador, si eso no se valida, va a dar futuros problemas
    
    * la cedula esta declarada como int
    * no se hacer interfaz xd
    */
    public RegistrarJugador vista;
    
    public ControladorRegistrarJugador(){
      
    }
}
