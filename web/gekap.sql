drop database if exists gekap;
create database gekap;
use gekap;

create table utente (
idUser int not null auto_increment,
username varchar(20) not null unique,
passwordhash longtext not null,
email varchar(30) not null unique,
nome varchar(30) not null,
cognome varchar(30) not null,
dataDiNascita date not null,
sesso char(1) not null,
via varchar(70) not null,
nCivico int not null,
città varchar(50) not null,
provincia char(2) not null,
CAP char(5) not null,
admin boolean not null,
primary key(iduser)
);

create table categoria (
idCat int not null auto_increment,
nome varchar(30) not null,
primary key(idCat)
);

create table prodotto (
codice int not null auto_increment,
nome mediumtext not null,
genere varchar(30),
trama longtext not null,
anno year not null,
prezzo int not null,
durata int not null,
lingua varchar(20) not null,
listaImmagini longtext not null,
trailer longtext not null,
categoria int not null,
primary key(codice),
foreign key(categoria) references categoria(idCat)
on update cascade
on delete cascade,
fulltext key(nome,trama)
);

create table attore (
id int not null auto_increment,
nome varchar(60) not null,
ruolo varchar(30) not null,
primary key(id)
);

create table prodottoCast (
prodotto int not null,
attore int not null,
primary key(prodotto,attore),
foreign key(prodotto) references prodotto(codice)
on delete cascade,
foreign key(attore) references attore(id)
on delete cascade
);

create table carrello (
utente int not null,
prodotto int not null,
quantitaP int not null,
prezzoTot int not null,
primary key(utente,prodotto),
foreign key(utente) references utente(idUser)
on update cascade
on delete cascade,
foreign key(prodotto) references prodotto(codice)
on update cascade
on delete cascade
);

create table ordine (
idOrdine int not null auto_increment,
utente int not null,
primary key(idOrdine),
foreign key(utente) references utente(idUser)
on update cascade
on delete cascade
);

create table ordineProdotto (
ordine int not null,
prodotto int not null,
primary key(ordine,prodotto),
foreign key(ordine) references ordine(idOrdine)
on update cascade
on delete cascade,
foreign key(prodotto) references prodotto(codice)
on update cascade
on delete cascade
);

create table listaPreferiti (
utente int not null,
primary key(utente),
foreign key(utente) references utente(idUser)
on update cascade
on delete cascade
);

create table listaProdotto (
lista int not null,
prodotto int not null,
primary key(lista,prodotto),
foreign key(lista) references listaPreferiti(utente)
on update cascade
on delete cascade,
foreign key(prodotto) references prodotto(codice)
on update cascade
on delete cascade
);

insert into categoria values
(1, "Film"),
(2, "Documentario"),
(3, "Stand-Up Comedy");

