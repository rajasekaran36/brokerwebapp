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
BrokerageDaoImpl b = new BrokerageDaoImpl();
TradeDaoImpl t = (TradeDaoImpl)session.getAttribute("trades");
b.processTrades(t.getallTrades());
session.setAttribute("brokerages",b);
%>
<table>
    <tr>
        <th>ID</th><th>UCCCODE</th><th>DATE & TIME</th><th>SCRIP</th><th>TRADETYPE</th><th>QTY</th><th>RATE</th><th>MARKET AMOUNT</th>
        <th>BROKERAGE AMOUNT</th><th>GST</th><th>STT</th><th>STAMP DUTY</th><th>TRANSACTION CHARGES</th><th>SEBI CHARGES</th>
        <th>NET AMMOUNT</th><th>OPTIONS</th>
    </tr>
    <%    
    for(Brokerage x:b.getAllBrokeragesMin()){
    %>
    <tr>
        <td><%=x.getId()%></td>
        <td><%=x.getUccCode()%></td>
        <td><%=x.getTradeDateAndTime()%></td>
        <td><%=x.getScrip()%></td>
        <td><%=x.getTradeType()%></td>
        <td><%=x.getQuantity()%></td>
        <td><%=x.getPrice()%></td>
        <td><%=x.getAmount()%></td>
        <td><%=x.getBrokerage()%></td>
        <td><%=x.getGst()%></td>
        <td><%=x.getSt()%></td>
        <td><%=x.getStampDuty()%></td>
        <td><%=x.getTransactionCharge()%></td>
        <td><%=x.getSebiCharges()%></td>
        <td><%=x.getNetamount()%></td>
        <td><button id='<%=x.getId()%>'onclick="location.href='contract.jsp?id=<%=x.getId()%>'">View Contract</button></td>
    </tr>
    <%
    session.setAttribute("sampbrock",x);
    }
    %>
    <tr>
        <td></td>
    </tr>
</table>
</body>
<html>
