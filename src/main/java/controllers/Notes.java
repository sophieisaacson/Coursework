package controllers;

import org.glassfish.jersey.media.multipart.FormDataParam;
import server.Main;

import javax.ws.rs.CookieParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Path("usersnotes/")

public class Notes {
    @POST
    @Path("add")
    public String FastPlanner(@CookieParam("Tokens") String Token, @FormDataParam("Note")String Note) {
        System.out.println("Invoked Users.FastPlanner()");
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT UserID FROM Users WHERE Token = ? INSERT INTO UsersNotes (UserID, Note) JOIN Users ON Users.UserID = UsersNotes.UserID VALUES (?, ?)");
            ps.setString(1, Note);
            ps.execute();
            return "{\"OK\": \"Added Note.\"}";
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to create new note, please see server control for more information.\"}";
        }
    }
}