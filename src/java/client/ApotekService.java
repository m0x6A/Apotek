/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.json.JSONException;
import org.json.JSONWriter;

/**
 *
 * @author xdr
 */
@WebServlet(name = "ApotekService", urlPatterns = {"/ApotekService"})
public class ApotekService extends HttpServlet {

	@Resource(name = "apotekdb")
	private DataSource apotekdb;

	/**
	 * Processes requests for both HTTP <code>GET</code> and
	 * <code>POST</code> methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String ort = request.getParameter("ort");
		String postNummer = request.getParameter("pn");
		String namn = request.getParameter("namn");
		String sql = null;

		if (ort != null) {
			sql = "SELECT DISTINCT namn,gatuadress,postort,ägarnamn,länsnamn,postnummer,kommunnamn FROM apotek INNER JOIN postadress on apotek.postnummer=postadress.postnr INNER JOIN ägare on apotek.ägarid=ägare.ägarid INNER JOIN län on apotek.länskod=län.länskod INNER JOIN kommun on apotek.länskod=kommun.länskod AND apotek.kommunkod=kommun.kommunkod WHERE postadress.postort LIKE '%" + ort + "%' ORDER BY postort".toLowerCase();
		}
		if (postNummer != null) {
			sql = "SELECT namn,gatuadress,postort,ägarnamn,länsnamn,postnummer,kommunnamn FROM apotek INNER JOIN postadress on apotek.postnummer=postadress.postnr INNER JOIN ägare on apotek.ägarid=ägare.ägarid INNER JOIN län on apotek.länskod=län.länskod INNER JOIN kommun on apotek.länskod=kommun.länskod AND apotek.kommunkod=kommun.kommunkod WHERE apotek.postnummer='" + postNummer + "' ORDER BY postnummer ASC".toLowerCase();
		}
		if (namn != null) {
			System.out.println(namn);
			sql = "SELECT namn,gatuadress,postort,ägarnamn,länsnamn,postnummer,kommunnamn FROM apotek INNER JOIN postadress on apotek.postnummer=postadress.postnr INNER JOIN ägare on apotek.ägarid=ägare.ägarid INNER JOIN län on apotek.länskod=län.länskod INNER JOIN kommun on apotek.länskod=kommun.länskod AND apotek.kommunkod=kommun.kommunkod WHERE ägare.ägarnamn LIKE '%" + namn + "%' ORDER BY ägarnamn".toLowerCase();
		}

		PrintWriter out = response.getWriter();

		JSONWriter jsonWriter = new JSONWriter(out);

		jsonWriter.object();

		jsonWriter.key("apotek");
		jsonWriter.array();
		try {

			Connection dbConnection = apotekdb.getConnection();
			Statement statement = dbConnection.createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {

				jsonWriter.object();
				jsonWriter.key("lan");
				jsonWriter.value(result.getString("länsnamn"));

				jsonWriter.key("kommun");
				jsonWriter.value(result.getString("kommunnamn"));
				jsonWriter.key("forsaljningsstalle");
				jsonWriter.value(result.getString("namn"));

				jsonWriter.key("adress");
				jsonWriter.value(result.getString("gatuadress"));
				jsonWriter.key("ort");
				jsonWriter.value(result.getString("postort"));
				jsonWriter.key("postnummer");
				jsonWriter.value(result.getString("postnummer"));
				jsonWriter.key("namn");
				jsonWriter.value(result.getString("ägarnamn"));

				jsonWriter.endObject(); 
			}

			jsonWriter.endArray(); 
			jsonWriter.endObject(); 
			result.close();
			statement.close();
			dbConnection.close();
		} catch (SQLException | JSONException ex) {
			Logger.getLogger(ApotekService.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		processRequest(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		processRequest(request, response);

	}
}
