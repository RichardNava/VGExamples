package com.hazerta.client;

import com.hazerta.db.DAO;
import com.hazerta.models.VideoGame;
import com.hazerta.models.VideoGameManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VideogameClient {

    public static void main(String[] args) {

        VideoGameManager vgm = new VideoGameManager();

//        vgm.distinctVG().forEach(v -> System.out.println("Nombre: " + v.getNombre()
//                + " Valoración: " + v.getValoracion()));
//        vgm.passVG(false).forEach(v -> System.out.println("Nombre: " + v.getNombre()
//                + " Valoración: " + v.getValoracion()));
        try {

//            System.out.println(String.format("%.2f", vgm.averageVG(false)
//                    .orElseThrow(() -> new NullPointerException())));
//            System.out.println(vgm.findFirst("RPG"));
//            vgm.collectList().forEach(v -> System.out.println(v.getValoracion()));
            vgm.toMap().forEach((k,v)-> System.out.println(k+" "+v));
            
        } catch (NoSuchElementException e) {
            System.out.println("No hay videojuegos para establecer la media");
        } catch (NullPointerException e) {
            System.out.println("No hay videojuegos para establecer la media "+ e.getClass().getSimpleName());
        }
    }

}
