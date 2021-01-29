/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelos.Utilidades;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DAW-B
 */
@WebServlet("/poblaciones")
public class servletPoblaciones extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ArrayList<String> provincias;
    private String rutaFicheros;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    public void init (ServletConfig config) 
            throws ServletException {
        
        provincias = new ArrayList<String>();
        provincias.add("Abacete");
        provincias.add("Ciudad Real");
        provincias.add("Cuanca");
        provincias.add("Guadalajara");
        provincias.add("Toledo");
        
        rutaFicheros =config.getServletContext().getRealPath("").concat(File.separator).concat("ficheros");
        
    }
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet servletPoblaciones</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet servletPoblaciones at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
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
        ArrayList<String> pueblos = Utilidades.getPoblaciones(rutaFicheros.concat(File.separator).concat("pueblosCLM/Albacete.txt"));
        request.setAttribute("provincias", provincias);
        request.setAttribute("provincia","Albacete");
        request.setAttribute("pueblos",pueblos);
        request.getRequestDispatcher("pueblos.jsp").forward(request, response);
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
        String archivoSeleccionado = request.getParameter("provincia").concat(".txt");
        ArrayList<String> pueblos = Utilidades.getPoblaciones(rutaFicheros.concat(File.separator).concat("pueblosCLM/Albacete.txt"));
        request.setAttribute("provincias", provincias);
        request.setAttribute("provincia",request.getParameter("provincia"));
        request.setAttribute("pueblos",pueblos);
        request.getRequestDispatcher("pueblos.jsp").forward(request, response);
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
