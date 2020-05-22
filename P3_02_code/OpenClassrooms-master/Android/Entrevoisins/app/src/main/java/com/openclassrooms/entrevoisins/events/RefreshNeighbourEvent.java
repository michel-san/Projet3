package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

    /**
     * Event fired when a user refresh Neighbour
     */
    public class RefreshNeighbourEvent {

        /**
         * refresh Neighbour
         */
        public Neighbour neighbour;

        /**
         * Constructor.
         *
         * @param neighbour
         */
        public RefreshNeighbourEvent(Neighbour neighbour) {
            this.neighbour = neighbour;
        }
    }
