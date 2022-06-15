/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import vista.MenuPrincipal;
import vista.Estadistica;
import modelo.Bingo;
import modelo.Carton;
import modelo.Jugador;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
/**
 *
 * @author Kevin Salazar
 * @version 1.0
 */
public class ControladorEstadistica implements ActionListener{
  public Estadistica vista;
  public ArrayList<Jugador> jugadores;
  public ArrayList<Carton> cartones;
  public MenuPrincipal vistaAnterior;
  
  public ControladorEstadistica(Estadistica pVistaEstadistica, ArrayList<Jugador> pJugadores,
      ArrayList<Carton> pCartones, MenuPrincipal pVistaAnterior) {
    this.vista = pVistaEstadistica;
    this.jugadores = pJugadores;
    this.cartones = pCartones;
    this.vistaAnterior = pVistaAnterior;
    this.vista.btConsultar.addActionListener(this);
    this.vista.btVolver.addActionListener(this);
  }
  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Volver":
        cerrarVentanaEstadistica();
        break;
      case "Consultar": 
        generarConsulta();  
        break;
      default:
        break;
    }
  }
  
  public void generarConsulta(){
      String tipo = vista.getTipoConsulta();
      JFreeChart grafico = null;
      DefaultCategoryDataset datos = new DefaultCategoryDataset();
      int d1 = 210;
      int d2 = 65;
      int d3 = 84;
      int d4 = 154;
      int d5 = 55;
      int d6 = 75;
      int d7 = 525;
      int d8 = 0;
      int d9 = 57;
      int d10 = 14;
      datos.addValue(d1, "Grafica 1", "Uno");
      datos.addValue(d2, "Grafica 1", "Dos");
      datos.addValue(d3, "Grafica 1", "Tres");
      datos.addValue(d4, "Grafica 1", "Cuatro");
      datos.addValue(d5, "Grafica 1", "Cinco");
      datos.addValue(d6, "Grafica 1", "seis");
      datos.addValue(d7, "Grafica 1", "siete");
      datos.addValue(d8, "Grafica 1", "ocho");
      datos.addValue(d9, "Grafica 1", "nueve");
      datos.addValue(d10, "Grafica 1", "diez");
      
      if(tipo.equals("Números más cantados")){  
        grafico = ChartFactory.createBarChart("Grafica Prueba", "Eje X", "Eje Y", datos, PlotOrientation.VERTICAL, true, true, false);
      }
      if(tipo.equals("Historial config de partidas")){
        DefaultPieDataset pastel = new DefaultPieDataset();
        pastel.setValue("Uno", d1);
        pastel.setValue("Dos", d2);
        pastel.setValue("Tres", d3);
        pastel.setValue("Cuatro", d4);
        pastel.setValue("Cinco",d5);
        pastel.setValue("seis",d6);
        pastel.setValue("siete",d7);
        pastel.setValue("ocho",d8);
        pastel.setValue("nueve",d9);
        pastel.setValue("diez",d10);
        grafico = ChartFactory.createPieChart("GraficaPrueba", pastel, true, true, false);  
      }
      if(tipo.equals("Cantidad de partidas por fecha")){
        JOptionPane.showMessageDialog(null, "Opción no disponible...\nDisculpe los inconvenientes", "Atención", JOptionPane.WARNING_MESSAGE);
        return;
      }
      if(tipo.equals("Top 5 ganadores")){
        DefaultPieDataset pastel = new DefaultPieDataset();
        pastel.setValue("Uno", d1);
        pastel.setValue("Dos", d2);
        pastel.setValue("Tres", d3);
        pastel.setValue("Cuatro", d4);
        pastel.setValue("Cinco",d5);
        pastel.setValue("seis",d6);
        pastel.setValue("siete",d7);
        pastel.setValue("ocho",d8);
        pastel.setValue("nueve",d9);
        pastel.setValue("diez",d10);
        grafico = ChartFactory.createPieChart3D("GraficaPrueba", pastel, true, true, false);  
      }
      ChartPanel cPanel = new ChartPanel(grafico);
      JFrame informacion = new JFrame("Grafica");
      informacion.getContentPane().add(cPanel);
      informacion.pack();
      informacion.setVisible(true); 
      informacion.setLocationRelativeTo(null);
  }
  
  private void cerrarVentanaEstadistica() {
    this.vista.setVisible(false);
    this.vistaAnterior.setVisible(true);
  }
  
}