insert into prodotto values
(1, "Fight Club", "Thriller", "Un uomo di trent'anni è insofferente su tutto e la notte non riesce più a dormire. In cerca di qualche luogo dove scaricare la propria ansia si mette a frequentare quei corsi dove gruppi di malati gravi si riuniscono e confessano agli altri le rispettive situazioni. Mentre si lascia andare alla commozione e al pianto di fronte a quello che vede, l'uomo fa la conoscenza prima di Marla Singer poi di Tyler Durden. Lei è una ragazza a sua volta alla deriva, incapace di scelte o decisioni; lui è un tipo deciso e vigoroso con un'idea precisa in testa. Tyler fa saltare per aria l'appartamento dell'uomo e i due vanno a vivere insieme in una casa fatiscente. Deciso a coinvolgerlo nel suo progetto, Tyler lo fa entrare in un 'Fight Club', uno stanzone sotterraneo dove ci si riunisce per picchiarsi e in questo modo sentirsi di nuovo vivi…", 1999, 599, 139, "EN, IT", "1.jpg", "https://www.youtube.com/watch?v=FEqp8tSh1F4", 1),
(2, "Forrest Gump", "Drammatico", "Forrest Gump - un ragazzo non proprio idiota, ma dal basso coefficiente d'intelligenza - racconta la sua storia a coloro che, uno dopo l'altro, si siedono vicino a lui in attesa dell'autobus. È stato in Vietnam, ha stretto la mano a tre presidenti, ha incontrato Elvis, ha assistito ai movimenti studenteschi, ha casualmente fatto esplodere il Watergate, ha suggerito le parole di 'Imagine' a John Lennon e per tutta la vita è stato innamorato di una ragazza conosciuta fin da bambino.", 1994, 799, 142, "EN, IT", "2.jpg", "https://www.youtube.com/watch?v=GKe68fGdNaw&t=70s", 1),
(3, "Eternal Sunshine of the Spotless Mind", "Romantico", "Joel e Clementine sono una coppia molto innamorata. Un giorno però, la ragazza, stanca della sua relazione ormai in fase di declino, decide, mediante un esperimento scientifico, di farsi asportare dalla mente la parte relativa alla storia con Joel. Il giovane, una volta venuto a conoscenza di questo fatto, sceglie di fare altrettanto ma durante il procedimento cambia idea.", 2004, 899, 108, "EN, IT" ,"3.jpg", "https://www.youtube.com/watch?v=2uVgsCwVuUQ", 1),
(4, "Avengers Endgame", "Fantascienza", "Thanos ha schioccato le dita in Avengers: Infinity War e metà della vita sulla Terra si è dissolta all'istante. Adesso un gruppo di guerrieri, inclusi Captain America, Black Widow, Iron Man, Thor e Hulk, uniscono le forze per contrapporsi al genocidio del Titano Pazzo.Dal momento che i nostri eroi non sono stati in grado di fermare Thanos la prima volta, cosa li fa credere di riuscirsi adesso? ", 2019, 1299, 175, "EN, IT", "4.jpg", "https://www.youtube.com/watch?v=l3NjiaBgVas", 1),
(5, "La città incantata", "Animazione", "Chihiro è una ragazzina di dieci anni, capricciosa e testarda, convinta che l'intero universo debba sottostare ai suoi capricci. Quando i suoi genitori, Akio e Yugo, le dicono che devono cambiare casa, la bambina va su tutte le furie e non fa nulla per nascondere la sua rabbia. Abbandonando per sempre la vecchia casa, Chihiro si aggrappa al ricordo dei suoi amici e di un mazzo di fiori, ultime tracce della sua vecchia vita. Arrivati in fondo ad una misteriosa strada senza uscita, Chihiro ed i suoi genitori si trovano davanti ad un immenso edificio rosso sulla cui facciata si apre una galleria senza fine che somiglia ad una gigantesca bocca. Con una certa riluttanza, Chihiro segue i genitori nel tunnel. Il tunnel li conduce ad una città fantasma, dove li aspetta un sontuoso banchetto. Akio e Yugo si gettano famelici sul cibo e vengono trasformati in maiali sotto gli occhi della figlia. Sono scivolati in un mondo abitato da antiche divinità e esseri magici, governato da una strega malvagia, l'arpia Yubaba. Yubaba spiega a Chihiro che i nuovi arrivati vengono trasformati in animali prima di essere uccisi e mangiati. Coloro che riescono a sfuggire a questo tragico destino saranno condannati all'annientamento, quando verrà dimostrato che non servono a nulla. Per sua fortuna, Chihiro trova un alleato nell'enigmatico Haku. Per ritardare il più possibile il terribile giorno della resa dei conti e sopravvivere in un mondo strano e pericoloso, Chichiro dovrà rendersi utile e quindi lavorare. E così la ragazzina rinuncerà alla sua pigrizia, alla sua umanità, alla sua ragione, ai suoi ricordi e addirittura al suo nome…", 2001, 899, 125, "EN, IT, JP","5.jpg", "https://www.youtube.com/watch?v=kZ9xvC-PlKo", 1),
(6, "Humanity", "Stand-up", "Sette anni dopo il suo ultimo speciale, Ricky Gervais scaglia il suo tipico sarcasmo contro l'essere famosi, la mortalità e una società che prende tutto sul personale", 2018, 480, 78, "EN", "6.jpg", "https://www.youtube.com/watch?v=XAwU3Lyn--U", 3),
(7, "Il Nostro Pianeta", "Natura", "Un documentario ambizioso per sperimentare la bellezza del nostro pianeta e scoprire l'impatto che i cambiamenti climatici stanno avendo su tutte le creature viventi",  2019, 399, 76, "EN, IT", "7.jpg", "https://www.youtube.com/watch?v=61suf8S9Hcw", 2),
(8, "Dentro la mente di Bill Gates", "Scienza", "Entra nella mente del miliardario e fondatore di Microsoft Bill Gates mentre parla di chi lo ha influenzato e degli obiettivi ambiziosi che ancora si prefigge", 2019, 700, 83, "EN", "8.jpg", "https://www.youtube.com/watch?v=lsdZXivzWcE", 2),
(9, "Mostly Stories","Stand-up","Barba lunga e zero peli sulla lingua, Segura spiffera verità comiche e amare sui piercing e l'area 51 del corpo degli uomini. E anche la balla che ha raccontato a Tyson", 2016, 450, 73, "EN", "9.jpg", "https://www.youtube.com/watch?v=uDr1W-Dm4do", 3),
(10, "MINIMALISM a documentary about the important things", "Tecnologia", "Documentario che sottolinea la virtù del minimalismo tramite interviste a persone che rifiutano l'ideale americano del consumismo come fonte di felicità", 2016, 299, 78, "EN, IT", "10.jpg", "https://www.youtube.com/watch?v=0Co1Iptd4p4", 2),
(11, "Minions", "Animazione", "La storia dei Minions inizia all'alba dei tempi. Partendo da organismi gialli unicellulari, i Minion si evolvono attraverso i secoli, perennemente al servizio del più spregevole dei padroni. Continuamente senza successo nel preservare questi maestri, dal T-Rex a Napoleone, i Minion si sono ritrovati senza qualcuno da servire e sono caduti in una profonda depressione. Ma un Minion di nome Kevin ha un piano, e lui - insieme all'adolescente ribelle Stuart e all'adorabile piccolo Bob - decide di avventurarsi nel mondo per trovare un nuovo capo malvagio da seguire per sé e i suoi fratelli. Il trio si imbarca in un viaggio emozionante che li condurrà alla loro prossima potenziale padrona, Scarlet Overkill (il Premio Oscar Sandra Bullock), la prima super-cattiva al mondo. Un viaggio che li porterà dalla gelida Antartide alla New York City del 1960, fino ad arrivare a Londra, dove dovranno affrontare la loro sfida più grande: salvare tutti i Minion… ", 2015, 430, 91, "EN, IT", "11.jpg", "https://www.youtube.com/watch?v=KPQgsPX5qdM", 1),
(12, "Bohemian Rhapsody", "Biografia", "Il film ripercorre l'ascesa della band attraverso le loro canzoni iconiche e il sound rivoluzionario, la loro implosione quasi totale mentre le spirali del lifestyle di Mercry sfuggono al controllo e la loro trionfante reunion alla vigilia del Live Aid, dove Mercury, mentre affronta una malattia mortale, guida la band in una delle più grandi performance nella storia della musica rock. Nel mentre, rafforza l'eredità di una band che era sempre più simile a una famiglia, e che continua ad ispirare outsiders, sognatori e amanti della musica fino ad oggi.", 2018, 799, 134, "EN, IT", "12.jpg", "https://www.youtube.com/watch?v=1wg8UOWZGp4", 1),
(13, "Sherlock Holmes", "Giallo", "Nuovo adattamento cinematografico dei racconti di Arthur Conan Doyle, diretto da Guy Ritchie, che rivoluziona look e comportamenti del celebre ispettore e del suo fidato assistente Watson. Il nuovo Sherlock è un donnaiolo, ama fare a pugni ed ha un problema col gioco d'azzardo. La trama del film non è tratta da un singolo racconto di Conan Doyle, ma è una storia originale, in cui Holmes dovrà combattere un nuovo nemico e svelare un pericoloso complotto che potrebbe distruggere il Paese.", 2009, 500, 128, "EN, IT", "13.jpg", "https://www.youtube.com/watch?v=YFLhRlG7Y7I", 1),
(14, "Aladdin", "Avventura", "L'avvincente storia dell'affascinante furfante Aladdin, la coraggiosa e autonoma Principessa Jasmine e il Genio che potrebbero essere la chiave del loro futuro. Diretto da Guy Ritchie, che porta il suo singolare talento per un'azione frenetica e viscerale nella fittizia città portuale di Agrabah, il film è stato scritto da John August e Ritchie sulla base del classico Disney 'Aladdin'. Nella parte del Genio, che fu dell’indimenticabile Robin Williams, il volto nuovo di Will Smith.", 2019, 1340, 128, "EN, IT", "14.jpg", "https://www.youtube.com/watch?v=FLdEVdFZJgk", 1),
(15, "Hilarious", "Stand-Up", "Puledri indomabili, amore dopo il divorzio, pericoli dell'autoerotismo...nessun argomento intimorisce questo comico!", 2010, 330, 83, "EN", "15.jpg", "https://www.youtube.com/watch?v=DmKQQboHBzM",3),
(16, "Joker", "Thriller", "La nascita di un cattivo. Creazione di un criminale figlio della sua società. Una faccia che cambia il sorriso in un ghigno malefico. “Joker” ruota intorno alla figura dell'iconico villain ed è una storia originale, mai vista prima sul grande schermo. L’esplorazione di Arthur Fleck (Joaquin Phoenix), un uomo disprezzato dalla società, non è solo la cruda storia di un personaggio, ma un monito per tutti.", 2019, 499, 122, "EN, IT", "16.jpg", "https://www.youtube.com/watch?v=o7nkJDjuSp4",1);


