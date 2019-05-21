package es.jdl.entrysign.services.rest;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;
import es.jdl.entrysign.domain.EntrySign;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListSignService extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<EntrySign> ret = null;
        if (request.isUserInRole("admin")) {
            ret = ObjectifyService.ofy().load().type(EntrySign.class).list();
        } else {
            ret = ObjectifyService.ofy().load().type(EntrySign.class).filter("user", request.getUserPrincipal().getName()).list();
        }
        RESTUtils.writeJSONResponse(response, ret);
    }
}
