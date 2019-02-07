AUTOCOMMIT OFF;

DROP TABLE item;

CREATE TABLE item
(
   item_id      BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
   num          VARCHAR(10) NOT NULL,
   title        VARCHAR(32),
   artist       VARCHAR(32),
   release_date DATE,
   list_price   DECIMAL(5,2),
   price        DECIMAL(5,2),
   CONSTRAINT   pk_item  PRIMARY KEY(item_id)
);

INSERT INTO item VALUES (DEFAULT, 'CD501', 'Diva', 'Annie Lennox', '1992-01-04', 17.97, 13.99);
INSERT INTO item VALUES (DEFAULT, 'CD502', 'Dream of the Blue Turtles', 'Sting', '1985-02-05', 17.97, 14.99);
INSERT INTO item VALUES (DEFAULT, 'CD503', 'Trouble is...', 'Kenny Wayne Shepherd Band', '1997-08-08', 17.97, 14.99);
INSERT INTO item VALUES (DEFAULT, 'CD504', 'Lie to Me', 'Jonny Lang', '1997-08-26', 17.97, 14.99);
INSERT INTO item VALUES (DEFAULT, 'CD505', 'Little Earthquakes', 'Tori Amos', '1992-01-18', 17.97, 14.99);
INSERT INTO item VALUES (DEFAULT, 'CD506', 'Seal', 'Seal', '1991-08-18', 17.97, 14.99);
INSERT INTO item VALUES (DEFAULT, 'CD507', 'Ian Moore', 'Ian Moore', '1993-12-05', 9.97, 9.97);
INSERT INTO item VALUES (DEFAULT, 'CD508', 'So Much for the Afterglow', 'Everclear', '1997-01-19', 16.97, 13.99);
INSERT INTO item VALUES (DEFAULT, 'CD509', 'Surfacing', 'Sarah McLachlan', '1997-12-04', 17.97, 13.99);
INSERT INTO item VALUES (DEFAULT, 'CD510', 'Hysteria', 'Def Leppard', '1987-06-20', 17.97, 14.99);
INSERT INTO item VALUES (DEFAULT, 'CD511', 'A Life of Saturdays', 'Dexter Freebish', '2000-12-06', 16.97, 12.99);
INSERT INTO item VALUES (DEFAULT, 'CD512', 'Human Clay', 'Creed', '1999-10-21', 18.97, 13.28);
INSERT INTO item VALUES (DEFAULT, 'CD513', 'My, I''m Large', 'Bobs', '1987-02-20', 11.97, 11.97);
INSERT INTO item VALUES (DEFAULT, 'CD514', 'So', 'Peter Gabriel', '1986-10-03', 17.97, 13.99);
INSERT INTO item VALUES (DEFAULT, 'CD515', 'Big Ones', 'Aerosmith', '1994-05-08', 18.97, 14.99);
INSERT INTO item VALUES (DEFAULT, 'CD516', '90125', 'Yes', '1983-10-16', 11.97, 11.97);
INSERT INTO item VALUES (DEFAULT, 'CD517', '1984', 'Van Halen', '1984-08-19', 11.97, 11.97);
INSERT INTO item VALUES (DEFAULT, 'CD518', 'Escape', 'Journey', '1981-02-25', 11.97, 11.97);
INSERT INTO item VALUES (DEFAULT, 'CD519', 'Greatest Hits', 'Jay and the Americans', '1966-12-05', 13.99, 9.97);
INSERT INTO item VALUES (DEFAULT, 'CD520', 'Ray of Light', 'Madonna', '2000-12-15', 14.99, 10.97);
INSERT INTO item VALUES (DEFAULT, 'CD521', 'Music', 'Madonna', '2002-02-27', 14.99, 11.97);

COMMIT;