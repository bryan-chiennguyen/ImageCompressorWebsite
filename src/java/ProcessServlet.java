/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class ProcessServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private int numLines;
    private final static int DEFAULT_LEN = 100;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        String inputText = request.getParameter("compress-input");
        Scanner scan = new Scanner(inputText);
        RLEconverter converter = new RLEconverter();
        String line = null;
        String[] decompressed = new String[DEFAULT_LEN];
        numLines = 0;
        while (scan.hasNext()) {
            line = scan.next();
            if (line != null && line.length() > 0)
                decompressed[numLines] = line;
            numLines++;
        }
        String[] needCompress = new String[numLines];
        for (int i = 0; i < numLines; i++) {
            needCompress[i] = decompressed[i];
        }
        scan.close();
        //compressed = new String[numLines+1];
        if (decompressed != null){
        String[] compressed = converter.compressLines(needCompress);
        if (compressed == null) {
            request.setAttribute("finalVal", "Empty File");
        }
        else {
            String ans = converter.getCompressedFileStr(compressed, converter.fileChars);
            request.setAttribute("finalVal", ans); 
            }
        }
        
        request.getRequestDispatcher("results.jsp").forward(request, response);
        //response.sendRedirect("results.jsp");
        //try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            /*out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProcessServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProcessServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }*/
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
