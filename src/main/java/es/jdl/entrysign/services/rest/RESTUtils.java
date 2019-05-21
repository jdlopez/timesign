package es.jdl.entrysign.services.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RESTUtils {
    private static Gson gson = new GsonBuilder()
            .setDateFormat("dd/MM/yyyy HH:mm:ss").create(); // .registerTypeAdapter(Ref.class, new GsonRefSerializer())

    /** escribe el objeto pasado en formato JSON como respuesta HTTP */
    public static void writeJSONResponse(HttpServletResponse resp, Object respObj) throws IOException {
        if (respObj == null)
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Result is NULL");
        else {
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            writer.print(gson.toJson(respObj));
            writer.flush();
        }
    }

    /**
     * Gestion basica de errores para servicios REST
     * @param resp
     * @param cause
     * @throws IOException
     */
    public static void returnException(HttpServletResponse resp, Throwable cause) throws IOException {
        resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                "{ \"error\": \"" + (
                        cause.getMessage() + cause.getCause()==null?"":
                                " caused by: " + cause.getCause().getMessage()) +
                        "\"}");
    }
}
