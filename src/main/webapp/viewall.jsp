<%@ page import="com.kgisl.brokermvc.*"%>
<html>
    <style>
        table {
          font-family: arial, sans-serif;
          border-collapse: collapse;
          width: 100%;
        }
        
        td, th {
          border: 1px solid #dddddd;
          text-align: left;
          padding: 8px;
        }
        
        tr:nth-child(even) {
          background-color: #dddddd;
        }
    </style>
<body>
<h2>All Trades</h2>
<%
TradeDaoImpl t = (TradeDaoImpl)session.getAttribute("trades");
%>
<table>
    <tr>
        <th>ID</th><th>UCCCODE</th><th>DATE & TIME</th><th>SCRIP</th><th>TRADETYPE</th><th>QTY</th><th>RATE</th><th>ST TYPE</th><th>GST TYPE</th>
    </tr>
    <%    
    for(Trade x:t.getallTrades()){
    %>
    <tr>
        <td><%=x.getId()%></td>
        <td><%=x.getUccCode()%></td>
        <td><%=x.getDateAndTime()%></td>
        <td><%=x.getScrip()%></td>
        <td><%=x.getTradeType()%></td>
        <td><%=x.getQty()%></td>
        <td><%=x.getRate()%></td>
        <td><%=x.getStType()%></td>
        <td><%=x.getGstType()%></td>
    </tr>
    <%
    }
    %>
    <tr>
        <td></td>
    </tr>
</table>
</body>
<html>
