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
 *
 * @author Andy Porras
 */
public class BingoDAOXML implements BingoDAO {

  @Override
  public boolean registrarBingo(Bingo pBingo) {
    File file = new File("Bingos.xml");
    try {
      String etiquetaXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "\n<Usuarios><\\Usuarios>";
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

      NodeList partida = doc.getElementsByTagName("partida");

      for (int i = 0; i < partida.getLength(); i++) {
        Node node = partida.item(i);
        Element elemento = (Element) node;
        //String tipo = elemento.getElementsByTagName("tipo").item(0).getTextContent();
        // if (pBingo.getIdentificador().equals(identificador)) {
        //   return false;
        // }
      }
      Element nodoPartida = doc.createElement("partida");
      Element nodoTipo = doc.createElement("tipo");
      Element nodoNumerosCantados = doc.createElement("números Cantados");
      Element nodoGanadores = doc.createElement("ganadores");
      Element nodoFecha = doc.createElement("fecha");
      Element nodoHora = doc.createElement("hora");
      // String numeros = Integer.toString(pBingo.getNumerosCantados());
      // nodoTipo.setTextContent(pBingo.getTipo());
      // nodoNumerosCantados.setTextContent(numeros);
      // nodoGanadores.setTextContent(pBingo.getGanadores());
      // nodoFecha.setTextContent(pBingo.getFecha());
      // nodoHora.setTextContent(pBingo.getHora());
      
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

  @Override
  public ArrayList<Bingo> cargarBingos() {
    try {
      ArrayList<Bingo> bingos = new ArrayList<Bingo>();
      File file = new File("Bingos.xml");
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document doc = db.parse(file);
      doc.getDocumentElement().normalize();
      NodeList nodeList = doc.getElementsByTagName("partida");
      for (int itr = 0; itr < nodeList.getLength(); itr++) {
        Node node = nodeList.item(itr);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
          // Element eElement = (Element) node;
          String tipo = doc.getElementsByTagName("tipo").item(itr).getTextContent();
          String numerosCantados = doc.getElementsByTagName("números Cantados").item(itr).getTextContent();
          String ganadores = doc.getElementsByTagName("ganadores").item(itr).getTextContent();
          String fecha = doc.getElementsByTagName("fecha").item(itr).getTextContent();
          String hora = doc.getElementsByTagName("hora").item(itr).getTextContent();
          
          bingos.add(new Bingo(tipo, numerosCantados, ganadores, fecha, hora));
          // String nombre = eElement.getElementsByTagName("Nombre").item(0).getTextContent();
          // String contrasena = eElement.getElementsByTagName("Contraseña").item(0).getTextContent();

          // usuarios.add(new Bingo(nombre, contrasena));
        }
      }
      return bingos;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

}
