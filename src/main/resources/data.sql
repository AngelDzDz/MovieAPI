INSERT INTO USERS(USERNAME, PASSWORD) VALUES("Angel","$2a$10$8U.54oAMMBCLHlGwdpycv.UC6r4YgOOad8sS3oFmS/fkOuLIFuyRO");


INSERT INTO genres(id,name) VALUES(1,'Drama');


INSERT INTO movies (id, title, fk_genre, director, yearofrelease, description, duration, stars, iswatched,fk_user)
VALUES (1, 'Inception', 1, 'Christopher Nolan', 2010, 'A mind-bending heist in dreams within dreams.', 148, 5, true, 1);

INSERT INTO movies (id, title, fk_genre, director, yearofrelease, description, duration, stars, iswatched, fk_user)
VALUES (2, 'The Grand Budapest Hotel', 1, 'Wes Anderson', 2014, 'A quirky adventure in a luxurious European hotel.', 99, 4, true, 1);

INSERT INTO movies (id, title, fk_genre, director, yearofrelease, description, duration, stars, iswatched, fk_user)
VALUES (3, 'La La Land', 1, 'Damien Chazelle', 2016, 'A romantic musical about chasing dreams in Hollywood.', 128, 4, false,1);

INSERT INTO movies (id, title, fk_genre, director, yearofrelease, description, duration, stars, iswatched, fk_user)
VALUES (4, 'The Shape of Water', 1, 'Guillermo del Toro', 2017, 'A fantastical love story involving a mysterious aquatic creature.', 123, 4, false, 1);

INSERT INTO movies (id, title, fk_genre, director, yearofrelease, description, duration, stars, iswatched,fk_user)
VALUES (5, 'The Martian', 1, 'Ridley Scott', 2015, 'An astronaut stranded on Mars fights for survival.', 144, 4, true, 1);

INSERT INTO movies (id, title, fk_genre, director, yearofrelease, description, duration, stars, iswatched, fk_user)
VALUES (6, 'Coco', 1, 'Lee Unkrich', 2017, 'An animated adventure in the Land of the Dead, exploring family and music.', 105, 5, false, 1);

INSERT INTO movies (id, title, fk_genre, director, yearofrelease, description, duration, stars, iswatched,fk_user)
VALUES (7, 'Interstellar', 1, 'Christopher Nolan', 2014, 'A space odyssey exploring love, time dilation, and the survival of humanity.', 169, 5, false, 1);

INSERT INTO movies (id, title, fk_genre, director, yearofrelease, description, duration, stars, iswatched,fk_user)
VALUES (8, 'Black Panther', 1, 'Ryan Coogler', 2018, 'Marvels superhero saga set in the fictional African nation of Wakanda.', 134, 4, false, 1);

INSERT INTO movies (id, title, fk_genre, director, yearofrelease, description, duration, stars, iswatched, fk_user)
VALUES (9, 'Eternal Sunshine of the Spotless Mind', 1, 'Michel Gondry', 2004, 'A unique love story involving memory erasure.', 108, 5, false, 1);

INSERT INTO movies (id, title, fk_genre, director, yearofrelease, description, duration, stars, iswatched,fk_user)
VALUES (10, 'The Dark Knight', 1, 'Christopher Nolan', 2008, 'Gotham Citys vigilante faces the chaos brought by the Joker.', 152, 5, false,1);

