/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apotek;

/**
 *
 * @author xdr
 */
public class Apotek {

	private String lan;
	private String kommun;
	private String forsaljningsstalle;
	private String adress;
	private String postnummer;
	private String ort;
	private String namn; //namn på apoteksbolag

	public String getPostnummer() {
		return postnummer;
	}

	public void setPostnummer(String Postnummer) {
		this.postnummer = Postnummer;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getForsaljningsstalle() {
		return forsaljningsstalle;
	}

	public void setForsaljningsstalle(String forsaljningsstalle) {
		this.forsaljningsstalle = forsaljningsstalle;
	}

	public String getKommun() {
		return kommun;
	}

	public void setKommun(String kommun) {
		this.kommun = kommun;
	}

	public String getLan() {
		return lan;
	}

	public void setLan(String lan) {
		this.lan = lan;
	}

	public String getNamn() {
		return namn;
	}

	public void setNamn(String namn) {
		this.namn = namn;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}
}
