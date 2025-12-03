package main.ewalletSystem;

import main.ewalletSystem.service.ApplicationService;
import main.ewalletSystem.service.impl.EWalletServiceImpl;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to EWalletSystem");
        ApplicationService applicationService = new EWalletServiceImpl();
        applicationService.startApplication();


    }
}