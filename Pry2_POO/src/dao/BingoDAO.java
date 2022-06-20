/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.ArrayList;

import modelo.Bingo;

/**
 * Clase padre que permite la gestion de los datos de los bingos en un archivo XML
 * @author Andy Porras
 */
public interface BingoDAO {
  public abstract boolean registrarBingo(Bingo pBingo);
  public abstract ArrayList<Integer> numerosCantados();
  public abstract ArrayList<String> listaFechasDeJuegos();
  public abstract ArrayList<String> listaTiposDeJuegos();
  public abstract ArrayList<String> listaJugadoresGanadores();
}
