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
	public Centrala() {
		
		addPacjent(new Pacjent(0,"96123983764", "Janusz", "Tracz", 12, "Ro¿ana", 6, 16,
				"Bia³ystok"));
		addPacjent(new Pacjent(1,"96123983345", "Jan", "Tra", 11, "Ro¿any", 9, 9,
				"Warszawa"));
		addPacjent(new Pacjent(2,"96123934567", "Marek", "Rafa³", 1, "Bia³a", 2, 3,
				"Poznañ"));
		
addLekarz(new Lekarz("kucharz96", "1234", "Jan", "Wacek", 35, 100, "111111111"));
		
		addLekarz(new Lekarz("jankomuzykant", "12346", "Kamil", "Kowalski", 30, 110, "111111112"));
		
		addLekarz(new Lekarz("cos96", "ser123", "Magda", "Nowakowska", 45, 200, "211111111"));

		
		
		
		
	}
	
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
		if(login.equals("admin") && haslo.equals("admin"))
			return login;
		
		for (Lekarz a : lekarze)
		{
			if(a.getLogin().equals(login) && a.getHaslo().equals(haslo))
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
