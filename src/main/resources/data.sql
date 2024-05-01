-- Insert users
INSERT INTO Users (LastName, FirstName, Telephone, DateOfBirth, Gender, Email, HashedPassword, RegistrationDate, PreferredLanguage, Profession, HobbiesInterests, EmergencyContact, ProfilePhoto, NotificationPreferences, IsActive, role)
VALUES
    ('Doe', 'John', '123456789', '1990-01-01', 'Male', 'john.doe@example.com', 'hashedpassword1', NOW(), 'English', 'Software Engineer', 'Reading, Cooking', 'Jane Doe (sister) - 987654321', 'john_doe.jpg', 'Email', 'TRUE', 'Driver'),
    ('Smith', 'Emma', '987654321', '1995-05-15', 'Female', 'emma.smith@example.com', 'hashedpassword2', NOW(), 'English', 'Graphic Designer', 'Painting, Traveling', 'Jack Smith (brother) - 123456789', 'emma_smith.jpg', 'Push_Notification', 'TRUE', 'Passenger'),
    ('Johnson', 'Michael', '5551234567', '1988-09-20', 'Male', 'michael.johnson@example.com', 'hashedpassword3', NOW(), 'English', 'Teacher', 'Hiking, Photography', 'Sarah Johnson (wife) - 5559876543', 'michael_johnson.jpg', 'SMS', 'TRUE', 'Driver'),
    ('Brown', 'Sophia', '1112223333', '1982-03-10', 'Female', 'sophia.brown@example.com', 'hashedpassword4', NOW(), 'English', 'Doctor', 'Playing Piano, Yoga', 'James Brown (husband) - 1113332222', 'sophia_brown.jpg', 'Email', 'TRUE', 'Passenger');

-- Insert cars
INSERT INTO Cars (DriverID, Brand, Model, CarYear, Color, LicensePlate, SerialNumber, NumberOfSeats)
VALUES
    (1, 'Toyota', 'Corolla', 2018, 'Blue', 'ABC123', '123456789', 5),
    (3, 'Honda', 'Civic', 2020, 'Red', 'XYZ789', '987654321', 4),
    (4, 'Ford', 'Fusion', 2016, 'Black', 'DEF456', '456789123', 4),
    (2, 'Volkswagen', 'Jetta', 2019, 'White', 'GHI789', '789123456', 5);

-- Insert days of week
INSERT INTO DaysOfWeek (DayName)
VALUES
    ('Monday'),
    ('Tuesday'),
    ('Wednesday'),
    ('Thursday'),
    ('Friday'),
    ('Saturday'),
    ('Sunday');

-- Insert trajets
INSERT INTO Trajets (DepartureAddress, DestinationAddress, DesiredDepartureTime, DesiredArrivalTime, UserID, DayID)
VALUES
    ('2070 Rue de la Falaise, Saint-Lazare', '169 Rue Champlain, Salaberry-de-Valleyfield', '2024-04-18 08:00:00', '2024-04-18 09:00:00', 1, 1),
    ('789 Oak St, City3', '321 Pine St, City4', '2024-04-18 09:00:00', '2024-04-18 10:00:00', 3, 2),
    ('2070 Rue de la Falaise, Saint-Lazare', '169 Rue Champlain, Salaberry-de-Valleyfield', '2024-04-18 10:00:00', '2024-04-18 11:00:00', 4, 3),
    ('876 Birch St, City7', '543 Walnut St, City8', '2024-04-18 11:00:00', '2024-04-18 12:00:00', 2, 4);

-- Insert journeys
INSERT INTO Journeys (DriverID, TrajetID, AvailableSeats, Price, JourneyCreationDate, IsActive)
VALUES
    (1, 1, 4, 10.00, NOW(), 'TRUE'),
    (3, 2, 3, 8.50, NOW(), 'TRUE'),
    (4, 3, 2, 7.00, NOW(), 'TRUE'),
    (2, 4, 1, 6.00, NOW(), 'TRUE');

-- Insert reservations
INSERT INTO Reservations (PassengerID, JourneyID, ReservationDate, ReservationStatus)
VALUES
    (2, 1, NOW(), 'Confirmed'),
    (4, 2, NOW(), 'Pending'),
    (3, 3, NOW(), 'Confirmed'),
    (1, 4, NOW(), 'Pending');

-- Insert ratings
INSERT INTO Ratings (RaterID, RatedID, Score, RatingDate, Comment)
VALUES
    (2, 1, 4, NOW(), 'Great driver, punctual and friendly.'),
    (4, 2, 5, NOW(), 'Excellent journey, would highly recommend.'),
    (3, 3, 3, NOW(), 'Average experience, but overall satisfied.'),
    (1, 4, 2, NOW(), 'Disappointing ride, driver was late and unprofessional.');
