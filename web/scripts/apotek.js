$(document).ready(function() {
    /* Kopplar klick-händelse till knappen getapotekbutton */
    $("#getapotekbutton").click(function() {
        var url = 'ApotekService?ort=';
        /* Läser in innehållet inmatningsfältet och trimmar bort eventuella blanksteg */
        var query = $("#searchplacename").val().trim();
        /* Kontrollerar om något har skrivits in i inmatningsfältet */
        if (query.length === 0)
        {
            alert("Du angav ingen ort!");
            return false;
        }
        /* anropar sökfunktionen med rätt url */
        search(url + query);
    });

    /* Kopplar klick-händelse till knappen getapotekbyzipbutton */
    $("#getapotekbyzipbutton").click(function() {
        var url = 'ApotekService?pn=';
        /* Läser in innehållet inmatningsfältet och trimmar bort eventuella blanksteg */
        var query = $("#searchzip").val().trim();
        /* Kontrollerar om något har skrivits in i inmatningsfältet */
        if (query.length === 0)
        {
            alert("Du angav inget postnummer!");
            return false;
        } else if (query.length !== 6) {
            alert("Du angav fel antal siffror i postnummret!");
            return false;
        }
        /* anropar sökfunktionen med rätt url */
        query = encodeURIComponent(query);
        search(url + query);
    });

    /* Kopplar klick-händelse till knappen getapotekbynamebutton */
    $("#getapotekbynamebutton").click(function() {
        var url = 'ApotekService?namn=';
        /* Läser in innehållet inmatningsfältet och trimmar bort eventuella blanksteg */
        var query = $("#searchname").val().trim();
        /* Kontrollerar om något har skrivits in i inmatningsfältet */
        if (query.length === 0)
        {
            alert("Du angav inget namn!");
            return false;
        }
        /* anropar sökfunktionen med rätt url */
        search(url + query);
    });
});


/* Sökfunktion som utför AJAX-anrop */
function search(url)
{
    $.getJSON(url, function(json) {
        /* Rensar bort gamla rader ur tabellen */
        var table = $("#apotektable tbody").children().remove();
        /* Parsar genom resultatet från webbtjänstanropet, d.v.s. JSON-data*/
        $.parseJSON(json);
        /* Om sökningen inte gav något resultat */
        if (json.apotek.length === 0) {
            alert("Inga apotek hittades!");
            return false;
        }
        /* söker ut tabellkroppen för tabellen med id apotektable*/
        var table = $("#apotektable tbody");

        /* Itererar genom samtliga rader i resultatet */
        $.each(json.apotek, function(i, apotek) {
            /* Skapar en tabell rad för varje abpotek och hämtar ut respektive 
             * attribut för det aktuella apoteket och lägger in raden i tabellen*/
            $('<tr><td>' + apotek.lan
                    + '</td><td>' + apotek.kommun
                    + '</td><td>' + apotek.forsaljningsstalle
                    + '</td><td>' + apotek.adress
                    + '</td><td>' + apotek.postnummer
                    + '</td><td>' + apotek.ort
                    + '</td><td>' + apotek.namn
                    + '</td></tr>').appendTo(table);
        });
        /* Sätter tabellrader med jämna radnummer med CSS-klassen even för visa dessa med annan färg */
        table.find("tr:even").addClass("even");
    }).error(function(jqXHR, textStatus, errorThrown) {
        console.log("error " + textStatus);
        console.log("error thrown: " + errorThrown);
        console.log("incoming Text " + jqXHR.responseText);
    });

}

     