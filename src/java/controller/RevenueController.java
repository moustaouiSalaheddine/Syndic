/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import service.AppartementService;
import service.ImmeubleService;
import service.RevenueService;

/**
 *
 * @author Sinponzakra
 */
@WebServlet(name = "RevenueController", urlPatterns = {"/RevenueController"})
public class RevenueController extends HttpServlet {

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
        String date;
        String montant;
        String annee;
        String immeuble_id;
        String appartement;
        String id;
        RevenueService rs = new RevenueService();
        AppartementService as = new AppartementService();
        ImmeubleService is = new ImmeubleService();
        String op = request.getParameter("op");

        switch (op) {
            case "load":
                response.getWriter().write(new Gson().toJson(rs.findAll()));
                break;
            case "add":
                date = request.getParameter("date");
                montant = request.getParameter("montant");
                appartement = request.getParameter("appartement");
                rs.create(new Revenue(new Date(date), Double.parseDouble(montant), as.findById(Integer.parseInt(appartement))));
                response.getWriter().write(new Gson().toJson(rs.findAll()));
                break;
            case "delete":
                rs.delete(rs.findById(Integer.parseInt(request.getParameter("id"))));
                response.getWriter().write(new Gson().toJson(rs.findAll()));
                break;
            case "update":
                id = request.getParameter("id"); 
                date = request.getParameter("date");
                montant = request.getParameter("montant");
                appartement = request.getParameter("appartement");
                Revenue r = rs.findById(Integer.parseInt(id));
                r.setDate(new Date(date));
                r.setMontant(Double.parseDouble(montant));
                r.setAppartement(as.findById(Integer.parseInt(appartement)));
                rs.update(r);
                response.getWriter().write(new Gson().toJson(rs.findAll()));
                break;
            case "revenue":
                annee = request.getParameter("annee");
                immeuble_id = request.getParameter("immeuble");
                response.getWriter().write(new Gson().toJson(rs.findByAnneeAndImmeuble(new Date(annee), is.findById(Integer.parseInt(immeuble_id)))));
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
