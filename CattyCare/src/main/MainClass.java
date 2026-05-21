package main;

import controller.KucingController;
import view.AplikasiPenitipanView;

public class MainClass {
    public static void main(String[] args){
        AplikasiPenitipanView view = new AplikasiPenitipanView();
        new KucingController(view);
        view.tampilkan();
    }
}