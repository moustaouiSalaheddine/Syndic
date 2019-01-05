/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.AppartementHabitant;
import beans.AppartementHabitantPK;
import beans.Habitant;
import beans.Revenue;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.AppartementHabitantService;
import service.AppartementService;
import service.HabitantService;
import service.RevenueService;

/**
 *
 * @author Sinponzakra
 */
@WebServlet(name = "LocationController", urlPatterns = {"/LocationController"})
public class LocationController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String nom;
        String prenom;
        String cin;
        String telephone;
        String email;
        String password;
        String type;
        String dateDebut;
        String dateFin;
        String appartement;
        String id;
        RevenueService rs = new RevenueService();
        AppartementHabitantService ahs = new AppartementHabitantService();
        AppartementService as = new AppartementService();
        HabitantService hs = new HabitantService();
        String op = request.getParameter("op");

        switch (op) {
            case "load":
                response.getWriter().write(new Gson().toJson(ahs.findAll()));
                break;
            case "load2":
                response.getWriter().write(new Gson().toJson(hs.findAllSyndic()));
                break;    
            case "add":
                nom = request.getParameter("nom");
                prenom = request.getParameter("prenom");
                cin = request.getParameter("cin");
                telephone = request.getParameter("telephone");
                email = request.getParameter("email");
                password = request.getParameter("password");
                type = request.getParameter("type");
                dateDebut = request.getParameter("dateDebut");
                dateFin = request.getParameter("dateFin");
                appartement = request.getParameter("appartement");

                // insert Habitant
                Habitant h = hs.created(new Habitant(nom, prenom, cin, telephone, email, password, type));
                if (!dateFin.equals("")) {
                    ahs.create(new AppartementHabitant(new AppartementHabitantPK(new Date(dateDebut), Integer.parseInt(appartement), h.getId()), as.findById(Integer.parseInt(appartement)), h, new Date(dateFin)));
                } else {
                    ahs.create(new AppartementHabitant(new AppartementHabitantPK(new Date(dateDebut), Integer.parseInt(appartement), h.getId()), as.findById(Integer.parseInt(appartement)), h, null));
                }
                response.getWriter().write(new Gson().toJson(ahs.findAll()));
                break;
            case "add2":
                nom = request.getParameter("nom");
                prenom = request.getParameter("prenom");
                cin = request.getParameter("cin");
                telephone = request.getParameter("telephone");
                email = request.getParameter("email");
                password = request.getParameter("password");
                type = request.getParameter("type");

                // insert Habitant
                hs.create(new Habitant(nom, prenom, cin, telephone, email, password, type));
                
                response.getWriter().write(new Gson().toJson(hs.findAllSyndic()));
                break;
            case "delete":
                ahs.delete(ahs.findByHabitant(hs.findById(Integer.parseInt(request.getParameter("id")))));
                hs.delete(hs.findById(Integer.parseInt(request.getParameter("id"))));
                response.getWriter().write(new Gson().toJson(ahs.findAll()));
                break;
            case "update":
                id = request.getParameter("id");
                nom = request.getParameter("nom");
                prenom = request.getParameter("prenom");
                cin = request.getParameter("cin");
                telephone = request.getParameter("telephone");
                email = request.getParameter("email");
                password = request.getParameter("password");
                type = request.getParameter("type");
                dateDebut = request.getParameter("dateDebut");
                dateFin = request.getParameter("dateFin");
                appartement = request.getParameter("appartement");

                Habitant h_up = hs.findById(Integer.parseInt(id));
                h_up.setNom(nom);
                h_up.setPrenom(prenom);
                h_up.setCin(cin);
                h_up.setTelephone(telephone);
                h_up.setEmail(email);
                h_up.setPassword(password);
                h_up.setType(type);
                hs.update(h_up);
                
                ahs.delete(ahs.findByHabitant(h_up));
                if (!dateFin.equals("")) {
                    ahs.create(new AppartementHabitant(new AppartementHabitantPK(new Date(dateDebut), Integer.parseInt(appartement), h_up.getId()), as.findById(Integer.parseInt(appartement)), h_up, new Date(dateFin)));
                } else {
                    ahs.create(new AppartementHabitant(new AppartementHabitantPK(new Date(dateDebut), Integer.parseInt(appartement), h_up.getId()), as.findById(Integer.parseInt(appartement)), h_up, null));
                }
                response.getWriter().write(new Gson().toJson(ahs.findAll()));
                break;
            case "loadById":
                System.out.println("ID =======> "+Integer.parseInt(request.getParameter("id")));
                Habitant hd = hs.findById(Integer.parseInt(request.getParameter("id")));
                response.getWriter().write(new Gson().toJson(ahs.findByHabitant(hd)));
                break;
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
