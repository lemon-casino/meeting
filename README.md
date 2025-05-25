# Meeting Project

This is a simple remote meeting project using **Spring Boot** (Java 21) for the backend and **Vue 3** for the frontend.

## Backend

The backend code is located in the `server` directory.

* Build with Maven 3 and JDK 21.
* Provides basic REST APIs to create meetings, list meetings and join a meeting.

To run the backend:

```bash
mvn spring-boot:run -f server/pom.xml
```

## Frontend

The frontend source is located in the `client` directory and uses [Vite](https://vitejs.dev/) with Vue 3.

To start the frontend in development mode:

```bash
npm install
npm run dev
```

Dependencies include `@vitejs/plugin-vue` for Vue support and Tailwind CSS via CDN for quick styling.

This project currently contains only minimal functionality as a starting point for further development of remote meeting features.
