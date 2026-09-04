# Skill-Based Job Recommendation System

A beginner-friendly college placement project built with HTML, CSS, JavaScript, plain Java, JDBC, and MySQL. Students maintain skills, receive ranked job recommendations, apply, and track applications. An administrator can manage jobs and application statuses.

## Features

- Student registration, login, profile updates, and multi-skill selection.
- Search by title/company, filter by location and minimum match.
- Skill-based recommendations, application submission, and tracking.
- Admin login, job add/edit/delete, student list, and application-status updates.

## Requirements

- JDK 17 or later (JDK 21 is also fine)
- MySQL Server 8+
- Visual Studio Code with the Extension Pack for Java (recommended)
- MySQL Connector/J 8.4.0 JAR

## Project Structure

```text
src/model        Simple data classes
src/dao          JDBC SQL operations
src/service      JobRecommendationService matching algorithm
src/controller   HTTP API and small web server
src/util         JDBC configuration and HTTP helpers
web              HTML, CSS and JavaScript pages
database.sql     Database schema and ready-to-use sample data
```

## MySQL and JDBC setup

1. Start MySQL and open MySQL Workbench or a MySQL terminal.
2. Open `database.sql` and run the complete file. It creates `job_recommendation_system` and all sample data.
3. Download the **Platform Independent** MySQL Connector/J ZIP from the official MySQL site. Extract `mysql-connector-j-8.4.0.jar` into the `lib` folder. If the downloaded version differs, either rename the JAR to `mysql-connector-j-8.4.0.jar` or change that name in `run.bat`.
4. Open `src/util/DatabaseConnection.java`. Change `USER` and `PASSWORD` to your MySQL username/password. The default values are `root` / `root`.

## Run in VS Code

1. Open the `Skill-Based-Job-Recommendation-System` folder in VS Code.
2. Complete the database and JDBC steps above.
3. Open the integrated terminal and run `./run.bat` (or double-click `run.bat` in Windows Explorer). In VS Code you can instead use **Terminal → Run Task → Run SkillMatch Jobs**.
4. Wait for `Open http://localhost:8080` and open that address in a browser.
5. Keep the terminal running while using the app. Press `Ctrl+C` to stop the server.

No Node.js, Maven, Tomcat, Spring, or JavaScript framework is needed. The app uses Java's built-in `HttpServer` and JDBC.

## Sample login credentials

| Type | Login | Password |
|---|---|---|
| Student | `student@example.com` | `student123` |
| Admin | `admin` | `admin123` |

## Skill-matching algorithm

`JobRecommendationService.java` converts the student’s selected skills into a set. For each job it counts the required skills also found in that set, then calculates:

`matching percentage = (matched required skills / total required skills) × 100`

For example, a student with Java, SQL, HTML, CSS matches 3 of a job's Java, SQL, HTML, JavaScript requirements, so the result is 75%. Jobs are sorted from highest percentage to lowest. This is simple rule-based matching, not AI or machine learning.

## Screenshots

Add screenshots of the Home, Recommendations, Applications, and Admin pages here after running the project.

## Future enhancements

- Password hashing and server-side session tokens.
- Pagination and richer job filters.
- Resume upload, email notifications, and employer accounts.
- Recommendation weights based on experience or preferred location.
