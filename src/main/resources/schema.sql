CREATE TABLE User (
    userID INT AUTO_INCREMENT PRIMARY KEY,
    LastName VARCHAR(255) NOT NULL,
    FirstName VARCHAR(255) NOT NULL,
    Telephone VARCHAR(20) NOT NULL,
    DateOfBirth DATE,
    Gender ENUM('Male', 'Female', 'Other'),
    Email VARCHAR(255) UNIQUE NOT NULL,
    HashedPassword VARCHAR(255) NOT NULL, -- Strong (At least 8 characters with 1 uppercase, lowercase, digit, special character) and must be hashed during registration.
    RegistrationDate DATETIME NOT NULL,
    PreferredLanguage VARCHAR(50) NOT NULL, -- Language entered or retrieved via user's device.
    Profession VARCHAR(100),
    HobbiesInterests TEXT,
    EmergencyContact VARCHAR(255),
    ProfilePhoto VARCHAR(255),
    NotificationPreferences ENUM('Email', 'SMS', 'Push-Notification'),
    IsActive VARCHAR(10), -- TRUE or FALSE
    role ENUM('Driver', 'Passenger') NOT NULL,
);


CREATE TABLE Cars (
    CarID INT AUTO_INCREMENT PRIMARY KEY,
    DriverID INT NOT NULL,
    Brand VARCHAR(100) NOT NULL,
    Model VARCHAR(100) NOT NULL,
    CarYear INT NOT NULL, -- 4 characters
    Color VARCHAR(50) NOT NULL,
    LicensePlate VARCHAR(20) NOT NULL,
    SerialNumber VARCHAR(50) NOT NULL, -- Serial number or identification number (NIV)
    NumberOfSeats INT NOT NULL,
    FOREIGN KEY (DriverID) REFERENCES User(userID)
);

CREATE TABLE DaysOfWeek (
    DayID INT AUTO_INCREMENT PRIMARY KEY,
    DayName VARCHAR(20) NOT NULL
);

CREATE TABLE Trajets (
    TrajetID INT AUTO_INCREMENT PRIMARY KEY,
    DepartureAddress VARCHAR(255) NOT NULL,
    DestinationAddress VARCHAR(255) NOT NULL,
    DesiredDepartureTime DATETIME NOT NULL,
    DesiredArrivalTime DATETIME,
    UserID INT NOT NULL,
    DayID INT NOT NULL,
    FOREIGN KEY (UserID) REFERENCES User(userID),
    FOREIGN KEY (DayID) REFERENCES DaysOfWeek(DayID)
);

CREATE TABLE Journeys (
    JourneyID INT AUTO_INCREMENT PRIMARY KEY,
    DriverID INT NOT NULL,
    TrajetID INT NOT NULL,
    AvailableSeats INT NOT NULL,
    Price DECIMAL(10, 2) NOT NULL,
    JourneyCreationDate DATETIME NOT NULL,
    IsActive VARCHAR(10), -- TRUE or FALSE
    FOREIGN KEY (DriverID) REFERENCES User(userID),
    FOREIGN KEY (TrajetID) REFERENCES Trajets(TrajetID)
);

CREATE TABLE Reservations (
    reservationID INT AUTO_INCREMENT PRIMARY KEY,
    PassengerID INT NOT NULL,
    JourneyID INT NOT NULL,
    ReservationDate DATETIME NOT NULL,
    ReservationStatus ENUM('Confirmed', 'Pending', 'Cancelled') NOT NULL,
    FOREIGN KEY (PassengerID) REFERENCES User(userID),
    FOREIGN KEY (JourneyID) REFERENCES Journeys(journeyID)
);

CREATE TABLE Ratings (
    ratingID INT AUTO_INCREMENT PRIMARY KEY,
    RaterID INT NOT NULL,
    RatedID INT NOT NULL,
    Score INT NOT NULL,
    RatingDate DATETIME NOT NULL,
    Comment TEXT,
    FOREIGN KEY (RaterID) REFERENCES User(userID),
    FOREIGN KEY (RatedID) REFERENCES User(userID)
);
