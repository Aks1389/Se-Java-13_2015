package selenium.tests;

public class RunTest {

	public static void main(String[] args) throws Exception {
		LoginTest login = new LoginTest();
		AddFilmPositive newMovie = new AddFilmPositive();
		DelFilm delMovie = new DelFilm();
		Logout logout = new Logout();
		
		login.testLogin();
		newMovie.testAddFilm();
		delMovie.testDelFilm();
		logout.testLogout();
	}

}
