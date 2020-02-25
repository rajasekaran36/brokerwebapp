<%@ page import="com.kgisl.brokermvc.*"%>

<html>
<body>
<h2>Broker Web App</h2>
<%
TradeDaoImpl t = new TradeDaoImpl();
t.loadTradeInfoFromFile("src/main/resources/tradefile-2.csv");
session.setAttribute("trades",t);
%>
<button onclick="location.href='viewall.jsp'"> View All Trade Data</button>
<button onclick="location.href='viewallbrock.jsp'"> View All Brock Data</button>

</body>
</html>