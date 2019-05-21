package es.jdl.entrysign.services.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.googlecode.objectify.ObjectifyService;
import es.jdl.entrysign.domain.EntrySign;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

public class DoSignRESTService extends HttpServlet {

    Gson gson = new GsonBuilder()
            .registerTypeAdapter(Date.class, new DateLongFormatTypeAdapter())
            .create();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntrySign ent = gson.fromJson(request.getReader(), EntrySign.class);
        ent.setUser(request.getUserPrincipal().getName());
        ent.setSignTime(new Date());
        ObjectifyService.ofy().save().entity(ent).now();
        RESTUtils.writeJSONResponse(response, ent);
    }

    public class DateLongFormatTypeAdapter extends TypeAdapter<Date> {

        @Override
        public void write(JsonWriter out, Date value) throws IOException {
            out.value(value.getTime());
        }

        @Override
        public Date read(JsonReader in) throws IOException {
            return new Date(in.nextLong());
        }

    }

}
