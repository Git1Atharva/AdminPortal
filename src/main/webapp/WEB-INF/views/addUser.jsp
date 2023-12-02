
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Add User</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
    <h1>Add User</h1>
        <form action="<%= request.getContextPath() %>/addUser" method="post">

            <div class="form-group">
                <label for="username">Username:</label>

                <input type="text" class="form-control" id="username"  name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="text" class="form-control"  id="password" name="password" required>
            </div>
            <div class="form-group">
                            <label for="password">Role:</label>
                            <input type="text" class="form-control"  id="role" name="role" required>
                        </div>
            <button type="submit" class="btn btn-primary">Add User</button>
        </form>


    </div>
</body>
</html>

