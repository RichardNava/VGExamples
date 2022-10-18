package com.hazerta.models;

import com.hazerta.db.DAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VideoGameManager {

    DAO dao;
    List<VideoGame> data = new ArrayList<>();

    public VideoGameManager() {
        try {
            this.dao = new DAO("jdbc:derby://localhost:1527/videogames");
            this.data = dao.videogames;
        } catch (SQLException ex) {
            Logger.getLogger(VideoGameManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Stream<VideoGame> topVG(int n) {
        return data.stream()
                .sorted(Comparator
                        .comparing((VideoGame v) -> v.getValoracion() * 10) // Comparing nos permite establecer el criterio por el que quiero comparar
                        .reversed()
                        .thenComparing((VideoGame v) -> v.getNombre())) // Establecemos un segundo criterio
                .limit(n);
    }

    public Stream<VideoGame> distinctVG() {
        return topVG(10)
                .distinct();
    }

    public Stream<VideoGame> passVG(boolean b) {
        return data.stream()
                .distinct()
                .filter(v -> (b) ? v.getValoracion() >= 5 : v.getValoracion() < 5);
    }

    public OptionalDouble averageVG() {
        return data.stream()
                .peek((VideoGame vg) -> System.out.println("Nombre: " + vg.getNombre()))
                .mapToDouble((VideoGame vg) -> vg.getValoracion())
                .peek((double n) -> System.out.println("Valoración: " + n))
                .average();
    }

    public OptionalDouble averageVG(boolean b) {
        return passVG(b)
                .peek((VideoGame vg) -> System.out.println("Nombre: " + vg.getNombre()))
                .mapToDouble((VideoGame vg) -> vg.getValoracion())
                .peek((double n) -> System.out.println("Valoración: " + n))
                .average();
    }

    public Optional<VideoGame> findFirst(String genre) {
        return distinctVG()
                .parallel() // Mejora el rendimiento pero el proceso tiene una naturaleza estocástica
                .filter(v -> v.getGenero().equals(genre))
                .findAny();
    }

    public List<VideoGame> collectList() {
        return distinctVG()
                .map(v -> {
                    v.setValoracion(v.getValoracion() / 2);
                    return v;
                })
                .collect(Collectors.toList());
    }
    
    public Map<Boolean,List<VideoGame>> toMap(){
        return distinctVG()
                .collect(Collectors.partitioningBy(v->v.getValoracion()>5));
    }

}
