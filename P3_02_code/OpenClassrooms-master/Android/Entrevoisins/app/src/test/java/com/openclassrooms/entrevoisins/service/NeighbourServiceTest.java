package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    /**
     * Unit test on delete Neighbour == -Neighbours
     */
    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    /**
     * The Favorite size == number of Favorites Neighbours
     * // vérifiant que l’onglet Favoris n’affiche que les voisins marqués comme favoris
     */
    @Test
    public void getNeighboursFavoriteWithSuccess() {
        service.removeAllFavorites();
        List<Neighbour> neighbours = service.getNeighbours();
        assertEquals(0, service.getNeighboursFavorite().size());
        assertFalse(neighbours.get(0).isFavorite());
        service.changeStatus(neighbours.get(0).getId());
        assertEquals(1, service.getNeighboursFavorite().size());
        assertEquals(neighbours.get(0).getId(),service.getNeighboursFavorite().get(0).getId());
    }

    /**
     * Unit test  getNeighbourById
     */
    @Test
    public void getNeighboursByIdWithSuccess() {
        assertEquals(1, service.getNeighboursById(1).getId());
        assertNotEquals(1, service.getNeighboursById(3).getId());
        assertNull(service.getNeighboursById(-1));
    }

    @Test
    public void changeStatus() {
        Neighbour neighbourToChangeStatus = service.getNeighbours().get(0);
        service.changeStatus(neighbourToChangeStatus.getId());
        assertTrue(service.getNeighboursById(neighbourToChangeStatus.getId()).isFavorite());
        assertTrue(service.getNeighboursFavorite().contains(neighbourToChangeStatus));
    }
}