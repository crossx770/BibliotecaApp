CREATE TABLE Carti (
  ID INT AUTO_INCREMENT,
  NAME VARCHAR(255),
  AUTHOR VARCHAR(255),
  GENRE VARCHAR(255),
  CONSTRAINT BOOK_ID_pk PRIMARY KEY (ID)
);

INSERT INTO Carti (name, author, genre)
VALUES ('Harry Potter și Camera Secretelor', 'J. K. Rowling', 'Fantezie');


CREATE TABLE Clienti(
  ID INT AUTO_INCREMENT,
  NAME VARCHAR(255),
  SURNAME VARCHAR(255),
  CITY VARCHAR(255),
  CONSTRAINT Client_ID_pk PRIMARY KEY (ID)
);

INSERT INTO Clienti (name, surname, city)
VALUES ('Andrei', 'Popescu', 'Bucharest');

CREATE TABLE Orders (
    ID BIGINT AUTO_INCREMENT,
    PersonID bigint,
    BookID bigint,
    borowDate DATE,
    CONSTRAINT ORDER_ID_pk PRIMARY KEY (ID),
    CONSTRAINT FK_PersonOrder FOREIGN KEY (PersonID) REFERENCES clienti(ID),
	CONSTRAINT FK_BookOrder FOREIGN KEY (BookID) REFERENCES carti(ID)
);
