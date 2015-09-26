package org.freekode.wowauction.updater.servlets;

import org.freekode.wowauction.updater.beans.interfaces.SnapshotBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@WebServlet("/snapshot")
public class SnapshotServlet extends HttpServlet {
    @Autowired
    private SnapshotBean snapshotBean;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("accounts", snapshotBean.findAll());

        req.getRequestDispatcher("/snapshot.jsp").forward(req, resp);
    }
}
