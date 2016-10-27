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
    private String mailhost = "smtp.gmail.com";
    private String user;
    private String password;
    private Session session;
    Context co;
    String root = Environment.getExternalStorageDirectory().toString();

    static {
        Security.addProvider(new com.example.w7.robofission_labs.JSSEProvider());
    }

    public GMailSender(String user, String password, Context c) {
        this.user = user;
        this.password = password;
        this.co = c;

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", mailhost);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.quitwait", "false");

        session = Session.getDefaultInstance(props, this);
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user, password);
    }

    public synchronized void sendMail(String subject, String body, String sender, String recipients) throws Exception {
        try{
            MimeMessage message = new MimeMessage(session);
            //DataHandler handler = new DataHandler(new ByteArrayDataSource(body.getBytes(), "text/plain"));
            message.setSender(new InternetAddress(sender));
            message.setSubject(subject);
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(recipients));
            //message.setDataHandler(handler);
            BodyPart messageBodyPart = new MimeBodyPart();

            Multipart multipart = new MimeMultipart();


            //messageBodyPart = new MimeBodyPart();
            String filesource = "file://" + root + "/Robofission/SampleFile.txt";
            String fileName = "sampleimage.jpg";
            File file =new File(filesource);
            //System.out.println("message sent");
            DataSource source = new FileDataSource("file://" + root + "/Robofission/sampleimage.jpg");
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(fileName);
            multipart.addBodyPart(messageBodyPart);


            message.setContent(multipart);
            Transport.send(message);

        }catch(Exception e){
           // Toast.makeText(co ,"Mail not Send", Toast.LENGTH_SHORT).show();
            System.out.println("message not sent");
            throw new RuntimeException(e);


        }
    }

    public class ByteArrayDataSource implements DataSource {
        private byte[] data;
        private String type;

        public ByteArrayDataSource(byte[] data, String type) {
            super();
            this.data = data;
            this.type = type;
        }

        public ByteArrayDataSource(byte[] data) {
            super();
            this.data = data;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getContentType() {
            if (type == null)
                return "application/octet-stream";
            else
                return type;
        }

        public InputStream getInputStream() throws IOException {
            return new ByteArrayInputStream(data);
        }

        public String getName() {
            return "ByteArrayDataSource";
        }
        public OutputStream getOutputStream() throws IOException {
            throw new IOException("Not Supported");
        }
    }
}