insert into attore values 
(1,"Edward Norton", "Narratore"),
(2,"Brad Pitt", "Tyler Durden"),
(3,"Helena Bohnam Carter", "Marla Singer"),
(4,"David Fincher", "Regista"),
(5,"Jared Leto", "Angel Face"),
(6,"Meat Loaf", "Robert Bob Paulson"),

(7,"Tom Hanks", "Forrest Gump"),
(8,"Robin Wright", "Jenny Curran"),
(9,"Gary Sinise", "Tenente Dan Taylor"),
(10,"Mykelti Williamson", "Bubba"),
(11,"Robert Zemeckis", "Regista"),
(12,"Sally Field", "Signora Gump"),

(13,"Jim Carrey", "Joel Barish"),
(14,"Kate Winslet", "Clementine Kruczynski"),
(15,"Kirsten Dunst", "Mary"),
(16,"Mark Ruffalo", "Stan"),
(17,"Elijah Wood", "Patrick"),
(18,"Michel Gondry", "Regista"),

(19, "Anthony & Joe Russo", "Regista"),
(20, "Robert Downey Jr", "Tony Stark / Iron Man"),
(21, "Josh Brolin", "Thanos"),
(22, "Chris Hemsworth", "Thor"),
(23, "Chris Evans", "Steve Rogers / Captain America"),
(24, "Mark Ruffalo", "Bruce Banner / Hulk"),

