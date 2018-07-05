package com.crm.TestBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.util.WebEventListener;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	
	public TestBase()
	{
		 prop=new Properties();
		try {
			FileInputStream ip=new FileInputStream("C:\\Users\\nikil kaarthi\\workspace\\FreeCRM\\src\\main\\java\\com\\crm\\config\\config.properties");
			try {
				prop.load(ip);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void Initalize()
	{
		String browsername=prop.getProperty("browser");
		if(browsername.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",prop.getProperty("chromedriver"));
			driver=new ChromeDriver();
			
		}
		EventFiringWebDriver edriver=new EventFiringWebDriver(driver);
		WebEventListener wdriver=new WebEventListener();
		edriver.register(wdriver);
		driver=edriver;
		driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));
		
		
	}
	public  static boolean sendMail_first(
    		final String userName,
    		final String passWord,
    		String host,
    		String port,
    		String starttls,
    		String auth,
    		boolean debug,
    		String socketFactoryClass,
    		String fallback,
    		String[] to,
    		String[] cc,
    		String[] bcc,
    		String subject,
    		String text,
    		String attachmentPath,
    		String attachmentName){
    	    Properties props = new Properties();
            props.put("mail.smtp.starttls.enable", starttls);
            props.put("mail.smtp.auth",auth);
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", port);

    try
    {
    	    Session session = Session.getInstance(props,
    	    new javax.mail.Authenticator() {
    	    protected PasswordAuthentication getPasswordAuthentication() {
    	    return new PasswordAuthentication(userName, passWord);
    	     }
   });

        MimeMessage msg = new MimeMessage(session);
        msg.setText(text);
        msg.setSubject(subject);
        //attachment start
        // create the message part 
        Multipart multipart = new MimeMultipart();
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        DataSource source = 
        new FileDataSource(attachmentPath);
        messageBodyPart.setDataHandler(
        new DataHandler(source));
        messageBodyPart.setFileName(attachmentName);
        multipart.addBodyPart(messageBodyPart); 
        // attachment ends
        // Put parts in message
        msg.setContent(multipart);
        msg.setFrom(new InternetAddress(userName));
       for(int i=0;i<to.length;i++){
    	   msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
                    }
       for(int i=0;i<cc.length;i++){
        msg.addRecipient(Message.RecipientType.CC, new InternetAddress(cc[i]));
                    }
       for(int i=0;i<bcc.length;i++){
        msg.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc[i]));
                    }
        msg.saveChanges();
       Transport transport = session.getTransport("smtp");
       transport.connect(host,userName,passWord);
       transport.sendMessage(msg,msg.getAllRecipients());
       transport.close();
       return true;
    }
    catch (Exception mex)
    {
        mex.printStackTrace();
        return false;

    }

    }
}
