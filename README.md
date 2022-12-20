### Repo
*spring-il-mio-fotoalbum*

### Todo
Creare un sito che permetta le seguenti funzioni.

Creare un area amminstratore in cui e' possibile:
-   vedere *tutte le foto* inserite (filtrabili per nome e tag)
-   vedere *i dettagli di una singola foto*
-   *aggiungerne di nuove* (con validazione)
-   *modificarle* (con validazione)
-   *cancellarle*

Ovviamente queste operazioni può svolgerle solo lui, quindi l’accesso alle pagine deve essere protetto da autenticazione.

Una foto e' caratterizzata *ALMENO* dalle seguenti caratteristiche, di cui andranno definiti eventuali *vincoli di integrita'*:
-   titolo
-   descrizione
-   url
-   tag
-   visibile
-   categorie

Una foto può essere collegata a più categorie, e una categoria può essere collegata a più foto. Prevedere quindi anche una semplice pagina di creazione, modifica, cancellazione categorie.

Deve essere presente anche una *homepage pubblica*, nella quale le foto (visibili) sono mostrate agli utenti. Devono essere filtrabili per titolo e tag.

Ogni foto può essere commentabile dagli utenti, quindi prevedere nella pagina pubblica di dettaglio un’area per aggiungere un nuovo commento e una per mostrare l’elenco di quelli già inviati. Questi commenti sono inviati in forma anonima, quindi senza autenticazione. Ma andranno invece gestiti tramite *Web API*.

L’area di amministrazione va realizzata sfruttando *Thymeleaf + controller MVC*. Tutte le pagine pubbliche e l’invio dei commenti devono essere gestite tramite *javascript + webapi* (`VueJS` altamente consigliato).
