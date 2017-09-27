==========================
Changes for contractor API
==========================
====
TODO
====

    - Business Logic
    	- DB restructuring based on business models and process.
    	- Implement fetching user from db base on LoginRequest. If no user matched return Not Found. If found create new token session and return 200 ok with LoginResponse.
    	- Implement fetching SessionToken from db by calling security context and return 404 not found or 200 ok.
    	- Implement passing id as user/id. Implement fetching user data by user id from security context.
    	- Implement creating new user logic/registration.
    	- Implement updating user logic.
    	- Implement deactivating/deleting user logic.
    	- Implement fetching jobs based on business logic.
    	- Implement creating jobs logic.
    	- Implement updating jobs logic.
    	- Implement removing jobs logic.

unreleased
==========

Job Status Implementation

    - Refactored job status table.

Firebase Notification

    - Added firebase notification models and logic.
    - Added and implemented RestEasy client.

Code Cleanup

    - Code cleanup

- REST API Logic
    - Implement REST Api logic for created models.

Hibernate

    - Hibernate refactoring

REST API Logic

    - Hibernate refactoring
    - REST Api access points refactoring.

REST API Logic

    - Implement Authorization/Authentication.

DB Restructuring

    - Updated db tables.
    - Recreated entities based on new table models.

Code Refactoring

    - Added controller classes for handling business logic.

Implement and test DB schema/scripts.

    - Refactoring db scripts in init_db.sql .

Code Cleanup

    - Cleaned up unnecessary code.
    - Added basic entry points.
    - Added UserRights enum based on entry points.

Hibernate Integration

    - Added hibernate/postgresql connection.
    - Added drop and create template scripts.
    - BCrypt integration.

Core Development

    - Added base entity classes models.
    - Added base libraries.
    - Added authentication and authorization core models.
    - Code structure refactoring.

Initial import

    - Setup of gradle project.
    - Added RESTFull Api classes.
    - Added CHANGES.rst document.
    - Updated README.md document.