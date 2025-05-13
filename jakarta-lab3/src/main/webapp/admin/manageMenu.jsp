<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Manage Menu</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/manageMenu.css">
</head>
<body>
<div class="container">
  <h1>Manage Menu</h1>

  <form method="post" action="${pageContext.request.contextPath}/admin/menu">
    <input type="hidden" name="action" value="add"/>
    <input type="text" name="name" placeholder="Name" required/>
    <input type="text" name="description" placeholder="Description" required/>
    <input type="number" name="price" step="0.01" placeholder="Price" min="0" required/>
    <input type="hidden" name="menuId" value="${menuId}">
    <button type="submit" class="add-button">Add Item</button>
  </form>

  <h2>Current Menu</h2>

  <table>
    <thead>
    <tr>
      <th>Name</th>
      <th>Description</th>
      <th>Price</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${menuItems}">
      <tr>
        <td><c:out value="${item.name}" /></td>
        <td><c:out value="${item.description}" /></td>
        <td>$<c:out value="${item.price}" /></td>
        <td>
          <div class="menu-item-actions">
            <form method="post" action="${pageContext.request.contextPath}/admin/menu" style="display:inline;">
              <input type="hidden" name="action" value="delete"/>
              <input type="hidden" name="menuId" value="${menuId}">
              <input type="hidden" name="id" value="${item.id}"/>
              <button type="submit">Delete</button>
            </form>
          </div>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>

  <a class="btn-link" href="${pageContext.request.contextPath}/">Back to Home</a>
</div>
</body>
</html>
