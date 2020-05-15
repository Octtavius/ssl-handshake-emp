

import java.io.IOException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class SimpleServlet extends HttpServlet{

	Logger log = Logger.getLogger(SimpleServlet.class.getName());
	   private static final long serialVersionUID = 1L;
	   
	   
	   @Override
	   protected void doGet(HttpServletRequest request, HttpServletResponse resp) 
	         throws ServletException, IOException {
		   
		   Object certs[] = (Object[]) request.getAttribute("javax.servlet.request.X509Certificate");
		    if (certs != null ) {
		    	List<X509Certificate> mylist = new ArrayList<X509Certificate>(); 
			    for(Object cert: certs) {
				boolean temp = (cert instanceof X509Certificate);
				mylist.add((X509Certificate)cert);
				X509Certificate certOb = (X509Certificate)cert;
				log.log(Level.INFO, "cert public id: " + certOb.getPublicKey().toString());
				
				log.log(Level.INFO, "cert sigalgname: " + certOb.getPublicKey().hashCode());
				log.log(Level.INFO, "cert sigalgname: " + certOb.getSigAlgName());
				try {
				    log.log(Level.INFO, "cert subj alt name: " + certOb.getSubjectAlternativeNames());
				} catch (CertificateParsingException e) {
				    // TODO Auto-generated catch block
				    e.printStackTrace();
				}
			      System.out.printf("DEBUG: Cert[%s] = %s%n", cert.getClass().getName(), cert);
		    }
		    }

		   
	      resp.setContentType("text/plain");
	      resp.getWriter().write("Hello World! Maven Web Project Example.");
	   }
}
