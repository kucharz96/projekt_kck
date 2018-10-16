

import java.util.Date;
//data
//kolejny
public class Recepta {
	
	private static int total_id = 0;
	private int id;
	private int id_lekarza;
	private String pesel_pacjenta;
	private Date data;
	private String opis;
	
	public Recepta(int id_lekarza, String pesel_pacjenta, String opis) {
		this.id = total_id++;
		this.id_lekarza = id_lekarza;
		this.pesel_pacjenta = pesel_pacjenta;
		this.data = new Date();
		this.opis = opis;
	}
	
	public int getId() {
		return id;
	}
	public int getId_lekarza() {
		return id_lekarza;
	}
	public void setId_lekarza(int id_lekarza) {
		this.id_lekarza = id_lekarza;
	}
	public String getPesel_pacjenta() {
		return pesel_pacjenta;
	}
	public void setPesel_pacjenta(String pesel_pacjenta) {
		this.pesel_pacjenta = pesel_pacjenta;
	}
	public Date getData() {
		return data;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	
}
