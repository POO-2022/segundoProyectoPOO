/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.ArrayList;

import modelo.Bingo;

/**
 *
 * @author Andy Porras
 */
public interface BingoDAO {
  public abstract boolean registrarBingo(Bingo pBingo);
  public abstract ArrayList<Bingo> cargarBingos();
}