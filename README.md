
# HotelBooker

This project is an API built using Java, Java Spring, Flyway Migrations, PostgresSQL as the database


A API simulates hotel management, where it's possible to create hotels, assign rooms to these hotels, and create reservations for each room. Additionally, each reservation has validation to ensure it is unique for that room and specific dates.


All the documentation and Postman collections will be within the application.


## Instalação e Execução

Clone the repository.

```bash
https://github.com/gu1lherme10-dev/hotelbooker.git
```
To run:
```bash
docker-compose up
```



    
## Database Configuration

To set up the database for the API, you'll need to configure your PostgreSQL database with default settings. Below is an example of how you can do this:

```yaml
POSTGRES_DB: postgres
POSTGRES_USER: postgres
POSTGRES_PASSWORD: postgres
PORT: 5432
```
## Hotel Routes

### GET - /hotels
Returns all created hotels.

### GET - /hotels/:{id}
Returns a specific hotel.

### POST - /hotels
Creates a hotel.

### DELETE - /hotels/:{id}
Deletes a specific hotel.

### PUT - /hotels/:{id}
Updates a specific hotel.

#### Parameters

- **id**: The unique identifier of the hotel.


## Room Routes

### GET - /hotels/{id}/rooms
Returns all rooms created for the hotel.

### GET - /hotels/:{id}/room/:{roomId}
Returns a specific room.

### POST - /hotels/:{id}/rooms
Creates a room for a hotel.

### DELETE - /hotels/:{id}/rooms/:{roomId}
Deletes a specific room.

### PUT - /hotels/:{id}/rooms/:{roomId}
Updates a specific room.

#### Parameters

- **id**: The unique identifier of the hotel.
- **roomId**: The unique identifier of the room.
## Reserves Routes



### GET - /hotels/:{id}/rooms/:{roomId}/reserves
Returns all reserves for a specific room.

### GET - /hotels/:{id}/rooms/:{roomId}/reserves/:{reserveId}
Returns a specific reserve for a room.

### POST - /hotels/:{id}/rooms/:{roomId}/reserves
Creates a reserve for a specific room.

### DELETE - /hotels/:{id}/rooms/:{roomId}/reserves/:{reserveId}
Deletes a specific reserve for a room.

### PUT - /hotels/:{id}/rooms/:{roomId}/reserves/:{reserveId}
Updates a specific reserve for a room.

#### Parameters

- **id**: The unique identifier of the hotel.
- **roomId**: The unique identifier of the room.
- **reserveId**: The unique identifier of the reserve.
