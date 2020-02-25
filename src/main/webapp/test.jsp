<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@page isELIgnored="false"%>
<html>  
<head>  
<title>Core Tag Example</title>  
</head>  
<body>    
  <c:forEach var="i" begin="1" end="10">
      <p><c:out value="${i}"></c:out></p><br>
  </c:forEach>
</body>  
</html>  