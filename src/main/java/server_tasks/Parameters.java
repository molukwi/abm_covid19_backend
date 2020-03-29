package server_tasks;

import java.util.Arrays;

public class Parameters {
    private float random_encounters;
    private float[] prob_communities;
    private float initial_fraction_infected;
    private float fraction_interacting;
    private float p_infection;
    private float p_contact;

    public Parameters(float random_encounters, float[] prob_communities, float initial_fraction_infected,
                      float fraction_interacting, float p_infection, float p_contact) {
        this.random_encounters = random_encounters;
        this.prob_communities = prob_communities;
        this.initial_fraction_infected = initial_fraction_infected;
        this.fraction_interacting = fraction_interacting;
        this.p_infection = p_infection;
        this.p_contact = p_contact;
    }

    @Override
    public String toString() {
        return random_encounters + " "
                + Arrays.toString(prob_communities) + " "
                + initial_fraction_infected + " "
                + fraction_interacting + " "
                + p_infection + " "
                + p_contact;
    }


    public static class Builder {
        private float random_encounters;
        private float[] prob_communities;
        private float initial_fraction_infected;
        private float fraction_interacting;
        private float p_infection;
        private float p_contact;

        public Builder withRandomEncounters(float random_encounters) {
            this.random_encounters = random_encounters;
            return this;
        }

        public Builder withProbCommunities(float[] prob_communities) {
            this.prob_communities = prob_communities;
            return this;
        }

        public Builder withInitialFractionInfected(float initial_fraction_infected) {
            this.initial_fraction_infected = initial_fraction_infected;
            return this;
        }

        public Builder withFractionInteracting(float fraction_interacting) {
            this.fraction_interacting = fraction_interacting;
            return this;
        }

        public Builder withPInfection(float p_infection) {
            this.p_infection = p_infection;
            return this;
        }

        public Builder withPContact(float p_contact) {
            this.p_contact = p_contact;
            return this;
        }

        public Parameters build() {
            return new Parameters(random_encounters, prob_communities, initial_fraction_infected,
                    fraction_interacting, p_infection,
                    p_contact);
        }

        public float getInitial_fraction_infected() {
            return initial_fraction_infected;
        }

        public float getRandom_encounters() {
            return random_encounters;
        }

        public float[] getProb_communities() {
            return prob_communities;
        }

        public float getP_infection() {
            return p_infection;
        }

        public float getP_contact() {
            return p_contact;
        }
    }
}
