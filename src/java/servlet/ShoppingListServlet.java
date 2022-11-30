package servlet;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShoppingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();;
        String action = request.getParameter("action");
        String username = request.getParameter("username");
        if (action != null && action.equals("logout")) {
            session.invalidate();
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;
        }
        if (username != null) {
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            return;
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        ArrayList<String> items = null;
        switch(action) {
            case "register":
                String username = request.getParameter("username");
                if(username == null || username.equals("")){
                    getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
                    return;
                } else {
                request.setAttribute("message", "Enter a username");
                }
                break;
            case "add":
                
                String item = request.getParameter("item");
                if(item == null || item.equals("")) {
                    request.setAttribute("message", "Enter an item");
                }else{
                    items = (ArrayList<String>)session.getAttribute("items");
                    items.add(item);
                }
                break;
            case "delete":
                String itemDelete = request.getParameter("item");
                if(itemDelete == null || itemDelete.equals("")) {
                    request.setAttribute("message", "Select an item to delete");
                }else{
                    items = (ArrayList<String>)session.getAttribute("items");
                    items.remove(itemDelete);
                }
                break;
        }
        request.getSession().setAttribute("items", items);
        request.setAttribute("items", items);
        getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);

    }
}
