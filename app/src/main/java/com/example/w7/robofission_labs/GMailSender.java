package com.example.w7.robofission_labs;

/**
 * Created by ashik619 on 26-10-2016.
 */

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
        import javax.mail.Session;
        import javax.mail.Transport;
        import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
        import java.io.InputStream;
        import java.io.OutputStream;
        import java.security.Security;
        import java.util.Properties;

public class GMailSender extends javax.mail.Authenticator {

    String root = Environment.getExternalStorageDirectory().toString();
    private String to="order@robofissionlabs.in";//change accordingly
    private  String attachmentPath =   root + "/Robofission/";

    public GMailSender() {


        //Get the session object

    }



    public void sendMail(String attachmentName) throws Exception {
        try{
            attachmentPath = attachmentPath + attachmentName;
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication("robofissionlabs1@gmail.com","*****");//change accordingly
                        }

                    });

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("robofissionlabs1@gmail.com"));//change accordingly
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject("Hello");
            //message.setText("Testing");
            Multipart multipart = new MimeMultipart();
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            //File att = new File(new File(attachmentPath), attachmentName);
           // messageBodyPart.attachFile(att);
            //messageBodyPart.attachFile("file://" + root + "/Robofission/SampleFile.txt");
            //messageBodyPart.setFileName("SampleFile.txt");
           // messageBodyPart.setText("fuck");
            DataSource source = new FileDataSource(attachmentPath);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(attachmentName);
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

            //send message
            Transport.send(message);

            System.out.println("message sent successfully");

        }catch(Exception e){
            System.out.println("message not sent");
            throw new RuntimeException(e);


        }
    }


}

