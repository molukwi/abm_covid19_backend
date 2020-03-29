# ABM Covid-19 Backend

## Versions
* Java 11

## Run
1. run Server.java
1. open http://localhost:8888/api/data?random_encounters=0.01&prob_communities=0.1,0.2,0.3,0.4,0.5
&initial_fraction_infected=0.08&fraction_interacting=0.9&p_infection=0.1&p_contact=1.0

## Parameters
* random_encounters
* prob_communities
* initial_fraction_infected
* fraction_interacting
* p_infection
* p_contact

## Configuration
### scriptPath
Path of the python script which will be executed.
### jsonPath
Path of the generated json by the python script (same path as in the python script)
