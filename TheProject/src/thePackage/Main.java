package thePackage;

public class Main {
    public static void main(String[] args) {
        DBHelper.createUsersTable();

        // Ana sayfayı başlat
        new MainPage();

        // İzleme sayfasını ayrı olarak başlat (istersen butonla da açabilirsin)
        new DatabasePage();
    }
}
