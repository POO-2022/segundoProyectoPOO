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
import modelo.Statistic;
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
  public ArrayList<Statistic> DBGanadores;  
  public ArrayList<Statistic> DBNumeros; 
  public ArrayList<Statistic> DBPartidas;  
  public Estadistica vista;
  public ArrayList<Jugador> top5Ganadores; //se pueden recuperar de la base de datos csv
  public ArrayList<Integer> top10Nums;
  public ArrayList<String> cantPartidas;
  public MenuPrincipal vistaAnterior;
  public JFreeChart grafico;
  
  /**
   * 
   * @param pVistaEstadistica
   * @param pVistaAnterior la ventana previa a la que se abrió
   * @param t5g lista con el historial de  ganado
   * @param t10n lista con todos los numeros más cantados
   * @param cpt lista con un historial de configuracion de partidas
   */
  public ControladorEstadistica(Estadistica pVistaEstadistica, MenuPrincipal pVistaAnterior, 
          ArrayList<Jugador> t5g, ArrayList<Integer> t10n, ArrayList<String> cpt) {
    this.vista = pVistaEstadistica; 
    this.vistaAnterior = pVistaAnterior;
    this.vista.btConsultar.addActionListener(this);
    this.vista.btVolver.addActionListener(this);
    this.top10Nums = t10n;
    this.top5Ganadores = t5g;
    this.cantPartidas = cpt;
    this.DBNumeros = new ArrayList<Statistic>();
    this.DBGanadores = new ArrayList<Statistic>();
    this.DBPartidas = new ArrayList<Statistic>();
  }
  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Volver":
        cerrarVentanaEstadistica();
        break;
      case "Consultar": 
        try{  
          generarConsulta();  
        }
        catch(Exception exc){
            JOptionPane.showMessageDialog(null, "Error: datos insuficientes...");
        }
        break;
      default:
        break;
    }
  }
  
  public void generarConsulta(){
      String tipo = vista.getTipoConsulta();
      if(tipo.equals("Números más cantados")){  
        numsMasCantados();
        return;
      }
      if(tipo.equals("Historial config de partidas")){
        histConfigPartidas(); 
        return;
      }
      if(tipo.equals("Cantidad de partidas por fecha")){
        cantPartXFecha();
        return;
      }
      if(tipo.equals("Top 5 ganadores")){
        top5Ganadores();
        return;
      }
      return;
  }
  
  //estadistica de los 10 numeros mas cantados en partidas
  public void numsMasCantados(){
    DefaultCategoryDataset datos = new DefaultCategoryDataset();
    for(int i = 0; i<DBNumeros.size(); i++){
        datos.addValue(DBNumeros.get(i).getCant(), "Grafica 1", DBNumeros.get(i).getDato_aux());
    }grafico = ChartFactory.createBarChart("Grafica Prueba", "Eje X", "Eje Y", datos, PlotOrientation.VERTICAL, true, true, false);
    mostrar(grafico);
    return;
  }
  
  //historial de cuantas partidas de cada tipo se han hecho
  public void histConfigPartidas(){
    DefaultCategoryDataset datos = new DefaultCategoryDataset();
    DefaultPieDataset pastel = new DefaultPieDataset();
    for(int i = 0; i<DBPartidas.size(); i++){
        datos.addValue(DBPartidas.get(i).getCant(), "Grafica 1", DBPartidas.get(i).getDato_aux());
    }
    grafico = ChartFactory.createPieChart("GraficaPrueba", pastel, true, true, false);
    mostrar(grafico);
    return;
  }
  
  //estadistica de cantidad de partidas hechas por fecha
  //sin implementar...
  public void cantPartXFecha(){
    JOptionPane.showMessageDialog(null, "Opción no disponible...\nDisculpe los inconvenientes", "Atención", JOptionPane.WARNING_MESSAGE);
    return;
  }
  
  //top 5 ganadores
  public void top5Ganadores(){   
    DefaultPieDataset pastel = new DefaultPieDataset();
    for(int i = 0; i<DBGanadores.size(); i++){
        pastel.setValue(DBGanadores.get(i).getDato_aux(), DBGanadores.get(i).getCant());
    }
    grafico = ChartFactory.createPieChart3D("GraficaPrueba", pastel, true, true, false);
    mostrar(grafico);
    return;
  }
  
  //muestra el gráfico en pantalla
  public void mostrar(JFreeChart pGrafico){
    ChartPanel cPanel = new ChartPanel(pGrafico);
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
