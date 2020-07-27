package controller;

import model.Login;
import model.LoginDAO;
import model.Utente;
import model.UtenteDAO;

import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

@WebFilter("/*")
public class CookieLoginFilter extends HttpFilter {

    private static final long serialVersionUID = 1L;
    private final UtenteDAO utenteDAO = new UtenteDAO();
    private final LoginDAO loginDAO = new LoginDAO();

    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        String path = request.getRequestURI();
        if (!path.contains("/servlet_login") && !path.contains("/servlet_logout")) {
            HttpSession session = request.getSession();
            Utente utente = (Utente) session.getAttribute("utente");

            if (utente == null) {
                Cookie cookies[] = request.getCookies();
                // cookie con nome 'login' o null se non esiste
                Cookie loginCookie = cookies == null ? null : Arrays.stream(cookies).filter(c -> c.getName().equals("login")).findAny().orElse(null);

                if (loginCookie != null) {
                    String[] sp = loginCookie.getValue().split("_");
                    String id = sp[0];
                    String token = sp.length > 1 ? sp[1] : null;

                    Login login = loginDAO.doRetrieveById(id);
                    if (login != null && login.getToken().equals(token)) {
                        utente = utenteDAO.doRetrieveByUseId(login.getIdutente());
                        session.setAttribute("utente", utente);

                        // per maggiore sicurezza genera nuovo token
                        token = UUID.randomUUID().toString();
                        login.setToken(token);
                        loginDAO.doUpdate(login);
                        loginCookie.setValue(id + "_" + token);
                        loginCookie.setMaxAge(30 * 24 * 60 * 60); // 30 giorni
                        response.addCookie(loginCookie);
                    } else {
                        loginCookie.setMaxAge(0);
                        response.addCookie(loginCookie);
                        if (login != null) {
                            loginDAO.doDelete(id);
                        }
                    }
                }
            }
        }

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }

}
