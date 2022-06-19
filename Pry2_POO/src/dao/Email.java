package dao;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
/**
 * Funcion de envio de correos mediante java
 * 
 * @author KEVIN SALAZAR
 * @version 10.4
 */
public class Email {
  private static final String CORREO = "javamailcorreos@gmail.com";
  private static final String PSSW = "tzsaghkdzlnvyeou"; //NECESITA DE UN CODIGO DE VERIF DE GOOGLE, NO LA CONTRASEÑA DEL CORREO

  /**
   * 
   * @param pID el ID del carton
   * @param pNombre El nombre del jugador
   * @param pDireccion La direccion de correo del jugador
   * @param pMensaje El cuerpo del correo
   * @param pAsunto El asunto o titulo del correo
   * @throws MessagingException En caso de que no se envie el correo
   */
  public Email(String pID, String pNombre, String pDireccion, String pMensaje, String pAsunto) throws MessagingException {
    enviarEmail(pID, pNombre, pDireccion, pMensaje, pAsunto);  
  }

  // metodos accesores
  public static String getCORREO() {
    return CORREO;
  }

  public static String getPSSW() {
    return PSSW;
  }

  /**
   * Crea y envia un correo a una direccion de envío
   * 
   * @param toAddress Direccion de correo del destinatario
   * @param subject   Encabezado del correo
   * @param message   Cuerpo o mensaje del correo
   */
  public static void enviarEmail(String id, String nombre, String pDireccion, String pMensaje, String pAsunto) throws AddressException, MessagingException {
    try{
      // se obtiene el objeto Session. La configuración es para
      // una cuenta de gmail.
      Properties props = new Properties();
      props.put("mail.smtp.host", "smtp.gmail.com");
      props.setProperty("mail.smtp.starttls.enable", "true");
      props.setProperty("mail.smtp.port", "587");
      props.setProperty("mail.smtp.user", getCORREO());
      props.setProperty("mail.smtp.auth", "true");

      Session session = Session.getDefaultInstance(props, null);
      // session.setDebug(true);

      // Se compone la parte del texto
      BodyPart texto = new MimeBodyPart();
      texto.setText(pMensaje);

      // Se compone el adjunto con la imagen
      BodyPart adjunto = new MimeBodyPart();
      adjunto.setDataHandler(
          new DataHandler(new FileDataSource("Cartones\\"+id+".png")));
      adjunto.setFileName(id);
      // Una MultiParte para agrupar texto e imagen.
      MimeMultipart multiParte = new MimeMultipart();
      multiParte.addBodyPart(texto);
      multiParte.addBodyPart(adjunto);

      // Se compone el correo, dando to, from, subject y el
      // contenido.
      MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress(getCORREO()));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(pDireccion));
      message.setSubject(pAsunto);
      message.setContent(multiParte);

      // Se envia el correo.
      Transport t = session.getTransport("smtp");
      t.connect(getCORREO(), getPSSW());
      t.sendMessage(message, message.getAllRecipients());
      t.close();
    }
    catch (Exception e){
      e.printStackTrace();
    }
  }
}

