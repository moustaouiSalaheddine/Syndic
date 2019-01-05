/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.ImmeubleData;
import beans.Quartier;
import beans.Ville;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.QuartierService;
import service.VilleService;

/**
 *
 * @author Sinponzakra
 */
@WebServlet(name = "QuartierController", urlPatterns = {"/QuartierController"})
public class QuartierController extends HttpServlet {

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
        String ville_id;
        String id;
        VilleService vs = new VilleService();
        QuartierService qs = new QuartierService();
        String op = request.getParameter("op");

        switch (op) {
            case "load":
                response.getWriter().write(new Gson().toJson(qs.findAll()));
                break;
            case "add":
                nom = request.getParameter("nom");
                ville_id = request.getParameter("ville");
                qs.create(new Quartier(nom, vs.findById(Integer.parseInt(ville_id))));

                response.getWriter().write(new Gson().toJson(qs.findAll()));
                break;
            case "delete":
                qs.delete(qs.findById(Integer.parseInt(request.getParameter("id"))));
                response.getWriter().write(new Gson().toJson(qs.findAll()));
                break;
            case "update":
                id = request.getParameter("id"); 
                nom = request.getParameter("nom");
                ville_id = request.getParameter("ville");
                Quartier q = qs.findById(Integer.parseInt(id));
                q.setNom(nom);
                q.setVilleId(vs.findById(Integer.parseInt(ville_id)));
                qs.update(q);
                response.getWriter().write(new Gson().toJson(qs.findAll()));
                break;
            case "loadByVille":
                ville_id = request.getParameter("ville");
                response.getWriter().write(new Gson().toJson(qs.findAllByVille(vs.findById(Integer.parseInt(ville_id)))));
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
