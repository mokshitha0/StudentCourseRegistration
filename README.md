# Student Course Registration API

Spring Boot REST API for registering students for courses. Uses an in-memory `ArrayList`; data resets when the application restarts.

## Run
- Import as an existing Maven project in Spring Tool Suite (STS).
- Run `StudentCourseRegistrationApplication`.
- Default port: `8081`.

## CRUD endpoints
- `POST /students` — register a student
- `GET /students` — list all registrations
- `GET /students/{id}` — get a registration by ID
- `PUT /students/{id}` — update a registration
- `DELETE /students/{id}` — delete a registration

## Custom methods (at least 3)
1. `GET /students/search?name=anu` — case-insensitive partial name search
2. `GET /students/course/Java` — filter registrations by course
3. `GET /students/age-range?minAge=18&maxAge=25` — filter by inclusive age range
4. `GET /students/check-registration?email=student@example.com&courseName=Java` — check whether an email is registered for a course

Duplicate registration of the same email for the same course is rejected.

## Example POST body
```json
{
  "studentName": "Anu Sharma",
  "courseName": "Java",
  "email": "anu@example.com",
  "age": 21
}
```

Set Postman body to **raw → JSON**. `studentId` is assigned by the service.
