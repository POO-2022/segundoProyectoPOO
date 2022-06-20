/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.Graphics2D;

import modelo.Carton;
import vista.VistaCarton;

/**
 * clase diseñada para controlar la vista del carton
 * 
 * @author Ing. Kevin
 */
public class ControladorVistaCarton {
  public VistaCarton vista;

  /*
   * constructor de la clase
   * 
   * @param pVistaVistaCartones
   */
  public ControladorVistaCarton(VistaCarton pVistaVistaCartones) {
    this.vista = pVistaVistaCartones;
  }

  /*
   * metodo que genera el carton
   */
  public void generacionCarton(Carton carton) {
    String id = carton.getID();
    int[][] nums = carton.getValores();
    vista.setLabelID(id);
    vista.setC00(String.valueOf(nums[0][0]));
    vista.setC01(String.valueOf(nums[0][1]));
    vista.setC02(String.valueOf(nums[0][2]));
    vista.setC03(String.valueOf(nums[0][3]));
    vista.setC04(String.valueOf(nums[0][4]));
    vista.setC10(String.valueOf(nums[1][0]));
    vista.setC11(String.valueOf(nums[1][1]));
    vista.setC12(String.valueOf(nums[1][2]));
    vista.setC13(String.valueOf(nums[1][3]));
    vista.setC14(String.valueOf(nums[1][4]));
    vista.setC20(String.valueOf(nums[2][0]));
    vista.setC21(String.valueOf(nums[2][1]));
    vista.setC22(String.valueOf(nums[2][2]));
    vista.setC23(String.valueOf(nums[2][3]));
    vista.setC24(String.valueOf(nums[2][4]));
    vista.setC30(String.valueOf(nums[3][0]));
    vista.setC31(String.valueOf(nums[3][1]));
    vista.setC32(String.valueOf(nums[3][2]));
    vista.setC33(String.valueOf(nums[3][3]));
    vista.setC34(String.valueOf(nums[3][4]));
    vista.setC40(String.valueOf(nums[4][0]));
    vista.setC41(String.valueOf(nums[4][1]));
    vista.setC42(String.valueOf(nums[4][2]));
    vista.setC43(String.valueOf(nums[4][3]));
    vista.setC44(String.valueOf(nums[4][4]));
    vista.saveFile(id);
  }

  /*
   * metodo que pinta la ventana
   */
  private void paint(Graphics2D g2) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                   // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }
}
