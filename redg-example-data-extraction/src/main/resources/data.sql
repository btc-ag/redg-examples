INSERT INTO PRODUCT VALUES
  ('0000000000001', 'eCar Model T', 'The new generation of electric driving is here!', 145000.99, 42),
  ('0000000000002', 'eCar Model Y', 'Y take the sedan if you can have a SUV?', 98000.99, 21),
  ('0000000000003', 'Overly expensive smartphone', NULL, 1449.99, 384),
  ('0000000000004', 'Experimental Alcubierre drive',
   'Ever wanted to see the mighty auroras of Jupiter after a hard day of work? Be the first to try our new Alcubierre drive. Free delivery on earth',
   999999.99, 2),
  ('0000000000005', 'Miniature Black Hole',
   'When a goldfish is too boring, you need the new Minitaure Black Hole in your apartment! Low maintenance, feeds and cleans itself, perfect for bachelors',
   42000, 12);

INSERT INTO ADDRESS VALUES
  (1, 'Oak Grove Dr.', '4800', NULL, 'CA 91109', 'Pasadena', 'United States of America'),
  (2, 'Escherweg', '5', NULL, '26121', 'Oldenburg', 'Germany'),
  (3, 'Somewhere Street', '1234', 'Ap. 4C', 'WA 98101', 'Seattle', 'United States of America');

INSERT INTO CUSTOMER VALUES
  (1, 'Adam', 'Astronaut'),
  (2, 'Dieter', 'Driver'),
  (3, 'Yknot', 'Annewfone'),
  (4, 'Law', 'Student');

INSERT INTO CUSTOMER_ADDRESS VALUES
  (1, 1),
  (2, 2),
  (3, 3),
  (4, 3);

INSERT INTO "ORDER" VALUES
  (1, 1, 0),
  (2, 1, 199.99),
  (3, 1, 5.99),
  (3, 2, 5.99),
  (3, 3, 5.99),
  (4, 1, 19.99);

INSERT INTO PRODUCT_ORDER VALUES
  ('0000000000004', 1, 1),
  ('0000000000001', 2, 1),
  ('0000000000002', 2, 1),
  ('0000000000003', 3, 1),
  ('0000000000003', 3, 2),
  ('0000000000003', 3, 3),
  ('0000000000005', 4, 1);

