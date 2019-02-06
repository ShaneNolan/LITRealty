Web Application Project
Shane Nolan | K00205031

Agent Functionality:
1.1	-> Working.
Extra   -> Mutli select upload.
		-> Handles special cases such as updating, folder change.
	-> Authorization
		-> Only agents assigned to a property can edit and delete those properties.
	-> Validation, empty fields, xss/sql.

1.2 	-> Working.

1.3 	-> Working.

Customer Functionality
2.1	-> Working.

2.2	-> Working.
Extra	-> Slideshow display.

2.3	-> Working.

2.4	-> Working.

Unique Feature
3	-> Working.

Tracking Cookie
-> AES 256 Encryption
-> Unique ID generator
-> PBKDF2WithHmacSHA256 
	-> Generate a secure symmetric AES key
-> Anti tampering
	-> GCM operation mode

User Tracking
-> Works with Tracking Cookie to display relevant properties based on their searches
-> Frequency of urls searched
-> Handles favourites feature too (also encrypted)
-> Tracking Filter

Home Screen 
-> Both User Tracking and Tracking Cookie display properties relevant to the user, helping to increase "sales"
-> Gets most prominent search
	-> Selects a max of 4 and a minimum of 1 properties based on that search
	-> If there are more than 4 properties, it randomly displays 4.
-> Margin of error
	-> 1/4 of lean way on based price for the sql query to recommended properties

Session Servlet
-> Reduces the number of queries on the database by storing the agents, property types, style types, garage types and vendors in the current session.

SHA256, Salt & 10,000
-> Secure database password hashing with SHA256, randomly generated salt and 10,000 iterations.

Dashboard Security
-> All administrative features are checked with a filter to ensure the user is authenticated.

UI
-> Bootstrap is used throughout the website for a better user experience.