<!-- displayData.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
    <title>Display Data</title>
       <link href="style.css" rel="stylesheet">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
     <p>${error}</p>
      <p>${demo}</p>
        <center><h1>Users Data</h1></center>
        <table class="table" style="margin-top: 90px;">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Password</th>
                    <th>Role</th>
                    <th>Action</th>

                </tr>
            </thead>
            <tbody>
                <c:forEach var="data" items="${employeeList}">
                    <tr>
                        <td>${data.userId}</td>
                        <td>${data.userName}</td>
                        <td>${data.userPassword}</td>
                          <td>${data.userRole}</td>
                        <td> <a class="btn btn-primary" href="/updateUser?id=${data.userId}" >Update</a>
                       <a class="btn btn-danger" href="/deleteUser?id=${data.userId}">Delete</a></td>

                    </tr>

            </tbody>
             </c:forEach>

        </table>
        <a class="btn btn-success" href="/addUser">Add User</a></td>


<c:if test="${wrongUser}">
<script>
alert("You don't have access to delete");
</script>
</c:if>
<c:if test="${wrongUpdate}">
<script>
alert("You don't have access to Update");
</script>
</c:if>
<c:if test="${wrong}">
    <script>
    alert("You don't have access to Add User");
    </script>
</c:if>
<c:if test="${validUser}">
   <script>
   alert("Deleted Succesfully !!!");
   </script>
</c:if>
<c:if test="${added}">
           <script>
           alert("User added Succesfully !!!");
           </script>
           </c:if>
           <c:if test="${updated}">
                      <script>
                      alert("User updated Succesfully !!!");
                      </script>
                      </c:if>


    </div>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
   </body>
</html>
