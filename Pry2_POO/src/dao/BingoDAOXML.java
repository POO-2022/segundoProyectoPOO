/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import modelo.Bingo;

/**
 *  Clase que permite la gestion de los datos de los bingos en un archivo XML
 *  tiene funciones para leer, escribir los datos de los bingos en un archivo XML
 *  es hija de la clase BingoDAO
 * @author Andy Porras
 */
public class BingoDAOXML implements BingoDAO {
  
  /*
   * Funcion que regitra los datos del bingo en un archivo XML
   * @param pBingo objeto de tipo Bingo
   * @return true si el registro fue exitoso, false en caso contrario
   */
  @Override
  public boolean registrarBingo(Bingo pBingo) {
    File file = new File("Bingos.xml");
    try {
      String etiquetaXML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>"
          + "\n<partidasJugadas></partidasJugadas>";
      if (!file.exists()) {
        file.createNewFile();
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(etiquetaXML);
        bw.close();
      }

      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document doc = db.parse(file);
      doc.getDocumentElement().normalize();
      // crear el elemento nodo de cada objeto
      Element nodoPartida = doc.createElement("partida");
      Element nodoTipo = doc.createElement("tipo");
      Element nodoNumerosCantados;
      nodoNumerosCantados = doc.createElement("numerosCantados");
      Element nodoGanadores = doc.createElement("ganadores");
      Element nodoFecha = doc.createElement("fecha");
      Element nodoHora = doc.createElement("hora");

      String numerosCantados = "";
      for (int i = 0; i < pBingo.getNumeroJugado().size(); i++) {
        if (i == pBingo.getNumeroJugado().size() - 1) {
          numerosCantados += pBingo.getNumeroJugado().get(i);
        } else {
          numerosCantados += pBingo.getNumeroJugado().get(i) + ",";
        }
      }
      String ganadores = "";
      for (int i = 0; i < pBingo.getJugadoresGadores().size(); i++) {
        if (i == pBingo.getJugadoresGadores().size() - 1) {
          ganadores += pBingo.getJugadoresGadores().get(i).getCedula();
        } else {
          ganadores += pBingo.getJugadoresGadores().get(i).getCedula() + ",";
        }
      }
      String fecha = pBingo.getFecha();
      String hora = pBingo.getHora();
      String tipo = pBingo.getTipoJuego();

      // String numeros = Integer.toString(pBingo.getNumerosCantados());
      nodoTipo.setTextContent(tipo);
      nodoNumerosCantados.setTextContent(numerosCantados);
      nodoGanadores.setTextContent(ganadores);
      nodoFecha.setTextContent(fecha);
      nodoHora.setTextContent(hora);

      nodoPartida.appendChild(nodoTipo);
      nodoPartida.appendChild(nodoNumerosCantados);
      nodoPartida.appendChild(nodoGanadores);
      nodoPartida.appendChild(nodoFecha);
      nodoPartida.appendChild(nodoHora);
      doc.getChildNodes().item(0).appendChild(nodoPartida);

      TransformerFactory factory = TransformerFactory.newInstance();
      Transformer transformer;
      transformer = factory.newTransformer();
      Source source = new DOMSource(doc);
      FileWriter fw = new FileWriter(file);
      PrintWriter pw = new PrintWriter(fw);
      StreamResult result = new StreamResult(pw);
      transformer.transform(source, result);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  /*
   * Funcion que lee los datos del bingo de un archivo XML
   * @return ArrayList con los numeros cantados del bingo
   */
  @Override
  public ArrayList<Integer> numerosCantados() {
    try {
      ArrayList<Integer> listaNumerosCantados = new ArrayList<Integer>();
      File file = new File("Bingos.xml");
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document doc = db.parse(file);
      doc.getDocumentElement().normalize();
      NodeList nodeList = doc.getElementsByTagName("partida");
      for (int itr = 0; itr < nodeList.getLength(); itr++) {
        Node node = nodeList.item(itr);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
          String numerosCantados = doc.getElementsByTagName("numerosCantados").item(itr).getTextContent();
          ArrayList<Integer> listaNumeros = eliminarComas(numerosCantados);
          for (int i = 0; i < listaNumeros.size(); i++) {
            listaNumerosCantados.add(listaNumeros.get(i));
          }
        }
      }
      return listaNumerosCantados;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /*
   * Funcion que lee los datos del bingo de un archivo XML
   * @return ArrayList con las fechas de los bingos
   */
  @Override
  public ArrayList<String> listaFechasDeJuegos() {
    try {
      ArrayList<String> listaFechas = new ArrayList<String>();
      File file = new File("Bingos.xml");
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document doc = db.parse(file);
      doc.getDocumentElement().normalize();
      NodeList nodeList = doc.getElementsByTagName("partida");
      for (int itr = 0; itr < nodeList.getLength(); itr++) {
        Node node = nodeList.item(itr);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
          String fecha = doc.getElementsByTagName("fecha").item(itr).getTextContent();
          if (fecha.equals("")) {
          } else {
            listaFechas.add(fecha);
          }
        }
      }
      return listaFechas;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /*
   * Funcion que lee los datos del bingo de un archivo XML
   * @return ArrayList con los tipos de juegos de los bingos
   */
  @Override
  public ArrayList<String> listaTiposDeJuegos() {
    try {
      ArrayList<String> listaTipos = new ArrayList<String>();
      File file = new File("Bingos.xml");
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document doc = db.parse(file);
      doc.getDocumentElement().normalize();
      NodeList nodeList = doc.getElementsByTagName("partida");
      for (int itr = 0; itr < nodeList.getLength(); itr++) {
        Node node = nodeList.item(itr);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
          String tipo = doc.getElementsByTagName("tipo").item(itr).getTextContent();
          if (tipo != null || tipo != "") {
            listaTipos.add(tipo);
          }
        }
      }
      return listaTipos;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /*
   * Funcion que lee los datos del bingo de un archivo XML
   * @return ArrayList con los ganadores de los bingos
   */
  @Override
  public ArrayList<String> listaJugadoresGanadores() {
    try {
      ArrayList<String> listaGanadores = new ArrayList<String>();
      File file = new File("Bingos.xml");
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document doc = db.parse(file);
      doc.getDocumentElement().normalize();
      NodeList nodeList = doc.getElementsByTagName("partida");
      for (int itr = 0; itr < nodeList.getLength(); itr++) {
        Node node = nodeList.item(itr);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
          String ganadores = doc.getElementsByTagName("ganadores").item(itr).getTextContent();
          if (ganadores != "") {
            listaGanadores.add(ganadores);
          }
        }
      }
      return listaGanadores;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
  /*
   * Elimina las comas de una cadena de texto
   * @param cadena cadena de texto
   * @return ArrayList con los numeros de la cadena sin las comas
   */
  private ArrayList<Integer> eliminarComas(String cadena) {
    ArrayList<Integer> lista = new ArrayList<Integer>();
    StringTokenizer st = new StringTokenizer(cadena, ",");
    while (st.hasMoreTokens()) {
      int numero = Integer.parseInt(st.nextToken());
      lista.add(numero);
    }
    return lista;
  }
}
