<%@ page import="com.kgisl.brokermvc.*"%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>Contract Notice</title>
    <style>
    .invoice-box {
        max-width: 800px;
        margin: auto;
        padding: 30px;
        border: 1px solid #eee;
        box-shadow: 0 0 10px rgba(0, 0, 0, .15);
        font-size: 16px;
        line-height: 24px;
        font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;
        color: #555;
    }
    
    .invoice-box table {
        width: 100%;
        line-height: inherit;
        text-align: left;
    }
    
    .invoice-box table td {
        padding: 5px;
        vertical-align: top;
    }
    
    .invoice-box table tr td:nth-child(2) {
        text-align: right;
    }
    
    .invoice-box table tr.top table td {
        padding-bottom: 20px;
    }
    
    .invoice-box table tr.top table td.title {
        font-size: 45px;
        line-height: 45px;
        color: #333;
    }
    
    .invoice-box table tr.information table td {
        padding-bottom: 40px;
    }
    
    .invoice-box table tr.heading td {
        background: #eee;
        border-bottom: 1px solid #ddd;
        font-weight: bold;
    }
    
    .invoice-box table tr.details td {
        padding-bottom: 20px;
    }
    
    .invoice-box table tr.item td{
        border-bottom: 1px solid #eee;
    }
    
    .invoice-box table tr.item.last td {
        border-bottom: none;
    }
    
    .invoice-box table tr.total td:nth-child(2) {
        border-top: 2px solid #eee;
        font-weight: bold;
    }
    
    @media only screen and (max-width: 600px) {
        .invoice-box table tr.top table td {
            width: 100%;
            display: block;
            text-align: center;
        }
        
        .invoice-box table tr.information table td {
            width: 100%;
            display: block;
            text-align: center;
        }
    }
    
    /** RTL **/
    .rtl {
        direction: rtl;
        font-family: Tahoma, 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;
    }
    
    .rtl table {
        text-align: right;
    }
    
    .rtl table tr td:nth-child(2) {
        text-align: left;
    }
    </style>
</head>

<body>
    <%
    BrokerageDaoImpl im = (BrokerageDaoImpl)session.getAttribute("brokerages");

    //Brokerage b = (Brokerage)session.getAttribute("sampbrock");
    Integer brokerageId = Integer.parseInt(request.getParameter("id"));
    Brokerage b = im.getBrokerageById(brokerageId);
    out.println(1000);
    %>

    <%!
    public static String rounder(Double val){
         return String.format("%.2f", val);  
    }
    %>
    <div class="invoice-box">
        <table cellpadding="0" cellspacing="0">
            <tr class="top">
                <td colspan="2">
                    <table>
                        <tr>
                            <td class="title">
                                <img src="https://www.sparksuite.com/images/logo.png" style="width:100%; max-width:300px;">
                            </td>
                            
                            <td>
                                Invoice #: <%=b.getId()%><br>
                                Trade Date & Time:<%=b.getTradeDateAndTime()%><br>
                                Due: <%=b.getTradeDateAndTime()%>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            
            <tr class="information">
                <td colspan="2">
                    <table>
                        <tr>
                            <td>
                                XYZ Broking, Pvt.<br>
                                Saravavanampatty<br>
                                Coimbatore-641035.
                            </td>
                            
                            <td>
                                Acme Corp.<br>
                                John Doe<br>
                                john@example.com
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            
            <tr class="heading">
                <td>
                    Trade Details
                </td>
                
                <td>
                    Info #
                </td>
            </tr>

            <tr class="details">
                <td>
                    Any Company 
                </td>

                <td>
                    <%=b.getScrip()%>
                </td>
            </tr>
            <tr class="details">
                <td>
                    Trade Type
                </td>
                
                <td>
                    <%=b.getTradeType()%>
                </td>
            </tr>

            <tr class="details">
                <td>
                    Total Quantity
                </td>
                
                <td>
                    <%=b.getQuantity()%>
                </td>
            </tr>

            <tr class="details">
                <td>
                    Rate Per Quantity
                </td>
                
                <td>
                    <%=b.getPrice()%>
                </td>
            </tr>
            <tr class="heading">
                <td>
                    Payment Method
                </td>
                
                <td>
                    Check #
                </td>
            </tr>
            
            <tr class="details">
                <td>
                    Check
                </td>
                
                <td>
                    1000
                </td>
            </tr>
            
            <tr class="heading">
                <td>
                    Break Up
                </td>
                
                <td>
                    Amount
                </td>
            </tr>
            
            <tr class="item">
                <td>
                    Market Amount
                </td>
                
                <td>
                    Rs.  <%=rounder(b.getAmount())%>
                </td>
            </tr>
            <tr class="item">
                <td>
                    Brokerage Amount
                </td>
                
                <td>
                    Rs.  <%=rounder(b.getBrokerage())%>
                </td>
            </tr>
            <tr class="item">
                <td>
                    GST
                </td>
                
                <td>
                    Rs.  <%=rounder(b.getGst())%>
                </td>
            </tr>

            <tr class="item">
                <td>
                    ST
                </td>
                
                <td>
                    Rs.  <%=rounder(b.getSt())%>
                </td>
            </tr>


            <tr class="item">
                <td>
                    Stamp Duty
                </td>
                
                <td>
                    Rs.  <%=rounder(b.getStampDuty())%>
                </td>
            </tr>

            <tr class="item">
                <td>
                    Transaction Charges
                </td>
                
                <td>
                    Rs.  <%=rounder(b.getTransactionCharge())%>
                </td>
            </tr>

            <tr class="item last">
                <td>
                    SEBI Charges
                </td>
                
                <td>
                    Rs.  <%=rounder(b.getSebiCharges())%>
                </td>
            </tr>

            <tr class="total">
                <td></td>
                
                <td>
                   Total: Rs. <%=rounder(b.getNetamount())%>
                </td>
            </tr>
        </table>
    </div>
</body>
</html>