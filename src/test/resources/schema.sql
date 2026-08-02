DROP TABLE IF EXISTS countrylanguage;
DROP TABLE IF EXISTS city;
DROP TABLE IF EXISTS country;

CREATE TABLE country
(
    Code           VARCHAR(3)     NOT NULL PRIMARY KEY,
    Name           VARCHAR(60)    NOT NULL,
    Continent      VARCHAR(20)    NOT NULL,
    Region         VARCHAR(32)    NOT NULL,
    SurfaceArea    DECIMAL(10, 2) NOT NULL,
    IndepYear      SMALLINT,
    Population     INT            NOT NULL,
    LifeExpectancy DECIMAL(3, 1),
    GNP            DECIMAL(10, 2),
    GNPOld         DECIMAL(10, 2),
    LocalName      VARCHAR(45)    NOT NULL,
    GovernmentForm VARCHAR(45)    NOT NULL,
    HeadOfState    VARCHAR(60),
    Capital        INT,
    Code2          VARCHAR(2)     NOT NULL
);

CREATE TABLE city
(
    ID          INT AUTO_INCREMENT PRIMARY KEY,
    Name        VARCHAR(35) NOT NULL,
    CountryCode VARCHAR(3)  NOT NULL,
    District    VARCHAR(20) NOT NULL,
    Population  INT DEFAULT 0 NOT NULL,
    FOREIGN KEY (CountryCode) REFERENCES country (Code)
);
CREATE INDEX idx_citycountrycode ON city (CountryCode);

CREATE TABLE countrylanguage
(
    CountryCode VARCHAR(3)                 NOT NULL,
    Language    VARCHAR(30)                NOT NULL,
    IsOfficial  VARCHAR(1) DEFAULT 'F' NOT NULL,
    Percentage  DECIMAL(4, 1) DEFAULT 0.0 NOT NULL,
    PRIMARY KEY (CountryCode, Language),
    FOREIGN KEY (CountryCode) REFERENCES country (Code)
);
CREATE INDEX idx_countrylanguagecode ON countrylanguage (CountryCode);