(25, "Hayao Miyazaki", "Regista"),
(26, "Rumi Hiiragi", "Chihiro Ogino"),
(27, "Miyu Irino", "Haku"),
(28, "Bunta Sugawara", "Kamaji"),
(29, "Mari Natsuki", "Yubaba"),
(30, "Yumi Tamai", "Lin"),

(31, "Ricky Gervais" , " "),
(32, "David Attenborough", "Narratore"),
(33, "Bill Gates", "Narratore"),
(34, "Tom Segura", " "),
(35, "Joshua Millburn", "Narratore"),
(36, "Ryan Nicodemus", "Regista"),

(37, "Sandra Bullock", "Scarlett Overkill"),
(38, "Kyle Balda", "Regista"),
(39, "Steve Carell", "Gru"),

(40, "Rami Malek", "Freddie Mercury"),
(41, "Dexter Fletcher", "Regista"),
(42, "Ben Hardy", "Roger Taylor"),
(43, "Joseph Mazzello", "John Deacon"),
(44, "Gwilym Lee", "Brian May"),

(45, "Guy Ritchie", "Regista"),
(46, "Robert Downey Jr", "Sherlock Holmes"),
(47, "Jude Law","Dott. John Watson"),
(48, "Rachel McAdams", "Irene Adler"),
(49, "Mark Strong", "Lord Blackwood"),

(50, "Guy Ritchie", "Regista"),
(51, "Will Smith", "Genio della Lampada"),
(52, "Mena Massoud", "Aladeen"),
(53, "Naomi Scott", "Jasmine"),
(54, "Marwan Kenzari", "Jafar"),
(55, "Louis CK", " "),

(56, "Todd Phillips", "Regista"),
(57, "Joaquin Phoenix", "Arthur Fleck"),
(58, "Robert De Niro", "Murray Franklin"),
(59, "Zazie Beetz", "Sophie Dumond"),
(60, "Frances Conroy", "Penny Fleck");

insert into prodottoCast values
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),

(2, 7),
(2, 8),
(2, 9),
(2, 10),
(2, 11),
(2, 12),

(3, 13),
(3, 14),
(3, 15),
(3, 16),
(3, 17),
(3, 18),

(4, 19),
(4, 20),
(4, 21),
(4, 22),
(4, 23),
(4, 24),

(5, 25),
(5, 26),
(5, 27),
(5, 28),
(5, 29),
(5, 30),

(6, 31),
(7, 32),
(8, 33),
(9, 34),
(10, 35),
(10, 36),

(11, 37),
(11, 38),
(11, 39),

(12, 40),
(12, 41),
(12, 42),
(12, 43),
(12, 44),

(13, 45),
(13, 46),
(13, 47),
(13, 48),
(13, 49),

(14, 50),
(14, 51),
(14, 52),
(14, 53),
(14, 54),
(15, 55),

(16, 56),
(16, 57),
(16, 58),
(16, 59),
(16, 60);

insert into Utente values
(1, "admin1", SHA1("admin1"), "admin1@gekap.it", "Angelica", "Proietto", "1999-03-07", "F", "San Bernardino", 91, "Lioni", "AV", "83047", true),
(2, "admin2", SHA1("admin2"), "admin2@gekap.it", "Francesco Pio", "Covino", "1999-02-19", "M", "Gramsci", 6, "Morra De Sanctis", "AV", "83040", true),
(3, "gscotti", SHA1("milionario"), "gerryscotti@gmail.com", "Virginio", "Scotti", "1956-07-08", "M", "Cristoforo Colombo", 34, "Miradolo Terme", "MI", "80900", false);
