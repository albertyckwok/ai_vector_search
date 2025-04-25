# AI Vector Search example
Using Langchain4j and Oracle database 23ai.

This is a simple Java app to test drive the new feature of vector search using Oracle database 23ai.

## Setup
The following are the steps to get this code running:
1. Provision an Oracle 23ai database.
    - You can use any Oracle Database service, autonomous database (ADB) serverless, Exadata dedicated or cloud@customer, etc. You can even use the free docker container image by running the following command.
    ```bash
    docker run -p 1521:1521 -e ORACLE_PASSWORD=free -e APP_USER=developer -e APP_USER_PASSWORD=free gvenzl/oracle-free:23-slim
    ```
2. Build the application use Maven.
    ```bash
    cd ai_vector_search
    mvn clean package
    ```
3. Setup environment variable to connect to your database created on step 1, then run the app.
    - **Note**: you the the quote if you are using the connection string from OCI database service (DBCS)
    ```bash
    export dbUser='DatabaseUserName'
    export myPwd='<DatabasePassword>'
    export cs='<ConnectionString>'
    java -jar target/vector_embedding-1.0.0.jar
    ```