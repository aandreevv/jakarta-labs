<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Orders</title>
  <link rel="stylesheet" href="../css/styles.css">
  <link rel="stylesheet" href="../css/orders.css">
</head>
<body>
<div class="container">
  <h1>All Orders</h1>

  <c:choose>
    <c:when test="${not empty orders}">
      <c:forEach var="order" items="${orders}">
        <div class="order-block">
          <h2>Order #<c:out value="${order.id}" /></h2>
          <p>Status:
            <span class="${order.processed ? 'processed' : 'pending'}">
                <c:out value="${order.processed ? 'Processed' : 'Pending'}" />
            </span>
          </p>

          <c:if test="${!order.processed}">
            <form method="post" action="${pageContext.request.contextPath}/admin/orders/process">
              <input type="hidden" name="orderId" value="<c:out value="${order.id}" />"/>
              <button type="submit" class="process-button">Process</button>
            </form>
          </c:if>

          <table>
            <thead>
            <tr>
              <th>Name</th>
              <th>Description</th>
              <th>Price ($)</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${order.items}">
              <tr>
                <td><c:out value="${item.menuItem.name}" /></td>
                <td><c:out value="${item.menuItem.description}" /></td>
                <td><c:out value="${item.menuItem.price}" /></td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
      </c:forEach>
    </c:when>
    <c:otherwise>
      <p>No orders found.</p>
    </c:otherwise>
  </c:choose>

  <a class="btn-link" href="${pageContext.request.contextPath}/">Back to Home</a>
</div>
</body>
</html>
