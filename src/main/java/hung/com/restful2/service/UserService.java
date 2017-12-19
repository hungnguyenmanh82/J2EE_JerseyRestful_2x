package hung.com.restful2.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import hung.com.restful2.dao.UserDao;
import hung.com.restful2.model.User;

@Path("/service")
public class UserService {
	
   UserDao userDao = new UserDao();
   private static final String SUCCESS_RESULT="<result>success</result>";
   private static final String FAILURE_RESULT="<result>failure</result>";


   @GET
   @Path("/users")
   @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
   public List<User> getUsers(){
      return userDao.getAllUsers();
   }

   @GET
   @Path("/users/{userid}")
   @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
   public User getUser(@PathParam("userid") int userid){
      return userDao.getUser(userid);
   }

   @POST
   @Path("/users")
   @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
   public String createUser(@FormParam("id") int id,
      @FormParam("name") String name,
      @FormParam("profession") String profession,
      @Context HttpServletResponse servletResponse) throws IOException{
      User user = new User(id, name, profession);
      int result = userDao.addUser(user);
      if(result == 1){
         return SUCCESS_RESULT;
      }
      return FAILURE_RESULT;
   }

   @PUT
   @Path("/users")
   @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })

   public String updateUser(@FormParam("id") int id,
      @FormParam("name") String name,
      @FormParam("profession") String profession,
      @Context HttpServletResponse servletResponse) throws IOException{
      User user = new User(id, name, profession);
      int result = userDao.updateUser(user);
      if(result == 1){
         return SUCCESS_RESULT;
      }
      return FAILURE_RESULT;
   }

   @DELETE
   @Path("/users/{userid}")
   @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
   public String deleteUser(@PathParam("userid") int userid){
      int result = userDao.deleteUser(userid);
      if(result == 1){
         return SUCCESS_RESULT;
      }
      return FAILURE_RESULT;
   }

   @OPTIONS
   @Path("/users")
   @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
   public String getSupportedOperations(){
      return "<operations>GET, PUT, POST, DELETE</operations>";
   }
}