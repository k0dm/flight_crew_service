
FROM amazoncorretto:17-alpine-jdk

# Create a directory
WORKDIR /app

# Copy all the files from the current directory to the image
COPY . .

# build the project avoiding tests
RUN ./gradlew clean build -x test

# Expose port 8081
EXPOSE 8081

# Run the jar file
CMD ["java", "-jar", "./build/libs/flight_crew_service-0.0.1-SNAPSHOT.jar"]