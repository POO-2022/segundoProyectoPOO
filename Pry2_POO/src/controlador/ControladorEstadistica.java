/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
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
  public ArrayList<String> top5Ganadores; //se pueden recuperar de la base de datos csv
  public ArrayList<Integer> top10Nums;
  public ArrayList<String> cantPartidas;
  public MenuPrincipal vistaAnterior;
  
  /**
   * 
   * @param pVistaEstadistica
   * @param pVistaAnterior la ventana previa a la que se abrió
   * @param t5g lista con el historial de  ganado
   * @param t10n lista con todos los numeros más cantados
   * @param cpt lista con un historial de configuracion de partidas
   */
  public ControladorEstadistica(Estadistica pVistaEstadistica, MenuPrincipal pVistaAnterior, 
          ArrayList<String> t5g, ArrayList<Integer> t10n, ArrayList<String> cpt) {
    this.vista = pVistaEstadistica; 
    this.vistaAnterior = pVistaAnterior;
    this.vista.btConsultar.addActionListener(this);
    this.vista.btVolver.addActionListener(this);
    this.top10Nums = t10n;
    this.top5Ganadores = t5g;
    this.cantPartidas = cpt;
    this.DBNumeros = getTop10Nums(t10n);
    this.DBGanadores = getGanadores(t5g);
    this.DBPartidas = getConfigPartidas(cpt);
  }
  
  public ArrayList<Statistic> getTop10Nums(ArrayList <Integer> lista){
    ArrayList<Statistic> res = new ArrayList<Statistic>();  
    for(int i = 1; i<=75; i++){
        Statistic nuevo = new Statistic(i, null, 0);
        int contador = 0;
        for(Integer actual: lista){
            if(actual == i){
                contador++;
            }
        }
        nuevo.setCant(contador);
        res.add(nuevo);
    }
    
    //Seleccion de los 10 numeros más cantados
    int dataN[] = new int[res.size()];
    ArrayList<Statistic> resultado = new ArrayList<Statistic>();
    int n = 0;
    for(Statistic s: res){
      dataN[n] = s.getCant();
      n++;
    }
    dataN = bubbleSort(dataN);
    
    for(int i = 74; i>74-10; i--){
      for(Statistic s: res){
        if(dataN[i] == s.getCant()){
          resultado.add(s);  
          res.remove(s);
          break;
          
        }
      }
    }
    return resultado;
  }
  
  //algoritmo de ordenamiento burbuja
  public int[] bubbleSort(int arr[]){
    int result[]=arr;
    int n = result.length;
    for (int i = 0; i < n - 1; i++)
      for (int j = 0; j < n - i - 1; j++)
        if (result[j] > result[j + 1]) {
          int temp = result[j];
          result[j] = result[j + 1];
          result[j + 1] = temp;
        }
    return result;        
  }
  
  public ArrayList<Statistic> getConfigPartidas(ArrayList<String> configs){
    ArrayList<Statistic> res = new ArrayList<Statistic>();
  
    int contX=0;
    int contZ=0;
    int contL=0;
    int contE=0;
    for(int i = 0; i<configs.size(); i++){
      if(configs.get(i).equals("Z")){
        contZ++;
      }
      if(configs.get(i).equals("X")){
        contX++;
      }
      if(configs.get(i).equals("L")){
        contL++;
      }
      if(configs.get(i).equals("E")){
        contE++;
      }
    }
    res.add(new Statistic(0,"Juego en X",contX));
    res.add(new Statistic(0,"Juego en Z",contZ));
    res.add(new Statistic(0,"Carton Lleno",contL));
    res.add(new Statistic(0,"Cuatro Esquinas",contE));
    
    return res;
  }
  
  public ArrayList<Statistic> getGanadores(ArrayList<String> ganadores){
    ArrayList<Statistic> res = new ArrayList<Statistic>();
    
    for(int i = 0; i<ganadores.size(); i++){
      Statistic nuevo = new Statistic (0, "", 0);
      int contador=0;
      String actual=ganadores.get(i);
      nuevo.setDato_aux(actual);
      for(String s: ganadores){
        if(actual.equals(s)){
          contador++;
        }   
      }
      
      for(int a=0; a<ganadores.size(); a++){
        String obj = ganadores.get(a);  
        if(obj.equals(actual)){
            ganadores.remove(obj);
        }  
      }
      
      nuevo.setCant(contador);
      res.add(nuevo);
    }
    
    return res;
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
    JFreeChart grafico = null;  
    DefaultCategoryDataset datos = new DefaultCategoryDataset();
    String name = "Grafica";
    for(Statistic actual: DBNumeros){
        int dato = actual.getCant();
        String txt = String.valueOf(actual.getDato());
        datos.addValue(dato, "Grafica 1", txt);
    }
    grafico = ChartFactory.createBarChart("Grafica Prueba", "Eje X", "Eje Y", datos, PlotOrientation.VERTICAL, true, true, false);
    ChartPanel cPanel = new ChartPanel(grafico);
    JFrame informacion = new JFrame("Grafica");
    informacion.getContentPane().add(cPanel);
    informacion.pack();
    informacion.setVisible(true); 
    informacion.setLocationRelativeTo(null);
    return;
  }
  
  //historial de cuantas partidas de cada tipo se han hecho
  public void histConfigPartidas(){
    JFreeChart grafico = null;  
    DefaultPieDataset pastel = new DefaultPieDataset();
    String name = "Grafica";
    System.out.println(DBPartidas.size());
    /*for(int i = 0; i<DBPartidas.size(); i++){
      pastel.setValue(DBPartidas.get(i).getDato_aux(), DBPartidas.get(i).getCant());      
    }*/
    for(Statistic modo:DBPartidas){
      pastel.setValue(modo.getDato_aux(), modo.getCant());
      System.out.println(modo.getDato_aux()+":"+ modo.getCant());
    }
    grafico = ChartFactory.createPieChart("GraficaPrueba", pastel, true, true, false);
    ChartPanel cPanel = new ChartPanel(grafico);
    JFrame informacion = new JFrame("Grafica");
    informacion.getContentPane().add(cPanel);
    informacion.pack();
    informacion.setVisible(true); 
    informacion.setLocationRelativeTo(null);
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
    JFreeChart grafico = null;  
    DefaultPieDataset pastel = new DefaultPieDataset();
    for(int i = 0; i<DBGanadores.size(); i++){
        if(i==5){ //solo son necesarios 5 ganadores
            break;
        }
        pastel.setValue(DBGanadores.get(i).getDato_aux(), DBGanadores.get(i).getCant());
    }
    grafico = ChartFactory.createPieChart3D("GraficaPrueba", pastel, true, true, false);
    ChartPanel cPanel = new ChartPanel(grafico);
    JFrame informacion = new JFrame("Grafica");
    informacion.getContentPane().add(cPanel);
    informacion.pack();
    informacion.setVisible(true); 
    informacion.setLocationRelativeTo(null);
    return;
  }
  
  private void cerrarVentanaEstadistica() {
    this.vista.setVisible(false);
    this.vistaAnterior.setVisible(true);
  }
  
}
