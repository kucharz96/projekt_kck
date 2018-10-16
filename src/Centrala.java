import java.util.ArrayList;
import java.util.List;
//siemka
//costam
//Jeszcze jedno
//asdasdasdasdsadasd
public class Centrala {
	
	private List<Lekarz> lekarze = new ArrayList<Lekarz>();
	private List<Pacjent> pacjenci = new ArrayList<Pacjent>();
	private List<Recepta> recepty = new ArrayList<Recepta>();
	private List<Skierowanie> skierowania = new ArrayList<Skierowanie>();
	private List<Wizyta> wizyty = new ArrayList<Wizyta>();
	
	
	public List<Lekarz> getLekarze() {
		return lekarze;
	}
	
	public List<Pacjent> getPacjenci() {
		return pacjenci;
	}
	public List<Recepta> getRecepty() {
		return recepty;
	}
	public List<Skierowanie> getSkierowania() {
		return skierowania;
	}
	public List<Wizyta> getWizyty() {
		return wizyty;
	}
	public String Logowanie(String login, String haslo)
	{
		if(login == "admin" && haslo == "admin")
			return login;
		for (Lekarz a : lekarze)
		{
			if(a.getLogin() == login && a.getHaslo() == haslo)
				return login;
		}
		return "";
	}
	public void addLekarz(Lekarz e) {
		lekarze.add(e);
	}
	public void removeLekarz(int index) {
		lekarze.remove(index);
	}
	public void addPacjent(Pacjent e) {
		pacjenci.add(e);
	}
	public void removePacjent(int index) {
		pacjenci.remove(index);
	}

	public void addRecepta(Recepta e) {
		recepty.add(e);
	}
	public void removeRecepta(int index) {
		recepty.remove(index);
	}
	public void addSkierowanie(Skierowanie e) {
		skierowania.add(e);
	}
	public void removeSkierowanie(int index) {
		skierowania.remove(index);
	}
	public void addWizyta(Wizyta e) {
		wizyty.add(e);
	}
	public void removeWizyta(int index) {
		wizyty.remove(index);
	}
	
}
