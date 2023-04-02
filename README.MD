# Tudu

Tudu is a task tracking API that allows users to create, manage, and complete tasks in task lists.

## Setup

To run Tudu, you'll need to have the following software installed:

- Java 11 or later
- Maven 3.6 or later
- PostgreSQL 10 or later

You'll also need to set up a PostgreSQL database and create a database user with the appropriate permissions.

To configure Tudu to use your database, update the following properties in `application.yml`:

```
spring:
  datasource:
    url: <your-database-url>
    username: <your-database-username>
    password: <your-database-password>
```

## Installation

To install and run Tudu, follow these steps:

1. Clone the repository: `git clone https://github.com/yourusername/tudu.git`
2. Navigate to the project directory: `cd tudu`
3. Build the project: `mvn clean package`
4. Run the project: `java -jar target/tudu-0.0.1-SNAPSHOT.jar`

You should now be able to access Tudu at `http://localhost:8080`.

## Example curl requests

Here are some example curl requests that you can use to interact with Tudu:

Create a new task list:

```
curl -X POST -H "Content-Type: application/json" -d '{"name":"My task list"}' http://localhost:8080/api/tasklists
```

Create a new task:

```
curl -X POST -H "Content-Type: application/json" -d '{"description":"My task"}' http://localhost:8080/api/tasklists/<task-list-id>/tasks
```

Update a task:

```
curl -X PUT -H "Content-Type: application/json" -d '{"description":"My updated task", "completed": true}' http://localhost:8080/api/tasklists/<task-list-id>/tasks/<task-id>
```

Get all tasks in a task list:

```
curl -X GET http://localhost:8080/api/tasklists/<task-list-id>/tasks
```

Delete a task:

```
curl -X DELETE http://localhost:8080/api/tasklists/<task-list-id>/tasks/<task-id>
```

Please note that these are just examples, and you may need to adjust the URLs and request bodies based on your specific needs and requirements.

## License

Tudu is released under the MIT License. See [LICENSE](LICENSE) for details.
