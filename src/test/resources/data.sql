INSERT INTO tipo_veicolo (descrizione) VALUES
	 ('MACCHINA'),
	 ('MOTO'),
	 ('BICI');
INSERT INTO tipo_sospensione (descrizione) VALUES
	 ('SENZA'),
	 ('MONO'),
	 ('BI');
INSERT INTO tipo_freno (descrizione) VALUES
	 ('TAMBURO'),
	 ('DISCO');
INSERT INTO tipo_alimentazione (descrizione) VALUES
	 ('BENZINA'),
	 ('DIESEL'),
	 ('ELETTRICO'),
	 ('MANUALE');
INSERT INTO categoria (descrizione) VALUES
	 ('STRADA'),
	 ('FUORISTRADA'),
	 ('SUV'),
	 ('MOTOCROSS'),
	 ('FRECCIAROSSA');
INSERT INTO messaggi_sistema (cod,lang,messaggio) VALUES
	 ('bici.not.found','IT','bici non trovata'),
	 ('bici.num.marce.invalid','IT','le marce devono essere comprese tra 1 e 3'),
	 ('bici.pieghevole.null','IT','campo pieghevole mancante'),
	 ('bici.tipo.freno.invalid','IT','tipo freno non trovato'),
	 ('bici.tipo.sospensione','IT','tipo sospensione non trovato'),
	 ('bici.tipo.sospensione.invalid','IT','tipo sospensione non valido'),
	 ('macchina.cilindrata.null','IT','campo mancante cilindrata'),
	 ('macchina.porte.invalid','IT','le porte devono essere 3 o 5'),
	 ('macchina.porte.null','IT','campo mancante numero porte'),
	 ('macchina.targa.invalid','IT','la targa deve contenere 7 caratteri di cui prime 2 lettere, successive 3 numeri, ultime 2 lettere');
INSERT INTO messaggi_sistema (cod,lang,messaggio) VALUES
	 ('macchina.targa.null','IT','campo targa mancante'),
	 ('moto.cilindrata.null','IT','campo mancante cilindrata'),
	 ('moto.targa.invalid','IT','la targa deve contenere 6 caratteri di cui prime 2 lettere, successive 2 numeri, ultime 2 lettere'),
	 ('moto.targa.null','IT','campo targa mancante'),
	 ('veicolo.anno.null','IT','campo anno produzione mancante'),
	 ('veicolo.categoria.invalid','IT','categoria non valido'),
	 ('veicolo.categoria.null','IT','campo tipo categoria mancante'),
	 ('veicolo.colore.null','IT','campo colore mancante'),
	 ('veicolo.id.invalid','IT','id veicolo non valido'),
	 ('veicolo.id.null','IT','campo id veicolo mancante');
INSERT INTO messaggi_sistema (cod,lang,messaggio) VALUES
	 ('veicolo.marca.null','IT','campo marca mancante'),
	 ('veicolo.modello.null','IT','campo modello mancante'),
	 ('veicolo.num.ruote.invalid','IT','numero ruote non valido'),
	 ('veicolo.num.ruote.null','IT','campo numero ruote mancante'),
	 ('veicolo.tipo.alim.invalid','IT','tipo alimentazione non valido'),
	 ('veicolo.tipo.alim.null','IT','campo tipo alimentazione mancante'),
	 ('veicolo.tipo.invalid','IT','tipo veicolo non valido'),
	 ('veicolo.tipo.null','IT','campo tipo veicolo mancante');