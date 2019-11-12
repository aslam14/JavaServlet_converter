package currency;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/converter")
public class Currency extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Currency() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().println("Username is " + request.getParameter("username"));
		//response.getWriter().println("Input currency is " + request.getParameter("inputCurrency"));
		//response.getWriter().println("Target currency is " + request.getParameter("targetCurrency"));
		
		double resultantValue = getConverterdValue(request.getParameter("targetCurrency"), Double.parseDouble(request.getParameter("inputCurrency")));
	
		
		try {
			Connection connection= DatabaseInitializer.GetConnection();
			
			PreparedStatement statement = connection.prepareStatement("Insert INTO currency_converter.currency (username, input, target, result) values (?,?,?,?)"); 
			statement.setString(1, request.getParameter("username"));
			statement.setDouble(2, Double.parseDouble(request.getParameter("inputCurrency")));
			statement.setString(3,  request.getParameter("targetCurrency"));
			statement.setDouble(4,  resultantValue);
	       
	        statement.executeUpdate(); 
	  
            // Close all the connections 
            statement.close(); 
            connection.close(); 
	  
			
		} catch (Exception e) {
			response.getWriter().println(e.getMessage());
			return;
		}		
		
		//response.getWriter().println("Result is: " + resultantValue + request.getParameter("targetCurrency"));
		response.getWriter().println("Data is saved");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private double getConverterdValue(String targetValue, double inputCurrency) {
		switch (targetValue) {
		  case "Dollar":
		    return inputCurrency * 1.10;
		  case "Pound":
			    return inputCurrency * 0.86;
		  case "Yen":
			    return inputCurrency * 120.39;
		  case "Yuan":
			    return inputCurrency * 7.71;
		  default:
			  return 0;
	}
	}
}
