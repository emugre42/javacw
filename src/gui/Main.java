package gui;

public class Main {
    public static void main(String[] args) {
        IDandPasswords id_and_Password = new IDandPasswords();
        LoginPage loginPage = new LoginPage(id_and_Password.getLoginInfo());
    }
}
