/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.Appartement;
import beans.Immeuble;
import beans.ImmeubleData;
import beans.Quartier;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.AppartementService;
import service.ImmeubleService;
import service.QuartierService;
import service.VilleService;

/**
 *
 * @author Sinponzakra
 */
@WebServlet(name = "ImmeubleController", urlPatterns = {"/ImmeubleController"})
public class ImmeubleController extends HttpServlet {

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
        String numero;
        String codepostal;
        String quartier_id;
        VilleService vs = new VilleService();
        ImmeubleService is = new ImmeubleService();
        QuartierService qs = new QuartierService();
        AppartementService as = new AppartementService();
        String op = request.getParameter("op");

        switch (op) {
            case "load":
                response.getWriter().write(new Gson().toJson(is.findAll()));
                break;
            case "add":
                nom = request.getParameter("nom");
                quartier_id = request.getParameter("quartier");
                numero = request.getParameter("numero");
                codepostal = request.getParameter("codepostal");
                
                
                //modify that later
                Immeuble i =  is.created(new Immeuble(Integer.parseInt(numero), nom, codepostal, qs.findById(Integer.parseInt(quartier_id)),null));
                List<ImmeubleData> data = new Gson().fromJson(request.getParameter("tab"), new TypeToken<List<ImmeubleData>>(){}.getType());
                System.out.println("mList =>" +data);
                data.forEach(e -> {
                    //System.out.println("Etage : "+e.getEtage());
                    e.getAppart().forEach(a -> {
                        System.out.println("heere =>" +a.toString().substring(8, a.toString().length() - 1));
                        String[] datas = a.toString().substring(8, a.toString().length() - 1).split("_");;
                        String appartement = datas[0];
                        String dimension = datas[1];
                        //System.out.println("Appartemnet => "+ appartement+" | dimension => "+dimension);
                        as.create(new Appartement(Integer.parseInt(appartement), Integer.parseInt(dimension), e.getEtage(), i));
                    });
                });
                response.getWriter().write(new Gson().toJson(is.findAll()));
                break;
            case "delete":
                List<Appartement> apps =  as.findAllByImmeuble(is.findById(Integer.parseInt(request.getParameter("id"))));
                apps.forEach(e -> {
                    as.delete(e);
                });
                is.delete(is.findById(Integer.parseInt(request.getParameter("id"))));
                response.getWriter().write(new Gson().toJson(is.findAll()));
                break;
            case "update":
                id = request.getParameter("id");
                nom = request.getParameter("nom");
                quartier_id = request.getParameter("quartier");
                numero = request.getParameter("numero");
                codepostal = request.getParameter("codepostal");
                
                Immeuble im = is.findById(Integer.parseInt(id));
                im.setNom(nom);
                im.setNumero(Integer.parseInt(numero));
                im.setQuarierId(qs.findById(Integer.parseInt(quartier_id)));
                im.setCodePostal(codepostal);
                
                is.update(im);
                
                List<ImmeubleData> dataa = new Gson().fromJson(request.getParameter("tab"), new TypeToken<List<ImmeubleData>>(){}.getType());
                System.out.println("mList =>" +dataa);
                
                as.findAllByImmeuble(im).forEach(a -> {
                    as.delete(a);
                });
                
                dataa.forEach(e -> {
                    //System.out.println("Etage : "+e.getEtage());
                    e.getAppart().forEach(a -> {
                        System.out.println("heere =>" +a.toString().substring(8, a.toString().length() - 1));
                        String[] datas = a.toString().substring(8, a.toString().length() - 1).split("_");;
                        String appartement = datas[0];
                        String dimension = datas[1];
                        //System.out.println("Appartemnet => "+ appartement+" | dimension => "+dimension);
                        as.create(new Appartement(Integer.parseInt(appartement), Integer.parseInt(dimension), e.getEtage(), im));
                    });
                });
                
                response.getWriter().write(new Gson().toJson(is.findAll()));
                
                break;
            case "loadAppartementByImmeuble":
                id = request.getParameter("id"); 
                response.getWriter().write(new Gson().toJson(as.findAllByImmeuble(is.findById(Integer.parseInt(id)))));
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
