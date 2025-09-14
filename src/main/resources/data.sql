-- Insert users (let MySQL auto-generate IDs)
INSERT INTO `users` (name, email, password, role) VALUES
('Admin','admin@gmail.com','$2a$10$OSM43h2ja9LOPohMJO504u8FTw/3Kf8nVb6Sxz36W9GTrCdViR642','ADMIN'),
('User1','user1@gmail.com','$2a$10$HvOP94czbYBu0gph0z60S.CLhw2BItUWzHXbJD7uhM1gSGvgZ7WSC','USER'),
('User2','user2@gmail.com','$2a$10$HqSG6YU7RIJnKzQRFJzexeXfBYJ5QZX294Yd908feZD1WF9TYV/VC','USER');

-- Insert ideal sensors (let MySQL auto-generate IDs - will be 1,2,3,4,5,6 in order)
INSERT INTO `ideal sensors` (temperature_min, temperature_max, humidity_min, humidity_max, conductivity_min, conductivity_max, ph_min, ph_max) VALUES
(18,27,60,70,2.0,3.5,5.5,6.5),  -- Will get ID 1
(18,24,60,70,1.5,2.5,5.5,7.5),  -- Will get ID 2
(16,20,65,75,1.5,2.0,6.0,7.0),  -- Will get ID 3
(15,20,60,70,1.8,2.3,6.0,7.0),  -- Will get ID 4
(18,26,60,70,1.8,2.5,5.5,6.8),  -- Will get ID 5
(15,22,60,70,0.8,1.2,5.5,6.5);  -- Will get ID 6

-- Insert crops (let MySQL auto-generate IDs, reference ideal_sensor IDs 1-6)
INSERT INTO `crops` (description, name, user_id, idealsensor_id) VALUES
('It is redder','Tomato',3,1),
('A vine plant producing large orange fruits. Rich in nutrients and commonly used in soups and desserts','Pumpkin',2,2),
('A cool-season vegetable with white florets. Commonly eaten roasted, steamed, or in soups','Cauliflower',2,3),
('A leafy green vegetable rich in iron and vitamins. Grows best in cool weather. Can be eaten raw or cooked','Spinach',1,4),
('Colorful fruits (green, red, yellow) rich in vitamin C. Grows in warm climates and used fresh or cooked','Bell Pepper',3,5),
('Crisp leafy green vegetable, perfect for salads and sandwiches','Lettuce',1,6);

-- Insert simulated sensors (let MySQL auto-generate IDs, reference crop IDs 1-4)
-- Note: These values should be within the ideal sensor ranges to avoid validation errors
INSERT INTO `simulated sensors` (temperature, humidity, conductivity, ph, crop_id) VALUES
(20.0,65.0,2.2,6.0,1),  -- Tomato: temp 18-27, humidity 60-70, conductivity 2.0-3.5, ph 5.5-6.5
(22.0,65.0,2.0,6.5,2),  -- Pumpkin: temp 18-24, humidity 60-70, conductivity 1.5-2.5, ph 5.5-7.5
(18.0,68.0,1.7,6.2,3),  -- Cauliflower: temp 16-20, humidity 65-75, conductivity 1.5-2.0, ph 6.0-7.0
(17.0,65.0,2.0,6.5,4);  -- Spinach: temp 15-20, humidity 60-70, conductivity 1.8-2.3, ph 6.0-7.0