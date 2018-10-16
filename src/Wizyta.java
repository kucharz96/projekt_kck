import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Wizyta {
	
	private static int total_id = 0;
	private int id;
	private int id_lekarza;
	private String pesel_pacjenta;
	private Date data;
	private String opis;
	
		public Wizyta(int id_lekarza, String pesel_pacjenta, String opis, String data) {
		this.id = total_id++;
		this.id_lekarza = id_lekarza;
		this.pesel_pacjenta = pesel_pacjenta;
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		
		    try {
				this.data = formatter.parse(data);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
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
	public void setData(String data) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		try {
		    this.data = formatter.parse(data);
		    
		} catch (ParseException e) {
		}
		
	}
	
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	
}
