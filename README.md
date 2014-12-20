[![Build Status](https://travis-ci.org/nbouteme/projet-java.svg?branch=master)](https://travis-ci.org/nbouteme/projet-java)

Projet de Java - Gestion de parking
===================================

Résumé
------

Classes:

| Parking                     | Singleton        | Description                                                                      |
|-----------------------------+------------------+----------------------------------------------------------------------------------|
| instance                    | Parking          |                                                                                  |
| getInstance                 | Parking          | Renvoie l'instance unique du parking                                             |
| places                      | ArrayList<Place> | Contient les places du parking                                                   |
| vehiculeExiste(Vehicule v)  | bool             | Renvois true si le vehicule est stationé                                         |
| park(Vehicule v)            | void, throws     | Gare un vehicule                                                                 |
| park(Vehicule v, int place) | void, throws     | Pareil mais a une place donnée                                                   |
| unpark(int place)           | Vehicule         | Enleve un véhicule                                                               |
| etatParking                 | void             | Affiche l'état du parking(Places, et infos sur les voiture stationnée)           |
| bookPlace                   | Place            | Renvois la premiere place libre trouvée                                          |
| bookPlace(int)              | Place            | Renvois une place si celle a l'indice est vide                                   |
| freePlace()                 | void             | throw. Libere une place reservee                                                 |
| getLocation(String immat)   | int              | Renvoie le num de la place d'une voiture selon son immat                         |
| retirerVehicule(immat)      | Voiture          | Retire une voiture de sa place et la renvoie, null si non trouvé                 |
| reorganiserPlace            | void             | Deplace les vehicules sur les place transporteur si une place normal est liberée |


| Abstraite: Place               | Description                   |                        |
|--------------------------------+-------------------------------+------------------------|
| Place                          | Contructeur privé             |                        |
| bool reserve                   | vrai si la place est reservée |                        |
| reserver(bool)                 | void                          | Reserve la place throw |
| liberer()                      | void                          | libere la place, throw |
| Abstraite: park(ITransporteur) | Gare un vehicule transporteur |                        |
| park(IParticulier)             | Gare un vehicule particulier  |                        |

park(IParticulier) n'a pas besoin d'etre abstraite car on sait que
quoi qu'il arrive, un vehicule particulier peut se garer sur n'importe
quel type de place

| PlaceTransporteur   | Place                | Description |
|---------------------+----------------------+-------------|
| park(ITransporteur) | gare un transporteur |             |

| PlaceParticulier    | Place                                             | Description |
|---------------------+---------------------------------------------------+-------------|
| park(ITransporteur) | Throw, on ne peut pas garer un transporteur ici ! |             |

| Abstraite: Vehicule |
| String immatricule  |
| String modele       |
| String marque       |
| String Proprietaire |

| Voiture                                      | Vehicule | implémente IParticulier
| todo: Trouver des caracteristique de voiture |          |

| Moto                                      | Vehicule | implémente IParticulier |
| todo: Trouver des caracteristique de moto |          |                         |

| Camion                                      | Vehicule | implémente ITranporteur |
| todo: Trouver des caracteristique de camion |          |                         |

Interfaces:

| ITransporteur | Interface vide qui garanti qu'un vehicule est transporteur |
| IParticulier  | Interface vide qui garanti qu'un vehicule est Particulier  |


On verra plus tard pour l'interface graphique(swing/AWT) et les factures(serialisable).
