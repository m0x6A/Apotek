/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import apotek.ApotekHandler;
import apotek.Apotek;
import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author xdr
 */
@WebService(serviceName = "ApotekSoapService")

//@HandlerChain(file = "ApotekSoapService_handler.xml")
public class ApotekSoapService {

	/**
	 * Web service operation
	 *
	 * @return
	 */
	@WebMethod(operationName = "getAll")
	public ArrayList getAll() {
		//TODO write your implementation code here:
		return ApotekHandler.getAll();
	}

	/**
	 * Web service operation
	 *
	 * @param postnummer
	 * @return
	 */
	@WebMethod(operationName = "getByPostnummer")
	public ArrayList<Apotek> getByPostnummer(@WebParam(name = "postnummer") String postnummer) {
		//TODO write your implementation code here:

		return ApotekHandler.getAllByPostnummer(postnummer);
	}

	/**
	 * Web service operation
	 *
	 * @param ort
	 * @return
	 */
	@WebMethod(operationName = "getByOrt")
	public ArrayList<Apotek> getByOrt(@WebParam(name = "ort") String ort) {
		//TODO write your implementation code here:
		return ApotekHandler.getAllByOrt(ort);
	}

	/**
	 * Web service operation
	 *
	 * @param namn
	 * @return
	 */
	@WebMethod(operationName = "getByNamn")
	public ArrayList<Apotek> getByNamn(@WebParam(name = "namn") String namn) {
		//TODO write your implementation code here:
		return ApotekHandler.getAllByNamn(namn);
	}

	/**
	 * Web service operation
	 *
	 * @return
	 */
	@WebMethod(operationName = "getNumberOfApotek")
	public int getNumberOfApotek() {
		//TODO write your implementation code here:
		return ApotekHandler.getNumberOfApotek();
	}
}
