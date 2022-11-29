/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.DAOUSUARIO;
import Model.usuario;

/**
 *
 * @author anibal
 */
@WebServlet(name = "srvUsuario", urlPatterns = {"/srvUsuario"})
public class srvUsuario extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");

        String accion = request.getParameter("accion");
        try {
            if (accion != null) {
                switch (accion) {
                    case "verificar":
                        verificar(request, response);
                        break;
                    case "cerrar":
                        cerrarsession(request, response);

                    default:
                        response.sendRedirect("identificar.jsp");
                }

            } else {
                response.sendRedirect("identificar.jsp");
            }
        } catch (Exception e) {
            try {
                this.getServletConfig().getServletContext().getRequestDispatcher("/mensaje").forward(request, response);
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }

    }

    private void cerrarsession(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession sesion = request.getSession();
        sesion.setAttribute("usuario", null);
        sesion.invalidate();
        response.sendRedirect("identificar.jsp");
    }

    private void verificar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session;
        DAOUSUARIO dao;
        usuario usuario;
        usuario = this.obtenerUsuario(request);
        dao = new DAOUSUARIO();
        usuario = dao.identificar(usuario);
        if (usuario != null && usuario.getCargo().getNombreCargo().equals("ADMINISTRADOR")) {
            session = request.getSession();
            session.setAttribute("usuario", usuario);
            request.setAttribute("msje", "Bienvenido ");
            this.getServletConfig().getServletContext().getRequestDispatcher("/vistas/index.jsp").forward(request, response);

        } else if (usuario != null && usuario.getCargo().getNombreCargo().equals("PLANILLERO")) {
            session = request.getSession();
            session.setAttribute("vendedor", usuario);
            this.getServletConfig().getServletContext().getRequestDispatcher("/vistas/formPlanillero.jsp").forward(request, response);

        } else if (usuario != null && usuario.getCargo().getNombreCargo().equals("RH")) {
            session = request.getSession();
            session.setAttribute("vendedor", usuario);
            this.getServletConfig().getServletContext().getRequestDispatcher("/vistas/formRH.jsp").forward(request, response);
        } else {
            request.setAttribute("msje", "Usuario o contraseña incorrectos");
            request.getRequestDispatcher("identificar.jsp").forward(request, response);
        }

    }

    private usuario obtenerUsuario(HttpServletRequest request) {
        usuario u = new usuario();
        u.setNombreususario(request.getParameter("txtUsu"));
        u.setClave(request.getParameter("txtPass"));
        return u;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
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
