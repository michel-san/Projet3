package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * Create a neighbour
     * @param neighbour
     */
    void createNeighbour(Neighbour neighbour);

    /**
     * Get all my NeighboursFavorite
     * @return {@link List}
     */
    List<Neighbour> getNeighboursFavorite();
    /**
     * Get all my NeighboursById
     * @return {@link List}
     */
    Neighbour getNeighboursById(long id);

    void changeStatus(long neighbourId);

    void removeAllFavorites();
}
