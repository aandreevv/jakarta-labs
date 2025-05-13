<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Menu</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/menu.css">
</head>
<body>
<div class="container">
  <h1>Menu</h1>

  <form method="get" action="${pageContext.request.contextPath}/menu/${menuId}">
    <input type="text" name="searchName" placeholder="Search by name" value="${param.searchName}" />
    <button type="submit">Search</button>
  </form>

  <table>
    <thead>
    <tr>
      <th>Name</th>
      <th>Description</th>
      <th>Price ($)</th>
      <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${menuItems}">
      <tr>
        <td><c:out value="${item.name}" /></td>
        <td><c:out value="${item.description}" /></td>
        <td><c:out value="${item.price}" /></td>
        <td>
          <c:choose>
            <c:when test="${not empty cart && cart.contains(item)}">
              <form method="post" action="form-order">
                <input type="hidden" name="itemId" value="${item.id}" />
                <input type="hidden" name="menuId" value="${menuId}" />
                <input type="hidden" name="action" value="remove" />
                <button type="submit">Remove from Order</button>
              </form>
            </c:when>
            <c:otherwise>
              <form method="post" action="form-order">
                <input type="hidden" name="itemId" value="${item.id}" />
                <input type="hidden" name="menuId" value="${menuId}" />
                <input type="hidden" name="action" value="add" />
                <button type="submit">Add to Order</button>
              </form>
            </c:otherwise>
          </c:choose>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>

<%--  <c:if test="${not empty sessionScope.cart}">--%>
    <form method="post" action="${pageContext.request.contextPath}/submit-order">
      <input type="hidden" name="menuId" value="${menuId}" />
      <button type="submit" class="btn-create-order">Create Order</button>
    </form>
<%--  </c:if>--%>


  <a class="btn-link" href="${pageContext.request.contextPath}/">Back to Home</a>
</div>
</body>
</html>
