# Web Application Project

## Agent Functionality:
- Log-in and log-out feature for agents (each agent must be authenticated using their user-name and password from the database. Once logged in, each agent must able to: 
	- [x] View, edit, delete and insert a property to the database. An insertion/update must also include the ability to upload a new/updated image(s) for the property in question.  A property can have multiple images associated with it and your update/insert features must cater for this requirement. Any deletion must require the agent to confirm whether they are sure they want to proceed with this deletion or not.  
	- [x] Each property that appears on the website has a vendor (who has trusted LIT Realty to sell their home). You can assume that each property has one vendor and that, the agent who is responsible for selling the property will manage their details. Only authenticated agents can view vendor information. It is possible that one vendor may be selling more than one property. There is currently no “vendor” table within the database. 
	- [x] Once an agent has been authenticated, every subsequent page they visit must display their (profile) picture. 

- Extra Functionality:
	- [x] Mutli select upload:
	- Handles special cases such as updating, folder change.
	- [x] Authorization
	- Only agents assigned to a property can edit and delete those properties.
	- [x] Validation:
	- Empty fields, xss/sql.

## Customer Functionality
- [x] Every customer will be able to search the database for a property based on its price and location. The search results should be presented in tabular form. This table must include a thumbnail image for the property. As an extra option, the customer should also be able to refine their search results - consider using Data Tables (https://datatables.net/) to help you refine your search results.  
- [x] You must enable the thumbnail image so that it appears as a link that when clicked on, will provide extra information about the property in question (this is in effect a drill-down: extra information such as the square footage of the house, property style, property-type, garage type, number of bathrooms, number of bedrooms as well as details of the agent responsible for selling the house should be displayed – you should also display the larger images for the property). You must also mark on Google maps the location of the property.
- [x] On the drill-down page for each property a customer should be able to add a property to a list of their “favourites”. This list can be viewed at any time by the customer and you must also provide the ability for the customer to remove any property from their list of favourites. The list of favourites must also be available to the customer after their browser session has been terminated. Obviously, each customers list of favourites will be independent of each other. Assume that no customer will access the site from more than one computer. 
- [x] The ability to view the most recently added properties to the system. This is a list of any properties in the database (regardless of their location/price etc) which have been added in the last 7 days.  

- Extra Functionality:
	- [x] Slideshow display.

## Unique Feature
- You must add a unique feature. The feature you add must complement the existing functionality.  
However, the unique feature should include some custom code. Incorporating API's/code from online will only get you so far. 

- [x] Tracking Cookie:
	- AES 256 Encryption
	- Unique ID generator
	- PBKDF2WithHmacSHA256 
		- Generate a secure symmetric AES key
	- Anti tampering
		- GCM operation mode

- [x] User Tracking
	- Works with Tracking Cookie to display relevant properties based on their searches
	- Frequency of urls searched
	- Handles favourites feature too (also encrypted)
	- Tracking Filter

- [x] Home Screen 
	- Both User Tracking and Tracking Cookie display properties relevant to the user, helping to increase "sales"
	- Gets most prominent search
		- Selects a max of 4 and a minimum of 1 properties based on that search
		- If there are more than 4 properties, it randomly displays 4.
	- Margin of error
		- 1/4 of lean way on based price for the sql query to recommended properties

- [x] Session Servlet
	- Reduces the number of queries on the database by storing the agents, property types, style types, garage types and vendors in the current session.

- [x] SHA256, Salt & 10,000
	- Secure database password hashing with SHA256, randomly generated salt and 10,000 iterations.

- [x] Dashboard Security
	- All administrative features are checked with a filter to ensure the user is authenticated.

- [x] UI
	- Bootstrap is used throughout the website for a better user experience.
