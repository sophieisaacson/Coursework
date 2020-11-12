package controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import server.Main;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*@Path("notes/")

/*public class Notes {
    @GET
    @Path("list")
     public String NotesList() {
        System.out.println("Invoked Notes.NotesList()");
        JSONArray response = new JSONArray();
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT UsersNotes.UserNote FROM Notes JOIN Users ON Users.UserID = UserNotes.UserID");
            ResultSet results = ps.executeQuery();
            while (results.next()==true) {
                JSONObject row = new JSONObject();
                row.put("UserID", results.getInt(1));
                row.put("Username", results.getString(2));
                response.add(row);
            }
            return response.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to list items. Please see server console for more information.\"}";
        }
    }
}*/

