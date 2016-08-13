/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apotek;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xdr
 */
public class ApotekHandler {

	public static ArrayList getAll() {
		ArrayList apotekList = new ArrayList<Apotek>();

		try {
			String url = "jdbc:mysql://atlantis.informatik.umu.se/apoteksdatabas";
			String user = "itsysark";
			String password = "civet4-track";
			Connection dbConnection = DriverManager.getConnection(url, user, password);
			Statement statement = dbConnection.createStatement();
			String sql = "SELECT DISTINCT namn,gatuadress,postort,ägarnamn,länsnamn,postnummer,kommunnamn FROM apotek INNER JOIN postadress on apotek.postnummer=postadress.postnr INNER JOIN ägare on apotek.ägarid=ägare.ägarid INNER JOIN län on apotek.länskod=län.länskod INNER JOIN kommun on apotek.länskod=kommun.länskod AND apotek.kommunkod=kommun.kommunkod";
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				apotek.Apotek a = new Apotek();
				a.setAdress(result.getString("gatuadress"));

				a.setForsaljningsstalle(result.getString("namn"));

				a.setKommun(result.getString("kommunnamn"));

				a.setLan(result.getString("länsnamn"));

				a.setNamn(result.getString("ägarnamn"));

				a.setOrt(result.getString("postort"));

				a.setPostnummer(result.getString("postnummer"));

				apotekList.add(a);
			}
			result.close();
			statement.close();
			dbConnection.close();
		} catch (SQLException ex) {
			Logger.getLogger(ApotekHandler.class.getName()).log(Level.SEVERE, null, ex);
		}
		return apotekList;
	}

	public static ArrayList getAllByPostnummer(String postnummer) {
		ArrayList apotekList = new ArrayList<>();

		try {
			String url = "jdbc:mysql://atlantis.informatik.umu.se/apoteksdatabas";
			String user = "itsysark";
			String password = "civet4-track";

			Connection dbConnection = DriverManager.getConnection(url, user, password);
			Statement statement = dbConnection.createStatement();
			String sql = "SELECT DISTINCT namn,gatuadress,postort,ägarnamn,länsnamn,postnummer,kommunnamn FROM apotek INNER JOIN postadress on apotek.postnummer=postadress.postnr INNER JOIN ägare on apotek.ägarid=ägare.ägarid INNER JOIN län on apotek.länskod=län.länskod INNER JOIN kommun on apotek.länskod=kommun.länskod AND apotek.kommunkod=kommun.kommunkod WHERE apotek.postnummer='" + postnummer + "'";
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				apotek.Apotek a = new Apotek();
				a.setAdress(result.getString("gatuadress"));

				a.setForsaljningsstalle(result.getString("namn"));

				a.setKommun(result.getString("kommunnamn"));

				a.setLan(result.getString("länsnamn"));

				a.setNamn(result.getString("ägarnamn"));

				a.setOrt(result.getString("postort"));

				a.setPostnummer(result.getString("postnummer"));

				apotekList.add(a);
			}
			result.close();
			statement.close();
			dbConnection.close();
		} catch (SQLException ex) {
			Logger.getLogger(ApotekHandler.class.getName()).log(Level.SEVERE, null, ex);
		}
		return apotekList;
	}

	public static ArrayList getAllByOrt(String ort) {
		ArrayList apotekList = new ArrayList<Apotek>();
		byte ptext[] = ort.getBytes(ISO_8859_1);
		String ortEncoded = new String(ptext, UTF_8);
		try {
			String url = "jdbc:mysql://atlantis.informatik.umu.se/apoteksdatabas";
			String user = "itsysark";
			String password = "civet4-track";
			//Connection dbConnection = apotekdb.getConnection();

			Connection dbConnection = DriverManager.getConnection(url, user, password);
			Statement statement = dbConnection.createStatement();
			String sql = "SELECT DISTINCT namn,gatuadress,postort,ägarnamn,länsnamn,postnummer,kommunnamn FROM apotek INNER JOIN postadress on apotek.postnummer=postadress.postnr INNER JOIN ägare on apotek.ägarid=ägare.ägarid INNER JOIN län on apotek.länskod=län.länskod INNER JOIN kommun on apotek.länskod=kommun.länskod AND apotek.kommunkod=kommun.kommunkod WHERE postadress.postort LIKE '%" + ortEncoded + "%'";
			System.out.println(ortEncoded);
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				apotek.Apotek a = new Apotek();
				a.setAdress(result.getString("gatuadress"));
				a.setForsaljningsstalle(result.getString("namn"));
				a.setKommun(result.getString("kommunnamn"));

				a.setLan(result.getString("länsnamn"));

				a.setNamn(result.getString("ägarnamn"));

				a.setOrt(result.getString("postort"));

				a.setPostnummer(result.getString("postnummer"));

				apotekList.add(a);
			}
			result.close();
			statement.close();
			dbConnection.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return apotekList;
	}

	public static ArrayList getAllByNamn(String namn) {
		ArrayList apotekList = new ArrayList<Apotek>();
		byte ptext[] = namn.getBytes(ISO_8859_1);
		String namnEncoded = new String(ptext, UTF_8);
		try {
			String url = "jdbc:mysql://atlantis.informatik.umu.se/apoteksdatabas";

			String user = "itsysark";
			String password = "civet4-track";
			Connection dbConnection = DriverManager.getConnection(url, user, password);
			Statement statement = dbConnection.createStatement();
			String sql = "SELECT DISTINCT namn,gatuadress,postort,ägarnamn,länsnamn,postnummer,kommunnamn FROM apotek INNER JOIN postadress on apotek.postnummer=postadress.postnr INNER JOIN ägare on apotek.ägarid=ägare.ägarid INNER JOIN län on apotek.länskod=län.länskod INNER JOIN kommun on apotek.länskod=kommun.länskod AND apotek.kommunkod=kommun.kommunkod WHERE ägare.ägarnamn LIKE '%" + namnEncoded + "%'";
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				Apotek a = new Apotek();
				a.setAdress(result.getString("gatuadress"));
				a.setForsaljningsstalle(result.getString("namn"));
				a.setKommun(result.getString("kommunnamn"));
				a.setLan(result.getString("länsnamn"));
				a.setNamn(result.getString("ägarnamn"));
				a.setOrt(result.getString("postort"));
				a.setPostnummer(result.getString("postnummer"));
				apotekList.add(a);
			}
			result.close();
			statement.close();
			dbConnection.close();
		} catch (SQLException ex) {
			Logger.getLogger(ApotekHandler.class.getName()).log(Level.SEVERE, null, ex);
		}
		return apotekList;
	}

	public static int getNumberOfApotek() {
		int numberOfApotek = 0;
		try {
			String url = "jdbc:mysql://atlantis.informatik.umu.se/apoteksdatabas";

			String user = "itsysark";
			String password = "civet4-track";
			Connection dbConnection = DriverManager.getConnection(url, user, password);
			Statement statement = dbConnection.createStatement();
			String sql = "SELECT DISTINCT namn,gatuadress,postort,ägarnamn,länsnamn,postnummer,kommunnamn FROM apotek INNER JOIN postadress on apotek.postnummer=postadress.postnr INNER JOIN ägare on apotek.ägarid=ägare.ägarid INNER JOIN län on apotek.länskod=län.länskod INNER JOIN kommun on apotek.länskod=kommun.länskod AND apotek.kommunkod=kommun.kommunkod";
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				numberOfApotek++;
			}
			result.close();
			statement.close();
			dbConnection.close();
		} catch (SQLException ex) {
			Logger.getLogger(ApotekHandler.class.getName()).log(Level.SEVERE, null, ex);
		}

		return numberOfApotek;

	}
}
