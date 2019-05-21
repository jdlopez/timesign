package es.jdl.entrysign.services.rest;

import com.google.gson.Gson;
import com.googlecode.objectify.ObjectifyService;
import es.jdl.entrysign.domain.EntrySign;
import es.jdl.entrysign.domain.EntrySignType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class LastSignRESTService extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getUserPrincipal().getName();
        List<EntrySign> all = ObjectifyService.ofy().load().type(EntrySign.class)
                .filter("user", userName).list();
        Date today = new Date();
        EntrySign entry = null;
        if (all != null) {
            for (EntrySign e: all) {
                if (sameDay(e.getSignTime(), today)
                        && (entry == null
                        || entry.getSignTime().before(e.getSignTime()))
                )
                    entry = e;
            }
        }
        if (entry == null) {
            entry = new EntrySign();
            entry.setActualTime(new Date());
            entry.setType(EntrySignType.out);
            entry.setId(null);
            entry.setSignTime(null);
            entry.setUser(userName);
        }
        RESTUtils.writeJSONResponse(response, entry);
    }

    private static final long MILLIS_PER_DAY = 3600 * 24 * 1000L;

    private boolean sameDay(Date dateOne, Date dateTwo) {
        long julianDayNumber1 = dateOne.getTime() / MILLIS_PER_DAY;
        long julianDayNumber2 = dateTwo.getTime() / MILLIS_PER_DAY;

        return julianDayNumber1 == julianDayNumber2;
    }
}
