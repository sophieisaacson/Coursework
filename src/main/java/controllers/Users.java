package controllers;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import server.Main;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Path("users/")
@Consumes(MediaType.MULTIPART_FORM_DATA)
@Produces(MediaType.APPLICATION_JSON)

public class Users{
    @GET
    @Path("list")
    public String UsersList() {
        System.out.println("Invoked Users.UsersList()");
        JSONArray response = new JSONArray();
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT UserID, UserName FROM Users");
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
    @GET
    @Path("get/{Username}")
    public String GetUser(@PathParam("Username")String Username) {
        System.out.println("Invoked Users.GetUser() with Username " + Username);
        JSONArray response = new JSONArray();
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT Password FROM Users WHERE Username = ?");
            ps.setString(1, Username);
            ResultSet results = ps.executeQuery();
            JSONObject row = new JSONObject();
            if (results.next()==true) {
                row.put("Username", Username);
                row.put("Password", results.getString(1));
                response.add(row);
            }
            return response.toString();
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to get item, please see server console for more information.\"}";
        }
    }
    @POST
    @Path("add")
    public String UsersAdd(@FormDataParam("Username")String Username, @FormDataParam("Password")String Password, @FormDataParam("Email")String Email) {
        System.out.println("Invoked Users.UsersAdd()");
        try {
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Users (Username, Password, Email) VALUES (?,?,?)");
            ps.setString(1, Username);
            ps.setString(2, Password);
            ps.setString(3, Email);
            ps.execute();
            return "{\"OK\": \"Added User.\"}";
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to create new item, please see server control for more information.\"}";
        }
    }
    @POST
    @Path("update")
    public String UsersUpdate(@FormDataParam("Username") String Username , @FormDataParam("Password")String Password) {
        try {
            System.out.println("Invoked Users.UsersUpdate/update Username=" + Username);
            PreparedStatement ps = Main.db.prepareStatement("UPDATE Users SET Password = ? WHERE Username = ?");
            ps.setString(1, Username);
            ps.setString(2, Password);
            ps.execute();
            return "{\"OK\": \"Users updated\"}";
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to update item, please see server console for more information.\"}";
        }
    }
    @POST
    @Path("delete/{UserID}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String DeleteUser(@PathParam("UserID") Integer UserID) throws Exception {
        System.out.println("Invoked Users.DeleteUser()");
        if (UserID == null) {
            throw new Exception("UserID is missing in the HTTP request's URL.");
        }
        try {
            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Users WHERE UserID = ?");
            ps.setInt(1, UserID);
            ps.execute();
            return "{\"OK\": \"User deleted\"}";
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"Error\": \"Unable to delete item, please see server console for more information.\"}";
        }
    }


}
