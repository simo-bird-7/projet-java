[![Build Status](<https://travis-ci.org/nbouteme/projet-java.svg?branch=master>)](<https://travis-ci.org/nbouteme/projet-java>)

Projet de Java - Gestion de parking
=================================

Résumé
---

Classes:

<table border="2" cellspacing="0" cellpadding="6" rules="groups" frame="hsides">


<colgroup>
<col   />

<col   />

<col   />
</colgroup>
<thead>
<tr>
<th scope="col" >Parking</th>
<th scope="col" >Singleton</th>
<th scope="col" >Description</th>
</tr>
</thead>

<tbody>
<tr>
<td >instance</td>
<td >Parking</td>
<td >&#xa0;</td>
</tr>


<tr>
<td >getInstance</td>
<td >Parking</td>
<td >Renvoie l'instance unique du parking</td>
</tr>


<tr>
<td >places</td>
<td >ArrayList<Place></td>
<td >Contient les places du parking</td>
</tr>


<tr>
<td >vehiculeExiste(Vehicule v)</td>
<td >bool</td>
<td >Renvois true si le vehicule est stationé</td>
</tr>


<tr>
<td >park(Vehicule v)</td>
<td >void, throws</td>
<td >Gare un vehicule</td>
</tr>


<tr>
<td >park(Vehicule v, int place)</td>
<td >void, throws</td>
<td >Pareil mais a une place donnée</td>
</tr>


<tr>
<td >unpark(int place)</td>
<td >Vehicule</td>
<td >Enleve un véhicule</td>
</tr>


<tr>
<td >etatParking</td>
<td >void</td>
<td >Affiche l'état du parking(Places, et infos sur les voiture stationnée)</td>
</tr>


<tr>
<td >bookPlace</td>
<td >Place</td>
<td >Renvois la premiere place libre trouvée</td>
</tr>


<tr>
<td >bookPlace(int)</td>
<td >Place</td>
<td >Renvois une place si celle a l'indice est vide</td>
</tr>


<tr>
<td >freePlace()</td>
<td >void</td>
<td >throw. Libere une place reservee</td>
</tr>


<tr>
<td >getLocation(String immat)</td>
<td >int</td>
<td >Renvoie le num de la place d'une voiture selon son immat</td>
</tr>


<tr>
<td >retirerVehicule(immat)</td>
<td >Voiture</td>
<td >Retire une voiture de sa place et la renvoie, null si non trouvé</td>
</tr>


<tr>
<td >reorganiserPlace</td>
<td >void</td>
<td >Deplace les vehicules sur les place transporteur si une place normal est liberée</td>
</tr>
</tbody>
</table>

<table border="2" cellspacing="0" cellpadding="6" rules="groups" frame="hsides">


<colgroup>
<col   />

<col   />

<col   />
</colgroup>
<thead>
<tr>
<th scope="col" >Abstraite: Place</th>
<th scope="col" >Description</th>
<th scope="col" >&#xa0;</th>
</tr>
</thead>

<tbody>
<tr>
<td >Place</td>
<td >Contructeur privé</td>
<td >&#xa0;</td>
</tr>


<tr>
<td >bool reserve</td>
<td >vrai si la place est reservée</td>
<td >&#xa0;</td>
</tr>


<tr>
<td >reserver(bool)</td>
<td >void</td>
<td >Reserve la place throw</td>
</tr>


<tr>
<td >liberer()</td>
<td >void</td>
<td >libere la place, throw</td>
</tr>


<tr>
<td >Abstraite: park(ITransporteur)</td>
<td >Gare un vehicule transporteur</td>
<td >&#xa0;</td>
</tr>


<tr>
<td >park(IParticulier)</td>
<td >Gare un vehicule particulier</td>
<td >&#xa0;</td>
</tr>
</tbody>
</table>

park(IParticulier) n'a pas besoin d'etre abstraite car on sait que
quoi qu'il arrive, un vehicule particulier peut se garer sur n'importe
quel type de place

<table border="2" cellspacing="0" cellpadding="6" rules="groups" frame="hsides">


<colgroup>
<col   />

<col   />

<col   />
</colgroup>
<thead>
<tr>
<th scope="col" >PlaceTransporteur</th>
<th scope="col" >Place</th>
<th scope="col" >Description</th>
</tr>
</thead>

<tbody>
<tr>
<td >park(ITransporteur)</td>
<td >gare un transporteur</td>
<td >&#xa0;</td>
</tr>
</tbody>
</table>

<table border="2" cellspacing="0" cellpadding="6" rules="groups" frame="hsides">


<colgroup>
<col   />

<col   />

<col   />
</colgroup>
<thead>
<tr>
<th scope="col" >PlaceParticulier</th>
<th scope="col" >Place</th>
<th scope="col" >Description</th>
</tr>
</thead>

<tbody>
<tr>
<td >park(ITransporteur)</td>
<td >Throw, on ne peut pas garer un transporteur ici !</td>
<td >&#xa0;</td>
</tr>
</tbody>
</table>

<table border="2" cellspacing="0" cellpadding="6" rules="groups" frame="hsides">


<colgroup>
<col   />
</colgroup>
<tbody>
<tr>
<td >Abstraite: Vehicule</td>
</tr>


<tr>
<td >String immatricule</td>
</tr>


<tr>
<td >String modele</td>
</tr>


<tr>
<td >String marque</td>
</tr>


<tr>
<td >String Proprietaire</td>
</tr>
</tbody>
</table>

<table border="2" cellspacing="0" cellpadding="6" rules="groups" frame="hsides">


<colgroup>
<col   />

<col   />

<col   />
</colgroup>
<tbody>
<tr>
<td >Voiture</td>
<td >Vehicule</td>
<td >implémente IParticulier</td>
</tr>


<tr>
<td >todo: Trouver des caracteristique de voiture</td>
<td >&#xa0;</td>
</tr>
</tbody>
</table>

<table border="2" cellspacing="0" cellpadding="6" rules="groups" frame="hsides">


<colgroup>
<col   />

<col   />

<col   />
</colgroup>
<tbody>
<tr>
<td >Moto</td>
<td >Vehicule</td>
<td >implémente IParticulier</td>
</tr>


<tr>
<td >todo: Trouver des caracteristique de moto</td>
<td >&#xa0;</td>
<td >&#xa0;</td>
</tr>
</tbody>
</table>

<table border="2" cellspacing="0" cellpadding="6" rules="groups" frame="hsides">


<colgroup>
<col   />

<col   />

<col   />
</colgroup>
<tbody>
<tr>
<td >Camion</td>
<td >Vehicule</td>
<td >implémente ITranporteur</td>
</tr>


<tr>
<td >todo: Trouver des caracteristique de camion</td>
<td >&#xa0;</td>
<td >&#xa0;</td>
</tr>
</tbody>
</table>

Interfaces:

<table border="2" cellspacing="0" cellpadding="6" rules="groups" frame="hsides">


<colgroup>
<col   />

<col   />
</colgroup>
<tbody>
<tr>
<td >ITransporteur</td>
<td >Interface vide qui garanti qu'un vehicule est transporteur</td>
</tr>


<tr>
<td >IParticulier</td>
<td >Interface vide qui garanti qu'un vehicule est Particulier</td>
</tr>
</tbody>
</table>

On verra plus tard pour l'interface graphique(swing/AWT) et les factures(serialisable).

<table border="2" cellspacing="0" cellpadding="6" rules="groups" frame="hsides">


<colgroup>
<col   />

<col   />
</colgroup>
<tbody>
<tr>
<td >a</td>
<td >b</td>
</tr>


<tr>
<td >c</td>
<td >d</td>
</tr>
</tbody>
</table>
