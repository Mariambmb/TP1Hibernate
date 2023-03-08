package com.inti.servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import com.inti.model.Aeroport;
import com.inti.model.Client;
import com.inti.model.Passager;
import com.inti.model.Reservation;
import com.inti.model.Ville;
import com.inti.model.Vol;
import com.inti.util.HibernateUtil;

@WebServlet("/AeroportServlet")
public class AeroportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Logger log = LogManager.getLogger();
	private Session s;
    public AeroportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		s = HibernateUtil.getSessionFactory().openSession();
		log.debug("Connexion à la BDD et configuration d'hibernate depuis aeroport");
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/Aeroport.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try 
		{
			s.beginTransaction();
			log.info("Début enregistrement Aeroport");
			
			
			Aeroport A1 = new Aeroport(request.getParameter("nomA"));
		
			s.save(A1);

			
			s.getTransaction().commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error("Erreur enregistrement Aeroport");
			
			s.getTransaction().rollback();
		}
		doGet(request, response);
	}

}
